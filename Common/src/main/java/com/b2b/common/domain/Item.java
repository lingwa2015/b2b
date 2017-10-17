package com.b2b.common.domain;

import com.b2b.enums.ItemSizeEnum;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

public class Item extends BaseItem {

	private static final long serialVersionUID = 5400511110626500983L;

	private Integer id;

	private Integer categoryId;

	private Integer itemVariety;

	private BigDecimal itemWeight;

	private Integer warehouseId;

	private String itemName;

	private String size;

	private Date createdTime;

	private Integer createdUserid;

	private Integer status;

	private Long price;

	private String imgPath;

	private String bigImgPath;

	private Long costPrice;

	private Long notaxInclusiveCostPrice;

	private String brand;

	private Integer isdown;

	private Long buyPrice;

	private Long notaxInclusiveBuyPrice;

	private String buySize;

	private Integer convertNum;

	private BigDecimal profit;

	private Long salePrice;

	private String saleSize;

	private Integer saleSizeNum;

	private BigDecimal kgNum;

	private Long saleCostPrice;

	private Long notaxInclusiveSaleCostPrice;

	private Long purchasePrice;

	private Integer categorylevelId;

	private Integer purchaseNum;

	private Long totalPurchasePrice;

	private Integer actualUsedStock;

	private Date updatedTime;

	private Integer updatedUserid;

	private BigDecimal weight;

	private Integer customerId;

	private String taste;

	private String place;

    private Integer shelfLife;

	private Long marketPrice;

	

	private String grade;

	private String storeWay;

	private Integer recommend;

	

	private String remark;

	private String barcode;

	private Integer freeSpecialSupply;
	private Long jdPrice;
	private Long tmPrice;
	
	private Long csPrice;
	
	private Integer cityId;

	private Integer isnew;
	
	private Integer blacktotalnum;
	
	private Integer property;

	private Integer kaNum;
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	 public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getCategorylevelId() {
		return categorylevelId;
	}

	public void setCategorylevelId(Integer categorylevelId) {
		this.categorylevelId = categorylevelId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getItemVariety() {
		return itemVariety;
	}

	public void setItemVariety(Integer itemVariety) {
		this.itemVariety = itemVariety;
	}

	public BigDecimal getItemWeight() {
		return itemWeight;
	}

	public void setItemWeight(BigDecimal itemWeight) {
		this.itemWeight = itemWeight;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName == null ? null : itemName.trim();
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size == null ? null : size.trim();
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath == null ? null : imgPath.trim();
	}

	public String getBigImgPath() {
		return bigImgPath;
	}

	public void setBigImgPath(String bigImgPath) {
		this.bigImgPath = bigImgPath == null ? null : bigImgPath.trim();
	}

	public Long getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Long costPrice) {
		this.costPrice = costPrice;
	}

	public Long getNotaxInclusiveCostPrice() {
		return notaxInclusiveCostPrice;
	}

	public void setNotaxInclusiveCostPrice(Long notaxInclusiveCostPrice) {
		this.notaxInclusiveCostPrice = notaxInclusiveCostPrice;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand == null ? null : brand.trim();
	}

	public Integer getIsdown() {
		return isdown;
	}

	public void setIsdown(Integer isdown) {
		this.isdown = isdown;
	}

	public Long getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Long buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Long getNotaxInclusiveBuyPrice() {
		return notaxInclusiveBuyPrice;
	}

	public void setNotaxInclusiveBuyPrice(Long notaxInclusiveBuyPrice) {
		this.notaxInclusiveBuyPrice = notaxInclusiveBuyPrice;
	}

	public String getBuySize() {
		return buySize;
	}

	public void setBuySize(String buySize) {
		this.buySize = buySize == null ? null : buySize.trim();
	}

	public Integer getConvertNum() {
		return convertNum;
	}

	public void setConvertNum(Integer convertNum) {
		this.convertNum = convertNum;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Long getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Long salePrice) {
		this.salePrice = salePrice;
	}

	public String getSaleSize() {
		return saleSize;
	}

	public void setSaleSize(String saleSize) {
		this.saleSize = saleSize == null ? null : saleSize.trim();
	}

	public Integer getSaleSizeNum() {
		return saleSizeNum;
	}

	public void setSaleSizeNum(Integer saleSizeNum) {
		this.saleSizeNum = saleSizeNum;
	}

	public BigDecimal getKgNum() {
		return kgNum;
	}

	public void setKgNum(BigDecimal kgNum) {
		this.kgNum = kgNum;
	}

	public Long getSaleCostPrice() {
		return saleCostPrice;
	}

