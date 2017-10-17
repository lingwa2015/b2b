package com.b2b.common.domain;

import java.util.Date;

public class BeCustomerBlackWhiteList {
    private Integer beBlackwhiteId;

    private Integer customerId;

    private String customerName;

    private Integer beBlackwhiteType;

    private Integer status;

    private Date createdTime;

    private Integer createdUserid;

    private Date updatedTime;

    private Integer updatedUserid;

    public Integer getBeBlackwhiteId() {
        return beBlackwhiteId;
    }

    public void setBeBlackwhiteId(Integer beBlackwhiteId) {
        this.beBlackwhiteId = beBlackwhiteId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getBeBlackwhiteType() {
        return beBlackwhiteType;
    }

    public void setBeBlackwhiteType(Integer beBlackwhiteType) {
        this.beBlackwhiteType = beBlackwhiteType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getCreatedUserid() {
        return createdUserid;
    }

    public void setCreatedUserid(Integer createdUserid) {
        this.createdUserid = createdUserid;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getUpdatedUserid() {
        return updatedUserid;
    }

    public void setUpdatedUserid(Integer updatedUserid) {
        this.updatedUserid = updatedUserid;
    }
}