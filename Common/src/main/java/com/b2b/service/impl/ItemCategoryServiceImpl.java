package com.b2b.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.ItemCategoryMapper;
import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.ItemCategoryExample;
import com.b2b.common.domain.ItemCategoryExample.Criteria;
import com.b2b.page.Page;
import com.b2b.service.ItemCategoryService;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

	@Autowired
	ItemCategoryMapper itemCategoryMapper;
	
	@Override
	public void create(ItemCategory dto) {
		dto.setStatus(Constant.VALID_STATUS);
		itemCategoryMapper.insert(dto);
	}

	@Override
	public void update(ItemCategory dto) {
		dto.setStatus(Constant.VALID_STATUS);
		itemCategoryMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public String delete(int id) {
		ItemCategory dto = this.findById(id);
		
		dto.setStatus(Constant.DELETE_STATUS);
		itemCategoryMapper.updateByPrimaryKey(dto);
		return null;
	}

	@Override
	public ItemCategory findById(int id) {
		return itemCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ItemCategory> findAll() {
		ItemCategoryExample example = new ItemCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andParentIdIsNull();
		return itemCategoryMapper.selectByExample(example);
	}
	
	@Override
	public List<ItemCategory> findAll(int catId) {
		ItemCategoryExample example = new ItemCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andIdEqualTo(catId);
		criteria.andParentIdIsNull();
		return itemCategoryMapper.selectByExample(example);
	}

	@Override
	public List<ItemCategory> findByBusinessId(int businessId) {
		ItemCategoryExample example = new ItemCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andBusiness_idEqualTo(businessId);
		criteria.andParentIdIsNull();
		return itemCategoryMapper.selectByExample(example);
	}
	
	public List<ItemCategory> findByLevelCatId(int businessId,int catId)
	{
		ItemCategoryExample example = new ItemCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS); 
		criteria.andIdEqualTo(catId);
		if(businessId!=0){
		criteria.andBusiness_idEqualTo(businessId);
		}
		criteria.andParentIdIsNotNull();
		return itemCategoryMapper.selectByExample(example);
	}

	@Override
	public List<ItemCategory> findByParentId(int businessId) {
		ItemCategoryExample example = new ItemCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS); 
		if(businessId!=0){
		criteria.andBusiness_idEqualTo(businessId);
		}
		criteria.andParentIdIsNotNull();
		return itemCategoryMapper.selectByExample(example);
	}

	@Override
	public Page<ItemCategory> findPage(ItemCategory itemCat, int currentPage,
			int pageSize,int businessId) {
		ItemCategoryExample example = new ItemCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		//criteria.andParentIdIsNull();
		if(businessId!=0)
		criteria.andBusiness_idEqualTo(businessId);
		
		int count = itemCategoryMapper.countByExample(example);
		if(count ==0){
			return new Page<ItemCategory>();
		}
		
		int start = Page.calStartRow(currentPage, pageSize);
		example.setLimit(pageSize);
		example.setStart(start);
		example.setLimitFlag(true);
		
		List<ItemCategory> list = itemCategoryMapper.selectByExample(example);
		Page<ItemCategory> page = new Page<ItemCategory>(currentPage,count,pageSize,list);
		
		return page;
	}

	@Override
	public String check(ItemCategory dto) {
		return null;
	}

	@Override
	public int countId(Integer id) {
		return this.itemCategoryMapper.countId(id);
	}

	@Override
	public Page<HashMap<String, Object>> findAllPage(ItemCategory itemCategory,
			int currentPage, int pageSize, Integer businessId,Integer cityId) {
		ItemCategoryExample example = new ItemCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		//criteria.andParentIdIsNull();
		if(businessId!=0)
		criteria.andBusiness_idEqualTo(businessId);
		
		criteria.andCityIdEqualTo(cityId);
		int count = itemCategoryMapper.countByExample(example);
		if(count ==0){
			return new Page<HashMap<String, Object>>();
		}
		
		int start = Page.calStartRow(currentPage, pageSize);
		List<HashMap<String, Object>> list = itemCategoryMapper.findAllPage(start,pageSize,cityId);
		
		//List<ItemCategory> list = itemCategoryMapper.selectByExample(example);
		Page<HashMap<String, Object>> page = new Page<HashMap<String, Object>>(currentPage,count,pageSize,list);
		
		return page;
	}

	@Override
	public HashMap<String, Object> findWithTwoCat(int id) {
		return this.itemCategoryMapper.findWithTwoCat(id);
	}

	@Override
	public List<ItemCategory> findAllEXOther() {
		return this.itemCategoryMapper.findAllEXOther();
	}

	@Override
	public List<ItemCategory> findCatByParentId(Integer id) {
		return this.itemCategoryMapper.findCatByParentId(id);
	}

	@Override
	public List<ItemCategory> findCatOrderByScore() {
		return this.itemCategoryMapper.findCatOrderByScore();
	}

	@Override
	public List<ItemCategory> findAllOneCatsByCityId(Integer cityId) {
		return this.itemCategoryMapper.findAllOneCatsByCityId(cityId);
	}

	@Override
	public List<ItemCategory> findAllTwoCatsByCityId(Integer cityId) {
		return this.itemCategoryMapper.findAllTwoCatsByCityId(cityId);
	}

	@Override
	public List<ItemCategory> findCatByParentIdAndCityId(Integer categoryId,
			Integer cityId) {
		return this.itemCategoryMapper.findCatByParentIdAndCityId(categoryId,cityId);
	}

	@Override
	public List<ItemCategory> findAllEXOtherByCityId(Integer cityId) {
		return this.itemCategoryMapper.findAllEXOtherByCityId(cityId);
	}

	@Override
	public ItemCategory findOfficeCatIdByCityId(Integer cityId) {
		return this.itemCategoryMapper.findOfficeCatIdByCityId(cityId);
	}

	@Override
	public ItemCategory findByNameAndCityId(String categoryName, Integer cityId, Integer parentId) {
		return this.itemCategoryMapper.findByNameAndCityId(categoryName, cityId, parentId);
	}

	@Override
	public List<ItemCategory> findByCityIdAndIsshow(Integer cityId, Integer isshow) {
		return this.itemCategoryMapper.findByCityIdAndIsshow(cityId,isshow);
	}

}
