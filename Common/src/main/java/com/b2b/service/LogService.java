package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.SysLog;
import com.b2b.page.Page;

public interface LogService {

	public void createLog(SysLog dto);

	public List<SysLog> findList(Date startTime, Date endTime, String phone, String content, Integer cityId);
}
