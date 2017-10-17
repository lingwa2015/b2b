package com.b2b.web.app.controller;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.BaseTranDetail;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.Refund;
import com.b2b.common.domain.RefundItem;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.util.OrderNumberGenerator;
import com.b2b.dto.OrderMonthDto;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.page.Page;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemService;
import com.b2b.service.OrderService;
import com.b2b.service.RefundService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * @author Ming.Zi
 * @Apr 4, 2015
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/appOrder")
public class AppOrderController {

	Logger logger = LoggerFactory.getLogger(AppOrderController.class);

	@Autowired
	ItemService itemService;

	@Autowired
	OrderService orderService;

	@Autowired
	RefundService refundService;

	@Autowired
	ItemCategoryService itemCategoryService;


	@RequestMapping(value = "/appRefundDetail.htm", method = RequestMethod.GET)
	public ModelAndView appRefundDetail(@RequestParam("id") int id,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("app/refund/detail");
		try {
					CustomerUser personUser = (CustomerUser) request.getSession().getAttribute(Constant.USER_APP_KEY);
                    Pair<Refund, List<RefundItem>> refundPair=refundService.findById(id);
                    mv.addObject("user", personUser);
                    mv.addObject("refundItemList", refundPair.getRight());
                    mv.addObject("refund", refundPair.getLeft());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}
		return mv;
	}

	@RequestMapping(value = "/appOrderDetail.htm", method = RequestMethod.GET)
	public ModelAndView appOrderDetail(@RequestParam("orderNo") String orderNo,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("app/order/detail");
		try {
					CustomerUser personUser = (CustomerUser) request.getSession().getAttribute(Constant.USER_APP_KEY);
                    Pair<Order, List<OrderItem>> orderPair=orderService.findByOrderNo(orderNo);
                    mv.addObject("user", personUser);
                    mv.addObject("orderItemList", orderPair.getRight());
                    mv.addObject("order", orderPair.getLeft());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Pair<Order, List<OrderItem>>> page = new Page<Pair<Order, List<OrderItem>>>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<Pair<Order, List<OrderItem>>>());
			mv.addObject("page", page);
		}
		return mv;
	}

	@RequestMapping(value = "/appPriceList.htm", method = RequestMethod.GET)
	public ModelAndView appPriceList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("app/order/priceList");
		try {

			Order order = new Order();
			CustomerUser personUser = (CustomerUser) request.getSession().getAttribute(Constant.USER_APP_KEY);
			order.setUserId(personUser.getId());

			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM");
            List<Pair<OrderMonthDto,List<BaseTranDetail>>> pairList= new ArrayList<Pair<OrderMonthDto, List<BaseTranDetail>>>();
            List<Order> orderList=orderService.fundOrderByUser(order);
            List<Refund> refundList=refundService.find(personUser.getId(), null, null);

            String thisMonth="";

            if(CollectionUtils.isNotEmpty(orderList)){
            	Map<String,List<BaseTranDetail>> tranMaps = Maps.newLinkedHashMap();

            	Map<String,Long> feeMaps = Maps.newLinkedHashMap();

                 for (Order orderObject : orderList) {
                	 thisMonth=df.format(orderObject.getExecutedTime());
                	 if(tranMaps.containsKey(thisMonth)){
                		 List<BaseTranDetail> list = tranMaps.get(thisMonth);
                		 list.add(orderObject);

                		 Long fee = feeMaps.get(thisMonth);
                		 fee = fee + orderObject.getTotalFee();

                		 tranMaps.put(thisMonth, list);
                		 feeMaps.put(thisMonth, fee);
                	 }else{
                		 List<BaseTranDetail> list = Lists.newArrayList();
                		 list.add(orderObject);

                		 Long fee = 0l;
                		 fee = fee + orderObject.getTotalFee();

                		 tranMaps.put(thisMonth, list);
                		 feeMaps.put(thisMonth, fee);
                	 }
                 }

                 for (Refund refundObject : refundList) {
                	 refundObject.setTotalFee(-refundObject.getTotalFee());
                	 thisMonth=df.format(refundObject.getExecutedTime());
                	 if(tranMaps.containsKey(thisMonth)){
                		 List<BaseTranDetail> list = tranMaps.get(thisMonth);
                		 list.add(refundObject);

                		 Long fee = feeMaps.get(thisMonth);
                		 fee = fee + refundObject.getTotalFee();

                		 tranMaps.put(thisMonth, list);
                		 feeMaps.put(thisMonth, fee);
                	 }else{
                		 List<BaseTranDetail> list = Lists.newArrayList();
                		 list.add(refundObject);

                		 Long fee = 0l;
                		 fee = fee + refundObject.getTotalFee();

                		 tranMaps.put(thisMonth, list);
                		 feeMaps.put(thisMonth, fee);
                	 }
                 }


                 for(String month : tranMaps.keySet()){
                	 Long fee = feeMaps.get(month);
                	 List<BaseTranDetail> list = tranMaps.get(month);

                	 Collections.sort(list);
                	 OrderMonthDto mdto = new OrderMonthDto(month,fee);

                	 pairList.add(new MutablePair<OrderMonthDto, List<BaseTranDetail>>(mdto, list));
                 }
            }

            mv.addObject("user",personUser);
			mv.addObject("orderList", pairList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<List<Pair<OrderMonthDto,List<BaseTranDetail>>>> page = new Page<List<Pair<OrderMonthDto,List<BaseTranDetail>>>>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<List<Pair<OrderMonthDto,List<BaseTranDetail>>>>());
			mv.addObject("page", page);
		}
		return mv;
	}

	@RequestMapping(value = "/appOrderList.htm", method = RequestMethod.GET)
	public ModelAndView appOrderList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("app/order/list");
		try {

			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));

			Date startTime = null;
			Date endTime = null;

			Order order = new Order();
			CustomerUser personUser = (CustomerUser) request.getSession().getAttribute(Constant.USER_APP_KEY);
			order.setUserId(personUser.getId());
			//
