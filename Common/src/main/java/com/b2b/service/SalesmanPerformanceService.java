package com.b2b.service;

import java.util.Date;

import com.b2b.common.domain.SalesmanPerformance;
import com.b2b.page.Page;

public interface SalesmanPerformanceService {

	void create(Date endDate, Date date2);

	Page<SalesmanPerformance> findPageByDate(Date startTime, Date endTime,
			int currentPage, int defaultPageSize);

}
