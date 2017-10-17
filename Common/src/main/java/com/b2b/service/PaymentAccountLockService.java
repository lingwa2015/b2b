package com.b2b.service;

import com.b2b.common.domain.PaymentAccountLock;

import java.util.Date;

public interface PaymentAccountLockService {


	void create(PaymentAccountLock dto);

	void update(PaymentAccountLock dto);

	PaymentAccountLock findisLock(PaymentAccountLock aLock, Date lockdate);

	int findLockByCityId(PaymentAccountLock aLock);

	PaymentAccountLock findisLockByCityId(PaymentAccountLock aLock, Date date,
								   Integer cityId);
}
