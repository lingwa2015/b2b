package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.ShopWeekReport;

public interface ShopWeekReportService {

	void insert(ShopWeekReport report);

	ShopWeekReport queryLastWeek(Integer id, Date querydate);

	List<ShopWeekReport> findByCondition(String userName, Date starttime,
			String param, int reseauId);

}
