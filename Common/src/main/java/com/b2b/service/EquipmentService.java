package com.b2b.service;

import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.Equipment;

public interface EquipmentService {

	void update(Equipment dto);

	void create(Equipment dto);

	HashMap<String, Object> findWithParentName(int id);

	Equipment findById(int id);

	void deleteOneWithTwoTypeById(Integer id);

	void deteleById(Integer id);

	List<Equipment> findByParentId(Integer oneTypeId);

	List<HashMap<String, Object>> findAllByCityId(Integer cityId);

	List<Equipment> findOneTypeByCityId(Integer cityId);

	List<Equipment> findTwoTypeByCityId(Integer cityId);

}
