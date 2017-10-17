package com.b2b.common.dao;

import com.b2b.common.domain.ItemDailyReport;
import com.b2b.common.domain.ItemDailyReportExample;
import com.b2b.common.domain.ItemDailyReportKey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ItemDailyReportMapper {
    long countByExample(ItemDailyReportExample example);

    int deleteByExample(ItemDailyReportExample example);

    int deleteByPrimaryKey(ItemDailyReportKey key);

    int insert(ItemDailyReport record);

    int insertSelective(ItemDailyReport record);

    List<ItemDailyReport> selectByExample(ItemDailyReportExample example);

    ItemDailyReport selectByPrimaryKey(ItemDailyReportKey key);

    int updateByExampleSelective(@Param("record") ItemDailyReport record, @Param("example") ItemDailyReportExample example);

    int updateByExample(@Param("record") ItemDailyReport record, @Param("example") ItemDailyReportExample example);

    int updateByPrimaryKeySelective(ItemDailyReport record);

    int updateByPrimaryKey(ItemDailyReport record);

    List<ItemDailyReport> findAllItem();

    Integer selectOrderItemNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    ItemDailyReport selectShopOrderInfo(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectShopNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    ItemDailyReport selectOrderInfo(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectOrderShopNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    ItemDailyReport selectRefundInfo(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectOrderRefundShopNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectAllShopNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectStockNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    List<ItemDailyReport> findItemDailyReports(@Param("starttime")Date starttime, @Param("endtime")Date endtime,
        @Param("itemName")String itemName, @Param("param")String param, @Param("isnew")String isnew,
        @Param("isrecommend")String isrecommend, @Param("onecate")int onecate, @Param("twocate")int twocate,@Param("cityId")Integer cityId);

    List<ItemDailyReport> findAllItems(@Param("date")Date data);

    int insertItemDailyReportList(List<ItemDailyReport> reports);
}