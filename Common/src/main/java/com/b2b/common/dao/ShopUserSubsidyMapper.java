package com.b2b.common.dao;

import com.b2b.common.domain.ShopUserSubsidy;
import com.b2b.common.domain.ShopUserSubsidyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopUserSubsidyMapper {
    int countByExample(ShopUserSubsidyExample example);

    int deleteByExample(ShopUserSubsidyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopUserSubsidy record);

    int insertSelective(ShopUserSubsidy record);

    List<ShopUserSubsidy> selectByExample(ShopUserSubsidyExample example);

    ShopUserSubsidy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopUserSubsidy record, @Param("example") ShopUserSubsidyExample example);

    int updateByExample(@Param("record") ShopUserSubsidy record, @Param("example") ShopUserSubsidyExample example);

    int updateByPrimaryKeySelective(ShopUserSubsidy record);

    int updateByPrimaryKey(ShopUserSubsidy record);

	ShopUserSubsidy findByUserIdAndShopId(@Param("userId")Integer userId, @Param("shopId")Integer shopId);

	List<ShopUserSubsidy> findListByShopIdAndType(@Param("shopId")Integer shopId, @Param("type")Integer type, @Param("freeFee")Long freeFee);

	List<ShopUserSubsidy> findListByShopId(@Param("shopId")Integer shopId);
}