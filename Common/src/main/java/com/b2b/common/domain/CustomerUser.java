package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CustomerUser {
	private Integer id;

	private String userName;

	private String passWord;

	private String address;

	private String mobilePhone;

	private Date createdTime;

	private Integer createdUserid;

	private Date updatedTime;

	private Integer updatedUserid;

	private Integer status;

	private Integer isadmin;

	private Integer businessId;

	private Integer managershopid;

	private String companyName;

	private String companyMemo;

	private Integer companyPersonnum;

	private String interfacePerson;

	private BigDecimal discount;

	private Long budget;

	private Integer iswxvip;

	private String position;

	private String likeman;

	private Integer payBillWay;

	private Integer goodsShelfNum;

	private Integer popsicleIceboxNum;

	private Integer drinkIceboxNum;

	private Integer buyWay;

	private Date listTime;

	private Long money;

	private BigDecimal shopDiscount;

	private String region;

	private String grade;

    private Integer regionId;

	private Date contractDate;

	private Integer contractDay;
	private Date checkTime;
	private String special;
	
	private Integer reseauId;
	
	 private Integer cityId;
	
	private String reseauName;
	
	private String contractNumber;

    private Integer interfacePersonId;

    private BigDecimal royaltyCoefficient;
    
    private String dutyParagraph;
	
	private String checkStr;
	
	private List<String> checks;

	private Integer deliveryId;

	private Date submitTime;

	private Integer checkstatus;

	private String checkremark;

	private Integer passNum;

	private Integer unpassNum;

	private Integer checkingNum;

	private Integer num;

	private Integer submitNum;

	private Integer sevenNum;
	
	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord == null ? null : passWord.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getCreatedUserid() {
		return createdUserid;
	}

	public void setCreatedUserid(Integer createdUserid) {
		this.createdUserid = createdUserid;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getUpdatedUserid() {
		return updatedUserid;
	}

	public void setUpdatedUserid(Integer updatedUserid) {
		this.updatedUserid = updatedUserid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Integer getManagershopid() {
		return managershopid;
	}

	public void setManagershopid(Integer managershopid) {
		this.managershopid = managershopid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName == null ? null : companyName.trim();
	}

	public String getCompanyMemo() {
		return companyMemo;
	}

	public void setCompanyMemo(String companyMemo) {
		this.companyMemo = companyMemo == null ? null : companyMemo.trim();
	}

	public Integer getCompanyPersonnum() {
		return companyPersonnum;
	}

	public void setCompanyPersonnum(Integer companyPersonnum) {
		this.companyPersonnum = companyPersonnum;
	}

	public String getInterfacePerson() {
		return interfacePerson;
	}

	public void setInterfacePerson(String interfacePerson) {
		this.interfacePerson = interfacePerson == null ? null : interfacePerson
				.trim();
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
	}

	public Integer getIswxvip() {
		return iswxvip;
	}

	public void setIswxvip(Integer iswxvip) {
		this.iswxvip = iswxvip;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}

	public String getLikeman() {
		return likeman;
	}

	public void setLikeman(String likeman) {
		this.likeman = likeman == null ? null : likeman.trim();
	}

	public Integer getPayBillWay() {
		return payBillWay;
	}

	public void setPayBillWay(Integer payBillWay) {
		this.payBillWay = payBillWay;
	}

	public Integer getGoodsShelfNum() {
		return goodsShelfNum;
	}

	public void setGoodsShelfNum(Integer goodsShelfNum) {
		this.goodsShelfNum = goodsShelfNum;
	}

	public Integer getPopsicleIceboxNum() {
		return popsicleIceboxNum;
	}

	public void setPopsicleIceboxNum(Integer popsicleIceboxNum) {
		this.popsicleIceboxNum = popsicleIceboxNum;
	}

	public Integer getDrinkIceboxNum() {
		return drinkIceboxNum;
	}

	public void setDrinkIceboxNum(Integer drinkIceboxNum) {
		this.drinkIceboxNum = drinkIceboxNum;
	}

	public Integer getBuyWay() {
		return buyWay;
	}

	public void setBuyWay(Integer buyWay) {
		this.buyWay = buyWay;
	}

	public Date getListTime() {
		return listTime;
	}

	public void setListTime(Date listTime) {
		this.listTime = listTime;
	}

	public BigDecimal getShopDiscount() {
		return shopDiscount;
	}

	public void setShopDiscount(BigDecimal shopDiscount) {
		this.shopDiscount = shopDiscount;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region == null ? null : region.trim();
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade == null ? null : grade.trim();
	}

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Integer getContractDay() {
		return contractDay;
	}

	public void setContractDay(Integer contractDay) {
		this.contractDay = contractDay;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special == null ? null : special.trim();
    }

    public Integer getReseauId() {
        return reseauId;
    }

    public void setReseauId(Integer reseauId) {
        this.reseauId = reseauId;
    }
	
	public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

	public String getReseauName() {
		return reseauName;
	}

	public void setReseauName(String reseauName) {
		this.reseauName = reseauName;
	}
     public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber == null ? null : contractNumber.trim();
    }

    public Integer getInterfacePersonId() {
        return interfacePersonId;
    }

    public void setInterfacePersonId(Integer interfacePersonId) {
        this.interfacePersonId = interfacePersonId;
    }

    public BigDecimal getRoyaltyCoefficient() {
        return royaltyCoefficient;
    }

    public void setRoyaltyCoefficient(BigDecimal royaltyCoefficient) {
        this.royaltyCoefficient = royaltyCoefficient;
    }

	public String getDutyParagraph() {
		return dutyParagraph;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}
    
    public String getCheckStr() {
        return checkStr;
    }

    public void setCheckStr(String checkStr) {
        this.checkStr = checkStr == null ? null : checkStr.trim();
    }

	public List<String> getChecks() {
		return checks;
	}

	public void setChecks(List<String> checks) {
		this.checks = checks;
	}

	public Integer getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Integer getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(Integer checkstatus) {
		this.checkstatus = checkstatus;
	}

	public String getCheckremark() {
		return checkremark;
	}

	public void setCheckremark(String checkremark) {
		this.checkremark = checkremark;
	}

	public Integer getPassNum() {
		return passNum;
	}

	public void setPassNum(Integer passNum) {
		this.passNum = passNum;
	}

	public Integer getUnpassNum() {
		return unpassNum;
	}

	public void setUnpassNum(Integer unpassNum) {
		this.unpassNum = unpassNum;
	}

	public Integer getCheckingNum() {
		return checkingNum;
	}

	public void setCheckingNum(Integer checkingNum) {
		this.checkingNum = checkingNum;
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
}