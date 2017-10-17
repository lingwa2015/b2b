package com.b2b.common.domain;

public class OrderItem extends BaseOrderItem {
    private Integer id;

    private String orderNo;

    private Integer itemId;

    private String itemName;

    private Integer num;

    private Long fee;

    private Long itemPrice;

    private String itemSize;

    private Long itemCostPrice;

    private Long notaxInclusiveCostPrice;

    private String itemSizeType;

    private Integer consumeStockNum;

    private float bigNum;
    
    private int big;
    
    private String saleSize;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
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

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize == null ? null : itemSize.trim();
    }

    public Long getItemCostPrice() {
        return itemCostPrice;
    }

    public void setItemCostPrice(Long itemCostPrice) {
        this.itemCostPrice = itemCostPrice;
    }

    public Long getNotaxInclusiveCostPrice() {
        return notaxInclusiveCostPrice;
    }

    public void setNotaxInclusiveCostPrice(Long notaxInclusiveCostPrice) {
        this.notaxInclusiveCostPrice = notaxInclusiveCostPrice;
    }

    public String getItemSizeType() {
        return itemSizeType;
    }

    public void setItemSizeType(String itemSizeType) {
        this.itemSizeType = itemSizeType == null ? null : itemSizeType.trim();
    }

    public Integer getConsumeStockNum() {
        return consumeStockNum;
    }

    public void setConsumeStockNum(Integer consumeStockNum) {
        this.consumeStockNum = consumeStockNum;
    }

	public float getBigNum() {
		return bigNum;
	}

	public void setBigNum(float bigNum) {
		this.bigNum = bigNum;
	}

	public int getBig() {
		float num2 = getBigNum();
		int a = 0;
		if(num2 % 1 ==0){
			a = (int) num2;
		}
		return a;
	}

	public String getSaleSize() {
		return saleSize;
	}

	public void setSaleSize(String saleSize) {
		this.saleSize = saleSize;
	}
	
	
//    public long getProfitFee() {
//		long price = (itemPrice==null)?0:itemPrice;
//		long costPrice = (itemCostPrice==null)?0:itemCostPrice;
//
//		profitFee = price - costPrice;
//		return profitFee;
//	}
//
//	public void setProfitFee(Long profitFee) {
//		this.profitFee = profitFee;
//	}
    
    

}