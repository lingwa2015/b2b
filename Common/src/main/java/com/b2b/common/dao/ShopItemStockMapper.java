package com.b2b.common.dao;

import com.b2b.common.domain.ShopItemStock;
import com.b2b.common.domain.ShopItemStockExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShopItemStockMapper {
	int countByExample(ShopItemStockExample example);

	int deleteByExample(ShopItemStockExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(ShopItemStock record);

	int insertSelective(ShopItemStock record);

	List<ShopItemStock> selectByExample(ShopItemStockExample example);

	ShopItemStock selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") ShopItemStock record,
			@Param("example") ShopItemStockExample example);

	int updateByExample(@Param("record") ShopItemStock record,
			@Param("example") ShopItemStockExample example);

	int updateByPrimaryKeySelective(ShopItemStock record);

	int updateByPrimaryKey(ShopItemStock record);

	List<ShopItemStock> findlossDetail(@Param("shop_id") Integer shop_id,
			@Param("querydate") Date querydate,
			@Param("startdate") Date startdate, @Param("enddate") Date enddate);

	Integer findByItemIdAndSumdateAndSaleId(@Param("itemId")Integer itemId, @Param("sumdate")Date sumdate, @Param("id")Integer id);

	Integer findByItemIdAndSumdate(@Param("itemId")Integer itemId, @Param("sumdate")Date sumdate);
}