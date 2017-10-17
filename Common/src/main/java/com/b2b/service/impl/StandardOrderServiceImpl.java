package com.b2b.service.impl;

import java.util.ArrayList;
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
import com.b2b.common.dao.StandardOrderItemMapper;
import com.b2b.common.dao.StandardOrderMapper;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.OrderItemExample;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderExample;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.StandardOrderExample.Criteria;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.common.domain.StandardOrderItemExample;
import com.b2b.common.domain.Stock;
import com.b2b.page.Page;
import com.b2b.service.ItemService;
import com.b2b.service.StandardOrderService;
import com.b2b.service.StockService;
import com.b2b.service.UserService;

@Service
public class StandardOrderServiceImpl implements StandardOrderService {

	private static final Logger logger = LoggerFactory
			.getLogger(StandardOrderServiceImpl.class);

	@Autowired
	StandardOrderMapper standardOrderMapper;

	@Autowired
	StandardOrderItemMapper standardOrderItemMapper;

	@Autowired
	UserService userService;
	
	@Autowired
	ItemService itemService;
	

	@Override
	public Page<Pair<StandardOrder, List<StandardOrderItem>>> findStandardOrder(StandardOrder order,
			Date startTime, Date endTime, int currentPage, int pageSize) {
		Page<Pair<StandardOrder, List<StandardOrderItem>>> page = null;

		StandardOrderExample orderExample = new StandardOrderExample();
		orderExample.setOrderByClause(" executed_time desc");
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
		
		int standardorderId = order.getStandardorderId();

		if (standardorderId>0) {
			criteria.andStandardorderIdEqualTo(standardorderId);
		}
		
		if(order.getSnackpackageType()!=null){
			criteria.andSnackpackageTypeEqualTo(order.getSnackpackageType());
		}else{
			criteria.andSnackpackageTypeGreaterThanOrEqualTo(3);
		}

		int count = standardOrderMapper.countByExample(orderExample);
		if (count == 0) {
			currentPage = 1;
			page = new Page<Pair<StandardOrder, List<StandardOrderItem>>>(currentPage, count,
					pageSize, null);
			return page;
		}
		int start = Page.calStartRow(currentPage, pageSize);

		orderExample.setLimit(pageSize);
		orderExample.setStart(start);
		orderExample.setLimitFlag(true);

		List<StandardOrder> orders = standardOrderMapper.selectByExample(orderExample);
		List<Pair<StandardOrder, List<StandardOrderItem>>> orderItemList = new ArrayList<Pair<StandardOrder, List<StandardOrderItem>>>();
		for (StandardOrder order2 : orders) {
			List<StandardOrderItem> itemList = this.findOrderItem(order2.getStandardorderId(),
					true);
			if (CollectionUtils.isNotEmpty(itemList)) {
				orderItemList.add(Pair.of(order2, itemList));
			}
		}

		page = new Page<Pair<StandardOrder, List<StandardOrderItem>>>(currentPage, count,
				pageSize, orderItemList);

		return page;
	}
	
	public List<StandardOrderItem> selectByTime(Date startTime, Date endTime) {
		List<StandardOrderItem> orderItemList=standardOrderItemMapper.selectItemNum(startTime,endTime);
		return orderItemList;
	}
	
	@Override
	public int getStandardOrderByOrder(Date time,int snackpackageType){
		StandardOrderExample orderExample = new StandardOrderExample();
		Criteria criteria = orderExample.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);

		// 执行时间
		if (time != null) {
			criteria.andExecutedTimeEqualTo(time);
		}
		
		if(snackpackageType>0){
			criteria.andSnackpackageTypeEqualTo(snackpackageType);
		}
		
