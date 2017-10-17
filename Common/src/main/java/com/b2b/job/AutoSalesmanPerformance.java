package com.b2b.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.service.SalesmanPerformanceService;

@Service("autoSalesmanPerformance")
public class AutoSalesmanPerformance {
	private static final Logger logger = LoggerFactory
			.getLogger(AutoSalesmanPerformance.class);
	
	@Autowired
	private SalesmanPerformanceService salesmanPerformanceService;
	
	public void autowork(){
		logger.warn("开始统计业务员业绩.................................");
		try {
			Date date = new Date();
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
			Date endDate=dft.parse(dft.format(date));
			String time = "2016-03-01 00:00:00";
			Date date2 = dft2.parse(time);
			this.salesmanPerformanceService.create(endDate,date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		logger.warn("统计业务员业绩结束.................................");
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		date = calendar.getTime();
		try {
			Date endDate=dft.parse(dft.format(date));
			System.out.println(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
