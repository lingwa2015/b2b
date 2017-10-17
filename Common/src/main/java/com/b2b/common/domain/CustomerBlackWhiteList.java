package com.b2b.common.domain;

import java.util.Date;
import java.util.List;

public class CustomerBlackWhiteList {
    private Integer blackwhiteId;

    private Integer customerId;

    private String customerName;

    private Integer blackwhiteType;

    private Integer status;

    private Date createdTime;

    private Integer createdUserid;

    private Date updatedTime;

    private Integer updatedUserid;
    
    List<BlackWhiteListVariety> blackWhiteListVarietys;
    
    List<BlackWhiteListItem> blackWhiteListItems;
    
    List<BlackWhiteListCategory> blackWhiteListCategorys;
    
    public List<BlackWhiteListVariety> getBlackWhiteListVarietys() {
		return blackWhiteListVarietys;
	}

	public void setBlackWhiteListVarietys(
			List<BlackWhiteListVariety> blackWhiteListVarietys) {
		this.blackWhiteListVarietys = blackWhiteListVarietys;
	}

	public List<BlackWhiteListItem> getBlackWhiteListItems() {
		return blackWhiteListItems;
	}

	public void setBlackWhiteListItems(List<BlackWhiteListItem> blackWhiteListItems) {
		this.blackWhiteListItems = blackWhiteListItems;
	}

	public List<BlackWhiteListCategory> getBlackWhiteListCategorys() {
		return blackWhiteListCategorys;
	}

	public void setBlackWhiteListCategorys(
			List<BlackWhiteListCategory> blackWhiteListCategorys) {
		this.blackWhiteListCategorys = blackWhiteListCategorys;
	}

	public Integer getBlackwhiteId() {
        return blackwhiteId;
    }

    public void setBlackwhiteId(Integer blackwhiteId) {
        this.blackwhiteId = blackwhiteId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Integer getBlackwhiteType() {
        return blackwhiteType;
    }

    public void setBlackwhiteType(Integer blackwhiteType) {
        this.blackwhiteType = blackwhiteType;
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
}