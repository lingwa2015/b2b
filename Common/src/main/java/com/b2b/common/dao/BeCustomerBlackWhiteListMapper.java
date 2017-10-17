package com.b2b.common.dao;

import com.b2b.common.domain.BeCustomerBlackWhiteList;
import com.b2b.common.domain.BeCustomerBlackWhiteListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BeCustomerBlackWhiteListMapper {
    int countByExample(BeCustomerBlackWhiteListExample example);

    int deleteByExample(BeCustomerBlackWhiteListExample example);

    int deleteByPrimaryKey(Integer beBlackwhiteId);

    int insert(BeCustomerBlackWhiteList record);

    int insertSelective(BeCustomerBlackWhiteList record);

    List<BeCustomerBlackWhiteList> selectByExample(BeCustomerBlackWhiteListExample example);

    BeCustomerBlackWhiteList selectByPrimaryKey(Integer beBlackwhiteId);

    int updateByExampleSelective(@Param("record") BeCustomerBlackWhiteList record, @Param("example") BeCustomerBlackWhiteListExample example);

    int updateByExample(@Param("record") BeCustomerBlackWhiteList record, @Param("example") BeCustomerBlackWhiteListExample example);

    int updateByPrimaryKeySelective(BeCustomerBlackWhiteList record);

    int updateByPrimaryKey(BeCustomerBlackWhiteList record);
}