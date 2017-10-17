package com.b2b.common.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.b2b.common.domain.RefundItem;
import com.b2b.common.domain.RefundItemExample;

public interface RefundItemMapper {
    int countByExample(RefundItemExample example);

    int deleteByExample(RefundItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RefundItem record);

    int insertSelective(RefundItem record);

    List<RefundItem> selectByExample(RefundItemExample example);

    RefundItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RefundItem record, @Param("example") RefundItemExample example);

    int updateByExample(@Param("record") RefundItem record, @Param("example") RefundItemExample example);

    int updateByPrimaryKeySelective(RefundItem record);

    int updateByPrimaryKey(RefundItem record);

    RefundItem countByStatisticsTotal(HashMap<String, Object> paramMap);

    List<RefundItem> selectStatisticsByDateTime(HashMap<String, Object> paramMap);

	List<RefundItem> findRefundItemWithPosition(Integer id);
}