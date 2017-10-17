package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.StandardOrderItemMapper;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.service.StandardOrderItemService;

@Service
public class StandardOrderItemServiceImpl implements StandardOrderItemService {
	@Autowired
	private StandardOrderItemMapper standardOrderItemMapper;

	@Override
	public List<StandardOrderItem> findByStandOrderId(Integer snackpackageId) {
		return this.standardOrderItemMapper.findByStandOrderId(snackpackageId);
	}
}
