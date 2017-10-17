package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ShopSales {
    private Integer id;

    private Integer shopId;

    private String userName;

    private Integer payBillWay;

    private Date contractDate;

    private String contractNumber;

    private Long sumBase;

    private BigDecimal royaltyCoefficient;

    private BigDecimal sumProportion;

    private Long sumFee;

    private Integer cityId;

    private Date sumDate;
    
    private String post;
    
    private Integer interfaceId;
    
    private Integer iswxvip;
    
    private Integer num;

    private Integer submitNum;

    private Integer sevenNum;

    private Date submitTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getPayBillWay() {
        return payBillWay;
    }

    public void setPayBillWay(Integer payBillWay) {
        this.payBillWay = payBillWay;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber == null ? null : contractNumber.trim();
    }

    public Long getSumBase() {
        return sumBase;
    }

    public void setSumBase(Long sumBase) {
        this.sumBase = sumBase;
    }

    public BigDecimal getRoyaltyCoefficient() {
        return royaltyCoefficient;
    }

    public void setRoyaltyCoefficient(BigDecimal royaltyCoefficient) {
        this.royaltyCoefficient = royaltyCoefficient;
    }

    public BigDecimal getSumProportion() {
        return sumProportion;
    }

    public void setSumProportion(BigDecimal sumProportion) {
        this.sumProportion = sumProportion;
    }

    public Long getSumFee() {
        return sumFee;
    }

    public void setSumFee(Long sumFee) {
        this.sumFee = sumFee;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Date getSumDate() {
        return sumDate;
    }

    public void setSumDate(Date sumDate) {
        this.sumDate = sumDate;
    }

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Integer getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Integer interfaceId) {
		this.interfaceId = interfaceId;
	}

	public Integer getIswxvip() {
		return iswxvip;
	}

	public void setIswxvip(Integer iswxvip) {
		this.iswxvip = iswxvip;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

    public Integer getSubmitNum() {
        return submitNum;
    }

    public void setSubmitNum(Integer submitNum) {
        this.submitNum = submitNum;
    }

    public Integer getSevenNum() {
        return sevenNum;
    }

    public void setSevenNum(Integer sevenNum) {
        this.sevenNum = sevenNum;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
}