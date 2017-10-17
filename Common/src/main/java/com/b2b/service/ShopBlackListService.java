package com.b2b.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.ShopBlackList;

public interface ShopBlackListService {

	void insert(ShopBlackList list);

	List<HashMap<String, Object>> findByShopId(Integer shop_id);

	void save(List<ShopBlackList> shopBlackList, Integer shop_id);

	ArrayList<Integer> findAllByShopId(Integer customerId);

	ShopBlackList findByShopIdAndItemId(Integer userId, Integer itemId);

	int countByItemId(Integer id);

}
