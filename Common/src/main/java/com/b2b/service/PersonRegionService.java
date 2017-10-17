package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.PersonRegion;
import com.b2b.common.domain.PersonRegionExample;
import com.b2b.common.domain.PersonUser;

public interface PersonRegionService {

	void insert(PersonRegion personRegion);

	void delete(PersonRegionExample example);

	List<PersonRegion> findByPID(int id);

	List<PersonRegion> findByRegion(String region);

	List<PersonUser> findPersonuserByRegionAndPost(String region);

}
