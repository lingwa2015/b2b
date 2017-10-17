package com.sxc.natasha.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Description: AccountModeEnum 交易方式枚举
 *
 * @author:     zhengshutian
 * @version:    1.0
 * Filename:    AccountModeEnum.java
 * Create at:   2015-02-03
 *
 * Copyright:   Copyright (c)2015
 * Company:     songxiaocai
 *
 * Modification History:
 * Date              Author      Version     Description
 * ------------------------------------------------------------------
 * 2015-02-03      zhengshutian    1.0         1.0 Version
 */
public enum AccountModeEnum {
	
	CASH(1, "现金充值"),
	POS(2, "POS刷卡充值"),
	REFUND(3, "退款返还"),
	EXPEND(4, "下单消费");
	
	private int id;
	private String value;
	
	private AccountModeEnum(int id, String value) {
		this.id = id;
		this.value = value;
	}
	
	private static Map<Integer, AccountModeEnum> accountModeIdMap = new HashMap<Integer, AccountModeEnum>();
	private static Map<String, AccountModeEnum> accountModeNameMap = new HashMap<String, AccountModeEnum>();
	
	static {
		for (AccountModeEnum accountModeEnum : AccountModeEnum.values()) {
			accountModeIdMap.put(accountModeEnum.id, accountModeEnum);
			accountModeNameMap.put(accountModeEnum.name().toLowerCase(), accountModeEnum);
		}
	}

	public static AccountModeEnum parseId(int id) {
		return accountModeIdMap.get(id);
	}

	public static AccountModeEnum parseName(String name) {
		return (null != name) ? accountModeNameMap.get(name.toLowerCase()) : null;
	}
	
	public String getName() {
		return this.name();
	}
	
	public String getValue() {
		return value;
	}
	
	public int getId() {
		return id;
	}
	
	public static List<String> getNames() {
		List<String> names = new ArrayList<String>();
		for (AccountModeEnum accountModeEnum : AccountModeEnum.values()) {
			names.add(accountModeEnum.name());
		}
		return names;
	}
	
	public static Map<Integer, String> getSelectMap(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (AccountModeEnum accountModeEnum : AccountModeEnum.values()) {
			map.put(accountModeEnum.getId(), accountModeEnum.getValue());
		}
		
		return map;
	}
	
}
