package com.b2b.common.dao;

import com.b2b.common.domain.NewCustomerActivity;
import com.b2b.common.domain.NewCustomerActivityExample;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewCustomerActivityMapper {
    int countByExample(NewCustomerActivityExample example);

    int deleteByExample(NewCustomerActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NewCustomerActivity record);

    int insertSelective(NewCustomerActivity record);

    List<NewCustomerActivity> selectByExample(NewCustomerActivityExample example);

    NewCustomerActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NewCustomerActivity record, @Param("example") NewCustomerActivityExample example);

    int updateByExample(@Param("record") NewCustomerActivity record, @Param("example") NewCustomerActivityExample example);

    int updateByPrimaryKeySelective(NewCustomerActivity record);

    int updateByPrimaryKey(NewCustomerActivity record);

	List<NewCustomerActivity> findByCondition(Integer cityId);

	List<NewCustomerActivity> findByDateAndCityId(@Param("date")Date date, @Param("cityId")Integer cityId);

	List<NewCustomerActivity> findByConditionAndCityId(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("userName")String userName,
			@Param("reseauId")Integer reseauId, @Param("cityId")Integer cityId);

	Long findTotalFeeByConditionAndCityId(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("userName")String userName,
			@Param("reseauId")Integer reseauId, @Param("cityId")Integer cityId);

	List<NewCustomerActivity> findByStartAndEndTimeAndCityId(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("cityId")Integer cityId);


}