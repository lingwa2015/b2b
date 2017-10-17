package com.b2b.service.impl;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.UpMonthStockMapper;
import com.b2b.common.domain.UpMonthStock;
import com.b2b.service.OrderService;
import com.b2b.service.UpMonthStockService;

@Service
public class UpMonthStockServiceImpl implements UpMonthStockService {
	
	@Autowired
	private UpMonthStockMapper upMonthStockMapper;
	
	@Autowired
	private OrderService orderService;
	
	@Override
	public List<UpMonthStock> findLastMouth(String selectTime, Date start,
			Date end) {
		return this.upMonthStockMapper.findLastMouth(selectTime, start, end);
	}

	@Override
	public void insert(UpMonthStock upMonthStock) {
		this.upMonthStockMapper.insert(upMonthStock);
	}
	

}
