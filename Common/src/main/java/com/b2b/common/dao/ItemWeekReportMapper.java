package com.b2b.common.dao;

import com.b2b.common.domain.ItemWeekReport;
import com.b2b.common.domain.ItemWeekReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ItemWeekReportMapper {
    long countByExample(ItemWeekReportExample example);

    int deleteByExample(ItemWeekReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemWeekReport record);

    int insertSelective(ItemWeekReport record);

    List<ItemWeekReport> selectByExample(ItemWeekReportExample example);

    ItemWeekReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemWeekReport record, @Param("example") ItemWeekReportExample example);

    int updateByExample(@Param("record") ItemWeekReport record, @Param("example") ItemWeekReportExample example);

    int updateByPrimaryKeySelective(ItemWeekReport record);

    int updateByPrimaryKey(ItemWeekReport record);

    List<ItemWeekReport> findAllItem();

    int deleteByDate(@Param("date4")Date date4, @Param("weekth")Integer weekth);

    Integer selectOrderItemNum(@Param("itemId")Integer itemId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    ItemWeekReport selectShopOrderInfo(@Param("itemId")Integer itemId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    Integer selectShopNum(@Param("itemId")Integer itemId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    ItemWeekReport selectOrderInfo(@Param("itemId")Integer itemId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    Integer selectOrderShopNum(@Param("itemId")Integer itemId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    ItemWeekReport selectRefundInfo(@Param("itemId")Integer itemId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    Integer selectOrderRefundShopNum(@Param("itemId")Integer itemId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    Integer selectAllShopNum(@Param("itemId")Integer itemId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    Integer selectStockNum(@Param("itemId")Integer itemId, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

    List<ItemWeekReport> findItemWeekReports(@Param("starttime")Date starttime, @Param("endtime")Date endtime,
        @Param("itemName")String itemName, @Param("param")String param, @Param("isnew")String isnew,
            @Param("isrecommend")String isrecommend, @Param("onecate")int onecate, @Param("twocate")int twocate, @Param("cityId")Integer cityId);

    List<ItemWeekReport> findAllItems(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("weekth")Integer weekth);
}