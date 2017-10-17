package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.BeCustomerBlackWhiteListMapper;
import com.b2b.common.domain.BeBlackWhiteListCategory;
import com.b2b.common.domain.BeBlackWhiteListItem;
import com.b2b.common.domain.BeBlackWhiteListVariety;
import com.b2b.common.domain.BeCustomerBlackWhiteList;
import com.b2b.common.domain.BeCustomerBlackWhiteListExample;
import com.b2b.common.domain.BeCustomerBlackWhiteListExample.Criteria;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.PersonUser;
import com.b2b.service.BeBlackWhiteListCategoryService;
import com.b2b.service.BeBlackWhiteListItemService;
import com.b2b.service.BeBlackWhiteListVarietyService;
import com.b2b.service.BeCustomerBlackWhiteListService;
import com.b2b.service.CustomerService;
import com.b2b.service.UserService;

@Service
public class BeCustomerBlackWhiteListServiceImpl implements
		BeCustomerBlackWhiteListService {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BeCustomerBlackWhiteListMapper beCustomerBlackWhiteListMapper;
	
	@Autowired
	private BeBlackWhiteListVarietyService beBlackWhiteListVarietyService;
	
	@Autowired
	private BeBlackWhiteListCategoryService beBlackWhiteListCategoryService;
	
	@Autowired
	private BeBlackWhiteListItemService beBlackWhiteListItemService;
	
	
	@Override
	public void saveAll(PersonUser user, int customerId,
			List<BeBlackWhiteListVariety> list1,
			List<BeBlackWhiteListVariety> list3, List<BeBlackWhiteListItem> list4,
			List<BeBlackWhiteListCategory> list5,
			List<BeBlackWhiteListCategory> list6) {
		BeCustomerBlackWhiteList customerblackList = findByCustomerIdAndBW(customerId, 1);
		BeCustomerBlackWhiteList customerwhiteList = findByCustomerIdAndBW(customerId, 2);
		CustomerUser customer = customerService.findById(customerId);
		Date date = new Date();
		int blackId;
		int whiteId;
		BeCustomerBlackWhiteList customerBlackList = new BeCustomerBlackWhiteList();
		if(null==customerblackList){
			customerBlackList.setCustomerId(customer.getId());
			customerBlackList.setCustomerName(customer.getUserName());
			customerBlackList.setCreatedTime(date);
			customerBlackList.setCreatedUserid(user.getId());
			customerBlackList.setUpdatedUserid(user.getId());
			customerBlackList.setUpdatedTime(date);
			customerBlackList.setBeBlackwhiteType(1);
			customerBlackList.setStatus(1);
			this.beCustomerBlackWhiteListMapper.insert(customerBlackList);
			blackId=customerBlackList.getBeBlackwhiteId();
		}else{
			blackId=customerblackList.getBeBlackwhiteId();
		}
		BeCustomerBlackWhiteList customerWhiteList = new BeCustomerBlackWhiteList();
		if(null==customerwhiteList){
			customerWhiteList.setCustomerId(customer.getId());
			customerWhiteList.setCustomerName(customer.getUserName());
			customerWhiteList.setCreatedTime(date);
			customerWhiteList.setCreatedUserid(user.getId());
			customerWhiteList.setUpdatedUserid(user.getId());
			customerWhiteList.setUpdatedTime(date);
			customerWhiteList.setBeBlackwhiteType(2);
			customerWhiteList.setStatus(1);
			this.beCustomerBlackWhiteListMapper.insert(customerWhiteList);
			whiteId=customerWhiteList.getBeBlackwhiteId();
		}else{
			whiteId=customerwhiteList.getBeBlackwhiteId();
		}
		
		for (BeBlackWhiteListVariety blackListVariety : list1) {
			blackListVariety.setBeBlackwhiteId(blackId);
			BeBlackWhiteListVariety exist = this.beBlackWhiteListVarietyService.findBlackByVarietyId(customerId,1,blackListVariety.getVarietyId());
			if(null!=exist){
				continue;
			}
			this.beBlackWhiteListVarietyService.create(blackListVariety);
			
		}
//		for (BlackWhiteListItem blackListItem : list2) {
//			blackListItem.setBlackwhiteId(blackId);
//			BlackWhiteListItem exist = this.blackwhitelistItemService.findBlackByItemId(customerId,1,blackListItem.getItemId());
//			if(null!=exist){
//				continue;
//			}
//			this.blackwhitelistItemService.create(blackListItem);
//		}
		
		for (BeBlackWhiteListVariety blackListVariety : list3) {
			blackListVariety.setBeBlackwhiteId(whiteId);
			BeBlackWhiteListVariety exist = this.beBlackWhiteListVarietyService.findBlackByVarietyId(customerId,2,blackListVariety.getVarietyId());
			if(null!=exist){
				continue;
			}
			this.beBlackWhiteListVarietyService.create(blackListVariety);
			
		}
		
		for (BeBlackWhiteListItem blackListItem : list4) {
			blackListItem.setBeBlackwhiteId(whiteId);
			BeBlackWhiteListItem exist = this.beBlackWhiteListItemService.findByItemId(customerId,2,blackListItem.getItemId());
			if(null!=exist){
				continue;
			}
			this.beBlackWhiteListItemService.create(blackListItem);
		}
		
		for (BeBlackWhiteListCategory blackWhiteListCategory : list5) {
			blackWhiteListCategory.setBeBlackwhiteId(blackId);
			BeBlackWhiteListCategory exist = this.beBlackWhiteListCategoryService.findBlackByCategoryId(customerId,1,blackWhiteListCategory.getCategoryId());
			if(null!=exist){
				continue;
			}
			this.beBlackWhiteListCategoryService.create(blackWhiteListCategory);
		}
		
		for (BeBlackWhiteListCategory blackWhiteListCategory : list6) {
			blackWhiteListCategory.setBeBlackwhiteId(whiteId);
			BeBlackWhiteListCategory exist = this.beBlackWhiteListCategoryService.findBlackByCategoryId(customerId,2,blackWhiteListCategory.getCategoryId());
			if(null!=exist){
				continue;
			}
			this.beBlackWhiteListCategoryService.create(blackWhiteListCategory);
		}
	}


	@Override
	public BeCustomerBlackWhiteList findByCustomerIdAndBW(int id, int flag) {
		BeCustomerBlackWhiteListExample example = new BeCustomerBlackWhiteListExample();
		Criteria criteria = example.createCriteria();
		criteria.andCustomerIdEqualTo(id);
		criteria.andBeBlackwhiteTypeEqualTo(flag);
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		List<BeCustomerBlackWhiteList> list = this.beCustomerBlackWhiteListMapper.selectByExample(example);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}


	@Override
	public void updateAll(PersonUser user, int customerId,
			List<BeBlackWhiteListVariety> list1,
			List<BeBlackWhiteListVariety> list3,
			List<BeBlackWhiteListItem> list4,
			List<BeBlackWhiteListCategory> list5,
			List<BeBlackWhiteListCategory> list6) {
		BeCustomerBlackWhiteList customerblackList = findByCustomerIdAndBW(customerId, 1);
		BeCustomerBlackWhiteList customerwhiteList = findByCustomerIdAndBW(customerId, 2);
		CustomerUser customer = customerService.findById(customerId);
		Date date = new Date();
		int blackId;
		int whiteId;
		BeCustomerBlackWhiteList customerBlackList = new BeCustomerBlackWhiteList();
		if(null==customerblackList){
			customerBlackList.setCustomerId(customer.getId());
			customerBlackList.setCustomerName(customer.getUserName());
			customerBlackList.setCreatedTime(date);
			customerBlackList.setCreatedUserid(user.getId());
			customerBlackList.setUpdatedUserid(user.getId());
			customerBlackList.setUpdatedTime(date);
			customerBlackList.setBeBlackwhiteType(1);
			customerBlackList.setStatus(1);
			this.beCustomerBlackWhiteListMapper.insert(customerBlackList);
			blackId=customerBlackList.getBeBlackwhiteId();
		}else{
			customerblackList.setUpdatedTime(date);
			customerblackList.setUpdatedUserid(user.getId());
			blackId=customerblackList.getBeBlackwhiteId();
		}
		BeCustomerBlackWhiteList customerWhiteList = new BeCustomerBlackWhiteList();
		if(null==customerwhiteList){
			customerWhiteList.setCustomerId(customer.getId());
			customerWhiteList.setCustomerName(customer.getUserName());
			customerWhiteList.setCreatedTime(date);
			customerWhiteList.setCreatedUserid(user.getId());
			customerWhiteList.setUpdatedUserid(user.getId());
			customerWhiteList.setUpdatedTime(date);
			customerWhiteList.setBeBlackwhiteType(2);
			customerWhiteList.setStatus(1);
			this.beCustomerBlackWhiteListMapper.insert(customerWhiteList);
			whiteId=customerWhiteList.getBeBlackwhiteId();
		}else{
			customerwhiteList.setUpdatedTime(date);
			customerwhiteList.setUpdatedUserid(user.getId());
			whiteId=customerwhiteList.getBeBlackwhiteId();
		}
		
		this.beBlackWhiteListCategoryService.deleteAll(blackId);
		this.beBlackWhiteListCategoryService.deleteAll(whiteId);
		this.beBlackWhiteListVarietyService.deleteAll(blackId);
		this.beBlackWhiteListVarietyService.deleteAll(whiteId);
		this.beBlackWhiteListItemService.deleteAll(whiteId);
		
		for (BeBlackWhiteListVariety blackListVariety : list1) {
			blackListVariety.setBeBlackwhiteId(blackId);
			BeBlackWhiteListVariety exist = this.beBlackWhiteListVarietyService.findBlackByVarietyId(customerId,1,blackListVariety.getVarietyId());
			if(null!=exist){
				continue;
			}
			this.beBlackWhiteListVarietyService.create(blackListVariety);
			
		}
//		for (BlackWhiteListItem blackListItem : list2) {
//			blackListItem.setBlackwhiteId(blackId);
//			BlackWhiteListItem exist = this.blackwhitelistItemService.findBlackByItemId(customerId,1,blackListItem.getItemId());
//			if(null!=exist){
//				continue;
//			}
//			this.blackwhitelistItemService.create(blackListItem);
//		}
		
		for (BeBlackWhiteListVariety blackListVariety : list3) {
			blackListVariety.setBeBlackwhiteId(whiteId);
			BeBlackWhiteListVariety exist = this.beBlackWhiteListVarietyService.findBlackByVarietyId(customerId,2,blackListVariety.getVarietyId());
			if(null!=exist){
				continue;
			}
			this.beBlackWhiteListVarietyService.create(blackListVariety);
			
		}
		
		for (BeBlackWhiteListItem blackListItem : list4) {
			blackListItem.setBeBlackwhiteId(whiteId);
			BeBlackWhiteListItem exist = this.beBlackWhiteListItemService.findByItemId(customerId,2,blackListItem.getItemId());
			if(null!=exist){
				continue;
			}
			this.beBlackWhiteListItemService.create(blackListItem);
		}
		
		for (BeBlackWhiteListCategory blackWhiteListCategory : list5) {
			blackWhiteListCategory.setBeBlackwhiteId(blackId);
			BeBlackWhiteListCategory exist = this.beBlackWhiteListCategoryService.findBlackByCategoryId(customerId,1,blackWhiteListCategory.getCategoryId());
			if(null!=exist){
				continue;
			}
			this.beBlackWhiteListCategoryService.create(blackWhiteListCategory);
		}
		
		for (BeBlackWhiteListCategory blackWhiteListCategory : list6) {
			blackWhiteListCategory.setBeBlackwhiteId(whiteId);
			BeBlackWhiteListCategory exist = this.beBlackWhiteListCategoryService.findBlackByCategoryId(customerId,2,blackWhiteListCategory.getCategoryId());
			if(null!=exist){
				continue;
			}
			this.beBlackWhiteListCategoryService.create(blackWhiteListCategory);
		}
		
	}


}
