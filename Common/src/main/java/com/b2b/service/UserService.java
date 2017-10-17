package com.b2b.service;

import java.util.ArrayList;
import java.util.List;

import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.Role;
import com.b2b.common.domain.ShopUser;
import com.b2b.page.Page;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

	public void create(PersonUser dto);
	
	public void update(PersonUser dto);
	
	public String delete(PersonUser personUser);
	
	public String setAdmin(PersonUser personUser);
	
	public String unSetAdmin(PersonUser personUser);
	
	public PersonUser findById(int id);
	
	public PersonUser findByPhone(String mobilePhone);
	
	public List<PersonUser> findUsersByCondition(PersonUser personUser);
	
	public String sendSms(String message);
	
	public float getSmsBalance();
	
	/**
	 * 登录验证
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public PersonUser findByLogin(String userName, String password);
	
	public Page<PersonUser> findPage(PersonUser personUser , int currentPage ,int pageSize,int businessId);
	
	public String check(PersonUser dto);

	public void saveRole(int userId, ArrayList<Integer> list);

	public List<Privilege> findPrivileges(Integer id);

	public void deleteReseauId(Integer id);
	
	/**
	 * 客户经理页面，返回一个 limit 1
	 * @param reseauId
	 * @return
	 */
	public PersonUser findByReseauId(Integer reseauId);

	public PersonUser findByOpenId(String openid);

	public List<PersonUser> findUserAndRoles(Integer cityId);

	public void updateUserToManager(ShopUser user, Integer user_id);

	public void cancalManager(PersonUser user);

	public void updateDefaultCity(int userId, int cityId);

	public List<PersonUser> findUserkfFirstByCityId(Integer cityId);

	public List<PersonUser> findUsercgFirstByCityId(Integer cityId);

	public List<PersonUser> findUserpsFirstByCityId(Integer cityId);

	public List<PersonUser> findAll();

	public List<PersonUser> findUsershFirstByCityId(Integer cityId);

	public List<PersonUser> findSHByCityId(Integer cityId);

	public List<PersonUser> findUserkhjlFirstByCityId(Integer cityId);

	public void updateSelect(PersonUser user);

	public List<Role> findRolesById(Integer id);

	public List<Integer> findIdsByManagerId(Integer id);

	public List<Integer> findReseauIdsByManagerId(Integer id);

	List<PersonUser> findWarning(Integer reseauId);

	List<PersonUser> findByPost(String post, Integer cityId);

    List<PersonUser> findUserAndRolesNew(Integer cityId, String userName, String mobilePhone, Integer validStatus);


	public List<PersonUser> findByManagerId(Integer id);

	public List<PersonUser> findSalesByCityIdAndGreadAndManageId(Integer cityId, Integer gread, Integer id);

	public List<PersonUser> findSalesByCityIdAndPostAndGread(Integer cityId, String post, Integer gread);
}
