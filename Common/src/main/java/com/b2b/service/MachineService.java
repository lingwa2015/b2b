package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.Machine;

public interface MachineService {

	Machine findById(Integer id);

	void update(Machine dto);

	void create(Machine dto);

	List<Machine> findAll(Integer oneTypeId, Integer twoTypeId, String userName, String machineId, Integer cityId);

	Machine findWithTypeNameById(Integer id);

	void updateUserId(Integer macId, Integer customerid);

	void deleteMachine(Integer id);

	Machine findByMachineIdAndCityId(String machineId, Integer cityId);

	void updateUserIdAndForegift(Integer macId, Integer customerid, long l);

}
