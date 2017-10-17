package com.b2b.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.BeBlackWhiteListVarietyMapper;
import com.b2b.common.domain.BeBlackWhiteListVariety;
import com.b2b.common.domain.BeBlackWhiteListVarietyExample;
import com.b2b.common.domain.BeBlackWhiteListVarietyExample.Criteria;
import com.b2b.common.domain.BlackWhiteListVariety;
import com.b2b.service.BeBlackWhiteListVarietyService;

@Service
public class BeBlackWhiteListVarietyServiceImpl implements
		BeBlackWhiteListVarietyService {
	@Autowired
	private BeBlackWhiteListVarietyMapper beBlackWhiteListVarietyMapper;
	
	@Override
	public BeBlackWhiteListVariety findBlackByVarietyId(int customerId, int i,
			Integer varietyId) {
		return this.beBlackWhiteListVarietyMapper.findByVarietyId(customerId,i,varietyId);
	}

	@Override
	public void create(BeBlackWhiteListVariety blackListVariety) {
		this.beBlackWhiteListVarietyMapper.insert(blackListVariety);
	}

	@Override
	public List<HashMap<String, Object>> findAll(Integer id, int i) {
		return this.beBlackWhiteListVarietyMapper.findAll(id,i);
	}

	@Override
	public void deleteAll(int blackId) {
		BeBlackWhiteListVarietyExample example = new BeBlackWhiteListVarietyExample();
		Criteria criteria = example.createCriteria();
		criteria.andBeBlackwhiteIdEqualTo(blackId);
		this.beBlackWhiteListVarietyMapper.deleteByExample(example);
	}

	@Override
	public void deleteById(int id) {
		this.beBlackWhiteListVarietyMapper.deleteByPrimaryKey(id);
	}

}
