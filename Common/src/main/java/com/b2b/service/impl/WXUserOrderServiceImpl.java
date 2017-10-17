package com.b2b.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.StandardOrderItemMapper;
import com.b2b.common.dao.WXUserOrderMapper;
import com.b2b.common.domain.NotifyState;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderBag;
import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.common.domain.WXUserAddress;
import com.b2b.common.domain.WXUserInvoice;
import com.b2b.common.domain.WXUserOrder;
import com.b2b.common.domain.WXUserOrderExample;
import com.b2b.common.domain.WXUserOrderExample.Criteria;
import com.b2b.page.Page;
import com.b2b.service.NotifyStateService;
import com.b2b.service.OrderService;
import com.b2b.service.StandardOrderService;
import com.b2b.service.WXUserAddressService;
import com.b2b.service.WXUserInvoiceService;
import com.b2b.service.WXUserOrderService;

@Service
public class WXUserOrderServiceImpl implements WXUserOrderService {
	@Autowired
	private WXUserOrderMapper wXUserOrderMapper;
	
	@Autowired
	private StandardOrderItemMapper standardOrderItemMapper;
	
	@Autowired
	private StandardOrderService standardOrderService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private WXUserInvoiceService wxUserInvoiceService;
	
	@Autowired
	private WXUserAddressService wxuserAddressService;
	
	@Autowired
	private NotifyStateService notifyStateService;
	
	@Override
	public Page<Pair<WXUserOrder, List<StandardOrderItem>>> findAll(
			Date startTime, Date endTime, int currentPage, int pageSize,
			String orderNum, String userName, String param) {
		Page<Pair<WXUserOrder, List<StandardOrderItem>>> page = null;
		int count = this.wXUserOrderMapper.findCountByCondition(startTime,endTime,orderNum,userName,param);
		if (count == 0) {
			currentPage = 1;
			page = new Page<Pair<WXUserOrder, List<StandardOrderItem>>>(currentPage, count,pageSize, null);
			return page;
		}
		int start = Page.calStartRow(currentPage, pageSize);
		List<WXUserOrder> wxOrder = this.wXUserOrderMapper.findOrderByCondition(startTime,endTime,orderNum,userName,param,start,pageSize);
		List<Pair<WXUserOrder, List<StandardOrderItem>>> list = new ArrayList<Pair<WXUserOrder, List<StandardOrderItem>>>();
		for (WXUserOrder order : wxOrder) {
			List<StandardOrderItem> itemList = this.standardOrderItemMapper.findByStandOrderId(order.getSnackpackageId());
			if (CollectionUtils.isNotEmpty(itemList)) {
				list.add(Pair.of(order, itemList));
			}
		}
		page = new Page<Pair<WXUserOrder, List<StandardOrderItem>>>(currentPage, count,
				pageSize, list);

		return page;
	}
	
	@Override
	public Page<WXUserOrder> findAllwxOrder(Date startTime, Date endTime,
			int currentPage, int pageSize, String orderNum,
			String userName, String param) {
		Page<WXUserOrder> page = null;
		int count = this.wXUserOrderMapper.findCountByCondition(startTime,endTime,orderNum,userName,param);
		if (count == 0) {
			currentPage = 1;
			page = new Page<WXUserOrder>(currentPage, count,pageSize, null);
			return page;
		}
		int start = Page.calStartRow(currentPage, pageSize);
		List<WXUserOrder> wxOrderList = this.wXUserOrderMapper.findAllwxOrderByCondition(startTime,endTime,orderNum,userName,param,start,pageSize);
		page = new Page<WXUserOrder>(currentPage, count,pageSize, wxOrderList);
		return page;
	}


	@Override
	public WXUserOrder findById(Integer id) {
		return this.wXUserOrderMapper.findById(id);
	}

	@Override
	public void createOrder(Order order,List<OrderBag> orderBagList,String fastname, String fastnum, Integer id) {
		this.orderService.createOrderWithBag(order, orderBagList);
		String fastNO = "";
		if(fastname!=""&&fastnum!=""){
			fastNO = fastname+"  "+fastnum;
			this.updateFast(fastNO,id);
		}
		this.changePayfeeStatus(order.getOrderNo(),1);
	}
	
	
	
	

	@Override
	public Pair<WXUserOrder, List<StandardOrderItem>> findPairById(int id) {
		WXUserOrder order = this.findById(id);
		List<StandardOrderItem> itemList = this.standardOrderItemMapper.findByStandOrderId(order.getSnackpackageId());
		return Pair.of(order,itemList);
	}

	@Override
	public List<WXUserOrder> findByWXUserId(Integer id) {
		return this.wXUserOrderMapper.findByWXUserId(id);
	}

