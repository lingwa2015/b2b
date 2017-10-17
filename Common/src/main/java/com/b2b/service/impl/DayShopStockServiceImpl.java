package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.DayShopStockMapper;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.DayShopStock;
import com.b2b.common.domain.DayShopStockExample;
import com.b2b.common.domain.DayShopStockExample.Criteria;
import com.b2b.service.CustomerService;
import com.b2b.service.DayShopStockService;
import com.b2b.service.ShopItemService;

@Service
public class DayShopStockServiceImpl implements DayShopStockService {
	@Autowired
	private DayShopStockMapper dayShopStockMapper;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ShopItemService shopItemService;
	
	@Override
	public void create(Date endDate) {
		this.delete(endDate);
		List<CustomerUser> shops =this.customerService.findShops();
		for (CustomerUser shop : shops) {
			DayShopStock stock = new DayShopStock();
			Long money = this.shopItemService.findShopItemMoney(shop.getId());
			stock.setSumDate(endDate);
			stock.setCreatedTime(new Date());
			stock.setShopId(shop.getId());
			stock.setMoney(money);
			this.dayShopStockMapper.insert(stock);
		}
	}

	private void delete(Date endDate) {
		DayShopStockExample example = new DayShopStockExample();
		Criteria criteria = example.createCriteria();
		criteria.andSumDateEqualTo(endDate);
		this.dayShopStockMapper.deleteByExample(example);
	}

	@Override
	public DayShopStock findByDate(Date sumDate,Integer id) {
		return this.dayShopStockMapper.findByDate(sumDate,id);
	}

}
