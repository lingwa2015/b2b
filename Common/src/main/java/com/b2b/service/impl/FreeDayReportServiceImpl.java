package com.b2b.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.FreeDayReportMapper;
import com.b2b.common.domain.FreeDayReport;
import com.b2b.common.domain.FreeDayReportExample;
import com.b2b.common.domain.FreeDayReportExample.Criteria;
import com.b2b.service.FreeDayReportService;

@Service
public class FreeDayReportServiceImpl implements FreeDayReportService {
	@Autowired
	private FreeDayReportMapper freeDayReportMapper;
	
	@Override
	public FreeDayReport findByDate(Date queryDate) {
		return this.freeDayReportMapper.findByDate(queryDate);
	}

	@Override
	public void insert(ArrayList<FreeDayReport> list2, Date startDate) {
		FreeDayReportExample example = new FreeDayReportExample();
		Criteria criteria = example.createCriteria();
		criteria.andSumDateEqualTo(startDate);
		this.freeDayReportMapper.deleteByExample(example);
		for (FreeDayReport freeDayReport : list2) {
			this.freeDayReportMapper.insert(freeDayReport);
		}
	}

	@Override
	public List<FreeDayReport> findAll(Date starttime, Date endtime,int reseauId) {
		return this.freeDayReportMapper.findAll(starttime,endtime,reseauId);
	}

	@Override
	public FreeDayReport findByDateAndReseauId(Integer id, Date queryDate) {
		return this.freeDayReportMapper.findByDateAndReseauId(id,queryDate);
	}

	@Override
	public List<FreeDayReport> findAllDayReport() {
		return this.freeDayReportMapper.findAllDayReport();
	}

	
}
