package com.b2b.common.dao;

import com.b2b.common.domain.OrderBag;
import com.b2b.common.domain.OrderBagExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrderBagMapper {
    int countByExample(OrderBagExample example);

    int deleteByExample(OrderBagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderBag record);

    int insertSelective(OrderBag record);

    List<OrderBag> selectByExample(OrderBagExample example);

    OrderBag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderBag record, @Param("example") OrderBagExample example);

    int updateByExample(@Param("record") OrderBag record, @Param("example") OrderBagExample example);

    int updateByPrimaryKeySelective(OrderBag record);

    int updateByPrimaryKey(OrderBag record);

	List<HashMap<String, Object>> findOrderBagAndBagExecuteTimeByOrderNo(
			@Param("orderNo")String orderNo);
}