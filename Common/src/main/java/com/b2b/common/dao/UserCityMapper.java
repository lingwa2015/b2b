package com.b2b.common.dao;

import com.b2b.common.domain.City;
import com.b2b.common.domain.UserCity;
import com.b2b.common.domain.UserCityExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserCityMapper {
    int countByExample(UserCityExample example);

    int deleteByExample(UserCityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCity record);

    int insertSelective(UserCity record);

    List<UserCity> selectByExample(UserCityExample example);

    UserCity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCity record, @Param("example") UserCityExample example);

    int updateByExample(@Param("record") UserCity record, @Param("example") UserCityExample example);

    int updateByPrimaryKeySelective(UserCity record);

    int updateByPrimaryKey(UserCity record);

	List<City> findCityByUserId(@Param("id")Integer id);
}