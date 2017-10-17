package com.b2b.web.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.b2b.enums.ItemSizeEnum;

public class SizeUtil {

	static Map<String,String> sizeMap = new LinkedHashMap<String, String>();

	static{
		for (ItemSizeEnum itemSizeEnum : ItemSizeEnum.values()) {
			sizeMap.put(itemSizeEnum.getName(), itemSizeEnum.getValue());
		}
	}

	public static Map<String,String> getSizeMap(){
		return sizeMap;
	}

	public static String getValue(String name){
		return sizeMap.get(name);
	}
    
}
