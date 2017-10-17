package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.ShopUserSubsidy;

public interface ShopUserSubsidyService {

	ShopUserSubsidy findByUserIdAndShopId(Integer userId, Integer shopId);

	void save(ShopUserSubsidy user);

	List<ShopUserSubsidy> findListByShopIdAndType(Integer shopId, Integer type, Long freeFee);

	void deleteById(Integer id);

	List<ShopUserSubsidy> findListByShopId(Integer shopId);

}
