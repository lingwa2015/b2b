package com.b2b.common.domain;

import java.util.Date;
import java.util.List;

public class Storage {
    private Integer id;

    private Integer userId;

    private Date createdTime;

    private Date modifiedTime;

    private Integer state;

    private Integer lastModUser;
    
    private Date executedTime;

    private Long totalFee;
    
    private List<StorageItem> storageItemList;

    private Integer supplierId;

    private String number;
	
	private Integer cityId;
    
    private String supplierName;

    private Integer purchaseFlag;

    private String purchaseId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLastModUser() {
        return lastModUser;
    }

    public void setLastModUser(Integer lastModUser) {
        this.lastModUser = lastModUser;
    }

    public Date getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(Date executedTime) {
        this.executedTime = executedTime;
    }
    
    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

	public List<StorageItem> getStorageItemList() {
		return storageItemList;
	}

	public void setStorageItemList(List<StorageItem> storageItemList) {
		this.storageItemList = storageItemList;
	}

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }
	
	 public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

    public Integer getPurchaseFlag() {
        return purchaseFlag;
    }

    public void setPurchaseFlag(Integer purchaseFlag) {
        this.purchaseFlag = purchaseFlag;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }
}