package com.b2b.common.domain;

import java.util.Date;

public class TranConsume {
    private Integer id;

    private Integer userId;

    private Long sourcingNo;

    private Long sourcing;

    private Long consume;

    private Long redFee;

    private Long actualConsume;

    private Long monthStockMoney;

    private Long goodsMoney;

    private Date createDate;

    private Date sumDate;

    private Long lwFeeOne;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getSourcingNo() {
        return sourcingNo;
    }

    public void setSourcingNo(Long sourcingNo) {
        this.sourcingNo = sourcingNo;
    }

    public Long getSourcing() {
        return sourcing;
    }

    public void setSourcing(Long sourcing) {
        this.sourcing = sourcing;
    }

    public Long getConsume() {
        return consume;
    }

    public void setConsume(Long consume) {
        this.consume = consume;
    }

    public Long getRedFee() {
        return redFee;
    }

    public void setRedFee(Long redFee) {
        this.redFee = redFee;
    }

    public Long getActualConsume() {
        return actualConsume;
    }

    public void setActualConsume(Long actualConsume) {
        this.actualConsume = actualConsume;
    }

    public Long getMonthStockMoney() {
        return monthStockMoney;
    }

    public void setMonthStockMoney(Long monthStockMoney) {
        this.monthStockMoney = monthStockMoney;
    }

    public Long getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(Long goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getSumDate() {
        return sumDate;
    }

    public void setSumDate(Date sumDate) {
        this.sumDate = sumDate;
    }

    public Long getLwFeeOne() {
        return lwFeeOne;
    }

    public void setLwFeeOne(Long lwFeeOne) {
        this.lwFeeOne = lwFeeOne;
    }
}