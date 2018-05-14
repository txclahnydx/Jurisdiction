package com.ruyidd.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.ruyidd.annotation.SystemLog;
import com.ruyidd.system.entity.SysLogEntityWithBLOBs;
import com.ruyidd.system.service.SysLogService;
import com.ruyidd.utils.WebUtils;

@Aspect
@Component
public class SystemLogAspect {

	private final static Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
	
	//记录时间
	private ThreadLocal<Long> startTime = new ThreadLocal<>();
	
	private ThreadLocal<StringBuffer> printLogStr = new ThreadLocal<>();
	
	//标记日志是否存储到数据库中,只有方法上有@SystemLog注释的，才存储到数据库
	private ThreadLocal<Boolean> logStorageFlag = new ThreadLocal<>();
	
	private SysLogEntityWithBLOBs sysLog = null;
	
	private StringBuffer logBuffer = null;
	
	@Autowired
	private SysLogService sysLogService;

	@Pointcut("execution(public * com.ruyidd.*.web.*Controller.*(..))")
	public void pointCut() {
	}
	
	@Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        HttpServletRequest request = WebUtils.getRequest();
        HttpSession session = request.getSession();
        sysLog = new SysLogEntityWithBLOBs();
        sysLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        sysLog.setHttpMethod(request.getMethod());
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object o = args[i];
            if(o instanceof ServletRequest || (o instanceof ServletResponse) || o instanceof MultipartFile){
                args[i] = o.toString();
            }
        }
        String str = JSONObject.toJSONString(args);
        sysLog.setParams(str.length()>5000?JSONObject.toJSONString("请求参数数据过长不与显示"):str);
        String ip = WebUtils.getIpAddr();
        if("0.0.0.0".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip) || "localhost".equals(ip) || "127.0.0.1".equals(ip)){
            ip = "127.0.0.1";
        }
        sysLog.setRemoteAddr(ip);
        sysLog.setRequestUri(request.getRequestURL().toString());
        if(session != null){
            sysLog.setSessionId(session.getId());
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemLog mylog = method.getAnnotation(SystemLog.class);
        logStorageFlag.set(false);
        if(mylog != null){
        	logStorageFlag.set(true);
            //注解上的描述
            sysLog.setTitle(mylog.module()+":"+mylog.methods());
            sysLog.setOperationType(mylog.operationType());
        }

        Map<String,String> browserMap = WebUtils.getOsAndBrowserInfo();
        sysLog.setBrowser(browserMap.get("os")+"-"+browserMap.get("browser"));

        if(!"127.0.0.1".equals(ip)){
            Map<String,String> map = WebUtils.getAddressByIP(WebUtils.getIpAddr());
            sysLog.setArea(map.get("area"));
            sysLog.setProvince(map.get("province"));
            sysLog.setCity(map.get("city"));
        }
        sysLog.setType(WebUtils.isAjax(request)?"Ajax请求":"普通请求");
//        if(MySysUser.ShiroUser() != null) {
//            sysLog.setUsername(StringUtils.isNotBlank(MySysUser.nickName()) ? MySysUser.nickName() : MySysUser.loginName());
//        }
          logBuffer = new StringBuffer();
          logBuffer.append("\r\n********************Request Start********************\r\n");
          logBuffer.append("&&&&&&&&&&&&&&&	请求URL:"+request.getRequestURL()+"\r\n");
          logBuffer.append("&&&&&&&&&&&&&&&	请求参数:"+Arrays.toString(args)+"\r\n");
          logBuffer.append("&&&&&&&&&&&&&&&	请求方法:"+joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+"\r\n");
	      printLogStr.set(logBuffer);
    }
	
	@Around("pointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		 Object obj = null;
        try {
        	obj = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
            return obj;
        } catch (Throwable e) {
            e.printStackTrace();
            sysLog.setException(e.getMessage());
            logBuffer = printLogStr.get();
            logBuffer.append("&&&&&&&&&&&&&&&	Exception Msg:"+e.getMessage()+"\r\n");
            logBuffer.append("&&&&&&&&&&&&&&&	共耗时:"+(System.currentTimeMillis() - startTime.get())+" 毫秒"+"\r\n");
            logBuffer.append("********************Request Interrupt********************\r\n");
            logger.error(logBuffer.toString());
            //异常，记录日志
            sysLog.setResponse("Request Or Response Error");
            sysLog.setUseTime(System.currentTimeMillis() - startTime.get());
            sysLogService.insert(sysLog);
            throw e;
        }
    }
	
	@AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void doAfterReturning(Object ret) {
//        if(MySysUser.ShiroUser() != null) {
//            sysLog.setUsername(StringUtils.isNotBlank(MySysUser.nickName()) ? MySysUser.nickName() : MySysUser.loginName());
//        }
        String retString = JSONObject.toJSONString(ret);
       
        sysLog.setResponse(retString.length()>5000?JSONObject.toJSONString("返回参数数据过长不与显示"):retString);
        sysLog.setUseTime(System.currentTimeMillis() - startTime.get());
        //存储到数据库中
        if(logStorageFlag.get()) {
        	sysLogService.insert(sysLog);
        }
        logBuffer = printLogStr.get();
        logBuffer.append("&&&&&&&&&&&&&&&	返回参数:"+retString+"\r\n");
        logBuffer.append("&&&&&&&&&&&&&&&	共耗时:"+(System.currentTimeMillis() - startTime.get())+" 毫秒"+"\r\n");
        logBuffer.append("********************Request End********************\r\n");
        logger.info(logBuffer.toString());
    }

}
