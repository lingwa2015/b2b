package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.ItemScore;

public interface ItemScoreService {

	void insert(ItemScore itemScore);

	List<ItemScore> findAvgGroupByItemId();

	List<ItemScore> findByItemId(Integer itemId);

}
