package com.b2b.enums;

import java.util.Map;

import com.google.common.collect.Maps;



public enum ItemSizeEnum {

		SIZE("SIZE", "规格"),
		BUY_SIZE("BUY_SIZE", "批发规格"),
		SALE_SIZE("SALE_SIZE", "零售规格");


		private String name;
		private String value;

		private ItemSizeEnum(String name, String value) {
			this.name = name;
			this.value = value;
		}


		private static Map<String, ItemSizeEnum> sizeNameMap = Maps.newHashMap();

		static {
			for (ItemSizeEnum itemSizeEnum : ItemSizeEnum.values()) {
				sizeNameMap.put(itemSizeEnum.name, itemSizeEnum);
			}
		}


		public static ItemSizeEnum parseName(String name) {
			return (null != name) ? sizeNameMap.get(name.toLowerCase()) : null;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}


	}
