package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ItemCategory {
    private Integer id;

    private BigDecimal categoryWeight;

    private String categoryName;

    private Integer warehouseId;

    private Integer parentId;

    private Date createdTime;

    private Integer createdUserid;

    private Integer status;
    
    private Integer business_id=0;
    
    private Date updatedTime;

    private Integer updatedUserid;
	
	private Integer score;
	
	private Integer cityId;
	
	 private Integer isShow;
    
    List<ItemCategory> category;

    public Integer getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(Integer business_id) {
		this.business_id = business_id;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCategoryWeight() {
        return categoryWeight;
    }

    public void setCategoryWeight(BigDecimal categoryWeight) {
        this.categoryWeight = categoryWeight;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
	
	public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
	
	 public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
	
	public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

	public List<ItemCategory> getCategory() {
		return category;
	}

	public void setCategory(List<ItemCategory> category) {
		this.category = category;
	}
    
}