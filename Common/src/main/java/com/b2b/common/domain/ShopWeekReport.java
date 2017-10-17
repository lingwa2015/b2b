package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ShopWeekReport {
    private Integer id;

    private Integer userId;

    private String userName;

    private String region;

    private Date sumdate;

    private Date enddate;

    private BigDecimal discount;

    private Long consumeFee;

    private BigDecimal beforeConsumeFee;

    private Long expendFee;

    private Long avgFee;

    private Integer consumePen;

    private Integer consumeNum;

    private Long avgManFee;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Date getSumdate() {
        return sumdate;
    }

    public void setSumdate(Date sumdate) {
        this.sumdate = sumdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
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

    public Long getExpendFee() {
        return expendFee;
    }

    public void setExpendFee(Long expendFee) {
        this.expendFee = expendFee;
    }

    public Long getAvgFee() {
        return avgFee;
    }

    public void setAvgFee(Long avgFee) {
        this.avgFee = avgFee;
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

    public Long getAvgManFee() {
        return avgManFee;
    }

    public void setAvgManFee(Long avgManFee) {
        this.avgManFee = avgManFee;
    }
}