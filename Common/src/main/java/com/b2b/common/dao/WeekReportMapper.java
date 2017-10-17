package com.b2b.common.dao;

import com.b2b.common.domain.WeekReport;
import com.b2b.common.domain.WeekReportExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WeekReportMapper {
    int countByExample(WeekReportExample example);

    int deleteByExample(WeekReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeekReport record);

    int insertSelective(WeekReport record);

    List<WeekReport> selectByExample(WeekReportExample example);

    WeekReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeekReport record, @Param("example") WeekReportExample example);

    int updateByExample(@Param("record") WeekReport record, @Param("example") WeekReportExample example);

    int updateByPrimaryKeySelective(WeekReport record);

    int updateByPrimaryKey(WeekReport record);

	WeekReport findByCondition(Date queryDate);
}