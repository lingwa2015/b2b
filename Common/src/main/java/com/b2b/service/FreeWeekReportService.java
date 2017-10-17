package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.FreeWeekReport;

public interface FreeWeekReportService {

	FreeWeekReport findBySumDate(Date querydate);

	void insert(FreeWeekReport report);

	List<FreeWeekReport> findAll();

}
