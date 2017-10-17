package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.WXConsumRecordsMapper;
import com.b2b.common.domain.WXConsumRecords;
import com.b2b.common.domain.WXConsumRecordsExample;
import com.b2b.service.WXConsumRecordsService;

@Service
public class WXConsumRecordsServiceImpl implements WXConsumRecordsService {
	@Autowired
	private WXConsumRecordsMapper wxConsumRecordsMapper;
	
	@Override
	public void insert(WXConsumRecords consumRecords) {
		this.wxConsumRecordsMapper.insert(consumRecords);
	}

	@Override
	public void deleteWXConsumRecordsByOrderNo(String orderNo) {
		WXConsumRecordsExample example = new WXConsumRecordsExample();
		com.b2b.common.domain.WXConsumRecordsExample.Criteria criteria = example.createCriteria();
		criteria.andOrderNoEqualTo(orderNo);
		this.wxConsumRecordsMapper.deleteByExample(example);
	}

}
