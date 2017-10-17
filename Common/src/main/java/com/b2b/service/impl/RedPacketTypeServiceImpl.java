package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.RedPacketTypeMapper;
import com.b2b.common.domain.RedPacketType;
import com.b2b.service.RedPacketTypeService;

@Service
public class RedPacketTypeServiceImpl implements RedPacketTypeService{
	@Autowired
	private RedPacketTypeMapper redPacketTypeMapper;
	
	@Override
	public void save(RedPacketType redPacketType) {
		this.redPacketTypeMapper.insert(redPacketType);
	}

	@Override
	public Integer findTotalNumByRedId(Integer id) {
		return this.redPacketTypeMapper.findTotalNumByRedId(id);
	}

	@Override
	public List<RedPacketType> findByRedId(Integer redId) {
		return this.redPacketTypeMapper.findByRedId(redId);
	}

}
