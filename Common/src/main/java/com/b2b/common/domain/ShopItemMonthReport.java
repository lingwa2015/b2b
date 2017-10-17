package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ShopItemMonthReport {
    private Integer id;

    private String reseauName;

    private Integer userId;

    private String userName;

    private Date sumDate;

    private Integer categoryId;

    private String categoryName;

    private Integer categorylevelId;

    private String categorylevelName;

    private Integer itemId;

    private String itemName;

    private String size;

    private Long price;

    private Integer orderNum;

    private Integer itemNum;

    private Integer refundNum;

    private BigDecimal turnoverRate;

    private Integer cityId;

    private Integer regionId;

    private Integer reseauId;

    private String region;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReseauName() {
        return reseauName;
    }

    public void setReseauName(String reseauName) {
        this.reseauName = reseauName == null ? null : reseauName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getSumDate() {
        return sumDate;
    }

    public void setSumDate(Date sumDate) {
        this.sumDate = sumDate;
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

    public Integer getCategorylevelId() {
        return categorylevelId;
    }

    public void setCategorylevelId(Integer categorylevelId) {
        this.categorylevelId = categorylevelId;
    }

    public String getCategorylevelName() {
        return categorylevelName;
    }

    public void setCategorylevelName(String categorylevelName) {
        this.categorylevelName = categorylevelName == null ? null : categorylevelName.trim();
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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public BigDecimal getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(BigDecimal turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getReseauId() {
        return reseauId;
    }

    public void setReseauId(Integer reseauId) {
        this.reseauId = reseauId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }
}