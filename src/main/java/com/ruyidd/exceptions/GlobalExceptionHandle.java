package com.ruyidd.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ruyidd.utils.WebUtils;

public class GlobalExceptionHandle implements HandlerExceptionResolver {

	private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception e) {
		logger.error("********************Exception start********************");
		logger.error("Request Method:"+(object==null?"":object.toString()));
		logger.error("Exception:"+e.getMessage());
		if(WebUtils.isAjax(request)) {
			if(e instanceof CustomException) {
				WebUtils.responseResults(response, e.getMessage());
			} else {
				WebUtils.responseResults(response, "您无此操作权限");
			}
		}else {
			logger.error("********************Exception   end********************");
	        return new ModelAndView("/public/error/500");
		}
		logger.error("********************Exception   end********************");
		return null;
	}

}
