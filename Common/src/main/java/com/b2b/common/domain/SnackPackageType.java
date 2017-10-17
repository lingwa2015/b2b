package com.b2b.common.domain;

import java.util.Date;

public class SnackPackageType {
    private Integer id;

    private Integer spType;

    private String spValue;

    private String imgPath;

    private String remark;

    private Integer status;

    private Date createdTime;

    private Integer createdUserid;

    private Date updatedTime;

    private Integer updatedUserid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpType() {
        return spType;
    }

    public void setSpType(Integer spType) {
        this.spType = spType;
    }

    public String getSpValue() {
        return spValue;
    }

    public void setSpValue(String spValue) {
        this.spValue = spValue == null ? null : spValue.trim();
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
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
}