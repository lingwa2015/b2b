package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.WeekReportMapper;
import com.b2b.common.domain.WeekReport;
import com.b2b.common.domain.WeekReportExample;
import com.b2b.common.domain.WeekReportExample.Criteria;
import com.b2b.service.WeekReportService;

@Service
public class WeekReportServiceImpl implements WeekReportService {
	@Autowired
	WeekReportMapper weekReportMapper;
	
	@Override
	public WeekReport findByCondition(Date startDate) {
		return this.weekReportMapper.findByCondition(startDate);
	}

	@Override
	public void insert(WeekReport report) {
		WeekReportExample example = new WeekReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andSumDateEqualTo(report.getSumDate());
		this.weekReportMapper.deleteByExample(example);
		this.weekReportMapper.insert(report);
	}

	@Override
	public List<WeekReport> findAll() {
		WeekReportExample example = new WeekReportExample();
		example.setOrderByClause("sum_date desc");
		return this.weekReportMapper.selectByExample(example);
	}

}
