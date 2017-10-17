package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ItemSupplierMapper;
import com.b2b.common.domain.ItemSupplier;
import com.b2b.common.domain.ItemSupplierExample;
import com.b2b.common.domain.ItemSupplierExample.Criteria;
import com.b2b.service.ItemSupplierService;
import com.b2b.service.SupplierService;

@Service
public class ItemSupplierServiceImpl implements ItemSupplierService {
	@Autowired
	private ItemSupplierMapper itemSupplierMapper;
	
	@Autowired
	private SupplierService supplierService;
	@Override
	public void deleteByItemId(Integer id) {
		ItemSupplierExample example = new ItemSupplierExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(id);
		this.itemSupplierMapper.deleteByExample(example);
	}

	@Override
	public void save(ItemSupplier itemSupplier) {
		this.itemSupplierMapper.insert(itemSupplier);
	}

	@Override
	public Integer save(List<ItemSupplier> itemSupplier, Integer suppilerId) {
		ItemSupplierExample example = new ItemSupplierExample();
		com.b2b.common.domain.ItemSupplierExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andSuppilerIdEqualTo(suppilerId);
		this.itemSupplierMapper.deleteByExample(example);
		for (ItemSupplier itemSupplier2 : itemSupplier) {
			ItemSupplier exist = this.itemSupplierMapper.findByItemIdAndSupperId(suppilerId,itemSupplier2.getItemId());
			if(exist!=null){
				return itemSupplier2.getItemId();
			}else{
				this.itemSupplierMapper.insert(itemSupplier2);
			}
		}
		return null;
		
	}

}
