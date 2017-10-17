package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.Transfer;

public interface TransferService {

	void insert(Transfer dto);

	List<Transfer> findByCondition(String machineId, Date startTime,
			Date endTime, String type, Integer cityId);

	void delete(String id);

	Transfer findById(String transferId);

	void deleteByMacId(Integer id);

}
