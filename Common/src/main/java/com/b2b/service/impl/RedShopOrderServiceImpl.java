package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.RedShopOrderMapper;
import com.b2b.common.domain.RedShopOrder;
import com.b2b.common.domain.RedShopOrderExample;
import com.b2b.common.domain.RedShopOrderExample.Criteria;
import com.b2b.service.RedShopOrderService;

@Service
public class RedShopOrderServiceImpl implements RedShopOrderService {
	@Autowired
	RedShopOrderMapper redShopOrderMapper;
	
	@Override
	public RedShopOrder findByOrderNo(String id) {
		return this.redShopOrderMapper.findByOrderNo(id);
	}

	@Override
	public void save(RedShopOrder redShopOrder) {
		this.redShopOrderMapper.insert(redShopOrder);
	}

}
