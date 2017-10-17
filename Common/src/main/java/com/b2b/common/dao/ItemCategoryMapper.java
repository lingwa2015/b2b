package com.b2b.common.dao;

import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.ItemCategoryExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemCategoryMapper {
    int countByExample(ItemCategoryExample example);

    int deleteByExample(ItemCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemCategory record);

    int insertSelective(ItemCategory record);

    List<ItemCategory> selectByExample(ItemCategoryExample example);

    ItemCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemCategory record, @Param("example") ItemCategoryExample example);

    int updateByExample(@Param("record") ItemCategory record, @Param("example") ItemCategoryExample example);

    int updateByPrimaryKeySelective(ItemCategory record);

    int updateByPrimaryKey(ItemCategory record);

	int countId(Integer id);

	List<HashMap<String, Object>> findAllPage(@Param("start")int start, @Param("pageSize")int pageSize, @Param("cityId")Integer cityId);

	HashMap<String, Object> findWithTwoCat(int id);

	List<ItemCategory> findAllEXOther();

	List<ItemCategory> findCatByParentId(Integer id);

	List<ItemCategory> findCatOrderByScore();

	List<ItemCategory> findAllOneCatsByCityId(@Param("cityId")Integer cityId);

	List<ItemCategory> findAllTwoCatsByCityId(@Param("cityId")Integer cityId);

	List<ItemCategory> findCatByParentIdAndCityId(@Param("categoryId")Integer categoryId,
			@Param("cityId")Integer cityId);

	List<ItemCategory> findAllEXOtherByCityId(@Param("cityId")Integer cityId);

	ItemCategory findOfficeCatIdByCityId(@Param("cityId")Integer cityId);

    ItemCategory findByNameAndCityId(@Param("categoryName")String categoryName, @Param("cityId")Integer cityId, @Param("parentId")Integer parentId);

	List<ItemCategory> findByCityIdAndIsshow(@Param("cityId")Integer cityId, @Param("isshow")Integer isshow);
}