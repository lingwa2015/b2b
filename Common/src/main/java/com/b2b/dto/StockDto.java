package com.b2b.dto;

import org.springframework.beans.BeanUtils;

import com.b2b.common.domain.Stock;

public class StockDto extends Stock {

	private static final long serialVersionUID = -8871595175991994221L;

	private Long itemCostPrice;
	
	private Long totalAmount;
	
	private Integer isdown;

	public Integer getIsdown() {
		return isdown;
	}

	public void setIsdown(Integer isdown) {
		this.isdown = isdown;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getItemCostPrice() {
		return itemCostPrice;
	}

	public void setItemCostPrice(Long itemCostPrice) {
		this.itemCostPrice = itemCostPrice;
	}

	public StockDto() {
		super();
	}

	public StockDto(Stock stock) {
		this();
		BeanUtils.copyProperties(stock, this);
	}

}
