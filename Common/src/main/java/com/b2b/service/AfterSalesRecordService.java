package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.AfterSalesRecord;

public interface AfterSalesRecordService {

	void insert(AfterSalesRecord record);

	List<AfterSalesRecord> findByCondition(String userName, String recordMan,String fuzeMan,
			String recordType, String doState, Integer regionId, Integer reseauId, Integer cityId);

	void delete(Integer id);

	AfterSalesRecord findById(Integer id);

	void update(AfterSalesRecord record);

	List<AfterSalesRecord> findByUserId(Integer shopId);

	List<AfterSalesRecord> findByUserIdAndFlag(Integer shopId, int flag);

	List<AfterSalesRecord> findByUserIdLimitTwo(Integer customerId);

	List<AfterSalesRecord> findByUserIdNotCompelete(Integer customerId);

	int findCountByUserIdNotCompelete(Integer shopId);

}
