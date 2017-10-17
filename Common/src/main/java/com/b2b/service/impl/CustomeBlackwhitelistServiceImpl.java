package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.CustomerBlackWhiteListMapper;
import com.b2b.common.domain.BlackWhiteListCategory;
import com.b2b.common.domain.BlackWhiteListItem;
import com.b2b.common.domain.BlackWhiteListVariety;
import com.b2b.common.domain.CustomerBlackWhiteList;
import com.b2b.common.domain.CustomerBlackWhiteListExample;
import com.b2b.common.domain.CustomerBlackWhiteListExample.Criteria;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.PersonUser;
import com.b2b.service.BlackwhitelistCategoryService;
import com.b2b.service.BlackwhitelistItemService;
import com.b2b.service.BlackwhitelistVarietyService;
import com.b2b.service.CustomeBlackwhitelistService;
import com.b2b.service.CustomerService;
import com.b2b.service.UserService;

@Service
public class CustomeBlackwhitelistServiceImpl implements
		CustomeBlackwhitelistService {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerBlackWhiteListMapper customerBlackWhiteListMapper;
	
	@Autowired
	private BlackwhitelistItemService blackwhitelistItemService;
	
	@Autowired
	private BlackwhitelistVarietyService blackwhitelistVarietyService;
	
	@Autowired
	private BlackwhitelistCategoryService blackwhitelistCategoryService;
	
	@Override
	public void saveAll(PersonUser personUser, int customerId,
			List<BlackWhiteListVariety> list1, List<BlackWhiteListItem> list2,
			List<BlackWhiteListVariety> list3, List<BlackWhiteListItem> list4,
			List<BlackWhiteListCategory> list5) {
		CustomerBlackWhiteList customerblackList = findByCustomerIdAndBW(customerId, 1);
		CustomerBlackWhiteList customerwhiteList = findByCustomerIdAndBW(customerId, 2);
		CustomerUser customer = customerService.findById(customerId);
		Date date = new Date();
		int blackId;
		int whiteId;
		CustomerBlackWhiteList customerBlackList = new CustomerBlackWhiteList();
		if(null==customerblackList){
			customerBlackList.setCustomerId(customer.getId());
			customerBlackList.setCustomerName(customer.getUserName());
			customerBlackList.setCreatedTime(date);
			customerBlackList.setCreatedUserid(personUser.getId());
			customerBlackList.setUpdatedUserid(personUser.getId());
			customerBlackList.setUpdatedTime(date);
			customerBlackList.setBlackwhiteType(1);
			customerBlackList.setStatus(1);
			this.customerBlackWhiteListMapper.insert(customerBlackList);
			blackId=customerBlackList.getBlackwhiteId();
		}else{
			blackId=customerblackList.getBlackwhiteId();
		}
		CustomerBlackWhiteList customerWhiteList = new CustomerBlackWhiteList();
		if(null==customerwhiteList){
			customerWhiteList.setCustomerId(customer.getId());
			customerWhiteList.setCustomerName(customer.getUserName());
			customerWhiteList.setCreatedTime(date);
			customerWhiteList.setCreatedUserid(personUser.getId());
			customerWhiteList.setUpdatedUserid(personUser.getId());
			customerWhiteList.setUpdatedTime(date);
			customerWhiteList.setBlackwhiteType(2);
			customerWhiteList.setStatus(1);
			this.customerBlackWhiteListMapper.insert(customerWhiteList);
			whiteId=customerWhiteList.getBlackwhiteId();
		}else{
			whiteId=customerwhiteList.getBlackwhiteId();
		}
		
		for (BlackWhiteListVariety blackListVariety : list1) {
			blackListVariety.setBlackwhiteId(blackId);
			BlackWhiteListVariety exist = this.blackwhitelistVarietyService.findBlackByVarietyId(customerId,1,blackListVariety.getVarietyId());
			if(null!=exist){
				continue;
			}
			this.blackwhitelistVarietyService.create(blackListVariety);
			
		}
		for (BlackWhiteListItem blackListItem : list2) {
			blackListItem.setBlackwhiteId(blackId);
			BlackWhiteListItem exist = this.blackwhitelistItemService.findBlackByItemId(customerId,1,blackListItem.getItemId());
			if(null!=exist){
				continue;
			}
			this.blackwhitelistItemService.create(blackListItem);
		}
		
		for (BlackWhiteListVariety blackListVariety : list3) {
			blackListVariety.setBlackwhiteId(whiteId);
			BlackWhiteListVariety exist = this.blackwhitelistVarietyService.findBlackByVarietyId(customerId,2,blackListVariety.getVarietyId());
			if(null!=exist){
				continue;
			}
			this.blackwhitelistVarietyService.create(blackListVariety);
			
		}
		
		for (BlackWhiteListItem blackListItem : list4) {
			blackListItem.setBlackwhiteId(whiteId);
			BlackWhiteListItem exist = this.blackwhitelistItemService.findBlackByItemId(customerId,2,blackListItem.getItemId());
			if(null!=exist){
				continue;
			}
			this.blackwhitelistItemService.create(blackListItem);
		}
		
		for (BlackWhiteListCategory blackWhiteListCategory : list5) {
			blackWhiteListCategory.setBlackwhiteId(blackId);
			BlackWhiteListCategory exist = this.blackwhitelistCategoryService.findBlackByItemId(customerId,1,blackWhiteListCategory.getCategoryId());
			if(null!=exist){
				continue;
			}
			this.blackwhitelistCategoryService.create(blackWhiteListCategory);
		}
		
	}

	@Override
	public CustomerBlackWhiteList findByCustomerIdAndBW(int id, int bw) {
		CustomerBlackWhiteListExample example = new CustomerBlackWhiteListExample();
		Criteria criteria = example.createCriteria();
		criteria.andCustomerIdEqualTo(id);
		criteria.andBlackwhiteTypeEqualTo(bw);
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		List<CustomerBlackWhiteList> list = this.customerBlackWhiteListMapper.selectByExample(example);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public void updateAll(PersonUser personUser, int customerId,
			List<BlackWhiteListVariety> list1, List<BlackWhiteListItem> list2,
			List<BlackWhiteListVariety> list3, List<BlackWhiteListItem> list4,
			List<BlackWhiteListCategory> list5) {
		CustomerBlackWhiteList customerblackList = findByCustomerIdAndBW(customerId, 1);
		CustomerBlackWhiteList customerwhiteList = findByCustomerIdAndBW(customerId, 2);
		CustomerUser customer = customerService.findById(customerId);
		Date date = new Date();
		int blackId;
		int whiteId;
		CustomerBlackWhiteList customerBlackList = new CustomerBlackWhiteList();
		if(null==customerblackList){
			customerBlackList.setCustomerId(customer.getId());
			customerBlackList.setCustomerName(customer.getUserName());
			customerBlackList.setCreatedTime(date);
			customerBlackList.setCreatedUserid(personUser.getId());
			customerBlackList.setUpdatedUserid(personUser.getId());
			customerBlackList.setUpdatedTime(date);
			customerBlackList.setBlackwhiteType(1);
			customerBlackList.setStatus(1);
			this.customerBlackWhiteListMapper.insert(customerBlackList);
			blackId=customerBlackList.getBlackwhiteId();
		}else{
			customerblackList.setUpdatedTime(date);
			customerblackList.setUpdatedUserid(personUser.getId());
			blackId=customerblackList.getBlackwhiteId();
		}
		CustomerBlackWhiteList customerWhiteList = new CustomerBlackWhiteList();
		if(null==customerwhiteList){
			customerWhiteList.setCustomerId(customer.getId());
			customerWhiteList.setCustomerName(customer.getUserName());
			customerWhiteList.setCreatedTime(date);
			customerWhiteList.setCreatedUserid(personUser.getId());
			customerWhiteList.setUpdatedUserid(personUser.getId());
			customerWhiteList.setUpdatedTime(date);
			customerWhiteList.setBlackwhiteType(2);
			customerWhiteList.setStatus(1);
			this.customerBlackWhiteListMapper.insert(customerWhiteList);
			whiteId=customerWhiteList.getBlackwhiteId();
		}else{
			customerwhiteList.setUpdatedTime(date);
			customerwhiteList.setUpdatedUserid(personUser.getId());
			whiteId=customerwhiteList.getBlackwhiteId();
		}
		
		this.blackwhitelistVarietyService.deleteAll(blackId);
		this.blackwhitelistVarietyService.deleteAll(whiteId);
		this.blackwhitelistCategoryService.deleteAll(blackId);
		this.blackwhitelistItemService.deleteAll(blackId);
		this.blackwhitelistItemService.deleteAll(whiteId);
		
		for (BlackWhiteListVariety blackListVariety : list1) {
			blackListVariety.setBlackwhiteId(blackId);
			BlackWhiteListVariety exist = this.blackwhitelistVarietyService.findBlackByVarietyId(customerId,1,blackListVariety.getVarietyId());
			if(null!=exist){
				continue;
			}
			this.blackwhitelistVarietyService.create(blackListVariety);
			
		}
		for (BlackWhiteListItem blackListItem : list2) {
			blackListItem.setBlackwhiteId(blackId);
			BlackWhiteListItem exist = this.blackwhitelistItemService.findBlackByItemId(customerId,1,blackListItem.getItemId());
			if(null!=exist){
				continue;
			}
			this.blackwhitelistItemService.create(blackListItem);
		}
		
		for (BlackWhiteListVariety blackListVariety : list3) {
			blackListVariety.setBlackwhiteId(whiteId);
			BlackWhiteListVariety exist = this.blackwhitelistVarietyService.findBlackByVarietyId(customerId,2,blackListVariety.getVarietyId());
			if(null!=exist){
				continue;
			}
			this.blackwhitelistVarietyService.create(blackListVariety);
			
		}
		
		for (BlackWhiteListItem blackListItem : list4) {
			blackListItem.setBlackwhiteId(whiteId);
			BlackWhiteListItem exist = this.blackwhitelistItemService.findBlackByItemId(customerId,2,blackListItem.getItemId());
			if(null!=exist){
				continue;
			}
			this.blackwhitelistItemService.create(blackListItem);
		}
		
		for (BlackWhiteListCategory blackWhiteListCategory : list5) {
			blackWhiteListCategory.setBlackwhiteId(blackId);
			BlackWhiteListCategory exist = this.blackwhitelistCategoryService.findBlackByItemId(customerId,1,blackWhiteListCategory.getCategoryId());
			if(null!=exist){
				continue;
			}
			this.blackwhitelistCategoryService.create(blackWhiteListCategory);
		}
	}

}
