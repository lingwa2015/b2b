package com.b2b.common.domain;

import java.util.Date;
import java.util.List;

public class StockCheck {
    private Integer id;

    private Integer userId;

    private Date createdTime;

    private Date modifiedTime;

    private Integer state;

    private Integer lastModUser;

    private Date executedTime;

    private String remark;
	
	private Integer type;
	
	private Integer cityId;
	
	private Long totalFee;
    
    private List<StockCheckItem> itemList;
    
    private String userName;

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
    


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
	
	public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
	
	public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }
	
		public List<StockCheckItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<StockCheckItem> itemList) {
		this.itemList = itemList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}