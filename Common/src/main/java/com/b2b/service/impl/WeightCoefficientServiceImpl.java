package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.WeightCoefficientMapper;
import com.b2b.common.domain.WeightCoefficient;
import com.b2b.common.domain.WeightCoefficientExample;
import com.b2b.common.domain.WeightCoefficientExample.Criteria;
import com.b2b.page.Page;
import com.b2b.service.WeightCoefficientService;

@Service
public class WeightCoefficientServiceImpl implements WeightCoefficientService {
	@Autowired
	private WeightCoefficientMapper weightCoefficientMapper;
	
	@Override
	public Page<WeightCoefficient> findPage(int currentPage, int pageSize) {
		WeightCoefficientExample example = new WeightCoefficientExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		int count = weightCoefficientMapper.countByExample(example);
		if(count ==0){
			return new Page<WeightCoefficient>();
		}
		int start = Page.calStartRow(currentPage, pageSize);
		example.setLimit(pageSize);
		example.setStart(start);
		example.setLimitFlag(true);
		List<WeightCoefficient> list = weightCoefficientMapper.selectByExample(example);
		Page<WeightCoefficient> page = new Page<WeightCoefficient>(currentPage,count,pageSize,list);
		return page;
	}

	@Override
	public WeightCoefficient findById(Integer id) {
		return this.weightCoefficientMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(WeightCoefficient dto) {
		dto.setStatus(Constant.VALID_STATUS);
		this.weightCoefficientMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public void create(WeightCoefficient dto) {
		dto.setStatus(Constant.VALID_STATUS);
		this.weightCoefficientMapper.insert(dto);
	}

	@Override
	public void delete(WeightCoefficient weightCoefficient) {
		this.weightCoefficientMapper.updateByPrimaryKeySelective(weightCoefficient);
	}

}
