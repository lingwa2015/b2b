package com.b2b.common.dao;

import com.b2b.common.domain.StockMoneyMonitor;
import com.b2b.common.domain.StockMoneyMonitorExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StockMoneyMonitorMapper {
    int countByExample(StockMoneyMonitorExample example);

    int deleteByExample(StockMoneyMonitorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StockMoneyMonitor record);

    int insertSelective(StockMoneyMonitor record);

    List<StockMoneyMonitor> selectByExample(StockMoneyMonitorExample example);

    StockMoneyMonitor selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StockMoneyMonitor record, @Param("example") StockMoneyMonitorExample example);

    int updateByExample(@Param("record") StockMoneyMonitor record, @Param("example") StockMoneyMonitorExample example);

    int updateByPrimaryKeySelective(StockMoneyMonitor record);

    int updateByPrimaryKey(StockMoneyMonitor record);

	int count(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("cityId")Integer cityId);

	List<StockMoneyMonitor> findPageList(@Param("start")int currentPage, @Param("pageSize")int pageSize,
			@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("cityId")Integer cityId);
}