package com.b2b.service;

import com.b2b.common.domain.SeniorSet;

public interface SeniorSetService {

	SeniorSet findById(Integer shopId);

	void update(SeniorSet set);

	void create(SeniorSet set);

	void updateUnselective(SeniorSet set);

}
