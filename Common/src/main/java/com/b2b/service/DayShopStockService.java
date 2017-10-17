package com.b2b.service;

import java.util.Date;

import com.b2b.common.domain.DayShopStock;

public interface DayShopStockService {

	void create(Date endDate);

	DayShopStock findByDate(Date sumDate, Integer id);

}
