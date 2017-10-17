package com.b2b.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


public enum OrderStatusEnum {
	
		ALL(0, "全部"),
		CLOSE(-1, "已付款"),
		PAY(1, "已付款"),
		PRINT(2, "已打印"),
		CANCEL(3, "已取消"),
		SUM(99, "已核算");
		
		private int id;
		private String value;
		private static List<Integer> allStatus = Lists.newArrayList();

		private OrderStatusEnum(int id, String value) {
			this.id = id;
			this.value = value;
		}
		
		private static Map<Integer, OrderStatusEnum> orderStatusIdMap = Maps.newHashMap();
		private static Map<String, OrderStatusEnum> orderStatusNameMap = Maps.newHashMap();

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
