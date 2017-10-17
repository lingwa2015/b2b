package com.b2b.common.dao;

import com.b2b.common.domain.Equipment;
import com.b2b.common.domain.EquipmentExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EquipmentMapper {
    int countByExample(EquipmentExample example);

    int deleteByExample(EquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    List<Equipment> selectByExample(EquipmentExample example);

    Equipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Equipment record, @Param("example") EquipmentExample example);

    int updateByExample(@Param("record") Equipment record, @Param("example") EquipmentExample example);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);

	List<HashMap<String, Object>> findAll();

	HashMap<String, Object> findWithParentName(@Param("id")int id);

	void deleteById(@Param("id")Integer id);

	void deleteByParentId(@Param("id")Integer id);

	List<Equipment> findByParentId(@Param("oneTypeId")Integer oneTypeId);

	List<HashMap<String, Object>> findAllByCityId(@Param("cityId")Integer cityId);

	List<Equipment> findOneTypeByCityId(@Param("cityId")Integer cityId);

	List<Equipment> findTwoTypeByCityId(@Param("cityId")Integer cityId);
}