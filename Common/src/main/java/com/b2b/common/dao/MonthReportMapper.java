package com.b2b.common.dao;

import com.b2b.common.domain.MonthReport;
import com.b2b.common.domain.MonthReportExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MonthReportMapper {
    int countByExample(MonthReportExample example);

    int deleteByExample(MonthReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MonthReport record);

    int insertSelective(MonthReport record);

    List<MonthReport> selectByExample(MonthReportExample example);

    MonthReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MonthReport record, @Param("example") MonthReportExample example);

    int updateByExample(@Param("record") MonthReport record, @Param("example") MonthReportExample example);

    int updateByPrimaryKeySelective(MonthReport record);

    int updateByPrimaryKey(MonthReport record);

	MonthReport findByDate(Date month);

	List<MonthReport> findList();

}