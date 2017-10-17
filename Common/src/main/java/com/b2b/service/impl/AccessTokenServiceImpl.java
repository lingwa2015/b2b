package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.AccessTokenMapper;
import com.b2b.common.domain.AccessToken;
import com.b2b.service.AccessTokenService;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {
	@Autowired
	AccessTokenMapper accessTokenMapper;

	@Override
	public AccessToken findById(String openId) {
		return this.accessTokenMapper.selectByPrimaryKey(openId);
	}

	@Override
	public void update(AccessToken token) {
		this.accessTokenMapper.updateByPrimaryKeySelective(token);
	}

	@Override
	public void insert(AccessToken token2) {
		this.accessTokenMapper.insert(token2);
	}


}
