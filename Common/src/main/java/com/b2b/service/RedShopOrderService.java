package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.RedShopOrder;

public interface RedShopOrderService {

	RedShopOrder findByOrderNo(String id);

	void save(RedShopOrder redShopOrder);

}
