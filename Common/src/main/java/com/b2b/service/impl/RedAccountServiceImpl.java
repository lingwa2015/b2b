package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.common.dao.RedAccountMapper;
import com.b2b.common.domain.RedAccount;
import com.b2b.common.domain.RedAccountExample;
import com.b2b.common.domain.RedAccountExample.Criteria;
import com.b2b.service.RedAccountService;

@Service
public class RedAccountServiceImpl implements RedAccountService {
	@Autowired
	RedAccountMapper accountMapper;
	
	@Override
	public RedAccount findByUserIdAndType(Integer userId, Integer type) {
		return accountMapper.findByUserIdAndType(userId,type);
	}

	@Override
	public void save(RedAccount account) {
		this.accountMapper.insert(account);
	}
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void updateAccountMoneyByUserIdAndType(Integer userId, Integer type, Long redfee) {
		this.accountMapper.updateAccountMoneyByUserIdAndType(userId,type,redfee);
	}

	
}
