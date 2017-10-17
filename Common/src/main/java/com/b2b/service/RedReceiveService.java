package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.RedReceive;

public interface RedReceiveService {
	/**
	 * 查找用户当天领取红包的次数
	 * @param buyerId
	 * @param sign
	 * @param cityId
	 * @return
	 */
	Integer findTodayNumByBuyerIdAndTypeAndCityId(Integer buyerId, int sign, Integer cityId);

	RedReceive findByOrderNo(String orderNo);

	void save(RedReceive receive);
	/**
	 * 查找某一类型红包当天领取数量
	 * @param id 红包类型
	 * @param cityId 
	 * @return
	 */
	Integer findTodayNumByRedTypeIdAndCityId(Integer id, Integer cityId);

	void update(RedReceive receive);

	List<RedReceive> findByCondition(Integer redId, Integer cityId);

	Long findTotalFeeByCityId(Integer cityId);

}
