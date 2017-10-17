package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ShopMonthReport {
    private Integer id;

    private String region;

    private String userName;

    private Integer userId;

    private Date sumDate;

    private BigDecimal discount;

    private Long consumeFee;

    private Long expandFee;

    private Long newconsumeFee;

    private Long newexpandFee;

    private Long oldconsumeFee;

    private Long oldexpandFee;

    private BigDecimal beforeExpandFee;

    private Integer consumePen;

    private BigDecimal beforeConsumePen;

    private Integer consumeNum;

    private BigDecimal beforeConsumeNum;

    private Long avgFee;

    private BigDecimal beforeAvgFee;

    private Long sourcingFee;

    private BigDecimal beforeSourcingFee;

    private Integer orderNum;

    private Integer refundNum;

    private BigDecimal beforeOrderNum;

    private Integer lossPercent;

    private BigDecimal beforeLossPercent;

    private Long monthFirstStock;

    private Long stock;

    private Long avgManFee;

    private BigDecimal beforeAvgManFee;

    private Long wastage;

    private Long zhekoucaigou;

    private Long chengben;

    private Long profit;

    private BigDecimal profitRate;
	
	private Long subsidy;
    
    private Integer openShopNum;

    private Integer newopenShopNum;

    private Integer oldopenShopNum;

    private Integer consumeShopNum;

    private Integer newconsumeShopNum;

    private Integer oldconsumeShopNum;

    private Long avgShopFee;
    
    private Long avgPenFee;
    
    private BigDecimal repeatBuyRate;
    
    private BigDecimal turnoverRate;

    private Integer isnew;

private Integer consumeCusNum;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSumDate() {
        return sumDate;
    }

    public void setSumDate(Date sumDate) {
        this.sumDate = sumDate;
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

    public Long getExpandFee() {
        return expandFee;
    }

    public void setExpandFee(Long expandFee) {
        this.expandFee = expandFee;
    }

    public BigDecimal getBeforeExpandFee() {
        return beforeExpandFee;
    }

    public void setBeforeExpandFee(BigDecimal beforeExpandFee) {
        this.beforeExpandFee = beforeExpandFee;
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

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public BigDecimal getBeforeOrderNum() {
        return beforeOrderNum;
    }

    public void setBeforeOrderNum(BigDecimal beforeOrderNum) {
        this.beforeOrderNum = beforeOrderNum;
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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getAvgManFee() {
        return avgManFee;
    }

    public void setAvgManFee(Long avgManFee) {
        this.avgManFee = avgManFee;
    }

    public BigDecimal getBeforeAvgManFee() {
        return beforeAvgManFee;
    }

    public void setBeforeAvgManFee(BigDecimal beforeAvgManFee) {
        this.beforeAvgManFee = beforeAvgManFee;
    }

    public Long getWastage() {
        return wastage;
    }

    public void setWastage(Long wastage) {
        this.wastage = wastage;
    }

    public Long getZhekoucaigou() {
        return zhekoucaigou;
    }

    public void setZhekoucaigou(Long zhekoucaigou) {
        this.zhekoucaigou = zhekoucaigou;
    }

    public Long getChengben() {
        return chengben;
    }

    public void setChengben(Long chengben) {
        this.chengben = chengben;
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
	public Long getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(Long subsidy) {
        this.subsidy = subsidy;
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

	public Long getAvgPenFee() {
		return avgPenFee;
	}

	public void setAvgPenFee(Long avgPenFee) {
		this.avgPenFee = avgPenFee;
	}

	public BigDecimal getRepeatBuyRate() {
		return repeatBuyRate;
	}

	public void setRepeatBuyRate(BigDecimal repeatBuyRate) {
		this.repeatBuyRate = repeatBuyRate;
	}

	public BigDecimal getTurnoverRate() {
		return turnoverRate;
	}

	public void setTurnoverRate(BigDecimal turnoverRate) {
		this.turnoverRate = turnoverRate;
	}

    public Integer getIsnew() {
        return isnew;
    }

    public void setIsnew(Integer isnew) {
        this.isnew = isnew;
    }

    public Long getNewconsumeFee() {
        return newconsumeFee;
    }

    public void setNewconsumeFee(Long newconsumeFee) {
        this.newconsumeFee = newconsumeFee;
    }

    public Long getNewexpandFee() {
        return newexpandFee;
    }

    public void setNewexpandFee(Long newexpandFee) {
        this.newexpandFee = newexpandFee;
    }

    public Long getOldconsumeFee() {
        return oldconsumeFee;
    }

    public void setOldconsumeFee(Long oldconsumeFee) {
        this.oldconsumeFee = oldconsumeFee;
    }

    public Long getOldexpandFee() {
        return oldexpandFee;
    }

    public void setOldexpandFee(Long oldexpandFee) {
        this.oldexpandFee = oldexpandFee;
    }

    public Integer getNewopenShopNum() {
        return newopenShopNum;
    }

    public void setNewopenShopNum(Integer newopenShopNum) {
        this.newopenShopNum = newopenShopNum;
    }

    public Integer getOldopenShopNum() {
        return oldopenShopNum;
    }

    public void setOldopenShopNum(Integer oldopenShopNum) {
        this.oldopenShopNum = oldopenShopNum;
    }

    public Integer getNewconsumeShopNum() {
        return newconsumeShopNum;
    }

    public void setNewconsumeShopNum(Integer newconsumeShopNum) {
        this.newconsumeShopNum = newconsumeShopNum;
    }

    public Integer getOldconsumeShopNum() {
        return oldconsumeShopNum;
    }

    public void setOldconsumeShopNum(Integer oldconsumeShopNum) {
        this.oldconsumeShopNum = oldconsumeShopNum;
    }

	public Integer getConsumeCusNum() {
		return consumeCusNum;
	}

	public void setConsumeCusNum(Integer consumeCusNum) {
		this.consumeCusNum = consumeCusNum;
	}

	public Integer getOpenCusNum() {
		return openCusNum;
	}

	public void setOpenCusNum(Integer openCusNum) {
		this.openCusNum = openCusNum;
	}
    
}