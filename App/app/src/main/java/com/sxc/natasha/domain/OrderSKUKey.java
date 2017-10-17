package com.sxc.natasha.domain;

import java.io.Serializable;

public class OrderSKUKey extends BasePrintSKU implements Serializable{
	private static final long serialVersionUID = 7277821655939912499L;

	private Integer itemId;

    private String orderNo;

    private Integer skuId;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }
}