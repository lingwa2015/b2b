package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.CustomerCamera;

public interface CustomerCameraService {

	List<CustomerCamera> findByCondition(String userName, String param, Integer cityId);

	void updateNum(Integer cid, String num);

	CustomerCamera findByCid(Integer cid);

	void insert(CustomerCamera camera);

	void updateOpen(Integer cid, Integer openWelcome);

}
