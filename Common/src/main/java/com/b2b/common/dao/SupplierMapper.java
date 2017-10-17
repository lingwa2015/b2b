package com.b2b.common.dao;

import com.b2b.common.domain.ItemSupplier;
import com.b2b.common.domain.Supplier;
import com.b2b.common.domain.SupplierExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SupplierMapper {
    int countByExample(SupplierExample example);

    int deleteByExample(SupplierExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    List<Supplier> selectByExample(SupplierExample example);

    Supplier selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Supplier record, @Param("example") SupplierExample example);

    int updateByExample(@Param("record") Supplier record, @Param("example") SupplierExample example);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);

	List<Supplier> findByItemId(int id);

	List<HashMap<String, Object>> findBySupplierId(int id);

	List<HashMap<String, Object>> findItemBySupplierId(Integer supplier_id);

    List<HashMap<String,Object>> findByLikeUserNameAndCityId(@Param("userName")String userName, @Param("cityId")Integer cityId);
}