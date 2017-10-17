package com.b2b.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.CustomerUserMapper;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.CustomerUserExample;
import com.b2b.common.domain.CustomerUserExample.Criteria;
import com.b2b.common.domain.WXAccount;
import com.b2b.common.util.EncryptHelper;
import com.b2b.page.Page;
import com.b2b.service.CustomerService;
import com.b2b.service.CustomerWiseService;
import com.b2b.service.ShopItemService;
import com.b2b.service.WXAccountService;
import com.b2b.service.WXRechargeRecordService;
import com.b2b.service.WXUserService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerUserMapper customerUserMapper;
	
	@Autowired
	CustomerWiseService customerWiseService;
	
	@Autowired
	private WXAccountService wxAccountService;
	
	@Autowired
	private WXUserService wxUserService;
	
	@Autowired
	private ShopItemService shopItemService;
	
	@Autowired
	private WXRechargeRecordService wxRechargeRecordService;

	public static String regCode="ZXHD-CRM-0100-SLMMZC";
	public static String pwd="97662511";

	@Override
	public void create(CustomerUser dto) {
		dto.setStatus(Constant.VALID_STATUS);
		String password="000000";
		//密码MD5加密
		dto.setPassWord(EncryptHelper.md5(password));

		customerUserMapper.insert(dto);
	}

	@Override
	public void update(CustomerUser dto) {
		dto.setStatus(Constant.VALID_STATUS);
		CustomerUser user = this.customerUserMapper.selectByPrimaryKey(dto.getId());
		customerUserMapper.updateByPrimaryKeySelective(dto);
		if(null!=user.getShopDiscount() && null!=dto.getShopDiscount()){
			if(!user.getShopDiscount().equals(dto.getShopDiscount())){
				double a = dto.getShopDiscount().doubleValue();
				if(a==1){
					this.shopItemService.changeSalePriceToSourcingPrice(dto.getId());
				}else{
					this.shopItemService.changeSalePrice(dto.getId(),dto.getShopDiscount().doubleValue());
				}
			}
		}
		
	}

	@Override
	public String delete(CustomerUser dto) {

		dto.setStatus(Constant.DELETE_STATUS);
		customerUserMapper.updateByPrimaryKey(dto);
		return null;
	}

	@Override
	public CustomerUser findById(int id) {
		return customerUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public CustomerUser findByPhone(String phone) {
		CustomerUserExample example = new CustomerUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andMobilePhoneEqualTo(phone);

		List<CustomerUser> personUsers = customerUserMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(personUsers)){
			return personUsers.get(0);
		}

		return null;
	}
	
	@Override
	public CustomerUser findByUserName(String userName) {
		CustomerUserExample example = new CustomerUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andUserNameEqualTo(userName);
		List<CustomerUser> personUsers = customerUserMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(personUsers)){
			return personUsers.get(0);
		}

		return null;
	}

	@Override
	public CustomerUser findByLogin(String userName, String password) {
		CustomerUserExample example = new CustomerUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		if (StringUtils.isNotBlank(userName)) {
			criteria.andUserNameEqualTo(userName);
		}
		if (StringUtils.isNotBlank(password)) {
			criteria.andPassWordEqualTo(EncryptHelper.md5(password));
		}else{
			return null;
		}
		List<CustomerUser> personUsers = customerUserMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(personUsers)){
			return personUsers.get(0);
		}

		return null;
	}

	@Override
	public Page<CustomerUser> findPage(CustomerUser personUser, int currentPage, int pageSize,int businessId,Date startTime,Date endTime) {
		String userName = personUser.getUserName();
		String mobilePhone = personUser.getMobilePhone();

		CustomerUserExample example = new CustomerUserExample();
		example.setOrderByClause("created_time desc");

		//拼凑or条件
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);

		Criteria criteria1 = example.createCriteria();
		criteria1.andStatusEqualTo(Constant.VALID_STATUS);

		//如果搜索条件中用户姓名不为空
		if(StringUtils.isNotBlank(userName)){
			criteria.andUserNameLike("%"+userName+"%");
			criteria1.andCompanyNameLike("%"+userName+"%");
		}

		//如果搜索条件中手机号不为空
		if(StringUtils.isNotBlank(mobilePhone)){
			//criteria.andMobilePhoneEqualTo(mobilePhone);
			criteria.andMobilePhoneLike("%"+mobilePhone+"%");
			criteria1.andMobilePhoneLike("%"+mobilePhone+"%");
		}
		
		if (startTime != null) {
			criteria.andListTimeGreaterThanOrEqualTo(startTime);
			criteria1.andListTimeGreaterThanOrEqualTo(startTime);
		}
		
		if (endTime != null) {
			criteria.andListTimeLessThanOrEqualTo(endTime);
			criteria1.andListTimeLessThanOrEqualTo(endTime);
		}

		if(businessId>0){
			criteria.andBusinessIdEqualTo(businessId);
		    criteria1.andBusinessIdEqualTo(businessId);
		}
		example.or(criteria1);
		int count = customerUserMapper.countByExample(example);

		if(count ==0){
			return new Page<CustomerUser>();
		}


		int start = Page.calStartRow(currentPage, pageSize);
		example.setLimit(pageSize);
		example.setStart(start);
		example.setLimitFlag(true);

		List<CustomerUser> list = customerUserMapper.selectByExample(example);

		Page<CustomerUser> page = new Page<CustomerUser>(currentPage,count,pageSize,list);

		return page;
	}

	@Override
	public String check(CustomerUser dto) {
		return null;
	}

	@Override
	public List<CustomerUser> findUsersByCondition(CustomerUser personUser) {

		if(personUser == null){
			return null;
		}

		CustomerUserExample example = new CustomerUserExample();
		Criteria criteria = example.createCriteria();
		String mobilePhone=personUser.getMobilePhone();
		String companyName=personUser.getCompanyName();
		String userName = personUser.getUserName();
		if(mobilePhone!=null){
			criteria.andMobilePhoneEqualTo(mobilePhone);
		}else if(companyName!=null){
			criteria.andCompanyNameLike("%"+companyName+"%");
			criteria.andStatusEqualTo(1);
		}else if(userName!=null){
			criteria.andUserNameLike("%"+userName+"%");
			criteria.andStatusEqualTo(1);
		}
		criteria.andIswxvipNotEqualTo(2);
		criteria.andStatusNotEqualTo(2);
		return customerUserMapper.selectByExample(example);
	}

	@Override
	public String setAdmin(CustomerUser dto) {
		dto.setIsadmin(Constant.Admin_User);
		customerUserMapper.updateByPrimaryKey(dto);
		return null;
	}

	@Override
	public String unSetAdmin(CustomerUser dto) {
		dto.setIsadmin(Constant.Common_User);
		customerUserMapper.updateByPrimaryKey(dto);
		return null;
	}

