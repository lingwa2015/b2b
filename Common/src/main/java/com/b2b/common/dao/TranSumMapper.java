package com.b2b.common.dao;

import com.b2b.common.domain.TranSum;
import com.b2b.common.domain.TranSumExample;
import com.b2b.dto.TranSumDto;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TranSumMapper {
    int countByExample(TranSumExample example);

    int deleteByExample(TranSumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TranSum record);

    int insertSelective(TranSum record);

    List<TranSum> selectByExample(TranSumExample example);

    TranSum selectByPrimaryKey(Integer id);
    
    TranSum selectLast(TranSumExample example);

    int updateByExampleSelective(@Param("record") TranSum record, @Param("example") TranSumExample example);

    int updateByExample(@Param("record") TranSum record, @Param("example") TranSumExample example);

    int updateByPrimaryKeySelective(TranSum record);

    int updateByPrimaryKey(TranSum record);

	Long findCurrentMonthSourcingMoney(Integer customerUserId);

	void deleteByDateAndCityId(@Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate, @Param("cityId")Integer cityId);

	List<TranSumDto> findByCondition(@Param("userName")String userName, @Param("startTime")Date startTime,
			@Param("endTime")Date endTime, @Param("cityId")Integer cityId);
}