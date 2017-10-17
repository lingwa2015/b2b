package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.ItemVarietyMapper;
import com.b2b.common.domain.ItemVariety;
import com.b2b.common.domain.ItemVarietyExample;
import com.b2b.common.domain.ItemVarietyExample.Criteria;
import com.b2b.page.Page;
import com.b2b.service.ItemVarietyService;

@Service
public class ItemVarietyServiceImpl implements ItemVarietyService {
	@Autowired
	private ItemVarietyMapper itemVarietyMapper;
	
	@Override
	public void create(ItemVariety dto) {
		dto.setStatus(Constant.VALID_STATUS);
		this.itemVarietyMapper.insert(dto);
	}

	@Override
	public void update(ItemVariety dto) {
		dto.setStatus(Constant.VALID_STATUS);
		this.itemVarietyMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public Page<ItemVariety> findPage(int currentPage, int pageSize,Integer cityId) {
		ItemVarietyExample example = new ItemVarietyExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andCityIdEqualTo(cityId);
		int count = itemVarietyMapper.countByExample(example);
		if(count ==0){
			return new Page<ItemVariety>();
		}
		int start = Page.calStartRow(currentPage, pageSize);
		example.setLimit(pageSize);
		example.setStart(start);
		example.setLimitFlag(true);
		List<ItemVariety> list = itemVarietyMapper.selectByExample(example);
		Page<ItemVariety> page = new Page<ItemVariety>(currentPage,count,pageSize,list);
		return page;
	}

	@Override
	public ItemVariety findById(Integer id) {
		return this.itemVarietyMapper.selectByPrimaryKey(id);
	}
		

	@Override
	public void delete(ItemVariety itemVariety) {
		this.itemVarietyMapper.updateByPrimaryKeySelective(itemVariety);
	}

	@Override
	public List<ItemVariety> findAll() {
		ItemVarietyExample example = new ItemVarietyExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		return this.itemVarietyMapper.selectByExample(example);
	}

	@Override
	public ItemVariety findByName(String name, Integer cityId) {
		ItemVarietyExample example = new ItemVarietyExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemvarietyNameEqualTo(name);
		criteria.andCityIdEqualTo(cityId);
		List<ItemVariety> list = this.itemVarietyMapper.selectByExample(example);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

}
