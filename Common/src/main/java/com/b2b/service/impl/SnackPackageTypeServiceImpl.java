package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.AccountLockMapper;
import com.b2b.common.dao.CategoryNumMapper;
import com.b2b.common.dao.SnackPackageTypeMapper;
import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.AccountLockExample;
import com.b2b.common.domain.CategoryNum;
import com.b2b.common.domain.ItemVariety;
import com.b2b.common.domain.ItemVarietyExample;
import com.b2b.common.domain.SnackPackageType;
import com.b2b.common.domain.SnackPackageTypeExample;
import com.b2b.common.domain.WeightCoefficient;
import com.b2b.common.domain.SnackPackageTypeExample.Criteria;
import com.b2b.common.domain.TranSum;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.WeightCoefficientExample;
import com.b2b.dto.TranSumDto;
import com.b2b.page.Page;
import com.b2b.service.AccountLockService;
import com.b2b.service.CategoryNumService;
import com.b2b.service.SnackPackageTypeService;

@Service
public class SnackPackageTypeServiceImpl implements SnackPackageTypeService {
	
	@Autowired
	SnackPackageTypeMapper snackPackageTypeMapper;
	
	@Override
	public Page<SnackPackageType> findPage()
	{
		SnackPackageTypeExample example = new SnackPackageTypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		int count = snackPackageTypeMapper.countByExample(example);
		if(count ==0){
			return new Page<SnackPackageType>();
		}
		List<SnackPackageType> list = snackPackageTypeMapper.selectByExample(example);
		Page<SnackPackageType> page = new Page<SnackPackageType>(1,count,count,list);
		return page;
	}

	@Override
	public void insert(SnackPackageType snackPackageType) {
		// TODO 自动生成的方法存根
		snackPackageType.setStatus(Constant.VALID_STATUS);
		snackPackageTypeMapper.insert(snackPackageType);
	}
	
	@Override
	public SnackPackageType findById(int id){
		// TODO 自动生成的方法存根
		return snackPackageTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SnackPackageType> selectAll() {
		// TODO 自动生成的方法存根
		SnackPackageTypeExample example = new SnackPackageTypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		List<SnackPackageType> list = snackPackageTypeMapper.selectByExample(example);
		return list;
	}

	@Override
	public void updateSnackPackageType(SnackPackageType snackPackageType) {
		snackPackageType.setStatus(Constant.VALID_STATUS);
		snackPackageTypeMapper.updateByPrimaryKeySelective(snackPackageType);
	}
	
	@Override
	public void deleteSnackPackageType(SnackPackageType snackPackageType) {
		snackPackageTypeMapper.updateByPrimaryKeySelective(snackPackageType);
	}
}
