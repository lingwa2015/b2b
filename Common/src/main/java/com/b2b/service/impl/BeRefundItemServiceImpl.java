package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.BeRefundItemMapper;
import com.b2b.common.domain.BeRefundItem;
import com.b2b.service.BeRefundItemService;
@Service
public class BeRefundItemServiceImpl implements BeRefundItemService{
	@Autowired
	BeRefundItemMapper beRefundItemMapper;
	
	@Override
	public void insert(BeRefundItem refundItem) {
		this.beRefundItemMapper.insert(refundItem);
	}

	@Override
	public List<BeRefundItem> findTodayRefundItemByShopId(Integer shopId) {
		return this.beRefundItemMapper.findTodayRefundItemByShopId(shopId);
	}

}
