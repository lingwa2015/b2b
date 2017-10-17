package com.b2b.common.domain;

import java.util.Date;

public class ItemVariety {
    private Integer itemvarietyId;

    private String itemvarietyName;

    private String remark;

    private Integer status;

    private Date createdTime;

    private Integer createdUserid;

    private Date updatedTime;

    private Integer updatedUserid;

    private Integer cityId;

    public Integer getItemvarietyId() {
        return itemvarietyId;
    }

    public void setItemvarietyId(Integer itemvarietyId) {
        this.itemvarietyId = itemvarietyId;
    }

    public String getItemvarietyName() {
        return itemvarietyName;
    }

    public void setItemvarietyName(String itemvarietyName) {
        this.itemvarietyName = itemvarietyName == null ? null : itemvarietyName.trim();
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}