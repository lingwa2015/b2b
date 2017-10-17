package com.b2b.common.domain;

import java.util.Date;

public class FreeMonthReport {
    private Integer id;

    private Date sumDate;

    private Integer openShopNum;

    private Integer orderShopNum;

    private Long orderFee;

    private Integer orderNum;

    private Long refundFee;

    private Integer refundNum;

    private Long sourcingFee;

    private Long avgFee;

    private Long avdDayFee;
    
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

    public Integer getOpenShopNum() {
        return openShopNum;
    }

    public void setOpenShopNum(Integer openShopNum) {
        this.openShopNum = openShopNum;
    }

    public Integer getOrderShopNum() {
        return orderShopNum;
    }

    public void setOrderShopNum(Integer orderShopNum) {
        this.orderShopNum = orderShopNum;
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

    public Long getAvgFee() {
        return avgFee;
    }

    public void setAvgFee(Long avgFee) {
        this.avgFee = avgFee;
    }

    public Long getAvdDayFee() {
        return avdDayFee;
    }

    public void setAvdDayFee(Long avdDayFee) {
        this.avdDayFee = avdDayFee;
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