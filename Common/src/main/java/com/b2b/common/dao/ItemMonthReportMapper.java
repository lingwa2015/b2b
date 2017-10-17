package com.b2b.common.dao;

import com.b2b.common.domain.ItemMonthReport;
import com.b2b.common.domain.ItemMonthReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ItemMonthReportMapper {
    long countByExample(ItemMonthReportExample example);

    int deleteByExample(ItemMonthReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemMonthReport record);

    int insertSelective(ItemMonthReport record);

    List<ItemMonthReport> selectByExample(ItemMonthReportExample example);

    ItemMonthReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemMonthReport record, @Param("example") ItemMonthReportExample example);

    int updateByExample(@Param("record") ItemMonthReport record, @Param("example") ItemMonthReportExample example);

    int updateByPrimaryKeySelective(ItemMonthReport record);

    int updateByPrimaryKey(ItemMonthReport record);

    List<ItemMonthReport> findAllItem();

    int deleteByDate(@Param("date4") Date date4, @Param("cityId")Integer cityId);

    Integer selectOrderItemNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    ItemMonthReport selectShopOrderInfo(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectShopNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    ItemMonthReport selectOrderInfo(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectOrderShopNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    ItemMonthReport selectRefundInfo(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectOrderRefundShopNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectAllShopNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectStockNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectStorageOrderNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    Integer selectStorageNum(@Param("itemId")Integer itemId, @Param("date")Date data);

    List<ItemMonthReport> findItemDailyReports(@Param("date")Date date,
                                               @Param("itemName")String itemName, @Param("param")String param, @Param("isnew")String isnew,
                                               @Param("isrecommend")String isrecommend, @Param("onecate")int onecate, @Param("twocate")int twocate, @Param("cityId") Integer cityId);


    List<ItemMonthReport> findAllItems(@Param("date")Date date, @Param("cityId") Integer cityId);
}