package com.b2b.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.BlackWhiteListVarietyMapper;
import com.b2b.common.domain.BlackWhiteListVariety;
import com.b2b.common.domain.BlackWhiteListVarietyExample;
import com.b2b.common.domain.BlackWhiteListVarietyExample.Criteria;
import com.b2b.service.BlackwhitelistVarietyService;

@Service
public class BlackwhitelistVarietyServiceImpl implements
		BlackwhitelistVarietyService {
	@Autowired
	private BlackWhiteListVarietyMapper blackWhiteListVarietyMapper;

	@Override
	public BlackWhiteListVariety findBlackByVarietyId(Integer customerId,
			int flag, Integer varietyId) {
		return this.blackWhiteListVarietyMapper.findBlackByVarietyId(customerId,flag,varietyId);
	}

	@Override
	public void create(BlackWhiteListVariety blackListVariety) {
		this.blackWhiteListVarietyMapper.insert(blackListVariety);
	}

	@Override
	public List<HashMap<String, Object>> findAll(Integer id, int i) {
		return blackWhiteListVarietyMapper.findAll(id,i);
	}

	@Override
	public void deleteAll(int blackId) {
		BlackWhiteListVarietyExample example = new BlackWhiteListVarietyExample();
		Criteria criteria = example.createCriteria();
		criteria.andBlackwhiteIdEqualTo(blackId);
		this.blackWhiteListVarietyMapper.deleteByExample(example);
	}

	@Override
	public void deleteById(int id) {
		this.blackWhiteListVarietyMapper.deleteByPrimaryKey(id);
	}
	

}
