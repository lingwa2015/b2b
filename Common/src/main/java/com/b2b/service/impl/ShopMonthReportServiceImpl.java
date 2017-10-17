package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopMonthReportMapper;
import com.b2b.common.domain.ShopMonthReport;
import com.b2b.common.domain.ShopMonthReportExample;
import com.b2b.common.domain.ShopMonthReportExample.Criteria;
import com.b2b.common.util.DateUtil;
import com.b2b.service.ShopMonthReportService;

@Service

public class ShopMonthReportServiceImpl implements ShopMonthReportService {
	@Autowired
	private ShopMonthReportMapper shopMonthReportMapper;
	
	@Override
	public ShopMonthReport findByUserIdAndSumDate(Integer id, Date month) {
		return this.shopMonthReportMapper.findByUserIdAndSumDate(id,month);
	}

	@Override
	public void insert(ShopMonthReport report) {
		this.shopMonthReportMapper.insert(report);
	}

	@Override
	public List<ShopMonthReport> findByCondition(Date startTime, Date endTime,
			String username, String param,String region,int reseauId,Integer cityId) {
		return this.shopMonthReportMapper.findByCondition(startTime,endTime,username,param,region,reseauId,cityId);
	}

	@Override
	public void deleteByDate(Date date4) {
		Date date = DateUtil.beginOfMonth(date4);
		ShopMonthReportExample example = new ShopMonthReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andSumDateGreaterThanOrEqualTo(date);
		criteria.andSumDateLessThanOrEqualTo(date4);
		this.shopMonthReportMapper.deleteByExample(example);
	}

	@Override
	public List<ShopMonthReport> findNetMonthReport(int reseauId, String querydate,Integer cityId) {
		return this.shopMonthReportMapper.findNetMonthReport(reseauId,querydate,cityId);
	}

	@Override
	public List<ShopMonthReport> findAllMonthReport(Integer cityId) {
		return this.shopMonthReportMapper.findAllMonthReport(cityId);
	}

	@Override
	public ShopMonthReport findByReseauIdAndDate(Integer reseauId, Date date) {
		return this.shopMonthReportMapper.findByReseauIdAndDate(reseauId,date);
	}

	@Override
	public ShopMonthReport findByShopIdAndDate(Integer shopId, Date date) {
		return this.shopMonthReportMapper.findByShopIdAndDate(shopId,date);
	}

	@Override
	public void deleteByDateAndCityId(Date date, Integer id) {
		this.shopMonthReportMapper.deleteByDateAndCityId(date,id);
	}

	@Override
	public ShopMonthReport findByCityIdAndDate(Integer cityId, Date date) {
		return shopMonthReportMapper.findByCityIdAndDate(cityId,date);
	}

	@Override
	public int updateIsnew(Date date) {
		return shopMonthReportMapper.updateIsnew(date);
	}

}
