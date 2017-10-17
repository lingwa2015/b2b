package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.RegionMapper;
import com.b2b.common.domain.Region;
import com.b2b.common.domain.RegionExample;
import com.b2b.common.domain.RegionExample.Criteria;
import com.b2b.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionMapper regionMapper;
	
	@Override
	public List<Region> findAll() {
		RegionExample example = new RegionExample();
		Criteria criteria = example.createCriteria();
		criteria.andRegionIdNotEqualTo(1);
		return this.regionMapper.selectByExample(example);
	}
	
}
