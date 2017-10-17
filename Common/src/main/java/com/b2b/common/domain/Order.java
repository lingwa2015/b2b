package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order extends BaseTranDetail{
    private String orderNo;

    private Integer totalNum;

    private Long totalFee;

    private Long totalCost;

    private Long notaxInclusiveTotalCost;

    private Date createdTime;

    private Integer orderStatus;

    private Integer userId;

    private Integer businessId;

    private Integer status;

    private String address;

    private String memo;

    private Date executedTime;

	private String buyerPhone;

	private String buyerName;
	
	private String companyName;
	
	private String userName;

	private String companyNemo;
	
	private BigDecimal discount;

	private List<OrderItem> orderItemList;
	
    private Integer payBillWay;
    
	private Integer sign;
	
	private Integer comfirm;

    private Integer flag;
	
	private String zhidan;

    private String peisong;

    private String fenjian;
	
	private String sourcingId;
	
	private Date deliverDate;
	
	private Integer cityId;
	
	private Integer type;

    public Integer getPayBillWay() {
		return payBillWay;
	}

	public void setPayBillWay(Integer payBillWay) {
		this.payBillWay = payBillWay;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public Long getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Long totalCost) {
		this.totalCost = totalCost;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

    public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
	}
    public Long getNotaxInclusiveTotalCost() {
        return notaxInclusiveTotalCost;
    }

    public void setNotaxInclusiveTotalCost(Long notaxInclusiveTotalCost) {
        this.notaxInclusiveTotalCost = notaxInclusiveTotalCost;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(Date executedTime) {
        this.executedTime = executedTime;
        super.executedTime=executedTime;
    }

	public String getCompanyMemo() {
		return companyNemo;
	}

	public void setCompanyMemo(String companyNemo) {
		this.companyNemo = companyNemo;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	 public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }
	 public Integer getComfirm() {
        return comfirm;
    }

    public void setComfirm(Integer comfirm) {
        this.comfirm = comfirm;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getZhidan() {
        return zhidan;
    }

    public void setZhidan(String zhidan) {
        this.zhidan = zhidan == null ? null : zhidan.trim();
    }

    public String getPeisong() {
        return peisong;
    }

    public void setPeisong(String peisong) {
        this.peisong = peisong == null ? null : peisong.trim();
    }

    public String getFenjian() {
        return fenjian;
    }

    public void setFenjian(String fenjian) {
        this.fenjian = fenjian == null ? null : fenjian.trim();
    }

    public String getSourcingId() {
        return sourcingId;
    }

    public void setSourcingId(String sourcingId) {
        this.sourcingId = sourcingId == null ? null : sourcingId.trim();
    }
    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}