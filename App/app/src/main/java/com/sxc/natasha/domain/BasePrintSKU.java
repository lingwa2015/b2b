package com.sxc.natasha.domain;


public class BasePrintSKU {

	// 商品编码：商品自增id
	private Integer itemId;
	// 品名：品种+品级（新增字段）+省份产+包装净重（用空格连接）
	private String pingName;
	// 单位：（空）
	private String unit;
	
	public static String JOIN_CHAR=" ";

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getPingName() {
		return pingName;
	}

	public void setPingName(String pingName) {
		this.pingName = pingName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


	

}
