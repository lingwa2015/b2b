package com.b2b.common.dao;

import com.b2b.common.domain.StockCheck;
import com.b2b.common.domain.StockCheckExample;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StockCheckMapper {
    int countByExample(StockCheckExample example);

    int deleteByExample(StockCheckExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StockCheck record);

    int insertSelective(StockCheck record);

    List<StockCheck> selectByExample(StockCheckExample example);

    StockCheck selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StockCheck record, @Param("example") StockCheckExample example);

    int updateByExample(@Param("record") StockCheck record, @Param("example") StockCheckExample example);

    int updateByPrimaryKeySelective(StockCheck record);

    int updateByPrimaryKey(StockCheck record);

	List<HashMap<String, Object>> findStockCheckAboveZero(@Param("start")Date start,@Param("end") Date end, @Param("cityId")Integer cityId);

	List<HashMap<String, Object>> findStockCheckBelowZero(@Param("start")Date start,@Param("end") Date end, @Param("cityId")Integer cityId);

	Long findTotalByCondition(@Param("userName")String userName, @Param("type")String type, @Param("startTime")Date startTime,
			@Param("endTime")Date endTime, @Param("cityId")Integer cityId, @Param("itemName")String itemName);
}