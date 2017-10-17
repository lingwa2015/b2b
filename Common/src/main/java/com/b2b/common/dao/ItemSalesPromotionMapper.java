package com.b2b.common.dao;

import com.b2b.common.domain.ItemSalesPromotion;
import com.b2b.common.domain.ItemSalesPromotionExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemSalesPromotionMapper {
    int countByExample(ItemSalesPromotionExample example);

    int deleteByExample(ItemSalesPromotionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemSalesPromotion record);

    int insertSelective(ItemSalesPromotion record);

    List<ItemSalesPromotion> selectByExample(ItemSalesPromotionExample example);

    ItemSalesPromotion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemSalesPromotion record, @Param("example") ItemSalesPromotionExample example);

    int updateByExample(@Param("record") ItemSalesPromotion record, @Param("example") ItemSalesPromotionExample example);

    int updateByPrimaryKeySelective(ItemSalesPromotion record);

    int updateByPrimaryKey(ItemSalesPromotion record);

	List<ItemSalesPromotion> findByItemIdAndDateAndCityId(@Param("itemId")Integer itemId, @Param("date")Date date, @Param("cityId")Integer cityId);

	List<ItemSalesPromotion> findByItemIdAndStartAndEndTimeAndCityId(@Param("itemId")Integer itemId, @Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("cityId")Integer cityId);

	List<ItemSalesPromotion> findAllByConditions(@Param("itemname")String itemname, @Param("param")String param,@Param("cityId")Integer cityId);

	List<ItemSalesPromotion> findStratedByCityId(Integer cityId);

	List<ItemSalesPromotion> findByItemSaleIdAndShopId(@Param("itemSaleId")Integer itemSaleId , @Param("id")Integer id);

	List<ItemSalesPromotion> findStratedByCityIdAndShopId(@Param("cityId")Integer cityId, @Param("shopId")Integer shopId);

	List<ItemSalesPromotion> findPreferentialByItemSaleId(Integer id);

	ItemSalesPromotion findTotalByItemSaleId(Integer id);
}