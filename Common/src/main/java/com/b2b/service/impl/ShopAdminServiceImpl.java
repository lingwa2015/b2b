package com.b2b.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopAdminMapper;
import com.b2b.common.domain.ShopAdmin;
import com.b2b.common.domain.ShopAdminExample;
import com.b2b.common.domain.ShopAdminExample.Criteria;
import com.b2b.service.ShopAdminService;

@Service
public class ShopAdminServiceImpl implements ShopAdminService {
	@Autowired
	private ShopAdminMapper shopAdminMapper;

	@Override
	public ShopAdmin findByAdminIdAndShopId(Integer id, Integer shop_id) {
		return this.shopAdminMapper.findByAdminIdAndShopId(id,shop_id);
	}

	@Override
	public void save(ShopAdmin admin) {
		this.shopAdminMapper.insert(admin);
	}

	@Override
	public List<ShopAdmin> findByAdminId(Integer id) {
		return this.shopAdminMapper.findByAdminId(id);
	}

	@Override
	public List<HashMap<String, Object>> findAllShopByCondition(Integer id,String name) {
		return this.shopAdminMapper.findAllShopByCondition(id,name);
	}

	@Override
	public void delete(Integer id, Integer shopId) {
		ShopAdminExample example = new ShopAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andShopIdEqualTo(shopId);
		criteria.andAdminIdEqualTo(id);
		this.shopAdminMapper.deleteByExample(example);
	}
	
	
}
