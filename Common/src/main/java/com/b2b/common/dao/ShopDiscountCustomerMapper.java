package com.b2b.common.dao;

import com.b2b.common.domain.ShopDiscountCustomer;
import com.b2b.common.domain.ShopDiscountCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopDiscountCustomerMapper {
    int countByExample(ShopDiscountCustomerExample example);

    int deleteByExample(ShopDiscountCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopDiscountCustomer record);

    int insertSelective(ShopDiscountCustomer record);

    List<ShopDiscountCustomer> selectByExample(ShopDiscountCustomerExample example);

    ShopDiscountCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopDiscountCustomer record, @Param("example") ShopDiscountCustomerExample example);

    int updateByExample(@Param("record") ShopDiscountCustomer record, @Param("example") ShopDiscountCustomerExample example);

    int updateByPrimaryKeySelective(ShopDiscountCustomer record);

    int updateByPrimaryKey(ShopDiscountCustomer record);
}