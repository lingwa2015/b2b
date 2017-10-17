package com.b2b.common.dao;

import com.b2b.common.domain.FreeMonthReport;
import com.b2b.common.domain.FreeShopMonthReport;
import com.b2b.common.domain.FreeShopMonthReportExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface FreeShopMonthReportMapper {
    int countByExample(FreeShopMonthReportExample example);

    int deleteByExample(FreeShopMonthReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FreeShopMonthReport record);

    int insertSelective(FreeShopMonthReport record);

    List<FreeShopMonthReport> selectByExample(FreeShopMonthReportExample example);

    FreeShopMonthReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FreeShopMonthReport record, @Param("example") FreeShopMonthReportExample example);

    int updateByExample(@Param("record") FreeShopMonthReport record, @Param("example") FreeShopMonthReportExample example);

    int updateByPrimaryKeySelective(FreeShopMonthReport record);

    int updateByPrimaryKey(FreeShopMonthReport record);

	FreeShopMonthReport findByUserIdAndSumDate(@Param("id")Integer id, @Param("date")Date date4);

	List<FreeShopMonthReport> findAll(@Param("username")String username, @Param("querytime")Date starttime, @Param("param")String param, @Param("flag")String flag, @Param("region")String region, @Param("reseauId")int reseauId, @Param("cityId")Integer cityId);

	List<FreeShopMonthReport> findNetShopmonthReport(@Param("querydate")String querydate, @Param("reseauId")int reseauId, @Param("cityId")Integer cityId);

	FreeShopMonthReport findByReseauIdAndDate(@Param("reseauId")Integer reseauId, @Param("date")Date date);

	List<FreeShopMonthReport> findShopInfoByReseauIdAndDate(@Param("reseauId")Integer reseauId,
			@Param("date")Date date);

	List<FreeMonthReport> findAllMonthReport(@Param("cityId")Integer cityId);

	void deleteByDateAndCityId(@Param("date")Date date, @Param("cityId")Integer cityId);

	FreeShopMonthReport findBycityIdAndDate(@Param("cityId")Integer cityId, @Param("date")Date date);
}