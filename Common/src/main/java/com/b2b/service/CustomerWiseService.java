package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.CustomerWise;
import com.b2b.common.domain.CustomerWiseExample;
import com.b2b.page.Page;

public interface CustomerWiseService {

	Page<CustomerWise> findPage(int currentPage, int pageSize);

	CustomerWise findById(Integer id);

	void update(CustomerWise dto);

	void create(CustomerWise dto);

	void delete(CustomerWise customerWise);

    List<CustomerWise> getCustomerWiseInfo(int week);
    
    List<CustomerWise> selectByExample(CustomerWise customerWise);
}
