package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CustomerOrder {
    private Integer customerorderId;

    private Integer standardorderId;

    private Integer customerId;

    private Integer businessId;

    private Integer totalNum;

    private Long totalFee;

    private Long totalCost;

    private Date executedTime;

    private String customerAddress;

    private String remark;

    private BigDecimal discount;

    private Integer week;

    private Integer customerStatus;

    private Integer warehouseId;

    private Integer status;

    private Date createdTime;

    private Integer createdUserid;

    private Date updatedTime;

    private Integer updatedUserid;

    private Integer snackpackagenum;
    
	private List<CustomerOrderItem> customerOrderList;
	
	private String buyerPhone;

	private String buyerName;
	
	private String companyName;
	
	private String userName;

	private String companyMemo;

    public Integer getCustomerorderId() {
        return customerorderId;
    }

    public void setCustomerorderId(Integer customerorderId) {
        this.customerorderId = customerorderId;
    }

    public Integer getStandardorderId() {
        return standardorderId;
    }

    public void setStandardorderId(Integer standardorderId) {
        this.standardorderId = standardorderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
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

    public Date getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(Date executedTime) {
        this.executedTime = executedTime;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress == null ? null : customerAddress.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Integer customerStatus) {
        this.customerStatus = customerStatus;
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

    public Integer getSnackpackagenum() {
        return snackpackagenum;
    }

    public void setSnackpackagenum(Integer snackpackagenum) {
        this.snackpackagenum = snackpackagenum;
    }
    
	public List<CustomerOrderItem> getCustomerOrderList() {
		return customerOrderList;
	}

	public void setCustomerOrderList(List<CustomerOrderItem> customerOrderList) {
		this.customerOrderList = customerOrderList;
	}
	

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getCompanyMemo() {
		return companyMemo;
	}

	public void setCompanyMemo(String companyMemo) {
		this.companyMemo = companyMemo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}