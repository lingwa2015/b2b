package com.b2b.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.PayFriendsMapper;
import com.b2b.common.domain.NotifyState;
import com.b2b.common.domain.PayFriends;
import com.b2b.common.domain.PayFriendsExample;
import com.b2b.common.domain.WXUser;
import com.b2b.common.domain.PayFriendsExample.Criteria;
import com.b2b.common.domain.WXUserOrder;
import com.b2b.service.NotifyStateService;
import com.b2b.service.PayFriendsService;
import com.b2b.service.WXUserOrderService;
import com.b2b.service.WXUserService;

@Service
public class PayFriendsServiceImpl implements PayFriendsService {
	@Autowired
	private PayFriendsMapper payFriendsMapper;
	
	@Autowired
	private NotifyStateService notifyStateService;
	
	@Autowired
	private WXUserOrderService wxUserOrderService;
	
	@Autowired
	private WXUserService wxUserService;
	
	@Override
	public List<PayFriends> findByOrderId(Integer orderId) {
		PayFriendsExample example = new PayFriendsExample();
		example.setOrderByClause("fee desc");
		Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		criteria.andPayStateEqualTo(1);
		return this.payFriendsMapper.selectByExample(example);
	}

	@Override
	public int findTotalMoney(Integer orderId) {
		return this.payFriendsMapper.findTotalMoney(orderId);
	}

	@Override
	public PayFriends save(Long money, Integer orderId, String orderNo,
			WXUser wxUser) {
		PayFriends friends = new PayFriends();
		friends.setId(orderNo);
		friends.setCreateTime(new Date());
		friends.setFee(money);
		friends.setHeadImg(wxUser.getHeadImgurl());
		friends.setNickname(wxUser.getNickName());
		friends.setOrderId(orderId);
		friends.setUserId(wxUser.getId());
		friends.setPayState(0);
		try {
			this.payFriendsMapper.insert(friends);
			return friends;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HashMap<String, Object> changePayStatus(String id,int i) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		this.payFriendsMapper.changePayStatus(id,i);
		PayFriends friends = this.payFriendsMapper.selectByPrimaryKey(id);
		int money = this.payFriendsMapper.findTotalMoney(friends.getOrderId());
		WXUserOrder order = this.wxUserOrderService.findById(friends.getOrderId());
		if(money>=order.getSnackpackageTotal()){
			order.setPayfeeStatus(0);
			this.wxUserOrderService.changePayfeeStatus(order);
			map.put("state", 1);
		}else{
			map.put("state", 0);
		}
		NotifyState state = new NotifyState();
		state.setId(id);
		state.setStatus(1);
		state.setCreatedTime(new Date());
		this.notifyStateService.insert(state);
		WXUser user = this.wxUserService.findById(order.getWxuserId());
		WXUser user2 = this.wxUserService.findById(friends.getUserId());
		map.put("orderNo", order.getWxorderNum());
		map.put("openid", user.getOpenid());
		map.put("name", user.getNickName());
		map.put("frientName", user2.getNickName());
		map.put("pay", money);
		map.put("total", order.getSnackpackageTotal());
		map.put("id", order.getId());
		return map;
	}

	@Override
	public void changePayStatusToRefund(String id, int i) {
		this.payFriendsMapper.changePayStatus(id,i);
	}

	

}
