package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ItemWeekReport {
    private Integer id;

    private Date sumDate;

    private Date endDate;

    private Integer weekth;

    private Integer categoryId;

    private String categoryName;

    private Integer categorylevelId;

    private String categorylevelName;

    private Integer itemId;

    private String itemName;

    private String size;

    private Long price;

    private Integer orderItemNum;

    private Integer shopOrderItemNum;

    private Integer shopNum;

    private Long shopOrderItemTotal;

    private Integer orderNum;

    private Integer orderShopNum;

    private Long orderShopTotal;

    private Integer orderRefundNum;

    private Long orderRefundTotal;

    private Integer orderRefundShopNum;

    private BigDecimal profitRate;

    private BigDecimal shelfSalesRate;

    private Integer unsalableShopNum;

    private BigDecimal unsalableRate;

    private Integer reseauId;

    private String reseau;

    private Integer cityId;

    private String city;

    private Integer regionId;

    private String region;

    private String brand;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSumDate() {
        return sumDate;
    }

    public void setSumDate(Date sumDate) {
        this.sumDate = sumDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getWeekth() {
        return weekth;
    }

    public void setWeekth(Integer weekth) {
        this.weekth = weekth;
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

    public Integer getOrderItemNum() {
        return orderItemNum;
    }

    public void setOrderItemNum(Integer orderItemNum) {
        this.orderItemNum = orderItemNum;
    }

    public Integer getShopOrderItemNum() {
        return shopOrderItemNum;
    }

    public void setShopOrderItemNum(Integer shopOrderItemNum) {
        this.shopOrderItemNum = shopOrderItemNum;
    }

    public Integer getShopNum() {
        return shopNum;
    }

    public void setShopNum(Integer shopNum) {
        this.shopNum = shopNum;
    }

    public Long getShopOrderItemTotal() {
        return shopOrderItemTotal;
    }

    public void setShopOrderItemTotal(Long shopOrderItemTotal) {
        this.shopOrderItemTotal = shopOrderItemTotal;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderShopNum() {
        return orderShopNum;
    }

    public void setOrderShopNum(Integer orderShopNum) {
        this.orderShopNum = orderShopNum;
    }

    public Long getOrderShopTotal() {
        return orderShopTotal;
    }

    public void setOrderShopTotal(Long orderShopTotal) {
        this.orderShopTotal = orderShopTotal;
    }

    public Integer getOrderRefundNum() {
        return orderRefundNum;
    }

    public void setOrderRefundNum(Integer orderRefundNum) {
        this.orderRefundNum = orderRefundNum;
    }

    public Long getOrderRefundTotal() {
        return orderRefundTotal;
    }

    public void setOrderRefundTotal(Long orderRefundTotal) {
        this.orderRefundTotal = orderRefundTotal;
    }

    public Integer getOrderRefundShopNum() {
        return orderRefundShopNum;
    }

    public void setOrderRefundShopNum(Integer orderRefundShopNum) {
        this.orderRefundShopNum = orderRefundShopNum;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public BigDecimal getShelfSalesRate() {
        return shelfSalesRate;
    }

    public void setShelfSalesRate(BigDecimal shelfSalesRate) {
        this.shelfSalesRate = shelfSalesRate;
    }

    public Integer getUnsalableShopNum() {
        return unsalableShopNum;
    }

    public void setUnsalableShopNum(Integer unsalableShopNum) {
        this.unsalableShopNum = unsalableShopNum;
    }

    public BigDecimal getUnsalableRate() {
        return unsalableRate;
    }

    public void setUnsalableRate(BigDecimal unsalableRate) {
        this.unsalableRate = unsalableRate;
    }

    public Integer getReseauId() {
        return reseauId;
    }

    public void setReseauId(Integer reseauId) {
        this.reseauId = reseauId;
    }

    public String getReseau() {
        return reseau;
    }

    public void setReseau(String reseau) {
        this.reseau = reseau == null ? null : reseau.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }
}