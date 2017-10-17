package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class FreeShopWeekReport {
    private Integer id;

    private String region;

    private Integer userId;

    private String userName;

    private Date sumDate;

    private Date endDate;

    private Long orderFee;

    private Integer orderNum;

    private Long refundFee;

    private Integer refundNum;

    private Long sourcingFee;

    private BigDecimal beforeSourcingFee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(Long orderFee) {
        this.orderFee = orderFee;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Long refundFee) {
        this.refundFee = refundFee;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Long getSourcingFee() {
        return sourcingFee;
    }

    public void setSourcingFee(Long sourcingFee) {
        this.sourcingFee = sourcingFee;
    }

    public BigDecimal getBeforeSourcingFee() {
        return beforeSourcingFee;
    }

    public void setBeforeSourcingFee(BigDecimal beforeSourcingFee) {
        this.beforeSourcingFee = beforeSourcingFee;
    }
}