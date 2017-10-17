package com.b2b.common.dao;

import com.b2b.common.domain.ShopUser;
import com.b2b.common.domain.ShopUserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShopUserMapper {
    int countByExample(ShopUserExample example);

    int deleteByExample(ShopUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopUser record);

    int insertSelective(ShopUser record);

    List<ShopUser> selectByExample(ShopUserExample example);

    ShopUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopUser record, @Param("example") ShopUserExample example);

    int updateByExample(@Param("record") ShopUser record, @Param("example") ShopUserExample example);

    int updateByPrimaryKeySelective(ShopUser record);

    int updateByPrimaryKey(ShopUser record);

	List<ShopUser> findManagerByShopid(Integer shopId);

	void updateManage(Integer id);

	List<ShopUser> findAllSuperAdmin();

	void changeAdminToComon(Integer id);
}