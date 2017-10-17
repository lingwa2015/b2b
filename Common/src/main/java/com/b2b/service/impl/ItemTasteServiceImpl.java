package com.b2b.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ItemTasteMapper;
import com.b2b.common.domain.ItemTaste;
import com.b2b.common.domain.ItemTasteExample;
import com.b2b.common.domain.ItemTasteExample.Criteria;
import com.b2b.service.ItemTasteService;

@Service
public class ItemTasteServiceImpl implements ItemTasteService {
	@Autowired
	private ItemTasteMapper itemTasteMapper;
	
	@Override
	public ItemTaste findByNameAndItemId(Integer id, String taste,String barcode) {
		ItemTasteExample example = new ItemTasteExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andItemIdEqualTo(id);
		if(StringUtils.isEmpty(taste)){
			createCriteria.andTasteNameIsNull();
		}else{
			createCriteria.andTasteNameEqualTo(taste);
		}
		if(StringUtils.isEmpty(barcode)){
			createCriteria.andBarcodeIsNull();
		}else{
			createCriteria.andBarcodeEqualTo(barcode);
		}
		List<ItemTaste> list = this.itemTasteMapper.selectByExample(example);
		if(list.isEmpty()){
			return null; 
		}
		return list.get(0);
	}

	@Override
	public void created(ItemTaste itemtaste) {
		this.itemTasteMapper.insert(itemtaste);
	}

	@Override
	public void deleteAllByItemId(Integer id) {
		ItemTasteExample example = new ItemTasteExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andItemIdEqualTo(id);
		this.itemTasteMapper.deleteByExample(example);
	}

	@Override
	public List<ItemTaste> findByItemId(int id) {
		ItemTasteExample example = new ItemTasteExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andItemIdEqualTo(id);
		return this.itemTasteMapper.selectByExample(example);
	}

	@Override
	public List<ItemTaste> findAll() {
		return this.itemTasteMapper.selectByExample(null);
	}

}
