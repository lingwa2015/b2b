package com.b2b.common.dao;

import com.b2b.common.domain.MonthShopStock;
import com.b2b.common.domain.MonthShopStockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonthShopStockMapper {
    int countByExample(MonthShopStockExample example);

    int deleteByExample(MonthShopStockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MonthShopStock record);

    int insertSelective(MonthShopStock record);

    List<MonthShopStock> selectByExample(MonthShopStockExample example);

    MonthShopStock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MonthShopStock record, @Param("example") MonthShopStockExample example);

    int updateByExample(@Param("record") MonthShopStock record, @Param("example") MonthShopStockExample example);

    int updateByPrimaryKeySelective(MonthShopStock record);

    int updateByPrimaryKey(MonthShopStock record);
}