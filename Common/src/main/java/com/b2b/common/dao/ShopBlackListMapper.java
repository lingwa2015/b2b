package com.b2b.common.dao;

import com.b2b.common.domain.ShopBlackList;
import com.b2b.common.domain.ShopBlackListExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShopBlackListMapper {
    int countByExample(ShopBlackListExample example);

    int deleteByExample(ShopBlackListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopBlackList record);

    int insertSelective(ShopBlackList record);

    List<ShopBlackList> selectByExample(ShopBlackListExample example);

    ShopBlackList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopBlackList record, @Param("example") ShopBlackListExample example);

    int updateByExample(@Param("record") ShopBlackList record, @Param("example") ShopBlackListExample example);

    int updateByPrimaryKeySelective(ShopBlackList record);

    int updateByPrimaryKey(ShopBlackList record);

	List<HashMap<String, Object>> findByShopId(Integer shop_id);

	ArrayList<Integer> findAllByShopId(Integer customerId);

	ShopBlackList findByShopIdAndItemId(@Param("userId")Integer userId, @Param("itemId")Integer itemId);

	int countByItemId(Integer id);
}