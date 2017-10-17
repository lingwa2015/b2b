package com.b2b.service;

import com.b2b.common.domain.ShopAliUser;

public interface ShopAliUserService {

	ShopAliUser findByOpenId(String userId);

	void save(ShopAliUser user);

	void update(ShopAliUser user);

	ShopAliUser findById(Integer buyerId);

}
