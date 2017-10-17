package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.SysLogMapper;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.SysLogExample;
import com.b2b.common.domain.SysLogExample.Criteria;
import com.b2b.common.domain.PersonUser;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.UserService;

@Service
public class LogServiceImpl implements LogService {

   private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

   	@Autowired
	UserService userService;

    @Autowired
    SysLogMapper sysLogMapper;

	@Override
	public void createLog(SysLog dto) {
        dto.setCreateTime(new Date());
        sysLogMapper.insert(dto);
	}

	@Override
	public List<SysLog> findList(Date startTime, Date endTime, String phone, String content, Integer cityId) {
		return this.sysLogMapper.findList(startTime,endTime,phone,content,cityId); 
	}

}
