package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SeniorSet {
    private Integer shopId;

    private Long money;

    private Date created;

    private BigDecimal discount;

    private Integer start;

    private Integer showPrice;

    private Long freeFee;

    private Integer dayOrMonth;

    private Integer islayer;
	
	private Integer type;

    private List<String> checks;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(Integer showPrice) {
        this.showPrice = showPrice;
    }

    public Long getFreeFee() {
        return freeFee;
    }

    public void setFreeFee(Long freeFee) {
        this.freeFee = freeFee;
    }

    public Integer getDayOrMonth() {
        return dayOrMonth;
    }

    public void setDayOrMonth(Integer dayOrMonth) {
        this.dayOrMonth = dayOrMonth;
    }

    public Integer getIslayer() {
        return islayer;
    }

    public void setIslayer(Integer islayer) {
        this.islayer = islayer;
    }
	
	 public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<String> getChecks() {
        return checks;
    }

    public void setChecks(List<String> checks) {
        this.checks = checks;
    }
}