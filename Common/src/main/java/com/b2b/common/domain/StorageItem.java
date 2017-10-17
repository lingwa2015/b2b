package com.b2b.common.domain;

import java.util.Date;

public class StorageItem {
    private Integer id;

    private Integer itemId;

    private Integer num;

    private String itemName;

    private Long totalFee;

    private Integer storageId;

    private Integer buyNum;

    private Long buyPrice;

    private String size;

    private String sizeValue;
    
    private String position;

    private String buySize;

    private String barcode;

    private Integer purchaseFlag;

    private String saleSize;

    private Date executedTime;

    private String number;

    public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getStorageId() {
        return storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(String sizeValue) {
        this.sizeValue = sizeValue == null ? null : sizeValue.trim();
    }

    public String getBuySize() {
        return buySize;
    }

    public void setBuySize(String buySize) {
        this.buySize = buySize;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getPurchaseFlag() {
        return purchaseFlag;
    }

    public void setPurchaseFlag(Integer purchaseFlag) {
        this.purchaseFlag = purchaseFlag;
    }

    public String getSaleSize() {
        return saleSize;
    }

    public void setSaleSize(String saleSize) {
        this.saleSize = saleSize;
    }

    public Date getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(Date executedTime) {
        this.executedTime = executedTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}