package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.CityRegion;

public interface CityRegionService {

	List<CityRegion> findByCityId(Integer cityId);

	void insert(CityRegion cityRegion);

    CityRegion findByRegionId(Integer regionId);
}
