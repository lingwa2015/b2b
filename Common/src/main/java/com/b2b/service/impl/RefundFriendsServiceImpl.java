package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.RefundFriendsMapper;
import com.b2b.common.domain.RefundFriends;
import com.b2b.service.RefundFriendsService;

@Service
public class RefundFriendsServiceImpl implements RefundFriendsService {
	@Autowired
	RefundFriendsMapper refundFriendsMapper;
	
	@Override
	public void save(RefundFriends friends) {
		this.refundFriendsMapper.insertSelective(friends);
	}

}
