package com.b2b.service;

import com.b2b.common.domain.AccessToken;

public interface AccessTokenService {

	AccessToken findById(String openId);

	void update(AccessToken token);

	void insert(AccessToken token2);
	
}
