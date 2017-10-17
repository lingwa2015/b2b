package com.b2b.common.dao;

import com.b2b.common.domain.CityRegion;
import com.b2b.common.domain.CityRegionExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CityRegionMapper {
    int countByExample(CityRegionExample example);

    int deleteByExample(CityRegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CityRegion record);

    int insertSelective(CityRegion record);

    List<CityRegion> selectByExample(CityRegionExample example);

    CityRegion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CityRegion record, @Param("example") CityRegionExample example);

    int updateByExample(@Param("record") CityRegion record, @Param("example") CityRegionExample example);

    int updateByPrimaryKeySelective(CityRegion record);

    int updateByPrimaryKey(CityRegion record);

	List<CityRegion> findByCityId(@Param("cityId")Integer cityId);
}