package com.b2b.common.domain;

import java.util.Date;
import java.util.List;

public class ShopOrder {
    private String id;

    private Long totalPrice;

    private Long freePrice;

    private Long redPrice;

    private Long actualPrice;

    private Integer buyerId;

    private Integer shopId;

    private String buyerName;

    private String shopName;

    private Date createdTime;

    private Integer status;
    
    private String headImgurl;
    
    private Long sourcing;
    
    private Long consume;
    
    private String month;
    
    private List<ShopOrderItem> shopOrderItems;
    
    private Long loss;
    
    private Long subsidy;
	
	private String imgPath;

    private Integer sign;
	
	private Integer cityId;
    
    private Long expend;
    
    private Long shelf;
    
    private Long last;
    
    private Long redFee;
    
    private Long lwFeeOne;

    private Integer lwType;

    private Integer hdId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getFreePrice() {
        return freePrice;
    }

    public void setFreePrice(Long freePrice) {
        this.freePrice = freePrice;
    }

    public Long getRedPrice() {
        return redPrice;
    }

    public void setRedPrice(Long redPrice) {
        this.redPrice = redPrice;
    }

    public Long getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Long actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getHeadImgurl() {
		return headImgurl;
	}

	public void setHeadImgurl(String headImgurl) {
		this.headImgurl = headImgurl;
	}

	public Long getSourcing() {
		return sourcing;
	}

	public void setSourcing(Long sourcing) {
		this.sourcing = sourcing;
	}

	public Long getConsume() {
		return consume;
	}

	public void setConsume(Long consume) {
		this.consume = consume;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<ShopOrderItem> getShopOrderItems() {
		return shopOrderItems;
	}

	public void setShopOrderItems(List<ShopOrderItem> shopOrderItems) {
		this.shopOrderItems = shopOrderItems;
	}

	public Long getLoss() {
		return loss;
	}

	public void setLoss(Long loss) {
		this.loss = loss;
	}

	public Long getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(Long subsidy) {
		this.subsidy = subsidy;
	}
    
	public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }
	
	 public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

	public Long getExpend() {
		return expend;
	}

	public void setExpend(Long expend) {
		this.expend = expend;
	}

	public Long getShelf() {
		return shelf;
	}

	public void setShelf(Long shelf) {
		this.shelf = shelf;
	}

	public Long getLast() {
		return last;
	}

	public void setLast(Long last) {
		this.last = last;
	}

	public Long getRedFee() {
		return redFee;
	}

	public void setRedFee(Long redFee) {
		this.redFee = redFee;
	}

	public Long getLwFeeOne() {
		return lwFeeOne;
	}

    public void setLwFeeOne(Long lwFeeOne) {
        this.lwFeeOne = lwFeeOne;
    }

    public Integer getLwType() {
        return lwType;
    }

    public void setLwType(Integer lwType) {
        this.lwType = lwType;
    }

    public Integer getHdId() {
        return hdId;
    }

    public void setHdId(Integer hdId) {
        this.hdId = hdId;
    }
}