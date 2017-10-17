package com.b2b.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.ibatis.annotations.Param;

import com.b2b.common.domain.Item;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderExample;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.common.domain.TranSum;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.page.Page;

public interface StandardOrderService {

	Page<Pair<StandardOrder, List<StandardOrderItem>>> findStandardOrder(StandardOrder order, Date startTime, Date endTime, int currentPage, int pageSize);
	
	Pair<StandardOrder, List<StandardOrderItem>> findByOrderNo(int standardorderId);
	
	void createStandardOrder(StandardOrder order);

	void updateStandardOrder(StandardOrder order);
	
	Pair<StandardOrder, List<HashMap<String, Object>>> findInfoByOrderNo(int orderNo);

	void updateOrderAndItems(StandardOrder order);
	
	int selectStandardOrderCount(Date stratTime,Date endTime,int snackpackageType);
	
	int getStandardOrderByOrder(Date time,int snackpackageType);
	
	List<StandardOrderItem> selectByTime(Date startTime, Date endTime);
	
    List<StandardOrder> getCurWeekStandOrder();
    
    List<StandardOrder> getStandOrderById(int snackpackageId);

	StandardOrder findById(String id);

	StandardOrder findByStandId(String standId);

	List<StandardOrderItem> findOrderItemById(int id);

	List<StandardOrder> getCurWeekAndLastWeekStandOrder(Date now, Date date);

}
