package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.DailyReportMapper;
import com.b2b.common.domain.DailyReport;
import com.b2b.common.domain.DailyReportExample;
import com.b2b.common.domain.DailyReportExample.Criteria;
import com.b2b.service.DailyReportService;

@Service
public class DailyReportServiceImpl implements DailyReportService{
	
	@Autowired
	private DailyReportMapper dailyReportMapper;
	
	@Override
	public DailyReport findByDate(Date sumdate) {
		return dailyReportMapper.findByDate(sumdate);
	}

	@Override
	public void createBySumBatch(DailyReport report) {
		DailyReportExample example = new DailyReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andSumDateEqualTo(report.getSumDate());
		this.dailyReportMapper.deleteByExample(example);
		this.dailyReportMapper.insert(report);
	}

	@Override
	public List<DailyReport> findList() {
		return this.dailyReportMapper.findList();
	}

}
