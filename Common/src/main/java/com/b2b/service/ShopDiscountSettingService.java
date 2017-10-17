package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.ShopDiscountCustomer;
import com.b2b.common.domain.ShopDiscountSetting;

public interface ShopDiscountSettingService {

	void save(ShopDiscountSetting dto);

	List<ShopDiscountSetting> findAllByCityId(Integer cityId);

	void changeStatus(ShopDiscountSetting setting);

	void changeTypeAndSaveShopDiscountCustomer(Integer shopDiscountId, List<ShopDiscountCustomer> datas);

	List<ShopDiscountSetting> findByCustomerIdAndCityId(Integer id, Integer cityId);

	List<ShopDiscountSetting> findPreferentialDetailsByConditions(Date startTime, Date endTime, String userName,
			Integer reseauId, Integer cityId);

	Long findCountByConditions(Date startTime, Date endTime, String userName, Integer reseauId, Integer cityId);

}
