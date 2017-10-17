package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.Reseau;

public interface ReseauService {

	void save(Reseau reseau);

	void update(Reseau reseau);

	List<Reseau> findAll();

	Reseau findById(Integer id);

	void delete(Integer id);

	List<Reseau> findAllByCityId(Integer cityId);

	List<Reseau> findByCityIdAndIds(Integer cityId, List<Integer> ids);

	List<Reseau> findAllWithManageNameByCityId(Integer cityId);

}
