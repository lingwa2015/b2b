package com.b2b.service;

import com.b2b.common.domain.WXConsumRecords;

public interface WXConsumRecordsService {

	void insert(WXConsumRecords consumRecords);

	void deleteWXConsumRecordsByOrderNo(String orderNo);

}
