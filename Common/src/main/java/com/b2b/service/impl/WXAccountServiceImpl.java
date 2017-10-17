package com.b2b.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.WXAccountMapper;
import com.b2b.common.domain.WXAccount;
import com.b2b.service.WXAccountService;

@Service
public class WXAccountServiceImpl implements WXAccountService {
	
	@Autowired
	WXAccountMapper wxAccountMapper;
	@Override
	public WXAccount findVipBycid(Integer customerUserId) {
		return this.wxAccountMapper.selectByPrimaryKey(customerUserId);
	}
	@Override
	public void create(WXAccount wxAccount) {
		try {
			wxAccountMapper.insert(wxAccount);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void updateForAddMoney(Integer cid, long money) {
		this.wxAccountMapper.updateForAddMoney(cid,money);
	}

}
