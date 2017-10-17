package com.b2b.service;

import java.util.ArrayList;
import java.util.List;

import com.b2b.common.domain.City;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.UserCity;

public interface UserCityService {

	void create(UserCity userCity);

	void deleteByUserId(Integer id);

	List<UserCity> findByUserId(int id);

	List<City> findCityByUserId(Integer id);

	void updateCity(PersonUser personUser, ArrayList<UserCity> list, int flag);

}
