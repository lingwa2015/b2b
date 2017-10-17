package com.b2b.common.domain;

import java.util.Date;

public class Payment {
    private Long id;

    private Integer supplierId;

    private String supplier;

    private Date paymentTime;

    private Date paymentedTime;

    private Date sumdate;

    private Long stockPrice;

    private Long outstockPrice;

    private Long paymentPrice;

    private Long paymentingPrice;

    private Long notpaymentPrice;

    private Long preferentialBenefitPrice;

    private Long receiptPrice;

    private Integer applicantId;

    private String applicant;

    private String remake;

    private Integer status;

    private Date createdTime;

    private Date updateTime;

    private Integer userId;

    private String userName;

    private String receiptNo;

    private Integer cityId;

    private Long beforeMonthNotpaymentPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentedTime() {
        return paymentedTime;
    }

    public void setPaymentedTime(Date paymentedTime) {
        this.paymentedTime = paymentedTime;
    }

    public Date getSumdate() {
        return sumdate;
    }

    public void setSumdate(Date sumdate) {
        this.sumdate = sumdate;
    }

    public Long getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Long stockPrice) {
        this.stockPrice = stockPrice;
    }

    public Long getOutstockPrice() {
        return outstockPrice;
    }

    public void setOutstockPrice(Long outstockPrice) {
        this.outstockPrice = outstockPrice;
    }

    public Long getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(Long paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public Long getPaymentingPrice() {
        return paymentingPrice;
    }

    public void setPaymentingPrice(Long paymentingPrice) {
        this.paymentingPrice = paymentingPrice;
    }

    public Long getNotpaymentPrice() {
        return notpaymentPrice;
    }

    public void setNotpaymentPrice(Long notpaymentPrice) {
        this.notpaymentPrice = notpaymentPrice;
    }

    public Long getPreferentialBenefitPrice() {
        return preferentialBenefitPrice;
    }

    public void setPreferentialBenefitPrice(Long preferentialBenefitPrice) {
        this.preferentialBenefitPrice = preferentialBenefitPrice;
    }

    public Long getReceiptPrice() {
        return receiptPrice;
    }

    public void setReceiptPrice(Long receiptPrice) {
        this.receiptPrice = receiptPrice;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo == null ? null : receiptNo.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Long getBeforeMonthNotpaymentPrice() {
        return beforeMonthNotpaymentPrice;
    }

    public void setBeforeMonthNotpaymentPrice(Long beforeMonthNotpaymentPrice) {
        this.beforeMonthNotpaymentPrice = beforeMonthNotpaymentPrice;
    }
}