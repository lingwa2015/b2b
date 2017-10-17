package com.b2b.common.dao;

import com.b2b.common.domain.ShopMonthReport;
import com.b2b.common.domain.ShopMonthReportExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShopMonthReportMapper {
    int countByExample(ShopMonthReportExample example);

    int deleteByExample(ShopMonthReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopMonthReport record);

    int insertSelective(ShopMonthReport record);

    List<ShopMonthReport> selectByExample(ShopMonthReportExample example);

    ShopMonthReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopMonthReport record, @Param("example") ShopMonthReportExample example);

    int updateByExample(@Param("record") ShopMonthReport record, @Param("example") ShopMonthReportExample example);

    int updateByPrimaryKeySelective(ShopMonthReport record);

    int updateByPrimaryKey(ShopMonthReport record);

	ShopMonthReport findByUserIdAndSumDate(@Param("id")Integer id, @Param("month")Date month);

	List<ShopMonthReport> findByCondition(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("username")String username, @Param("param")String param, @Param("region")String region, @Param("reseauId")int reseauId, @Param("cityId")Integer cityId);

	List<ShopMonthReport> findNetMonthReport(@Param("reseauId")int reseauId, @Param("querydate")String querydate, @Param("cityId")Integer cityId);

	List<ShopMonthReport> findAllMonthReport(@Param("cityId")Integer cityId);

	ShopMonthReport findByReseauIdAndDate(@Param("reseauId")Integer reseauId, @Param("date")Date date);

	ShopMonthReport findByShopIdAndDate(@Param("shopId")Integer shopId, @Param("date")Date date);

	void deleteByDateAndCityId(@Param("date")Date date, @Param("cityId")Integer cityId);

	ShopMonthReport findByCityIdAndDate(@Param("cityId")Integer cityId, @Param("date")Date date);

    int updateIsnew(@Param("date")Date date);
}