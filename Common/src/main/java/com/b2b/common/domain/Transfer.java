package com.b2b.common.domain;

import java.util.Date;

public class Transfer {
    private String transferId;

    private Date executedTime;

    private Integer type;

    private String remark;

    private Date createdTime;

    private Integer state;

    private Integer customerid;
	
	private Integer lastCustomerid;
	
	private Long foregift;
	
	private Integer cityId;
	
	private Integer macId;
    
    private String oneType;
    
    private String twoType;
    
    private String userName;
    
    private String lastUserName;
    
    private String address;
    
    private String lastAddr;
    
    private String machineId;
    
    protected Float actualForegift;

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId == null ? null : transferId.trim();
    }

    public Date getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(Date executedTime) {
        this.executedTime = executedTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }
	
	 public Integer getLastCustomerid() {
        return lastCustomerid;
    }

    public void setLastCustomerid(Integer lastCustomerid) {
        this.lastCustomerid = lastCustomerid;
    }
	
	 public Long getForegift() {
        return foregift;
    }

    public void setForegift(Long foregift) {
        this.foregift = foregift;
    }
	
	public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
	
	public Integer getMacId() {
        return macId;
    }

    public void setMacId(Integer macId) {
        this.macId = macId;
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

	public String getLastUserName() {
		return lastUserName;
	}

	public void setLastUserName(String lastUserName) {
		this.lastUserName = lastUserName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLastAddr() {
		return lastAddr;
	}

	public void setLastAddr(String lastAddr) {
		this.lastAddr = lastAddr;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public Float getActualForegift() {
		return actualForegift;
	}

	public void setActualForegift(Float actualForegift) {
		this.actualForegift = actualForegift;
	}
    
}