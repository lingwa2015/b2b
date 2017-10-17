package com.b2b.common.dao;

import com.b2b.common.domain.ActualShopReport;
import com.b2b.common.domain.ShopOrder;
import com.b2b.common.domain.ShopOrderExample;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ShopOrderMapper {
    int countByExample(ShopOrderExample example);

    int deleteByExample(ShopOrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    List<ShopOrder> selectByExample(ShopOrderExample example);

    ShopOrder selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByExample(@Param("record") ShopOrder record, @Param("example") ShopOrderExample example);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);

	Long findCurrentMonthConsumeMoney(Integer shopId);

	HashMap<String, Object> findTodayConsumeMoney(Integer shopId);

	List<ShopOrder> findOrderAndItemByShopId(@Param("shopId")Integer shopId, @Param("name")String name);

	List<ShopOrder> findMonthRecordByShopId(Integer shopId);

	List<ShopOrder> findByDate(@Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate);

	ShopOrder findMonthRecordByShopIdAndDate(@Param("shopId")Integer shopId, @Param("month")String month);

	ShopOrder findShopDailyReport(@Param("start")Date startDate, @Param("end")Date endDate);

	ShopOrder findByShopIdAndDate(@Param("id")Integer id, @Param("start")Date startDate, @Param("end")Date endDate);

	int findByDateGroupByBuyer(@Param("start")Date startDate, @Param("end")Date endDate);

	int findMonthConsumeNumByMonth(Date date);

	int findByMonthGroupByBuyer(Date date);

	int findPenByMonthGroupByBuyer(Date date);

	int findConsumeNumByShopIdAndMonth(@Param("id")Integer id, @Param("date")Date date);

	int findConsumePenByShopIdAndMonth(@Param("id")Integer id, @Param("date")Date date);

	int findTotalLossPercentByMonth(Date date);

	Date findFirstPayDay(Integer id);

	ShopOrder findWeekFeeByShopIdAndDate(@Param("id")Integer id, @Param("startDate")Date startDate,
			@Param("endDate")Date endDate);

	Integer findPenByShopIdAndDate(@Param("id")Integer id, @Param("startDate")Date startDate,
			@Param("endDate")Date endDate);

	Integer findConsumeNumByShopIdAndDateGroupBuyer(@Param("id")Integer id, @Param("startDate")Date startDate,
			@Param("endDate")Date endDate);

	List<ShopOrder> findOrderAndItem(@Param("name")String name, @Param("shopname")String shopname, @Param("param")String param, @Param("cityId")Integer cityId, @Param("itemName")String itemName);

	ShopOrder findTodayAllConsumeMoney(@Param("cityId")Integer cityId);

	List<ActualShopReport> findactualShopReportList(@Param("param") String param, @Param("username")String username, @Param("reseauId")int reseauId, @Param("cityId")Integer cityId);

	List<ShopOrder> findOrderAndItemByUseridAndtype(@Param("userid") Integer userid, @Param("type") Integer type);

	Long findFreeFee(@Param("query")Date query,@Param("buyerid")Integer buyerid, @Param("i")int i);

	Integer findKindsByShopIdAndDate(@Param("id")Integer id, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

	ShopOrder findProfitByShopIdAndDate(@Param("id")Integer id, @Param("startDate")Date fdate, @Param("endDate")Date endDate);

	ShopOrder findConsumeMoneyByReseauIdAndDate(@Param("reseauId")Integer reseauId,@Param("startdate")Date startdate, @Param("enddate")Date enddate);

	List<ShopOrder> findReseauCountInfo(@Param("reseauId")Integer reseauId);

	ActualShopReport findactualShopReportByShopId(@Param("shopId")Integer shopId);

	List<ShopOrder> findByDateAndCityId(@Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate,
			@Param("cityId")Integer cityId);

	ShopOrder findTodayAllConsumeMoneyByCityId(Integer cityId);

	Integer findTodayOrderNumByBuyerIdAndSign(@Param("buyerId")Integer buyerId, @Param("sign")int sign);

	Long findTotalUseRedFeeByCityId(Integer cityId);

    Boolean findTemp(@Param("shopId") Integer id, @Param("contractDate") Date contractDate);

	ShopOrder findTodayAllConsumeMoneyByInterfacerId(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	ShopOrder findConsumeMoneyByCityIdAndReseauIdsAndDate(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids, @Param("startdate")Date startdate,
			@Param("enddate")Date enddate);

	ShopOrder findConsumeMoneyByCityIdAndDate(@Param("cityId")Integer cityId, @Param("startdate")Date startdate, @Param("enddate")Date enddate);
}