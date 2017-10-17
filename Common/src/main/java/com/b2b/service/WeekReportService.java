package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.WeekReport;

public interface WeekReportService {

	WeekReport findByCondition(Date startDate);

	void insert(WeekReport report);

	List<WeekReport> findAll();

}
