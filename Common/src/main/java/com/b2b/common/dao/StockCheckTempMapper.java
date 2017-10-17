package com.b2b.common.dao;

import com.b2b.common.domain.StockCheckTemp;
import com.b2b.common.domain.StockCheckTempExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StockCheckTempMapper {
    int countByExample(StockCheckTempExample example);

    int deleteByExample(StockCheckTempExample example);

    int deleteByPrimaryKey(Integer itemId);

    int insert(StockCheckTemp record);

    int insertSelective(StockCheckTemp record);

    List<StockCheckTemp> selectByExample(StockCheckTempExample example);

    StockCheckTemp selectByPrimaryKey(Integer itemId);

    int updateByExampleSelective(@Param("record") StockCheckTemp record, @Param("example") StockCheckTempExample example);

    int updateByExample(@Param("record") StockCheckTemp record, @Param("example") StockCheckTempExample example);

    int updateByPrimaryKeySelective(StockCheckTemp record);

    int updateByPrimaryKey(StockCheckTemp record);

	void deleteAll(Object[] ids);
}