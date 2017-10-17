package com.b2b.common.dao;

import com.b2b.common.domain.StockCheckItem;
import com.b2b.common.domain.StockCheckItemExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StockCheckItemMapper {
    int countByExample(StockCheckItemExample example);

    int deleteByExample(StockCheckItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StockCheckItem record);

    int insertSelective(StockCheckItem record);

    List<StockCheckItem> selectByExample(StockCheckItemExample example);

    StockCheckItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StockCheckItem record, @Param("example") StockCheckItemExample example);

    int updateByExample(@Param("record") StockCheckItem record, @Param("example") StockCheckItemExample example);

    int updateByPrimaryKeySelective(StockCheckItem record);

    int updateByPrimaryKey(StockCheckItem record);

	List<Integer> findStockCheckIdsByItemName(@Param("itemName")String itemName);
}