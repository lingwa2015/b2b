package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.FreeShopWeekReportMapper;
import com.b2b.common.domain.FreeShopWeekReport;
import com.b2b.common.domain.FreeShopWeekReportExample;
import com.b2b.common.domain.FreeShopWeekReportExample.Criteria;
import com.b2b.service.FreeShopWeekReportService;

@Service
public class FreeShopWeekReportServiceImpl implements FreeShopWeekReportService {
	@Autowired
	private FreeShopWeekReportMapper freeShopWeekReportMapper;
	
	@Override
	public FreeShopWeekReport queryLastWeek(Integer id, Date querydate) {
		return this.freeShopWeekReportMapper.queryLastWeek(id,querydate);
	}

	@Override
	public void insert(FreeShopWeekReport report) {
		FreeShopWeekReportExample example = new FreeShopWeekReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(report.getUserId());
		criteria.andSumDateEqualTo(report.getSumDate());
		this.freeShopWeekReportMapper.deleteByExample(example);
		this.freeShopWeekReportMapper.insert(report);
	}

	@Override
	public List<FreeShopWeekReport> findByCondition(String username,
			Date starttime, String param, String flag,int reseauId) {
		return this.freeShopWeekReportMapper.findByCondition(username,starttime,param,flag,reseauId);
	}

}
