package com.b2b.common.domain;

import java.util.Date;

public class FreeShopDailyReport {
    private Integer id;

    private Integer shopId;

    private String shopName;

    private Date sumDate;

    private Long orderFee;

    private Integer orderNum;

    private Long refundFee;

    private Integer refundNum;

    private Long sourcingFee;
    
    private Integer openShopNum;
    
    private Integer orderShopNum;
    
    private String reseauName;
    
    private Long avgFee;
    
    private Integer openCusNum;
    
    private Integer orderCusNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
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

    public Long getSourcingFee() {
        return sourcingFee;
    }

    public void setSourcingFee(Long sourcingFee) {
        this.sourcingFee = sourcingFee;
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

	public String getReseauName() {
		return reseauName;
	}

	public void setReseauName(String reseauName) {
		this.reseauName = reseauName;
	}

	public Long getAvgFee() {
		return avgFee;
	}

	public void setAvgFee(Long avgFee) {
		this.avgFee = avgFee;
	}

	public Integer getOpenCusNum() {
		return openCusNum;
	}

	public void setOpenCusNum(Integer openCusNum) {
		this.openCusNum = openCusNum;
	}

	public Integer getOrderCusNum() {
		return orderCusNum;
	}

	public void setOrderCusNum(Integer orderCusNum) {
		this.orderCusNum = orderCusNum;
	}
    
    
}