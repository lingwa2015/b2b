package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/refund")
public class RefundController {

    private static final Logger logger = LoggerFactory.getLogger(RefundController.class);
    private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

    @Autowired
    RefundService refundService;

    @Autowired
    ItemService itemService;

	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	CustomerService customerService;

	@Autowired
	LogService logService;

	@Autowired
	AccountLockService accountLockService;
	
	@Autowired
	ReseauService reseauService;

    @RequestMapping("refundList.htm")
    public ModelAndView getRefundListPage(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView("refund/list");
        TestController.getMenuPoint(mv, request);
        try {
        	PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return new ModelAndView("noCity");
			}
            int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
                    request.getParameter("currentPage"), "1"));

            String startTimeStr = request.getParameter("startTime");
            String endTimeStr = request.getParameter("endTime");


            Date startTime = null;
            Date endTime = null;

            
            String param = request.getParameter("param");
            mv.addObject("param", param);


            if (StringUtils.isNotBlank(startTimeStr)) {
                startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
                mv.addObject("startTime", startTimeStr);
            }

            if (StringUtils.isNotBlank(endTimeStr)) {
                endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
                mv.addObject("endTime", endTimeStr);
            }
            
            int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
    		mv.addObject("reseauId", reseauId);

    		String userName = request.getParameter("userName");
    		mv.addObject("userName", userName);
    		String itemName = request.getParameter("itemName");
			mv.addObject("itemName", itemName);
            
            PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
            
            List<Refund> refundItemList = this.refundService.findByCondition(userName, startTime, endTime,param,reseauId,user.getCityId(),itemName);
            PageInfo<Refund> info = new PageInfo<Refund>(refundItemList);
            Page<Refund> RefundPage =  new Page<Refund>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
            Long money = this.refundService.findTotal(userName, startTime, endTime,param,reseauId,user.getCityId(),itemName);
            Integer num = this.refundService.findBeConfirmRefundByCityId(user.getCityId());
            mv.addObject("num", num);
            List<Reseau> list = this.reseauService.findAllByCityId(user.getCityId());
    		mv.addObject("reseaus", list);
            mv.addObject("money", money);
            mv.addObject("page", RefundPage);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Page<Refund> page = new Page<Refund>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<Refund>());
            mv.addObject("page", page);
        }
        return mv;
    }

    @RequestMapping(value = "addRefund.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addSave(HttpServletRequest request) {
        try {
        	PersonUser user=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
        	if(null == user.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
            Refund refund = new Refund();
            refund.setCityId(user.getCityId());
            long totalFeeAll=NumberTool.str2Double2Fen(request.getParameter("totalFeeAll"));
            totalFeeAll = 0;
            long totalcost = 0;
            long totalnocost = 0;
            int rowCount = Integer.parseInt(request.getParameter("rowCount"));
            List<RefundItem> refundItemList = new ArrayList<RefundItem>();
			String discount = request.getParameter("discount");
			if(StringUtils.isEmpty(discount))
			{
				return "折扣不能不空!";
			}
			BigDecimal dis=new BigDecimal(discount);
			refund.setDiscount(dis);
            for (int i = 0; i < rowCount; i++) {
                String itemIds = request.getParameter("skuId" + i);
                if (StringUtils.isEmpty(itemIds)) {
                    continue;
                }
                int itemId = Integer.parseInt(itemIds);
                Item item = itemService.findById(itemId);

                String size = request
						.getParameter("size" + i);
                
                long totalFee=0;
                long costPrice=0;
                long notaxInclusiveCostPrice=0;
                if (size.equals("SIZE")) { //普通规格
					costPrice = item.getCostPrice();
					notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
				} else if (size.equals("SALE_SIZE")) { // 零售
					costPrice = item.getSaleCostPrice();
					notaxInclusiveCostPrice = item.getNotaxInclusiveSaleCostPrice();
				} else if (size.equals("BUY_SIZE")) { //批发
					costPrice = item.getBuyPrice();
					notaxInclusiveCostPrice = item.getNotaxInclusiveBuyPrice();
				} else {
					return "商品 :" + item.getItemName() + ",无法找到规格数据";
				}
                RefundItem refundItem = new RefundItem();
                // sku_id
                refundItem.setItemId(Integer.valueOf(itemId));
                // sku_name
                refundItem.setItemName(item.getItemName());

                refundItem.setSize(size);

                int refundNum = Integer.parseInt(request
                        .getParameter("refundNum" + i));

                //int num = item.calNum(refundNum, size);
                refundItem.setNum(refundNum);
                Long price=NumberTool.str2Double2Fen(request.getParameter("price" + i));
//                BigDecimal value=new BigDecimal(100);
//				BigDecimal pr=(new BigDecimal(price).divide(value)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(value);
//				price=pr.longValue();
                refundItem.setItemPrice(price);
                Integer reason = Integer.parseInt(request.getParameter("reason" + i));
                refundItem.setReason(reason);
                String sizeValue = item.calSize(size);
                refundItem.setSizeValue(sizeValue);

                totalFee = refundItem.getItemPrice()*refundItem.getNum();
                costPrice = costPrice*refundItem.getNum();
                notaxInclusiveCostPrice = notaxInclusiveCostPrice*refundItem.getNum();
                totalFeeAll = totalFeeAll+ totalFee;
                totalcost += costPrice;
                totalnocost +=notaxInclusiveCostPrice;
                refundItem.setTotalFee(totalFee);
                refundItem.setCostFee(notaxInclusiveCostPrice);
                refundItem.setItemCostFee(costPrice);
                refundItemList.add(refundItem);
                
            }

//            PersonUser user=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
            int userId = Integer.valueOf(StringUtils.defaultIfBlank(
                    request.getParameter("userId"), "-1"));
            CustomerUser personUser= customerService.findById(userId);
            //BigDecimal value=new BigDecimal(100);
			//BigDecimal pr=(new BigDecimal(totalFeeAll).divide(value)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(value);
			//totalFeeAll=pr.longValue();
            refund.setUserId(personUser.getId());
            refund.setLastModUser(user.getId());
            refund.setTotalFee(totalFeeAll);
            refund.setCostFee(totalcost);
            refund.setNotaxinclusivecostfee(totalnocost);
            refund.setReason(1);
			String executedTimeStr = request.getParameter("executedTime");
			if (StringUtils.isEmpty(executedTimeStr)) {
				refund.setExecutedTime(new Date());
			} else {
				refund.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD));
			}
			AccountLock accountLock=new AccountLock();
			Date exce=refund.getExecutedTime();
            Calendar calendar = Calendar.getInstance();
			calendar.setTime(exce);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1;
            accountLock.setYears(year+"");
            accountLock.setMonths(month+"");
            accountLock.setCityId(user.getCityId());
            int lock=accountLockService.findLockByCityId(accountLock);
			if(lock==1)
			{
				return "执行时间不能设置到已锁帐月份!";
			}
			String remark = request.getParameter("remark");
			if(!StringUtils.isEmpty(remark)){
				refund.setRemark(remark);
			}
            refund.setRefundItemList(refundItemList);
            
            if(null!=personUser.getIswxvip()&&1==personUser.getIswxvip()){
            	refundService.createRefundAndMoney(refund,user.getId());
            	this.saveLog(request.getSession(),refund, "微信会员添加订单并扣款，orderNo:"+refund.getId()+"money:"+refund.getTotalFee(),user.getCityId());
            }else if(null!=personUser.getIswxvip()&&2==personUser.getIswxvip()){
				return "该用户未激活";
			}else{
				refundService.createRefund(refund);
				this.saveLog(request.getSession(),refund, "添加退货单，id:"+refund.getId(),user.getCityId());
			}
            //Thread.sleep(1000*60);
            return "添加成功";
        } catch (Exception e) {
            logger.error("添加退货单失败", e);
            return e.getMessage();
        }
    }

    @RequestMapping(value = "refundAdd.htm", method = RequestMethod.GET)
    public ModelAndView add(HttpServletRequest request) throws Exception {
        try {
        	PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return new ModelAndView("noCity");
			}

        	Refund dto = new Refund();
        	dto.setExecutedTime(new Date());


            ModelAndView mv = new ModelAndView("refund/add");

            mv.addObject("dto", dto);
            this.fillCommonData(mv, user.getCityId());

            TestController.getMenuPoint(mv, request);
            return mv;
        } catch (Exception e) {
            logger.error("show refund detail.", e);
            throw e;
        }
    }

	private void fillCommonData(ModelAndView view,int cityId){
		List<ItemCategory> catList =null;
		catList= itemCategoryService.findAllOneCatsByCityId(cityId);
		view.addObject("catList", catList);

		int cateId1=0;
		if(CollectionUtils.isNotEmpty(catList)){
			cateId1  = catList.get(0).getId();
		}

		view.addObject("cateId1", cateId1);

		 List<Item> itemList = itemService.findAllWithStockByCityId(cityId);
		 view.addObject("itemList", itemList);

		Item item1 = null;
		if(CollectionUtils.isNotEmpty(itemList)){
			for(Item item : itemList){
				if(item.getCategoryId().intValue()==cateId1){
					item1 = item;
					break;
				}
			}
		}

		view.addObject("item1", item1);
	}

    @RequestMapping(value = "refundDetail.htm", method = RequestMethod.GET)
    public ModelAndView detail(HttpServletRequest request) throws Exception {
        try {

            int id = Integer.valueOf(StringUtils.defaultIfBlank(
                    request.getParameter("id"), "0"));
            if (id == 0) {
                return new ModelAndView("errorPage");
            }
            Pair<Refund, List<RefundItem>> pair = refundService
                    .findById(id);

            ModelAndView mv = new ModelAndView("refund/detail");
            TestController.getMenuPoint(mv, request);
            mv.addObject("pair", pair);
            return mv;
        } catch (Exception e) {
            logger.error("show refund detail.", e);
            throw e;
        }
    }

    @RequestMapping(value = "deleteRefund.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delete(HttpServletRequest request) throws Exception {
        try {
        	PersonUser user=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
        	if(null == user.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
            int id = Integer.valueOf(StringUtils.defaultIfBlank(
                    request.getParameter("id"), "0"));
            if (id == 0) {
                return "删除失败";
            }

            Pair<Refund, List<RefundItem>> pair = refundService.findById(id);

            if(pair!=null){
            	Refund dto = pair.getKey();
            	if(!dto.getCityId().equals(user.getCityId())){
            		return "你设置的城市与实际不符";
            	}
            	CustomerUser personUser = customerService.findById(dto.getUserId());
            	if(null!=personUser.getIswxvip()&&1==personUser.getIswxvip()){
            		refundService.deleteAndBackMoney(id,user.getId());
            		this.saveLog(request.getSession(),dto, "删除退货单，id:"+dto.getId()+"扣除退款金额:"+dto.getTotalFee(),user.getCityId());
            	}else if(null!=personUser.getIswxvip()&&2==personUser.getIswxvip()){
    				return "该用户未激活,请联系充值后下单!";
    			}else{
    				refundService.delete(id);
    				this.saveLog(request.getSession(),dto, "删除退货单，id:"+dto.getId(),user.getCityId());
    			}
            }
        } catch (Exception e) {
            logger.error("删除退货单失败", e);
            return e.getMessage();
        }

        return "删除成功";
    }
    
    @RequestMapping(value = "deleteRefundNotStock.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteNotStock(HttpServletRequest request) throws Exception {
        try {
        	PersonUser user=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
        	if(null == user.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
            int id = Integer.valueOf(StringUtils.defaultIfBlank(
                    request.getParameter("id"), "0"));
            if (id == 0) {
                return "删除失败";
            }

            Pair<Refund, List<RefundItem>> pair = refundService.findById(id);

            if(pair!=null){
            	Refund dto = pair.getKey();
            	if(!dto.getCityId().equals(user.getCityId())){
            		return "你设置的城市与实际不符";
            	}
            	refundService.deleteNotStock(id);
    		    this.saveLog(request.getSession(),dto, "删除退货单，id:"+dto.getId(),user.getCityId());
            }
        } catch (Exception e) {
            logger.error("删除退货单失败", e);
            return e.getMessage();
        }

        return "删除成功";
    }
    
    @RequestMapping(value = "confirm.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String confirm(HttpServletRequest request){
    	try {
			int id = Integer.valueOf(StringUtils.defaultIfBlank(
			        request.getParameter("id"), "0"));
			if (id == 0) {
			    return "确认失败";
			}
			this.refundService.confirm(id);
		} catch (NumberFormatException e) {
			logger.error("确认失败", e);
            return e.getMessage();
		}

        return "确认成功";
    }

    @RequestMapping(value = "updateTotalFee.do",  produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateTotalFee(HttpServletRequest request) throws Exception {
        try {


            refundService.updateRefundTotalFee();
        } catch (Exception e) {
            logger.error("更新退货单费用失败.", e);
            return e.getMessage();
        }

        return "更新成功";
    }
    
    @RequestMapping(value = "/print.do", method = RequestMethod.POST)
	public ModelAndView printrefunds(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("refund/print");

		String orderNos = request.getParameter("refundId");
		if (StringUtils.isBlank(orderNos)) {
			throw new NullPointerException("退货单参数异常");
		}
		String[] orders = orderNos.split(",");
		//key: userId 按用户合并订单数据
		List<Pair<Refund, List<RefundItem>>> list = new ArrayList<Pair<Refund, List<RefundItem>>>();
		for (String orderNo : orders) {
			Pair<Refund, List<RefundItem>> pair = this.refundService.findById(Integer.valueOf(orderNo));
			if (pair == null || pair.getLeft() == null) {
				logger.error("订单数据异常, orderNo:" + orderNo);
				continue;
			}
			list.add(pair);
		}

		mv.addObject("list", list);


		return mv;
	}

    private void saveLog(HttpSession session,Refund dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(cityId);
	       sysLog.setDataType(LogDataTypeEnum.REFUND.getName());
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Refund>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
