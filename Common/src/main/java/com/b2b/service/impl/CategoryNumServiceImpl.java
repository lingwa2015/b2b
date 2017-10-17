package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.AccountLockMapper;
import com.b2b.common.dao.CategoryNumMapper;
import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.AccountLockExample;
import com.b2b.common.domain.CategoryNum;
import com.b2b.common.domain.TranSum;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.AccountLockExample.Criteria;
import com.b2b.dto.TranSumDto;
import com.b2b.service.AccountLockService;
import com.b2b.service.CategoryNumService;

@Service
public class CategoryNumServiceImpl implements CategoryNumService {
	
	@Autowired
	CategoryNumMapper categoryNumMapper;
	
	@Override
	public void insert(List<CategoryNum> categoryNum){
		deleteByExample();
		for(CategoryNum num:categoryNum){
			categoryNumMapper.insert(num);
		}
	}
	
	@Override
	public void deleteByExample(){
		categoryNumMapper.deleteByExample(null);
	}
	
	@Override
	public List<CategoryNum> selectAll(){
		return categoryNumMapper.selectByExample(null);
	}
	
}
