package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ConsumeRankMapper;
import com.b2b.common.domain.ConsumeRank;
import com.b2b.common.domain.ConsumeRankExample;
import com.b2b.common.domain.ConsumeRankExample.Criteria;
import com.b2b.service.ConsumeRankService;

@Service
public class ConsumeRankServiceImpl implements ConsumeRankService {
	@Autowired
	private ConsumeRankMapper consumeRankMapper;
	
	@Override
	public List<ConsumeRank> rank(Integer id,Date startDate, Date endDate) {
		return this.consumeRankMapper.rank(id,startDate,endDate);
	}

	@Override
	public void insert(ConsumeRank consumeRank) {
		this.consumeRankMapper.insert(consumeRank);
	}

	@Override
	public void delete(Integer id) {
		ConsumeRankExample example = new ConsumeRankExample();
		Criteria criteria = example.createCriteria();
		criteria.andShopIdEqualTo(id);
		this.consumeRankMapper.deleteByExample(example);
	}

	@Override
	public List<ConsumeRank> findByShopId(Integer shopId) {
		return consumeRankMapper.findByShopId(shopId);
	}

	@Override
	public Long findTotalByShopId(Integer shopId) {
		return this.consumeRankMapper.findTotalByShopId(shopId);
	}
	
	/**
	 * 周消费大于200，周消费品大于10个
	 */
	@Override
	public List<Integer> findShopIdByWeekFeeAndKinds() {
		return this.consumeRankMapper.findShopIdByWeekFeeAndKinds();
	}
	
	/**
	 * 根据店铺id查询排行耪，需要关联商品表的商品id
	 */
	@Override
	public List<ConsumeRank> findItemIdByShopId(Integer id) {
		return this.consumeRankMapper.findItemIdByShopId(id);
	}

}
