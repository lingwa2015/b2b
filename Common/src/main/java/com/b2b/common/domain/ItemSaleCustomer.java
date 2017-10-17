package com.b2b.common.domain;

public class ItemSaleCustomer {
    private Integer id;

    private Integer itemsaleId;

    private Integer customerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemsaleId() {
        return itemsaleId;
    }

    public void setItemsaleId(Integer itemsaleId) {
        this.itemsaleId = itemsaleId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}