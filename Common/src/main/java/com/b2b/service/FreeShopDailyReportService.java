package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.FreeShopDailyReport;

public interface FreeShopDailyReportService {

	void insert(FreeShopDailyReport report);

	List<FreeShopDailyReport> findNetDailyReport(Date starttime, Date endtime,
			int reseauId, Integer cityId);

	List<FreeShopDailyReport> findAllDailyReport(Integer cityId);

	FreeShopDailyReport findByReseauIdAndDate(Integer reseauId, Date date2);

	FreeShopDailyReport findByCityIdAndDate(Integer cityId, Date date);

}
