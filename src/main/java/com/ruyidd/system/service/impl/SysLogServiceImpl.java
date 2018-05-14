package com.ruyidd.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyidd.system.dao.SysLogEntityMapper;
import com.ruyidd.system.entity.SysLogEntityWithBLOBs;
import com.ruyidd.system.service.SysLogService;


/**
 * @author tianxc
 * 日志Service实现类
 */
@Service
public class SysLogServiceImpl implements SysLogService {
	
	@Autowired
	private SysLogEntityMapper sysLogEntityMapper;

	@Override
	public int insert(SysLogEntityWithBLOBs sysLog) {
		return sysLogEntityMapper.insertSelective(sysLog);
	}
	
	
}
