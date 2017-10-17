package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.CustomerCameraMapper;
import com.b2b.common.domain.CustomerCamera;
import com.b2b.common.domain.CustomerCameraExample;
import com.b2b.service.CustomerCameraService;

@Service
public class CustomerCameraServiceImpl implements CustomerCameraService {
	@Autowired
	CustomerCameraMapper customerCameraMapper;
	
	@Override
	public List<CustomerCamera> findByCondition(String userName, String param,Integer cityId) {
		return this.customerCameraMapper.findByCondition(userName,param,cityId);
	}

	@Override
	public void updateNum(Integer cid, String num) {
		CustomerCamera camera = new CustomerCamera();
		camera.setCid(cid);
		camera.setNum(num);
		this.customerCameraMapper.updateByPrimaryKey(camera);
	}

	@Override
	public CustomerCamera findByCid(Integer cid) {
		return this.customerCameraMapper.selectByPrimaryKey(cid);
	}

	@Override
	public void insert(CustomerCamera camera) {
		this.customerCameraMapper.insertSelective(camera);
	}

	@Override
	public void updateOpen(Integer cid, Integer openWelcome) {
		CustomerCamera camera = new CustomerCamera();
		camera.setCid(cid);
		camera.setOpenWelcome(openWelcome);
		this.customerCameraMapper.updateByPrimaryKeySelective(camera);
	}

}
