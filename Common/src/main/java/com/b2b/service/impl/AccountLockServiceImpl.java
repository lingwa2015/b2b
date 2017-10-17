package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.AccountLockMapper;
import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.AccountLockExample;
import com.b2b.common.domain.TranSum;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.AccountLockExample.Criteria;
import com.b2b.dto.TranSumDto;
import com.b2b.service.AccountLockService;

@Service
public class AccountLockServiceImpl implements AccountLockService {
	
	@Autowired
	AccountLockMapper accountLockMapper;
	
	@Override
	public void create(AccountLock dto){
		accountLockMapper.insert(dto);
	}
	
	@Override
	public void update(AccountLock dto){
		AccountLockExample example = new AccountLockExample();
		Criteria criteria = example.createCriteria();
		criteria.andYearsEqualTo(dto.getYears());
		criteria.andMonthsEqualTo(dto.getMonths());
		accountLockMapper.updateByExample(dto, example);
	}
	
	@Override
	public AccountLock findisLock(AccountLock aLock, Date lockdate) {
		return this.accountLockMapper.findisLock(aLock, lockdate);
	}

	@Override
	public int findLockByCityId(AccountLock dto) {
		int lock=0;
		AccountLockExample example = new AccountLockExample();
		Criteria criteria = example.createCriteria();
		criteria.andYearsEqualTo(dto.getYears());
		criteria.andMonthsEqualTo(dto.getMonths());
		criteria.andCityIdEqualTo(dto.getCityId());
		List<AccountLock> accountLockList=accountLockMapper.selectByExample(example);
		for (AccountLock aLock : accountLockList) {
			if (aLock != null) {
				lock=aLock.getLocks();
			}
		}
		return lock;
	}

	@Override
	public AccountLock findisLockByCityId(AccountLock aLock, Date lockdate,
			Integer cityId) {
		return this.accountLockMapper.findisLockByCityId(aLock, lockdate,cityId);
	}
}
