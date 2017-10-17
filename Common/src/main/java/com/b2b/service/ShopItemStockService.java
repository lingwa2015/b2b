package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.ShopItemStock;

public interface ShopItemStockService {

	void create(Date endDate);

	List<ShopItemStock> findlossDetail(Integer shop_id, Date querydate,
			Date startdate, Date enddate);

	Integer findByItemIdAndSumdateAndSaleId(Integer itemId, Date createdTime, Integer id);

	Integer findByItemIdAndSumdate(Integer itemId, Date createdTime);

}
