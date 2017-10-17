package com.b2b.common.domain;

import java.util.Date;

public class RedPacket {
    private Integer id;

    private String redName;

    private Long redBudget;

    private Integer baseDayNum;

    private Integer count;

    private Date startTime;

    private Date endTime;

    private Boolean skip;

    private Integer type;

    private Integer redStatus;

    private Date createdTime;

    private Integer createdUserid;

    private Integer cityId;
    
    private Integer sign;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRedName() {
        return redName;
    }

    public void setRedName(String redName) {
        this.redName = redName == null ? null : redName.trim();
    }

    public Long getRedBudget() {
        return redBudget;
    }

    public void setRedBudget(Long redBudget) {
        this.redBudget = redBudget;
    }

    public Integer getBaseDayNum() {
        return baseDayNum;
    }

    public void setBaseDayNum(Integer baseDayNum) {
        this.baseDayNum = baseDayNum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getSkip() {
        return skip;
    }

    public void setSkip(Boolean skip) {
        this.skip = skip;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRedStatus() {
        return redStatus;
    }

    public void setRedStatus(Integer redStatus) {
        this.redStatus = redStatus;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}
    
}