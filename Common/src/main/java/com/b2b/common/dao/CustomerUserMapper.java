package com.b2b.common.dao;

import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.CustomerUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface CustomerUserMapper {
    int countByExample(CustomerUserExample example);

    int deleteByExample(CustomerUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerUser record);

    int insertSelective(CustomerUser record);

    List<CustomerUser> selectByExample(CustomerUserExample example);

    CustomerUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerUser record, @Param("example") CustomerUserExample example);

    int updateByExample(@Param("record") CustomerUser record, @Param("example") CustomerUserExample example);

    int updateByPrimaryKeySelective(CustomerUser record);

    int updateByPrimaryKey(CustomerUser record);

	List<HashMap<String, Object>> findByLikeUserNameAndCityId(@Param("name")String userName,@Param("cityId")Integer cityId);

	int countByCondition(@Param("linkName") String linkName, @Param("userName") String userName, @Param("mobilePhone") String mobilePhone,
						 @Param("businessId") int businessId, @Param("param") int param, @Param("regionId") Integer regionId, @Param("grade") String grade, @Param("flag") String flag, @Param("buyWay") String buyWay, @Param("payBillWay") String payBillWay, @Param("reseauId") Integer reseauId, @Param("cityId") Integer cityId, @Param("isdelete") Integer isdelete, @Param("activationId")String activationId);

	List<CustomerUser> selectByConditionWithMoney(@Param("linkName") String linkName, @Param("userName") String userName, @Param("mobilePhone") String mobilePhone,
												  @Param("businessId") int businessId, @Param("param") int param, @Param("regionId") Integer regionId, @Param("sort") String sort, @Param("grade") String grade, @Param("flag") String flag, @Param("buyWay") String buyWay, @Param("payBillWay") String payBillWay, @Param("reseauId") Integer reseauId, @Param("pageSize") int pageSize, @Param("start") int start, @Param("cityId") Integer cityId, @Param("isdelete") Integer isdelete, @Param("activationId")String activationId);

	List<HashMap<String, Object>> findByName(@Param("userName")String userName, @Param("flag")Integer flag);

	List<HashMap<String, Object>> findAllShop();

	CustomerUser findByIdAndState(Integer customerUserId);

	List<CustomerUser> findShops();

	List<HashMap<String, Object>> findAllShopByCondition(@Param("name")String name, @Param("cityId")Integer cityId);

	List<CustomerUser> isCompanyExist(CustomerUser personUser);

	Integer findAllShopNum();

	List<CustomerUser> findFreeShops();

	List<CustomerUser> isUserNameExist(CustomerUser personUser);

	List<CustomerUser> isCompanyNameExist(CustomerUser personUser);

	List<CustomerUser> isExist(CustomerUser personUser);

	CustomerUser findCheckedDay(Integer shopId);

	void deleteReseauId(Integer id);

	int findFreeShopNumByReseauId(Integer id);

	CustomerUser findPrintOrdersInfo(@Param("userId")Integer userId);

	CustomerUser findActivenessById(@Param("shopId")Integer shopId);

	List<Integer> findIdsByUserName(@Param("userName")String userName);

	List<String> findByLikeNameAndCityId(@Param("name")String name, @Param("cityId")Integer cityId);

	List<CustomerUser> findtichengUserByCityId(@Param("cityId")Integer cityId);

	List<CustomerUser> findOpenShopBeforeLastMonth(@Param("date")Date date, @Param("cityId")Integer cityId);

	List<CustomerUser> findOpenFreeShopBeforeLastMonth(@Param("date")Date date, @Param("cityId")Integer cityId);

	Integer findCurrentMonthShopNumByInterfaceAndCityId(@Param("id")Integer id, @Param("cityId")Integer cityId);

	List<CustomerUser> findCurrentMonthOpenByInterfaceIdAndCityId(@Param("interfaceId")Integer interfaceId,@Param("cityId")Integer cityId);

	Integer findCurrentMonthNewNumByXSAndCityId(Integer cityId);

	Integer findCurrentMonthNewNumByCityId(Integer cityId);

	List<CustomerUser> findLastFortyDaysOpenByCityId(Integer cityId);

	List<String> findByNameOrCompanyNameAndCityId(@Param("name")String userName, @Param("companyName")String companyName, @Param("cityId")Integer cityId);

	List<CustomerUser> findShopByNameCityId(@Param("userName") String userName, @Param("cityId") Integer cityId, @Param("redId") Integer redId, @Param("selectev")String selectev);

	Integer findCurrentMonthNewNumByCityIdAndInterfaceIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	List<CustomerUser> findLastFortyDaysOpenByCityIdAndInterfaceIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	List<HashMap<String, Object>> findJlSalesByCityId(Integer cityId);

	List<HashMap<String, Object>> findSalesByCityIdAndIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	List<CustomerUser> findCurrentMonthOpenByCityId(Integer cityId);

	List<CustomerUser> findCurrentMonthOpenByCityIdAndInterfaceIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	void updateContractDate(@Param("id")Integer id, @Param("date")Date date);

	List<HashMap<String, Object>> findSalesByCityIdAndGreadAndManageId(@Param("cityId")Integer cityId,@Param("gread")Integer gread, @Param("id")Integer id);

	HashMap<String, Object> findSaleByCityIdAndId(@Param("cityId")Integer cityId,@Param("id")Integer id);

	List<HashMap<String, Object>> findAllShopByConditionAndIds(@Param("name")String name, @Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	List<HashMap<String, Object>> findAllShopByConditionAndReseauIds(@Param("name")String name, @Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	List<HashMap<String, Object>> findAllShopByConditionAndReseauId(@Param("name")String name, @Param("cityId")Integer cityId, @Param("reseauId")Integer reseauId);

	List<HashMap<String, Object>> findSalesByCityIdAndPostAndGread(@Param("cityId")Integer cityId, @Param("post")String post, @Param("gread")Integer gread);

	List<CustomerUser> findShopByNameCityIdAndItemSaleId(@Param("userName") String userName, @Param("cityId") Integer cityId, @Param("id") Integer id, @Param("selectev")String selectev);

	List<CustomerUser> findShopByNameCityIdAndShopDiscountId(@Param("userName") String userName, @Param("cityId") Integer cityId, @Param("id") Integer id, @Param("selectev") String selectev);

    List<CustomerUser> findAutoChangeGrade(@Param("dateStart")Date dateStart, @Param("dateEnd")Date dateEnd);

    Integer findContractnumNumByCityId(@Param("cityId")Integer cityId);

    Integer findCurrentMonthSubmitByCityIdAndInterfaceIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	Integer findContractnumNumByIdAndCityId(@Param("id")Integer id, @Param("cityId")Integer cityId);

	CustomerUser findByDeliveryId(@Param("id")Integer deliveryId);

    CustomerUser findDeliveryInfoByCityIdAndInterfaceIdsAndType(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids, @Param("type")Integer type);

	CustomerUser findDeliveryInfoByCityIdAndType(@Param("cityId")Integer cityId, @Param("type")Integer type);

	CustomerUser findCustomerUserInfoByCityIdAndInterfaceIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	CustomerUser findCustomerUserInfoByCityId(@Param("cityId")Integer cityId);

	CustomerUser findSevenInfoByCityIdAndInterfaceIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	CustomerUser findSevenInfoByCityId(@Param("cityId")Integer cityId);

	CustomerUser findNumByCityIdAndInterfaceIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	List<HashMap<String,Object>> findSaleNumByCityIdAndPostAndGread(@Param("cityId")Integer cityId, @Param("post")String post, @Param("gread")Integer gread);
}