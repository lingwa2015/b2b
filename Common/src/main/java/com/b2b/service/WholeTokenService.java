package com.b2b.service;

import com.b2b.common.domain.WholeToken;

public interface WholeTokenService {

	WholeToken findByIdOneHour(int id);

	WholeToken findById(int i);

	void insert(WholeToken token2);

	void update(WholeToken t);

}
