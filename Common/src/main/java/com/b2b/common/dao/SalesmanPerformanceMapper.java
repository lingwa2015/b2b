package com.b2b.common.dao;

import com.b2b.common.domain.SalesmanPerformance;
import com.b2b.common.domain.SalesmanPerformanceExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SalesmanPerformanceMapper {
    int countByExample(SalesmanPerformanceExample example);

    int deleteByExample(SalesmanPerformanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SalesmanPerformance record);

    int insertSelective(SalesmanPerformance record);

    List<SalesmanPerformance> selectByExample(SalesmanPerformanceExample example);

    SalesmanPerformance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SalesmanPerformance record, @Param("example") SalesmanPerformanceExample example);

    int updateByExample(@Param("record") SalesmanPerformance record, @Param("example") SalesmanPerformanceExample example);

    int updateByPrimaryKeySelective(SalesmanPerformance record);

    int updateByPrimaryKey(SalesmanPerformance record);

	int countGroupByInterfaceman(@Param("startTime")Date startTime, @Param("endTime")Date endTime);

	List<SalesmanPerformance> findPageByDate(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("start")int start, @Param("pageSize")int pageSize);
}