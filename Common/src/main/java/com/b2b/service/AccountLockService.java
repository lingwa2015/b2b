package com.b2b.service;

import java.util.Date;

import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.PersonUser;

/**
 * 锁帐服务
 * */
public interface AccountLockService {
	
	public void create(AccountLock dto);
	
	public void update(AccountLock dto);

	public AccountLock findisLock(AccountLock aLock, Date lockdate);

	public int findLockByCityId(AccountLock aLock);

	public AccountLock findisLockByCityId(AccountLock aLock, Date date,
			Integer cityId);
}
