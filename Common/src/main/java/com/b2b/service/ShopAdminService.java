package com.b2b.service;


import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.ShopAdmin;

public interface ShopAdminService {

	ShopAdmin findByAdminIdAndShopId(Integer id, Integer shop_id);

	void save(ShopAdmin admin);

	List<ShopAdmin> findByAdminId(Integer id);

	List<HashMap<String, Object>> findAllShopByCondition(Integer id, String name);

	void delete(Integer id, Integer shopId);

}
