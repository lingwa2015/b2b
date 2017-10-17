package com.b2b.common.domain;

public class RefundItem {
    private Integer id;

    private Integer itemId;

    private Integer num;

    private String itemName;

    private Integer refundId;

    private Long itemPrice;

    private Long totalFee;

    private Long itemCostFee;

    private Long costFee;

    private String size;

    private String sizeValue;
    
    private String position;
	
	private Integer reason;
    
    public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public Long getItemCostFee() {
        return itemCostFee;
    }

    public void setItemCostFee(Long itemCostFee) {
        this.itemCostFee = itemCostFee;
    }

    public Long getCostFee() {
        return costFee;
    }

    public void setCostFee(Long costFee) {
        this.costFee = costFee;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(String sizeValue) {
        this.sizeValue = sizeValue == null ? null : sizeValue.trim();
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }
}