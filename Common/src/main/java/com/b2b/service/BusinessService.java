package com.b2b.service;

import com.b2b.common.domain.Business;
import com.b2b.page.Page;

import java.util.List;


/**
 * 商家操作服务接口。
 * @author luwei
 *
 */
public interface BusinessService {
public void create(Business dto);
	
	public void update(Business dto);
	
	public String delete(int id);
	
	public Business findById(int id);
	
	public Business findByPhone(String mobilePhone);
	
	public List<Business> findBusinessesByCondition(Business business);
	public List<Business> findAll();
	
	public Page<Business> findPage(Business bs , int currentPage ,int pageSize);
	
	public String sendSms(String message);

	public float getSmsBalance();

}
