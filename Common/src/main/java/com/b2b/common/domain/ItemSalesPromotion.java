package com.b2b.common.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ItemSalesPromotion {
    private Integer id;

    private String itemName;

    private Integer itemId;

    private Integer type;

    private Long money;

    private BigDecimal discount;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Date createdTime;

    private Integer createdUserid;

    private Integer cityId;

    private String remark;

    private Integer shopType;
    
    private Integer sign;
    
    private Integer shopItemId;
    
    private Integer manNum;
    
    private Integer penNum;
    
    private Long consumefee;
    
    private Long yhfee;
    
    private Integer consumeNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Integer getCreatedUserid() {
        return createdUserid;
    }

    public void setCreatedUserid(Integer createdUserid) {
        this.createdUserid = createdUserid;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getShopType() {
        return shopType;
    }

    public void setShopType(Integer shopType) {
        this.shopType = shopType;
    }

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public Integer getShopItemId() {
		return shopItemId;
	}

	public void setShopItemId(Integer shopItemId) {
		this.shopItemId = shopItemId;
	}

	public Integer getManNum() {
		return manNum;
	}

	public void setManNum(Integer manNum) {
		this.manNum = manNum;
	}

	public Integer getPenNum() {
		return penNum;
	}

	public void setPenNum(Integer penNum) {
		this.penNum = penNum;
	}

	public Long getConsumefee() {
		return consumefee;
	}

	public void setConsumefee(Long consumefee) {
		this.consumefee = consumefee;
	}

	public Long getYhfee() {
		return yhfee;
	}

	public void setYhfee(Long yhfee) {
		this.yhfee = yhfee;
	}

	public Integer getConsumeNum() {
		return consumeNum;
	}

	public void setConsumeNum(Integer consumeNum) {
		this.consumeNum = consumeNum;
	}
    
	
}