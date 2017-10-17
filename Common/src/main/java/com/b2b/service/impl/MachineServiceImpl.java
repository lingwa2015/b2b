package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.MachineMapper;
import com.b2b.common.domain.Machine;
import com.b2b.common.domain.TransferExample;
import com.b2b.common.domain.TransferExample.Criteria;
import com.b2b.service.MachineService;
import com.b2b.service.TransferService;

@Service
public class MachineServiceImpl implements MachineService {
	@Autowired
	MachineMapper machineMapper;
	
	@Autowired
	TransferService transferService;
	
	@Override
	public Machine findById(Integer id) {
		return this.machineMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Machine dto) {
		this.machineMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public void create(Machine dto) {
		this.machineMapper.insert(dto);
	}

	@Override
	public List<Machine> findAll(Integer oneTypeId, Integer twoTypeId, String userName, String machineId,Integer cityId) {
		return this.machineMapper.findAll(oneTypeId,twoTypeId,userName,machineId,cityId);
	}

	@Override
	public Machine findWithTypeNameById(Integer id) {
		return this.machineMapper.findWithTypeNameById(id);
	}

	@Override
	public void updateUserId(Integer macId, Integer customerid) {
		this.machineMapper.updateUserId(macId,customerid);
	}

	@Override
	public void deleteMachine(Integer id) {
		this.machineMapper.deleteByPrimaryKey(id);
		this.transferService.deleteByMacId(id);
	}

	@Override
	public Machine findByMachineIdAndCityId(String machineId, Integer cityId) {
		return this.machineMapper.findByMachineIdAndCityId(machineId,cityId);
	}

	@Override
	public void updateUserIdAndForegift(Integer macId, Integer customerid,
			long foregift) {
		this.machineMapper.updateUserIdAndForegift(macId,customerid,foregift);
	}

}
