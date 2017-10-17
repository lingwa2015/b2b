package com.b2b.common.domain;

import java.util.Date;
import java.util.List;

public class Outstock {
    private Long id;

    private Integer supplierId;

    private String supplierName;

    private Integer printFlg;

    private Integer status;

    private Date executedTime;

    private Date outstockTime;

    private Integer cityId;

    private String remark;

    private Long totalPrice;

    private Integer userId;

    private String userName;

    private List<OutstockItem> outstockItemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getPrintFlg() {
        return printFlg;
    }

    public void setPrintFlg(Integer printFlg) {
        this.printFlg = printFlg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(Date executedTime) {
        this.executedTime = executedTime;
    }

    public Date getOutstockTime() {
        return outstockTime;
    }

    public void setOutstockTime(Date outstockTime) {
        this.outstockTime = outstockTime;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
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

    public List<OutstockItem> getOutstockItemList() {
        return outstockItemList;
    }

    public void setOutstockItemList(List<OutstockItem> outstockItemList) {
        this.outstockItemList = outstockItemList;
    }
}