package com.b2b.common.domain;

public class CustomerOrderItem extends BaseOrderItem{
    private Integer coiId;

    private Integer customerorderId;

    private Integer itemId;

    private String itemName;

    private Integer num;

    private Long fee;

    private Long itemPrice;

    private String itemSize;

    private Long itemCostPrice;

    private String itemSizeType;

    private Integer stockNum;

    public Integer getCoiId() {
        return coiId;
    }

    public void setCoiId(Integer coiId) {
        this.coiId = coiId;
    }

    public Integer getCustomerorderId() {
        return customerorderId;
    }

    public void setCustomerorderId(Integer customerorderId) {
        this.customerorderId = customerorderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize == null ? null : itemSize.trim();
    }

    public Long getItemCostPrice() {
        return itemCostPrice;
    }

    public void setItemCostPrice(Long itemCostPrice) {
        this.itemCostPrice = itemCostPrice;
    }

    public String getItemSizeType() {
        return itemSizeType;
    }

    public void setItemSizeType(String itemSizeType) {
        this.itemSizeType = itemSizeType == null ? null : itemSizeType.trim();
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }
}