	@Override
	public WXUserOrder findOrderDetailById(String id) {
		return this.wXUserOrderMapper.findOrderDetailById(id);
	}

	@Override
	public void updateFast(String fastNO, Integer id) {
		if(""==fastNO){
			this.wXUserOrderMapper.deleteFastNo(id);
		}else{
			this.wXUserOrderMapper.modifyFastNo(id,fastNO);
		}
	}

	@Override
	public void changePayStatus(String id) {
		WXUserOrderExample example = new WXUserOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andWxorderNumEqualTo(id);
		List<WXUserOrder> list = this.wXUserOrderMapper.selectByExample(example);
		this.wXUserOrderMapper.updatePayfeeStatusByOrderNo(list.get(0).getId());
		NotifyState state = new NotifyState();
		state.setId(id);
		state.setStatus(1);
		state.setCreatedTime(new Date());
		this.notifyStateService.insert(state);
	}

	public void changePayfeeStatus(String id, int i) {
		WXUserOrderExample example = new WXUserOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andWxorderNumEqualTo(id);
		List<WXUserOrder> list = this.wXUserOrderMapper.selectByExample(example);
		WXUserOrder order = new WXUserOrder();
		order.setId(list.get(0).getId());
		order.setPayfeeStatus(i);
		this.wXUserOrderMapper.updateByPrimaryKeySelective(order);
	}
	
	//退货
	@Override
	public void changePayfeeStatus(WXUserOrder order) {
		this.wXUserOrderMapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public WXUserOrder save(String id, String num, String totalfee,
			String wxname, String wxphone, String wxadress, String invoiceid,
			String invoiceName, String orderNo, Integer userId,int isgroup) {
		StandardOrder standardOrder = this.standardOrderService.findById(id);
		if(null!=standardOrder){
			Long price = standardOrder.getOfferPrice();
			Long total = Long.parseLong(num) * price;
			Long tatalFee = Long.parseLong(totalfee);
			if(total.equals(tatalFee) ){
				WXUserOrder order = new WXUserOrder();
				order.setWxname(wxname);
				order.setWxphone(wxphone);
				order.setWxaddress(wxadress);
				order.setCreatedTime(new Date());
				order.setWxuserId(userId);
				order.setWxorderNum(orderNo);
				order.setSnackpackageId(Integer.parseInt(id));
				order.setSnackpackageNum(Integer.parseInt(num));
				order.setSnackpackageFee(price);
				order.setSnackpackageTotal(Long.parseLong(totalfee));
				order.setPayfeeStatus(-1);
				order.setStatus(isgroup);
				order.setGroups(isgroup);
				if(""!=invoiceName&&null!=invoiceName){
					if(""!=invoiceid&&null!=invoiceid){
						this.wxUserInvoiceService.update(Integer.parseInt(invoiceid),invoiceName);
						order.setInvoiceId(Integer.parseInt(invoiceid));
						order.setInvoiceStatus(1);
					}else{
						WXUserInvoice invoice = new WXUserInvoice();
						invoice.setCompanyName(invoiceName);
						invoice.setWxuserId(userId);
						this.wxUserInvoiceService.save(invoice);
						order.setInvoiceId(invoice.getId());
						order.setInvoiceStatus(1);
					}
				}else{
					order.setInvoiceStatus(0);
				}
				this.wXUserOrderMapper.insert(order);
				List<WXUserAddress> list=wxuserAddressService.selectByExample(userId);
				if(!list.isEmpty()){
					WXUserAddress wxUserAddress = new WXUserAddress();
					wxUserAddress.setAddressDetails(wxadress);
					wxUserAddress.setName(wxname);
					wxUserAddress.setPhone(wxphone);
					wxUserAddress.setUpdatedTime(new Date());
					wxUserAddress.setId(list.get(0).getId());
					this.wxuserAddressService.update(wxUserAddress);
				}else{
					WXUserAddress wxUserAddress = new WXUserAddress();
					wxUserAddress.setAddressDetails(wxadress);
					wxUserAddress.setName(wxname);
					wxUserAddress.setPhone(wxphone);
					wxUserAddress.setWxuserId(userId);
					wxUserAddress.setCreatedTime(new Date());
					wxUserAddress.setDefaultStatus(1);
					wxUserAddress.setStatus(1);
					this.wxuserAddressService.create(wxUserAddress);
				}
				return order;
			}
		}
			return null;
	}

	@Override
	public HashMap<String, Object> findpingdanInfoById(Integer id) {
		return this.wXUserOrderMapper.findpingdanInfoById(id);
	}

	@Override
	public List<WXUserOrder> findExpirePingDanOrder() {
		return this.wXUserOrderMapper.findExpirePingDanOrder();
	}

}
