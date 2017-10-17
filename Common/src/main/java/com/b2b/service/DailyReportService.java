package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.DailyReport;

public interface DailyReportService {

	DailyReport findByDate(Date sumdate);

	void createBySumBatch(DailyReport report);

	List<DailyReport> findList();

}
