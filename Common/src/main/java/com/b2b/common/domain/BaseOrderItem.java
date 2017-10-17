package com.b2b.common.domain;

public class BaseOrderItem {

	protected int categoryId;

	protected int itemStock;

	protected long refundFee;

	protected int refundNum;

	protected long profitFee;

	protected String position;

	protected String oneCate;

	protected String twoCate;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getItemStock() {
		return itemStock;
	}

	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}

	public long getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(long refundFee) {
		this.refundFee = refundFee;
	}

	public int getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(int refundNum) {
		this.refundNum = refundNum;
	}

	public long getProfitFee() {
		return profitFee;
	}

	public void setProfitFee(long profitFee) {
		this.profitFee = profitFee;
	}

	public String getOneCate() {
		return oneCate;
	}

	public void setOneCate(String oneCate) {
		this.oneCate = oneCate;
	}

	public String getTwoCate() {
		return twoCate;
	}

	public void setTwoCate(String twoCate) {
		this.twoCate = twoCate;
	}

}
