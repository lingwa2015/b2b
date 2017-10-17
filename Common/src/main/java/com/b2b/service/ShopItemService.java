package com.b2b.service;

import com.b2b.common.domain.ShopItem;

import java.util.HashMap;
import java.util.List;

public interface ShopItemService {
	
	ShopItem findItemByItemIdAndShopId(Integer itemId, Integer shopId);

	void create(ShopItem shopItem2);

	void update(ShopItem shopitem);

	List<ShopItem> findItemByShopId(Integer lastComeId);

	void updateNum(Integer id, Integer num);

	ShopItem findItemById(Integer id);

	List<ShopItem> findItemsByShopIdAndIsdown(Integer shopId, Integer isdown, String itemName);

	HashMap<String, Object> findTolalMoneyAndKind(Integer shopid);

	Long findShopItemMoney(Integer userId);

	void changeSalePrice(Integer id, double discount);

	List<ShopItem> findactualShopItemList(String userName,
			String itemName, Integer cityId, String isnew, Integer reseauId, String param);

	int findactualShopItemConsumeTotal(String userName,
										  String itemName, Integer cityId, String isnew, Integer reseauId);

	ShopItem findTolal(String userName, String itemName, Integer cityId, String isnew, Integer reseauId);

	void changeSalePriceToSourcingPrice(Integer id);

	Long findTolalByReseauId(Integer reseauId);

	Long findTolalByCityId(Integer cityId);


    void updateDefaultLayerId(Integer shopId, int id);

	List<ShopItem> findItemByShopIdAndIsdownAndLayerId(Integer shopId, int i, Integer id);

    void updateLayerIdToNull(Integer layerId);

	List<ShopItem> findOftenBuyItemByBuyerIdAndShopIdAndSign(Integer id, Integer lastComeId, int i);

	void updateLayerIdWhereIsNull(Integer shopId, Integer id);

	ShopItem findItemByShopIdAndBarCode(Integer shopId, String barcode);

	Integer findTotalNumByItemIdAndSaleId(Integer itemId, Integer id);

	Integer findTotalNumByItemId(Integer itemId);
}
