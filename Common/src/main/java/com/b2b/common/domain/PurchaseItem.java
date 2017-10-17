package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PurchaseItem {
    private Integer id;

    private String purchaseId;

    private Integer itemId;

    private String itemName;

    private Integer categoryId;

    private String categoryName;

    private String size;

    private Long price;

    private Long costPrice;

    private Integer convertNum;

    private BigDecimal itemWeight;

    private Integer shelfLife;

    private Integer purchaseNum;

    private String itemRemark;

    private Integer purchasedNum;

    private Long totalPrice;

    private Integer stockNum;

    private Integer supplierId;

    private String supplierName;

    private Integer useNum;

    private String barcode;

    private Integer stockFlag;

    private Integer stockedNum;

    private Integer status;

    private Date stockedTime;

    private Date createdTime;

    private Integer isdown;

    private Integer cityId;

    private Integer diffNum;

    private Integer isfree;

    private List<Supplier> suppliers;

    private  BigDecimal weekvolume;

    private String grade;

    private BigDecimal weekVolumePlan;

    private String saleSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId == null ? null : purchaseId.trim();
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

    public BigDecimal getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(BigDecimal itemWeight) {
        this.itemWeight = itemWeight;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark == null ? null : itemRemark.trim();
    }

    public Integer getPurchasedNum() {
        return purchasedNum;
    }

    public void setPurchasedNum(Integer purchasedNum) {
        this.purchasedNum = purchasedNum;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public Integer getStockFlag() {
        return stockFlag;
    }

    public void setStockFlag(Integer stockFlag) {
        this.stockFlag = stockFlag;
    }

    public Integer getStockedNum() {
        return stockedNum;
    }

    public void setStockedNum(Integer stockedNum) {
        this.stockedNum = stockedNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStockedTime() {
        return stockedTime;
    }

    public void setStockedTime(Date stockedTime) {
        this.stockedTime = stockedTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getIsdown() {
        return isdown;
    }

    public void setIsdown(Integer isdown) {
        this.isdown = isdown;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDiffNum() {
        return diffNum;
    }

    public void setDiffNum(Integer diffNum) {
        this.diffNum = diffNum;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public Integer getIsfree() {
        return isfree;
    }

    public void setIsfree(Integer isfree) {
        this.isfree = isfree;
    }

    public String getSaleSize() {
        return saleSize;
    }

    public void setSaleSize(String saleSize) {
        this.saleSize = saleSize;
    }

    public BigDecimal getWeekvolume() {
        return weekvolume;
    }

    public void setWeekvolume(BigDecimal weekvolume) {
        this.weekvolume = weekvolume;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public BigDecimal getWeekVolumePlan() {
        return weekVolumePlan;
    }

    public void setWeekVolumePlan(BigDecimal weekVolumePlan) {
        this.weekVolumePlan = weekVolumePlan;
    }


}