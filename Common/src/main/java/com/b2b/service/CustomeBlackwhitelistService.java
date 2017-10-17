package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.BlackWhiteListCategory;
import com.b2b.common.domain.BlackWhiteListItem;
import com.b2b.common.domain.BlackWhiteListVariety;
import com.b2b.common.domain.CustomerBlackWhiteList;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.PersonUser;

public interface CustomeBlackwhitelistService {

	void saveAll(PersonUser personUser, int customerId, List<BlackWhiteListVariety> list1,
			List<BlackWhiteListItem> list2, List<BlackWhiteListVariety> list3,
			List<BlackWhiteListItem> list4, List<BlackWhiteListCategory> list5);
	
	CustomerBlackWhiteList findByCustomerIdAndBW(int id,int bw);

	void updateAll(PersonUser personUser, int customerId,
			List<BlackWhiteListVariety> list1, List<BlackWhiteListItem> list2,
			List<BlackWhiteListVariety> list3, List<BlackWhiteListItem> list4,
			List<BlackWhiteListCategory> list5);

}
