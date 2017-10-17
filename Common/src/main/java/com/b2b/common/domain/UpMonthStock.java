package com.b2b.common.domain;

import java.util.Date;

public class UpMonthStock {
    private Integer id;

    private Integer itemid;

    private String itemname;

    private Integer num;

    private Date createdTime;

    private Long costPrice;

    private Long totalCostPrice;

    private String saveMonth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname == null ? null : itemname.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Long costPrice) {
        this.costPrice = costPrice;
    }

    public Long getTotalCostPrice() {
        return totalCostPrice;
    }

    public void setTotalCostPrice(Long totalCostPrice) {
        this.totalCostPrice = totalCostPrice;
    }

    public String getSaveMonth() {
        return saveMonth;
    }

    public void setSaveMonth(String saveMonth) {
        this.saveMonth = saveMonth == null ? null : saveMonth.trim();
    }
}