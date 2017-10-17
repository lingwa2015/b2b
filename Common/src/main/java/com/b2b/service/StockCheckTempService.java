package com.b2b.service;

import com.b2b.common.domain.StockCheckTemp;

import java.util.List;

public interface StockCheckTempService {

	StockCheckTemp selectByPrimaryKey(Integer itemId);

	void updateByPrimaryKeySelective(StockCheckTemp stockCheckTemp);

	void insert(StockCheckTemp stockCheckTemp);

	void deleteAll(Object[] objects);

	List<StockCheckTemp> findAllByCityId(Integer cityId);

	void  deleteByPrimaryKey(Integer itemId);

}
