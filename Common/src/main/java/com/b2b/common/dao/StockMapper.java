package com.b2b.common.dao;

import com.b2b.common.domain.Stock;
import com.b2b.common.domain.StockExample;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface StockMapper {
    int countByExample(StockExample example);

    int deleteByExample(StockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Stock record);

    int insertSelective(Stock record);

    List<Stock> selectByExample(StockExample example);

    Stock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByExample(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

	List<HashMap<String, Object>> getStockInfoWithModifyNum(@Param("itemName")String itemName,
			@Param("limit")int limit, @Param("start")int start, @Param("cityId")Integer cityId,
				@Param("onecate")int onecate, @Param("twocate")int twocate, @Param("isdown")String isdown );

	List<Stock> findByItemIngoreIsdown(int itemId);
	
	//临时
	List<HashMap<String, Object>> temp();

	List<Stock> findByItemId(@Param("itemIds")ArrayList<Integer> itemIds);

	List<Stock> findIngoreIsdown(@Param("alertFlag")boolean alertFlag, @Param("itemName")String itemName);

	int count(@Param("itemName")String itemName, @Param("alertFlag")boolean alertFlag);

	List<Stock> findByItemIdIngoreIsdown(Integer itemId);

	Stock findByItemid(int itemId);

	List<Stock> findByCondition(@Param("itemName")String itemName, @Param("alertFlag")boolean alertFlag,
			@Param("onecate")int onecate, @Param("twocate")int twocate, @Param("isdown")String isdown, @Param("unsalable")int unsalable, @Param("cityId")Integer cityId);

	Long findStockTotalMoney(@Param("itemName")String itemName, @Param("alertFlag")boolean alertFlag,
			@Param("onecate")int onecate, @Param("twocate")int twocate, @Param("isdown")String isdown, @Param("unsalable")int unsalable, @Param("cityId")Integer cityId);

	List<HashMap<String, Object>> findAllStock(@Param("itemName")String itemName,
			@Param("alertFlag")boolean alertFlag, @Param("onecate")int onecate, @Param("twocate")int twocate, @Param("isdown")String isdown,  @Param("unsalable")int unsalable, @Param("cityId")Integer cityId);

	int countStockInfoWithModifyNum(@Param("itemName")String itemName, @Param("cityId")Integer cityId, @Param("onecate")int onecate, @Param("twocate")int twocate, @Param("isdown")String isdown);

	Long getStockTotalMoneyByCityId(@Param("cityId")Integer cityId);

	int updateStockNumReduce(@Param("itemId")int itemId, @Param("num")int num);

	void updateStockNumAdd(@Param("itemId")int itemId, @Param("num")int num);

}