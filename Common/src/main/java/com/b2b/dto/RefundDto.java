package com.b2b.dto;

import org.springframework.beans.BeanUtils;

import com.b2b.common.domain.Refund;

public class RefundDto {

	private static final long serialVersionUID = -8871595175991994222L;

	private String userName;
	
	private String mobilePhone;
	
	private Integer isLock;
	
	private String operating;

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public RefundDto() {
		super();
	}

	public RefundDto(Refund refund) {
		this();
		BeanUtils.copyProperties(refund, this);
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public String getOperating() {
		return operating;
	}

	public void setOperating(String operating) {
		this.operating = operating;
	}
	
	
}
