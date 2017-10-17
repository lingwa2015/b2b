package com.b2b.common.dao;

import com.b2b.common.domain.CustomerBlackWhiteList;
import com.b2b.common.domain.CustomerBlackWhiteListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerBlackWhiteListMapper {
    int countByExample(CustomerBlackWhiteListExample example);

    int deleteByExample(CustomerBlackWhiteListExample example);

    int deleteByPrimaryKey(Integer blackwhiteId);

    int insert(CustomerBlackWhiteList record);

    int insertSelective(CustomerBlackWhiteList record);

    List<CustomerBlackWhiteList> selectByExample(CustomerBlackWhiteListExample example);

    CustomerBlackWhiteList selectByPrimaryKey(Integer blackwhiteId);

    int updateByExampleSelective(@Param("record") CustomerBlackWhiteList record, @Param("example") CustomerBlackWhiteListExample example);

    int updateByExample(@Param("record") CustomerBlackWhiteList record, @Param("example") CustomerBlackWhiteListExample example);

    int updateByPrimaryKeySelective(CustomerBlackWhiteList record);

    int updateByPrimaryKey(CustomerBlackWhiteList record);
}