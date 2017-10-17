package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.WholeTokenMapper;
import com.b2b.common.domain.WholeToken;
import com.b2b.service.WholeTokenService;

@Service
public class WholeTokenServiceImpl implements WholeTokenService {
	@Autowired
	private WholeTokenMapper wholeTokenMapper;
	
	/**
	 * 一个小时内的token
	 */
	@Override
	public WholeToken findByIdOneHour(int id) {
		return this.wholeTokenMapper.findByIdOneHour(id);
	}

	@Override
	public WholeToken findById(int i) {
		return this.wholeTokenMapper.selectByPrimaryKey(i);
	}

	@Override
	public void insert(WholeToken token2) {
		this.wholeTokenMapper.insert(token2);
	}

	@Override
	public void update(WholeToken t) {
		this.wholeTokenMapper.updateByPrimaryKeySelective(t);
	}
	
}
