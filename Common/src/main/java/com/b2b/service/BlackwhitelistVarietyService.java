package com.b2b.service;

import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.BlackWhiteListVariety;

public interface BlackwhitelistVarietyService {

	BlackWhiteListVariety findBlackByVarietyId(Integer customerId, int flag,
			Integer varietyId);

	void create(BlackWhiteListVariety blackListVariety);

	List<HashMap<String, Object>> findAll(Integer id, int i);

	void deleteAll(int blackId);

	void deleteById(int id);


}
