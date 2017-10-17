package com.b2b.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.TranConsumeMapper;
import com.b2b.common.domain.TranConsume;
import com.b2b.common.domain.TranConsumeExample;
import com.b2b.common.domain.TranConsumeExample.Criteria;
import com.b2b.service.TranConsumeService;

@Service
public class TranConsumeServiceImpl implements TranConsumeService {
	@Autowired
	private TranConsumeMapper tranConsumeMapper;
	
//	@Override
//	public void delete(Date firstDate, Date sumDate) {
//		TranConsumeExample example = new TranConsumeExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andSumDateGreaterThanOrEqualTo(firstDate);
//		criteria.andSumDateLessThanOrEqualTo(sumDate);
//		this.tranConsumeMapper.deleteByExample(example);
//	}

	@Override
	public void create(TranConsume tranConsume) {
		this.tranConsumeMapper.insert(tranConsume);
	}

	@Override
	public Long findCurrentMonthConsumeMoney(Integer customerUserId) {
		return tranConsumeMapper.findCurrentMonthConsumeMoney(customerUserId);
	}

	@Override
	public Date findDate() {
		return this.tranConsumeMapper.findDate();
	}

	@Override
	public Long findLossMoney(Integer shop_id) {
		return this.tranConsumeMapper.findLossMoney(shop_id);
	}

	@Override
	public TranConsume findMoreLossMoney(Integer shopId) {
		return this.tranConsumeMapper.findMoreLossMoney(shopId);
	}

	@Override
	public TranConsume findMonthStockByUserIdAndDate(Integer id, Date date) {
		return this.tranConsumeMapper.findMonthStockByUserIdAndDate(id,date);
	}

	@Override
	public TranConsume findTotalMonthStockByDate(Date date) {
		return this.tranConsumeMapper.findTotalMonthStockByDate(date);
	}

	@Override
	public void deleteByDateAndCityId(Date firstDate, Date sumDate,
			Integer cityId) {
		this.tranConsumeMapper.deleteByDateAndCityId(firstDate,sumDate,cityId);
	}

	@Override
	public Long findCurrentMonthRedPriceByShopId(Integer shopId) {
		return this.tranConsumeMapper.findCurrentMonthRedPriceByShopId(shopId);
	}

}
