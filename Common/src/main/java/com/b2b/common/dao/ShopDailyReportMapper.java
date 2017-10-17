package com.b2b.common.dao;

import com.b2b.common.domain.ShopDailyReport;
import com.b2b.common.domain.ShopDailyReportExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShopDailyReportMapper {
    int countByExample(ShopDailyReportExample example);

    int deleteByExample(ShopDailyReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopDailyReport record);

    int insertSelective(ShopDailyReport record);

    List<ShopDailyReport> selectByExample(ShopDailyReportExample example);

    ShopDailyReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopDailyReport record, @Param("example") ShopDailyReportExample example);

    int updateByExample(@Param("record") ShopDailyReport record, @Param("example") ShopDailyReportExample example);

    int updateByPrimaryKeySelective(ShopDailyReport record);

    int updateByPrimaryKey(ShopDailyReport record);

	ShopDailyReport findByShopIdAndSumdate(@Param("id")Integer id, @Param("queryDate")Date queryDate);

	List<ShopDailyReport> findByCondition(@Param("userName")String userName, @Param("starttime")Date starttime, @Param("endtime")Date endtime, @Param("param")String param, @Param("reseauId")int reseauId,  @Param("cityId")Integer cityId);

	List<ShopDailyReport> findNetDailyReport(@Param("starttime")Date starttime, @Param("endtime")Date endtime, @Param("reseauId")int reseauId, @Param("cityId")Integer cityId);

	List<ShopDailyReport> findAllDailyReport(@Param("cityId")Integer cityId);

	ShopDailyReport findByReseauIdAndDate(@Param("reseauId")Integer reseauId, @Param("date")Date date);

	ShopDailyReport findByShopIdAndDate(@Param("shopId")Integer shopId, @Param("date")Date date);

	ShopDailyReport findByCityIdAndDate(@Param("cityId")Integer cityId, @Param("date")Date date);
}