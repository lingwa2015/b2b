package com.b2b.service;

import com.b2b.common.domain.Advisory;
import com.b2b.page.Page;

public interface AppAdvisoryService {

	void insertSelective(Advisory advisory);

	Page<Advisory> findPage(int currentPage, int pageSize);

	Advisory findById(int id);

	void update(Advisory advisory);
	

}
