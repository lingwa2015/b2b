package com.b2b.common.domain;




public class BaseExample {

	protected boolean limitFlag;
	protected int start;
	protected int limit;

	public boolean isLimitFlag() {
		return limitFlag;
	}

	public void setLimitFlag(boolean limitFlag) {
		this.limitFlag = limitFlag;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
