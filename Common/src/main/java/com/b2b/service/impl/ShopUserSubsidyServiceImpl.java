package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopUserSubsidyMapper;
import com.b2b.common.domain.ShopUserSubsidy;
import com.b2b.service.ShopUserSubsidyService;

@Service
public class ShopUserSubsidyServiceImpl implements ShopUserSubsidyService{
	@Autowired
	ShopUserSubsidyMapper shopUserSubsidyMapper;
	
	@Override
	public ShopUserSubsidy findByUserIdAndShopId(Integer userId, Integer shopId) {
		return this.shopUserSubsidyMapper.findByUserIdAndShopId(userId,shopId);
	}

	@Override
	public void save(ShopUserSubsidy user) {
		this.shopUserSubsidyMapper.insert(user);
	}

	@Override
	public List<ShopUserSubsidy> findListByShopIdAndType(Integer shopId, Integer type, Long freeFee) {
		return this.shopUserSubsidyMapper.findListByShopIdAndType(shopId,type,freeFee);
	}

	@Override
	public void deleteById(Integer id) {
	    this.shopUserSubsidyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<ShopUserSubsidy> findListByShopId(Integer shopId) {
		return this.shopUserSubsidyMapper.findListByShopId(shopId);
	}
	
}
