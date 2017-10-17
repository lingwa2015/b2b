package com.b2b.service;

import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.BlackWhiteListCategory;

public interface BlackwhitelistCategoryService {

	BlackWhiteListCategory findBlackByItemId(Integer customerId, int i,
			Integer categoryId);

	void create(BlackWhiteListCategory blackWhiteListCategory);

	List<HashMap<String, Object>> findAll(Integer id,int bw);

	void deleteAll(int blackId);

	void deleteById(int id);

}
