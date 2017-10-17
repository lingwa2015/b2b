package com.b2b.common.domain;

import java.util.Date;

public class ShopRefund {
    private Integer id;

    private String orderId;

    private Date createdTime;

    private Integer shopUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getShopUser() {
        return shopUser;
    }

    public void setShopUser(Integer shopUser) {
        this.shopUser = shopUser;
    }
}