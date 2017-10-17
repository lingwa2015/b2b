package com.b2b.web.util;

import java.util.HashMap;
import java.util.Map;

import com.b2b.enums.OrderStatusEnum;

public class StatusUtil {

	static Map<Integer,String> orderStatusMap = new HashMap<Integer, String>();
	
	static{
		for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
				orderStatusMap.put(orderStatusEnum.getId(), orderStatusEnum.getValue());
		}
		
	}
		
	public static Map<Integer,String> getOrderStatusMap(){
		return orderStatusMap;
	}
	
	public static String getOrderStatusName(int orderStatus){
		return orderStatusMap.get(orderStatus);
	}
	
	
}
