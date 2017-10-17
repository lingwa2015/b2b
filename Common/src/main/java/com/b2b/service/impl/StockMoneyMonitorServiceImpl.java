package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.StockMoneyMonitorMapper;
import com.b2b.common.domain.StockMoneyMonitor;
import com.b2b.page.Page;
import com.b2b.service.StockMoneyMonitorService;

@Service
public class StockMoneyMonitorServiceImpl implements StockMoneyMonitorService {
	@Autowired
	private StockMoneyMonitorMapper stockMoneyMonitorMapper;
	
	@Override
	public Page<StockMoneyMonitor> pageList(int currentPage, int pageSize, Date startTime, Date endTime, Integer cityId) {
		int count = this.stockMoneyMonitorMapper.count(startTime,endTime,cityId);
		if (count ==0) {
			return new Page<StockMoneyMonitor>();
		}
		int start = Page.calStartRow(currentPage, pageSize);
		List<StockMoneyMonitor> list = this.stockMoneyMonitorMapper.findPageList(start,pageSize,startTime,endTime,cityId);
		Page<StockMoneyMonitor> page = new Page<StockMoneyMonitor>(currentPage,count,pageSize,list);
		return page;
	}

	
}
