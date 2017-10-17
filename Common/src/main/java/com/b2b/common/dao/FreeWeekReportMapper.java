package com.b2b.common.dao;

import com.b2b.common.domain.FreeWeekReport;
import com.b2b.common.domain.FreeWeekReportExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FreeWeekReportMapper {
    int countByExample(FreeWeekReportExample example);

    int deleteByExample(FreeWeekReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FreeWeekReport record);

    int insertSelective(FreeWeekReport record);

    List<FreeWeekReport> selectByExample(FreeWeekReportExample example);

    FreeWeekReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FreeWeekReport record, @Param("example") FreeWeekReportExample example);

    int updateByExample(@Param("record") FreeWeekReport record, @Param("example") FreeWeekReportExample example);

    int updateByPrimaryKeySelective(FreeWeekReport record);

    int updateByPrimaryKey(FreeWeekReport record);

	FreeWeekReport findBySumDate(Date querydate);

	List<FreeWeekReport> findAll();
}