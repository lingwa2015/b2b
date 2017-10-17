package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.RedPacketCustomerMapper;
import com.b2b.common.domain.RedPacketCustomer;
import com.b2b.common.domain.RedPacketCustomerExample;
import com.b2b.common.domain.RedPacketCustomerExample.Criteria;
import com.b2b.service.RedPacketCustomerService;
import com.b2b.service.RedPacketService;

@Service
public class RedPacketCustomerServiceImpl implements RedPacketCustomerService{
	@Autowired
	private RedPacketCustomerMapper redPacketCustomerMapper;
	
	@Autowired
	private RedPacketService redPacketService;
	
	@Override
	public void save(List<RedPacketCustomer> datas) {
		for (RedPacketCustomer redPacketCustomer : datas) {
			RedPacketCustomerExample example = new RedPacketCustomerExample();
			Criteria criteria = example.createCriteria();
			criteria.andCustomerIdEqualTo(redPacketCustomer.getCustomerId());
			criteria.andRedIdEqualTo(redPacketCustomer.getRedId());
			List<RedPacketCustomer> list = this.redPacketCustomerMapper.selectByExample(example);
			if(list.isEmpty()){
				this.redPacketCustomerMapper.insert(redPacketCustomer);
			}
		}
	}

	@Override
	public void delete(List<RedPacketCustomer> datas) {
		for (RedPacketCustomer redPacketCustomer : datas) {
			RedPacketCustomerExample example = new RedPacketCustomerExample();
			Criteria criteria = example.createCriteria();
			criteria.andCustomerIdEqualTo(redPacketCustomer.getCustomerId());
			criteria.andRedIdEqualTo(redPacketCustomer.getRedId());
			this.redPacketCustomerMapper.deleteByExample(example);
		}
	}

	@Override
	public RedPacketCustomer findByRedIdAndShopId(Integer redId,Integer shopId) {
		return this.redPacketCustomerMapper.findByRedIdAndShopId(redId,shopId);
	}
	
}
