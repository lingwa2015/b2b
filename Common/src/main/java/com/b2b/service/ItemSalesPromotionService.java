package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.ItemSaleCustomer;
import com.b2b.common.domain.ItemSalesPromotion;

public interface ItemSalesPromotionService {

	List<ItemSalesPromotion> findByItemIdAndDateAndCityId(Integer itemId,Date startTime, Integer cityId);

	List<ItemSalesPromotion> findByItemIdAndStartAndEndTimeAndCityId(Integer itemId,Date startTime, Date endTime, Integer cityId);

	void save(ItemSalesPromotion dto);

	List<ItemSalesPromotion> findAllByConditions(String itemname, String param,
			Integer cityId);

	void changeStatus(ItemSalesPromotion promotion);

	void changeTypeAndSaveItemSaleCustomer(Integer itemsaleId, List<ItemSaleCustomer> datas);

	List<ItemSalesPromotion> findStratedByCityId(Integer cityId);

	List<ItemSalesPromotion> findByItemSaleIdAndShopId(Integer itemSaleId , Integer id);

	List<ItemSalesPromotion> findStratedByCityIdAndShopId(Integer cityId, Integer shopId);

	List<ItemSalesPromotion> findPreferentialByItemSaleId(Integer id);

	ItemSalesPromotion findTotalByItemSaleId(Integer id);

	ItemSalesPromotion findById(Integer id);

}
