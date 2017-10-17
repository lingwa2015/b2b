package com.b2b.common.dao;

import com.b2b.common.domain.ShopItemMonthReport;
import com.b2b.common.domain.ShopItemMonthReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ShopItemMonthReportMapper {
    long countByExample(ShopItemMonthReportExample example);

    int deleteByExample(ShopItemMonthReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopItemMonthReport record);

    int insertSelective(ShopItemMonthReport record);

    List<ShopItemMonthReport> selectByExample(ShopItemMonthReportExample example);

    int deleteByDate(@Param("date4")Date date4);

    ShopItemMonthReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopItemMonthReport record, @Param("example") ShopItemMonthReportExample example);

    int updateByExample(@Param("record") ShopItemMonthReport record, @Param("example") ShopItemMonthReportExample example);

    int updateByPrimaryKeySelective(ShopItemMonthReport record);

    int updateByPrimaryKey(ShopItemMonthReport record);

    List<ShopItemMonthReport> findShopItemsByDate(@Param("date")Date date, @Param("cityId")Integer cityId);

    Integer selectShopOrderItemSaleCount(@Param("shopId") Integer shopId, @Param("itemId")Integer itemId,@Param("date")Date date);

    Integer selectShopOrderItemRefundNum(@Param("shopId") Integer shopId, @Param("itemId")Integer itemId,@Param("date")Date date);

    Integer selectShopOrderItemStockNum(@Param("shopId") Integer shopId, @Param("itemId")Integer itemId,@Param("date")Date date);

    void deleteByDateAndCityId(@Param("date")Date date, @Param("cityId")Integer cityId);

    List<ShopItemMonthReport> findShopItemMontList(@Param("dateTime")Date dateTime, @Param("itemName")String itemName,
            @Param("userName")String userName, @Param("reseauId")Integer reseauId, @Param("cityId")Integer cityId, @Param("isnew")String isnew, @Param("param")String param);

    List<ShopItemMonthReport> findShopItems(@Param("date")Date date, @Param("cityId")Integer cityId);
}