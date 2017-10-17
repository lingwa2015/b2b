package com.b2b.service;

import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderBag;
import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.TranSum;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.page.Page;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public interface OrderService {

	Page<Pair<Order, Pair<List<OrderItem>, List<OrderBag>>>> findOrderWithBag(Order order, Date startTime, Date endTime, int currentPage, int pageSize,int businessId,int param);
	
	Page<Pair<Order, List<OrderItem>>> findOrder(Order order, Date startTime, Date endTime, int currentPage, int pageSize,int businessId);
	
	Pair<Order, List<OrderItem>> findByOrderNo(String orderNo);
	
	Pair<Order, List<OrderItem>> findByOrderNoSort(String orderNo);

    List<Order> fundOrderByUser(Order order);

	void updateOrderStatus(String orderNo,OrderStatusEnum statusEnum);

	void createOrder(Order order);
	
	void createSourcingOrder(Order order);

	void updateOrder(Order order);

	void cancelOrder(Order order);

	void updateOrderAndItems(Order order, Map<Integer, Integer> changedItems);

	List<OrderItem> statisticsOrderItem(Date startTime, Date endTime, String itemName, int userId, String catid, Integer type,String sortColumn, Integer cityId);

	OrderItem statisticsOrderItemTotal(Date startTime, Date endTime,String itemName,int userId, String catid, Integer type, Integer cityId);

	//List<TranSum> createTranSum(Date startTime, Date endTime);

	public List<Order> findTranSumOrder(int userId,Date startTime, Date endTime);

	void updateOrderTotalFee();

	void createForImportExcel(List<List<String>> dataList);

	void updateOrderAndItems(Order order);
	
	//int updateOrderStatus(Date startTime, Date endTime);
	
	List<OrderItem> statisticsOrderItemList(Date startTime, Date endTime, String itemName, int userId,String sortColumn);

	Pair<Order, List<HashMap<String, Object>>> findInfoByOrderNo(String orderNo);

	List<HashMap<String, Object>> findOrderInfoByDate(Date startTime,
			Date endTime,String orderNo,int userId);
	//微信会员自助下单并扣预存款
	void createOrderAndDeductMoney(Order order, Integer integer);
	
	Order findOrderById(String orderNo);
	//微信会员自助取消订单并加回预存款
	void cancelOrderAndAddMoney(Order order, Integer integer);
	//微信会员自助编辑订单并重算预存款
	void updateOrderAndItemsAndMoney(Order order, Integer integer);

	void createOrderWithBag(Order order, List<OrderBag> orderBagList);

	void createOrderWithBagAndDeductMoney(Order order,
			List<OrderBag> orderBagList, Integer id);

	Pair<Order, List<HashMap<String, Object>>> findBagInfoByOrderNo(String orderNo);

	void updateOrderAndBagAndItems(Order order, List<OrderBag> orderBagList);

	void updateOrderAndBagAndItemsAndMoney(Order order, List<OrderBag> orderBagList, Integer id);

	Date findNearestTimeByUserId(Integer id);

	List<Order> findOrderListByUserId(int userId);

	int countMoneyByUserAndTime(Integer id, Date firstDate, Date sumDate);

	int countNumByUserAndTime(Integer id, Date firstDate, Date sumDate);

	ArrayList<Integer> findLastTwoOrderItemByCid(Integer customerId);

	int countMoneyByUserAndTimeAndSign(Integer id, Date firstDate, Date sumDate,int sign);

	List<HashMap<String, Object>> findItemInfoByOrderNo(String id);
	
	/**
	 * 根据店铺id查找为上架的订单
	 * @param customerUserId
	 * @return
	 */
	Long findShopOrderByCompanyId(Integer customerUserId);
	
	/**
	 * 根据店铺id查找采购订单
	 * @param shopId
	 * @return
	 */
	List<Order> findShopOrderListByCompanyId(Integer shopId);
	/**
	 * 便利店当月采购金额
	 * @param customerUserId
	 * @return
	 */
	Long findCurrentMonthSourcingMoney(Integer customerUserId);

	//List<Order> findConfirm(Date firstDate, Date sumDate);

	void comfirmOrderAndReduceStock(String orderNo, String peisong, String string);

	void updateOrderAndItemsNoReduceStock(Order order);

	void cancelComfirmOrder(Order order);

	int findBeComfirmOrderNumByCityId(Integer cityId);
	
	/**
	 * 查询月份的订单数量
	 * @param id 
	 * @param date
	 * @return
	 */
	int findOrderNumByMonth(Integer id, Date date);
	
	/**
	 * 福利店月订单额
	 * @param id
	 * @param date2
	 * @param date1
	 * @return
	 */
	Order findCurrentMonthFreeShopOrderFee(Integer id, Date date2, Date date1);
	
	/**
	 * 福利店月下单店铺数
	 * @param date1 
	 * @param date2 
	 * @return
	 */
	int findFreeOrderShopNum(Date date2, Date date1);
	
	/**
	 * 福利店月下单金额，笔数
	 * @param date2
	 * @param date1
	 * @return
	 */
	Order findFreeOrderFeeAndOrderNumByMonth(Date date2, Date date1);

	List<Order> findgongdan(Date startTime, Date endTime, String orderNum,
			String userName, String region, String zhidan, String fenjian,
			String peisong, Integer reseauId, Integer cityId, String type);

	Order findgongdanCount(Date startTime, Date endTime,
			String orderNum, String userName, String region, String zhidan,
			String fenjian, String peisong,Integer reseauId, Integer cityId, String type);

	//List<Order> findConfirmNoOnshelf(Date firstDate, Date sumDate);

	List<Order> findGiftOrdersByCondition(String orderNum, String userName,
			Date startTime, Date endTime, Integer cityId);

	Order findTotalGiftOrdersByCondition(String orderNum, String userName,
			Date startTime, Date endTime, Integer cityId);

	Order findProfitByUserIdAndDate(Integer userid, Date startTime, Date endTime);

	Date findLastTimeByItemId(Integer itemId);

	List<Order> findByReseauIdAndDate(Date queryDate, Integer reseauId);

	//List<String> findPendingDeliveryByDate(Date firstDate, Date sumDate);

	void changeComfirmStatus(List<String> ordernos);

	List<Order> findOrderAndOrderItemByCondition(String orderNum,
			String userName, int param, Date startTime, Date endTime, Integer cityId, String itemName);

	List<Order> findByTimeAndCityId(Date start, Date end, Integer id);

	List<TranSum> createTranSumByCityId(Date firstDate, Date sumDate,
			Integer cityId);

	List<Order> findConfirmByCityId(Date firstDate, Date sumDate, Integer cityId);

	List<Order> findConfirmNoOnshelfByCityId(Date firstDate, Date sumDate,
			Integer cityId);

	List<String> findPendingDeliveryByDateAndCityId(Date firstDate,
			Date sumDate, Integer cityId);

	int updateOrderStatusByCityId(Date startTime, Date endTime, Integer cityId);
	
	/**
	 * 单店日下单金额，笔数
	 * @param id
	 * @param date
	 * @return
	 */
	Order findOrderFeeAndOrderNumByDayAndUserId(Integer id, Date date);
	
	/**
	 * 送达，激活客户
	 * @param order
	 */
	void updateOrderAndCustomer(Order order);

    void copyOrder(Order order);
}
