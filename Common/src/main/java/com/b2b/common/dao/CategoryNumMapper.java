package com.b2b.common.dao;

import com.b2b.common.domain.CategoryNum;
import com.b2b.common.domain.CategoryNumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CategoryNumMapper {
    int countByExample(CategoryNumExample example);

    int deleteByExample(CategoryNumExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CategoryNum record);

    int insertSelective(CategoryNum record);

    List<CategoryNum> selectByExample(CategoryNumExample example);

    CategoryNum selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CategoryNum record, @Param("example") CategoryNumExample example);

    int updateByExample(@Param("record") CategoryNum record, @Param("example") CategoryNumExample example);

    int updateByPrimaryKeySelective(CategoryNum record);

    int updateByPrimaryKey(CategoryNum record);
}