	public void setSaleCostPrice(Long saleCostPrice) {
		this.saleCostPrice = saleCostPrice;
	}

	public Long getNotaxInclusiveSaleCostPrice() {
		return notaxInclusiveSaleCostPrice;
	}

	public void setNotaxInclusiveSaleCostPrice(Long notaxInclusiveSaleCostPrice) {
		this.notaxInclusiveSaleCostPrice = notaxInclusiveSaleCostPrice;
	}

	public Long getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Long purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(Integer purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	public Long getTotalPurchasePrice() {
		return totalPurchasePrice;
	}

	public void setTotalPurchasePrice(Long totalPurchasePrice) {
		this.totalPurchasePrice = totalPurchasePrice;
	}

	public Integer getActualUsedStock() {
		return actualUsedStock;
	}

	public void setActualUsedStock(Integer actualUsedStock) {
		this.actualUsedStock = actualUsedStock;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStoreWay() {
		return storeWay;
	}

	public void setStoreWay(String storeWay) {
		this.storeWay = storeWay;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getIsnew() {
		return isnew;
	}

	public void setIsnew(Integer isnew) {
		this.isnew = isnew;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode == null ? null : barcode.trim();
	}

	public Integer getFreeSpecialSupply() {
		return freeSpecialSupply;
	}

	public void setFreeSpecialSupply(Integer freeSpecialSupply) {
		this.freeSpecialSupply = freeSpecialSupply;
	}

	public Long getJdPrice() {
		return jdPrice;
	}

	public void setJdPrice(Long jdPrice) {
		this.jdPrice = jdPrice;
	}

	public Long getTmPrice() {
		return tmPrice;
	}

	public void setTmPrice(Long tmPrice) {
		this.tmPrice = tmPrice;
	}

	public String calSize(String sizeType) {
		String value = null;

		if (ItemSizeEnum.SIZE.getName().equals(sizeType)) {
			value = size;
		} else if (ItemSizeEnum.BUY_SIZE.getName().equals(sizeType)) {
			value = buySize;
		} else if (ItemSizeEnum.SALE_SIZE.getName().equals(sizeType)) {
			value = saleSize;
		}

		return value;
	}

	public int calNum(int num, String sizeType) {
		int value = 0;

		if (ItemSizeEnum.SIZE.getName().equals(sizeType)) {
			value = num;
		} else if (ItemSizeEnum.BUY_SIZE.getName().equals(sizeType)) {
			if (convertNum == null) {
				value = num;
			} else {
				value = num * convertNum;
			}
		} else if (ItemSizeEnum.SALE_SIZE.getName().equals(sizeType)) {
			if (saleSizeNum == null) {
				value = num;
			} else {
				value = num * saleSizeNum;
			}
		}

		return value;
	}

	public Long calPrice(String sizeType) {
		Long value = 0l;

		if (ItemSizeEnum.SIZE.getName().equals(sizeType)) {
			value = costPrice;
		} else if (ItemSizeEnum.BUY_SIZE.getName().equals(sizeType)) {
			value = buyPrice;
		} else if (ItemSizeEnum.SALE_SIZE.getName().equals(sizeType)) {
			value = saleCostPrice;
		}

		if (value == null) {
			value = 0l;
		}
		return value;
	}

	public void calPrice() {
		// å•ä»· = æ‰¹å‘å•ä»· / æ‰¹å‘è§„æ ¼ç³»æ•°
		if (purchasePrice != null && convertNum != null) {
			if (convertNum > 0) {
				BigDecimal val = new BigDecimal(Double.valueOf(purchasePrice
						.toString()));
				val = val.divide(new BigDecimal(100));
				val = val.setScale(3, BigDecimal.ROUND_HALF_UP);

				val = val.divide(
						new BigDecimal(Double.valueOf(convertNum.toString())),
						3);
				BigDecimal result = val.setScale(3, BigDecimal.ROUND_HALF_UP);
				double value = result.doubleValue();

				String s = result.toString();

				if (!s.endsWith("0")) {
					val = new BigDecimal(value);
					val.setScale(2, BigDecimal.ROUND_HALF_UP);
					val = val.multiply(new BigDecimal(100));
					val = val.add(new BigDecimal(1));

				} else {
					val = new BigDecimal(s);
					val.setScale(2, BigDecimal.ROUND_HALF_UP);
					val = val.multiply(new BigDecimal(100));
				}

				this.setPrice(val.longValue());
			}
		}
	}

	public void calCostPrice() {
		// æˆæœ¬ä»· = é‡‡è´­ä»· / æ‰¹å‘è§„æ ¼ç³»æ•°
		if (buyPrice != null && convertNum != null) {
			if (convertNum > 0) {
				BigDecimal val = new BigDecimal(buyPrice);
				val = val.divide(new BigDecimal(convertNum), 3);
				val = val.setScale(0, BigDecimal.ROUND_HALF_UP);
				this.setCostPrice(val.longValue());
			}
		}
	}

	public void calMarketPrice() {
		BigDecimal val = new BigDecimal(marketPrice);
		val = val.setScale(0, BigDecimal.ROUND_HALF_UP);
		this.setCostPrice(val.longValue());
	}

	public void calSalePrice() {
		// é›¶å”®ä»· = æ‰¹å‘ä»·/(æ‰¹å‘è§„æ ¼ç³»æ•° / é›¶å”®è§„æ ¼ç³»æ•°)
		if (purchasePrice != null && convertNum != null && saleSizeNum != null) {
			if (saleSizeNum > 0) {
				BigDecimal val = new BigDecimal(convertNum);
				val = val.divide(new BigDecimal(saleSizeNum), 3);
				val = val.setScale(3, BigDecimal.ROUND_HALF_UP);

				BigDecimal price = new BigDecimal(purchasePrice)
						.divide(new BigDecimal(100));
				price = price.setScale(3, BigDecimal.ROUND_HALF_UP);

				val = price.divide(val, 3);
				BigDecimal result = val.setScale(3, BigDecimal.ROUND_HALF_UP);
				double value = result.doubleValue();
				String s = result.toString();

				if (!s.endsWith("0")) {

					val = new BigDecimal(value);
					val.setScale(2, BigDecimal.ROUND_HALF_UP);
					val = val.multiply(new BigDecimal(100));
					val = val.add(new BigDecimal(1));

				} else {
					val = new BigDecimal(s);
					val.setScale(2, BigDecimal.ROUND_HALF_UP);
					val = val.multiply(new BigDecimal(100));
				}

				this.setSalePrice(val.longValue());
			}
		}
	}

	public void calSaleCostPrice() {
		// é›¶å”®æˆæœ¬ä»· = é‡‡è´­ä»·/(æ‰¹å‘è§„æ ¼ç³»æ•° / é›¶å”®è§„æ ¼ç³»æ•°)
		if (buyPrice != null && convertNum != null && saleSizeNum != null) {
			if (saleSizeNum > 0) {
				BigDecimal val = new BigDecimal(convertNum);
				val = val.divide(new BigDecimal(saleSizeNum), 3);
				// val = val.setScale(3, BigDecimal.ROUND_HALF_UP);

				BigDecimal price = new BigDecimal(buyPrice);
				// price = price.setScale(3, BigDecimal.ROUND_HALF_UP);

				val = price.divide(val, 3);

				/*
				 * val=val.setScale(2, BigDecimal.ROUND_HALF_UP); double value =
				 * val.doubleValue();
				 * 
				 * val = new BigDecimal(value).multiply(new BigDecimal(100));
				 */
				val = val.setScale(0, BigDecimal.ROUND_HALF_UP);

				this.setSaleCostPrice(val.longValue());
			}
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public static void main(String[] args) {
		// Item item = new Item();
		// item.setBuyPrice(1250l);
		// item.setPurchasePrice(1550l);
		//
		// item.setConvertNum(100);
		// item.setSaleSizeNum(10);
		// item.calSaleCostPrice();
		// item.calCostPrice();
		// item.calPrice();
		// item.calSalePrice();
		// System.out.println("price:"+item.getPrice());
		// System.out.println("salePrice:"+item.getSalePrice());
		// System.out.println("saleCostPrice:"+item.getSaleCostPrice());
		// System.out.println("costPrice:"+item.getCostPrice());
		BigDecimal val = new BigDecimal(0.3);

		val = val.setScale(2, BigDecimal.ROUND_HALF_UP);

		// 20.46 --> 2046
		val = val.multiply(new BigDecimal(100));
		System.out.println(val.longValue());
	}

	public Long getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Long marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Integer getBlacktotalnum() {
		return blacktotalnum;
	}

	public void setBlacktotalnum(Integer blacktotalnum) {
		this.blacktotalnum = blacktotalnum;
	}
	public Long getCsPrice() {
        return csPrice;
    }

    public void setCsPrice(Long csPrice) {
        this.csPrice = csPrice;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getKaNum() {
		return kaNum;
	}

	public void setKaNum(Integer kaNum) {
		this.kaNum = kaNum;
	}
}