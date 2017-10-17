package com.b2b.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.BlackWhiteListCategoryMapper;
import com.b2b.common.domain.BlackWhiteListCategory;
import com.b2b.common.domain.BlackWhiteListCategoryExample;
import com.b2b.common.domain.BlackWhiteListCategoryExample.Criteria;
import com.b2b.service.BlackwhitelistCategoryService;

@Service
public class BlackwhitelistCategoryServiceImpl implements
		BlackwhitelistCategoryService {
	@Autowired
	private BlackWhiteListCategoryMapper blackWhiteListCategoryMapper;
	
	@Override
	public BlackWhiteListCategory findBlackByItemId(Integer customerId, int i,
			Integer categoryId) {
		return this.blackWhiteListCategoryMapper.findByItemId(customerId,i,categoryId);
	}

	@Override
	public void create(BlackWhiteListCategory blackWhiteListCategory) {
		this.blackWhiteListCategoryMapper.insert(blackWhiteListCategory);
	}

	@Override
	public List<HashMap<String, Object>> findAll(Integer id,int bw) {
		return this.blackWhiteListCategoryMapper.findAll(id,bw);
	}

	@Override
	public void deleteAll(int blackId) {
		BlackWhiteListCategoryExample example = new BlackWhiteListCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andBlackwhiteIdEqualTo(blackId);
		this.blackWhiteListCategoryMapper.deleteByExample(example);
	}

	@Override
	public void deleteById(int id) {
		this.blackWhiteListCategoryMapper.deleteByPrimaryKey(id);
	}

}
