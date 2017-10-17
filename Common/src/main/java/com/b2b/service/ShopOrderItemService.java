package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.ShopOrderItem;


public interface ShopOrderItemService {

	void create(ShopOrderItem shopOrderItem);

	List<ShopOrderItem> findItemByOrderId(String orderId);

}
