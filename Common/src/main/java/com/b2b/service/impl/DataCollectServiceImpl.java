package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.DataCollectMapper;
import com.b2b.common.domain.DataCollect;
import com.b2b.service.DataCollectService;

@Service
public class DataCollectServiceImpl implements DataCollectService {
	@Autowired
	DataCollectMapper dataCollectMapper;
	
	@Override
	public void save(DataCollect dataCollect) {
		this.dataCollectMapper.insert(dataCollect);
	}
	
}