//	@Override
//	public String sendSms(String message) {
//
//		String result="";
//		CustomerUserExample example = new CustomerUserExample();
//		example.setOrderByClause("Id desc");
//
//
//		int count = customerUserMapper.countByExample(example);
//
//		if(count ==0){
//			return "用户数量为空";
//		}
//
//		int currentPage=1;
//		int pageSize=100;
//
//	    int c=	count/pageSize;
//
//	    String mobiles="";
//
//
//	   Client zx=new Client(regCode, pwd);
//	   zx.register(regCode, pwd,"杭州冷恋科技有限公司","冷恋","常州科教城信息产业园","","","","","","");
//
////	   String  checkReuslt= zx.channelcheck(regCode, pwd, message);
////
////	 if(!checkReuslt.isEmpty())
////	 {
////		 return checkReuslt;
////	 }
////
//
//	     for(int i=0;i<c+1;i++)
//	     {
//
//	    		int start = Page.calStartRow(currentPage, pageSize);
//	    		example.setLimit(pageSize);
//	    		example.setStart(start);
//	    		example.setLimitFlag(true);
//
//	    		List<CustomerUser> list = customerUserMapper.selectByExample(example);
//
//	    		for(CustomerUser personUser:list)
//	    		{
//	    			String pattern="^1\\d{10}";
//	    		if(!personUser.getMobilePhone().isEmpty())
//	    		{
//	    			if(personUser.getMobilePhone().matches (pattern))
//	    			{
//	    				mobiles=mobiles+","+personUser.getMobilePhone();
//	    			}
//	    		}
//
//	    		}
//				result = zx.sendSMS(regCode, pwd, mobiles, message, "", "1", "", "1", "", "4");
//	     }
//
//	     zx.logout(regCode, pwd);
//
//		return result;
//	}
//
//	@Override
//	public float getSmsBalance() {
//		 Client zx=new Client("ZXHD-CRM-0100-SLMMZC", "97662511");
//		 float balance= zx.getbalance("ZXHD-CRM-0100-SLMMZC",  "97662511");
//		 return balance;
//	}

	@Override
	public List<HashMap<String, Object>> findByLikeUserNameAndCityId(String name,Integer cityId) {
		return this.customerUserMapper.findByLikeUserNameAndCityId(name,cityId);
	}

	@Override
	public void insertVIP(CustomerUser user,Integer id) {
		user.setStatus(1);
		this.customerUserMapper.insert(user);
		WXAccount wxAccount = new WXAccount();
		wxAccount.setId(user.getId());
		wxAccount.setMoney(0l);
		wxAccount.setCreatedTime(new Date());
		wxAccount.setUpdatedTime(wxAccount.getCreatedTime());
		this.wxAccountService.create(wxAccount);
		this.wxUserService.updateCID(id,user.getId());
	}

	@Override
	public List<CustomerUser> findAll() {
		CustomerUserExample example = new CustomerUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		return this.customerUserMapper.selectByExample(example);
	}

	@Override
	public void createRecharge(String id, int chargeMoney, int freeMoney,String orderNo) {
		wxRechargeRecordService.save(id, chargeMoney, freeMoney, orderNo);
		wxRechargeRecordService.changeStatus(orderNo);
	}

	@Override
	public Page<CustomerUser> findPageWithMoney(CustomerUser personUser, int currentPage, int pageSize, int businessId, int param, Integer regionId, String sort, String grade, String flag, String buyWay, String payBillWay, Integer reseauId, Integer cityId, Integer isdelete, String activationId) {
		String userName = personUser.getUserName();
		String mobilePhone = personUser.getMobilePhone();
		String linkName = personUser.getLikeman();

		int count = this.customerUserMapper.countByCondition(linkName,userName,mobilePhone,businessId,param,regionId,grade,flag,buyWay,payBillWay,reseauId, cityId, isdelete, activationId);
		
		if(count ==0){
			return new Page<CustomerUser>();
		}

		int start = Page.calStartRow(currentPage, pageSize);
		
		List<CustomerUser> list = customerUserMapper.selectByConditionWithMoney(linkName,userName,mobilePhone,businessId,param,regionId,sort,grade,flag,buyWay,payBillWay,reseauId,pageSize,start,cityId, isdelete, activationId);

		//List<CustomerUser> list = customerUserMapper.selectByExample(example);

		Page<CustomerUser> page = new Page<CustomerUser>(currentPage,count,pageSize,list);

		return page;
	}

	@Override
	public List<CustomerUser> findList() {
		CustomerUserExample example = new CustomerUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andIswxvipNotEqualTo(2);
		return this.customerUserMapper.selectByExample(example);
	}

	@Override
	public List<HashMap<String, Object>> findByName(String userName,
			Integer flag) {
		return this.customerUserMapper.findByName(userName,flag);
	}

	@Override
	public List<HashMap<String, Object>> findAllShop() {
		return this.customerUserMapper.findAllShop();
	}

	@Override
	public CustomerUser findByIdAndState(Integer customerUserId) {
		return this.customerUserMapper.findByIdAndState(customerUserId);
	}

	@Override
	public List<CustomerUser> findShops() {
		return this.customerUserMapper.findShops();
	}

	@Override
	public List<HashMap<String, Object>> findAllShopByCondition(String name,Integer cityId) {
		return this.customerUserMapper.findAllShopByCondition(name,cityId);
	}

	@Override
	public List<CustomerUser> isCompanyExist(CustomerUser personUser) {
		return this.customerUserMapper.isCompanyExist(personUser);
	}

	@Override
	public Integer findAllShopNum() {
		return this.customerUserMapper.findAllShopNum();
	}

	@Override
	public List<CustomerUser> findFreeShops() {
		return this.customerUserMapper.findFreeShops();
	}

	@Override
	public List<CustomerUser> isUserNameExist(CustomerUser personUser) {
		return this.customerUserMapper.isUserNameExist(personUser);
	}

	@Override
	public List<CustomerUser> isCompanyNameExist(CustomerUser personUser) {
		return this.customerUserMapper.isCompanyNameExist(personUser);
	}

	@Override
	public List<CustomerUser> isExist(CustomerUser personUser) {
		return this.customerUserMapper.isExist(personUser);
	}

	@Override
	public CustomerUser findCheckedDay(Integer shopId) {
		return this.customerUserMapper.findCheckedDay(shopId);
	}

	@Override
	public void deleteReseauId(Integer id) {
		this.customerUserMapper.deleteReseauId(id);
	}

	@Override
	public int findFreeShopNumByReseauId(Integer id) {
		return this.customerUserMapper.findFreeShopNumByReseauId(id);
	}

	@Override
	public CustomerUser findPrintOrdersInfo(Integer userId) {
		return this.customerUserMapper.findPrintOrdersInfo(userId);
	}

	@Override
	public CustomerUser findActivenessById(Integer shopId) {
		return this.customerUserMapper.findActivenessById(shopId);
	}

	@Override
	public List<Integer> findIdsByUserName(String userName) {
		return this.customerUserMapper.findIdsByUserName(userName);
	}

	@Override
	public List<String> findByLikeNameAndCityId(String name, Integer cityId) {
		return this.customerUserMapper.findByLikeNameAndCityId(name,cityId);
	}

	@Override
	public List<CustomerUser> findtichengUserByCityId(Integer cityId) {
		return this.customerUserMapper.findtichengUserByCityId(cityId);
	}
	
	/**
	 * 截止上月底开通的店铺数
	 */
	@Override
	public List<CustomerUser> findOpenShopBeforeLastMonth(Date date,Integer cityId) {
		return this.customerUserMapper.findOpenShopBeforeLastMonth(date,cityId);
	}

	@Override
	public List<CustomerUser> findOpenFreeShopBeforeLastMonth(Date date, Integer cityId) {
		return this.customerUserMapper.findOpenFreeShopBeforeLastMonth(date,cityId);
	}

	@Override
	public Integer findCurrentMonthShopNumByInterfaceAndCityId(Integer id, Integer cityId) {
		return this.customerUserMapper.findCurrentMonthShopNumByInterfaceAndCityId(id,cityId);
	}

	@Override
	public List<CustomerUser> findCurrentMonthOpenByInterfaceIdAndCityId(Integer interfaceId,Integer cityId) {
		return this.customerUserMapper.findCurrentMonthOpenByInterfaceIdAndCityId(interfaceId,cityId);
	}

	@Override
	public Integer findCurrentMonthNewNumByXSAndCityId(Integer cityId) {
		return this.customerUserMapper.findCurrentMonthNewNumByXSAndCityId(cityId);
	}

	@Override
	public Integer findCurrentMonthNewNumByCityId(Integer cityId) {
		return this.customerUserMapper.findCurrentMonthNewNumByCityId(cityId);
	}

	@Override
	public List<CustomerUser> findLastFortyDaysOpenByCityId(Integer cityId) {
		return this.customerUserMapper.findLastFortyDaysOpenByCityId(cityId);
	}

	@Override
	public List<String> findByNameOrCompanyNameAndCityId(String userName, String companyName, Integer cityId) {
		return this.customerUserMapper.findByNameOrCompanyNameAndCityId(userName, companyName, cityId);
	}

	@Override
	public List<CustomerUser> findShopByNameCityId(String userName, Integer cityId, Integer redId, String selectev) {
		return this.customerUserMapper.findShopByNameCityId(userName,cityId,redId, selectev);
	}

	@Override
	public Integer findCurrentMonthNewNumByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids) {
		return this.customerUserMapper.findCurrentMonthNewNumByCityIdAndInterfaceIds(cityId,ids);
	}

	@Override
	public List<CustomerUser> findLastFortyDaysOpenByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids) {
		return this.customerUserMapper.findLastFortyDaysOpenByCityIdAndInterfaceIds(cityId,ids);
	}

	@Override
	public List<HashMap<String, Object>> findJlSalesByCityId(Integer cityId) {
		return this.customerUserMapper.findJlSalesByCityId(cityId);
	}

	@Override
	public List<HashMap<String, Object>> findSalesByCityIdAndIds(Integer cityId, List<Integer> ids) {
		return this.customerUserMapper.findSalesByCityIdAndIds(cityId,ids);
	}

	@Override
	public List<CustomerUser> findCurrentMonthOpenByCityId(Integer cityId) {
		return this.customerUserMapper.findCurrentMonthOpenByCityId(cityId);
	}

	@Override
	public List<CustomerUser> findCurrentMonthOpenByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids) {
		return this.customerUserMapper.findCurrentMonthOpenByCityIdAndInterfaceIds(cityId,ids);
	}

	@Override
	public void updateContractDate(Integer id, Date date) {
		this.customerUserMapper.updateContractDate(id,date);
	}

	@Override
	public List<HashMap<String, Object>> findSalesByCityIdAndGreadAndManageId(Integer cityId,Integer gread, Integer id) {
		return this.customerUserMapper.findSalesByCityIdAndGreadAndManageId(cityId,gread,id);
	}

	@Override
	public HashMap<String, Object> findSaleByCityIdAndId(Integer cityId,Integer id) {
		return this.customerUserMapper.findSaleByCityIdAndId(cityId,id);
	}

	@Override
	public List<HashMap<String, Object>> findAllShopByConditionAndIds(String name, Integer cityId, List<Integer> ids) {
		return this.customerUserMapper.findAllShopByConditionAndIds(name,cityId,ids);
	}

	@Override
	public List<HashMap<String, Object>> findAllShopByConditionAndReseauIds(String name, Integer cityId,
			List<Integer> ids) {
		return this.customerUserMapper.findAllShopByConditionAndReseauIds(name,cityId,ids);
	}

	@Override
	public List<HashMap<String, Object>> findAllShopByConditionAndReseauId(String name, Integer cityId,
			Integer reseauId) {
		return this.customerUserMapper.findAllShopByConditionAndReseauId(name,cityId,reseauId);
	}

	@Override
	public List<HashMap<String, Object>> findSalesByCityIdAndPostAndGread(Integer cityId, String post, Integer gread) {
		return this.customerUserMapper.findSalesByCityIdAndPostAndGread(cityId,post,gread);
	}

	@Override
	public List<CustomerUser> findShopByNameCityIdAndItemSaleId(String userName, Integer cityId, Integer id, String selectev) {
		return this.customerUserMapper.findShopByNameCityIdAndItemSaleId(userName,cityId,id,selectev);
	}

	@Override
	public List<CustomerUser> findShopByNameCityIdAndShopDiscountId(String userName, Integer cityId, Integer id, String selectev) {
		return this.customerUserMapper.findShopByNameCityIdAndShopDiscountId(userName,cityId,id, selectev);
	}

	@Override
	public List<CustomerUser> findAutoChangeGrade(Date dateStart, Date dateEnd) {
		return customerUserMapper.findAutoChangeGrade(dateStart, dateEnd);
	}

	@Override
	public int updateByPrimaryKeySelective(CustomerUser customerUser) {
		return customerUserMapper.updateByPrimaryKeySelective(customerUser);
	}

	@Override
	public int updateByFindBean(CustomerUser personUser) {
		return customerUserMapper.updateByPrimaryKey(personUser);
	}

	@Override
	public Integer findContractnumNumByCityId(Integer cityId) {
		return customerUserMapper.findContractnumNumByCityId(cityId);
	}

	@Override
	public Integer findCurrentMonthSubmitByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids) {
		return this.customerUserMapper.findCurrentMonthSubmitByCityIdAndInterfaceIds(cityId,ids);
	}

	@Override
	public Integer findContractnumNumByIdAndCityId(Integer id, Integer cityId) {
		return customerUserMapper.findContractnumNumByIdAndCityId(id, cityId);
	}

	@Override
	public CustomerUser findByDeliveryId(Integer DeliveryId) {
		return customerUserMapper.findByDeliveryId(DeliveryId);
	}

	@Override
	public CustomerUser findDeliveryInfoByCityIdAndInterfaceIdsAndType(Integer cityId, List<Integer> ids, Integer type) {
		return customerUserMapper.findDeliveryInfoByCityIdAndInterfaceIdsAndType(cityId, ids, type);
	}

	@Override
	public CustomerUser findDeliveryInfoByCityIdAndType(Integer cityId, int type) {
		return customerUserMapper.findDeliveryInfoByCityIdAndType(cityId, type);
	}

	@Override
	public CustomerUser findCustomerUserInfoByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids) {
		return customerUserMapper.findCustomerUserInfoByCityIdAndInterfaceIds(cityId, ids);
	}

	@Override
	public CustomerUser findCustomerUserInfoByCityId(Integer cityId) {
		return customerUserMapper.findCustomerUserInfoByCityId(cityId);
	}

	@Override
	public CustomerUser findSevenInfoByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids) {
		return customerUserMapper.findSevenInfoByCityIdAndInterfaceIds(cityId, ids);
	}

	@Override
	public CustomerUser findSevenInfoByCityId(Integer cityId) {
		return customerUserMapper.findSevenInfoByCityId(cityId);
	}

	@Override
	public CustomerUser findNumByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids) {
		return customerUserMapper.findNumByCityIdAndInterfaceIds(cityId, ids);
	}

	@Override
	public List<HashMap<String, Object>> findSaleNumByCityIdAndPostAndGread(Integer cityId, String post, Integer gread) {
		return customerUserMapper.findSaleNumByCityIdAndPostAndGread(cityId, post, gread);
	}

}
