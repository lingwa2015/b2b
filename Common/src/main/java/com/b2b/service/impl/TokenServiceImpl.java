package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.TokenMapper;
import com.b2b.common.domain.Token;
import com.b2b.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenMapper tokenMapper;
	
	@Override
	public Token findByid(Integer shopId) {
		return this.tokenMapper.selectByPrimaryKey(shopId);
	}

	@Override
	public void create(Token token2) {
		this.tokenMapper.insert(token2);
	}

	@Override
	public void update(Token token) {
		this.tokenMapper.updateByPrimaryKeySelective(token);
	}

	@Override
	public Token findByIdAndTime(Integer shop_id) {
		return tokenMapper.findByIdAndTime(shop_id);
	}

}
