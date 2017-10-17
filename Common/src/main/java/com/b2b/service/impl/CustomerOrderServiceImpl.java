package com.b2b.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.Constant;
import com.b2b.common.dao.CustomerOrderItemMapper;
import com.b2b.common.dao.CustomerOrderMapper;
import com.b2b.common.domain.CustomerOrder;
import com.b2b.common.domain.CustomerOrderExample;
import com.b2b.common.domain.CustomerOrderExample.Criteria;
import com.b2b.common.domain.CustomerOrderItem;
import com.b2b.common.domain.CustomerOrderItemExample;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.common.domain.StandardOrderItemExample;
import com.b2b.common.domain.Stock;
import com.b2b.common.domain.CustomerUser;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.page.Page;
import com.b2b.service.CustomerOrderService;
import com.b2b.service.CustomerService;
import com.b2b.service.ItemService;
import com.b2b.service.OrderService;
import com.b2b.service.StockService;
import com.b2b.service.UserService;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	private static final Logger logger = LoggerFactory
			.getLogger(CustomerOrderServiceImpl.class);

	@Autowired
	CustomerOrderMapper customerOrderMapper;

	@Autowired
	CustomerOrderItemMapper customerOrderItemMapper;

	@Autowired
	CustomerService customerService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	OrderService orderService;

	@Override
	public Page<Pair<CustomerOrder, List<CustomerOrderItem>>> findOrder(CustomerOrder order,
			Date startTime, Date endTime, int currentPage, int pageSize) {
		Page<Pair<CustomerOrder, List<CustomerOrderItem>>> page = null;

		CustomerOrderExample orderExample = new CustomerOrderExample();
		orderExample.setOrderByClause("created_time desc");
		Criteria criteria = orderExample.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);

		// 开始时间
		if (startTime != null) {
			criteria.andExecutedTimeGreaterThanOrEqualTo(startTime);
		}

		// 结束时间
		if (endTime != null) {
			criteria.andExecutedTimeLessThanOrEqualTo(endTime);
		}
		
        if(order.getCustomerId()!=null&&order.getCustomerId()>0){
        	criteria.andCustomerIdEqualTo(order.getCustomerId());
        }

		int count = customerOrderMapper.countByExample(orderExample);
		if (count == 0) {
			currentPage = 1;
			page = new Page<Pair<CustomerOrder, List<CustomerOrderItem>>>(currentPage, count,
					pageSize, null);
			return page;
		}
		int start = Page.calStartRow(currentPage, pageSize);

		orderExample.setLimit(pageSize);
		orderExample.setStart(start);
		orderExample.setLimitFlag(true);

		List<CustomerOrder> orders = customerOrderMapper.selectByExample(orderExample);
		List<Pair<CustomerOrder, List<CustomerOrderItem>>> orderItemList = new ArrayList<Pair<CustomerOrder, List<CustomerOrderItem>>>();
		for (CustomerOrder order2 : orders) {
			List<CustomerOrderItem> itemList = this.findOrderItem(order2.getCustomerorderId(),
					true);
			CustomerUser personUser = this.customerService.findById(order2.getCustomerId());
			if (personUser != null) {
				order2.setCompanyName(personUser.getCompanyName());
				order2.setBuyerPhone(personUser.getMobilePhone());
				order2.setUserName(personUser.getUserName());
				order2.setCustomerAddress(personUser.getAddress());
				order2.setCompanyMemo(personUser.getCompanyMemo());
			}
			if (CollectionUtils.isNotEmpty(itemList)) {
				orderItemList.add(Pair.of(order2, itemList));
			}
		}

		page = new Page<Pair<CustomerOrder, List<CustomerOrderItem>>>(currentPage, count,
				pageSize, orderItemList);

		return page;
	}
	
	private List<CustomerOrderItem> findOrderItem(int orderNo, boolean needAllInfo) {
		CustomerOrderItemExample itemExample = new CustomerOrderItemExample();
		itemExample.createCriteria().andCustomerorderIdEqualTo(orderNo);
		List<CustomerOrderItem> itemList = customerOrderItemMapper.selectByExample(itemExample);

		if (CollectionUtils.isEmpty(itemList)) {
			logger.warn("无法找到订单商品:" + orderNo);
		} else {
			// 增加商品的分类信息
			if (needAllInfo) {
				for (CustomerOrderItem oi : itemList) {
					if(oi.getItemId()!=null){
						Item item = itemService.findById(oi.getItemId());
						if (item != null) {
							oi.setCategoryId(item.getCategoryId());
						} else {
							logger.warn("无法找到商品！商品id:" + oi.getItemId());
						}
					}
				}
			}
		}
		return itemList;
	}


	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void createCustomerOrder(CustomerOrder order) {
		// 如果执行日期为空，则默认为当前日期
		if (order.getExecutedTime() == null) {
			order.setExecutedTime(new Date());
		}
		this.customerOrderMapper.insert(order);
		List<CustomerOrderItem> resultlist=order.getCustomerOrderList();
		// 根据Collections.sort重载方法来实现
		for (CustomerOrderItem oi : resultlist) {
			oi.setCustomerorderId(order.getCustomerorderId());
			this.customerOrderItemMapper.insert(oi);
		}
	}

	@Override
	public void updateCustomerOrder(CustomerOrder order) {
		// TODO 自动生成的方法存根
		this.customerOrderMapper.updateByPrimaryKeySelective(order);
	}
	
	@Override  
	public Pair<CustomerOrder, List<HashMap<String, Object>>> findByOrderNo(int customerorderId) {
		CustomerOrder order = customerOrderMapper.selectByPrimaryKey(customerorderId);
		CustomerUser personUser = this.customerService.findById(order.getCustomerId());

		if (personUser != null) {
			order.setCompanyName(personUser.getCompanyName());
			order.setBuyerPhone(personUser.getMobilePhone());
			order.setUserName(personUser.getUserName());
			order.setCustomerAddress(personUser.getAddress());
			order.setCompanyMemo(personUser.getCompanyMemo());
		}
		List<HashMap<String, Object>> itemList = customerOrderItemMapper.findOrderItemByOrderNo(customerorderId);
		return Pair.of(order, itemList);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void updateOrderAndItems(CustomerOrder order) {
		int customerorderId = order.getCustomerorderId();
		Pair<CustomerOrder, List<CustomerOrderItem>> orderPair = this.findByOrderNoList(customerorderId);
		CustomerOrder oldOrder = orderPair.getLeft();
		List<CustomerOrderItem> oldItemList = orderPair.getRight();
		List<CustomerOrderItem> itemList = order.getCustomerOrderList();


		order.setStatus(Constant.VALID_STATUS);
		order.setCustomerStatus(oldOrder.getCustomerStatus());
		order.setCreatedTime(oldOrder.getCreatedTime());
		this.updateCustomerOrder(order);

		// 删掉以前的orderItem，然后重新插入
		this.deleteOrderItem(order.getCustomerorderId());

		// 新插入item数据
		for (CustomerOrderItem oi : itemList) {
			oi.setCustomerorderId(customerorderId);


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

			oi.setStockNum(num);
			customerOrderItemMapper.insert(oi);
		}

	}
	
	@Override
	public Pair<CustomerOrder, List<CustomerOrderItem>> findByOrderNoList(int customerorderId){
		CustomerOrder order = customerOrderMapper.selectByPrimaryKey(customerorderId);
		List<CustomerOrderItem> itemList = this.findOrderItem(customerorderId, true);
		return Pair.of(order, itemList);
	}
	
	public void deleteOrderItem(int customerorderId) {
		CustomerOrderItemExample itemExample = new CustomerOrderItemExample();
		itemExample.createCriteria().andCustomerorderIdEqualTo(customerorderId);
		customerOrderItemMapper.deleteByExample(itemExample);
	}
	
	@Override
	public void createOrder(int customerorderId,String orderNo){
		CustomerOrder customerOrder = customerOrderMapper.selectByPrimaryKey(customerorderId);
		Order order=new Order();
		order.setOrderNo(orderNo);
		order.setTotalNum(customerOrder.getTotalNum()); 
		order.setTotalFee(customerOrder.getTotalFee());
		order.setTotalCost(customerOrder.getTotalCost());
		order.setCreatedTime(new Date());
		order.setOrderStatus(OrderStatusEnum.PAY.getId());
		order.setUserId(customerOrder.getCustomerId());
		order.setBusinessId(customerOrder.getBusinessId());
		order.setStatus(Constant.VALID_STATUS);
		order.setAddress(customerOrder.getCustomerAddress());
		order.setMemo(customerOrder.getRemark());
		order.setExecutedTime(customerOrder.getExecutedTime());
		order.setDiscount(customerOrder.getDiscount());
		List<OrderItem> orderItemList=new ArrayList<OrderItem>();
		List<CustomerOrderItem> customerItemList = this.findOrderItem(customerorderId, true);
		for(CustomerOrderItem cust : customerItemList){
			OrderItem orderItem=new OrderItem();
			orderItem.setOrderNo(orderNo);
			orderItem.setItemId(cust.getItemId()); 
			orderItem.setItemName(cust.getItemName()); 
			orderItem.setNum(cust.getNum());
			orderItem.setFee(cust.getFee());
			orderItem.setItemPrice(cust.getItemPrice());
			orderItem.setItemSize(cust.getItemSize());
			orderItem.setItemCostPrice(cust.getItemCostPrice());
			orderItem.setItemSizeType(cust.getItemSizeType());
			orderItem.setConsumeStockNum(cust.getStockNum());
			orderItemList.add(orderItem);
		}
		order.setOrderItemList(orderItemList);
		orderService.createOrder(order);
	}
	
	@Override
	public List<CustomerOrderItem> getPurchasePlan(){
		return customerOrderItemMapper.selectPurchasePlan();
	}
	
}
