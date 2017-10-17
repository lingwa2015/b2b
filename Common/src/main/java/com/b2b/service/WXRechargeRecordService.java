package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.WXRechargeRecord;
import com.b2b.page.Page;

public interface WXRechargeRecordService {

	List<WXRechargeRecord> findAllByCid(Integer customerUserId);

	void save(String id, int money, int freeMoney,
			String orderNo);

	void changeStatus(String string);

	Page<WXRechargeRecord> findPageByCondition(int currentPage,
			int defaultPageSize, Date startTime, Date endTime, String userName);

}
