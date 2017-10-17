package com.b2b.common.domain;

import java.util.Date;

public class WXUserOrder {
    private Integer id;

    private Integer wxuserId;

    private Integer snackpackageId;

    private Integer snackpackageNum;

    private Long snackpackageFee;

    private Long snackpackageTotal;

    private Integer payfeeStatus;

    private Integer invoiceStatus;

    private Integer invoiceId;

    private Integer addressId;

    private String fastexpressNo;

    private Integer status;

    private Date createdTime;

    private Date updatedTime;

    private String wxorderNum;

    private Integer updatedUserid;
	
	  private String wxname;

    private String wxphone;

    private String wxaddress;
    
    
    private String addressDetails;
    
    private String name;
    
    private String phone;
    
    private String spValue;
    
    private String imgPath;
    
    private String companyName;
	
	private Integer groups;
	
	private Integer expire;
    
    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSpValue() {
		return spValue;
	}

	public void setSpValue(String spValue) {
		this.spValue = spValue;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWxuserId() {
        return wxuserId;
    }

    public void setWxuserId(Integer wxuserId) {
        this.wxuserId = wxuserId;
    }

    public Integer getSnackpackageId() {
        return snackpackageId;
    }

    public void setSnackpackageId(Integer snackpackageId) {
        this.snackpackageId = snackpackageId;
    }

    public Integer getSnackpackageNum() {
        return snackpackageNum;
    }

    public void setSnackpackageNum(Integer snackpackageNum) {
        this.snackpackageNum = snackpackageNum;
    }

    public Long getSnackpackageFee() {
        return snackpackageFee;
    }

    public void setSnackpackageFee(Long snackpackageFee) {
        this.snackpackageFee = snackpackageFee;
    }

    public Long getSnackpackageTotal() {
        return snackpackageTotal;
    }

    public void setSnackpackageTotal(Long snackpackageTotal) {
        this.snackpackageTotal = snackpackageTotal;
    }

    public Integer getPayfeeStatus() {
        return payfeeStatus;
    }

    public void setPayfeeStatus(Integer payfeeStatus) {
        this.payfeeStatus = payfeeStatus;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getFastexpressNo() {
        return fastexpressNo;
    }

    public void setFastexpressNo(String fastexpressNo) {
        this.fastexpressNo = fastexpressNo == null ? null : fastexpressNo.trim();
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

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getWxorderNum() {
        return wxorderNum;
    }

    public void setWxorderNum(String wxorderNum) {
        this.wxorderNum = wxorderNum == null ? null : wxorderNum.trim();
    }

    public Integer getUpdatedUserid() {
        return updatedUserid;
    }

    public void setUpdatedUserid(Integer updatedUserid) {
        this.updatedUserid = updatedUserid;
    }

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname == null ? null : wxname.trim();
    }

    public String getWxphone() {
        return wxphone;
    }

    public void setWxphone(String wxphone) {
        this.wxphone = wxphone == null ? null : wxphone.trim();
    }

    public String getWxaddress() {
        return wxaddress;
    }

    public void setWxaddress(String wxaddress) {
        this.wxaddress = wxaddress == null ? null : wxaddress.trim();
    }

    public Integer getGroups() {
        return groups;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

	public Integer getExpire() {
		return expire;
	}

	public void setExpire(Integer expire) {
		this.expire = expire;
	}
    
}