package com.b2b.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.StorageItemMapper;
import com.b2b.common.dao.SupplierMapper;
import com.b2b.common.domain.Business;
import com.b2b.common.domain.ItemSupplier;
import com.b2b.common.domain.ItemSupplierExample;
import com.b2b.common.domain.Supplier;
import com.b2b.common.domain.SupplierExample;
import com.b2b.common.domain.SupplierExample.Criteria;
import com.b2b.page.Page;
import com.b2b.service.ItemService;
import com.b2b.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	SupplierMapper supplierMapper;
	
	@Override
	public Page<Supplier> findPage(Supplier sup, int currentPage, int pageSize) {
		String supplierName = sup.getSupplierName();
		String mobilePhone = sup.getMobilePhone();
		
		SupplierExample example = new SupplierExample();
		example.setOrderByClause("created_time desc");
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		
		Criteria criteria1 = example.createCriteria();
		criteria1.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andCityIdEqualTo(sup.getCityId());
		criteria1.andCityIdEqualTo(sup.getCityId());
		//如果搜索条件中名称不为空
		if(StringUtils.isNotBlank(supplierName)){
			criteria.andSupplierNameLike("%"+supplierName+"%");
			criteria1.andLinkmanNameLike("%"+supplierName+"%");
		}
		
		//如果搜索条件中手机号不为空
		if(StringUtils.isNotBlank(mobilePhone)){
			criteria.andMobilePhoneLike("%"+mobilePhone+"%");
			criteria1.andMobilePhoneLike("%"+mobilePhone+"%");
		}
		example.or(criteria1);
		int count = supplierMapper.countByExample(example);
		if(count ==0){
			return new Page<Supplier>();
		}
		int start = Page.calStartRow(currentPage, pageSize);
		example.setLimit(pageSize);
		example.setStart(start);
		example.setLimitFlag(true);
		List<Supplier> list = supplierMapper.selectByExample(example);
		Page<Supplier> page = new Page<Supplier>(currentPage,count,pageSize,list);
		return page;
	}
	
	@Override
	public List<Supplier> getSupplierList()
	{
		SupplierExample example = new SupplierExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		List<Supplier> list = supplierMapper.selectByExample(example);
		return list;
	}

	@Override  
	public void create(Supplier dto) {
		dto.setStatus(Constant.VALID_STATUS);
		dto.setCreatedTime(new Date());
		supplierMapper.insert(dto);
	}

	@Override
	public void update(Supplier dto) {
		dto.setStatus(Constant.VALID_STATUS);
		supplierMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public void delete(int id) {
		Supplier dto=this.findById(id);
		if(dto!=null){
			dto.setStatus(Constant.DELETE_STATUS);
			supplierMapper.updateByPrimaryKey(dto);
		}
	}

	@Override
	public Supplier findById(int id) {
		return supplierMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Supplier> findByItemId(int id) {
		return this.supplierMapper.findByItemId(id);
	}

	@Override
	public List<HashMap<String, Object>> findBySupplierId(int id) {
		return this.supplierMapper.findBySupplierId(id);
	}

	@Override
	public List<HashMap<String, Object>> findItemBySupplierId(
			Integer supplier_id) {
		return this.supplierMapper.findItemBySupplierId(supplier_id);
	}

	@Override
	public List<Supplier> findByCityId(Integer cityId) {
		SupplierExample example = new SupplierExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andCityIdEqualTo(cityId);
		List<Supplier> list = supplierMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<HashMap<String, Object>> findByLikeUserNameAndCityId(String userName, Integer cityId) {
		return this.supplierMapper.findByLikeUserNameAndCityId(userName, cityId);
	}

}
