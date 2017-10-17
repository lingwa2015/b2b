package com.b2b.common.domain;

import java.util.Date;

public class WXUserInvoice {
    private Integer id;

    private Integer wxuserId;

    private String companyName;

    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWxuserId() {
        return wxuserId;
    }

    public void setWxuserId(Integer wxuserId) {
        this.wxuserId = wxuserId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}