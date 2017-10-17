package com.b2b.common.domain;

public class ShopDiscountCustomer {
    private Integer id;

    private Integer shopDiscountId;

    private Integer customerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopDiscountId() {
        return shopDiscountId;
    }

    public void setShopDiscountId(Integer shopDiscountId) {
        this.shopDiscountId = shopDiscountId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}