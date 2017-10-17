package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.ShopMonthReport;

public interface ShopMonthReportService {

	ShopMonthReport findByUserIdAndSumDate(Integer id, Date month2);

	void insert(ShopMonthReport report);

	List<ShopMonthReport> findByCondition(Date startTime, Date endTime,
			String username, String param, String region, int reseauId, Integer cityId);

	void deleteByDate(Date date4);

	List<ShopMonthReport> findNetMonthReport(int reseauId, String querydate, Integer cityId);

	List<ShopMonthReport> findAllMonthReport(Integer cityId);

	ShopMonthReport findByReseauIdAndDate(Integer reseauId, Date date);

	ShopMonthReport findByShopIdAndDate(Integer shopId, Date date);

	void deleteByDateAndCityId(Date date6, Integer id);

	ShopMonthReport findByCityIdAndDate(Integer cityId, Date date);

    int updateIsnew(Date date);
}
