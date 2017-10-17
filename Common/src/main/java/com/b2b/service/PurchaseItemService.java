package com.b2b.service;

import com.b2b.common.domain.PurchaseItem;

import java.util.List;

public interface PurchaseItemService {

	public  List<PurchaseItem> findItemPlan(String isDown, int cate, String itemName, Integer cityId, String grade, Integer newUserNum);

	public  PurchaseItem findbyItemId(Integer itemId);

	public  String findbySupplierId(Integer supplierId);

	public int insert(PurchaseItem purchaseItem);

	public int update(PurchaseItem purchaseItem);

	public int deleteByItemId(Integer itemId);

	public String findMaxPurchaseId();

	public List<PurchaseItem> findItemPlans(Integer city_id);

	public List<PurchaseItem> findPlans(Integer city_id);

	public Integer findUseNumByItemId(Integer itemId);

	PurchaseItem findItemsByItemIdAndCity(Integer itemId, Integer cityId);

	PurchaseItem findbyId(Integer id);

	List<PurchaseItem> findInfoByStock(String purchaseId);

	PurchaseItem findbyPurchaseIdAndItemid(String purchaseId, Integer itemId);

    Integer findEndCountByPurchaseId(String purchaseId);

	int updatePurchaseItemStatus(String purchaseId , Integer status);
}
