package com.b2b.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.b2b.common.domain.CustomerOrder;
import com.b2b.common.domain.CustomerOrderItem;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.page.Page;

public interface CustomerOrderService {

	Page<Pair<CustomerOrder, List<CustomerOrderItem>>> findOrder(CustomerOrder order, Date startTime, Date endTime, int currentPage, int pageSize);

	Pair<CustomerOrder, List<HashMap<String, Object>>> findByOrderNo(int customerorderId);
	
	Pair<CustomerOrder, List<CustomerOrderItem>> findByOrderNoList(int customerorderId);
	
	void createCustomerOrder(CustomerOrder order);

	void updateCustomerOrder(CustomerOrder order);
	
	void updateOrderAndItems(CustomerOrder order);
	
	void createOrder(int customerorderId,String orderNo);
	
	List<CustomerOrderItem> getPurchasePlan();
}
