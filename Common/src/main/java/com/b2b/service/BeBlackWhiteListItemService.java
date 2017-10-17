package com.b2b.service;

import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.BeBlackWhiteListItem;

public interface BeBlackWhiteListItemService {

	BeBlackWhiteListItem findByItemId(int customerId, int i, Integer itemId);

	void create(BeBlackWhiteListItem blackListItem);

	List<HashMap<String, Object>> findAll(Integer id, int i);

	void deleteAll(int whiteId);

	void deleteById(int id);

}
