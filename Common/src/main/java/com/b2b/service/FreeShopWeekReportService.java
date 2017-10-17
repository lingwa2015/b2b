package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.FreeShopWeekReport;
import com.b2b.common.domain.ShopWeekReport;

public interface FreeShopWeekReportService {

	FreeShopWeekReport queryLastWeek(Integer id, Date querydate);

	void insert(FreeShopWeekReport report);

	List<FreeShopWeekReport> findByCondition(String username, Date starttime,
			String param, String flag, int reseauId);

}
