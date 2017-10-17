package com.sxc.natasha.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by longpo on 2015/3/26.
 */
public class PickStoreHouse implements Serializable {

    private Integer id;
    private String name;
    private String address;
    private String phone;
    private Date createTime;
    private Integer state;
    private String manager;
    private Integer houseType;
    private String areaCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Integer getHouseType() {
        return houseType;
    }

    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Override
    public String toString() {
        return "PickStoreHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", createTime=" + createTime +
                ", state=" + state +
                ", manager='" + manager + '\'' +
                ", houseType=" + houseType +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }
}
