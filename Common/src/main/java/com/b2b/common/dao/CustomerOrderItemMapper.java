package com.b2b.common.dao;

import com.b2b.common.domain.CustomerOrderItem;
import com.b2b.common.domain.CustomerOrderItemExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CustomerOrderItemMapper {
    int countByExample(CustomerOrderItemExample example);

    int deleteByExample(CustomerOrderItemExample example);

    int deleteByPrimaryKey(Integer coiId);

    int insert(CustomerOrderItem record);

    int insertSelective(CustomerOrderItem record);

    List<CustomerOrderItem> selectByExample(CustomerOrderItemExample example);

    CustomerOrderItem selectByPrimaryKey(Integer coiId);

    int updateByExampleSelective(@Param("record") CustomerOrderItem record, @Param("example") CustomerOrderItemExample example);

    int updateByExample(@Param("record") CustomerOrderItem record, @Param("example") CustomerOrderItemExample example);

    int updateByPrimaryKeySelective(CustomerOrderItem record);

    int updateByPrimaryKey(CustomerOrderItem record);
    
    List<HashMap<String, Object>> findOrderItemByOrderNo(int customerorderid);
    
    List<CustomerOrderItem> selectPurchasePlan();
}