		List<StandardOrder> orders =standardOrderMapper.selectByExample(orderExample);
		if(orders.size()>0){
			StandardOrder or=orders.get(0);
			if(or!=null){
				return or.getStandardorderId();
			}
		}
		return 0;
	}

	private List<StandardOrderItem> findOrderItem(int orderNo, boolean needAllInfo) {
		StandardOrderItemExample itemExample = new StandardOrderItemExample();
		itemExample.createCriteria().andStandardorderIdEqualTo(orderNo);
		List<StandardOrderItem> itemList = standardOrderItemMapper.selectByExample(itemExample);

		if (CollectionUtils.isEmpty(itemList)) {
			logger.warn("无法找到订单商品:" + orderNo);
		} else {
			// 增加商品的分类信息
			if (needAllInfo) {
				for (StandardOrderItem oi : itemList) {
					Item item = itemService.findById(oi.getItemId());
					if (item != null) {
						oi.setCategoryId(item.getCategoryId());
					} else {
						logger.warn("无法找到商品！商品id:" + oi.getItemName());
					}

//					Stock stock = stockService.findByItem(oi.getItemId());
//					if (stock != null) {
//						oi.setItemStock(stock.getNum());
//					} else {
//						logger.warn("无法找到商品的库存！商品id:" + oi.getItemName());
//					}
				}
			}
		}
		return itemList;
	}


	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void createStandardOrder(StandardOrder order) {
		// 如果执行日期为空，则默认为当前日期
		if (order.getExecutedTime() == null) {
			order.setExecutedTime(new Date());
		}
		this.standardOrderMapper.insert(order);
		for (StandardOrderItem oi : order.getStandardOrderList()) {

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
			oi.setStandardorderId(order.getStandardorderId());
			oi.setStockNum(num);
			this.standardOrderItemMapper.insert(oi);
		}

	}

	@Override
	public void updateStandardOrder(StandardOrder order) {
		// TODO 自动生成的方法存根
		this.standardOrderMapper.updateByPrimaryKeySelective(order);
	}
	
	/**
	 * 订单列表，编辑订单，已下架的商品仍然显示不能编辑
	 */
	@Override
	public Pair<StandardOrder, List<HashMap<String, Object>>> findInfoByOrderNo(int orderNo) {
		StandardOrder order = standardOrderMapper.selectByPrimaryKey(orderNo);
		//order.setOfferPrice(order.getOfferPrice()/100);
		List<HashMap<String, Object>> itemList = standardOrderItemMapper.findOrderItemByOrderNo(orderNo);
		return Pair.of(order, itemList);
	}
	
	public void deleteOrderItem(int standardorderId) {
		StandardOrderItemExample itemExample = new StandardOrderItemExample();
		itemExample.createCriteria().andStandardorderIdEqualTo(standardorderId);
		standardOrderItemMapper.deleteByExample(itemExample);
	}
	
	@Override
	public Pair<StandardOrder, List<StandardOrderItem>> findByOrderNo(int standardorderId) {
		StandardOrder order = standardOrderMapper.selectByPrimaryKey(standardorderId);
		//order.setOfferPrice(order.getOfferPrice()/100);
		List<StandardOrderItem> itemList = this.findOrderItem(standardorderId, true);
		return Pair.of(order, itemList);
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void updateOrderAndItems(StandardOrder order) {
		int standardorderId = order.getStandardorderId();
		Pair<StandardOrder, List<StandardOrderItem>> orderPair = this.findByOrderNo(standardorderId);
		StandardOrder oldOrder = orderPair.getLeft();
		List<StandardOrderItem> oldItemList = orderPair.getRight();
		List<StandardOrderItem> itemList = order.getStandardOrderList();


		order.setStatus(Constant.VALID_STATUS);
		order.setStandardStatus(oldOrder.getStandardStatus());
		order.setCreatedTime(oldOrder.getCreatedTime());
		this.updateStandardOrder(order);

		// 删掉以前的orderItem，然后重新插入
		this.deleteOrderItem(order.getStandardorderId());

		// 新插入item数据
		for (StandardOrderItem oi : itemList) {
			oi.setStandardorderId(standardorderId);


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
			standardOrderItemMapper.insert(oi);
		}

	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public int selectStandardOrderCount(Date stratTime,Date endTime,int snackpackageType){
		StandardOrderExample orderExample = new StandardOrderExample();
		Criteria criteria = orderExample.createCriteria();
		criteria.andStatusEqualTo(Constant.VALID_STATUS);
		if(stratTime!=null){
			criteria.andExecutedTimeGreaterThanOrEqualTo(stratTime);
		}
		if(endTime!=null){
			criteria.andExecutedTimeLessThanOrEqualTo(endTime);
		}
		if(snackpackageType>0){
			criteria.andSnackpackageTypeEqualTo(snackpackageType);
		}
		List<StandardOrder> listStandard= standardOrderMapper.selectByExample(orderExample);
		return listStandard.size();
	}
	
	
	@Override
	public List<StandardOrder> getCurWeekStandOrder(){
		return standardOrderMapper.getCurWeekStandOrder();
	}
    
	@Override
	public List<StandardOrder> getStandOrderById(int snackpackageId){
		return standardOrderMapper.getStandOrderById(snackpackageId);
	}

	@Override
	public StandardOrder findById(String id) {
		return this.standardOrderMapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	@Override
	public StandardOrder findByStandId(String standId) {
		return this.standardOrderMapper.findByStandId(Integer.parseInt(standId));
	}

	@Override
	public List<StandardOrderItem> findOrderItemById(int id) {
		StandardOrderItemExample itemExample = new StandardOrderItemExample();
		itemExample.createCriteria().andStandardorderIdEqualTo(id);
		List<StandardOrderItem> itemList = standardOrderItemMapper.selectByExample(itemExample);
		return itemList;
	}

	@Override
	public List<StandardOrder> getCurWeekAndLastWeekStandOrder(Date now, Date date) {
		return this.standardOrderMapper.getCurWeekAndLastWeekStandOrder(now,date);
	}
}
