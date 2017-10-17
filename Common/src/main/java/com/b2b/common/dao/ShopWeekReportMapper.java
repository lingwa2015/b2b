package com.b2b.common.dao;

import com.b2b.common.domain.ShopWeekReport;
import com.b2b.common.domain.ShopWeekReportExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShopWeekReportMapper {
    int countByExample(ShopWeekReportExample example);

    int deleteByExample(ShopWeekReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopWeekReport record);

    int insertSelective(ShopWeekReport record);

    List<ShopWeekReport> selectByExample(ShopWeekReportExample example);

    ShopWeekReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopWeekReport record, @Param("example") ShopWeekReportExample example);

    int updateByExample(@Param("record") ShopWeekReport record, @Param("example") ShopWeekReportExample example);

    int updateByPrimaryKeySelective(ShopWeekReport record);

    int updateByPrimaryKey(ShopWeekReport record);

	ShopWeekReport queryLastWeek(@Param("id")Integer id, @Param("querydate")Date querydate);

	List<ShopWeekReport> findByCondition(@Param("userName")String userName, @Param("starttime")Date starttime,
			@Param("param")String param, @Param("reseauId")int reseauId);
}