package com.b2b.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zx.com.sms.client.Client;

import com.b2b.Constant;
import com.b2b.common.dao.PersonUserMapper;
import com.b2b.common.domain.City;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.PersonUserExample;
import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.PersonUserExample.Criteria;
import com.b2b.common.domain.Role;
import com.b2b.common.domain.ShopUser;
import com.b2b.common.domain.UserCity;
import com.b2b.common.domain.UserRole;
import com.b2b.common.util.EncryptHelper;
import com.b2b.page.Page;
import com.b2b.service.CityService;
import com.b2b.service.PersonRegionService;
import com.b2b.service.ShopUserService;
import com.b2b.service.UserCityService;
import com.b2b.service.UserRoleService;
import com.b2b.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	PersonUserMapper personUserMapper;
	
	@Autowired
	PersonRegionService personRegionService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	ShopUserService shopUserService;
	
	@Autowired
	private UserCityService userCityService;
	
	@Autowired
	CityService cityService;

	public static String regCode="ZXHD-CRM-0100-SLMMZC";
	public static String pwd="97662511";

	@Override
	public void create(PersonUser dto) {
		dto.setStatus(Constant.VALID_STATUS);
		String password="Lingwa123456";
		//密码MD5加密
		dto.setPassWord(EncryptHelper.md5(password));

		personUserMapper.insert(dto);
		
		if(null!=dto.getCityId()){
			UserCity userCity = new UserCity();
			userCity.setCityId(dto.getCityId());
			userCity.setUserId(dto.getId());
			this.userCityService.create(userCity);
		}

	}

	@Override
	public void update(PersonUser dto) {
		dto.setStatus(Constant.VALID_STATUS);
		personUserMapper.updateByPrimaryKey(dto);
		
//		this.userCityService.deleteByUserId(dto.getId());
//		if(list.size()>0){
//			for (Integer id : list) {
//				UserCity userCity = new UserCity();
//				userCity.setCityId(id);
//				userCity.setUserId(dto.getId());
//				this.userCityService.create(userCity);
//			}
//		}	
	}

	@Override
	public String delete(PersonUser dto) {

		dto.setStatus(Constant.DELETE_STATUS);
		dto.setReseauId(null);
		personUserMapper.updateByPrimaryKey(dto);
		return null;
	}

	@Override
	public PersonUser findById(int id) {
		return personUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public PersonUser findByPhone(String phone) {
		PersonUserExample example = new PersonUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andMobilePhoneEqualTo(phone);

		List<PersonUser> personUsers = personUserMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(personUsers)){
			return personUsers.get(0);
		}

		return null;
	}

	@Override
	public PersonUser findByLogin(String userName, String password) {
		PersonUserExample example = new PersonUserExample();
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
		List<PersonUser> personUsers = personUserMapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(personUsers)){
			return personUsers.get(0);
		}

		return null;
	}

	@Override
	public Page<PersonUser> findPage(PersonUser personUser, int currentPage, int pageSize,int businessId) {
		String userName = personUser.getUserName();
		String mobilePhone = personUser.getMobilePhone();
		Integer cityId = personUser.getDingCityId();
		PersonUserExample example = new PersonUserExample();
		example.setOrderByClause("created_time desc");

		//拼凑or条件
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andDingCityIdEqualTo(cityId);
		Criteria criteria1 = example.createCriteria();
		criteria1.andStatusEqualTo(Constant.VALID_STATUS);
		criteria1.andDingCityIdEqualTo(cityId);
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

		if(businessId>0){
			criteria.andBusinessIdEqualTo(businessId);
		    criteria1.andBusinessIdEqualTo(businessId);
		}
		example.or(criteria1);
		int count = personUserMapper.countByExample(example);

		if(count ==0){
			return new Page<PersonUser>();
		}


		int start = Page.calStartRow(currentPage, pageSize);
		example.setLimit(pageSize);
		example.setStart(start);
		example.setLimitFlag(true);

		List<PersonUser> list = personUserMapper.selectByExample(example);

		Page<PersonUser> page = new Page<PersonUser>(currentPage,count,pageSize,list);

		return page;
	}

	@Override
	public String check(PersonUser dto) {
		return null;
	}

	@Override
	public List<PersonUser> findUsersByCondition(PersonUser personUser) {

		if(personUser == null){
			return null;
		}

		PersonUserExample example = new PersonUserExample();
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
		return personUserMapper.selectByExample(example);
	}

	@Override
	public String setAdmin(PersonUser dto) {
		dto.setIsadmin(Constant.Admin_User);
		personUserMapper.updateByPrimaryKey(dto);
		return null;
	}

	@Override
	public String unSetAdmin(PersonUser dto) {
		dto.setIsadmin(Constant.Common_User);
		personUserMapper.updateByPrimaryKey(dto);
		return null;
	}

	@Override
	public String sendSms(String message) {

		String result="";
		PersonUserExample example = new PersonUserExample();
		example.setOrderByClause("Id desc");


		int count = personUserMapper.countByExample(example);

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

	    		List<PersonUser> list = personUserMapper.selectByExample(example);

	    		for(PersonUser personUser:list)
	    		{
	    			String pattern="^1\\d{10}";
	    		if(!personUser.getMobilePhone().isEmpty())
	    		{
	    			if(personUser.getMobilePhone().matches (pattern))
	    			{
	    				mobiles=mobiles+","+personUser.getMobilePhone();
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

	@Override
	public void saveRole(int userId, ArrayList<Integer> list) {
		this.userRoleService.deleteByUserId(userId);
		for (Integer a : list) {
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(a);
			this.userRoleService.save(userRole);
		}
	}

	@Override
	public List<Privilege> findPrivileges(Integer id) {
		return this.userRoleService.findPrivilegesByUserId(id);
	}

	@Override
	public void deleteReseauId(Integer id) {
		this.personUserMapper.deleteReseauId(id);
	}

	@Override
	public PersonUser findByReseauId(Integer reseauId) {
		return this.personUserMapper.findByReseauId(reseauId);
	}

	@Override
	public PersonUser findByOpenId(String openid) {
		return this.personUserMapper.findByOpenId(openid);
	}

	@Override
	public List<PersonUser> findUserAndRoles(Integer cityId) {
		List<PersonUser> users = this.personUserMapper.findAllByCityId(cityId);
		for (PersonUser personUser : users) {
			List<Role> roles = this.userRoleService.findRoleByUserId(personUser.getId());
			//List<City> citys = this.userCityService.findCityByUserId(personUser.getId());
			personUser.setRoles(roles);
			//personUser.setCitys(citys);
		}
		return users;
	}

	@Override
	public void updateUserToManager(ShopUser user, Integer user_id) {
		PersonUser personUser = this.personUserMapper.selectByPrimaryKey(user_id);
		personUser.setOpenid(user.getOpenid());
		this.personUserMapper.updateByPrimaryKeySelective(personUser);
	}

	@Override
	public void cancalManager(PersonUser user) {
		this.personUserMapper.updateByPrimaryKey(user);
	}

	@Override
	public void updateDefaultCity(int userId, int cityId) {
		PersonUser user = new PersonUser();
		user.setId(userId);
		user.setCityId(cityId);
		this.personUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public List<PersonUser> findUserkfFirstByCityId(Integer cityId) {
		return this.personUserMapper.findUserkfFirstByCityId(cityId);
	}

	@Override
	public List<PersonUser> findUsercgFirstByCityId(Integer cityId) {
		return this.personUserMapper.findUsercgFirstByCityId(cityId);
	}

	@Override
	public List<PersonUser> findUserpsFirstByCityId(Integer cityId) {
		return this.personUserMapper.findUserpsFirstByCityId(cityId);
	}

	@Override
	public List<PersonUser> findAll() {
		List<PersonUser> users = this.personUserMapper.findAll();
		for (PersonUser personUser : users) {
			List<City> citys = this.userCityService.findCityByUserId(personUser.getId());
			City city = this.cityService.findById(personUser.getDingCityId());
			personUser.setCityName(city.getCityName());
			personUser.setCitys(citys);
		}
		return users;
	}

	@Override
	public List<PersonUser> findUsershFirstByCityId(Integer cityId) {
		return this.personUserMapper.findUsershFirstByCityId(cityId);
	}

	@Override
	public List<PersonUser> findSHByCityId(Integer cityId) {
		return this.personUserMapper.findSHByCityId(cityId);
	}

	@Override
	public List<PersonUser> findWarning(Integer reseauId) {
		return this.personUserMapper.findWarning(reseauId);
	}

	@Override
	public List<PersonUser> findUserkhjlFirstByCityId(Integer cityId) {
		return this.personUserMapper.findUserkhjlFirstByCityId(cityId);
	}

	@Override
	public void updateSelect(PersonUser user) {
		this.personUserMapper.updateByPrimaryKeySelective(user);
	}

	public List<Role> findRolesById(Integer id){
		return this.userRoleService.findRoleByUserId(id);
	}

	@Override
	public List<Integer> findIdsByManagerId(Integer id) {
		return this.personUserMapper.findIdsByManagerId(id);
	}

	@Override
	public List<Integer> findReseauIdsByManagerId(Integer id) {
		return this.personUserMapper.findReseauIdsByManagerId(id);
	}

	@Override
	public List<PersonUser> findByPost(String post, Integer cityId) {
		return this.personUserMapper.findByPost(post, cityId);
	}

	@Override
	public List<PersonUser> findUserAndRolesNew(Integer cityId, String userName, String mobilePhone, Integer validStatus) {
		List<PersonUser> users = this.personUserMapper.findAllByCityIdUserNameTelStatus(cityId, userName, mobilePhone, validStatus);
//		List<PersonUser> users1 = this.personUserMapper.find;
		for (PersonUser personUser : users) {
			List<Role> roles = this.userRoleService.findRoleByUserId(personUser.getId());
			//List<City> citys = this.userCityService.findCityByUserId(personUser.getId());
			personUser.setRoles(roles);
			//personUser.setCitys(citys);
		}
		return users;
	}

	@Override
	public List<PersonUser> findByManagerId(Integer id) {
		return this.personUserMapper.findByManagerId(id);
	}

	@Override
	public List<PersonUser> findSalesByCityIdAndGreadAndManageId(Integer cityId, Integer gread, Integer id) {
		return this.personUserMapper.findSalesByCityIdAndGreadAndManageId(cityId,gread,id);
	}

	@Override
	public List<PersonUser> findSalesByCityIdAndPostAndGread(Integer cityId, String post, Integer gread) {
		return this.personUserMapper.findSalesByCityIdAndPostAndGread(cityId,post,gread);
	}

}
