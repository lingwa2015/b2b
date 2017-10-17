package com.sxc.natasha.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Order implements Serializable{
	private static final long serialVersionUID = 3211697210519602507L;

	private String orderNo;

    private Integer num;

    private Long totalFee;

    private Integer buyerId;

    private String buyerName;

    private Integer sellerId;

    private String sellerName;

    private Integer payType;

    private Integer orderStatus;

    private Date createTime;

    private Integer state;

    private Integer operatorUserId;

    private String areaCode;

    private Integer pickhouseId;

    private Integer centerhouseId;

    private Date picktime;

    private Integer buyerPickhouseId;

    private Date lastUpdateTime;

    List<OrderSKU> skuList;




    private Date payTime;
    private String payTimeStr;
    private String createTimeStr;
    private String pickTimeStr;
    private String buyerMobilePhone;
    private String pickStoreHouseName;
    private String pickStoreHouseAddress;
    private String pickStoreHouseManagerName;
    private String pickStoreHouseManagerPhone;
    private Long discountedFee;

    public Long getDiscountedFee() {
        return discountedFee;
    }

    public void setDiscountedFee(Long discountedFee) {
        this.discountedFee = discountedFee;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPickStoreHouseAddress() {
        return pickStoreHouseAddress;
    }

    public void setPickStoreHouseAddress(String pickStoreHouseAddress) {
        this.pickStoreHouseAddress = pickStoreHouseAddress;
    }

    public String getPayTimeStr() {
        return payTimeStr;
    }

    public void setPayTimeStr(String payTimeStr) {
        this.payTimeStr = payTimeStr;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getPickTimeStr() {
        return pickTimeStr;
    }

    public void setPickTimeStr(String pickTimeStr) {
        this.pickTimeStr = pickTimeStr;
    }

    public String getBuyerMobilePhone() {
        return buyerMobilePhone;
    }

    public void setBuyerMobilePhone(String buyerMobilePhone) {
        this.buyerMobilePhone = buyerMobilePhone;
    }

    public String getPickStoreHouseName() {
        return pickStoreHouseName;
    }

    public void setPickStoreHouseName(String pickStoreHouseName) {
        this.pickStoreHouseName = pickStoreHouseName;
    }



    public String getPickStoreHouseManagerName() {
        return pickStoreHouseManagerName;
    }

    public void setPickStoreHouseManagerName(String pickStoreHouseManagerName) {
        this.pickStoreHouseManagerName = pickStoreHouseManagerName;
    }

    public String getPickStoreHouseManagerPhone() {
        return pickStoreHouseManagerPhone;
    }

    public void setPickStoreHouseManagerPhone(String pickStoreHouseManagerPhone) {
        this.pickStoreHouseManagerPhone = pickStoreHouseManagerPhone;
    }

    public void setSkuList(List<OrderSKU> skuList) {
        this.skuList = skuList;
    }

    public List<OrderSKU> getSkuList() {

        return skuList;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public Integer getPickhouseId() {
        return pickhouseId;
    }

    public void setPickhouseId(Integer pickhouseId) {
        this.pickhouseId = pickhouseId;
    }

    public Integer getCenterhouseId() {
        return centerhouseId;
    }

    public void setCenterhouseId(Integer centerhouseId) {
        this.centerhouseId = centerhouseId;
    }

    public Date getPicktime() {
        return picktime;
    }

    public void setPicktime(Date picktime) {
        this.picktime = picktime;
    }

    public Integer getBuyerPickhouseId() {
        return buyerPickhouseId;
    }

    public void setBuyerPickhouseId(Integer buyerPickhouseId) {
        this.buyerPickhouseId = buyerPickhouseId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}