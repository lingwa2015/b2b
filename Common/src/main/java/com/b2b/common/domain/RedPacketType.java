package com.b2b.common.domain;

public class RedPacketType {
    private Integer id;

    private Integer redPacketId;

    private Integer type;

    private Long downFee;

    private Long upFee;

    private Integer num;

    private Long fee;
    
    private Integer rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(Integer redPacketId) {
        this.redPacketId = redPacketId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getDownFee() {
        return downFee;
    }

    public void setDownFee(Long downFee) {
        this.downFee = downFee;
    }

    public Long getUpFee() {
        return upFee;
    }

    public void setUpFee(Long upFee) {
        this.upFee = upFee;
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

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}
    
}