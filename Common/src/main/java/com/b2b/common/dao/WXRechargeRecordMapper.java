package com.b2b.common.dao;

import com.b2b.common.domain.WXRechargeRecord;
import com.b2b.common.domain.WXRechargeRecordExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WXRechargeRecordMapper {
    int countByExample(WXRechargeRecordExample example);

    int deleteByExample(WXRechargeRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(WXRechargeRecord record);

    int insertSelective(WXRechargeRecord record);

    List<WXRechargeRecord> selectByExample(WXRechargeRecordExample example);

    WXRechargeRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WXRechargeRecord record, @Param("example") WXRechargeRecordExample example);

    int updateByExample(@Param("record") WXRechargeRecord record, @Param("example") WXRechargeRecordExample example);

    int updateByPrimaryKeySelective(WXRechargeRecord record);

    int updateByPrimaryKey(WXRechargeRecord record);

	int countByCondition(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("userName")String userName);

	List<WXRechargeRecord> findPageByCondition(@Param("startTime")Date startTime, @Param("endTime") Date endTime,
			@Param("userName")String userName, @Param("start")int start, @Param("pageSize")int pageSize);
}