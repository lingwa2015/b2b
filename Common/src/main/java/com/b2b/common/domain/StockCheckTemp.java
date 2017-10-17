package com.b2b.common.domain;

import java.util.Date;

public class StockCheckTemp {
    private Integer itemId;

    private String itemName;

    private Integer modifyNum;

    private String userName;

    private Integer userId;

    private Integer oldNum;

    private Date created;

    private Integer cityId;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getModifyNum() {
        return modifyNum;
    }

    public void setModifyNum(Integer modifyNum) {
        this.modifyNum = modifyNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOldNum() {
        return oldNum;
    }

    public void setOldNum(Integer oldNum) {
        this.oldNum = oldNum;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}