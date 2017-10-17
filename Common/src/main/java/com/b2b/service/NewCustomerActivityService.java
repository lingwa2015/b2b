package com.b2b.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.NewCustomerActivity;

public interface NewCustomerActivityService {

	void save(NewCustomerActivity dto);

	List<NewCustomerActivity> findByCondition(Integer cityId);

	void delete(NewCustomerActivity dto);

	List<NewCustomerActivity> findByDateAndCityId(Date contractDate, Integer cityId);

	List<NewCustomerActivity> findByConditionAndCityId(Date startTime, Date endTime, String userName,
			Integer reseauId, Integer cityId);

	Long findTotalFeeByConditionAndCityId(Date startTime, Date endTime, String userName, Integer reseauId,
			Integer cityId);

	List<NewCustomerActivity> findByStartAndEndTimeAndCityId(Date startTime, Date endTime, Integer cityId);

}
