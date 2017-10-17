package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ShopDailyReport {
    private Integer id;

    private Integer shopId;

    private String shopName;

    private Date sumdate;

    private BigDecimal discount;

    private Long totalConsume;

    private Long totalExpend;

    private BigDecimal beforeLastWeekExpand;

    private Integer consumePen;

    private Integer consumePersonNum;

    private Integer beforeLastWeekNum;

    private Long avgExpend;

    private BigDecimal beforeLastWeekAvg;

    private String region;

    private Long onshelfFee;

    private Integer onshelfKind;

    private Integer moveKind;

    private BigDecimal movePercent;
    
	private Long orderFee;

    private Integer orderNum;
	
    private Long avgPenFee;
    
    private Integer openShopNum;
    
    private Integer consumeShopNum;
    
    private Long avgShopFee;

    private Long avgShopPen;
    
    private String openCusNum;
    
    private String consumeCusNum;

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

    public Date getSumdate() {
        return sumdate;
    }

    public void setSumdate(Date sumdate) {
        this.sumdate = sumdate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Long getTotalConsume() {
        return totalConsume;
    }

    public void setTotalConsume(Long totalConsume) {
        this.totalConsume = totalConsume;
    }

    public Long getTotalExpend() {
        return totalExpend;
    }

    public void setTotalExpend(Long totalExpend) {
        this.totalExpend = totalExpend;
    }

    public BigDecimal getBeforeLastWeekExpand() {
        return beforeLastWeekExpand;
    }

    public void setBeforeLastWeekExpand(BigDecimal beforeLastWeekExpand) {
        this.beforeLastWeekExpand = beforeLastWeekExpand;
    }

    public Integer getConsumePen() {
        return consumePen;
    }

    public void setConsumePen(Integer consumePen) {
        this.consumePen = consumePen;
    }

    public Integer getConsumePersonNum() {
        return consumePersonNum;
    }

    public void setConsumePersonNum(Integer consumePersonNum) {
        this.consumePersonNum = consumePersonNum;
    }

    public Integer getBeforeLastWeekNum() {
        return beforeLastWeekNum;
    }

    public void setBeforeLastWeekNum(Integer beforeLastWeekNum) {
        this.beforeLastWeekNum = beforeLastWeekNum;
    }

    public Long getAvgExpend() {
        return avgExpend;
    }

    public void setAvgExpend(Long avgExpend) {
        this.avgExpend = avgExpend;
    }

    public BigDecimal getBeforeLastWeekAvg() {
        return beforeLastWeekAvg;
    }

    public void setBeforeLastWeekAvg(BigDecimal beforeLastWeekAvg) {
        this.beforeLastWeekAvg = beforeLastWeekAvg;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Long getOnshelfFee() {
        return onshelfFee;
    }

    public void setOnshelfFee(Long onshelfFee) {
        this.onshelfFee = onshelfFee;
    }

    public Integer getOnshelfKind() {
        return onshelfKind;
    }

    public void setOnshelfKind(Integer onshelfKind) {
        this.onshelfKind = onshelfKind;
    }

    public Integer getMoveKind() {
        return moveKind;
    }

    public void setMoveKind(Integer moveKind) {
        this.moveKind = moveKind;
    }

    public BigDecimal getMovePercent() {
        return movePercent;
    }

    public void setMovePercent(BigDecimal movePercent) {
        this.movePercent = movePercent;
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

	public Long getAvgPenFee() {
		return avgPenFee;
	}

	public void setAvgPenFee(Long avgPenFee) {
		this.avgPenFee = avgPenFee;
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

	public Long getAvgShopFee() {
		return avgShopFee;
	}

	public void setAvgShopFee(Long avgShopFee) {
		this.avgShopFee = avgShopFee;
	}

    public Long getAvgShopPen() {
        return avgShopPen;
    }

    public void setAvgShopPen(Long avgShopPen) {
        this.avgShopPen = avgShopPen;
    }

	public String getOpenCusNum() {
		return openCusNum;
	}

	public void setOpenCusNum(String openCusNum) {
		this.openCusNum = openCusNum;
	}

	public String getConsumeCusNum() {
		return consumeCusNum;
	}

	public void setConsumeCusNum(String consumeCusNum) {
		this.consumeCusNum = consumeCusNum;
	}
    
}