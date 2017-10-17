package com.b2b.common.domain;

import java.util.Date;

public class AfterSalesRecord {
    private Integer id;

    private Integer recordType;

    private Integer doState;

    private String userName;

    private String region;

    private Integer userId;

    private String content;

    private String attachment;

    private Integer status;

    private String recordMan;

    private Date createdTime;

    private Integer flag;

    private String fuzeMan;

    private Integer cityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public Integer getDoState() {
        return doState;
    }

    public void setDoState(Integer doState) {
        this.doState = doState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRecordMan() {
        return recordMan;
    }

    public void setRecordMan(String recordMan) {
        this.recordMan = recordMan == null ? null : recordMan.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getFuzeMan() {
        return fuzeMan;
    }

    public void setFuzeMan(String fuzeMan) {
        this.fuzeMan = fuzeMan == null ? null : fuzeMan.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}