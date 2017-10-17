package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.MonthReport;

public interface MonthReportService {

	MonthReport findByDate(Date month2);

	void create(MonthReport report);

	List<MonthReport> findList();

}
