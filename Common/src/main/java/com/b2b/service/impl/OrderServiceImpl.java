package com.b2b.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.Constant;
import com.b2b.common.dao.OrderItemMapper;
import com.b2b.common.dao.OrderMapper;
import com.b2b.common.dao.RefundItemMapper;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderBag;
import com.b2b.common.domain.OrderExample;
import com.b2b.common.domain.OrderExample.Criteria;
import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.OrderItemExample;
import com.b2b.common.domain.PersonRegion;
import com.b2b.common.domain.Refund;
import com.b2b.common.domain.RefundItem;
import com.b2b.common.domain.Stock;
import com.b2b.common.domain.TranSum;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.WXConsumRecords;
import com.b2b.common.domain.WXConsumRecordsExample;
import com.b2b.common.util.DateUtil;
import com.b2b.common.util.OIComparator;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.page.Page;
import com.b2b.service.CustomerService;
import com.b2b.service.ItemService;
import com.b2b.service.OrderBagService;
import com.b2b.service.OrderService;
import com.b2b.service.PersonRegionService;
import com.b2b.service.RefundService;
import com.b2b.service.StockService;
import com.b2b.service.UserService;
import com.b2b.service.WXAccountService;
import com.b2b.service.WXConsumRecordsService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory
			.getLogger(OrderServiceImpl.class);

	@Autowired
	OrderMapper orderMapper;

	@Autowired
	OrderItemMapper orderItemMapper;

	@Autowired
	RefundItemMapper refundItemMapper;

	@Autowired
	CustomerService customerService;

	@Autowired
	StockService stockService;

	@Autowired
	RefundService refundService;

	@Autowired
	ItemService itemService;
	
	@Autowired
	WXAccountService wxAccountService;
	
	@Autowired
	WXConsumRecordsService wxConsumRecordsService;
	
	@Autowired
	OrderBagService orderBagService;
	
	@Autowired
	PersonRegionService personRegionService;

    @Override
    public List<Order> fundOrderByUser(Order order) {
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause("executed_time desc");
        Criteria criteria = orderExample.createCriteria();
        Integer userId = order.getUserId();
        criteria.andUserIdEqualTo(userId);
        criteria.andStatusEqualTo(Constant.VALID_STATUS);

        return orderMapper.selectByExample(orderExample);
    }
    
    @Override
	public Page<Pair<Order, List<OrderItem>>> findOrder(Order order,
			Date startTime, Date endTime, int currentPage, int pageSize,
			int businessId) {
		Page<Pair<Order, List<OrderItem>>> page = null;

		OrderExample orderExample = new OrderExample();
		orderExample.setOrderByClause("executed_time desc,order_no desc");
		Criteria criteria = orderExample.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);

		if (businessId > 0)
			criteria.andBusinessIdEqualTo(businessId);
		// 开始时间
		if (startTime != null) {
			criteria.andExecutedTimeGreaterThanOrEqualTo(startTime);
		}

		// 结束时间
		if (endTime != null) {
			criteria.andExecutedTimeLessThanOrEqualTo(endTime);
		}

		String orderNo = order.getOrderNo();
		Integer userId = order.getUserId();

		if (StringUtils.isNotEmpty(orderNo)) {
			criteria.andOrderNoEqualTo(orderNo.trim());
		}

		if (userId != null && userId > 0) {
			criteria.andUserIdEqualTo(userId);
		}

		int count = orderMapper.countByExample(orderExample);
		if (count == 0) {
			currentPage = 1;
			page = new Page<Pair<Order, List<OrderItem>>>(currentPage, count,
					pageSize, null);
			return page;
		}
		int start = Page.calStartRow(currentPage, pageSize);

		orderExample.setLimit(pageSize);
		orderExample.setStart(start);
		orderExample.setLimitFlag(true);

		List<Order> orders = orderMapper.selectByExample(orderExample);
		List<Pair<Order, List<OrderItem>>> orderItemList = new ArrayList<Pair<Order, List<OrderItem>>>();
		for (Order order2 : orders) {
			List<OrderItem> itemList = this.findOrderItem(order2.getOrderNo(),
					true);

			CustomerUser personUser = this.customerService.findById(order2.getUserId());
			if (personUser != null) {
				order2.setCompanyName(personUser.getCompanyName());
				order2.setBuyerPhone(personUser.getMobilePhone());
				order2.setUserName(personUser.getUserName());
				order2.setAddress(personUser.getAddress());
				order2.setCompanyMemo(personUser.getCompanyMemo());
			}

			if (CollectionUtils.isNotEmpty(itemList)) {
				orderItemList.add(Pair.of(order2, itemList));
			}
		}

		page = new Page<Pair<Order, List<OrderItem>>>(currentPage, count,
				pageSize, orderItemList);

		return page;
	}
    
	@Override
	public Page<Pair<Order, Pair<List<OrderItem>, List<OrderBag>>>> findOrderWithBag(Order order,
			Date startTime, Date endTime, int currentPage, int pageSize,
			int businessId,int param) {
		Page<Pair<Order, Pair<List<OrderItem>, List<OrderBag>>>> page = null;

		OrderExample orderExample = new OrderExample();
		orderExample.setOrderByClause("executed_time desc,created_time desc");
		Criteria criteria = orderExample.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		if(param==1){
			criteria.andComfirmEqualTo(0);
		}else if (param==2) {
			criteria.andOrderStatusNotEqualTo(2);
		}
		
		if (businessId > 0)
			criteria.andBusinessIdEqualTo(businessId);
		// 开始时间
		if (startTime != null) {
			criteria.andExecutedTimeGreaterThanOrEqualTo(startTime);
		}

		// 结束时间
		if (endTime != null) {
			criteria.andExecutedTimeLessThanOrEqualTo(endTime);
		}

		String orderNo = order.getOrderNo();
		Integer userId = order.getUserId();

		if (StringUtils.isNotEmpty(orderNo)) {
			criteria.andOrderNoEqualTo(orderNo.trim());
		}

		if (userId != null && userId > 0) {
			criteria.andUserIdEqualTo(userId);
		}

		int count = orderMapper.countByExample(orderExample);
		if (count == 0) {
			currentPage = 1;
			page = new Page<Pair<Order, Pair<List<OrderItem>, List<OrderBag>>>>(currentPage, count,
					pageSize, null);
			return page;
		}
		int start = Page.calStartRow(currentPage, pageSize);
		
		orderExample.setLimit(pageSize);
		orderExample.setStart(start);
		orderExample.setLimitFlag(true);
		List<Order> orders = orderMapper.selectByExample(orderExample);
		List<Pair<Order, Pair<List<OrderItem>, List<OrderBag>>>> orderItemList = new ArrayList<Pair<Order, Pair<List<OrderItem>, List<OrderBag>>>>();
		for (Order order2 : orders) {
			List<OrderItem> itemList = this.findOrderItem(order2.getOrderNo(),
					true);
			List<OrderBag> bagList = this.orderBagService.findByOrderNo(order2.getOrderNo());
			CustomerUser personUser = this.customerService.findById(order2.getUserId());
			if (personUser != null) {
				order2.setCompanyName(personUser.getCompanyName());
				order2.setBuyerPhone(personUser.getLikeman()+personUser.getMobilePhone());
				order2.setUserName(personUser.getUserName());
				order2.setAddress(personUser.getAddress());
				order2.setCompanyMemo(personUser.getCompanyMemo());
				order2.setPayBillWay(personUser.getPayBillWay());
			};
			if (CollectionUtils.isNotEmpty(itemList)) {
				orderItemList.add(Pair.of(order2, Pair.of(itemList,bagList)));
			}
		}

		page = new Page<Pair<Order, Pair<List<OrderItem>, List<OrderBag>>>>(currentPage, count,
				pageSize, orderItemList);

		return page;
	}

	@Override
	public Pair<Order, List<OrderItem>> findByOrderNo(String orderNo) {
		Order order = orderMapper.selectByPrimaryKey(orderNo);

		CustomerUser personUser = this.customerService.findById(order.getUserId());

		if (personUser != null) {
			order.setCompanyName(personUser.getCompanyName());
			order.setBuyerPhone(personUser.getMobilePhone());
			order.setUserName(personUser.getUserName());
			order.setAddress(personUser.getAddress());
			order.setCompanyMemo(personUser.getCompanyMemo());
		}

		List<OrderItem> itemList = this.findOrderItem(orderNo, true);
		return Pair.of(order, itemList);
	}
	
	@Override
	public Pair<Order, List<OrderItem>> findByOrderNoSort(String orderNo) {
		Order order = orderMapper.selectByPrimaryKey(orderNo);

		CustomerUser personUser = this.customerService.findById(order.getUserId());

		if (personUser != null) {
			order.setCompanyName(personUser.getCompanyName());
			order.setBuyerPhone(personUser.getMobilePhone());
			order.setUserName(personUser.getUserName());
			order.setAddress(personUser.getAddress());
			order.setCompanyMemo(personUser.getCompanyMemo());
		}

		List<OrderItem> itemList = this.findOrderItem(orderNo, true);
		Collections.sort(itemList, new OIComparator());
		return Pair.of(order, itemList);
	}

	private List<OrderItem> findOrderItem(String orderNo, boolean needAllInfo) {
//		OrderItemExample itemExample = new OrderItemExample();
//		itemExample.createCriteria().andOrderNoEqualTo(orderNo);
//		List<OrderItem> itemList = orderItemMapper.selectByExample(itemExample);
//
//		if (CollectionUtils.isEmpty(itemList)) {
//			logger.warn("无法找到订单商品:" + orderNo);
//		} else {
//			// 增加商品的分类信息
//			if (needAllInfo) {
//				for (OrderItem oi : itemList) {
//					Item item = itemService.findById(oi.getItemId());
//					if (item != null) {
//						oi.setCategoryId(item.getCategoryId());
//					} else {
//						logger.warn("无法找到商品！商品:" + oi.getItemName());
//					}
//
//					Stock stock = stockService.findByItem(oi.getItemId());
//					if (stock != null) {
//						oi.setItemStock(stock.getNum());
//					} else {
//						logger.warn("无法找到商品的库存！商品:" + oi.getItemName());
//					}
//				}
//			}
//		}
		return this.orderItemMapper.findOrderItemWithCatAndStockByOrderNo(orderNo);
	}

	@Override
	public void updateOrderStatus(String orderNo, OrderStatusEnum statusEnum) {
		Order order = orderMapper.selectByPrimaryKey(orderNo);
		if (order != null) {
			order.setOrderStatus(statusEnum.getId());
			orderMapper.updateByPrimaryKey(order);
		}
	}

