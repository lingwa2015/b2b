package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.ConsumeRank;

public interface ConsumeRankService {

	List<ConsumeRank> rank(Integer id, Date startDate, Date endDate);

	void insert(ConsumeRank consumeRank);

	void delete(Integer id);

	List<ConsumeRank> findByShopId(Integer shopId);

	Long findTotalByShopId(Integer shopId);

	List<Integer> findShopIdByWeekFeeAndKinds();

	List<ConsumeRank> findItemIdByShopId(Integer id);

}
