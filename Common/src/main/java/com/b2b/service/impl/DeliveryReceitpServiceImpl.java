package com.b2b.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.DeliveryReceitpMapper;
import com.b2b.common.domain.DeliveryReceitp;
import com.b2b.service.DeliveryReceitpService;

@Service
public class DeliveryReceitpServiceImpl implements DeliveryReceitpService {
	@Autowired
	DeliveryReceitpMapper deliveryReceitpMapper;
	
	@Override
	public List<DeliveryReceitp> findByCityId(Integer cityId) {
		return this.deliveryReceitpMapper.findByCityId(cityId);
	}

	@Override
	public List<DeliveryReceitp> findByCityIdAndInterfaceId(Integer cityId, Integer id) {
		return this.deliveryReceitpMapper.findByCityIdAndInterfaceId(cityId,id);
	}

	@Override
	public void insert(DeliveryReceitp dto) {
		this.deliveryReceitpMapper.insert(dto);
	}

	@Override
	public void update(DeliveryReceitp dto) {
		if(null==dto.getIsvxvip()){
			this.deliveryReceitpMapper.updateIswxvipNull(dto.getId());
		}
		this.deliveryReceitpMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public DeliveryReceitp findById(Integer id) {
		return this.deliveryReceitpMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<DeliveryReceitp> findByCondition(Integer cityId, Date startTime, Date endTime, String linkName, String username, String status, String tagStatus) {
		return this.deliveryReceitpMapper.findByCondition(cityId, startTime, endTime, linkName, username, status, tagStatus);
	}

	@Override
	public void delete(Integer id) {
		this.deliveryReceitpMapper.delete(id);
	}

	@Override
	public int findTodayNumByCityId(Integer cityId) {
		return this.deliveryReceitpMapper.findTodayNumByCityId(cityId);
	}

	@Override
	public int findTodayNumByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids) {
		return this.deliveryReceitpMapper.findTodayNumByCityIdAndInterfaceIds(cityId,ids);
	}

	@Override
	public List<DeliveryReceitp> findByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids) {
		return this.deliveryReceitpMapper.findByCityIdAndInterfaceIds(cityId,ids);
	}

	@Override
	public int findMonthNumByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids) {
		return this.deliveryReceitpMapper.findMonthNumByCityIdAndInterfaceIds(cityId,ids);
	}

	@Override
	public HashMap<String, Object> findSaleNumByCityIdAndIds(Integer cityId, Integer id) {
		return this.deliveryReceitpMapper.findSaleNumByCityIdAndIds(cityId, id);
	}

}
