package com.b2b.service;

import com.b2b.common.domain.Token;

public interface TokenService {

	Token findByid(Integer shopId);

	void create(Token token2);

	void update(Token token);

	Token findByIdAndTime(Integer shop_id);

}
