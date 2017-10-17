package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PersonUser {
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

    private Integer businessId=0;

    private Integer managershopid=0;

    private String companyName;

    private String companyMemo;
    private Integer companyPersonnum;

    private String interfacePerson;

    private BigDecimal discount;
	
	private String post;

    private String weixinnum;

    private String weixinimg;

    private String openid;
	
	private Integer reseauId;
	
	private Integer cityId;
	
	private Integer dingCityId;
	
	private Integer isGm;
	
	private String comboValue;
    
    private List<Privilege> privileges;
	
	private List<Role> roles;
	
	private List<City> citys;
	
	private String cityName;
	
	private Integer gread;

	private Integer managerId;

	private Integer onsaleFeeflag;

	private Integer checkTimeFlag;

	private Integer createdtimeFlag;
	
	private String manageName;

	private Integer contractnum;

    private Integer passNum;

    private Integer unpassNum;

    private Integer num;

    private Integer sevenNum;

    public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
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
        this.interfacePerson = interfacePerson == null ? null : interfacePerson.trim();
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    public String getWeixinnum() {
        return weixinnum;
    }

    public void setWeixinnum(String weixinnum) {
        this.weixinnum = weixinnum == null ? null : weixinnum.trim();
    }

    public String getWeixinimg() {
        return weixinimg;
    }

    public void setWeixinimg(String weixinimg) {
        this.weixinimg = weixinimg == null ? null : weixinimg.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
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
	
	public Integer getDingCityId() {
        return dingCityId;
    }

    public void setDingCityId(Integer dingCityId) {
        this.dingCityId = dingCityId;
    }
	
	public Integer getIsGm() {
        return isGm;
    }

    public void setIsGm(Integer isGm) {
        this.isGm = isGm;
    }

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<City> getCitys() {
		return citys;
	}

	public void setCitys(List<City> citys) {
		this.citys = citys;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	 public Integer getGread() {
        return gread;
    }

    public void setGread(Integer gread) {
        this.gread = gread;
    }

    public Integer getOnsaleFeeflag() {
        return onsaleFeeflag;
    }

    public void setOnsaleFeeflag(Integer onsaleFeeflag) {
        this.onsaleFeeflag = onsaleFeeflag;
    }

    public Integer getCheckTimeFlag() {
        return checkTimeFlag;
    }

    public void setCheckTimeFlag(Integer checkTimeFlag) {
        this.checkTimeFlag = checkTimeFlag;
    }

    public Integer getCreatedtimeFlag() {
        return createdtimeFlag;
    }

    public void setCreatedtimeFlag(Integer createdtimeFlag) {
        this.createdtimeFlag = createdtimeFlag;
    }
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

	public String getManageName() {
		return manageName;
	}

	public void setManageName(String manageName) {
		this.manageName = manageName;
	}

    public String getComboValue() {
        return comboValue;
    }

    public void setComboValue(String comboValue) {
        this.comboValue = comboValue;
    }

    public Integer getContractnum() {
        return contractnum;
    }

    public void setContractnum(Integer contractnum) {
        this.contractnum = contractnum;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSevenNum() {
        return sevenNum;
    }

    public void setSevenNum(Integer sevenNum) {
        this.sevenNum = sevenNum;
    }
}