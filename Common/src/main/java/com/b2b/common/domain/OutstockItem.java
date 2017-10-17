package com.b2b.common.domain;

public class OutstockItem {
    private Long id;

    private Long outstockId;

    private Integer itemId;

    private String itemName;

    private Integer categoryId;

    private String categoryName;

    private String size;

    private Long price;

    private Long costPrice;

    private Integer convertNum;

    private Integer outstockNum;

    private String itemRemark;

    private Long totalPrice;

    private String barcode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOutstockId() {
        return outstockId;
    }

    public void setOutstockId(Long outstockId) {
        this.outstockId = outstockId;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Long costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getConvertNum() {
        return convertNum;
    }

    public void setConvertNum(Integer convertNum) {
        this.convertNum = convertNum;
    }

    public Integer getOutstockNum() {
        return outstockNum;
    }

    public void setOutstockNum(Integer outstockNum) {
        this.outstockNum = outstockNum;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark == null ? null : itemRemark.trim();
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}