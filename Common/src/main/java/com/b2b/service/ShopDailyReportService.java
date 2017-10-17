package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.ShopDailyReport;

public interface ShopDailyReportService {

	ShopDailyReport findByShopIdAndSumdate(Integer id, Date queryDate);

	void createBySumBatch(ShopDailyReport report);

	List<ShopDailyReport> findByCondition(String userName, Date starttime, Date endtime, String param, int reseauId, Integer cityId);

	List<ShopDailyReport> findNetDailyReport(Date starttime, Date endtime, int reseauId, Integer cityId);

	List<ShopDailyReport> findAllDailyReport(Integer cityId);

	ShopDailyReport findByReseauIdAndDate(Integer reseauId, Date date2);

	ShopDailyReport findByShopIdAndDate(Integer shopId, Date date);

	ShopDailyReport findByCityIdAndDate(Integer cityId, Date date2);

}
