package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.NotifyStateMapper;
import com.b2b.common.domain.NotifyState;
import com.b2b.common.domain.NotifyStateExample;
import com.b2b.service.NotifyStateService;

@Service
public class NotifyStateServiceImpl implements NotifyStateService {
	
	@Autowired
	private NotifyStateMapper notifyStateMapper;
	
	@Override
	public void insert(NotifyState state) {
		this.notifyStateMapper.insertSelective(state);
	}

	@Override
	public NotifyState findById(String id) {
		return this.notifyStateMapper.selectByPrimaryKey(id);
	}

}
