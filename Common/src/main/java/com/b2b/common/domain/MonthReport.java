package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class MonthReport {
    private Integer id;

    private Date sumDate;

    private Integer openNum;

    private Integer consumeShopNum;

    private Long consumeFee;

    private BigDecimal beforeConsumeFee;

    private Long avgFee;

    private BigDecimal beforeAvgFee;

    private Long avgDayFee;

    private BigDecimal beforeAvgDayFee;

    private Integer consumePen;

    private BigDecimal beforeConsumePen;

    private Integer consumeNum;

    private BigDecimal beforeConsumeNum;

    private Long sourcingFee;

    private BigDecimal beforeSourcingFee;

    private Integer orderNum;

    private BigDecimal beforeOrderNum;

    private Integer refundNum;

    private Integer lossPercent;

    private BigDecimal beforeLossPercent;
    
    private Long monthFirstStock;

    private Long monthLastStock;

    private Long expandFee;
	
    private Long getFee;    
	
    private Long invoiceFee;

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

    public Integer getOpenNum() {
        return openNum;
    }

    public void setOpenNum(Integer openNum) {
        this.openNum = openNum;
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

    public BigDecimal getBeforeAvgFee() {
        return beforeAvgFee;
    }

    public void setBeforeAvgFee(BigDecimal beforeAvgFee) {
        this.beforeAvgFee = beforeAvgFee;
    }

    public Long getAvgDayFee() {
        return avgDayFee;
    }

    public void setAvgDayFee(Long avgDayFee) {
        this.avgDayFee = avgDayFee;
    }

    public BigDecimal getBeforeAvgDayFee() {
        return beforeAvgDayFee;
    }

    public void setBeforeAvgDayFee(BigDecimal beforeAvgDayFee) {
        this.beforeAvgDayFee = beforeAvgDayFee;
    }

    public Integer getConsumePen() {
        return consumePen;
    }

    public void setConsumePen(Integer consumePen) {
        this.consumePen = consumePen;
    }

    public BigDecimal getBeforeConsumePen() {
        return beforeConsumePen;
    }

    public void setBeforeConsumePen(BigDecimal beforeConsumePen) {
        this.beforeConsumePen = beforeConsumePen;
    }

    public Integer getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(Integer consumeNum) {
        this.consumeNum = consumeNum;
    }

    public BigDecimal getBeforeConsumeNum() {
        return beforeConsumeNum;
    }

    public void setBeforeConsumeNum(BigDecimal beforeConsumeNum) {
        this.beforeConsumeNum = beforeConsumeNum;
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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getBeforeOrderNum() {
        return beforeOrderNum;
    }

    public void setBeforeOrderNum(BigDecimal beforeOrderNum) {
        this.beforeOrderNum = beforeOrderNum;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Integer getLossPercent() {
        return lossPercent;
    }

    public void setLossPercent(Integer lossPercent) {
        this.lossPercent = lossPercent;
    }

    public BigDecimal getBeforeLossPercent() {
        return beforeLossPercent;
    }

    public void setBeforeLossPercent(BigDecimal beforeLossPercent) {
        this.beforeLossPercent = beforeLossPercent;
    }
	
	public Long getMonthFirstStock() {
        return monthFirstStock;
    }

    public void setMonthFirstStock(Long monthFirstStock) {
        this.monthFirstStock = monthFirstStock;
    }

    public Long getMonthLastStock() {
        return monthLastStock;
    }

    public void setMonthLastStock(Long monthLastStock) {
        this.monthLastStock = monthLastStock;
    }

    public Long getExpandFee() {
        return expandFee;
    }

    public void setExpandFee(Long expandFee) {
        this.expandFee = expandFee;
    }

	public Long getGetFee() {
		return getFee;
	}

	public void setGetFee(Long getFee) {
		this.getFee = getFee;
	}

	public Long getInvoiceFee() {
		return invoiceFee;
	}

	public void setInvoiceFee(Long invoiceFee) {
		this.invoiceFee = invoiceFee;
	}
    
}