package com.b2b.common.dao;

import com.b2b.common.domain.SnackPackageType;
import com.b2b.common.domain.SnackPackageTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SnackPackageTypeMapper {
    int countByExample(SnackPackageTypeExample example);

    int deleteByExample(SnackPackageTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SnackPackageType record);

    int insertSelective(SnackPackageType record);

    List<SnackPackageType> selectByExample(SnackPackageTypeExample example);

    SnackPackageType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SnackPackageType record, @Param("example") SnackPackageTypeExample example);

    int updateByExample(@Param("record") SnackPackageType record, @Param("example") SnackPackageTypeExample example);

    int updateByPrimaryKeySelective(SnackPackageType record);

    int updateByPrimaryKey(SnackPackageType record);
}