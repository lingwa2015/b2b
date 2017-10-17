package com.b2b.common.domain;

import java.util.List;

public class StandardOrderItem extends BaseOrderItem{
    private Integer soiId;

    private Integer standardorderId;

    private Integer itemId;

    private String itemName;

    private Integer num;

    private Long fee;

    private Long itemPrice;

    private String itemSize;

    private Long itemCostPrice;

    private Long notaxinclusivecostprice;

    private String itemSizeType;

    private Integer stockNum;
    
    private String imgPath;

    public Integer getSoiId() {
        return soiId;
    }

    public void setSoiId(Integer soiId) {
        this.soiId = soiId;
    }

    public Integer getStandardorderId() {
        return standardorderId;
    }

    public void setStandardorderId(Integer standardorderId) {
        this.standardorderId = standardorderId;
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

    public Long getNotaxinclusivecostprice() {
        return notaxinclusivecostprice;
    }

    public void setNotaxinclusivecostprice(Long notaxinclusivecostprice) {
        this.notaxinclusivecostprice = notaxinclusivecostprice;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}