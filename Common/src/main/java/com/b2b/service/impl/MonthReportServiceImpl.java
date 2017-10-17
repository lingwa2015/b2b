package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.MonthReportMapper;
import com.b2b.common.domain.MonthReport;
import com.b2b.common.domain.MonthReportExample;
import com.b2b.common.domain.MonthReportExample.Criteria;
import com.b2b.common.util.DateUtil;
import com.b2b.service.MonthReportService;

@Service
public class MonthReportServiceImpl implements MonthReportService {
	
	@Autowired
	private MonthReportMapper monthReportMapper;

	@Override
	public MonthReport findByDate(Date month2) {
		return this.monthReportMapper.findByDate(month2);
	}

	@Override
	public void create(MonthReport report) {
		Date date = DateUtil.beginOfMonth(report.getSumDate());
		MonthReportExample example = new MonthReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andSumDateGreaterThanOrEqualTo(date);
		criteria.andSumDateLessThanOrEqualTo(report.getSumDate());
		this.monthReportMapper.deleteByExample(example);
		this.monthReportMapper.insert(report);
	}

	@Override
	public List<MonthReport> findList() {
		return this.monthReportMapper.findList();
	}
}
