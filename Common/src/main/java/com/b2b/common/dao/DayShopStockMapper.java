package com.b2b.common.dao;

import com.b2b.common.domain.DayShopStock;
import com.b2b.common.domain.DayShopStockExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DayShopStockMapper {
    int countByExample(DayShopStockExample example);

    int deleteByExample(DayShopStockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DayShopStock record);

    int insertSelective(DayShopStock record);

    List<DayShopStock> selectByExample(DayShopStockExample example);

    DayShopStock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DayShopStock record, @Param("example") DayShopStockExample example);

    int updateByExample(@Param("record") DayShopStock record, @Param("example") DayShopStockExample example);

    int updateByPrimaryKeySelective(DayShopStock record);

    int updateByPrimaryKey(DayShopStock record);

	DayShopStock findByDate(@Param("sumDate")Date sumDate, @Param("id")Integer id);
}