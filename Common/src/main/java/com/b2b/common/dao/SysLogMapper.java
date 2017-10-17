package com.b2b.common.dao;

import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.SysLogExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysLogMapper {
    int countByExample(SysLogExample example);

    int deleteByExample(SysLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    List<SysLog> selectByExampleWithBLOBs(SysLogExample example);

    List<SysLog> selectByExample(SysLogExample example);

    SysLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysLog record, @Param("example") SysLogExample example);

    int updateByExampleWithBLOBs(@Param("record") SysLog record, @Param("example") SysLogExample example);

    int updateByExample(@Param("record") SysLog record, @Param("example") SysLogExample example);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKeyWithBLOBs(SysLog record);

    int updateByPrimaryKey(SysLog record);

	List<SysLog> findList(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("phone")String phone, @Param("content")String content, @Param("cityId")Integer cityId);
}