package com.b2b.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.domain.UpMonthStock;
import com.b2b.common.util.DateUtil;
import com.b2b.service.UpMonthStockService;

@Service
public class UpMonthStockJob {
	private static final Logger logger = LoggerFactory
			.getLogger(UpMonthStockJob.class);
	
	@Autowired
	private UpMonthStockService upMonthStockService;
	
	public void saveMonthStock(){
		logger.warn("开始保存库存快照.................................");
		try {
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH, -1);
			SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
			String saveTime = format.format(c.getTime());
			c.add(Calendar.MONTH, -1);
			String selectTime = format.format(c.getTime());
			SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM-dd");
			Calendar c_first = Calendar.getInstance();
			c_first.add(Calendar.MONTH, -1);
			c_first.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
			String firstDay = format1.format(c_first.getTime());//上个月第一天
			Calendar c_last = Calendar.getInstance();
			c_last.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
			String lastDay = format1.format(c_last.getTime());
			
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.MONTH, 1);
//		c.add(Calendar.MONTH, -1);
//		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
//		String saveTime = format.format(c.getTime());
//		c.add(Calendar.MONTH, -1);
//		String selectTime = format.format(c.getTime());
//		SimpleDateFormat format1 =  new SimpleDateFormat("yyyy-MM-dd");
//		Calendar c_first = Calendar.getInstance();
//		c_first.add(Calendar.MONTH, 1);
//		c_first.add(Calendar.MONTH, -1);
//		c_first.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
//	    String firstDay = format1.format(c_first.getTime());//上个月第一天
//	    Calendar c_last = Calendar.getInstance();
//	    c_last.add(Calendar.MONTH, 1);
//	    c_last.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
//        String lastDay = format1.format(c_last.getTime());
			List<UpMonthStock> upMonthStockList = this.upMonthStockService.findLastMouth(selectTime,DateUtil.parseDateStr(firstDay, "yyyy-MM-dd"),DateUtil.parseDateStr(lastDay, "yyyy-MM-dd"));
			Date date = new Date();
			for (UpMonthStock upMonthStock : upMonthStockList) {
				try {
					upMonthStock.setSaveMonth(saveTime);
					upMonthStock.setCreatedTime(date);
					if(upMonthStock.getNum()==0){
						upMonthStock.setCostPrice(0l);
					}else{
						upMonthStock.setCostPrice(upMonthStock.getTotalCostPrice()/upMonthStock.getNum());
					}
					this.upMonthStockService.insert(upMonthStock);
				} catch (Exception e) {
					logger.error("保存"+saveTime+"快照错误", e);
				}
			}
		} catch (Exception e) {
			logger.error("保存快照错误", e);
		}
		logger.warn("保存库存快照完成.................................");
	}
}
