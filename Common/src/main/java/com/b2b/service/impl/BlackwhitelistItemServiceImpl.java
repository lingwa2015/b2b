package com.b2b.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.BlackWhiteListItemMapper;
import com.b2b.common.domain.BlackWhiteListItem;
import com.b2b.common.domain.BlackWhiteListItemExample;
import com.b2b.common.domain.BlackWhiteListItemExample.Criteria;
import com.b2b.service.BlackwhitelistItemService;
@Service
public class BlackwhitelistItemServiceImpl implements BlackwhitelistItemService {
	@Autowired
	private BlackWhiteListItemMapper blackWhiteListItemMapper;
	
	@Override
	public BlackWhiteListItem findBlackByItemId(Integer customerId, int i,
			Integer itemId) {
		return this.blackWhiteListItemMapper.findByItemId(customerId,i,itemId);
	}

	@Override
	public void create(BlackWhiteListItem blackListItem) {
		this.blackWhiteListItemMapper.insert(blackListItem);
	}

	@Override
	public List<HashMap<String, Object>> findAll(Integer id, int i) {
		return this.blackWhiteListItemMapper.findAll(id,i);
	}

	@Override
	public void deleteAll(int blackId) {
		BlackWhiteListItemExample example = new BlackWhiteListItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andBlackwhiteIdEqualTo(blackId);
		this.blackWhiteListItemMapper.deleteByExample(example);
	}

	@Override
	public void deleteById(int id) {
		this.blackWhiteListItemMapper.deleteByPrimaryKey(id);
	}

}
