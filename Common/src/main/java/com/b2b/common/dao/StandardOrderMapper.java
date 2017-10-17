package com.b2b.common.dao;

import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderExample;
import com.b2b.common.domain.StandardOrderItem;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StandardOrderMapper {
    int countByExample(StandardOrderExample example);

    int deleteByExample(StandardOrderExample example);

    int deleteByPrimaryKey(Integer standardorderId);

    int insert(StandardOrder record);

    int insertSelective(StandardOrder record);

    List<StandardOrder> selectByExample(StandardOrderExample example);

    StandardOrder selectByPrimaryKey(Integer standardorderId);

    int updateByExampleSelective(@Param("record") StandardOrder record, @Param("example") StandardOrderExample example);

    int updateByExample(@Param("record") StandardOrder record, @Param("example") StandardOrderExample example);

    int updateByPrimaryKeySelective(StandardOrder record);

    int updateByPrimaryKey(StandardOrder record);
    
    List<StandardOrder> getCurWeekStandOrder();
    
    List<StandardOrder> getStandOrderById(@Param("snackpackageId")Integer snackpackageId);

	StandardOrder findByStandId(int parseInt);

	List<StandardOrder> getCurWeekAndLastWeekStandOrder(@Param("now")Date now, @Param("date")Date date);

}