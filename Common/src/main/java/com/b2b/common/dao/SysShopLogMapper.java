package com.b2b.common.dao;

import com.b2b.common.domain.SysShopLog;
import com.b2b.common.domain.SysShopLogExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysShopLogMapper {
    int countByExample(SysShopLogExample example);

    int deleteByExample(SysShopLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysShopLog record);

    int insertSelective(SysShopLog record);

    List<SysShopLog> selectByExampleWithBLOBs(SysShopLogExample example);

    List<SysShopLog> selectByExample(SysShopLogExample example);

    SysShopLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysShopLog record, @Param("example") SysShopLogExample example);

    int updateByExampleWithBLOBs(@Param("record") SysShopLog record, @Param("example") SysShopLogExample example);

    int updateByExample(@Param("record") SysShopLog record, @Param("example") SysShopLogExample example);

    int updateByPrimaryKeySelective(SysShopLog record);

    int updateByPrimaryKeyWithBLOBs(SysShopLog record);

    int updateByPrimaryKey(SysShopLog record);

	List<SysShopLog> findList(@Param("content")String content, @Param("company")String company, @Param("startTime")Date startTime,
			@Param("endTime")Date endTime, @Param("cityId")Integer cityId);
}