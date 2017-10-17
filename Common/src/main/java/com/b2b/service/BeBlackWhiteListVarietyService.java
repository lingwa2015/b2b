package com.b2b.service;

import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.BeBlackWhiteListVariety;
import com.b2b.common.domain.BlackWhiteListVariety;

public interface BeBlackWhiteListVarietyService {

	BeBlackWhiteListVariety findBlackByVarietyId(int customerId, int i,
			Integer varietyId);

	void create(BeBlackWhiteListVariety blackListVariety);

	List<HashMap<String, Object>> findAll(Integer id, int i);

	void deleteAll(int blackId);

	void deleteById(int id);

}
