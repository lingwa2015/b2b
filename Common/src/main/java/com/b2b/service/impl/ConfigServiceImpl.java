package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ConfigMapper;
import com.b2b.common.domain.Config;
import com.b2b.enums.ConfigEnum;
import com.b2b.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	ConfigMapper configMapper;
	
	@Override
	public Config findByName(String name) {
		Config config = configMapper.selectByPrimaryKey(name);
		return config;
	}

	@Override
	public String findByName(ConfigEnum name) {
		Config config = this.findByName(name.getName());
		if(config!=null){
			return config.getContent();
		}
		return null;
	}

}
