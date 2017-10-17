package com.b2b.common.dao;

import com.b2b.common.domain.CustomerCamera;
import com.b2b.common.domain.CustomerCameraExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CustomerCameraMapper {
    int countByExample(CustomerCameraExample example);

    int deleteByExample(CustomerCameraExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(CustomerCamera record);

    int insertSelective(CustomerCamera record);

    List<CustomerCamera> selectByExample(CustomerCameraExample example);

    CustomerCamera selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") CustomerCamera record, @Param("example") CustomerCameraExample example);

    int updateByExample(@Param("record") CustomerCamera record, @Param("example") CustomerCameraExample example);

    int updateByPrimaryKeySelective(CustomerCamera record);

    int updateByPrimaryKey(CustomerCamera record);

	List<CustomerCamera> findByCondition(@Param("userName")String userName, @Param("param")String param, @Param("cityId")Integer cityId);
}