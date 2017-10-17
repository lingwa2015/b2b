package com.b2b.common.dao;

import com.b2b.common.domain.ShopRefund;
import com.b2b.common.domain.ShopRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopRefundMapper {
    int countByExample(ShopRefundExample example);

    int deleteByExample(ShopRefundExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopRefund record);

    int insertSelective(ShopRefund record);

    List<ShopRefund> selectByExample(ShopRefundExample example);

    ShopRefund selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopRefund record, @Param("example") ShopRefundExample example);

    int updateByExample(@Param("record") ShopRefund record, @Param("example") ShopRefundExample example);

    int updateByPrimaryKeySelective(ShopRefund record);

    int updateByPrimaryKey(ShopRefund record);
}