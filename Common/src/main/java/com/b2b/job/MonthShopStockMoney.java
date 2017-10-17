package com.b2b.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.domain.MonthShopStock;
import com.b2b.common.domain.ShopItem;
import com.b2b.common.domain.ShopItemStock;
import com.b2b.service.CustomerService;
import com.b2b.service.MonthShopStockService;
import com.b2b.service.ShopItemService;
import com.b2b.service.ShopItemStockService;

@Service
public class MonthShopStockMoney {
	private static final Logger logger = LoggerFactory
			.getLogger(MonthShopStockMoney.class);
	@Autowired
	CustomerService customerService;
	@Autowired
	ShopItemService shopItemService;
	@Autowired
	MonthShopStockService monthShopStockService;
	@Autowired
	ShopItemStockService shopitemsStockService;
	
	public void work(){
		List<HashMap<String,Object>> shop = this.customerService.findAllShop();
		for (HashMap<String, Object> map : shop) {
			Long money = this.shopItemService.findShopItemMoney(Integer.valueOf(map.get("id").toString()));
			MonthShopStock stock = new MonthShopStock();
			stock.setCreatedTime(new Date());
			stock.setMoney(money);
			stock.setShopId(Integer.valueOf(map.get("id").toString()));
			stock.setSumDate(stock.getCreatedTime());
			this.monthShopStockService.create(stock);
		}
	}
	
	public void workItem(){
		try {
			Date date = new Date();
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
			Date endDate = dft2.parse(dft.format(date));
			this.shopitemsStockService.create(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
