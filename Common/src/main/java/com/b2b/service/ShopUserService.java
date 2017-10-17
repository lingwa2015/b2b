package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.ShopUser;
import com.b2b.common.domain.WXUser;

public interface ShopUserService {

	ShopUser findByOpenId(String openId);

	void create(ShopUser wxUser);

	void update(ShopUser user);

	ShopUser findById(Integer id);

	List<ShopUser> findManagerByShopid(Integer shopId);

	void updateManage(Integer id);

	List<ShopUser> findAllSuperAdmin();

	void changeAdminToComon(Integer id);

}
