package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.RedAccount;

public interface RedAccountService {

	RedAccount findByUserIdAndType(Integer userId, Integer type);

	void save(RedAccount account);

	void updateAccountMoneyByUserIdAndType(Integer userId, Integer type, Long redfee);

}
