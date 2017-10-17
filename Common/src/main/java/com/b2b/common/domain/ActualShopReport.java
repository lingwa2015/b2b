package com.b2b.common.domain;

import java.math.BigDecimal;

public class ActualShopReport {
	private String userName;
	private Integer shopId;
	private Long consumeFee;
	private Integer consumeNum;
	private Long sourcingFee;
	private Long onsaleFee;
	private Integer unsalable;
	private Integer kinds;
	private BigDecimal day;
	private String region;
    private String lastTime;
    private String checkTime;
    private Integer checkDay;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Long getConsumeFee() {
		return consumeFee;
	}
	public void setConsumeFee(Long consumeFee) {
		this.consumeFee = consumeFee;
	}
	public Integer getConsumeNum() {
		return consumeNum;
	}
	public void setConsumeNum(Integer consumeNum) {
		this.consumeNum = consumeNum;
	}
	public Long getSourcingFee() {
		return sourcingFee;
	}
	public void setSourcingFee(Long sourcingFee) {
		this.sourcingFee = sourcingFee;
	}
	public Long getOnsaleFee() {
		return onsaleFee;
	}
	public void setOnsaleFee(Long onsaleFee) {
		this.onsaleFee = onsaleFee;
	}
	public Integer getUnsalable() {
		return unsalable;
	}
	public void setUnsalable(Integer unsalable) {
		this.unsalable = unsalable;
	}
	public Integer getKinds() {
		return kinds;
	}
	public void setKinds(Integer kinds) {
		this.kinds = kinds;
	}
	public BigDecimal getDay() {
		return day;
	}
	public void setDay(BigDecimal day) {
		this.day = day;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public Integer getCheckDay() {
		return checkDay;
	}
	public void setCheckDay(Integer checkDay) {
		this.checkDay = checkDay;
	}
}
