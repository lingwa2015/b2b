package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.ItemVariety;
import com.b2b.page.Page;

public interface ItemVarietyService {

	void create(ItemVariety dto);

	void update(ItemVariety dto);

	Page<ItemVariety> findPage(int currentPage, int pageSize, Integer cityId);

	ItemVariety findById(Integer id);

	void delete(ItemVariety itemVariety);

	List<ItemVariety> findAll();

	ItemVariety findByName(String name, Integer cityId);

}
