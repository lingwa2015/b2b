package com.b2b.service;

import com.b2b.common.domain.ShopSales;

import java.util.Date;
import java.util.List;

public interface ShopSalesService {

	void insert(ShopSales sales);

	void deleteBySumDateAndCityId(Integer id, Date date1);

	List<ShopSales> findByCondition(String date, int interfaceId, Integer cityId, Date dateStart, Date dateEnd);

	List<ShopSales> findDetailByInterfaceIdAndDateAndCityId(String sumdate, Integer interfaceId, Integer cityId, Date dateEnd);

	List<ShopSales> findCurrentMonthDetailByInterfaceIdAndCityId(Integer interfaceId, Integer cityId);

}
