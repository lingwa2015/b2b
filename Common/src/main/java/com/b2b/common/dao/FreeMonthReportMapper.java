package com.b2b.common.dao;

import com.b2b.common.domain.FreeMonthReport;
import com.b2b.common.domain.FreeMonthReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FreeMonthReportMapper {
    int countByExample(FreeMonthReportExample example);

    int deleteByExample(FreeMonthReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FreeMonthReport record);

    int insertSelective(FreeMonthReport record);

    List<FreeMonthReport> selectByExample(FreeMonthReportExample example);

    FreeMonthReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FreeMonthReport record, @Param("example") FreeMonthReportExample example);

    int updateByExample(@Param("record") FreeMonthReport record, @Param("example") FreeMonthReportExample example);

    int updateByPrimaryKeySelective(FreeMonthReport record);

    int updateByPrimaryKey(FreeMonthReport record);

	List<FreeMonthReport> findAll();

}