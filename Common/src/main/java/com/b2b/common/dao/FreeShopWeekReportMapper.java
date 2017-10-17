package com.b2b.common.dao;

import com.b2b.common.domain.FreeShopWeekReport;
import com.b2b.common.domain.FreeShopWeekReportExample;
import com.b2b.common.domain.ShopWeekReport;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FreeShopWeekReportMapper {
    int countByExample(FreeShopWeekReportExample example);

    int deleteByExample(FreeShopWeekReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FreeShopWeekReport record);

    int insertSelective(FreeShopWeekReport record);

    List<FreeShopWeekReport> selectByExample(FreeShopWeekReportExample example);

    FreeShopWeekReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FreeShopWeekReport record, @Param("example") FreeShopWeekReportExample example);

    int updateByExample(@Param("record") FreeShopWeekReport record, @Param("example") FreeShopWeekReportExample example);

    int updateByPrimaryKeySelective(FreeShopWeekReport record);

    int updateByPrimaryKey(FreeShopWeekReport record);

	FreeShopWeekReport queryLastWeek(@Param("id")Integer id, @Param("querydate")Date querydate);

	List<FreeShopWeekReport> findByCondition(@Param("userName")String username, @Param("starttime")Date starttime,
			@Param("param")String param, @Param("flag")String flag, @Param("reseauId")int reseauId);
}