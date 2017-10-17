package com.b2b.service;

import com.b2b.common.domain.MemberPoint;

public interface MemberPointService {

	MemberPoint findByid(Integer shopId);

	void update(MemberPoint memberPoint);

	void insert(MemberPoint memberPoint2);

	void updatePoint(Integer shopId, Long point);

	void updateAccount(Integer shopId, Long point);

}
