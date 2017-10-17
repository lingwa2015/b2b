package com.b2b.web.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class JsonResult implements Serializable {
	private static final long serialVersionUID = -7041918243962143369L;
	private String status = "";
	private String msg = "";
	private List dataList;
	private Long totalFee;
	private Long totalCost;
	private BigDecimal profitRate;

	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Long getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}
	public Long getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Long totalCost) {
		this.totalCost = totalCost;
	}
	public BigDecimal getProfitRate() {
		return profitRate;
	}
	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}


}
