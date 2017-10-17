package com.b2b.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.NewCustomerActivityMapper;
import com.b2b.common.domain.NewCustomerActivity;
import com.b2b.service.NewCustomerActivityService;

@Service
public class NewCustomerActivityServiceImpl implements NewCustomerActivityService {
	@Autowired
	NewCustomerActivityMapper newCustomerActivityMapper;
	
	@Override
	public void save(NewCustomerActivity dto) {
		this.newCustomerActivityMapper.insert(dto);
	}

	@Override
	public List<NewCustomerActivity> findByCondition(Integer cityId) {
		return this.newCustomerActivityMapper.findByCondition(cityId);
	}

	@Override
	public void delete(NewCustomerActivity dto) {
		this.newCustomerActivityMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public List<NewCustomerActivity> findByDateAndCityId(Date date, Integer cityId) {
		return this.newCustomerActivityMapper.findByDateAndCityId(date,cityId);
	}

	@Override
	public List<NewCustomerActivity> findByConditionAndCityId(Date startTime, Date endTime, String userName,
			Integer reseauId, Integer cityId) {
		return this.newCustomerActivityMapper.findByConditionAndCityId(startTime,endTime,userName,reseauId,cityId);
	}

	@Override
	public Long findTotalFeeByConditionAndCityId(Date startTime, Date endTime, String userName, Integer reseauId,
			Integer cityId) {
		return this.newCustomerActivityMapper.findTotalFeeByConditionAndCityId(startTime,endTime,userName,reseauId,cityId);
	}

	@Override
	public List<NewCustomerActivity> findByStartAndEndTimeAndCityId(Date startTime, Date endTime, Integer cityId) {
		return this.newCustomerActivityMapper.findByStartAndEndTimeAndCityId(startTime,endTime,cityId);
	}

}
