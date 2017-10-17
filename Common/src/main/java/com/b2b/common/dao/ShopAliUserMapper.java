package com.b2b.common.dao;

import com.b2b.common.domain.ShopAliUser;
import com.b2b.common.domain.ShopAliUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShopAliUserMapper {
    int countByExample(ShopAliUserExample example);

    int deleteByExample(ShopAliUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopAliUser record);

    int insertSelective(ShopAliUser record);

    List<ShopAliUser> selectByExample(ShopAliUserExample example);

    ShopAliUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopAliUser record, @Param("example") ShopAliUserExample example);

    int updateByExample(@Param("record") ShopAliUser record, @Param("example") ShopAliUserExample example);

    int updateByPrimaryKeySelective(ShopAliUser record);

    int updateByPrimaryKey(ShopAliUser record);

	ShopAliUser findByOpenId(String userId);
}