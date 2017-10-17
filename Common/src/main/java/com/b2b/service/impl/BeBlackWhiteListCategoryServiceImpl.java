package com.b2b.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.BeBlackWhiteListCategoryMapper;
import com.b2b.common.domain.BeBlackWhiteListCategory;
import com.b2b.common.domain.BeBlackWhiteListCategoryExample;
import com.b2b.common.domain.BeBlackWhiteListCategoryExample.Criteria;
import com.b2b.service.BeBlackWhiteListCategoryService;

@Service
public class BeBlackWhiteListCategoryServiceImpl implements
		BeBlackWhiteListCategoryService {
	@Autowired
	private BeBlackWhiteListCategoryMapper beBlackWhiteListCategoryMapper;
	
	@Override
	public BeBlackWhiteListCategory findBlackByCategoryId(int customerId, int i,
			Integer categoryId) {
		return this.beBlackWhiteListCategoryMapper.findByCategoryId(customerId,i,categoryId);
	}

	@Override
	public void create(BeBlackWhiteListCategory blackWhiteListCategory) {
		this.beBlackWhiteListCategoryMapper.insert(blackWhiteListCategory);
	}

	@Override
	public List<HashMap<String, Object>> findAll(Integer id, int i) {
		return this.beBlackWhiteListCategoryMapper.findAll(id,i);
	}

	@Override
	public void deleteAll(int blackId) {
		BeBlackWhiteListCategoryExample example = new BeBlackWhiteListCategoryExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andBeBlackwhiteIdEqualTo(blackId);
		this.beBlackWhiteListCategoryMapper.deleteByExample(example);
	}

	@Override
	public void deleteById(int id) {
		this.beBlackWhiteListCategoryMapper.deleteByPrimaryKey(id);
	}

}
