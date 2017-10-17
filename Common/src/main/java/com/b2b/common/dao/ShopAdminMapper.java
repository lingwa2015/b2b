package com.b2b.common.dao;

import com.b2b.common.domain.ShopAdmin;
import com.b2b.common.domain.ShopAdminExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShopAdminMapper {
    int countByExample(ShopAdminExample example);

    int deleteByExample(ShopAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopAdmin record);

    int insertSelective(ShopAdmin record);

    List<ShopAdmin> selectByExample(ShopAdminExample example);

    ShopAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopAdmin record, @Param("example") ShopAdminExample example);

    int updateByExample(@Param("record") ShopAdmin record, @Param("example") ShopAdminExample example);

    int updateByPrimaryKeySelective(ShopAdmin record);

    int updateByPrimaryKey(ShopAdmin record);

	ShopAdmin findByAdminIdAndShopId(@Param("id")Integer id, @Param("shop_id")Integer shop_id);

	List<ShopAdmin> findByAdminId(Integer id);

	List<HashMap<String, Object>> findAllShopByCondition(@Param("id")Integer id, @Param("name")String name);
}