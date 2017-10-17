package com.b2b.common.dao;

import com.b2b.common.domain.TranConsume;
import com.b2b.common.domain.TranConsumeExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TranConsumeMapper {
    int countByExample(TranConsumeExample example);

    int deleteByExample(TranConsumeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TranConsume record);

    int insertSelective(TranConsume record);

    List<TranConsume> selectByExample(TranConsumeExample example);

    TranConsume selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TranConsume record, @Param("example") TranConsumeExample example);

    int updateByExample(@Param("record") TranConsume record, @Param("example") TranConsumeExample example);

    int updateByPrimaryKeySelective(TranConsume record);

    int updateByPrimaryKey(TranConsume record);

	Long findCurrentMonthConsumeMoney(Integer customerUserId);

	Date findDate();

	Long findLossMoney(Integer shop_id);

	TranConsume findMoreLossMoney(Integer shopId);

	TranConsume findMonthStockByUserIdAndDate(@Param("id")Integer id, @Param("date")Date date);

	TranConsume findTotalMonthStockByDate(@Param("date")Date date);

	void deleteByDateAndCityId(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("cityId")Integer cityId);

	Long findCurrentMonthRedPriceByShopId(Integer shopId);
}