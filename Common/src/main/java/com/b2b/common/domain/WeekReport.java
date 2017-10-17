package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class WeekReport {
    private Integer id;

    private Date sumDate;

    private Date endDate;

    private Integer openShopNum;

    private Integer consumeShopNum;

    private Long consumeFee;

    private BigDecimal beforeConsumeFee;

    private Long avgFee;

    private Long avgDayFee;

    private Integer consumePen;

    private Integer consumeNum;

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

    public Integer getOpenShopNum() {
        return openShopNum;
    }

    public void setOpenShopNum(Integer openShopNum) {
        this.openShopNum = openShopNum;
    }

    public Integer getConsumeShopNum() {
        return consumeShopNum;
    }

    public void setConsumeShopNum(Integer consumeShopNum) {
        this.consumeShopNum = consumeShopNum;
    }

    public Long getConsumeFee() {
        return consumeFee;
    }

    public void setConsumeFee(Long consumeFee) {
        this.consumeFee = consumeFee;
    }

    public BigDecimal getBeforeConsumeFee() {
        return beforeConsumeFee;
    }

    public void setBeforeConsumeFee(BigDecimal beforeConsumeFee) {
        this.beforeConsumeFee = beforeConsumeFee;
    }

    public Long getAvgFee() {
        return avgFee;
    }

    public void setAvgFee(Long avgFee) {
        this.avgFee = avgFee;
    }

    public Long getAvgDayFee() {
        return avgDayFee;
    }

    public void setAvgDayFee(Long avgDayFee) {
        this.avgDayFee = avgDayFee;
    }

    public Integer getConsumePen() {
        return consumePen;
    }

    public void setConsumePen(Integer consumePen) {
        this.consumePen = consumePen;
    }

    public Integer getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(Integer consumeNum) {
        this.consumeNum = consumeNum;
    }
}