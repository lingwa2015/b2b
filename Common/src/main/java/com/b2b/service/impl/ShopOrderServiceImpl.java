package com.b2b.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.common.dao.ShopOrderMapper;
import com.b2b.common.domain.ActualShopReport;
import com.b2b.common.domain.NotifyState;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.RedReceive;
import com.b2b.common.domain.Refund;
import com.b2b.common.domain.ShopOrder;
import com.b2b.common.domain.ShopOrderItem;
import com.b2b.common.domain.ShopRefund;
import com.b2b.common.domain.TranConsume;
import com.b2b.service.NotifyStateService;
import com.b2b.service.OrderService;
import com.b2b.service.RedAccountService;
import com.b2b.service.RedReceiveService;
import com.b2b.service.RefundService;
import com.b2b.service.ShopItemService;
import com.b2b.service.ShopOrderItemService;
import com.b2b.service.ShopOrderService;
import com.b2b.service.ShopRefundService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {
	@Autowired
	private ShopOrderMapper shopOrderMapper;
	
	@Autowired 
	ShopOrderItemService shopOrderItemService;
	
	@Autowired
    private ShopItemService shopItemService;
	
	@Autowired
	private NotifyStateService notifyStateService;
	
	@Autowired
	private ShopRefundService shopRefundService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	RefundService refundService;
	
	@Autowired
	RedAccountService redAccountService;
	
	@Autowired
	RedReceiveService redReceiveService;
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void create(ShopOrder order) {
		this.shopOrderMapper.insert(order);
		List<ShopOrderItem> list = order.getShopOrderItems();
		for (ShopOrderItem shopOrderItem : list) {
			this.shopOrderItemService.create(shopOrderItem);
			//this.shopItemService.updateNum(shopOrderItem.getItemId(),shopOrderItem.getNum());
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void changeStatus(String orderId) {
		ShopOrder order = new ShopOrder();
		order.setId(orderId);
		order.setStatus(1);
		this.shopOrderMapper.updateByPrimaryKeySelective(order);
		List<ShopOrderItem> list = this.shopOrderItemService.findItemByOrderId(orderId);
		for (ShopOrderItem shopOrderItem : list) {
			this.shopItemService.updateNum(shopOrderItem.getItemId(),shopOrderItem.getNum());
		}
		ShopOrder shopOrder = this.shopOrderMapper.selectByPrimaryKey(orderId);
		if(null!=shopOrder.getRedPrice() && 0<shopOrder.getRedPrice()){
			this.redAccountService.updateAccountMoneyByUserIdAndType(shopOrder.getBuyerId(), shopOrder.getSign(), -shopOrder.getRedPrice());
		}
		NotifyState state = new NotifyState();
		state.setId(orderId);
		state.setStatus(1);
		state.setCreatedTime(new Date());
		this.notifyStateService.insert(state);
	}

	@Override
	public Long findCurrentMonthConsumeMoney(Integer shopId) {
		return this.shopOrderMapper.findCurrentMonthConsumeMoney(shopId);
	}

	@Override
	public HashMap<String, Object> findTodayConsumeMoney(Integer shopId) {
		return this.shopOrderMapper.findTodayConsumeMoney(shopId);
	}

	@Override
	public List<ShopOrder> findOrderAndItemByShopId(Integer shopId,String name) {
		return this.shopOrderMapper.findOrderAndItemByShopId(shopId,name);
	}

	@Override
	public List<ShopOrder> findMonthRecordByShopId(Integer shopId) {
		return this.shopOrderMapper.findMonthRecordByShopId(shopId);
	}

//	@Override
//	public List<TranConsume> createTranConsume(Date firstDate, Date sumDate) {
//		List<TranConsume> list = Lists.newArrayList();
//		Map<Integer, TranConsume> map = Maps.newHashMap();
//		List<ShopOrder> orders = this.shopOrderMapper.findByDate(firstDate,sumDate);
//		List<Order> order = this.orderService.findConfirm(firstDate,sumDate);
//		if (CollectionUtils.isNotEmpty(orders)) {
//			for (ShopOrder shopOrder : orders) {
//				int userId = shopOrder.getShopId();
//				TranConsume tranSum = map.get(userId);
//				if(tranSum==null){
//					TranConsume consume = new TranConsume();
//					consume.setConsume(shopOrder.getTotalPrice());
//					consume.setActualConsume(shopOrder.getActualPrice());
//					consume.setCreateDate(new Date());
//					consume.setUserId(userId);
//					consume.setSourcing(0l);
//					consume.setSourcingNo(0l);
//					map.put(userId, consume);
//				}else{
//					tranSum.setConsume(tranSum.getConsume()+shopOrder.getTotalPrice());
//					tranSum.setActualConsume(tranSum.getActualConsume()+shopOrder.getActualPrice());
//				}
//			}
//		}
//		for (Order order2 : order) {
//			int userId = order2.getUserId();
//			TranConsume tranSum = map.get(userId);
//			if(tranSum==null){
//				TranConsume consume = new TranConsume();
//				consume.setSourcing(order2.getTotalFee());
//				consume.setCreateDate(new Date());
//				consume.setUserId(userId);
//				consume.setSourcingNo(0l);
//				map.put(userId, consume);
//			}else{
//				tranSum.setSourcing(tranSum.getSourcing()+order2.getTotalFee());
//			}
//		}
//		List<Order> orderno = this.orderService.findConfirmNoOnshelf(firstDate,sumDate);
//		for (Order order3 : orderno) {
//			int userId = order3.getUserId();
//			TranConsume tranSum = map.get(userId);
//			if(tranSum==null){
//				TranConsume consume = new TranConsume();
//				consume.setSourcingNo(order3.getTotalFee());
//				consume.setCreateDate(new Date());
//				consume.setUserId(userId);
//				map.put(userId, consume);
//			}else{
//				tranSum.setSourcingNo(tranSum.getSourcingNo()+order3.getTotalFee());
//			}
//		}
//		//tuohuo 
//		List<Refund> refundList = refundService.findTranSumRefunds(firstDate,
//				sumDate);
//		if (CollectionUtils.isNotEmpty(refundList)) {
//			for (Refund refund : refundList) {
//				int userId = refund.getUserId();
//				TranConsume tranSum = map.get(userId);
//				if (tranSum == null) {
//					TranConsume consume = new TranConsume();
//					consume.setSourcing(-refund.getTotalFee());
//					consume.setCreateDate(new Date());
//					consume.setUserId(userId);
//					map.put(userId, consume);
//				}else{
//					tranSum.setSourcing(tranSum.getSourcing()-refund.getTotalFee());
//				}
//			}
//		}
//		list.addAll(map.values());
//
//		return list;
//	}

	@Override
	public ShopOrder findById(String id) {
		return this.shopOrderMapper.selectByPrimaryKey(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void changeRefundStatus(ShopOrder order,Integer id) {
		order.setStatus(2);
		this.shopOrderMapper.updateByPrimaryKeySelective(order);
		ShopRefund refund = new ShopRefund();
		refund.setOrderId(order.getId());
		refund.setCreatedTime(new Date());
		refund.setShopUser(id);
		this.shopRefundService.create(refund);
		if(order.getRedPrice()>0){
			this.redAccountService.updateAccountMoneyByUserIdAndType(order.getBuyerId(), order.getSign(), order.getRedPrice());
		}
		RedReceive receive = this.redReceiveService.findByOrderNo(order.getId());
		if(null!=receive){
			this.redAccountService.updateAccountMoneyByUserIdAndType(order.getBuyerId(), order.getSign(), -receive.getRedFee());
			receive.setSign(2);//退回
			this.redReceiveService.update(receive);
		}
	}

	@Override
	public ShopOrder findMonthRecordByShopIdAndDate(Integer shopId,
			String month) {
		return this.shopOrderMapper.findMonthRecordByShopIdAndDate(shopId,month);
	}

	@Override
	public ShopOrder findShopDailyReport(Date startDate, Date endDate) {
		return this.shopOrderMapper.findShopDailyReport(startDate,endDate);
	}

	@Override
	public ShopOrder findByShopIdAndDate(Integer id, Date startDate,
			Date endDate) {
		return this.shopOrderMapper.findByShopIdAndDate(id,startDate,endDate);
	}

	@Override
	public int findByDateGroupByBuyer(Date startDate, Date endDate) {
		return this.shopOrderMapper.findByDateGroupByBuyer(startDate,endDate);
	}

	@Override
	public int findMonthConsumeNumByMonth(Date date2) {
		return this.shopOrderMapper.findMonthConsumeNumByMonth(date2);
	}

	@Override
	public int findByMonthGroupByBuyer(Date date2) {
		return this.shopOrderMapper.findByMonthGroupByBuyer(date2);
	}

	@Override
	public int findPenByMonthGroupByBuyer(Date date) {
		return this.shopOrderMapper.findPenByMonthGroupByBuyer(date);
	}

	@Override
	public int findConsumeNumByShopIdAndMonth(Integer id, Date date) {
		return this.shopOrderMapper.findConsumeNumByShopIdAndMonth(id,date);
	}

	@Override
	public int findConsumePenByShopIdAndMonth(Integer id, Date date) {
		return this.shopOrderMapper.findConsumePenByShopIdAndMonth(id,date);
	}

	@Override
	public int findTotalLossPercentByMonth(Date date) {
		return this.shopOrderMapper.findTotalLossPercentByMonth(date);
	}

	@Override
	public Date findFirstPayDay(Integer id) {
		return this.shopOrderMapper.findFirstPayDay(id);
	}

	@Override
	public ShopOrder findWeekFeeByShopIdAndDate(Integer id, Date startDate,
			Date endDate) {
		return this.shopOrderMapper.findWeekFeeByShopIdAndDate(id,startDate,endDate);
	}

	@Override
	public Integer findPenByShopIdAndDate(Integer id, Date startDate,
			Date endDate) {
		return this.shopOrderMapper.findPenByShopIdAndDate(id,startDate,endDate);
	}

	@Override
	public Integer findConsumeNumByShopIdAndDateGroupBuyer(Integer id,
			Date startDate, Date endDate) {
		return this.shopOrderMapper.findConsumeNumByShopIdAndDateGroupBuyer(id,startDate,endDate);
	}

	@Override
	public List<ShopOrder> findOrderAndItem(String name, String shopname,String param,Integer cityId,String itemName) {
		return this.shopOrderMapper.findOrderAndItem(name,shopname,param,cityId,itemName);
	}

	@Override
	public ShopOrder findTodayAllConsumeMoney(Integer cityId) {
		return this.shopOrderMapper.findTodayAllConsumeMoney(cityId);
	}
	
	/**
	 * 实时单店日报
	 */
	@Override
	public List<ActualShopReport> findactualShopReportList(String param, String username, int reseauId,Integer cityId) {
		return this.shopOrderMapper.findactualShopReportList(param,username,reseauId,cityId);
	}

	@Override
	public List<ShopOrder> findOrderAndItemByUseridAndtype(Integer userid,
			Integer type) {
		return this.shopOrderMapper.findOrderAndItemByUseridAndtype(userid,type);
	}

	@Override
	public Long findFreeFee(Date query,Integer buyerid, int i) {
		return this.shopOrderMapper.findFreeFee(query,buyerid,i);
	}

	@Override
	public Integer findKindsByShopIdAndDate(Integer id, Date startDate,
			Date endDate) {
		return this.shopOrderMapper.findKindsByShopIdAndDate(id,startDate,endDate);
	}

	@Override
	public ShopOrder findProfitByShopIdAndDate(Integer id, Date fdate,
			Date endDate) {
		return this.shopOrderMapper.findProfitByShopIdAndDate(id,fdate,endDate);
	}

	@Override
	public ShopOrder findConsumeMoneyByReseauIdAndDate(Integer reseauId,Date startdate,Date enddate) {
		return this.shopOrderMapper.findConsumeMoneyByReseauIdAndDate(reseauId,startdate,enddate);
	}

	@Override
	public List<ShopOrder> findReseauCountInfo(Integer reseauId) {
		return this.shopOrderMapper.findReseauCountInfo(reseauId);
	}

	@Override
	public ActualShopReport findactualShopReportByShopId(Integer shopId) {
		return this.shopOrderMapper.findactualShopReportByShopId(shopId);
	}

	@Override
	public List<TranConsume> createTranConsumeByCityId(Date firstDate,
			Date sumDate, Integer cityId) {
		List<TranConsume> list = Lists.newArrayList();
		Map<Integer, TranConsume> map = Maps.newHashMap();
		List<ShopOrder> orders = this.shopOrderMapper.findByDateAndCityId(firstDate,sumDate,cityId);
		List<Order> order = this.orderService.findConfirmByCityId(firstDate,sumDate,cityId);
		if (CollectionUtils.isNotEmpty(orders)) {
			for (ShopOrder shopOrder : orders) {
				int userId = shopOrder.getShopId();
				TranConsume tranSum = map.get(userId);
				if(tranSum==null){
					TranConsume consume = new TranConsume();
					consume.setConsume(shopOrder.getTotalPrice());
					consume.setActualConsume(shopOrder.getActualPrice());
					consume.setRedFee(shopOrder.getRedPrice());
					consume.setLwFeeOne(shopOrder.getLwFeeOne());
					consume.setCreateDate(new Date());
					consume.setUserId(userId);
					consume.setSourcing(0l);
					consume.setSourcingNo(0l);
					map.put(userId, consume);
				}else{
					tranSum.setConsume(tranSum.getConsume()+shopOrder.getTotalPrice());
					tranSum.setActualConsume(tranSum.getActualConsume()+shopOrder.getActualPrice());
					tranSum.setRedFee(tranSum.getRedFee()+shopOrder.getRedPrice());
					tranSum.setLwFeeOne(tranSum.getLwFeeOne()+shopOrder.getLwFeeOne());
				}
			}
		}
		for (Order order2 : order) {
			int userId = order2.getUserId();
			TranConsume tranSum = map.get(userId);
			if(tranSum==null){
				TranConsume consume = new TranConsume();
				consume.setSourcing(order2.getTotalFee());
				consume.setCreateDate(new Date());
				consume.setUserId(userId);
				consume.setSourcingNo(0l);
				map.put(userId, consume);
			}else{
				tranSum.setSourcing(tranSum.getSourcing()+order2.getTotalFee());
			}
		}
		List<Order> orderno = this.orderService.findConfirmNoOnshelfByCityId(firstDate,sumDate,cityId);
		for (Order order3 : orderno) {
			int userId = order3.getUserId();
			TranConsume tranSum = map.get(userId);
			if(tranSum==null){
				TranConsume consume = new TranConsume();
				consume.setSourcingNo(order3.getTotalFee());
				consume.setCreateDate(new Date());
				consume.setUserId(userId);
				map.put(userId, consume);
			}else{
				tranSum.setSourcingNo(tranSum.getSourcingNo()+order3.getTotalFee());
			}
		}
		//tuohuo 
		List<Refund> refundList = refundService.findTranSumRefundsByCityId(firstDate, sumDate, cityId);
		if (CollectionUtils.isNotEmpty(refundList)) {
			for (Refund refund : refundList) {
				int userId = refund.getUserId();
				TranConsume tranSum = map.get(userId);
				if (tranSum == null) {
					TranConsume consume = new TranConsume();
					consume.setSourcing(-refund.getTotalFee());
					consume.setCreateDate(new Date());
					consume.setUserId(userId);
					map.put(userId, consume);
				}else{
					tranSum.setSourcing(tranSum.getSourcing()-refund.getTotalFee());
				}
			}
		}
		list.addAll(map.values());

		return list;
	}

	@Override
	public ShopOrder findTodayAllConsumeMoneyByCityId(Integer cityId) {
		return this.shopOrderMapper.findTodayAllConsumeMoneyByCityId(cityId);
	}

	@Override
	public Integer findTodayOrderNumByBuyerIdAndSign(Integer buyerId, int sign) {
		return this.shopOrderMapper.findTodayOrderNumByBuyerIdAndSign(buyerId,sign);
	}

	@Override
	public Long findTotalUseRedFeeByCityId(Integer cityId) {
		return this.shopOrderMapper.findTotalUseRedFeeByCityId(cityId);
	}

	@Override
	public ShopOrder findTodayAllConsumeMoneyByInterfacerId(Integer cityId, List<Integer> ids) {
		return this.shopOrderMapper.findTodayAllConsumeMoneyByInterfacerId(cityId,ids);
	}

	@Override
	public ShopOrder findConsumeMoneyByCityIdAndReseauIdsAndDate(Integer cityId, List<Integer> ids, Date startdate,
			Date enddate) {
		return this.shopOrderMapper.findConsumeMoneyByCityIdAndReseauIdsAndDate(cityId,ids,startdate,enddate);
	}

	@Override
	public ShopOrder findConsumeMoneyByCityIdAndDate(Integer cityId, Date startdate, Date enddate) {
		return this.shopOrderMapper.findConsumeMoneyByCityIdAndDate(cityId,startdate,enddate);
	}

	@Override
	public List<ShopOrder> findConsumeMoneyByReseauIdsAndDateAndCityId(List<Integer> ids, Date startdate, Date enddate, Date date,Integer cityId) {
		return this.shopOrderMapper.findConsumeMoneyByReseauIdsAndDateAndCityId(ids,startdate,enddate,date,cityId);
	}

	@Override
	public List<ShopOrder> findConsumeMoneyByDateAndCityId(Integer cityId, Date startdate, Date enddate, Date date2) {
		return this.shopOrderMapper.findConsumeMoneyByDateAndCityId(cityId,startdate,enddate,date2);
	}

}
