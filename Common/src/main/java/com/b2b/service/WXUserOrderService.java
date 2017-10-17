package com.b2b.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderBag;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.common.domain.WXUserOrder;
import com.b2b.page.Page;

public interface WXUserOrderService {

	Page<Pair<WXUserOrder, List<StandardOrderItem>>> findAll(Date startTime,
			Date endTime, int currentPage, int defaultPageSize,
			String orderNum, String userName, String param);

	WXUserOrder findById(Integer id);

	void createOrder(Order order, List<OrderBag> orderBagList, String fastname, String fastnum, Integer id);

	Pair<WXUserOrder, List<StandardOrderItem>> findPairById(int parseInt);

	List<WXUserOrder> findByWXUserId(Integer id);

	WXUserOrder findOrderDetailById(String id);

	void updateFast(String fastNO, Integer id);
	
	void changePayStatus(String string);

	Page<WXUserOrder> findAllwxOrder(Date startTime, Date endTime,
			int currentPage, int defaultPageSize, String orderNum,
			String userName, String param);

	void changePayfeeStatus(WXUserOrder order);

	WXUserOrder save(String id, String num, String totalfee, String wxname,
			String wxphone, String wxadress, String invoiceid,
			String invoiceName, String orderNo, Integer userId, int isgroup);

	HashMap<String, Object> findpingdanInfoById(Integer id);

	List<WXUserOrder> findExpirePingDanOrder();

}
