package com.b2b.common.dao;

import com.b2b.common.domain.ItemPriceHistory;
import com.b2b.common.domain.ItemPriceHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemPriceHistoryMapper {
    int countByExample(ItemPriceHistoryExample example);

    int deleteByExample(ItemPriceHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemPriceHistory record);

    int insertSelective(ItemPriceHistory record);

    List<ItemPriceHistory> selectByExample(ItemPriceHistoryExample example);

    ItemPriceHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemPriceHistory record, @Param("example") ItemPriceHistoryExample example);

    int updateByExample(@Param("record") ItemPriceHistory record, @Param("example") ItemPriceHistoryExample example);

    int updateByPrimaryKeySelective(ItemPriceHistory record);

    int updateByPrimaryKey(ItemPriceHistory record);

    List<ItemPriceHistory> findByItemName(@Param("itemName")String itemName, @Param("cityId")Integer cityId);
}