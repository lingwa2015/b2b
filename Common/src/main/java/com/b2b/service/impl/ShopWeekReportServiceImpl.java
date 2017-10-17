package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopWeekReportMapper;
import com.b2b.common.domain.ShopWeekReport;
import com.b2b.common.domain.ShopWeekReportExample;
import com.b2b.common.domain.ShopWeekReportExample.Criteria;
import com.b2b.service.ShopWeekReportService;

@Service
public class ShopWeekReportServiceImpl implements ShopWeekReportService {
	@Autowired
	ShopWeekReportMapper shopWeekReportMapper;
	
	@Override
	public void insert(ShopWeekReport report) {
		ShopWeekReportExample example = new ShopWeekReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(report.getUserId());
		criteria.andSumdateEqualTo(report.getSumdate());
		this.shopWeekReportMapper.deleteByExample(example);
		this.shopWeekReportMapper.insert(report);
	}

	@Override
	public ShopWeekReport queryLastWeek(Integer id, Date querydate) {
		return shopWeekReportMapper.queryLastWeek(id,querydate);
	}

	@Override
	public List<ShopWeekReport> findByCondition(String userName,
			Date starttime, String param,int reseauId) {
		return shopWeekReportMapper.findByCondition(userName,starttime,param,reseauId);
	}

}
