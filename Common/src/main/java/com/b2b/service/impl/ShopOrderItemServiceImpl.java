package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopOrderItemMapper;
import com.b2b.common.domain.ShopOrderItem;
import com.b2b.common.domain.ShopOrderItemExample;
import com.b2b.common.domain.ShopOrderItemExample.Criteria;
import com.b2b.service.ShopOrderItemService;

@Service
public class ShopOrderItemServiceImpl implements ShopOrderItemService {
	@Autowired
	private ShopOrderItemMapper shopOrderItemMapper;
	
	@Override
	public void create(ShopOrderItem shopOrderItem) {
		this.shopOrderItemMapper.insert(shopOrderItem);
	}

	@Override
	public List<ShopOrderItem> findItemByOrderId(String orderId) {
		ShopOrderItemExample example = new ShopOrderItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		return this.shopOrderItemMapper.selectByExample(example);
	}

}
