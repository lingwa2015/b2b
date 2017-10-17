package com.b2b.common.domain;

import java.util.Date;

public class BaseTranDetail implements Comparable<BaseTranDetail>{

	protected Date executedTime;
	protected String no;
	protected Long totalFee;
	protected String region;
private String userName;
	
	private String mobilePhone;
	
	private Integer isLock;
	
	private String operating;
	
	private String nickName;
	public Date getExecutedTime() {
		return executedTime;
	}

	public void setExecutedTime(Date executedTime) {
		this.executedTime = executedTime;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Long getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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
	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public int compareTo(BaseTranDetail o) {
		if(o.getExecutedTime()!=null&&this.getExecutedTime()!=null){
			return o.getExecutedTime().compareTo(this.getExecutedTime());
		}
		return 0;
	}


}
