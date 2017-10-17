package com.b2b.service;

import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.ItemCategory;
import com.b2b.page.Page;

public interface ItemCategoryService {

	public void create(ItemCategory dto);

	public void update(ItemCategory dto);
	
	public String delete(int id);
	
	public ItemCategory findById(int id);
	
	public List<ItemCategory> findByBusinessId(int businessId);
	
	//shandiao
	public List<ItemCategory> findAll();
	
	public List<ItemCategory> findAll(int catId);
	
	public Page<ItemCategory> findPage(ItemCategory itemCat , int currentPage ,int pageSize,int businessId);
	
	public String check(ItemCategory dto);
	
	//shandiao 
	public List<ItemCategory> findByParentId(int businessId);
	
	public List<ItemCategory> findByLevelCatId(int businessId,int catId);

	public int countId(Integer id);

	public Page<HashMap<String, Object>> findAllPage(ItemCategory itemCategory,
			int currentPage, int pageSize, Integer businessId, Integer cityId);

	public HashMap<String, Object> findWithTwoCat(int id);

	public List<ItemCategory> findAllEXOther();
	//shandiao
	public List<ItemCategory> findCatByParentId(Integer id);

	public List<ItemCategory> findCatOrderByScore();

	public List<ItemCategory> findAllOneCatsByCityId(Integer cityId);

	public List<ItemCategory> findAllTwoCatsByCityId(Integer cityId);

	public List<ItemCategory> findCatByParentIdAndCityId(Integer categoryId,
			Integer cityId);

	public List<ItemCategory> findAllEXOtherByCityId(Integer cityId);

	public ItemCategory findOfficeCatIdByCityId(Integer cityId);


	ItemCategory findByNameAndCityId(String categoryName, Integer cityId, Integer parentId);

	public List<ItemCategory> findByCityIdAndIsshow(Integer cityId, Integer isshow);
}
