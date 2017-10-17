package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ItemSaleCustomerMapper;
import com.b2b.common.domain.ItemSaleCustomer;
import com.b2b.common.domain.ItemSaleCustomerExample;
import com.b2b.common.domain.ItemSaleCustomerExample.Criteria;
import com.b2b.service.ItemSaleCustomerService;

@Service
public class ItemSaleCustomerServiceImpl implements ItemSaleCustomerService{
	
	@Autowired
	ItemSaleCustomerMapper itemSaleCustomerMapper;

	@Override
	public void delete(List<ItemSaleCustomer> datas) {
		if(!datas.isEmpty()){
			for (ItemSaleCustomer itemSaleCustomer : datas) {
				ItemSaleCustomerExample example = new ItemSaleCustomerExample();
				Criteria criteria = example.createCriteria();
				criteria.andItemsaleIdEqualTo(itemSaleCustomer.getItemsaleId());
				criteria.andCustomerIdEqualTo(itemSaleCustomer.getCustomerId());
				this.itemSaleCustomerMapper.deleteByExample(example);
			}
		}
	}
	@Override
	public void insert(List<ItemSaleCustomer> datas) {
		if(!datas.isEmpty()){
			for (ItemSaleCustomer itemSaleCustomer : datas) {
				ItemSaleCustomerExample example = new ItemSaleCustomerExample();
				Criteria criteria = example.createCriteria();
				criteria.andItemsaleIdEqualTo(itemSaleCustomer.getItemsaleId());
				criteria.andCustomerIdEqualTo(itemSaleCustomer.getCustomerId());
				List<ItemSaleCustomer> list = this.itemSaleCustomerMapper.selectByExample(example);
				if(list.isEmpty()){
					this.itemSaleCustomerMapper.insert(itemSaleCustomer);
				}
			}
		}
	}

}
