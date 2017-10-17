package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.FreeWeekReportMapper;
import com.b2b.common.domain.FreeWeekReport;
import com.b2b.common.domain.FreeWeekReportExample;
import com.b2b.common.domain.FreeWeekReportExample.Criteria;
import com.b2b.service.FreeWeekReportService;

@Service
public class FreeWeekReportServiceImpl implements FreeWeekReportService{
	@Autowired
	FreeWeekReportMapper freeWeekReportMapper;
	
	@Override
	public FreeWeekReport findBySumDate(Date querydate) {
		return this.freeWeekReportMapper.findBySumDate(querydate);
	}

	@Override
	public void insert(FreeWeekReport report) {
		FreeWeekReportExample example = new FreeWeekReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andSumDateEqualTo(report.getSumDate());
		this.freeWeekReportMapper.deleteByExample(example);
		this.freeWeekReportMapper.insert(report);
	}

	@Override
	public List<FreeWeekReport> findAll() {
		return this.freeWeekReportMapper.findAll();
	}

}
