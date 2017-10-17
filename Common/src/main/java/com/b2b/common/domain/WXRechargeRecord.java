package com.b2b.common.domain;

import java.util.Date;

public class WXRechargeRecord {
    private String id;

    private Integer cid;

    private Long rechargeMoney;

    private Long freeMoney;

    private Date createdTime;

    private Integer createdUserid;

    private Integer state;
    
    private String companyName;
    
    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Long getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Long rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Long getFreeMoney() {
        return freeMoney;
    }

    public void setFreeMoney(Long freeMoney) {
        this.freeMoney = freeMoney;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}