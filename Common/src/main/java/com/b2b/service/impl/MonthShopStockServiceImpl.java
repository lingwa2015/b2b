package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.MonthShopStockMapper;
import com.b2b.common.domain.MonthShopStock;
import com.b2b.service.MonthShopStockService;

@Service
public class MonthShopStockServiceImpl implements MonthShopStockService{
	@Autowired
	MonthShopStockMapper monthShopStockMapper;
	
	@Override
	public void create(MonthShopStock stock) {
		// TODO Auto-generated method stub
		this.monthShopStockMapper.insert(stock);
	}

}
