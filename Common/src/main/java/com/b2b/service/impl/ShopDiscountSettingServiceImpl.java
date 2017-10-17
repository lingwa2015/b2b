package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopDiscountSettingMapper;
import com.b2b.common.domain.ShopDiscountCustomer;
import com.b2b.common.domain.ShopDiscountCustomerExample;
import com.b2b.common.domain.ShopDiscountCustomerExample.Criteria;
import com.b2b.common.domain.ShopDiscountSetting;
import com.b2b.service.ShopDiscountCustomerService;
import com.b2b.service.ShopDiscountSettingService;

@Service
public class ShopDiscountSettingServiceImpl implements ShopDiscountSettingService {
	
	@Autowired
	private ShopDiscountSettingMapper shopDiscountSettingMapper;
	
	@Autowired
	private ShopDiscountCustomerService shopDiscountCustomerService;
	
	@Override
	public void save(ShopDiscountSetting dto) {
		this.shopDiscountSettingMapper.insert(dto);
	}

	@Override
	public List<ShopDiscountSetting> findAllByCityId(Integer cityId) {
		return this.shopDiscountSettingMapper.findAllByCityId(cityId);
	}

	@Override
	public void changeStatus(ShopDiscountSetting setting) {
		this.shopDiscountSettingMapper.updateByPrimaryKeySelective(setting);
	}

	@Override
	public void changeTypeAndSaveShopDiscountCustomer(Integer shopDiscountId, List<ShopDiscountCustomer> datas) {
		ShopDiscountSetting setting = new ShopDiscountSetting();
		setting.setId(shopDiscountId);
		setting.setShopType(2);
		this.shopDiscountSettingMapper.updateByPrimaryKeySelective(setting);
		this.shopDiscountCustomerService.insert(datas);
	}

	@Override
	public List<ShopDiscountSetting> findByCustomerIdAndCityId(Integer id, Integer cityId) {
		return this.shopDiscountSettingMapper.findByCustomerIdAndCityId(id,cityId);
	}

	@Override
	public List<ShopDiscountSetting> findPreferentialDetailsByConditions(Date startTime, Date endTime, String userName,
			Integer reseauId, Integer cityId) {
		return this.shopDiscountSettingMapper.findPreferentialDetailsByConditions(startTime,endTime,userName,reseauId,cityId);
	}

	@Override
	public Long findCountByConditions(Date startTime, Date endTime, String userName, Integer reseauId, Integer cityId) {
		return this.shopDiscountSettingMapper.findCountByConditions(startTime,endTime,userName,reseauId,cityId);
	}
}
