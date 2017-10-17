package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopAliUserMapper;
import com.b2b.common.domain.ShopAliUser;
import com.b2b.service.ShopAliUserService;

@Service
public class ShopAliUserServiceImpl implements ShopAliUserService {
	@Autowired
	private ShopAliUserMapper shopAliUserMapper;
	
	@Override
	public ShopAliUser findByOpenId(String userId) {
		return shopAliUserMapper.findByOpenId(userId);
	}

	@Override
	public void save(ShopAliUser user) {
		this.shopAliUserMapper.insert(user);
	}

	@Override
	public void update(ShopAliUser user) {
		this.shopAliUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public ShopAliUser findById(Integer buyerId) {
		return this.shopAliUserMapper.selectByPrimaryKey(buyerId);
	}

}
