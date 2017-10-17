package com.sxc.natasha.domain;

import java.io.Serializable;

/**
 * Created by longpo on 15/3/19.
 */
public class ItemModel implements Serializable {
    private int id;
    private float bjPrice;
    private String img;
    private float itemPrice;
    private String itemTitle;
    private int salStock;
    private int packageWeight;
    private String unit;

    private int limitPer;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(int packageWeight) {
        this.packageWeight = packageWeight;
    }

    public int getSalStock() {
        return salStock;
    }

    public void setSalStock(int salStock) {
        this.salStock = salStock;
    }

    public int getLimitPer() {
        return limitPer;
    }

    public void setLimitPer(int limitPer) {
        this.limitPer = limitPer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBjPrice() {
        return bjPrice;
    }

    public void setBjPrice(float bjPrice) {
        this.bjPrice = bjPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }


}
