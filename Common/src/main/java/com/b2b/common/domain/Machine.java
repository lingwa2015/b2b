package com.b2b.common.domain;

import java.util.Date;

public class Machine {
    private Integer id;

    private String machineId;

    private Integer oneTypeId;

    private Integer twoTypeId;

    private Integer userId;

    private String remark;

    private Integer status;

    private Date createdTime;
	
	private Integer cityId;
    
	private Long foregift;
	
    private String oneType;
    
    private String twoType;
    
    private String userName;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId == null ? null : machineId.trim();
    }

    public Integer getOneTypeId() {
        return oneTypeId;
    }

    public void setOneTypeId(Integer oneTypeId) {
        this.oneTypeId = oneTypeId;
    }

    public Integer getTwoTypeId() {
        return twoTypeId;
    }

    public void setTwoTypeId(Integer twoTypeId) {
        this.twoTypeId = twoTypeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
	
	public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
	
	public Long getForegift() {
        return foregift;
    }

    public void setForegift(Long foregift) {
        this.foregift = foregift;
    }

	public String getOneType() {
		return oneType;
	}

	public void setOneType(String oneType) {
		this.oneType = oneType;
	}

	public String getTwoType() {
		return twoType;
	}

	public void setTwoType(String twoType) {
		this.twoType = twoType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}