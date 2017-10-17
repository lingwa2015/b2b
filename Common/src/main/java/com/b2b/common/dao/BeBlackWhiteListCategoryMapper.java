package com.b2b.common.dao;

import com.b2b.common.domain.BeBlackWhiteListCategory;
import com.b2b.common.domain.BeBlackWhiteListCategoryExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BeBlackWhiteListCategoryMapper {
    int countByExample(BeBlackWhiteListCategoryExample example);

    int deleteByExample(BeBlackWhiteListCategoryExample example);

    int deleteByPrimaryKey(Integer bbwlcId);

    int insert(BeBlackWhiteListCategory record);

    int insertSelective(BeBlackWhiteListCategory record);

    List<BeBlackWhiteListCategory> selectByExample(BeBlackWhiteListCategoryExample example);

    BeBlackWhiteListCategory selectByPrimaryKey(Integer bbwlcId);

    int updateByExampleSelective(@Param("record") BeBlackWhiteListCategory record, @Param("example") BeBlackWhiteListCategoryExample example);

    int updateByExample(@Param("record") BeBlackWhiteListCategory record, @Param("example") BeBlackWhiteListCategoryExample example);

    int updateByPrimaryKeySelective(BeBlackWhiteListCategory record);

    int updateByPrimaryKey(BeBlackWhiteListCategory record);

	BeBlackWhiteListCategory findByCategoryId(@Param("customerId")int customerId, @Param("flag")int flag,
			@Param("categoryId")Integer categoryId);

	List<HashMap<String, Object>> findAll(@Param("id")Integer id, @Param("flag")int flag);
}