package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopSalesMapper;
import com.b2b.common.domain.ShopSales;
import com.b2b.service.ShopSalesService;

@Service
public class ShopSalesServiceImpl implements ShopSalesService {
	@Autowired
	ShopSalesMapper shopSalesMapper;

	@Override
	public void insert(ShopSales sales) {
		this.shopSalesMapper.insert(sales);
	}

	@Override
	public void deleteBySumDateAndCityId(Integer cityId, Date sumdate) {
		this.shopSalesMapper.deleteBySumDateAndCityId(cityId,sumdate);
	}

	@Override
	public List<ShopSales> findByCondition(String date, int interfaceId, Integer cityId, Date dateStart, Date dateEnd) {
		return this.shopSalesMapper.findByCondition(date,interfaceId,cityId, dateStart, dateEnd);
	}

	@Override
	public List<ShopSales> findDetailByInterfaceIdAndDateAndCityId(String sumdate, Integer interfaceId,
																   Integer cityId, Date dateEnd) {

		return this.shopSalesMapper.findDetailByInterfaceIdAndDateAndCityId(sumdate,interfaceId,cityId, dateEnd);
	}

	@Override
	public List<ShopSales> findCurrentMonthDetailByInterfaceIdAndCityId(Integer interfaceId, Integer cityId) {
		return this.shopSalesMapper.findCurrentMonthDetailByInterfaceIdAndCityId(interfaceId,cityId);
	}
}
