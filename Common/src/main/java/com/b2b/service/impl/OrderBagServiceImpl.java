package com.b2b.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.OrderBagMapper;
import com.b2b.common.domain.OrderBag;
import com.b2b.common.domain.OrderBagExample;
import com.b2b.common.domain.OrderBagExample.Criteria;
import com.b2b.service.OrderBagService;

@Service
public class OrderBagServiceImpl implements OrderBagService {
	@Autowired
	private OrderBagMapper orderBagMapper;
	
	@Override
	public void insert(OrderBag orderBag) {
		this.orderBagMapper.insert(orderBag);
	}

	@Override
	public List<OrderBag> findByOrderNo(String orderNo) {
		OrderBagExample example = new OrderBagExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andOrdernoEqualTo(orderNo);
		return this.orderBagMapper.selectByExample(example);
	}

	@Override
	public List<HashMap<String, Object>> findOrderBagAndBagExecuteTimeByOrderNo(String orderNo) {
		return orderBagMapper.findOrderBagAndBagExecuteTimeByOrderNo(orderNo);
	}

	@Override
	public void deleteOrderBag(String orderNo) {
		OrderBagExample example = new OrderBagExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrdernoEqualTo(orderNo);
		this.orderBagMapper.deleteByExample(example);
		
	}

	@Override
	public void update(OrderBag orderBag) {
		this.orderBagMapper.updateByPrimaryKeySelective(orderBag);
	}

}
