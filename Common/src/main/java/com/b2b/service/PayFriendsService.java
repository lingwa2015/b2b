package com.b2b.service;

import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.PayFriends;
import com.b2b.common.domain.WXUser;

public interface PayFriendsService {

	List<PayFriends> findByOrderId(Integer orderId);

	int findTotalMoney(Integer orderId);

	PayFriends save(Long money, Integer orderId, String orderNo, WXUser wxUser);

	HashMap<String, Object> changePayStatus(String string, int i);

	void changePayStatusToRefund(String id, int i);

}
