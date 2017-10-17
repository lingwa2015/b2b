package com.b2b.common.domain;

import java.util.Date;

public class SalesmanPerformance {
    private Integer id;

    private String interfaceMan;

    private Integer orderNum;

    private Integer refundNum;

    private Integer companyId;

    private String conpanyName;

    private Long afterMarsaleMoney;

    private Long saleMoney;

    private Long newSaleMoney;

    private Long bagMoney;

    private Date countDate;

    private Date createTime;
    
    private Integer customerNum;

    public Integer getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(Integer customerNum) {
		this.customerNum = customerNum;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterfaceMan() {
        return interfaceMan;
    }

    public void setInterfaceMan(String interfaceMan) {
        this.interfaceMan = interfaceMan == null ? null : interfaceMan.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getConpanyName() {
        return conpanyName;
    }

    public void setConpanyName(String conpanyName) {
        this.conpanyName = conpanyName == null ? null : conpanyName.trim();
    }

    public Long getAfterMarsaleMoney() {
        return afterMarsaleMoney;
    }

    public void setAfterMarsaleMoney(Long afterMarsaleMoney) {
        this.afterMarsaleMoney = afterMarsaleMoney;
    }

    public Long getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(Long saleMoney) {
        this.saleMoney = saleMoney;
    }

    public Long getNewSaleMoney() {
        return newSaleMoney;
    }

    public void setNewSaleMoney(Long newSaleMoney) {
        this.newSaleMoney = newSaleMoney;
    }

    public Long getBagMoney() {
        return bagMoney;
    }

    public void setBagMoney(Long bagMoney) {
        this.bagMoney = bagMoney;
    }

    public Date getCountDate() {
        return countDate;
    }

    public void setCountDate(Date countDate) {
        this.countDate = countDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}