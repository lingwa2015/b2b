package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.SysShopLog;
import com.b2b.page.Page;

public interface SysShopLogService {

	void createLog(SysShopLog sysLog);

	List<SysShopLog> findList(String content, String company, Date startTime, Date endTime, Integer cityId);

}