//			PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_APP_KEY);
			int businessId=personUser.getBusinessId();

			Page<Pair<Order, List<OrderItem>>> orderPage = orderService
					.findOrder(order, startTime, endTime, currentPage, 5,businessId);

			logger.warn("orderSize:"+orderPage.getResult().size());

			mv.addObject("page", orderPage);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Pair<Order, List<OrderItem>>> page = new Page<Pair<Order, List<OrderItem>>>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<Pair<Order, List<OrderItem>>>());
			mv.addObject("page", page);
		}
		return mv;
	}

	@RequestMapping(value = "/appOrderAjaxList.do", method = RequestMethod.POST,produces="text/json;charset=UTF-8")
	public 	@ResponseBody String appOrderAjaxList(HttpServletRequest request) {

		List<Order> list = Lists.newArrayList();

		try {

			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));

			Date startTime = null;
			Date endTime = null;

			Order order = new Order();
			CustomerUser personUser = (CustomerUser) request.getSession().getAttribute(Constant.USER_APP_KEY);
			order.setUserId(personUser.getId());

			Page<Pair<Order, List<OrderItem>>> orderPage = orderService
					.findOrder(order, startTime, endTime, currentPage,
							5,0);

			List<Pair<Order, List<OrderItem>>> resultList = orderPage.getResult();
			if(CollectionUtils.isNotEmpty(resultList)){
				for(Pair<Order, List<OrderItem>> orderPair : resultList){
					Order order2 = orderPair.getLeft();
					order2.setOrderItemList(orderPair.getRight());

					list.add(order2);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

		Gson gson = new Gson();
		Type t = new TypeToken<List<Order>>() {}.getType();

		String jsonStr = gson.toJson(list, t);

		return jsonStr;
	}

	@RequestMapping(value = "/order.htm", method = RequestMethod.GET)
	public ModelAndView showOrders(HttpServletRequest req) {
		ModelAndView view = new ModelAndView("app/order/order");
		CustomerUser personUser = (CustomerUser) req.getSession().getAttribute(Constant.USER_APP_KEY);
		view.addObject("user", personUser);
		return view;
	}


	@RequestMapping(value = "/appCreateOrder.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String appCreateOrder(HttpServletRequest req) {
		try {
			String dataStr = req.getParameter("data");
			CustomerUser personUser = (CustomerUser) req.getSession().getAttribute(Constant.USER_APP_KEY);

			if (personUser == null) {
				return "error:用户不存在!";
			}

			Order order = new Order();
			order.setMemo(req.getParameter("memo"));
			order.setAddress(personUser.getAddress());
			order.setCreatedTime(new Date());
			order.setOrderStatus(OrderStatusEnum.PAY.getId());
			order.setStatus(Constant.VALID_STATUS);
			order.setUserId(personUser.getId());
			order.setBusinessId(personUser.getBusinessId());
//			order.setOrderNo(OrderNumberGenerator.buildOrderNo(order.getCreatedTime(), order.getUserId(), Constant.FROM_APP));
			order.setOrderNo(OrderNumberGenerator.buildOrderNo(order.getCreatedTime(), order.getUserId()));

			List<OrderItem> orderItemList = new ArrayList<OrderItem>();

			for (String row : dataStr.split("@")) {
				if (StringUtils.isEmpty(row)) {
					continue;
				}

				String[] tmpArray = row.split("#");
				if (tmpArray.length != 2) {
					continue;
				}
				String itemIdStr = tmpArray[0];
				String numStr = tmpArray[1];

				try {
					OrderItem oi = new OrderItem();
					oi.setOrderNo(order.getOrderNo());
					oi.setItemId(Integer.parseInt(itemIdStr));
					oi.setNum(Integer.parseInt(numStr));
					orderItemList.add(oi);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}

			Integer orderTotalNum = 0;
			Long orderTotalFee = 0l;

			order.setOrderItemList(orderItemList);

			for (OrderItem oi : orderItemList) {
				if(oi.getNum()==null||oi.getNum()==0){
					return "error:这个商品id :" + oi.getItemId() + ",数量为0";
				}
				Item item = this.itemService.findById(oi.getItemId());
				if (item == null) {
					return "error:这个商品id :" + oi.getItemId() + ",无法找到商品数据";
				}

				oi.setFee(item.getPrice() * oi.getNum());
				oi.setItemName(item.getItemName());
				oi.setItemPrice(item.getPrice());
				oi.setItemCostPrice(item.getCostPrice());
				oi.setItemSize(item.getSize());

				orderTotalNum += oi.getNum();
				orderTotalFee += oi.getFee();

			}

			if(orderTotalNum<=0){
				return "error:订单中商品数量为0";
			}

			order.setTotalFee(orderTotalFee);
			order.setTotalNum(orderTotalNum);
			orderService.createOrder(order);
		} catch (Exception e) {
			logger.error("",e);
			return "error:" + e.getMessage();
		}
		return "ok";
	}

	@RequestMapping(value = "/cancelOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelOrder(HttpServletRequest request) {
		try {

			String orderNo = request.getParameter("orderNo");
			Order order = new Order();
			order.setOrderNo(orderNo);
			order.setStatus(Constant.DELETE_STATUS);
			this.orderService.updateOrder(order);
		} catch (Throwable e) {
			logger.error("编辑订单状态", e);
			return "failure";
		}
		return "success";
	}



	@RequestMapping(value = "/appCreateOrder.htm", method = RequestMethod.POST)
	public ModelAndView appCreateOrderhtm(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("app/order/order");

		try {

			Map<Integer, Integer> itemId2Num = new HashMap<Integer, Integer>();
			Gson gson = new Gson();
			Type t = new TypeToken<Map<Integer, Integer>>() {}.getType();

			String orderNos = req.getParameter("orderNos");
			String[] rowArray = orderNos.split("@");

			for(String row : rowArray){
				String[] rowTmp = row.split("#");
				if(rowTmp.length != 2){
					continue;
				}
				try {
					Integer itemId = Integer.parseInt(rowTmp[0]);
					Integer num = Integer.parseInt(rowTmp[1]);
					itemId2Num.put(itemId, num);
				} catch (Exception e) {
					logger.error("",e);
					continue;
				}

			}
			mv.addObject("shoppingCart", gson.toJson(itemId2Num,t)+";");
			//获取商品数据

			List<Item> itemList = itemService.findAll();
			Map<Integer, List<Item>> cateGoryId2Items = new HashMap<Integer, List<Item>>();

			for(Item item : itemList){
				if(cateGoryId2Items.get(item.getCategoryId()) == null){
					cateGoryId2Items.put(item.getCategoryId(), new ArrayList<Item>());
				}

				cateGoryId2Items.get(item.getCategoryId()).add(item);
			}

			Gson gsonNew = new Gson();
			Type tNew = new TypeToken<Map<Integer, List<Item>>>() {}.getType();
			mv.addObject("itemList", gsonNew.toJson(cateGoryId2Items,tNew)+";");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Pair<Order, List<OrderItem>>> page = new Page<Pair<Order, List<OrderItem>>>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<Pair<Order, List<OrderItem>>>());
			mv.addObject("page", page);
		}
		return mv;
	}
}
