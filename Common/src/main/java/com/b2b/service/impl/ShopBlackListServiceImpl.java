package com.b2b.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopBlackListMapper;
import com.b2b.common.domain.ShopBlackList;
import com.b2b.common.domain.ShopBlackListExample;
import com.b2b.common.domain.ShopBlackListExample.Criteria;
import com.b2b.service.ShopBlackListService;

@Service
public class ShopBlackListServiceImpl implements ShopBlackListService {
	@Autowired
	private ShopBlackListMapper shopBlackListMapper;
	
	@Override
	public void insert(ShopBlackList list) {
		this.shopBlackListMapper.insert(list);
	}

	@Override
	public List<HashMap<String, Object>> findByShopId(Integer shop_id) {
		return this.shopBlackListMapper.findByShopId(shop_id);
	}

	@Override
	public void save(List<ShopBlackList> shopBlackList, Integer shop_id) {
		ShopBlackListExample example = new ShopBlackListExample();
		Criteria criteria = example.createCriteria();
		criteria.andShopIdEqualTo(shop_id);
		this.shopBlackListMapper.deleteByExample(example);
		for (ShopBlackList shopBlackList2 : shopBlackList) {
			this.shopBlackListMapper.insert(shopBlackList2);
		}
	}

	@Override
	public ArrayList<Integer> findAllByShopId(Integer customerId) {
		return this.shopBlackListMapper.findAllByShopId(customerId);
	}

	@Override
	public ShopBlackList findByShopIdAndItemId(Integer userId, Integer itemId) {
		return this.shopBlackListMapper.findByShopIdAndItemId(userId,itemId);
	}

	@Override
	public int countByItemId(Integer id) {
		return this.shopBlackListMapper.countByItemId(id);
	}

}
