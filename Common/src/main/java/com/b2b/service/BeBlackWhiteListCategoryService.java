package com.b2b.service;

import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.BeBlackWhiteListCategory;

public interface BeBlackWhiteListCategoryService {

	BeBlackWhiteListCategory findBlackByCategoryId(int customerId, int i,
			Integer categoryId);

	void create(BeBlackWhiteListCategory blackWhiteListCategory);

	List<HashMap<String, Object>> findAll(Integer id, int i);

	void deleteAll(int blackId);

	void deleteById(int id);

}
