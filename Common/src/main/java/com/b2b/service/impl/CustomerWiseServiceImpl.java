package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.CustomerWiseMapper;
import com.b2b.common.domain.CustomerWise;
import com.b2b.common.domain.CustomerWiseExample;
import com.b2b.common.domain.WeightCoefficient;
import com.b2b.common.domain.CustomerWiseExample.Criteria;
import com.b2b.page.Page;
import com.b2b.service.CustomerWiseService;

@Service
public class CustomerWiseServiceImpl implements CustomerWiseService {
	@Autowired
	private CustomerWiseMapper customerWiseMapper;

	@Override
	public Page<CustomerWise> findPage(int currentPage, int pageSize) {
		CustomerWiseExample example = new CustomerWiseExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		int count = customerWiseMapper.countByExample(example);
		if(count ==0){
			return new Page<CustomerWise>();
		}
		int start = Page.calStartRow(currentPage, pageSize);
		example.setLimit(pageSize);
		example.setStart(start);
		example.setLimitFlag(true);
		List<CustomerWise> list = customerWiseMapper.selectByExample(example);
		Page<CustomerWise> page = new Page<CustomerWise>(currentPage,count,pageSize,list);
		return page;
	}
	
	@Override
	public List<CustomerWise> selectByExample(CustomerWise customerWise){
		CustomerWiseExample example = new CustomerWiseExample();
		Criteria criteria = example.createCriteria();
		if(customerWise.getCustomerId()!=null){
			criteria.andCustomerIdEqualTo(customerWise.getCustomerId());
		}
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		return this.customerWiseMapper.selectByExample(example);
	}

	@Override
	public CustomerWise findById(Integer id) {
		return this.customerWiseMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(CustomerWise dto) {
		dto.setStatus(Constant.VALID_STATUS);
		this.customerWiseMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public void create(CustomerWise dto) {
		dto.setStatus(Constant.VALID_STATUS);
		this.customerWiseMapper.insert(dto);
	}

	@Override
	public void delete(CustomerWise customerWise) {
		this.customerWiseMapper.updateByPrimaryKeySelective(customerWise);
	}
	
	@Override
	public List<CustomerWise> getCustomerWiseInfo(int week){
		return this.customerWiseMapper.getCustomerWiseInfo(week);
	}
}
