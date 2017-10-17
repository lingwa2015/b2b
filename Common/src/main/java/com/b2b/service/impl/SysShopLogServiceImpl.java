package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.SysShopLogMapper;
import com.b2b.common.domain.SysShopLog;
import com.b2b.service.SysShopLogService;

@Service
public class SysShopLogServiceImpl implements SysShopLogService {
	@Autowired
	private SysShopLogMapper logMapper;
	
	@Override
	public void createLog(SysShopLog sysLog) {
		this.logMapper.insert(sysLog);
	}

	@Override
	public List<SysShopLog> findList(String content, String company,
			Date startTime, Date endTime,Integer cityId) {
		return this.logMapper.findList(content,company,startTime,endTime,cityId);
	}


}
