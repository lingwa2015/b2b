package com.b2b.common.domain;

import java.util.Date;

public class AccountLock {
    private String years;

    private String months;

    private Integer locks;

    private Date createdTime;

    private Integer userId;

    private Integer cityId;

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years == null ? null : years.trim();
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months == null ? null : months.trim();
    }

    public Integer getLocks() {
        return locks;
    }

    public void setLocks(Integer locks) {
        this.locks = locks;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}