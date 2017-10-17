package com.b2b.job;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.Stock;
import com.b2b.common.util.DateUtil;
import com.b2b.service.CustomerService;
import com.b2b.service.OrderService;
import com.b2b.service.StockService;

@Service
public class CustomerLastOrderTime {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private StockService stockService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(CustomerLastOrderTime.class);
	
	public void updateLastOrderTime(){
		logger.warn("开始更新客户最新订单时间.................................");
		try {
			List<CustomerUser> customerList= this.customerService.findAll();
			for (CustomerUser customerUser : customerList) {
				Date time = this.orderService.findNearestTimeByUserId(customerUser.getId());
				logger.warn("更新客户最新订单时间:"+DateUtil.formatDate(time, "yyyy-MM-dd hh:mm:ss"));
				if(time!=null){
					customerUser.setListTime(time);
					this.customerService.update(customerUser);
				}
			}
		} catch (Exception e) {
			logger.error("更新客户最新订单时间",e);
		}
		logger.warn("更新客户最新订单时间完毕.................................");
	}
	
	public void updateStockLastTime(){
		logger.warn("开始更新库存商品最新下单时间.................................");
		try {
			List<Stock> stocks = this.stockService.findByCondition(null, false, 0, 0, null,0,null);
			for (Stock stock : stocks) {
				Date time = this.orderService.findLastTimeByItemId(stock.getItemId());
				if(time!=null){
					stock.setLastTime(time);
					this.stockService.updateLastTime(stock);
				}
			}
		} catch (Exception e) {
			logger.error("更新库存商品最新下单时间",e);
		}
		logger.warn("更新库存商品最新下单时间完毕.................................");
	}
}
