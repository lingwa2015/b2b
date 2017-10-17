package com.b2b.service.impl;

import com.b2b.common.dao.ShopItemMapper;
import com.b2b.common.domain.ShopItem;
import com.b2b.service.ShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ShopItemServiceImpl implements ShopItemService {
	@Autowired
	private ShopItemMapper shopItemMapper;
	
	@Override
	public ShopItem findItemByItemIdAndShopId(Integer itemId, Integer shopId) {
		return this.shopItemMapper.findItemByItemIdAndShopId(itemId,shopId);
	}

	@Override
	public void create(ShopItem shopItem2) {
		this.shopItemMapper.insert(shopItem2);
	}

	@Override
	public void update(ShopItem shopitem) {
		this.shopItemMapper.updateByPrimaryKeySelective(shopitem);
	}

	@Override
	public List<ShopItem> findItemByShopId(Integer shopid) {
		return this.shopItemMapper.findItemByShopId(shopid);
	}
	
	/**
	 * 便利店付款成功后，减去对应的库存，如果库存小于购买的数量了，则下架
	 */
	@Override
	public void updateNum(Integer id, Integer num) {
		this.shopItemMapper.updateNum(id,num);
	}

	@Override
	public ShopItem findItemById(Integer id) {
		return this.shopItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ShopItem> findItemsByShopIdAndIsdown(Integer shopId,
			Integer isdown, String itemName) {
		return this.shopItemMapper.findItemsByShopIdAndIsdown(shopId,isdown,itemName);
	}

	@Override
	public HashMap<String, Object> findTolalMoneyAndKind(Integer shopid) {
		return this.shopItemMapper.findTolalMoneyAndKind(shopid);
	}

	@Override
	public Long findShopItemMoney(Integer userId) {
		return this.shopItemMapper.findShopItemMoney(userId);
	}

	@Override
	public void changeSalePrice(Integer id, double discount) {
		this.shopItemMapper.changeSalePrice(id,discount);
	}

	@Override
	public List<ShopItem> findactualShopItemList(String userName,
			String itemName,Integer cityId, String isnew, Integer reseauId, String param) {
		return this.shopItemMapper.findactualShopItemList(userName,itemName,cityId, isnew, reseauId, param);
	}

	@Override
	public int findactualShopItemConsumeTotal(String userName,
												 String itemName,Integer cityId, String isnew, Integer reseauId) {
		return this.shopItemMapper.findactualShopItemConsumeTotal(userName,itemName,cityId, isnew, reseauId);
	}

	@Override
	public ShopItem findTolal(String userName, String itemName,Integer cityId, String isnew, Integer reseauId) {
		return this.shopItemMapper.findTolal(userName,itemName,cityId, isnew, reseauId);
	}

	@Override
	public void changeSalePriceToSourcingPrice(Integer id) {
		this.shopItemMapper.changeSalePriceToSourcingPrice(id);
	}

	@Override
	public Long findTolalByReseauId(Integer reseauId) {
		return this.shopItemMapper.findTolalByReseauId(reseauId);
	}

	@Override
	public Long findTolalByCityId(Integer cityId) {
		return this.shopItemMapper.findTolalByCityId(cityId);
	}

	@Override
	public void updateDefaultLayerId(Integer shopId, int id) {
		this.shopItemMapper.updateDefaultLayerId(shopId,id);
	}

	@Override
	public List<ShopItem> findItemByShopIdAndIsdownAndLayerId(Integer shopId, int isdown, Integer id) {
		return this.shopItemMapper.findItemByShopIdAndIsdownAndLayerId(shopId,isdown,id);
	}

	@Override
	public void updateLayerIdToNull(Integer layerId) {
		this.shopItemMapper.updateLayerIdToNull(layerId);
	}

	@Override
	public List<ShopItem> findOftenBuyItemByBuyerIdAndShopIdAndSign(Integer buyerId, Integer shopId, int sign) {
		return this.shopItemMapper.findOftenBuyItemByBuyerIdAndShopIdAndSign(buyerId,shopId,sign);
	}

	@Override
	public void updateLayerIdWhereIsNull(Integer shopId, Integer id) {
		this.shopItemMapper.updateLayerIdWhereIsNull(shopId,id);
	}

	@Override
	public ShopItem findItemByShopIdAndBarCode(Integer shopId, String barcode) {
		return this.shopItemMapper.findItemByShopIdAndBarCode(shopId,barcode);
	}

	@Override
	public Integer findTotalNumByItemIdAndSaleId(Integer itemId, Integer id) {
		return this.shopItemMapper.findTotalNumByItemIdAndSaleId(itemId,id);
	}

	@Override
	public Integer findTotalNumByItemId(Integer itemId) {
		return this.shopItemMapper.findTotalNumByItemId(itemId);
	}

}
