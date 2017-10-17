package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopRefundMapper;
import com.b2b.common.domain.ShopRefund;
import com.b2b.service.ShopRefundService;

@Service
public class ShopRefundServiceImpl implements ShopRefundService {
	@Autowired
	private ShopRefundMapper shopRefundMapper;
	
	@Override
	public void create(ShopRefund refund) {
		this.shopRefundMapper.insert(refund);
	}

}
