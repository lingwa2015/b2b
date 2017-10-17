package com.b2b.service;

import com.b2b.common.domain.DeliveryReceitp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface DeliveryReceitpService {

	List<DeliveryReceitp> findByCityId(Integer cityId);

	List<DeliveryReceitp> findByCityIdAndInterfaceId(Integer cityId, Integer id);

	void insert(DeliveryReceitp dto);

	void update(DeliveryReceitp dto);

	DeliveryReceitp findById(Integer id);

	List<DeliveryReceitp> findByCondition(Integer cityId, Date startTime, Date endTime, String linkName, String username, String status, String tagStatus);

	void delete(Integer id);

	int findTodayNumByCityId(Integer cityId);

	int findTodayNumByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids);

	List<DeliveryReceitp> findByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids);

    int findMonthNumByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids);

    HashMap<String,Object> findSaleNumByCityIdAndIds(Integer cityId, Integer id);
}
