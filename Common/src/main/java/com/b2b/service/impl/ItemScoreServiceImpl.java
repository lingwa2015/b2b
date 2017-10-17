package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ItemScoreMapper;
import com.b2b.common.domain.ItemScore;
import com.b2b.service.ItemScoreService;

@Service
public class ItemScoreServiceImpl implements ItemScoreService {
	@Autowired
	ItemScoreMapper itemScoreMapper;
	
	@Override
	public void insert(ItemScore itemScore) {
		this.itemScoreMapper.insert(itemScore);	
	}

	@Override
	public List<ItemScore> findAvgGroupByItemId() {
		return this.itemScoreMapper.findAvgGroupByItemId();
	}

	@Override
	public List<ItemScore> findByItemId(Integer itemId) {
		return this.itemScoreMapper.findByItemId(itemId);
	}

}
