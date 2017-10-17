package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zx.com.sms.client.Client;

import com.b2b.Constant;
import com.b2b.common.dao.BusinessMapper;
import com.b2b.common.domain.Business;
import com.b2b.common.domain.BusinessExample;
import com.b2b.common.domain.BusinessExample.Criteria;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.PersonUser;
import com.b2b.page.Page;
import com.b2b.service.BusinessService;
import com.b2b.service.CustomerService;
import com.b2b.service.UserService;

@Service
public class BusinessServiceImpl implements BusinessService {
	
	
	@Autowired
	BusinessMapper businessMapper;
	
	@Autowired
	CustomerService customerService;
	
	
	public static String regCode="ZXHD-CRM-0100-SLMMZC";
	public static String pwd="97662511";
	

	@Override
	public void create(Business dto) {

		dto.setStatus(Constant.VALID_STATUS);
		dto.setCreatedTime(new Date());
		businessMapper.insert(dto);
	}

	@Override
	public void update(Business dto) {
		dto.setStatus(Constant.VALID_STATUS);
		businessMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public String delete(int id) {
		Business dto=this.findById(id);
		if(dto!=null){
		dto.setStatus(Constant.DELETE_STATUS);
		businessMapper.updateByPrimaryKey(dto);
		CustomerUser personUser=customerService.findByPhone(dto.getMobilePhone());
		if(personUser!=null){
			personUser.setBusinessId(0);
			personUser.setManagershopid(0);
			customerService.update(personUser);
		}
			
		}
		return null;
	}

	@Override
	public Business findById(int id) {
		return businessMapper.selectByPrimaryKey(id);
	}

	@Override
	public Business findByPhone(String mobilePhone) {
		BusinessExample example=new BusinessExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andMobilePhoneEqualTo(mobilePhone);
		List<Business> businesses=businessMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(businesses)){
			return businesses.get(0);
		}
		
		return null;
	}

	@Override
	public List<Business> findBusinessesByCondition(Business business) {
		if(business==null)
			return null;
		BusinessExample example=new BusinessExample();
		Criteria criteria = example.createCriteria();
		criteria.andMobilePhoneEqualTo(business.getMobilePhone());
		return businessMapper.selectByExample(example);
	}
	
	@Override
	public List<Business> findAll(){
		BusinessExample example=new BusinessExample();
		Criteria criteria=example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		return businessMapper.selectByExample(example);
	}

	@Override
	public Page<Business> findPage(Business bs, int currentPage, int pageSize) {
		BusinessExample example=new BusinessExample();
		example.setOrderByClause("Id desc");
		Criteria criteria = example.createCriteria();
		String businessName=bs.getBusinessName();
		String mobilePhone=bs.getMobilePhone();
		
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		//
		//如果搜索条件中用户姓名不为空
				if(StringUtils.isNotBlank(businessName)){
					criteria.andBusinessNameEqualTo(businessName);
				}
						
				//如果搜索条件中手机号不为空
				if(StringUtils.isNotBlank(mobilePhone)){
					criteria.andMobilePhoneEqualTo(mobilePhone);
				}
				
				int count = businessMapper.countByExample(example);
				
				if(count ==0){
					return new Page<Business>();
				}
		
		int start = Page.calStartRow(currentPage, pageSize);
		example.setLimit(pageSize);
		example.setStart(start);
		example.setLimitFlag(true);
		List<Business> list=businessMapper.selectByExample(example);
		Page<Business> page=new Page<Business>(currentPage, count, pageSize, list);
		return page;
	}
	
	@Override
	public String sendSms(String message) {
		String result="";
		BusinessExample example = new BusinessExample();
		example.setOrderByClause("Id desc");


		int count = businessMapper.countByExample(example);

		if(count ==0){
			return "用户数量为空";
		}

		int currentPage=1;
		int pageSize=100;

	    int c=	count/pageSize;

	    String mobiles="";


	   Client zx=new Client(regCode, pwd);
	   zx.register(regCode, pwd,"杭州冷恋科技有限公司","冷恋","常州科教城信息产业园","","","","","","");

//	   String  checkReuslt= zx.channelcheck(regCode, pwd, message);
//
//	 if(!checkReuslt.isEmpty())
//	 {
//		 return checkReuslt;
//	 }
//

	     for(int i=0;i<c+1;i++)
	     {

	    		int start = Page.calStartRow(currentPage, pageSize);
	    		example.setLimit(pageSize);
	    		example.setStart(start);
	    		example.setLimitFlag(true);

	    		List<Business> list = businessMapper.selectByExample(example);

	    		for(Business business:list)
	    		{
	    			String pattern="^1\\d{10}";
	    		if(!business.getMobilePhone().isEmpty())
	    		{
	    			if(business.getMobilePhone().matches (pattern))
	    			{
	    				mobiles=mobiles+","+business.getMobilePhone();
	    			}
	    		}

	    		}
				result = zx.sendSMS(regCode, pwd, mobiles, message, "", "1", "", "1", "", "4");
	     }

	     zx.logout(regCode, pwd);

		return result;
	}

	@Override
	public float getSmsBalance() {
		Client zx=new Client("ZXHD-CRM-0100-SLMMZC", "97662511");
		 float balance= zx.getbalance("ZXHD-CRM-0100-SLMMZC",  "97662511");
		 return balance;
	}


}
