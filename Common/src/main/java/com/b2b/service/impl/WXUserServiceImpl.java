package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.WXUserMapper;
import com.b2b.common.domain.WXUser;
import com.b2b.common.domain.WXUserExample;
import com.b2b.common.domain.WXUserExample.Criteria;
import com.b2b.service.WXUserService;

@Service
public class WXUserServiceImpl implements WXUserService {
	@Autowired
	private WXUserMapper wXUserMapper;
	
	@Override
	public WXUser findByOpenId(String openId) {
		WXUserExample example = new WXUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andOpenidEqualTo(openId);
		List<WXUser> list = this.wXUserMapper.selectByExample(example);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public void create(WXUser weiXinUser) {
		this.wXUserMapper.insert(weiXinUser);
	}

	@Override
	public WXUser findById(Integer wxuserId) {
		return this.wXUserMapper.selectByPrimaryKey(wxuserId);
	}

	@Override
	public void updateCID(Integer id, Integer id2) {
		WXUser wxUser = new WXUser();
		wxUser.setId(id);
		wxUser.setCustomerUserId(id2);
		this.wXUserMapper.updateByPrimaryKeySelective(wxUser);
	}

	@Override
	public void upadte(WXUser wxUser) {
		this.wXUserMapper.updateByPrimaryKeySelective(wxUser);
	}
	
}
