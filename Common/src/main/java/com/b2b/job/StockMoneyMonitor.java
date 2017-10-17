package com.b2b.job;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.StockMoneyMonitorMapper;
import com.b2b.common.domain.City;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.Refund;
import com.b2b.common.domain.Stock;
import com.b2b.common.domain.StockCheck;
import com.b2b.common.domain.Storage;
import com.b2b.service.CityService;
import com.b2b.service.OrderService;
import com.b2b.service.RefundService;
import com.b2b.service.StockCheckService;
import com.b2b.service.StockService;
import com.b2b.service.StorageService;

@Service
public class StockMoneyMonitor {
	private static final Logger logger = LoggerFactory
			.getLogger(StockMoneyMonitor.class);
	@Autowired
	StockService stockService;
	
	@Autowired
	StorageService StorageService;
	
	@Autowired
	StockCheckService stockCheckService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	RefundService refundService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	StockMoneyMonitorMapper stockMoneyMonitorMapper;
	
	public void updateStockMoney(){
		logger.info("开始更新库存变更报表.................................");
		try {
			List<City> citys = this.cityService.findAllOpenCity();
			for (City city : citys) {
				Stock stock = new Stock();
				Long stockTotalAmount = stockService.getStockTotalMoneyByCityId(city.getId());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				
				Date start = calendar.getTime();
				
				calendar.add(Calendar.DAY_OF_MONTH, 1);
				calendar.add(Calendar.SECOND, -1);
				
				Date end = calendar.getTime();
				//入库单
				List<Storage> storage = this.StorageService.findByTimeAndCityId(start,end,city.getId());
				Long money = 0l;
				if(!storage.isEmpty()){
					for (Storage storage2 : storage) {
						money += storage2.getTotalFee();
					}
				}
				//正向盘库单
				List<HashMap<String, Object>> list1 = this.stockCheckService.findStockCheckAboveZero(start,end,city.getId());
				Long money1 = 0l;
				if(!list1.isEmpty()){
					for (HashMap<String, Object> map : list1) {
						long a = Long.parseLong(map.get("cost_price").toString());
						long b = Long.parseLong(map.get("num").toString());
						money1 += a*b;
					}
				}
				//负向盘库单
				List<HashMap<String, Object>> list2 = this.stockCheckService.findStockCheckBelowZero(start,end,city.getId());
				Long money2 = 0l;
				if(!list2.isEmpty()){
					for (HashMap<String, Object> map : list2) {
						long a = Long.parseLong(map.get("cost_price").toString());
						long b = Long.parseLong(map.get("num").toString());
						money2 += a*b;
					}
				}
				//订单
				List<Order> list3 = this.orderService.findByTimeAndCityId(start,end,city.getId());
				Long money3 = 0l;
				if(!list3.isEmpty()){
					for (Order order : list3) {
						money3 += order.getTotalCost();
					}
				}
				//退货单
				List<Refund> list4 = this.refundService.findByTimeAndCityId(start,end,city.getId());
				Long money4 = 0l;
				if(!list4.isEmpty()){
					for (Refund refund : list4) {
						money4 += refund.getTotalFee();
					}
				}
				com.b2b.common.domain.StockMoneyMonitor monitor = new com.b2b.common.domain.StockMoneyMonitor();
				monitor.setCityId(city.getId());
				monitor.setStockMoney(stockTotalAmount);
				monitor.setInStockMoney(money+money1+money4);
				monitor.setOutStockMoney(-money3+money2);
				monitor.setChangeMoney(money+money1+money4-money3+money2);
				monitor.setCreateTime(new Date());
				this.stockMoneyMonitorMapper.insert(monitor);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新库存变更报表出错................................."+e);
		}
	    logger.info("更新库存变更报表完毕.................................");
	}	    
}    