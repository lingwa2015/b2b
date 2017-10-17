package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.FreeMonthReport;

public interface FreeMonthReportService {

	void insert(FreeMonthReport report);

	List<FreeMonthReport> findAll();

}
