package com.b2b.service;

import com.b2b.common.domain.CustomerUser;
import com.b2b.page.Page;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface CustomerService {

	public void create(CustomerUser dto);
	
	public void update(CustomerUser dto);
	
	public String delete(CustomerUser personUser);
	
	public String setAdmin(CustomerUser personUser);
	
	public String unSetAdmin(CustomerUser personUser);
	
	public CustomerUser findById(int id);
	
	public CustomerUser findByPhone(String mobilePhone);
	
	public List<CustomerUser> findUsersByCondition(CustomerUser personUser);
	
//	public String sendSms(String message);
//
//	public float getSmsBalance();
	
	public CustomerUser findByUserName(String userName);
	
	/**
	 * 登录验证
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public CustomerUser findByLogin(String userName, String password);
	
	public Page<CustomerUser> findPage(CustomerUser personUser , int currentPage ,int pageSize,int businessId,Date startTime,Date endTime);
	
	public String check(CustomerUser dto);

	public List<HashMap<String, Object>> findByLikeUserNameAndCityId(String name, Integer cityId);

	public void insertVIP(CustomerUser user, Integer id);

	public List<CustomerUser> findAll();
	
	//后台对微信VIP充值
	public void createRecharge(String id, int chargeMoney, int freeMoney,String orderNo);

	public Page<CustomerUser> findPageWithMoney(CustomerUser personUser,
												int _currentPage, int defaultPageSize, int businessId,
												int param, Integer regionId, String sort, String grade, String flag, String buyWay, String payBillWay, Integer reseauId, Integer cityId, Integer isdelete, String activationId);

	public List<CustomerUser> findList();
	
	//区分用户类型，flag=3 便利店用户
	public List<HashMap<String, Object>> findByName(String userName,
			Integer flag);

	public List<HashMap<String, Object>> findAllShop();

	public CustomerUser findByIdAndState(Integer customerUserId);

	public List<CustomerUser> findShops();

	public List<HashMap<String, Object>> findAllShopByCondition(String name, Integer cityId);

	public List<CustomerUser> isCompanyExist(CustomerUser personUser);

	public Integer findAllShopNum();
	
	//所有福利店
	public List<CustomerUser> findFreeShops();

	public List<CustomerUser> isUserNameExist(CustomerUser personUser);

	public List<CustomerUser> isCompanyNameExist(CustomerUser personUser);

	public List<CustomerUser> isExist(CustomerUser personUser);

	public CustomerUser findCheckedDay(Integer shopId);

	public void deleteReseauId(Integer id);

	public int findFreeShopNumByReseauId(Integer id);

	public CustomerUser findPrintOrdersInfo(Integer userId);

	public CustomerUser findActivenessById(Integer shopId);

	public List<Integer> findIdsByUserName(String userName);

	public List<String> findByLikeNameAndCityId(String parameter, Integer cityId);

	public List<CustomerUser> findtichengUserByCityId(Integer cityId);

	public List<CustomerUser> findOpenShopBeforeLastMonth(Date date, Integer cityId);

	public List<CustomerUser> findOpenFreeShopBeforeLastMonth(Date date, Integer cityId);

	public Integer findCurrentMonthShopNumByInterfaceAndCityId(Integer id, Integer cityId);

	public List<CustomerUser> findCurrentMonthOpenByInterfaceIdAndCityId(Integer interfaceId,Integer cityId);

	public Integer findCurrentMonthNewNumByXSAndCityId(Integer cityId);

	public Integer findCurrentMonthNewNumByCityId(Integer cityId);

	public List<CustomerUser> findLastFortyDaysOpenByCityId(Integer cityId);

	public List<String> findByNameOrCompanyNameAndCityId(String userName, String companyName, Integer cityId);
	/**
	 * 查找便利店客户
	 * @param userName 客户简称
	 * @param cityId 城市id
	 * @param id 活动id
	 * @param selectev
	 * @return
	 */
	public List<CustomerUser> findShopByNameCityId(String userName, Integer cityId, Integer id, String selectev);

	public Integer findCurrentMonthNewNumByCityIdAndInterfaceIds(Integer cityId,List<Integer> ids);

	public List<CustomerUser> findLastFortyDaysOpenByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids);

	public List<HashMap<String, Object>> findJlSalesByCityId(Integer cityId);

	public List<HashMap<String, Object>> findSalesByCityIdAndIds(Integer cityId, List<Integer> ids);

	public List<CustomerUser> findCurrentMonthOpenByCityId(Integer cityId);

	public List<CustomerUser> findCurrentMonthOpenByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids);

	public void updateContractDate(Integer id, Date date);

	public List<HashMap<String, Object>> findSalesByCityIdAndGreadAndManageId(Integer cityId,Integer gread, Integer id);

	public HashMap<String, Object> findSaleByCityIdAndId(Integer cityId,Integer id);

	/**
	 * 查找某些业务员的客户
	 * @param name
	 * @param cityId
	 * @param ids
	 * @return
	 */
	public List<HashMap<String, Object>> findAllShopByConditionAndIds(String name, Integer cityId, List<Integer> ids);
	
	/**
	 * 查找某些网格下的客户
	 * @param name
	 * @param cityId
	 * @param ids
	 * @return
	 */
	public List<HashMap<String, Object>> findAllShopByConditionAndReseauIds(String name, Integer cityId,
			List<Integer> ids);
	
	/**
	 * 查找某一个网格下的客户
	 * @param name
	 * @param cityId
	 * @param reseauId
	 * @return
	 */
	public List<HashMap<String, Object>> findAllShopByConditionAndReseauId(String name, Integer cityId,
			Integer reseauId);

	public List<HashMap<String, Object>> findSalesByCityIdAndPostAndGread(Integer cityId, String Post, Integer gread);

	/**
	 * 商品促销店铺列表
	 * @param userName
	 * @param cityId
	 * @param id
	 * @param selectev
	 * @return
	 */
	public List<CustomerUser> findShopByNameCityIdAndItemSaleId(String userName, Integer cityId, Integer id, String selectev);

	/**
	 * 店铺打折活动店铺列表
	 * @param userName
	 * @param cityId
	 * @param id
	 * @param selectev
     * @return
	 */
	public List<CustomerUser> findShopByNameCityIdAndShopDiscountId(String userName, Integer cityId, Integer id, String selectev);

    List<CustomerUser> findAutoChangeGrade(Date dateStart, Date dateEnd);

	int updateByPrimaryKeySelective(CustomerUser customerUser);

	int updateByFindBean(CustomerUser personUser);

	Integer findContractnumNumByCityId(Integer cityId);

    Integer findCurrentMonthSubmitByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids);

    Integer findContractnumNumByIdAndCityId(Integer id, Integer cityId);

    CustomerUser findByDeliveryId(Integer DeliveryId);

    CustomerUser findDeliveryInfoByCityIdAndInterfaceIdsAndType(Integer cityId, List<Integer> ids, Integer type);

	CustomerUser findDeliveryInfoByCityIdAndType(Integer cityId, int type);

	CustomerUser findCustomerUserInfoByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids);

	CustomerUser findCustomerUserInfoByCityId(Integer cityId);

	CustomerUser findSevenInfoByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids);

	CustomerUser findSevenInfoByCityId(Integer cityId);

	CustomerUser findNumByCityIdAndInterfaceIds(Integer cityId, List<Integer> ids);

	List<HashMap<String,Object>> findSaleNumByCityIdAndPostAndGread(Integer cityId, String post, Integer gread);
}
