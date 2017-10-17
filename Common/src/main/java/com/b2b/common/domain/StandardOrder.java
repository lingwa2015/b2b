package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StandardOrder {
    private Integer standardorderId;

    private Date executedTime;

    private Integer week;

    private Long totalFee;

    private Integer standardStatus;

    private Integer warehouseId;

    private Integer status;

    private Date createdTime;

    private Integer createdUserid;

    private Date updatedTime;

    private Integer updatedUserid;

    private Integer snackpackageStatus;

    private Integer snackpackageType;

    private Integer parentid;
    
    private Long totalCost;

    private Integer totalNum;

    private Long budget;
    
    private BigDecimal weight;
    
    private Long offerPrice;

    private String remark;
    
    private String orderUpdateTime;
    
    private String typeValue;
    
    private String imgPath;

	private List<StandardOrderItem> standardOrderList;
	
    public Integer getStandardorderId() {
        return standardorderId;
    }

    public void setStandardorderId(Integer standardorderId) {
        this.standardorderId = standardorderId;
    }

    public Date getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(Date executedTime) {
        this.executedTime = executedTime;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getStandardStatus() {
        return standardStatus;
    }

    public void setStandardStatus(Integer standardStatus) {
        this.standardStatus = standardStatus;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getCreatedUserid() {
        return createdUserid;
    }

    public void setCreatedUserid(Integer createdUserid) {
        this.createdUserid = createdUserid;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getUpdatedUserid() {
        return updatedUserid;
    }

    public void setUpdatedUserid(Integer updatedUserid) {
        this.updatedUserid = updatedUserid;
    }

    public Integer getSnackpackageStatus() {
        return snackpackageStatus;
    }

    public void setSnackpackageStatus(Integer snackpackageStatus) {
        this.snackpackageStatus = snackpackageStatus;
    }

    public Integer getSnackpackageType() {
        return snackpackageType;
    }

    public void setSnackpackageType(Integer snackpackageType) {
        this.snackpackageType = snackpackageType;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
    
    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
    
    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
	public Long getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Long offerPrice) {
		this.offerPrice = offerPrice;
	}
    
	public List<StandardOrderItem> getStandardOrderList() {
		return standardOrderList;
	}

	public void setStandardOrderList(List<StandardOrderItem> standardOrderList) {
		this.standardOrderList = standardOrderList;
	}

	public String getOrderUpdateTime() {
		return orderUpdateTime;
	}

	public void setOrderUpdateTime(String orderUpdateTime) {
		this.orderUpdateTime = orderUpdateTime;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
}