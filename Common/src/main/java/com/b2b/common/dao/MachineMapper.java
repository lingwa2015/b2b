package com.b2b.common.dao;

import com.b2b.common.domain.Machine;
import com.b2b.common.domain.MachineExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MachineMapper {
    int countByExample(MachineExample example);

    int deleteByExample(MachineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Machine record);

    int insertSelective(Machine record);

    List<Machine> selectByExample(MachineExample example);

    Machine selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Machine record, @Param("example") MachineExample example);

    int updateByExample(@Param("record") Machine record, @Param("example") MachineExample example);

    int updateByPrimaryKeySelective(Machine record);

    int updateByPrimaryKey(Machine record);

	List<Machine> findAll(@Param("oneTypeId")Integer oneTypeId, @Param("twoTypeId")Integer twoTypeId, @Param("userName")String userName, @Param("machineId")String machineId, @Param("cityId")Integer cityId);

	Machine findWithTypeNameById(@Param("id")Integer id);

	void updateUserId(@Param("macId")Integer macId, @Param("customerid")Integer customerid);

	Machine findByMachineIdAndCityId(@Param("machineId")String machineId, @Param("cityId")Integer cityId);

	void updateUserIdAndForegift(@Param("macId")Integer macId, @Param("customerid")Integer customerid, @Param("foregift")long foregift);
}