package com.b2b.common.domain;

import java.util.Date;

public class StockMoneyMonitor {
    private Integer id;

    private Long stockMoney;

    private Long changeMoney;

    private Long inStockMoney;

    private Long outStockMoney;

    private Date createTime;

    private Integer cityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStockMoney() {
        return stockMoney;
    }

    public void setStockMoney(Long stockMoney) {
        this.stockMoney = stockMoney;
    }

    public Long getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(Long changeMoney) {
        this.changeMoney = changeMoney;
    }

    public Long getInStockMoney() {
        return inStockMoney;
    }

    public void setInStockMoney(Long inStockMoney) {
        this.inStockMoney = inStockMoney;
    }

    public Long getOutStockMoney() {
        return outStockMoney;
    }

    public void setOutStockMoney(Long outStockMoney) {
        this.outStockMoney = outStockMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}