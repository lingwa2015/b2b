package com.b2b.service;

import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.BlackWhiteListItem;

public interface BlackwhitelistItemService {

	BlackWhiteListItem findBlackByItemId(Integer customerId, int i,
			Integer itemId);

	void create(BlackWhiteListItem blackListItem);

	List<HashMap<String, Object>> findAll(Integer id, int i);

	void deleteAll(int blackId);

	void deleteById(int id);

}
