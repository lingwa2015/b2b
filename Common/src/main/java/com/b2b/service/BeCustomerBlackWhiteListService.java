package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.BeBlackWhiteListCategory;
import com.b2b.common.domain.BeBlackWhiteListItem;
import com.b2b.common.domain.BeBlackWhiteListVariety;
import com.b2b.common.domain.BeCustomerBlackWhiteList;
import com.b2b.common.domain.PersonUser;

public interface BeCustomerBlackWhiteListService {

	void saveAll(PersonUser user, int customerId, List<BeBlackWhiteListVariety> list1,
			List<BeBlackWhiteListVariety> list3, List<BeBlackWhiteListItem> list4,
			List<BeBlackWhiteListCategory> list5,
			List<BeBlackWhiteListCategory> list6);
	BeCustomerBlackWhiteList findByCustomerIdAndBW(int id,int flag);
	void updateAll(PersonUser user, int customerId,
			List<BeBlackWhiteListVariety> list1,
			List<BeBlackWhiteListVariety> list3,
			List<BeBlackWhiteListItem> list4,
			List<BeBlackWhiteListCategory> list5,
			List<BeBlackWhiteListCategory> list6);
}
