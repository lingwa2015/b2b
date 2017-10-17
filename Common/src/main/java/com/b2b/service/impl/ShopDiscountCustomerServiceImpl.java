package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopDiscountCustomerMapper;
import com.b2b.common.domain.ShopDiscountCustomer;
import com.b2b.common.domain.ShopDiscountCustomerExample;
import com.b2b.common.domain.ShopDiscountCustomerExample.Criteria;
import com.b2b.service.ShopDiscountCustomerService;

@Service
public class ShopDiscountCustomerServiceImpl implements ShopDiscountCustomerService {
	
	@Autowired
	ShopDiscountCustomerMapper shopDiscountCustomerMapper;
	
	@Override
	public void insert(List<ShopDiscountCustomer> datas) {
		if(!datas.isEmpty()){
			for (ShopDiscountCustomer shopDiscountCustomer : datas) {
				ShopDiscountCustomerExample example = new ShopDiscountCustomerExample();
				Criteria criteria = example.createCriteria();
				criteria.andShopDiscountIdEqualTo(shopDiscountCustomer.getShopDiscountId());
				criteria.andCustomerIdEqualTo(shopDiscountCustomer.getCustomerId());
				List<ShopDiscountCustomer> list = this.shopDiscountCustomerMapper.selectByExample(example);
				if(list.isEmpty()){
					this.shopDiscountCustomerMapper.insert(shopDiscountCustomer);
				}
			}
		}
	}

	@Override
	public void delete(List<ShopDiscountCustomer> datas) {
		if(!datas.isEmpty()){
			for (ShopDiscountCustomer shopDiscountCustomer : datas) {
				ShopDiscountCustomerExample example = new ShopDiscountCustomerExample();
				Criteria criteria = example.createCriteria();
				criteria.andShopDiscountIdEqualTo(shopDiscountCustomer.getShopDiscountId());
				criteria.andCustomerIdEqualTo(shopDiscountCustomer.getCustomerId());
				this.shopDiscountCustomerMapper.deleteByExample(example);
			}
		}
	}

}
