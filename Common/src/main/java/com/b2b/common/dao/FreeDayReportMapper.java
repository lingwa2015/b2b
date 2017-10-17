package com.b2b.common.dao;

import com.b2b.common.domain.FreeDayReport;
import com.b2b.common.domain.FreeDayReportExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FreeDayReportMapper {
    int countByExample(FreeDayReportExample example);

    int deleteByExample(FreeDayReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FreeDayReport record);

    int insertSelective(FreeDayReport record);

    List<FreeDayReport> selectByExample(FreeDayReportExample example);

    FreeDayReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FreeDayReport record, @Param("example") FreeDayReportExample example);

    int updateByExample(@Param("record") FreeDayReport record, @Param("example") FreeDayReportExample example);

    int updateByPrimaryKeySelective(FreeDayReport record);

    int updateByPrimaryKey(FreeDayReport record);

	FreeDayReport findByDate(Date queryDate);

	List<FreeDayReport> findAll(@Param("starttime")Date starttime, @Param("endtime")Date endtime, @Param("reseauId")int reseauId);

	FreeDayReport findByDateAndReseauId(@Param("id")Integer id, @Param("queryDate")Date queryDate);

	List<FreeDayReport> findAllDayReport();
}