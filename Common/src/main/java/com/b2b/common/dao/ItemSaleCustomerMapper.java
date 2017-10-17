package com.b2b.common.dao;

import com.b2b.common.domain.ItemSaleCustomer;
import com.b2b.common.domain.ItemSaleCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemSaleCustomerMapper {
    int countByExample(ItemSaleCustomerExample example);

    int deleteByExample(ItemSaleCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemSaleCustomer record);

    int insertSelective(ItemSaleCustomer record);

    List<ItemSaleCustomer> selectByExample(ItemSaleCustomerExample example);

    ItemSaleCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemSaleCustomer record, @Param("example") ItemSaleCustomerExample example);

    int updateByExample(@Param("record") ItemSaleCustomer record, @Param("example") ItemSaleCustomerExample example);

    int updateByPrimaryKeySelective(ItemSaleCustomer record);

    int updateByPrimaryKey(ItemSaleCustomer record);
}