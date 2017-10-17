package com.b2b.common.dao;

import com.b2b.common.domain.Transfer;
import com.b2b.common.domain.TransferExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TransferMapper {
    int countByExample(TransferExample example);

    int deleteByExample(TransferExample example);

    int deleteByPrimaryKey(String transferId);

    int insert(Transfer record);

    int insertSelective(Transfer record);

    List<Transfer> selectByExample(TransferExample example);

    Transfer selectByPrimaryKey(String transferId);

    int updateByExampleSelective(@Param("record") Transfer record, @Param("example") TransferExample example);

    int updateByExample(@Param("record") Transfer record, @Param("example") TransferExample example);

    int updateByPrimaryKeySelective(Transfer record);

    int updateByPrimaryKey(Transfer record);

	List<Transfer> findByCondition(@Param("machineId")String machineId, @Param("startTime")Date startTime,
			@Param("endTime")Date endTime, @Param("type")String type, @Param("cityId")Integer cityId);

	Transfer findNewest(@Param("macId")Integer macId);

	Transfer findById(@Param("transferId")String transferId);

}
