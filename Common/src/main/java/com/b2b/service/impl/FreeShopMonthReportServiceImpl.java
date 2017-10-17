package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.FreeShopMonthReportMapper;
import com.b2b.common.domain.FreeMonthReport;
import com.b2b.common.domain.FreeShopMonthReport;
import com.b2b.common.domain.FreeShopMonthReportExample;
import com.b2b.common.domain.FreeShopMonthReportExample.Criteria;
import com.b2b.common.util.DateUtil;
import com.b2b.service.FreeShopMonthReportService;

@Service
public class FreeShopMonthReportServiceImpl implements
		FreeShopMonthReportService {
	@Autowired
	private FreeShopMonthReportMapper freeShopMonthReportMapper;
	
	@Override
	public FreeShopMonthReport findByUserIdAndSumDate(Integer id, Date date4) {
		return this.freeShopMonthReportMapper.findByUserIdAndSumDate(id,date4);
	}

	@Override
	public void insert(FreeShopMonthReport report) {
		Date date = DateUtil.beginOfMonth(report.getSumDate());
		FreeShopMonthReportExample example = new FreeShopMonthReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andShopIdEqualTo(report.getShopId());
		criteria.andSumDateGreaterThanOrEqualTo(date);
		criteria.andSumDateLessThanOrEqualTo(report.getSumDate());
		this.freeShopMonthReportMapper.deleteByExample(example);
		this.freeShopMonthReportMapper.insert(report);
	}

	@Override
	public List<FreeShopMonthReport> findAll(String username ,Date starttime, String param,
			String flag, String region,int reseauId,Integer cityId) {
		return this.freeShopMonthReportMapper.findAll(username,starttime,param,flag,region,reseauId,cityId);
	}

	@Override
	public void deleteByDate(Date date1) {
		Date date = DateUtil.beginOfMonth(date1);
		FreeShopMonthReportExample example = new FreeShopMonthReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andSumDateGreaterThanOrEqualTo(date);
		criteria.andSumDateLessThanOrEqualTo(date1);
		this.freeShopMonthReportMapper.deleteByExample(example);
	}

	@Override
	public List<FreeShopMonthReport> findNetShopmonthReport(String querydate, int reseauId,Integer cityId) {
		return freeShopMonthReportMapper.findNetShopmonthReport(querydate,reseauId,cityId);
	}

	@Override
	public FreeShopMonthReport findByReseauIdAndDate(Integer reseauId, Date date) {
		return this.freeShopMonthReportMapper.findByReseauIdAndDate(reseauId,date);
	}

	@Override
	public List<FreeShopMonthReport> findShopInfoByReseauIdAndDate(
			Integer reseauId, Date date) {
		return this.freeShopMonthReportMapper.findShopInfoByReseauIdAndDate(reseauId,date);
	}

	@Override
	public List<FreeMonthReport> findAllMonthReport(Integer cityId) {
		return this.freeShopMonthReportMapper.findAllMonthReport(cityId);
	}

	@Override
	public void deleteByDateAndCityId(Date date, Integer cityId) {
		this.freeShopMonthReportMapper.deleteByDateAndCityId(date,cityId);
	}

	@Override
	public FreeShopMonthReport findBycityIdAndDate(Integer cityId, Date date) {
		return this.freeShopMonthReportMapper.findBycityIdAndDate(cityId,date);
	}

}
