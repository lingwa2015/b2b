package com.b2b.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class BaseItem implements Serializable {

	private static final long serialVersionUID = 1778883558905447164L;

	protected String categoryName;
	
	protected String categoryLeaveName;

	protected int stock;

	protected Float actualPrice;

	protected Float actualCostPrice;

	protected Float actualSalePrice;

	protected Float actualSaleCostPrice;

	protected Float actualBuyPrice;
	
	protected Float actualMarketPrice;

	protected Float actualPurchasePrice;
	
	private Float actualnotaxInclusiveSaleCostPrice;
    private Float actualnotaxInclusiveCostPrice;
    private Float actualnotaxInclusiveBuyPrice;
    
    private Float actualjdPrice;
    private Float actualtmPrice;
    private Float actualcsPrice;
    private Integer isblack;
    
    private BigDecimal score;
    
    private Integer frequency;
    
	public Float getActualnotaxInclusiveSaleCostPrice() {
		return actualnotaxInclusiveSaleCostPrice;
	}

	public void setActualnotaxInclusiveSaleCostPrice(
			Float actualnotaxInclusiveSaleCostPrice) {
		this.actualnotaxInclusiveSaleCostPrice = actualnotaxInclusiveSaleCostPrice;
	}

	public Float getActualnotaxInclusiveCostPrice() {
		return actualnotaxInclusiveCostPrice;
	}

	public void setActualnotaxInclusiveCostPrice(Float actualnotaxInclusiveCostPrice) {
		this.actualnotaxInclusiveCostPrice = actualnotaxInclusiveCostPrice;
	}

	public Float getActualnotaxInclusiveBuyPrice() {
		return actualnotaxInclusiveBuyPrice;
	}

	public void setActualnotaxInclusiveBuyPrice(Float actualnotaxInclusiveBuyPrice) {
		this.actualnotaxInclusiveBuyPrice = actualnotaxInclusiveBuyPrice;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryLeaveName() {
		return categoryLeaveName;
	}

	public void setCategoryLeaveName(String categoryLeaveName) {
		this.categoryLeaveName = categoryLeaveName;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Float getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Float actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Float getActualCostPrice() {
		return actualCostPrice;
	}

	public void setActualCostPrice(Float actualCostPrice) {
		this.actualCostPrice = actualCostPrice;
	}

	public Float getActualBuyPrice() {
		return actualBuyPrice;
	}

	public void setActualBuyPrice(Float actualBuyPrice) {
		this.actualBuyPrice = actualBuyPrice;
	}

	public Float getActualSalePrice() {
		return actualSalePrice;
	}

	public void setActualSalePrice(Float actualSalePrice) {
		this.actualSalePrice = actualSalePrice;
	}

	public Float getActualSaleCostPrice() {
		return actualSaleCostPrice;
	}

	public void setActualSaleCostPrice(Float actualSaleCostPrice) {
		this.actualSaleCostPrice = actualSaleCostPrice;
	}

	public Float getActualPurchasePrice() {
		return actualPurchasePrice;
	}

	public void setActualPurchasePrice(Float actualPurchasePrice) {
		this.actualPurchasePrice = actualPurchasePrice;
	}

	public Float getActualMarketPrice() {
		return actualMarketPrice;
	}

	public void setActualMarketPrice(Float actualMarketPrice) {
		this.actualMarketPrice = actualMarketPrice;
	}

	public Float getActualjdPrice() {
		return actualjdPrice;
	}

	public void setActualjdPrice(Float actualjdPrice) {
		this.actualjdPrice = actualjdPrice;
	}

	public Float getActualtmPrice() {
		return actualtmPrice;
	}

	public void setActualtmPrice(Float actualtmPrice) {
		this.actualtmPrice = actualtmPrice;
	}

	public Integer getIsblack() {
		return isblack;
	}

	public void setIsblack(Integer isblack) {
		this.isblack = isblack;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Float getActualcsPrice() {
		return actualcsPrice;
	}

	public void setActualcsPrice(Float actualcsPrice) {
		this.actualcsPrice = actualcsPrice;
	}
	
}
