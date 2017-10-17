package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.MemberPointMapper;
import com.b2b.common.domain.MemberPoint;
import com.b2b.service.MemberPointService;

@Service
public class MemberPointServiceImpl implements MemberPointService{
	@Autowired
	private MemberPointMapper memberPointMapper;
	
	@Override
	public MemberPoint findByid(Integer shopId) {
		return this.memberPointMapper.selectByPrimaryKey(shopId);
	}

	@Override
	public void update(MemberPoint memberPoint) {
		this.memberPointMapper.updateByPrimaryKeySelective(memberPoint);
	}

	@Override
	public void insert(MemberPoint memberPoint2) {
		this.memberPointMapper.insert(memberPoint2);
	}

	@Override
	public void updatePoint(Integer shopId, Long point) {
		MemberPoint memberPoint = this.memberPointMapper.selectByPrimaryKey(shopId);
		memberPoint.setAccount(memberPoint.getAccount()-point);
		this.memberPointMapper.updateByPrimaryKeySelective(memberPoint);
	}

	@Override
	public void updateAccount(Integer shopId, Long point) {
		this.memberPointMapper.updateAccount(shopId,point);
	}

}
