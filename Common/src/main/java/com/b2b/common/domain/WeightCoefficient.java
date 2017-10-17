package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class WeightCoefficient {
    private Integer weightId;

    private Integer newitemDay;

    private Integer newitemWeight;

    private BigDecimal newitemweightCoeff;

    private Integer profitWeight;

    private BigDecimal profitweightCoeff;

    private Integer itemWeights;

    private BigDecimal itemweightCoeff;

    private BigDecimal profit;

    private Integer status;

    private Date createdTime;

    private Integer createdUserid;

    private Date updatedTime;

    private Integer updatedUserid;

    public Integer getWeightId() {
        return weightId;
    }

    public void setWeightId(Integer weightId) {
        this.weightId = weightId;
    }

    public Integer getNewitemDay() {
        return newitemDay;
    }

    public void setNewitemDay(Integer newitemDay) {
        this.newitemDay = newitemDay;
    }

    public Integer getNewitemWeight() {
        return newitemWeight;
    }

    public void setNewitemWeight(Integer newitemWeight) {
        this.newitemWeight = newitemWeight;
    }

    public BigDecimal getNewitemweightCoeff() {
        return newitemweightCoeff;
    }

    public void setNewitemweightCoeff(BigDecimal newitemweightCoeff) {
        this.newitemweightCoeff = newitemweightCoeff;
    }

    public Integer getProfitWeight() {
        return profitWeight;
    }

    public void setProfitWeight(Integer profitWeight) {
        this.profitWeight = profitWeight;
    }

    public BigDecimal getProfitweightCoeff() {
        return profitweightCoeff;
    }

    public void setProfitweightCoeff(BigDecimal profitweightCoeff) {
        this.profitweightCoeff = profitweightCoeff;
    }

    public Integer getItemWeights() {
        return itemWeights;
    }

    public void setItemWeights(Integer itemWeights) {
        this.itemWeights = itemWeights;
    }

    public BigDecimal getItemweightCoeff() {
        return itemweightCoeff;
    }

    public void setItemweightCoeff(BigDecimal itemweightCoeff) {
        this.itemweightCoeff = itemweightCoeff;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
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