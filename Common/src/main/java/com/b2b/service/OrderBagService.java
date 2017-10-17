package com.b2b.service;

import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.OrderBag;

public interface OrderBagService {

	void insert(OrderBag orderBag);

	List<OrderBag> findByOrderNo(String orderNo);

	List<HashMap<String, Object>> findOrderBagAndBagExecuteTimeByOrderNo(String orderNo);

	void deleteOrderBag(String orderNo);

	void update(OrderBag orderBag);

}
