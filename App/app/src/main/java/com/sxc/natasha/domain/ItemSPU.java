package com.sxc.natasha.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ItemSPU implements Serializable{
	private static final long serialVersionUID = -1334875334280666466L;

	private Integer id;

    private Integer itemId;

    private String itemName;

    private Integer itemCatId;

    private String itemCatName;

    private Integer itemSupplierId;

    private String itemColor;

    private String itemShape;

    private String itemDiameter;

    private String itemLength;

    private String itemWeight;

    private String itemArea;

    private String itemAppearance;

    private String itemSpec;

    private String itemLustre;

    private String itemPackageMaterical;

    private String itemPackageSize;

    private String itemPackageWeight;

    private String itemSellerArea;

    private String itemPackageNum;

    private String itemLevel;

    private String itemStorage;

    private String itemTransport;

    private String itemExpiration;

    private Date itemPickTime;

    private Date itemSellTime;

    private Integer itemSupplyNum;

    private Long itemPrice;

    private Integer itemLimitPer;

    private Integer itemSellerId;

    private Integer itemStatus;

    private Integer itemSkuId;

    private Integer state;

    private Date createTime;

    private Date lastUpdateTime;
    
    private String itemGrade;

    private List<ItemSKU> skuList;
    private String province;
    private String city;
    private String area;
    private ItemSKU sku;
    private int stockNum;
    private String skuName;
    private Float actualPrice;
    private int saleStock;

    public List<ItemSKU> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<ItemSKU> skuList) {
        this.skuList = skuList;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public ItemSKU getSku() {
        return sku;
    }

    public void setSku(ItemSKU sku) {
        this.sku = sku;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Float getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Float actualPrice) {
        this.actualPrice = actualPrice;
    }

    public int getSaleStock() {
        return saleStock;
    }

    public void setSaleStock(int saleStock) {
        this.saleStock = saleStock;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Integer itemCatId) {
        this.itemCatId = itemCatId;
    }

    public String getItemCatName() {
        return itemCatName;
    }

    public void setItemCatName(String itemCatName) {
        this.itemCatName = itemCatName == null ? null : itemCatName.trim();
    }

    public Integer getItemSupplierId() {
        return itemSupplierId;
    }

    public void setItemSupplierId(Integer itemSupplierId) {
        this.itemSupplierId = itemSupplierId;
    }

    public String getItemColor() {
        return itemColor;
    }

    public void setItemColor(String itemColor) {
        this.itemColor = itemColor == null ? null : itemColor.trim();
    }

    public String getItemShape() {
        return itemShape;
    }

    public void setItemShape(String itemShape) {
        this.itemShape = itemShape == null ? null : itemShape.trim();
    }

    public String getItemDiameter() {
        return itemDiameter;
    }

    public void setItemDiameter(String itemDiameter) {
        this.itemDiameter = itemDiameter == null ? null : itemDiameter.trim();
    }

    public String getItemLength() {
        return itemLength;
    }

    public void setItemLength(String itemLength) {
        this.itemLength = itemLength == null ? null : itemLength.trim();
    }

    public String getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(String itemWeight) {
        this.itemWeight = itemWeight == null ? null : itemWeight.trim();
    }

    public String getItemArea() {
        return itemArea;
    }

    public void setItemArea(String itemArea) {
        this.itemArea = itemArea == null ? null : itemArea.trim();
    }

    public String getItemAppearance() {
        return itemAppearance;
    }

    public void setItemAppearance(String itemAppearance) {
        this.itemAppearance = itemAppearance == null ? null : itemAppearance.trim();
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec == null ? null : itemSpec.trim();
    }

    public String getItemLustre() {
        return itemLustre;
    }

    public void setItemLustre(String itemLustre) {
        this.itemLustre = itemLustre == null ? null : itemLustre.trim();
    }

    public String getItemPackageMaterical() {
        return itemPackageMaterical;
    }

    public void setItemPackageMaterical(String itemPackageMaterical) {
        this.itemPackageMaterical = itemPackageMaterical == null ? null : itemPackageMaterical.trim();
    }

    public String getItemPackageSize() {
        return itemPackageSize;
    }

    public void setItemPackageSize(String itemPackageSize) {
        this.itemPackageSize = itemPackageSize == null ? null : itemPackageSize.trim();
    }

    public String getItemPackageWeight() {
        return itemPackageWeight;
    }

    public void setItemPackageWeight(String itemPackageWeight) {
        this.itemPackageWeight = itemPackageWeight == null ? null : itemPackageWeight.trim();
    }

    public String getItemSellerArea() {
        return itemSellerArea;
    }

    public void setItemSellerArea(String itemSellerArea) {
        this.itemSellerArea = itemSellerArea == null ? null : itemSellerArea.trim();
    }

    public String getItemPackageNum() {
        return itemPackageNum;
    }

    public void setItemPackageNum(String itemPackageNum) {
        this.itemPackageNum = itemPackageNum == null ? null : itemPackageNum.trim();
    }

    public String getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(String itemLevel) {
        this.itemLevel = itemLevel == null ? null : itemLevel.trim();
    }

    public String getItemStorage() {
        return itemStorage;
    }

    public void setItemStorage(String itemStorage) {
        this.itemStorage = itemStorage == null ? null : itemStorage.trim();
    }

    public String getItemTransport() {
        return itemTransport;
    }

    public void setItemTransport(String itemTransport) {
        this.itemTransport = itemTransport == null ? null : itemTransport.trim();
    }

    public String getItemExpiration() {
        return itemExpiration;
    }

    public void setItemExpiration(String itemExpiration) {
        this.itemExpiration = itemExpiration == null ? null : itemExpiration.trim();
    }

    public Date getItemPickTime() {
        return itemPickTime;
    }

    public void setItemPickTime(Date itemPickTime) {
        this.itemPickTime = itemPickTime;
    }

    public Date getItemSellTime() {
        return itemSellTime;
    }

    public void setItemSellTime(Date itemSellTime) {
        this.itemSellTime = itemSellTime;
    }

    public Integer getItemSupplyNum() {
        return itemSupplyNum;
    }

    public void setItemSupplyNum(Integer itemSupplyNum) {
        this.itemSupplyNum = itemSupplyNum;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemLimitPer() {
        return itemLimitPer;
    }

    public void setItemLimitPer(Integer itemLimitPer) {
        this.itemLimitPer = itemLimitPer;
    }

    public Integer getItemSellerId() {
        return itemSellerId;
    }

    public void setItemSellerId(Integer itemSellerId) {
        this.itemSellerId = itemSellerId;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Integer getItemSkuId() {
        return itemSkuId;
    }

    public void setItemSkuId(Integer itemSkuId) {
        this.itemSkuId = itemSkuId;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

	public String getItemGrade() {
		return itemGrade;
	}

	public void setItemGrade(String itemGrade) {
		this.itemGrade = itemGrade;
	}
    
    
}