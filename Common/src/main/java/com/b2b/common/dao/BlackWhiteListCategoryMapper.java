package com.b2b.common.dao;

import com.b2b.common.domain.BlackWhiteListCategory;
import com.b2b.common.domain.BlackWhiteListCategoryExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BlackWhiteListCategoryMapper {
    int countByExample(BlackWhiteListCategoryExample example);

    int deleteByExample(BlackWhiteListCategoryExample example);

    int deleteByPrimaryKey(Integer bwlcId);

    int insert(BlackWhiteListCategory record);

    int insertSelective(BlackWhiteListCategory record);

    List<BlackWhiteListCategory> selectByExample(BlackWhiteListCategoryExample example);

    BlackWhiteListCategory selectByPrimaryKey(Integer bwlcId);

    int updateByExampleSelective(@Param("record") BlackWhiteListCategory record, @Param("example") BlackWhiteListCategoryExample example);

    int updateByExample(@Param("record") BlackWhiteListCategory record, @Param("example") BlackWhiteListCategoryExample example);

    int updateByPrimaryKeySelective(BlackWhiteListCategory record);

    int updateByPrimaryKey(BlackWhiteListCategory record);

	BlackWhiteListCategory findByItemId(@Param("customerId")Integer customerId,@Param("flag") int flag,
			@Param("categoryId")Integer categoryId);

	List<HashMap<String, Object>> findAll(@Param("id")Integer id,@Param("bw")int bw);
}