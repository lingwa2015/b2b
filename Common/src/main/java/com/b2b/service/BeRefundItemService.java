package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.BeRefundItem;

public interface BeRefundItemService {

	void insert(BeRefundItem refundItem);

	List<BeRefundItem> findTodayRefundItemByShopId(Integer shopId);

}
