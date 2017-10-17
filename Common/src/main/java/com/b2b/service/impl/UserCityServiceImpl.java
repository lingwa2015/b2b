package com.b2b.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.UserCityMapper;
import com.b2b.common.domain.City;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.UserCity;
import com.b2b.common.domain.UserCityExample;
import com.b2b.common.domain.UserCityExample.Criteria;
import com.b2b.service.UserCityService;
import com.b2b.service.UserService;

@Service
public class UserCityServiceImpl implements UserCityService{
	@Autowired
	UserCityMapper userCityMapper;
	@Autowired
	UserService userService;
	@Override
	public void create(UserCity userCity) {
		this.userCityMapper.insert(userCity);
	}

	@Override
	public void deleteByUserId(Integer id) {
		UserCityExample example = new UserCityExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(id);
		this.userCityMapper.deleteByExample(example);
	}

	@Override
	public List<UserCity> findByUserId(int id) {
		UserCityExample example = new UserCityExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(id);
		return this.userCityMapper.selectByExample(example);
	}

	@Override
	public List<City> findCityByUserId(Integer id) {
		return this.userCityMapper.findCityByUserId(id);
	}

	@Override
	public void updateCity(PersonUser personUser, ArrayList<UserCity> list,
			int flag) {
		UserCityExample example = new UserCityExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(personUser.getId());
		this.userCityMapper.deleteByExample(example);
		for (UserCity userCity : list) {
			this.userCityMapper.insert(userCity);
		}
		if(flag==0){
			if(list.size()>0){
				personUser.setCityId(list.get(0).getCityId());
			}else{
				personUser.setCityId(null);
			}
			this.userService.update(personUser);
		}
	}
	
	
}
