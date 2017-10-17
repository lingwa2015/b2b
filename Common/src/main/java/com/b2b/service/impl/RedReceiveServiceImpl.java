package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.common.dao.RedReceiveMapper;
import com.b2b.common.domain.RedAccount;
import com.b2b.common.domain.RedAccountExample;
import com.b2b.common.domain.RedAccountExample.Criteria;
import com.b2b.common.domain.RedReceive;
import com.b2b.service.RedAccountService;
import com.b2b.service.RedReceiveService;

@Service
public class RedReceiveServiceImpl implements RedReceiveService {
	@Autowired
	RedReceiveMapper redReceiveMapper;
	
	@Autowired
	RedAccountService redAccountService;
	
	@Override
	public Integer findTodayNumByBuyerIdAndTypeAndCityId(Integer buyerId, int sign, Integer cityId) {
		return this.redReceiveMapper.findTodayNumByBuyerIdAndTypeAndCityId(buyerId,sign,cityId);
	}

	@Override
	public RedReceive findByOrderNo(String orderNo) {
		return this.redReceiveMapper.findByOrderNo(orderNo);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void save(RedReceive receive) {
		this.redReceiveMapper.insert(receive);
		RedAccount redAccount = this.redAccountService.findByUserIdAndType(receive.getUserId(),receive.getType());
		if(null==redAccount){
			//创建红包庄户
			RedAccount account = new RedAccount();
			account.setAccount(receive.getRedFee());
			account.setType(receive.getType());
			account.setUserId(receive.getUserId());
			this.redAccountService.save(account);
		}else{
			//
			this.redAccountService.updateAccountMoneyByUserIdAndType(receive.getUserId(),receive.getType(),receive.getRedFee());
		}
	}

	@Override
	public Integer findTodayNumByRedTypeIdAndCityId(Integer id, Integer cityId) {
		return this.redReceiveMapper.findTodayNumByRedTypeIdAndCityId(id,cityId);
	}

	@Override
	public void update(RedReceive receive) {
		this.redReceiveMapper.updateByPrimaryKey(receive);
	}

	@Override
	public List<RedReceive> findByCondition(Integer redId, Integer cityId) {
		return this.redReceiveMapper.findByCondition(redId,cityId);
	}

	@Override
	public Long findTotalFeeByCityId(Integer cityId) {
		return this.redReceiveMapper.findTotalFeeByCityId(cityId);
	}

}
