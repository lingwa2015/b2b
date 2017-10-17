package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class FreeShopMonthReport {
    private Integer id;

    private String region;

    private String userName;

    private Integer shopId;

    private Date sumDate;

    private Long orderFee;

    private Integer orderNum;

    private Long refundFee;

    private Integer refundNum;

    private Long soucingFee;

    private Long oldsoucingFee;

    private Long newsoucingFee;

    private BigDecimal beforeSoucingFee;

    private Long avgDayFee;

    private Long profit;

    private BigDecimal profitRate;

    private Long zhekoucaigou;
    
    private Integer openshopnum;
    
    private Integer oldordershopnum;

    private Integer newordershopnum;

    private Integer ordershopnum;

    private Long avgShopDayFee;

    private Integer isnew;
    
    private Integer orderCusNum;
    
    private Integer openCusNum;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Date getSumDate() {
        return sumDate;
    }

    public void setSumDate(Date sumDate) {
        this.sumDate = sumDate;
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

    public Long getSoucingFee() {
        return soucingFee;
    }

    public void setSoucingFee(Long soucingFee) {
        this.soucingFee = soucingFee;
    }

    public BigDecimal getBeforeSoucingFee() {
        return beforeSoucingFee;
    }

    public void setBeforeSoucingFee(BigDecimal beforeSoucingFee) {
        this.beforeSoucingFee = beforeSoucingFee;
    }

    public Long getAvgDayFee() {
        return avgDayFee;
    }

    public void setAvgDayFee(Long avgDayFee) {
        this.avgDayFee = avgDayFee;
    }

    public Long getProfit() {
        return profit;
    }

    public void setProfit(Long profit) {
        this.profit = profit;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public Long getZhekoucaigou() {
        return zhekoucaigou;
    }

    public void setZhekoucaigou(Long zhekoucaigou) {
        this.zhekoucaigou = zhekoucaigou;
    }

	public Integer getOpenshopnum() {
		return openshopnum;
	}

	public void setOpenshopnum(Integer openshopnum) {
		this.openshopnum = openshopnum;
	}

	public Integer getOrdershopnum() {
		return ordershopnum;
	}

	public void setOrdershopnum(Integer ordershopnum) {
		this.ordershopnum = ordershopnum;
	}

	public Long getAvgShopDayFee() {
		return avgShopDayFee;
	}

	public void setAvgShopDayFee(Long avgShopDayFee) {
		this.avgShopDayFee = avgShopDayFee;
	}

    public Integer getIsnew() {
        return isnew;
    }

    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
    }


    public Long getOldsoucingFee() {
        return oldsoucingFee;
    }

    public void setOldsoucingFee(Long oldsoucingFee) {
        this.oldsoucingFee = oldsoucingFee;
    }

    public Long getNewsoucingFee() {
        return newsoucingFee;
    }

    public void setNewsoucingFee(Long newsoucingFee) {
        this.newsoucingFee = newsoucingFee;
    }

    public Integer getOldordershopnum() {
        return oldordershopnum;
    }

    public void setOldordershopnum(Integer oldordershopnum) {
        this.oldordershopnum = oldordershopnum;
    }

    public Integer getNewordershopnum() {
        return newordershopnum;
    }

    public void setNewordershopnum(Integer newordershopnum) {
        this.newordershopnum = newordershopnum;
    }

	public Integer getOrderCusNum() {
		return orderCusNum;
	}

	public void setOrderCusNum(Integer orderCusNum) {
		this.orderCusNum = orderCusNum;
	}

	public Integer getOpenCusNum() {
		return openCusNum;
	}

	public void setOpenCusNum(Integer openCusNum) {
		this.openCusNum = openCusNum;
	}
    
}