package com.sxc.natasha.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * list all status of order
 * 
 * @Author Ming.Zi
 * @Date Jan 28, 2015
 * @Version 1.0
 */
public enum OrderStatusEnum {
//全部、未付款、已付款、拣货中、待配送、配送中、待提货、已提货、待退款、退款完毕
	ALL(0, "全部"),
	UNPAY(1, "未付款"),
	WAITPICK(2, "已付款"),
	PICKING(3, "拣货中"),
	UNSHIP(4, "待配送"),
	SHIPPING(5, "配送中"),
	UNDELIVER(6, "待提货"),
	DELIVERED(7, "已提货"),
	WAITREFUND(8, "待退款"),
	REFUNDED(9, "退款完毕"),
    CLOSE(10, "关闭");
	
	private int id;
	private String value;
	private static List<Integer> allStatus = new ArrayList<Integer>();

	private OrderStatusEnum(int id, String value) {
		this.id = id;
		this.value = value;
	}
	
	private static Map<Integer, OrderStatusEnum> orderStatusIdMap = new HashMap<Integer, OrderStatusEnum>();
	private static Map<String, OrderStatusEnum> orderStatusNameMap = new HashMap<String, OrderStatusEnum>();

	static {
		for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
			orderStatusNameMap.put(orderStatusEnum.name().toLowerCase(), orderStatusEnum);
			orderStatusIdMap.put(orderStatusEnum.id, orderStatusEnum);
			allStatus.add(orderStatusEnum.getId());
		}
	}

	public static OrderStatusEnum parseId(int id) {
		return orderStatusIdMap.get(id);
	}

	public static OrderStatusEnum parseName(String name) {
		return (null != name) ? orderStatusNameMap.get(name.toLowerCase()) : null;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return this.name();
	}

	public int getId() {
		return id;
	}

	public static List<Integer> getAllStatus() {
		return allStatus;
	}

	public static List<String> getNames() {
		List<String> names = new ArrayList<String>();
		for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
			names.add(orderStatusEnum.name());
		}
		return names;
	}
}
