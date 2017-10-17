package com.b2b.common.dao;

import com.b2b.common.domain.FreeDayReport;
import com.b2b.common.domain.FreeShopDailyReport;
import com.b2b.common.domain.FreeShopDailyReportExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FreeShopDailyReportMapper {
    int countByExample(FreeShopDailyReportExample example);

    int deleteByExample(FreeShopDailyReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FreeShopDailyReport record);

    int insertSelective(FreeShopDailyReport record);

    List<FreeShopDailyReport> selectByExample(FreeShopDailyReportExample example);

    FreeShopDailyReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FreeShopDailyReport record, @Param("example") FreeShopDailyReportExample example);

    int updateByExample(@Param("record") FreeShopDailyReport record, @Param("example") FreeShopDailyReportExample example);

    int updateByPrimaryKeySelective(FreeShopDailyReport record);

    int updateByPrimaryKey(FreeShopDailyReport record);

	List<FreeShopDailyReport> findNetDailyReport(@Param("starttime")Date starttime, @Param("endtime")Date endtime,
			@Param("reseauId")int reseauId, @Param("cityId")Integer cityId);

	List<FreeShopDailyReport> findAllDailyReport(@Param("cityId")Integer cityId);

	FreeShopDailyReport findByReseauIdAndDate(@Param("reseauId")Integer reseauId,@Param("date") Date date);

	FreeShopDailyReport findByCityIdAndDate(@Param("cityId")Integer cityId, @Param("date")Date date);
}