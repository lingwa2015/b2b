package com.b2b.common.dao;

import com.b2b.common.domain.ItemSupplier;
import com.b2b.common.domain.ItemSupplierExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemSupplierMapper {
    int countByExample(ItemSupplierExample example);

    int deleteByExample(ItemSupplierExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemSupplier record);

    int insertSelective(ItemSupplier record);

    List<ItemSupplier> selectByExample(ItemSupplierExample example);

    ItemSupplier selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemSupplier record, @Param("example") ItemSupplierExample example);

    int updateByExample(@Param("record") ItemSupplier record, @Param("example") ItemSupplierExample example);

    int updateByPrimaryKeySelective(ItemSupplier record);

    int updateByPrimaryKey(ItemSupplier record);

	ItemSupplier findByItemIdAndSupperId(@Param("suppilerId")Integer suppilerId, @Param("itemId")Integer itemId);
}