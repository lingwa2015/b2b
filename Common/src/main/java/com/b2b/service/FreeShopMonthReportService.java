package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.FreeMonthReport;
import com.b2b.common.domain.FreeShopMonthReport;

public interface FreeShopMonthReportService {

	FreeShopMonthReport findByUserIdAndSumDate(Integer id, Date date4);

	void insert(FreeShopMonthReport report);

	List<FreeShopMonthReport> findAll(String username, Date starttime, String param, String flag, String region, int reseauId, Integer cityId);

	void deleteByDate(Date date1);

	List<FreeShopMonthReport> findNetShopmonthReport(String querydate, int reseauId, Integer cityId);

	FreeShopMonthReport findByReseauIdAndDate(Integer reseauId, Date date);

	List<FreeShopMonthReport> findShopInfoByReseauIdAndDate(Integer reseauId,
			Date date);

	List<FreeMonthReport> findAllMonthReport(Integer cityId);

	void deleteByDateAndCityId(Date date, Integer cityId);

	FreeShopMonthReport findBycityIdAndDate(Integer cityId, Date date);

}
