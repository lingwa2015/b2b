package com.b2b.service.impl;

import com.b2b.common.dao.StockCheckTempMapper;
import com.b2b.common.domain.StockCheckTemp;
import com.b2b.common.domain.StockCheckTempExample;
import com.b2b.common.domain.StockCheckTempExample.Criteria;
import com.b2b.service.StockCheckTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockCheckTempServiceImpl implements StockCheckTempService {
	@Autowired
	private StockCheckTempMapper stockCheckTempMapper;
	
	@Override
	public StockCheckTemp selectByPrimaryKey(Integer itemId) {
		return this.stockCheckTempMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public void updateByPrimaryKeySelective(StockCheckTemp stockCheckTemp) {
		this.stockCheckTempMapper.updateByPrimaryKeySelective(stockCheckTemp);
	}

	@Override
	public void insert(StockCheckTemp stockCheckTemp) {
		this.stockCheckTempMapper.insert(stockCheckTemp);
	}

	@Override
	public void deleteAll(Object[] ids) {
		this.stockCheckTempMapper.deleteAll(ids);
	}

	@Override
	public List<StockCheckTemp> findAllByCityId(Integer cityId) {
		StockCheckTempExample example = new StockCheckTempExample();
		example.setOrderByClause("created desc");
		Criteria criteria = example.createCriteria();
		criteria.andCityIdEqualTo(cityId);
		return this.stockCheckTempMapper.selectByExample(example);
	}

	@Override
	public void  deleteByPrimaryKey(Integer itemId){
		this.stockCheckTempMapper.deleteByPrimaryKey(itemId);
	}

}
