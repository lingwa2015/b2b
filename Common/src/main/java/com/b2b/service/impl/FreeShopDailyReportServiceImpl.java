package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.FreeShopDailyReportMapper;
import com.b2b.common.domain.FreeDayReport;
import com.b2b.common.domain.FreeShopDailyReport;
import com.b2b.common.domain.FreeShopDailyReportExample;
import com.b2b.common.domain.FreeShopDailyReportExample.Criteria;
import com.b2b.service.FreeShopDailyReportService;

@Service
public class FreeShopDailyReportServiceImpl implements FreeShopDailyReportService{
	@Autowired
	FreeShopDailyReportMapper freeShopDailyReportMapper;

	@Override
	public void insert(FreeShopDailyReport report) {
		FreeShopDailyReportExample example = new FreeShopDailyReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andShopIdEqualTo(report.getShopId());
		criteria.andSumDateEqualTo(report.getSumDate());
		this.freeShopDailyReportMapper.deleteByExample(example);
		this.freeShopDailyReportMapper.insert(report);
	}

	@Override
	public List<FreeShopDailyReport> findNetDailyReport(Date starttime, Date endtime,
			int reseauId,Integer cityId) {
		return this.freeShopDailyReportMapper.findNetDailyReport(starttime,endtime,reseauId,cityId);
	}

	@Override
	public List<FreeShopDailyReport> findAllDailyReport(Integer cityId) {
		return this.freeShopDailyReportMapper.findAllDailyReport(cityId);
	}

	@Override
	public FreeShopDailyReport findByReseauIdAndDate(Integer reseauId,
			Date date) {
		return this.freeShopDailyReportMapper.findByReseauIdAndDate(reseauId,date);
	}

	@Override
	public FreeShopDailyReport findByCityIdAndDate(Integer cityId, Date date) {
		return this.freeShopDailyReportMapper.findByCityIdAndDate(cityId,date);
	}
	
	
}
