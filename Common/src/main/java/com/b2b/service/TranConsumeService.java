package com.b2b.service;

import java.util.Date;

import com.b2b.common.domain.TranConsume;

public interface TranConsumeService {

	//void delete(Date firstDate, Date sumDate);

	void create(TranConsume tranConsume);

	Long findCurrentMonthConsumeMoney(Integer customerUserId);

	Date findDate();

	Long findLossMoney(Integer shop_id);

	TranConsume findMoreLossMoney(Integer shopId);

	TranConsume findMonthStockByUserIdAndDate(Integer id, Date date4);

	TranConsume findTotalMonthStockByDate(Date date);

	void deleteByDateAndCityId(Date firstDate, Date sumDate, Integer cityId);

	Long findCurrentMonthRedPriceByShopId(Integer shopId);

}
