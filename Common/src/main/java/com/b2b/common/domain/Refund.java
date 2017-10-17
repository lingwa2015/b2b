package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Refund extends BaseTranDetail{
    private Integer id;

    private Integer userId;

    private Date createdTime;

    private Date modifiedTime;

    private Integer state;

    private Integer lastModUser;

    private Date executedTime;

    private List<RefundItem> refundItemList;

    private long amount;

    private Long totalFee;

    private Long costFee;

    private Long notaxinclusivecostfee;

    private BigDecimal discount;
	private Integer reason;
	
	private String remark;
	
	private Integer cityId;
    
    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public List<RefundItem> getRefundItemList() {
        return refundItemList;
    }

    public void setRefundItemList(List<RefundItem> refundItemList) {
        this.refundItemList = refundItemList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLastModUser() {
        return lastModUser;
    }

    public void setLastModUser(Integer lastModUser) {
        this.lastModUser = lastModUser;
    }

    public Date getExecutedTime() {
        return executedTime;
    }

    public void setExecutedTime(Date executedTime) {
        this.executedTime = executedTime;
        super.executedTime=executedTime;
	}
	
	
    public Long getCostFee() {
        return costFee;
    }

    public void setCostFee(Long costFee) {
        this.costFee = costFee;
    }

    public Long getNotaxinclusivecostfee() {
        return notaxinclusivecostfee;
    }

    public void setNotaxinclusivecostfee(Long notaxinclusivecostfee) {
        this.notaxinclusivecostfee = notaxinclusivecostfee;
    }

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}