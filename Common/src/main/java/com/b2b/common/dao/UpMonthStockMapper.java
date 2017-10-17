package com.b2b.common.dao;

import com.b2b.common.domain.UpMonthStock;
import com.b2b.common.domain.UpMonthStockExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UpMonthStockMapper {
    int countByExample(UpMonthStockExample example);

    int deleteByExample(UpMonthStockExample example);

    int insert(UpMonthStock record);

    int insertSelective(UpMonthStock record);

    List<UpMonthStock> selectByExample(UpMonthStockExample example);

    int updateByExampleSelective(@Param("record") UpMonthStock record, @Param("example") UpMonthStockExample example);

    int updateByExample(@Param("record") UpMonthStock record, @Param("example") UpMonthStockExample example);

    List<UpMonthStock> findLastMouth(@Param("selectTime")String selectTime, @Param("start")Date start, @Param("end")Date end);
}