//	@Override
//	public int updateOrderStatus(Date startTime, Date endTime) {
//		int i = 0;
//		List<Order> sumList = this.findTranSumOrders(startTime, endTime);
//		if (CollectionUtils.isNotEmpty(sumList)) {
//			for (Order order : sumList) {
//				order.setOrderStatus(OrderStatusEnum.SUM.getId());
//				int ret = orderMapper.updateByPrimaryKeySelective(order);
//				i = i + ret;
//			}
//		}
//		return i;
//	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void createOrder(Order order) {
		// 如果执行日期为空，则默认为当前日期
		if (order.getExecutedTime() == null) {
			order.setExecutedTime(new Date());
		}
		this.orderMapper.insertSelective(order);
		for (OrderItem oi : order.getOrderItemList()) {

			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			num = num * coefficient;
			// 修改库存
			stockService.updateForReduce(oi.getItemId(), num);
			oi.setConsumeStockNum(num);
			this.orderItemMapper.insert(oi);
			// throw new RuntimeException("测试事务");
		}

	}
	
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void createSourcingOrder(Order order) {
		// 如果执行日期为空，则默认为当前日期
		if (order.getExecutedTime() == null) {
			order.setExecutedTime(new Date());
		}
		this.orderMapper.insertSelective(order);
		for (OrderItem oi : order.getOrderItemList()) {
			this.orderItemMapper.insert(oi);
		}

	}

	@Override
	public void updateOrder(Order order) {
		this.orderMapper.updateByPrimaryKeySelective(order);

	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void cancelOrder(Order order) {
		List<OrderItem> itemList = this
				.findOrderItem(order.getOrderNo(), false);
		if (CollectionUtils.isNotEmpty(itemList)) {
			for (OrderItem oi : itemList) {
				int num = oi.getNum();
				Item item = this.itemService.findById(oi.getItemId());
				String itemSize = oi.getItemSizeType();	// 规格
				int coefficient = 0;
				if ("SIZE".equals(itemSize)) { //普通规格
					coefficient = 1;
				} else if ("SALE_SIZE".equals(itemSize)) { // 零售
					coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
				} else if ("BUY_SIZE".equals(itemSize)) { //批发
					coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
				} else {
					throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
				}
				num = num * coefficient;
				stockService.updateForAdd(oi.getItemId(), num);
			}
		}

		order.setStatus(Constant.DELETE_STATUS);
		this.orderMapper.updateByPrimaryKeySelective(order);

	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void updateOrderAndItems(Order order,
			Map<Integer, Integer> changedItems) {
		this.orderMapper.updateByPrimaryKeySelective(order);

		if (CollectionUtils.isNotEmpty(order.getOrderItemList())) {
			for (OrderItem oi : order.getOrderItemList()) {
				this.orderItemMapper.updateByPrimaryKeySelective(oi);
			}
		}

		if (changedItems != null && changedItems.size() > 0) {
			for (Map.Entry<Integer, Integer> entry : changedItems.entrySet()) {
				Integer itemId = entry.getKey();
				Integer changed = entry.getValue();
				if (changed > 0) {
					stockService.updateForAdd(itemId, changed);
				} else if (changed < 0) {
					stockService.updateForReduce(itemId, changed);
				}
			}
		}
	}

	public List<OrderItem> statisticsOrderItem(Date startTime, Date endTime,
			String item_name, int userId,String catid, Integer type,
			String sortColumn,Integer cityId) {
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("item_name", item_name);
		paramMap.put("type", type);
		paramMap.put("cityId", cityId);
		if (userId > 0) {
			paramMap.put("userId", userId);
		}

		paramMap.put("orderStatus", OrderStatusEnum.CANCEL.getId());
		paramMap.put("status", Constant.VALID_STATUS);
		paramMap.put("catid", catid);
		if (StringUtils.isNotEmpty(sortColumn)) {
			if(sortColumn.equals("refundNum") || sortColumn.equals("refundFee")){
				paramMap.put("sortList", sortColumn+" asc ");
			}else{
				paramMap.put("sortList", sortColumn+" desc ");
			}
		}

//		int count = this.orderItemMapper.countByStatisticsSqlNew(paramMap);
//		if (count == 0) {
//			currentPage = 1;
//			page = new Page<OrderItem>(currentPage, count, pageSize, null);
//			return page;
//		}
		List<OrderItem> orderItems = this.orderItemMapper
				.selectStatisticsByDateTimeNew(paramMap);

//		// 把退货单给计算进去
//		if (CollectionUtils.isNotEmpty(orderItems)) {
//			for (OrderItem item : orderItems) {
//				paramMap.put("item_id", item.getItemId());
//				List<RefundItem> refundList = refundItemMapper
//						.selectStatisticsByDateTime(paramMap);
//				if (CollectionUtils.isNotEmpty(refundList)) {
//
//					long totalFee = 0;
//					long totalCostFee = 0;
//					int num = 0;
//
//					for (RefundItem refundItem : refundList) {
//						totalFee = totalFee + refundItem.getTotalFee();
//						totalCostFee = totalCostFee + refundItem.getItemPrice();
//						num = num + refundItem.getNum();
//					}
//
//					item.setRefundFee(-totalFee);
//					item.setRefundNum(-num);
//					item.setFee(item.getFee() - totalFee);
//					item.setItemPrice(item.getItemPrice() - totalFee);
//					item.setItemCostPrice(item.getItemCostPrice()
//							- totalCostFee);
//				}
//			}
//		}
//		page = new Page<OrderItem>(currentPage, count, pageSize, orderItems);
		return orderItems;
	}

	public List<OrderItem> statisticsOrderItemList(Date startTime, Date endTime, String itemName, int userId,String sortColumn)
	{
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("item_name", itemName);
		if (userId > 0) {
			paramMap.put("userId", userId);
		}

		paramMap.put("orderStatus", OrderStatusEnum.CANCEL.getId());
		paramMap.put("status", Constant.VALID_STATUS);
		if (StringUtils.isNotEmpty(sortColumn)) {
			paramMap.put("sortList", sortColumn+" desc ");
		}

		List<OrderItem> orderItems = this.orderItemMapper.selectStatisticsByDateTimeNew(paramMap);

//		// 把退货单给计算进去
//		if (CollectionUtils.isNotEmpty(orderItems)) {
//			for (OrderItem item : orderItems) {
//				paramMap.put("item_id", item.getItemId());
//				List<RefundItem> refundList = refundItemMapper
//						.selectStatisticsByDateTime(paramMap);
//				if (CollectionUtils.isNotEmpty(refundList)) {
//
//					long totalFee = 0;
//					long totalCostFee = 0;
//					int num = 0;
//
//					for (RefundItem refundItem : refundList) {
//						totalFee = totalFee + refundItem.getTotalFee();
//						totalCostFee = totalCostFee + refundItem.getItemPrice();
//						num = num + refundItem.getNum();
//					}
//
//					item.setRefundFee(-totalFee);
//					item.setRefundNum(-num);
//					item.setFee(item.getFee() - totalFee);
//					item.setItemPrice(item.getItemPrice() - totalFee);
//					item.setItemCostPrice(item.getItemCostPrice()
//							- totalCostFee);
//				}
//			}
//		}
		return orderItems;
}


	public OrderItem statisticsOrderItemTotal(Date startTime, Date endTime,String itemName,
			int userId,String catid,Integer type,Integer cityId) {
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		if (userId > 0) {
			paramMap.put("userId", userId);
		}
		paramMap.put("item_name", itemName);
		paramMap.put("orderStatus", OrderStatusEnum.CANCEL.getId());
		paramMap.put("status", Constant.VALID_STATUS);
		paramMap.put("catid", catid);
		paramMap.put("cityId", cityId);
		paramMap.put("type", type);
		OrderItem total = new OrderItem();
		total= orderItemMapper.selectStatisticsByDateTimeTotalNew(paramMap);
		// this.orderItemMapper.countByStatisticsTotal(paramMap);
		// RefundItem refund =
		// this.refundItemMapper.countByStatisticsTotal(paramMap);

//		List<OrderItem> orderList = orderItemMapper
//				.selectStatisticsByDateTime(paramMap);
//		List<RefundItem> refundList = refundItemMapper
//				.selectStatisticsByDateTime(paramMap);

//		if (CollectionUtils.isNotEmpty(orderList)) {
//			long totalFee = 0l;
//			// long totalPrice=0l;
//			long totalCostFee = 0l;
//
//			long totalRefundFee = 0l;
//			long totalRefundCostFee = 0l;
//
//			for (OrderItem orderItem : orderList) {
//				totalFee = totalFee + orderItem.getFee();
//				totalCostFee = totalCostFee + orderItem.getItemCostPrice();
//
//				if (CollectionUtils.isNotEmpty(refundList)) {
//					for (RefundItem refundItem : refundList) {
//						if (refundItem.getItemId()
//								.equals(orderItem.getItemId())) {
//							totalRefundFee = totalRefundFee
//									+ refundItem.getTotalFee();
//							totalRefundCostFee = totalRefundCostFee
//									+ (refundItem.getNum() * (orderItem
//											.getItemCostPrice() / orderItem
//											.getNum()));
//						}
//					}
//
//				}
//			}
//
//			total.setRefundFee(totalRefundFee);
//			total.setFee(totalFee - totalRefundFee);
//			total.setItemPrice(totalFee - totalRefundFee);
//			total.setItemCostPrice(totalCostFee - totalRefundCostFee);
//		}

		return total;
	}
	
	@Override
	public List<TranSum> createTranSumByCityId(Date startTime, Date endTime,
			Integer cityId) {
		List<TranSum> list = Lists.newArrayList();
		Map<Integer, TranSum> map = Maps.newHashMap();

		List<Order> sumList = this.findTranSumOrdersByCityId(startTime, endTime,cityId);
		if (CollectionUtils.isNotEmpty(sumList)) {
			for (Order order : sumList) {
				int userId = order.getUserId();
				TranSum tranSum = map.get(userId);

				if (tranSum == null) {
					tranSum = new TranSum();
					tranSum.setAmount(order.getTotalFee());
					tranSum.setCreateDate(new Date());
					tranSum.setUserId(userId);

					tranSum.setRefundNum(0);
					tranSum.setOrderNum(1);
					map.put(userId, tranSum);
				} else {
					tranSum.setOrderNum(tranSum.getOrderNum() + 1);
					tranSum.setAmount(order.getTotalFee() + tranSum.getAmount());
				}

			}
		}

		List<Refund> refundList = refundService.findTranSumRefundsByCityId(startTime,
				endTime,cityId);
		if (CollectionUtils.isNotEmpty(refundList)) {
			for (Refund refund : refundList) {
				int userId = refund.getUserId();
				TranSum tranSum = map.get(userId);

				if (tranSum == null) {
					tranSum = new TranSum();
					tranSum.setAmount(-refund.getTotalFee());
					tranSum.setCreateDate(new Date());
					tranSum.setUserId(userId);

					tranSum.setRefundNum(1);
					tranSum.setOrderNum(0);
					map.put(userId, tranSum);
				} else {
					tranSum.setAmount(tranSum.getAmount() - refund.getTotalFee());
					tranSum.setRefundNum(tranSum.getRefundNum() + 1);
				}
			}
		}

		list.addAll(map.values());

		return list;
	}
	
	

//	@Override
//	public List<TranSum> createTranSum(Date startTime, Date endTime) {
//		List<TranSum> list = Lists.newArrayList();
//		Map<Integer, TranSum> map = Maps.newHashMap();
//
//		List<Order> sumList = this.findTranSumOrders(startTime, endTime);
//		if (CollectionUtils.isNotEmpty(sumList)) {
//			for (Order order : sumList) {
//				int userId = order.getUserId();
//				TranSum tranSum = map.get(userId);
//
//				if (tranSum == null) {
//					tranSum = new TranSum();
//					tranSum.setAmount(order.getTotalFee());
//					tranSum.setCreateDate(new Date());
//					tranSum.setUserId(userId);
//
//					tranSum.setRefundNum(0);
//					tranSum.setOrderNum(1);
//					map.put(userId, tranSum);
//				} else {
//					tranSum.setOrderNum(tranSum.getOrderNum() + 1);
//					tranSum.setAmount(order.getTotalFee() + tranSum.getAmount());
//				}
//
//			}
//		}
//
//		List<Refund> refundList = refundService.findTranSumRefunds(startTime,
//				endTime);
//		if (CollectionUtils.isNotEmpty(refundList)) {
//			for (Refund refund : refundList) {
//				int userId = refund.getUserId();
//				TranSum tranSum = map.get(userId);
//
//				if (tranSum == null) {
//					tranSum = new TranSum();
//					tranSum.setAmount(-refund.getTotalFee());
//					tranSum.setCreateDate(new Date());
//					tranSum.setUserId(userId);
//
//					tranSum.setRefundNum(1);
//					tranSum.setOrderNum(0);
//					map.put(userId, tranSum);
//				} else {
//					tranSum.setAmount(tranSum.getAmount() - refund.getTotalFee());
//					tranSum.setRefundNum(tranSum.getRefundNum() + 1);
//				}
//			}
//		}
//
//		list.addAll(map.values());
//
//		return list;
//	}

	public List<Order> findTranSumOrders(Date startTime, Date endTime) {
		OrderExample orderExample = new OrderExample();

		List<Integer> statusList = Lists.newArrayList();
		//statusList.add(OrderStatusEnum.SUM.getId());
		statusList.add(OrderStatusEnum.CANCEL.getId());

		Criteria criteria = orderExample.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andOrderStatusNotIn(statusList);

		if (startTime != null) {
			criteria.andExecutedTimeGreaterThanOrEqualTo(startTime);
		}

		if (endTime != null) {
			criteria.andExecutedTimeLessThanOrEqualTo(endTime);
		}

		List<Order> orderList = orderMapper.selectByExample(orderExample);

		return orderList;
	}
	
	private List<Order> findTranSumOrdersByCityId(Date startTime, Date endTime,
			Integer cityId) {
		OrderExample orderExample = new OrderExample();

		List<Integer> statusList = Lists.newArrayList();
		//statusList.add(OrderStatusEnum.SUM.getId());
		statusList.add(OrderStatusEnum.CANCEL.getId());

		Criteria criteria = orderExample.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andOrderStatusNotIn(statusList);
		criteria.andCityIdEqualTo(cityId);
		if (startTime != null) {
			criteria.andExecutedTimeGreaterThanOrEqualTo(startTime);
		}

		if (endTime != null) {
			criteria.andExecutedTimeLessThanOrEqualTo(endTime);
		}

		List<Order> orderList = orderMapper.selectByExample(orderExample);

		return orderList;
	}

	@Override
	public void updateOrderTotalFee() {
		OrderExample orderExample = new OrderExample();
		Criteria criteria = orderExample.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);

		List<Order> orderList = orderMapper.selectByExample(orderExample);
		if (CollectionUtils.isNotEmpty(orderList)) {
			for (Order order : orderList) {
				List<OrderItem> itemList = this.findOrderItem(
						order.getOrderNo(), false);
				if (CollectionUtils.isNotEmpty(itemList)) {
					long totalFee = 0;
					int num = 0;
					for (OrderItem item : itemList) {
						totalFee += item.getFee();
						num += item.getNum();
					}

					if (totalFee != order.getTotalFee()
							|| num != order.getTotalNum()) {
						logger.warn("更新订单总金额,orderNo:" + order.getOrderNo()
								+ ",oldTotalFee:" + order.getTotalFee()
								+ ",oldTotalNum:" + order.getTotalNum()
								+ ",totalFee:" + totalFee + ",totalNum:" + num);
						order.setTotalFee(totalFee);
						order.setTotalNum(num);

						this.updateOrder(order);
					}
				}
			}
		}
	}

	@Override
	public void createForImportExcel(List<List<String>> dataList) {
		Order order = new Order();
		String mobilePhone = dataList.get(0).get(1);
		String executedDateStr = dataList.get(1).get(1);
		String content = dataList.get(2).get(1);

		CustomerUser personUser = customerService.findByPhone(mobilePhone);
		if (personUser == null) {
			throw new RuntimeException("找不到用户信息，手机号:" + mobilePhone);
		}

		Date executedDate = DateUtil.parseDateStr(executedDateStr,
				DateUtil.TIME_FORMAT_SEPERATE);

		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		int size = dataList.size();
		if (size < 5) {
			throw new RuntimeException("没有商品信息");
		}

		for (int i = 4; i < size; i++) {

		}
	}

	@Override
	public List<Order> findTranSumOrder(int userId, Date startTime, Date endTime) {
		OrderExample orderExample = new OrderExample();
		orderExample.setOrderByClause("executed_time asc ");

		List<Integer> statusList = Lists.newArrayList();
		// statusList.add(OrderStatusEnum.SUM.getId());
		statusList.add(OrderStatusEnum.CANCEL.getId());

		Criteria criteria = orderExample.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		criteria.andOrderStatusNotIn(statusList);
		criteria.andUserIdEqualTo(userId);

		if (startTime != null) {
			criteria.andExecutedTimeGreaterThanOrEqualTo(startTime);
		}

		if (endTime != null) {
			criteria.andExecutedTimeLessThanOrEqualTo(endTime);
		}

		List<Order> orderList = orderMapper.selectByExample(orderExample);

		return orderList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void updateOrderAndItems(Order order) {
		String orderNo = order.getOrderNo();
		Pair<Order, List<OrderItem>> orderPair = this.findByOrderNo(orderNo);
		Order oldOrder = orderPair.getLeft();
		List<OrderItem> oldItemList = orderPair.getRight();
		List<OrderItem> itemList = order.getOrderItemList();

		// 先更新库存
		for (OrderItem oi : oldItemList) {
			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			num = num * coefficient;

			stockService.updateForAdd(item.getId(), num);
		}

		order.setStatus(Constant.VALID_STATUS);
		order.setOrderStatus(oldOrder.getOrderStatus());
		order.setCreatedTime(oldOrder.getCreatedTime());
		order.setComfirm(oldOrder.getComfirm());
		this.updateOrder(order);

		// 删掉以前的orderItem，然后重新插入
		this.deleteOrderItem(order.getOrderNo());

		// 新插入item数据
		for (OrderItem oi : itemList) {
			oi.setOrderNo(orderNo);


			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			num = num * coefficient;

			oi.setConsumeStockNum(num);
			orderItemMapper.insert(oi);
			stockService.updateForReduce(item.getId(), num);
		}

	}

	public void deleteOrderItem(String orderNo) {
		OrderItemExample itemExample = new OrderItemExample();
		itemExample.createCriteria().andOrderNoEqualTo(orderNo);

		orderItemMapper.deleteByExample(itemExample);
	}
	
	/**
	 * 订单列表，编辑订单，已下架的商品仍然显示不能编辑
	 */
	@Override
	public Pair<Order, List<HashMap<String, Object>>> findInfoByOrderNo(String orderNo) {
		Order order = orderMapper.selectByPrimaryKey(orderNo);

		CustomerUser personUser = this.customerService.findById(order.getUserId());

		if (personUser != null) {
			order.setCompanyName(personUser.getCompanyName());
			order.setBuyerPhone(personUser.getMobilePhone());
			order.setUserName(personUser.getUserName());
			order.setAddress(personUser.getAddress());
			order.setCompanyMemo(personUser.getCompanyMemo());
			order.setFlag(personUser.getIswxvip());
		}

		List<HashMap<String, Object>> itemList = orderItemMapper.findOrderItemByOrderNo(orderNo);

		return Pair.of(order, itemList);
	}

	@Override
	public List<HashMap<String, Object>> findOrderInfoByDate(Date startTime,
			Date endTime,String orderNo,int userId) {
		return this.orderMapper.findOrderInfoByDate(startTime,endTime,orderNo,userId);
	}
	
	//微信会员自助下单并扣预存款
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void createOrderAndDeductMoney(Order order,Integer userId) {
		if (order.getExecutedTime() == null) {
			order.setExecutedTime(new Date());
		}
		this.orderMapper.insertSelective(order);
		for (OrderItem oi : order.getOrderItemList()) {

			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			num = num * coefficient;
			// 修改库存
			stockService.updateForReduce(oi.getItemId(), num);
			oi.setConsumeStockNum(num);
			this.orderItemMapper.insert(oi);
			// throw new RuntimeException("测试事务");
		}
		this.wxAccountService.updateForAddMoney(order.getUserId(), -order.getTotalFee());
		insertWXConsumRecords(order.getOrderNo(), order.getUserId(), userId, -order.getTotalFee());
	}

	@Override
	public Order findOrderById(String orderNo) {
		return this.orderMapper.selectByPrimaryKey(orderNo);
	}
	
	@Transactional
	@Override
	public void cancelOrderAndAddMoney(Order order,Integer userId) {
		List<OrderItem> itemList = this
				.findOrderItem(order.getOrderNo(), false);
		if (CollectionUtils.isNotEmpty(itemList)) {
			for (OrderItem oi : itemList) {
				int num = oi.getNum();
				Item item = this.itemService.findById(oi.getItemId());
				String itemSize = oi.getItemSizeType();	// 规格
				int coefficient = 0;
				if ("SIZE".equals(itemSize)) { //普通规格
					coefficient = 1;
				} else if ("SALE_SIZE".equals(itemSize)) { // 零售
					coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
				} else if ("BUY_SIZE".equals(itemSize)) { //批发
					coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
				} else {
					throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
				}
				num = num * coefficient;
				stockService.updateForAdd(oi.getItemId(), num);
			}
		}
		List<OrderBag> bags = this.orderBagService.findByOrderNo(order.getOrderNo());
		if(CollectionUtils.isNotEmpty(bags)){
			for (OrderBag orderBag : bags) {
				orderBag.setStatus(0);
				this.orderBagService.update(orderBag);
			}
		}

		order.setStatus(Constant.DELETE_STATUS);
		this.orderMapper.updateByPrimaryKeySelective(order);
		this.wxAccountService.updateForAddMoney(order.getUserId(), order.getTotalFee());
		insertWXConsumRecords(order.getOrderNo(), order.getUserId(), userId, order.getTotalFee());
	}

	@Override
	public void updateOrderAndItemsAndMoney(Order order,Integer userId) {
		String orderNo = order.getOrderNo();
		Pair<Order, List<OrderItem>> orderPair = this.findByOrderNo(orderNo);
		Order oldOrder = orderPair.getLeft();
		List<OrderItem> oldItemList = orderPair.getRight();
		List<OrderItem> itemList = order.getOrderItemList();
		// 先更新库存
		for (OrderItem oi : oldItemList) {
			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			num = num * coefficient;

			stockService.updateForAdd(item.getId(), num);
		}

		order.setStatus(Constant.VALID_STATUS);
		order.setOrderStatus(oldOrder.getOrderStatus());
		order.setCreatedTime(oldOrder.getCreatedTime());
		order.setComfirm(oldOrder.getComfirm());
		this.updateOrder(order);

		// 删掉以前的orderItem，然后重新插入
		this.deleteOrderItem(order.getOrderNo());

		// 新插入item数据
		for (OrderItem oi : itemList) {
			oi.setOrderNo(orderNo);


			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			num = num * coefficient;

			oi.setConsumeStockNum(num);
			orderItemMapper.insert(oi);
			stockService.updateForReduce(item.getId(), num);
		}
		long money = oldOrder.getTotalFee()-order.getTotalFee();
		this.wxAccountService.updateForAddMoney(order.getUserId(), money);
		this.wxConsumRecordsService.deleteWXConsumRecordsByOrderNo(order.getOrderNo());
		insertWXConsumRecords(order.getOrderNo(), order.getUserId(), userId, -order.getTotalFee());
	}
	
	public void insertWXConsumRecords(String orderNo,Integer customerId,Integer userId,Long money){
		WXConsumRecords consumRecords = new WXConsumRecords();
		consumRecords.setCustomerId(customerId);
		consumRecords.setMoney(money);
		consumRecords.setOrderNo(orderNo);
		consumRecords.setUserId(userId);
		consumRecords.setCreatedTime(new Date());
		this.wxConsumRecordsService.insert(consumRecords);
	}

	@Override
	public void createOrderWithBag(Order order, List<OrderBag> orderBagList) {
		// 如果执行日期为空，则默认为当前日期
				if (order.getExecutedTime() == null) {
					order.setExecutedTime(new Date());
				}
				this.orderMapper.insertSelective(order);
				for (OrderItem oi : order.getOrderItemList()) {

					int num = oi.getNum();
					Item item = this.itemService.findById(oi.getItemId());
					String itemSize = oi.getItemSizeType();	// 规格
					int coefficient = 0;
					if ("SIZE".equals(itemSize)) { //普通规格
						coefficient = 1;
					} else if ("SALE_SIZE".equals(itemSize)) { // 零售
						coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
					} else if ("BUY_SIZE".equals(itemSize)) { //批发
						coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
					} else {
						throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
					}
					num = num * coefficient;
					// 修改库存
					stockService.updateForReduce(oi.getItemId(), num);
					oi.setConsumeStockNum(num);
					this.orderItemMapper.insert(oi);
					// throw new RuntimeException("测试事务");
				}
				for (OrderBag orderBag : orderBagList) {
					this.orderBagService.insert(orderBag);
				}
	}

	@Override
	public void createOrderWithBagAndDeductMoney(Order order,
			List<OrderBag> orderBagList, Integer userId) {
		// 如果执行日期为空，则默认为当前日期
		if (order.getExecutedTime() == null) {
			order.setExecutedTime(new Date());
		}
		this.orderMapper.insertSelective(order);
		for (OrderItem oi : order.getOrderItemList()) {

			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			num = num * coefficient;
			// 修改库存
			stockService.updateForReduce(oi.getItemId(), num);
			oi.setConsumeStockNum(num);
			this.orderItemMapper.insert(oi);
			// throw new RuntimeException("测试事务");
		}
		for (OrderBag orderBag : orderBagList) {
			this.orderBagService.insert(orderBag);
		}
		this.wxAccountService.updateForAddMoney(order.getUserId(), -order.getTotalFee());
		insertWXConsumRecords(order.getOrderNo(), order.getUserId(), userId, -order.getTotalFee());
	}

	@Override
	public Pair<Order, List<HashMap<String, Object>>> findBagInfoByOrderNo(String orderNo) {
		Order order = orderMapper.selectByPrimaryKey(orderNo);

		CustomerUser personUser = this.customerService.findById(order.getUserId());

		if (personUser != null) {
			order.setCompanyName(personUser.getCompanyName());
			order.setBuyerPhone(personUser.getMobilePhone());
			order.setUserName(personUser.getUserName());
			order.setAddress(personUser.getAddress());
			order.setCompanyMemo(personUser.getCompanyMemo());
		}
		
		List<HashMap<String, Object>> listBag = orderBagService.findOrderBagAndBagExecuteTimeByOrderNo(orderNo);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		for (HashMap<String, Object> hashMap : listBag) {
			Date time = (Date) hashMap.get("executedTime");
			String name = (String) hashMap.get("bagName");
			String formatTime = df.format(time);
			hashMap.put("bagName", name+formatTime);
		}
		return Pair.of(order, listBag);
	}

	@Override
	public void updateOrderAndBagAndItems(Order order,List<OrderBag> orderBagList) {
		String orderNo = order.getOrderNo();
		Pair<Order, List<OrderItem>> orderPair = this.findByOrderNo(orderNo);
		Order oldOrder = orderPair.getLeft();
		List<OrderItem> oldItemList = orderPair.getRight();
		List<OrderItem> itemList = order.getOrderItemList();

		// 先更新库存
		for (OrderItem oi : oldItemList) {
			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			num = num * coefficient;

			stockService.updateForAdd(item.getId(), num);
		}

		order.setStatus(Constant.VALID_STATUS);
		order.setOrderStatus(oldOrder.getOrderStatus());
		order.setCreatedTime(oldOrder.getCreatedTime());
		this.updateOrder(order);

		// 删掉以前的orderItem，然后重新插入
		this.deleteOrderItem(order.getOrderNo());

		// 新插入item数据
		for (OrderItem oi : itemList) {
			oi.setOrderNo(orderNo);


			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			num = num * coefficient;

			oi.setConsumeStockNum(num);
			orderItemMapper.insert(oi);
			stockService.updateForReduce(item.getId(), num);
		}
		
		this.orderBagService.deleteOrderBag(orderNo);
		for (OrderBag orderBag : orderBagList) {
			this.orderBagService.insert(orderBag);
		}
	}

	@Override
	public void updateOrderAndBagAndItemsAndMoney(Order order,List<OrderBag> orderBagList, Integer userId) {
		String orderNo = order.getOrderNo();
		Pair<Order, List<OrderItem>> orderPair = this.findByOrderNo(orderNo);
		Order oldOrder = orderPair.getLeft();
		List<OrderItem> oldItemList = orderPair.getRight();
		List<OrderItem> itemList = order.getOrderItemList();

		// 先更新库存
		for (OrderItem oi : oldItemList) {
			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			num = num * coefficient;

			stockService.updateForAdd(item.getId(), num);
		}

		order.setStatus(Constant.VALID_STATUS);
		order.setOrderStatus(oldOrder.getOrderStatus());
		order.setCreatedTime(oldOrder.getCreatedTime());
		this.updateOrder(order);

		// 删掉以前的orderItem，然后重新插入
		this.deleteOrderItem(order.getOrderNo());

		// 新插入item数据
		for (OrderItem oi : itemList) {
			oi.setOrderNo(orderNo);


			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			num = num * coefficient;

			oi.setConsumeStockNum(num);
			orderItemMapper.insert(oi);
			stockService.updateForReduce(item.getId(), num);
		}
		
		this.orderBagService.deleteOrderBag(orderNo);
		for (OrderBag orderBag : orderBagList) {
			this.orderBagService.insert(orderBag);
		}
		long money = oldOrder.getTotalFee()-order.getTotalFee();
		this.wxAccountService.updateForAddMoney(order.getUserId(), money);
		this.wxConsumRecordsService.deleteWXConsumRecordsByOrderNo(order.getOrderNo());
		insertWXConsumRecords(order.getOrderNo(), order.getUserId(), userId, -order.getTotalFee());
		
	}
	
	//夜间更新客户最新下单时间
	@Override
	public Date findNearestTimeByUserId(Integer id) {
		return this.orderMapper.findNearestTimeByUserId(id);
	}

	@Override
	public List<Order> findOrderListByUserId(int userId) {
		OrderExample example = new OrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andUserIdEqualTo(userId);
		return this.orderMapper.selectByExample(example);
	}

	@Override
	public int countMoneyByUserAndTime(Integer id, Date firstDate, Date sumDate) {
		return this.orderMapper.countMoneyByUserAndTime(id,firstDate,sumDate);
	}

	@Override
	public int countNumByUserAndTime(Integer id, Date firstDate, Date sumDate) {
		return this.orderMapper.countNumByUserAndTime(id,firstDate,sumDate);
	}

	@Override
	public ArrayList<Integer> findLastTwoOrderItemByCid(Integer customerId) {
		return this.orderMapper.findLastTwoOrderItemByCid(customerId);
	}

	@Override
	public int countMoneyByUserAndTimeAndSign(Integer id, Date firstDate,
			Date sumDate,int sign) {
		return this.orderMapper.countMoneyByUserAndTimeAndSign(id,firstDate,sumDate,sign);
	}
	/**
	 * 便利店采购订单详情
	 * @param id
	 * @return
	 */
	@Override
	public List<HashMap<String, Object>> findItemInfoByOrderNo(String id) {
		return this.orderItemMapper.findItemInfoByOrderNo(id);
	}

	@Override
	public Long findShopOrderByCompanyId(Integer customerUserId) {
		return this.orderMapper.findShopOrderByCompanyId(customerUserId);
	}

	@Override
	public List<Order> findShopOrderListByCompanyId(Integer shopId) {
		return this.orderMapper.findShopOrderListByCompanyId(shopId);
	}

	@Override
	public Long findCurrentMonthSourcingMoney(Integer customerUserId) {
		return this.orderMapper.findCurrentMonthSourcingMoney(customerUserId);
	}

//	@Override
//	public List<Order> findConfirm(Date firstDate, Date sumDate) {
//		return this.orderMapper.findConfirm(firstDate,sumDate);
//	}
    
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void comfirmOrderAndReduceStock(String orderNo,String peisong, String zhidan) {
				Order order = this.orderMapper.selectByPrimaryKey(orderNo);
				order.setPeisong(peisong);
				order.setZhidan(zhidan);
				List<HashMap<String,Object>> list = this.orderItemMapper.findOrderItemByOrderNo(orderNo);
				for (HashMap<String,Object> oi : list) {
					int num = Integer.valueOf(oi.get("consumeStockNum").toString());
					int itemId = Integer.valueOf(oi.get("itemId").toString());
					// 修改库存
					stockService.updateForReduce(itemId, num);
				}
				CustomerUser user = this.customerService.findById(order.getUserId());
				
				order.setComfirm(2);
				
				this.orderMapper.updateByPrimaryKeySelective(order);
		
	}

	@Override
	public void updateOrderAndItemsNoReduceStock(Order order) {
		String orderNo = order.getOrderNo();
		Pair<Order, List<OrderItem>> orderPair = this.findByOrderNo(orderNo);
		Order oldOrder = orderPair.getLeft();
		List<OrderItem> itemList = order.getOrderItemList();

		order.setStatus(Constant.VALID_STATUS);
		order.setOrderStatus(oldOrder.getOrderStatus());
		order.setCreatedTime(oldOrder.getCreatedTime());
		order.setComfirm(oldOrder.getComfirm());
		this.updateOrder(order);

		// 删掉以前的orderItem，然后重新插入
		this.deleteOrderItem(order.getOrderNo());

		// 新插入item数据
		for (OrderItem oi : itemList) {
			oi.setOrderNo(orderNo);
			int num = oi.getNum();
			oi.setConsumeStockNum(num);
			orderItemMapper.insert(oi);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void cancelComfirmOrder(Order order) {
		order.setStatus(Constant.DELETE_STATUS);
		this.orderMapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public int findBeComfirmOrderNumByCityId(Integer cityId) {
		return this.orderMapper.findBeComfirmOrderNumByCityId(cityId);
	}

	@Override
	public int findOrderNumByMonth(Integer id, Date date) {
		return this.orderMapper.findOrderNumByMonth(id,date);
	}

	@Override
	public Order findCurrentMonthFreeShopOrderFee(Integer id, Date date2,
			Date date1) {
		return this.orderMapper.findCurrentMonthFreeShopOrderFee(id,date2,date1);
	}

	@Override
	public int findFreeOrderShopNum(Date date2, Date date1) {
		return this.orderMapper.findFreeOrderShopNum(date2,date1);
	}

	@Override
	public Order findFreeOrderFeeAndOrderNumByMonth(Date date2, Date date1) {
		return this.orderMapper.findFreeOrderFeeAndOrderNumByMonth(date2,date1);
	}

	@Override
	public List<Order> findgongdan(Date startTime, Date endTime,
			String orderNum, String userName, String region, String zhidan,
			String fenjian, String peisong,Integer reseauId, Integer cityId,String type) {
		return this.orderMapper.findgongdan(startTime,endTime,orderNum,userName,region,zhidan,fenjian,peisong,reseauId,cityId,type);
	}

	@Override
	public Order findgongdanCount(Date startTime,
			Date endTime, String orderNum, String userName, String region,
			String zhidan, String fenjian, String peisong,Integer reseauId, Integer cityId,String type) {
		return this.orderMapper.findgongdanCount(startTime,endTime,orderNum,userName,region,zhidan,fenjian,peisong,reseauId,cityId,type);
	}

//	@Override
//	public List<Order> findConfirmNoOnshelf(Date firstDate, Date sumDate) {
//		return this.orderMapper.findConfirmNoOnshelf(firstDate,sumDate);
//	}

	@Override
	public List<Order> findGiftOrdersByCondition(String orderNum,
			String userName, Date startTime, Date endTime,Integer cityId) {
		return this.orderMapper.findGiftOrdersByCondition(orderNum,userName,startTime,endTime,cityId);
	}

	@Override
	public Order findTotalGiftOrdersByCondition(String orderNum,
			String userName, Date startTime, Date endTime,Integer cityId) {
		return this.orderMapper.findTotalGiftOrdersByCondition(orderNum,userName,startTime,endTime,cityId);
	}

	@Override
	public Order findProfitByUserIdAndDate(Integer userid, Date startTime, Date endTime) {
		return this.orderMapper.findProfitByUserIdAndDate(userid,startTime,endTime);
	}

	@Override
	public Date findLastTimeByItemId(Integer itemId) {
		return this.orderMapper.findLastTimeByItemId(itemId);
	}

	@Override
	public List<Order> findByReseauIdAndDate(Date queryDate, Integer reseauId) {
		return this.orderMapper.findByReseauIdAndDate(queryDate,reseauId);
	}

//	@Override
//	public List<String> findPendingDeliveryByDate(Date firstDate,
//			Date sumDate) {
//		return this.orderMapper.findPendingDeliveryByDate(firstDate,sumDate);
//	}

	@Override
	public void changeComfirmStatus(List<String> ordernos) {
		this.orderMapper.changeComfirmStatus(ordernos);
	}

	@Override
	public List<Order> findOrderAndOrderItemByCondition(String orderNum,
			String userName, int param, Date startTime, Date endTime, Integer cityId,String itemName) {
		return this.orderMapper.findOrderAndOrderItemByCondition(orderNum,userName,param,startTime,endTime,cityId,itemName);
	}

	@Override
	public List<Order> findByTimeAndCityId(Date start, Date end, Integer cityId) {
		return this.orderMapper.findByTimeAndCityId(start,end,cityId);
	}
	
	//.
	@Override
	public List<Order> findConfirmByCityId(Date firstDate, Date sumDate,
			Integer cityId) {
		return this.orderMapper.findConfirmByCityId(firstDate,sumDate,cityId);
	}

	@Override
	public List<Order> findConfirmNoOnshelfByCityId(Date firstDate,
			Date sumDate, Integer cityId) {
		return this.orderMapper.findConfirmNoOnshelfByCityId(firstDate,sumDate,cityId);
	}

	@Override
	public List<String> findPendingDeliveryByDateAndCityId(Date firstDate,
			Date sumDate, Integer cityId) {
		return this.orderMapper.findPendingDeliveryByDateAndCityId(firstDate,sumDate,cityId);
	}

	@Override
	public int updateOrderStatusByCityId(Date startTime, Date endTime,
			Integer cityId) {
		int i = 0;
		List<Order> sumList = this.findTranSumOrdersByCityId(startTime, endTime, cityId);
		if (CollectionUtils.isNotEmpty(sumList)) {
			for (Order order : sumList) {
				order.setOrderStatus(OrderStatusEnum.SUM.getId());
				int ret = orderMapper.updateByPrimaryKeySelective(order);
				i = i + ret;
			}
		}
		return i;
	}

	@Override
	public Order findOrderFeeAndOrderNumByDayAndUserId(Integer id, Date date) {
		return this.orderMapper.findOrderFeeAndOrderNumByDayAndUserId(id,date);
	}

	@Override
	public void updateOrderAndCustomer(Order order) {
		this.orderMapper.updateByPrimaryKeySelective(order);
		CustomerUser customerUser = this.customerService.findById(order.getUserId());
		if(null!=customerUser.getContractDate()){
			List<Order> orders = this.orderMapper.findsixtyDayOrder(customerUser.getId());
			if(orders.isEmpty()){
				customerUser.setContractDate(order.getDeliverDate());
				this.customerService.update(customerUser);
			}
		}else{
			customerUser.setContractDate(order.getDeliverDate());
			this.customerService.update(customerUser);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void copyOrder(Order order) {
		// 如果执行日期为空，则默认为当前日期
		if (order.getExecutedTime() == null) {
			order.setExecutedTime(new Date());
		}
		this.orderMapper.insertSelective(order);
		for (OrderItem oi : order.getOrderItemList()) {

			int num = oi.getNum();
			Item item = this.itemService.findById(oi.getItemId());
			String itemSize = oi.getItemSizeType();	// 规格
			int coefficient = 0;
			if ("SIZE".equals(itemSize)) { //普通规格
				coefficient = 1;
			} else if ("SALE_SIZE".equals(itemSize)) { // 零售
				coefficient = (item.getSaleSizeNum()==null)?0:item.getSaleSizeNum();
			} else if ("BUY_SIZE".equals(itemSize)) { //批发
				coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
			} else {
				throw new RuntimeException("无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSize);
			}
			oi.setConsumeStockNum(num);
			this.orderItemMapper.insert(oi);
		}

	}

}
