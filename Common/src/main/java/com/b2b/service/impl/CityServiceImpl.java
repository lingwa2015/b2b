package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.CityMapper;
import com.b2b.common.domain.City;
import com.b2b.common.domain.CityExample;
import com.b2b.common.domain.CityExample.Criteria;
import com.b2b.service.CityService;

@Service
public class CityServiceImpl implements CityService{
	@Autowired
	CityMapper cityMapper;
	
	@Override
	public List<City> findAllOpenCity() {
		return this.cityMapper.findAllOpenCity();
	}

	@Override
	public City findById(Integer cityId) {
		return this.cityMapper.selectByPrimaryKey(cityId);
	}
	
}
