package com.b2b.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.b2b.common.domain.BaseTranDetail;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.Refund;
import com.b2b.common.domain.TranSum;

public class TranSumDto extends TranSum {

	private static final long serialVersionUID = -8871595175991994229L;

	private String userName;

	private String companyName;

	private String userPhone;

	private Integer userId;

	private List<Order> orderList;

	private List<Refund> refundList;

	private List<BaseTranDetail> detailList;

	private String interfacePerson;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public List<Refund> getRefundList() {
		return refundList;
	}

	public void setRefundList(List<Refund> refundList) {
		this.refundList = refundList;
	}

	public List<BaseTranDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<BaseTranDetail> detailList) {
		this.detailList = detailList;
	}
	
	public String getInterfacePerson() {
        return interfacePerson;
    }

    public void setInterfacePerson(String interfacePerson) {
        this.interfacePerson = interfacePerson == null ? null : interfacePerson.trim();
    }

	public TranSumDto() {
		super();
	}

	public TranSumDto(TranSum tranSum) {
		this();
		BeanUtils.copyProperties(tranSum, this);
	}

}
