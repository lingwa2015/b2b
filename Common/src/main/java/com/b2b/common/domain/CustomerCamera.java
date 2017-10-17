package com.b2b.common.domain;

import java.util.Date;

public class CustomerCamera {
    private Integer cid;

    private String num;

    private Integer openWelcome;

    private Date createdTime;
    
    private String companyName;
    private String userName;
    private String region;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public Integer getOpenWelcome() {
        return openWelcome;
    }

    public void setOpenWelcome(Integer openWelcome) {
        this.openWelcome = openWelcome;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
    
}