package com.b2b.common.dao;

import com.b2b.common.domain.CustomerWise;
import com.b2b.common.domain.CustomerWiseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerWiseMapper {
    int countByExample(CustomerWiseExample example);

    int deleteByExample(CustomerWiseExample example);

    int deleteByPrimaryKey(Integer wiseId);

    int insert(CustomerWise record);

    int insertSelective(CustomerWise record);

    List<CustomerWise> selectByExample(CustomerWiseExample example);

    CustomerWise selectByPrimaryKey(Integer wiseId);

    int updateByExampleSelective(@Param("record") CustomerWise record, @Param("example") CustomerWiseExample example);

    int updateByExample(@Param("record") CustomerWise record, @Param("example") CustomerWiseExample example);

    int updateByPrimaryKeySelective(CustomerWise record);

    int updateByPrimaryKey(CustomerWise record);
    
    List<CustomerWise> getCustomerWiseInfo(int week);
}