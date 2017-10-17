package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopDailyReportMapper;
import com.b2b.common.domain.ShopDailyReport;
import com.b2b.common.domain.ShopDailyReportExample;
import com.b2b.common.domain.ShopDailyReportExample.Criteria;
import com.b2b.service.ShopDailyReportService;

@Service
public class ShopDailyReportServiceImpl implements ShopDailyReportService{
	@Autowired
	private ShopDailyReportMapper shopDailyReportMapper;
	
	@Override
	public ShopDailyReport findByShopIdAndSumdate(Integer id, Date queryDate) {
		return this.shopDailyReportMapper.findByShopIdAndSumdate(id,queryDate);
	}

	@Override
	public void createBySumBatch(ShopDailyReport report) {
		ShopDailyReportExample example = new ShopDailyReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andShopIdEqualTo(report.getShopId());
		criteria.andSumdateEqualTo(report.getSumdate());
		this.shopDailyReportMapper.deleteByExample(example);
		this.shopDailyReportMapper.insert(report);
	}

	@Override
	public List<ShopDailyReport> findByCondition(String userName, Date starttime,Date endtime,String param,int reseauId,Integer cityId) {
		return this.shopDailyReportMapper.findByCondition(userName,starttime,endtime,param,reseauId,cityId);
	}

	@Override
	public List<ShopDailyReport> findNetDailyReport(Date starttime,Date endtime,int reseauId,Integer cityId) {
		return this.shopDailyReportMapper.findNetDailyReport(starttime,endtime,reseauId,cityId);
	}

	@Override
	public List<ShopDailyReport> findAllDailyReport(Integer cityId) {
		return this.shopDailyReportMapper.findAllDailyReport(cityId);
	}

	@Override
	public ShopDailyReport findByReseauIdAndDate(Integer reseauId, Date date) {
		return this.shopDailyReportMapper.findByReseauIdAndDate(reseauId,date);
	}

	@Override
	public ShopDailyReport findByShopIdAndDate(Integer shopId, Date date) {
		return this.shopDailyReportMapper.findByShopIdAndDate(shopId,date);
	}

	@Override
	public ShopDailyReport findByCityIdAndDate(Integer cityId, Date date) {
		return this.shopDailyReportMapper.findByCityIdAndDate(cityId,date);
	}
	
}
