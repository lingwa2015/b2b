package com.b2b.common.domain;

import java.util.Date;

public class Stock {
    private Integer id;

    private Integer itemId;

    private String itemName;

    private Integer num;

    private String position;

    private Integer type;

    private Integer state;

    private Date createTime;

    private Integer alertNum;
	
	private Date modifyTime;
	
	private Integer warningNum;

    private Date lastTime;
    
    private String size;
    
    private Long itemCostPrice;
    
    private Long totalAmount;
    
    private Integer isdown;
    
    private Integer day;
    
    private Integer saleSizeNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

 

    public Integer getAlertNum() {
        return alertNum;
    }

    public void setAlertNum(Integer alertNum) {
        this.alertNum = alertNum;
    }
	
	 public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getWarningNum() {
        return warningNum;
    }

    public void setWarningNum(Integer warningNum) {
        this.warningNum = warningNum;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Long getItemCostPrice() {
		return itemCostPrice;
	}

	public void setItemCostPrice(Long itemCostPrice) {
		this.itemCostPrice = itemCostPrice;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getIsdown() {
		return isdown;
	}

	public void setIsdown(Integer isdown) {
		this.isdown = isdown;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getSaleSizeNum() {
		return saleSizeNum;
	}

	public void setSaleSizeNum(Integer saleSizeNum) {
		this.saleSizeNum = saleSizeNum;
	}
	
}