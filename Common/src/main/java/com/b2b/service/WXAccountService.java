package com.b2b.service;

import com.b2b.common.domain.WXAccount;

public interface WXAccountService {

	WXAccount findVipBycid(Integer customerUserId);

	void create(WXAccount wxAccount);

	void updateForAddMoney(Integer cid, long money);

}
