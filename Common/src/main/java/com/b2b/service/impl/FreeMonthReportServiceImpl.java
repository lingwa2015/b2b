package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.FreeMonthReportMapper;
import com.b2b.common.domain.FreeMonthReport;
import com.b2b.common.domain.FreeMonthReportExample;
import com.b2b.common.domain.FreeMonthReportExample.Criteria;
import com.b2b.common.util.DateUtil;
import com.b2b.service.FreeMonthReportService;

@Service
public class FreeMonthReportServiceImpl implements FreeMonthReportService {
	@Autowired
	private FreeMonthReportMapper freeMonthReportMapper;
	
	@Override
	public void insert(FreeMonthReport report) {
		FreeMonthReportExample example = new FreeMonthReportExample();
		Criteria criteria = example.createCriteria();
		Date date = DateUtil.getFirstDayOfMonth(report.getSumDate());
		criteria.andSumDateGreaterThanOrEqualTo(date);
		criteria.andSumDateLessThanOrEqualTo(report.getSumDate());
		this.freeMonthReportMapper.deleteByExample(example);
		this.freeMonthReportMapper.insert(report);
	}

	@Override
	public List<FreeMonthReport> findAll() {
		return this.freeMonthReportMapper.findAll();
	}


}
