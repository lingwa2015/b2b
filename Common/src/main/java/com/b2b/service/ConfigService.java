package com.b2b.service;

import com.b2b.common.domain.Config;
import com.b2b.enums.ConfigEnum;

public interface ConfigService {

	public Config findByName(String name);
	
	public String findByName(ConfigEnum name);
}
