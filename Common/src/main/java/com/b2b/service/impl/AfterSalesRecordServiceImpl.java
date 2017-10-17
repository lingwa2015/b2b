package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.AfterSalesRecordMapper;
import com.b2b.common.domain.AfterSalesRecord;
import com.b2b.service.AfterSalesRecordService;

@Service
public class AfterSalesRecordServiceImpl implements AfterSalesRecordService {
	@Autowired
	private AfterSalesRecordMapper afterSalesRecordMapper;
	
	@Override
	public void insert(AfterSalesRecord record) {
		this.afterSalesRecordMapper.insert(record);
	}

	@Override
	public List<AfterSalesRecord> findByCondition(String userName,
			String recordMan,String fuzeMan, String recordType, String doState,Integer regionId,Integer reseauId,Integer cityId) {
		return this.afterSalesRecordMapper.findByCondition(userName,recordMan,fuzeMan,recordType,doState,regionId,reseauId,cityId);
	}

	@Override
	public void delete(Integer id) {
		this.afterSalesRecordMapper.delete(id);
	}

	@Override
	public AfterSalesRecord findById(Integer id) {
		return this.afterSalesRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(AfterSalesRecord record) {
		this.afterSalesRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<AfterSalesRecord> findByUserId(Integer shopId) {
		return this.afterSalesRecordMapper.findByUserId(shopId);
	}

	@Override
	public List<AfterSalesRecord> findByUserIdAndFlag(Integer shopId, int flag) {
		return this.afterSalesRecordMapper.findByUserIdAndFlag(shopId,flag);
	}

	@Override
	public List<AfterSalesRecord> findByUserIdLimitTwo(Integer shopId) {
		return this.afterSalesRecordMapper.findByUserIdLimitTwo(shopId);
	}

	@Override
	public List<AfterSalesRecord> findByUserIdNotCompelete(Integer customerId) {
		return this.afterSalesRecordMapper.findByUserIdNotCompelete(customerId);
	}

	@Override
	public int findCountByUserIdNotCompelete(Integer shopId) {
		return this.afterSalesRecordMapper.findCountByUserIdNotCompelete(shopId);
	}

}
