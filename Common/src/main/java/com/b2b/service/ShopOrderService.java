package com.b2b.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.ActualShopReport;
import com.b2b.common.domain.ShopOrder;
import com.b2b.common.domain.ShopUser;
import com.b2b.common.domain.TranConsume;

public interface ShopOrderService {

	void create(ShopOrder order);

	void changeStatus(String string);

	Long findCurrentMonthConsumeMoney(Integer shopId);

	HashMap<String, Object> findTodayConsumeMoney(Integer customerUserId);

	List<ShopOrder> findOrderAndItemByShopId(Integer shopId, String name);

	List<ShopOrder> findMonthRecordByShopId(Integer shopId);

	//List<TranConsume> createTranConsume(Date firstDate, Date sumDate);

	ShopOrder findById(String id);

	void changeRefundStatus(ShopOrder order, Integer id);

	ShopOrder findMonthRecordByShopIdAndDate(Integer shopId, String month);

	ShopOrder findShopDailyReport(Date startDate, Date endDate);

	ShopOrder findByShopIdAndDate(Integer id, Date startDate, Date endDate);

	int findByDateGroupByBuyer(Date startDate, Date endDate);

	int findMonthConsumeNumByMonth(Date date2);

	int findByMonthGroupByBuyer(Date date2);

	int findPenByMonthGroupByBuyer(Date date);
	//月报消费人数
	int findConsumeNumByShopIdAndMonth(Integer id, Date date);

	int findConsumePenByShopIdAndMonth(Integer id, Date date);
	
	//月报损耗率
	int findTotalLossPercentByMonth(Date date4);

	Date findFirstPayDay(Integer id);

	ShopOrder findWeekFeeByShopIdAndDate(Integer id, Date startDate,
			Date endDate);

	Integer findPenByShopIdAndDate(Integer id, Date startDate, Date endDate);

	Integer findConsumeNumByShopIdAndDateGroupBuyer(Integer id, Date startDate,
			Date endDate);

	List<ShopOrder> findOrderAndItem(String name, String shopname, String param, Integer cityId, String itemName);

	ShopOrder findTodayAllConsumeMoney(Integer cityId);

	List<ActualShopReport> findactualShopReportList(String param, String username, int reseauId, Integer cityId);

	List<ShopOrder> findOrderAndItemByUseridAndtype(Integer userid, Integer type);

	Long findFreeFee(Date query, Integer buyerid, int i);

	Integer findKindsByShopIdAndDate(Integer id, Date startDate, Date endDate);

	ShopOrder findProfitByShopIdAndDate(Integer id, Date fdate, Date endDate);

	ShopOrder findConsumeMoneyByReseauIdAndDate(Integer reseauId,Date startdate,Date enddate);

	List<ShopOrder> findReseauCountInfo(Integer reseauId);

	ActualShopReport findactualShopReportByShopId(Integer shopId);

	List<TranConsume> createTranConsumeByCityId(Date firstDate, Date sumDate,
			Integer cityId);
	/**
	 * 全站报表数据
	 * @param cityId
	 * @return
	 */
	ShopOrder findTodayAllConsumeMoneyByCityId(Integer cityId);

	Integer findTodayOrderNumByBuyerIdAndSign(Integer buyerId, int sign);
	
	/**
	 * 获取红包使用总金额
	 * @param cityId
	 * @return
	 */
	Long findTotalUseRedFeeByCityId(Integer cityId);

	ShopOrder findTodayAllConsumeMoneyByInterfacerId(Integer cityId, List<Integer> ids);
	
	/**
	 * 查找时间段内某些网格的消费统计，金额，笔数
	 * @param cityId
	 * @param ids
	 * @param startdate
	 * @param enddate
	 * @return
	 */
	ShopOrder findConsumeMoneyByCityIdAndReseauIdsAndDate(Integer cityId, List<Integer> ids, Date startdate,
			Date enddate);
	
	/**
	 * 查找时间段内消费统计
	 * @param cityId
	 * @param startdate
	 * @param enddate
	 * @return
	 */
	ShopOrder findConsumeMoneyByCityIdAndDate(Integer cityId, Date startdate, Date enddate);
}
