package com.b2b.common.dao;

import com.b2b.common.domain.StandardOrderItem;
import com.b2b.common.domain.StandardOrderItemExample;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StandardOrderItemMapper {
    int countByExample(StandardOrderItemExample example);

    int deleteByExample(StandardOrderItemExample example);

    int deleteByPrimaryKey(Integer soiId);

    int insert(StandardOrderItem record);

    int insertSelective(StandardOrderItem record);

    List<StandardOrderItem> selectByExample(StandardOrderItemExample example);

    StandardOrderItem selectByPrimaryKey(Integer soiId);

    int updateByExampleSelective(@Param("record") StandardOrderItem record, @Param("example") StandardOrderItemExample example);

    int updateByExample(@Param("record") StandardOrderItem record, @Param("example") StandardOrderItemExample example);

    int updateByPrimaryKeySelective(StandardOrderItem record);

    int updateByPrimaryKey(StandardOrderItem record);
    
    List<HashMap<String, Object>> findOrderItemByOrderNo(int soiId);
    
    List<StandardOrderItem> selectItemNum(@Param("startTime")Date startTime,@Param("endTime")Date endTime);

	List<StandardOrderItem> findByStandOrderId(@Param("snackpackageId")Integer snackpackageId);
}