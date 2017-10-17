package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopUserMapper;
import com.b2b.common.domain.ShopUser;
import com.b2b.common.domain.ShopUserExample;
import com.b2b.common.domain.ShopUserExample.Criteria;
import com.b2b.common.domain.WXUser;
import com.b2b.service.ShopUserService;

@Service
public class ShopUserServiceImpl implements ShopUserService {
	@Autowired
	private ShopUserMapper shopUserMapper;
	
	@Override
	public ShopUser findByOpenId(String openId) {
		ShopUserExample example = new ShopUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andOpenidEqualTo(openId);
		List<ShopUser> list = this.shopUserMapper.selectByExample(example);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public void create(ShopUser wxUser) {
		this.shopUserMapper.insert(wxUser);
	}

	@Override
	public void update(ShopUser user) {
		this.shopUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public ShopUser findById(Integer id) {
		return shopUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ShopUser> findManagerByShopid(Integer shopId) {
		return this.shopUserMapper.findManagerByShopid(shopId);
	}

	@Override
	public void updateManage(Integer id) {
		this.shopUserMapper.updateManage(id);
	}

	@Override
	public List<ShopUser> findAllSuperAdmin() {
		return this.shopUserMapper.findAllSuperAdmin();
	}

	@Override
	public void changeAdminToComon(Integer id) {
		this.shopUserMapper.changeAdminToComon(id);
	}


}
