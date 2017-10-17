package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.TransferMapper;
import com.b2b.common.domain.Machine;
import com.b2b.common.domain.Transfer;
import com.b2b.common.domain.TransferExample;
import com.b2b.common.domain.TransferExample.Criteria;
import com.b2b.service.MachineService;
import com.b2b.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService{
	@Autowired
	TransferMapper transferMapper;
	
	@Autowired
	MachineService machineService;
	
	@Override
	public void insert(Transfer dto) {
		this.transferMapper.insert(dto);
		if(null==dto.getForegift()){
			this.machineService.updateUserIdAndForegift(dto.getMacId(),dto.getCustomerid(),0l);
		}else{
			this.machineService.updateUserIdAndForegift(dto.getMacId(),dto.getCustomerid(),dto.getForegift());
		}
	}

	@Override
	public List<Transfer> findByCondition(String machineId, Date startTime,
			Date endTime,String type,Integer cityId) {
		return this.transferMapper.findByCondition(machineId,startTime,endTime,type,cityId);
	}

	@Override
	public void delete(String id) {
		Transfer transfer = this.transferMapper.selectByPrimaryKey(id);
		transfer.setState(0);
		this.transferMapper.updateByPrimaryKeySelective(transfer);
		Transfer dto = this.transferMapper.findNewest(transfer.getMacId());
		if(null!=dto){
			if(null != dto.getForegift()){
				this.machineService.updateUserIdAndForegift(transfer.getMacId(), dto.getCustomerid(), dto.getForegift());
			}else{
				this.machineService.updateUserIdAndForegift(transfer.getMacId(), dto.getCustomerid(), 0);
			}
		}else{
			this.machineService.updateUserIdAndForegift(transfer.getMacId(), null,0);
		}
	}

	@Override
	public Transfer findById(String transferId) {
		return this.transferMapper.findById(transferId);
	}

	@Override
	public void deleteByMacId(Integer id) {
		TransferExample example = new TransferExample();
		Criteria criteria = example.createCriteria();
		criteria.andMacIdEqualTo(id);
		this.transferMapper.deleteByExample(example);
	}

}
