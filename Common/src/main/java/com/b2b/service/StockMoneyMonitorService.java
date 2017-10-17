package com.b2b.service;

import java.util.Date;

import com.b2b.common.domain.StockMoneyMonitor;
import com.b2b.page.Page;

public interface StockMoneyMonitorService {

	Page<StockMoneyMonitor> pageList(int currentPage, int pageSize, Date startTime, Date endTime, Integer cityId);

}
