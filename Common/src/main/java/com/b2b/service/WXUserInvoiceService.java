package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.WXUserInvoice;

/**
 * 用户开票公司信息
 * */
public interface WXUserInvoiceService {
	
	void create(int wxuserId,String companyName);
	
//	void update(WXUserInvoice dto);
	
	WXUserInvoice findByWXUserId(int wxuserId);
	
	List<WXUserInvoice> findAll();

	WXUserInvoice findById(Integer invoiceid);

	void save(WXUserInvoice invoice);

	void update(int parseInt, String invoiceName);
}
