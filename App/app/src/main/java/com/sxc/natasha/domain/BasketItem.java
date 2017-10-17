package com.sxc.natasha.domain;

/**
 * Created by longpo on 15/3/19.
 */
public class BasketItem {
    private int id;
    private String title;
    private float price;
    private float priceOld;
    private int count;
    private String image;
    private int salStock;
    private int limitPer;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(float priceOld) {
        this.priceOld = priceOld;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
