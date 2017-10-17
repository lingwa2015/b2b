package com.b2b.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.RedPacketMapper;
import com.b2b.common.domain.RedPacket;
import com.b2b.common.domain.RedPacketCustomer;
import com.b2b.common.domain.RedPacketType;
import com.b2b.service.RedPacketCustomerService;
import com.b2b.service.RedPacketService;
import com.b2b.service.RedPacketTypeService;

@Service
public class RedPacketServiceImpl implements RedPacketService {
	@Autowired
	private RedPacketMapper redPacketMapper;
	
	@Autowired
	private RedPacketTypeService redPacketTypeService;
	
	@Autowired
	private RedPacketCustomerService redPacketCustomerService;
	
	@Override
	public void save(RedPacket red, ArrayList<RedPacketType> list) {
		this.redPacketMapper.insert(red);
		for (RedPacketType redPacketType : list) {
			redPacketType.setRedPacketId(red.getId());
			this.redPacketTypeService.save(redPacketType);
		}
	}

	@Override
	public List<RedPacket> findAllByCityId(Integer cityId) {
		return this.redPacketMapper.findAllByCityId(cityId);
	}

	@Override
	public List<RedPacket> findStartedRedPacket(Integer cityId) {
		return this.redPacketMapper.findStartedRedPacket(cityId);
	}

	@Override
	public RedPacket findStartedRedPacketById(Integer redId) {
		return this.redPacketMapper.findStartedRedPacketById(redId);
	}

	@Override
	public void changeType(RedPacket packet) {
		this.redPacketMapper.updateByPrimaryKeySelective(packet);
	}

	@Override
	public void changeTypeAndSaveRedPacketCustomer(Integer redId, List<RedPacketCustomer> datas) {
		RedPacket packet = new RedPacket();
		packet.setId(redId);
		packet.setType(2);
		this.redPacketMapper.updateByPrimaryKeySelective(packet);
		this.redPacketCustomerService.save(datas);
	}

}
