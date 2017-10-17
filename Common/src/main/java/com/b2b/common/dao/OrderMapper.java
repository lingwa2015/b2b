package com.b2b.common.dao;

import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderExample;
import com.b2b.common.domain.Refund;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(String orderNo);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(String orderNo);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

	List<HashMap<String, Object>> findOrderInfoByDate(@Param("startTime")Date startTime,
			@Param("endTime")Date endTime,@Param("orderNo")String orderNo,@Param("userId")int userId);
	
	//夜间更新客户最新下单时间
	Date findNearestTimeByUserId(@Param("id")Integer id);

	int countMoneyByUserAndTime(@Param("id")Integer id, @Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate);

	int countNumByUserAndTime(@Param("id")Integer id, @Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate);

	ArrayList<Integer> findLastTwoOrderItemByCid(Integer customerId);

	int countMoneyByUserAndTimeAndSign(@Param("id")Integer id, @Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate,@Param("sign")int sign);

	Long findShopOrderByCompanyId(Integer customerUserId);

	List<Order> findShopOrderListByCompanyId(Integer shopId);

	Long findCurrentMonthSourcingMoney(Integer shopId);

	List<Order> findConfirm(@Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate);

	int findBeComfirmOrderNumByCityId(@Param("cityId")Integer cityId);

	int findOrderNumByMonth(@Param("id")Integer id, @Param("date")Date date);

	Order findCurrentMonthFreeShopOrderFee(@Param("id")Integer id, @Param("start")Date date2, @Param("end")Date date1);

	int findFreeOrderShopNum(@Param("start")Date date2, @Param("end")Date date1);

	Order findFreeOrderFeeAndOrderNumByMonth(@Param("start")Date date2, @Param("end")Date date1);

	List<Order> findgongdan(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("orderNum")String orderNum,
			@Param("userName")String userName, @Param("region")String region, @Param("zhidan")String zhidan, @Param("fenjian")String fenjian,
			@Param("peisong")String peisong,@Param("reseauId") Integer reseauId, @Param("cityId")Integer cityId, @Param("type")String type);

	Order findgongdanCount(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("orderNum")String orderNum, @Param("userName")String userName, @Param("region")String region, @Param("zhidan")String zhidan,
			@Param("fenjian")String fenjian, @Param("peisong")String peisong,@Param("reseauId") Integer reseauId, @Param("cityId")Integer cityId, @Param("type")String type);

	List<Order> findConfirmNoOnshelf(@Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate);

	List<Order> findGiftOrdersByCondition(@Param("orderNum")String orderNum, @Param("userName")String userName,
			@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("cityId")Integer cityId);

	Order findTotalGiftOrdersByCondition(@Param("orderNum")String orderNum, @Param("userName")String userName,
			@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("cityId")Integer cityId);

	Order findProfitByUserIdAndDate(@Param("userid")Integer userid, @Param("startTime")Date startTime, @Param("endTime")Date endTime);

	Date findLastTimeByItemId(@Param("itemId")Integer itemId);

	List<Order> findByReseauIdAndDate(@Param("queryDate")Date queryDate, @Param("reseauId")Integer reseauId);

	List<String> findPendingDeliveryByDate(@Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate);

	void changeComfirmStatus(List<String> ordernos);

	List<Order> findOrderAndOrderItemByCondition(@Param("orderNum")String orderNum,
			@Param("userName")String userName, @Param("param")int param, @Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("cityId")Integer cityId, @Param("itemName")String itemName);

	List<Order> findByTimeAndCityId(@Param("start")Date start, @Param("end")Date end, @Param("cityId")Integer cityId);

	List<Order> findConfirmByCityId(@Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate, @Param("cityId")Integer cityId);

	List<Order> findConfirmNoOnshelfByCityId(@Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate,
			 @Param("cityId")Integer cityId);

	List<String> findPendingDeliveryByDateAndCityId(@Param("firstDate")Date firstDate,
			@Param("sumDate")Date sumDate, @Param("cityId")Integer cityId);

	Order findOrderFeeAndOrderNumByDayAndUserId(@Param("userid")Integer id, @Param("date")Date date);

	List<Order> findsixtyDayOrder(Integer id);
}