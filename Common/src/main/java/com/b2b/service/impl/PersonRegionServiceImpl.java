package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.PersonRegionMapper;
import com.b2b.common.domain.PersonRegion;
import com.b2b.common.domain.PersonRegionExample;
import com.b2b.common.domain.PersonRegionExample.Criteria;
import com.b2b.common.domain.PersonUser;
import com.b2b.service.PersonRegionService;

@Service
public class PersonRegionServiceImpl implements PersonRegionService {
	@Autowired
	private PersonRegionMapper personRegionMapper;
	
	@Override
	public void insert(PersonRegion personRegion) {
		personRegionMapper.insert(personRegion);
	}

	@Override
	public void delete(PersonRegionExample example) {
		this.personRegionMapper.deleteByExample(example);
	}

	@Override
	public List<PersonRegion> findByPID(int id) {
		PersonRegionExample example = new PersonRegionExample();
		Criteria criteria = example.createCriteria();
		criteria.andPIdEqualTo(id);
		return personRegionMapper.selectByExample(example);
	}

	@Override
	public List<PersonRegion> findByRegion(String region) {
		PersonRegionExample example = new PersonRegionExample();
		Criteria criteria = example.createCriteria();
		criteria.andRegionEqualTo(region);
		return this.personRegionMapper.selectByExample(example);
	}

	@Override
	public List<PersonUser> findPersonuserByRegionAndPost(String region) {
		return this.personRegionMapper.findPersonuserByRegionAndPost(region);
	}
	
}
