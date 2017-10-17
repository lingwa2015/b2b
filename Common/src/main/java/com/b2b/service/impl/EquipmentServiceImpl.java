package com.b2b.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.EquipmentMapper;
import com.b2b.common.domain.Equipment;
import com.b2b.service.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {
	@Autowired
	EquipmentMapper equipmentMapper;
	
	@Override
	public void update(Equipment dto) {
		this.equipmentMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public void create(Equipment dto) {
		this.equipmentMapper.insert(dto);
	}

	@Override
	public HashMap<String, Object> findWithParentName(int id) {
		return this.equipmentMapper.findWithParentName(id);
	}

	@Override
	public Equipment findById(int id) {
		return this.equipmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteOneWithTwoTypeById(Integer id) {
		this.equipmentMapper.deleteById(id);
		this.equipmentMapper.deleteByParentId(id);
	}

	@Override
	public void deteleById(Integer id) {
		this.equipmentMapper.deleteById(id);
	}

	@Override
	public List<Equipment> findByParentId(Integer oneTypeId) {
		return this.equipmentMapper.findByParentId(oneTypeId);
	}

	@Override
	public List<HashMap<String, Object>> findAllByCityId(Integer cityId) {
		return this.equipmentMapper.findAllByCityId(cityId);
	}

	@Override
	public List<Equipment> findOneTypeByCityId(Integer cityId) {
		return this.equipmentMapper.findOneTypeByCityId(cityId);
	}

	@Override
	public List<Equipment> findTwoTypeByCityId(Integer cityId) {
		return this.equipmentMapper.findTwoTypeByCityId(cityId);
	}

}
