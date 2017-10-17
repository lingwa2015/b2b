package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.WXUserAddress;

/**
 * 微信用户地址服务
 * */
public interface WXUserAddressService {
	void create(WXUserAddress dto);
	
	void updateDefaultStatusById(int id,int wxuserId);
	
	void updateAddressById(int id,int provinceId,int cityId,int townId,String address,String addressDetails);
	
	void delete(int id);
	
	List<WXUserAddress> findByWXUserId(int wxuserId);
	
	WXUserAddress findById(int id);
	
	List<WXUserAddress> selectByExample(int wxuserId);

	void update(WXUserAddress wxUserAddress);

}
