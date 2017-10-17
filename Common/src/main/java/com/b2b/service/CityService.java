package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.City;

public interface CityService {

	List<City> findAllOpenCity();

	City findById(Integer cityId);

}
