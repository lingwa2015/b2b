package com.b2b.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.BeBlackWhiteListItemMapper;
import com.b2b.common.domain.BeBlackWhiteListItem;
import com.b2b.common.domain.BeBlackWhiteListItemExample;
import com.b2b.common.domain.BeBlackWhiteListItemExample.Criteria;
import com.b2b.service.BeBlackWhiteListItemService;

@Service
public class BeBlackWhiteListItemServiceImpl implements
		BeBlackWhiteListItemService {
	@Autowired
	private BeBlackWhiteListItemMapper beBlackWhiteListItemMapper;
	
	@Override
	public BeBlackWhiteListItem findByItemId(int customerId, int i,
			Integer itemId) {
		return this.beBlackWhiteListItemMapper.findByItemId(customerId,i,itemId);
	}

	@Override
	public void create(BeBlackWhiteListItem blackListItem) {
		beBlackWhiteListItemMapper.insert(blackListItem);
	}

	@Override
	public List<HashMap<String, Object>> findAll(Integer id, int i) {
		return this.beBlackWhiteListItemMapper.findAll(id,i);
	}

	@Override
	public void deleteAll(int whiteId) {
		BeBlackWhiteListItemExample example = new BeBlackWhiteListItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andBeBlackwhiteIdEqualTo(whiteId);
		this.beBlackWhiteListItemMapper.deleteByExample(example);
	}

	@Override
	public void deleteById(int id) {
		this.beBlackWhiteListItemMapper.deleteByPrimaryKey(id);
	}

}
