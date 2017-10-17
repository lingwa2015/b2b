package com.b2b.common.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.OrderItemExample;

public interface OrderItemMapper {
    int countByExample(OrderItemExample example);

    int deleteByExample(OrderItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExample(OrderItemExample example);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    int updateByExample(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    int countByStatisticsSql(HashMap<String, Object> paramMap);

    OrderItem countByStatisticsTotal(HashMap<String, Object> paramMap);

    List<OrderItem> selectStatisticsByDateTime(HashMap<String, Object> paramMap);
    
    int countByStatisticsSqlNew(HashMap<String, Object> paramMap);

    List<OrderItem> selectStatisticsByDateTimeNew(HashMap<String, Object> paramMap);
    
    OrderItem selectStatisticsByDateTimeTotalNew(HashMap<String, Object> paramMap);

	List<HashMap<String, Object>> findOrderItemByOrderNo(String orderNo);

	List<OrderItem> findOrderItemWithCatAndStockByOrderNo(@Param("orderNo")String orderNo);

	List<HashMap<String, Object>> findItemInfoByOrderNo(String id);
}