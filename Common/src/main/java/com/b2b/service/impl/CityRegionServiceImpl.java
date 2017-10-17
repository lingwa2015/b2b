package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.CityRegionMapper;
import com.b2b.common.domain.CityRegion;
import com.b2b.common.domain.CityRegionExample;
import com.b2b.common.domain.CityRegionExample.Criteria;
import com.b2b.service.CityRegionService;

@Service
public class CityRegionServiceImpl implements CityRegionService {
	@Autowired
	private CityRegionMapper cityRegionMapper;
	
	@Override
	public List<CityRegion> findByCityId(Integer cityId) {
		return this.cityRegionMapper.findByCityId(cityId);
	}

	@Override
	public void insert(CityRegion cityRegion) {
		this.cityRegionMapper.insert(cityRegion);
	}

	@Override
	public CityRegion findByRegionId(Integer regionId) {
		return this.cityRegionMapper.selectByPrimaryKey(regionId);
	}

}
