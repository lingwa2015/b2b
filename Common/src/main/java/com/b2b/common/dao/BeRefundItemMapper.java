package com.b2b.common.dao;

import com.b2b.common.domain.BeRefundItem;
import com.b2b.common.domain.BeRefundItemExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BeRefundItemMapper {
    int countByExample(BeRefundItemExample example);

    int deleteByExample(BeRefundItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BeRefundItem record);

    int insertSelective(BeRefundItem record);

    List<BeRefundItem> selectByExample(BeRefundItemExample example);

    BeRefundItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BeRefundItem record, @Param("example") BeRefundItemExample example);

    int updateByExample(@Param("record") BeRefundItem record, @Param("example") BeRefundItemExample example);

    int updateByPrimaryKeySelective(BeRefundItem record);

    int updateByPrimaryKey(BeRefundItem record);

	List<BeRefundItem> findTodayRefundItemByShopId(Integer shopId);
}