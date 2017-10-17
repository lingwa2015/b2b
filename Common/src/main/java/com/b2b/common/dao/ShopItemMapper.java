package com.b2b.common.dao;

import com.b2b.common.domain.ShopItem;
import com.b2b.common.domain.ShopItemExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Lazy;

import java.util.HashMap;
import java.util.List;

public interface ShopItemMapper {
	int countByExample(ShopItemExample example);

	int deleteByExample(ShopItemExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(ShopItem record);

	int insertSelective(ShopItem record);

	List<ShopItem> selectByExample(ShopItemExample example);

	ShopItem selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") ShopItem record, @Param("example") ShopItemExample example);

	int updateByExample(@Param("record") ShopItem record, @Param("example") ShopItemExample example);

	int updateByPrimaryKeySelective(ShopItem record);

	int updateByPrimaryKey(ShopItem record);

	ShopItem findItemByItemIdAndShopId(@Param("itemId")Integer itemId,@Param("shopId")Integer shopId);

	List<ShopItem> findItemByShopId(Integer shopid);

	void updateNum(@Param("id")Integer id, @Param("num")Integer num);

	List<ShopItem> findItemsByShopIdAndIsdown(@Param("shopId")Integer shopId, @Param("isdown")Integer isdown,@Param("name")String itemName);

	HashMap<String, Object> findTolalMoneyAndKind(@Param("shopid")Integer shopid);

	Long findShopItemMoney(Integer userId);

	void changeSalePrice(@Param("id")Integer id, @Param("discount")double discount);

	List<ShopItem> findactualShopItemList(@Param("userName")String userName,
										  @Param("itemName")String itemName, @Param("cityId")Integer cityId, @Param("isnew")String isnew,
										  @Param("reseauId")Integer reseauId, @Param("param")String param);

	int findactualShopItemConsumeTotal(@Param("userName")String userName,
									 @Param("itemName")String itemName, @Param("cityId")Integer cityId, @Param("isnew")String isnew,
									 @Param("reseauId")Integer reseauId);

	ShopItem findTolal(@Param("userName")String userName,
					   @Param("itemName")String itemName, @Param("cityId")Integer cityId, @Param("isnew")String isnew, @Param("reseauId")Integer reseauId);

	void changeSalePriceToSourcingPrice(Integer id);

	Long findTolalByReseauId(@Param("reseauId")Integer reseauId);

	Long findTolalByCityId(Integer cityId);

    void updateDefaultLayerId(@Param("shopId") Integer shopId, @Param("id") int id);

	List<ShopItem> findItemByShopIdAndIsdownAndLayerId(@Param("shopId") Integer shopId, @Param("isdown") int isdown, @Param("layerId") Integer id);

    void updateLayerIdToNull(Integer layerId);

    List<ShopItem> findOftenBuyItemByBuyerIdAndShopIdAndSign(@Param("buyerId")Integer buyerId, @Param("shopId")Integer shopId, @Param("sign")int sign);

	void updateLayerIdWhereIsNull(@Param("shopId")Integer shopId, @Param("id")Integer id);

	ShopItem findItemByShopIdAndBarCode(@Param("shopId")Integer shopId, @Param("barcode")String barcode);

	Integer findTotalNumByItemIdAndSaleId(@Param("itemId")Integer itemId, @Param("id")Integer id);

	Integer findTotalNumByItemId(Integer itemId);
}