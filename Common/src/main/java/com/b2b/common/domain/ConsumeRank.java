package com.b2b.common.domain;

public class ConsumeRank {
    private Integer id;

    private Integer shopId;

    private String name;

    private String imgPath;

    private Long sourcingPrice;

    private Long marketPrice;

    private Long salePrice;

    private Integer num;

    private String size;

    private Integer consumeNum;

    private Integer itemId;
    
    private Integer one;
    
    private Integer two;

    private Integer three;
    
    private Integer four;
    
    private Integer five;
    
    private Integer six;
    
    private Integer seven;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath == null ? null : imgPath.trim();
    }

    public Long getSourcingPrice() {
        return sourcingPrice;
    }

    public void setSourcingPrice(Long sourcingPrice) {
        this.sourcingPrice = sourcingPrice;
    }

    public Long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public Integer getConsumeNum() {
        return consumeNum;
    }

    public void setConsumeNum(Integer consumeNum) {
        this.consumeNum = consumeNum;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

	public Integer getOne() {
		return one;
	}

	public void setOne(Integer one) {
		this.one = one;
	}

	public Integer getTwo() {
		return two;
	}

	public void setTwo(Integer two) {
		this.two = two;
	}

	public Integer getThree() {
		return three;
	}

	public void setThree(Integer three) {
		this.three = three;
	}

	public Integer getFour() {
		return four;
	}

	public void setFour(Integer four) {
		this.four = four;
	}

	public Integer getFive() {
		return five;
	}

	public void setFive(Integer five) {
		this.five = five;
	}

	public Integer getSix() {
		return six;
	}

	public void setSix(Integer six) {
		this.six = six;
	}

	public Integer getSeven() {
		return seven;
	}

	public void setSeven(Integer seven) {
		this.seven = seven;
	}
    
}