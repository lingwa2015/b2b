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
import org.apache.commons.lang3.StringUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/paymentApply")
public class PaymentApplyController {

	private static final Logger logger = LoggerFactory.getLogger(PaymentApplyController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	@Autowired
	SupplierService supplierService;
	
	@Autowired
	CustomerService customerService;

	@Autowired
	UserService userService;

	@Autowired
	LogService logService;

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	PaymentApplyService paymentApplyService;

	@Autowired
	PaymentService paymentService;

	@Autowired
	PaymentAccountLockService paymentAccountLockService;

	@RequestMapping("paymentApplyList.htm")
	public ModelAndView paymentApplyList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("paymentApply/paymentApplyList");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}

		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		String createStartTimeStr = request.getParameter("createStartTime");
		String createEndTimeStr = request.getParameter("createEndTime");
		String paymentTimeStr = request.getParameter("paymentTime");

		Date startTime = null;
		Date endTime = null;
		Date createStartTime = null;
		Date createEndTime = null;
		Date paymentTime = null;

		if (StringUtils.isNotBlank(startTimeStr)) {
			startTime = DateUtil.parseDateStr(startTimeStr, "yyyy-MM");
			mv.addObject("startTime", startTimeStr);
		}
		if (StringUtils.isNotBlank(endTimeStr)) {
			endTime = DateUtil.parseDateStr(endTimeStr, "yyyy-MM");
			mv.addObject("endTime", endTimeStr);
		}
		if (StringUtils.isNotBlank(createStartTimeStr)) {
			createStartTime = DateUtil.parseDateStr(createStartTimeStr, DATE_FORMAT_YMD);
			mv.addObject("createStartTime", createStartTimeStr);
		}
		if (StringUtils.isNotBlank(createEndTimeStr)) {
			createEndTime = DateUtil.parseDateStr(createEndTimeStr, DATE_FORMAT_YMD);
			mv.addObject("createEndTime", createEndTimeStr);
		}
		if (StringUtils.isNotBlank(paymentTimeStr)) {
			paymentTime = DateUtil.parseDateStr(paymentTimeStr, DATE_FORMAT_YMD);
			mv.addObject("paymentTime", paymentTimeStr);
		}

		String status = request.getParameter("status");
		mv.addObject("status", status);
		String supplierName = request.getParameter("supplierName");
		mv.addObject("supplierName", supplierName);

		Long paymentPrice = this.paymentApplyService.findPriceByStatus(startTime, endTime, createStartTime,
				createEndTime, paymentTime, "0", supplierName , user.getCityId());
		Long paymentedPrice = this.paymentApplyService.findPriceByStatus(startTime, endTime, createStartTime,
				createEndTime, paymentTime, "1", supplierName , user.getCityId());
		mv.addObject("paymentPrice", paymentPrice);
		mv.addObject("paymentedPrice", paymentedPrice);

		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		PageHelper.startPage(currentPage, 50);
		List<PaymentApply> list = this.paymentApplyService.findList(startTime, endTime, createStartTime,
				createEndTime, paymentTime, status, supplierName , user.getCityId());

		PageInfo<PaymentApply> info = new PageInfo<PaymentApply>(list);

		Page<PaymentApply> page = new Page<PaymentApply>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());

		mv.addObject("page", page);
		TestController.getMenuPoint(mv, request);

		return mv;
	}

	@RequestMapping(value = "/paymentApplyAdd.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest req) {
		try {
			PersonUser pUser = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String supplierId = req.getParameter("supplierId");
			if (StringUtils.isEmpty(supplierId)) {
				return "供应商不能为空!";
			}

			Date paymentTimeDate = null;
			String paymentTime = req.getParameter("paymentTime");
			if (StringUtils.isNotBlank(paymentTime)) {
				paymentTimeDate = DateUtil.parseDateStr(paymentTime, DATE_FORMAT_YMD);

				String[] paymentTimeS = paymentTime.split("-");
				PaymentAccountLock aLock=new PaymentAccountLock();
				aLock.setYears(paymentTimeS[0]);
				aLock.setMonths(paymentTimeS[1]);
				aLock.setCityId(pUser.getCityId());
				int isHad = this.paymentAccountLockService.findLockByCityId(aLock);
				if (isHad > 0) {
					return "已锁帐，无法操作";
				}
			}


			PaymentApply paymentApply = new PaymentApply();
			paymentApply.setSupplierId(Integer.valueOf(supplierId));
			Supplier supplier = this.supplierService.findById(Integer.valueOf(supplierId));
			paymentApply.setSupplier(supplier.getSupplierName());
			paymentApply.setPaymentTime(paymentTimeDate);
			String[] queryDate = req.getParameter("queryDate").split("-");
			paymentApply.setYear(Integer.valueOf(queryDate[0]));
			paymentApply.setMonth(Integer.valueOf(queryDate[1]));
			String queryDateStr = req.getParameter("queryDate");
			String paymentPriceStr = req.getParameter("paymentPrice");
			if (StringUtils.isNotBlank(paymentPriceStr)) {
				paymentApply.setPaymentPrice(NumberTool.str2Double2Fen(paymentPriceStr));
			}
			Integer applicantId = Integer.valueOf(req.getParameter("applicantId"));
			paymentApply.setCityId(pUser.getCityId());
			paymentApply.setApplicantId(applicantId);
			paymentApply.setApplicant(this.userService.findById(applicantId.intValue()).getUserName());
			paymentApply.setAccountName(supplier.getAccountName());
			paymentApply.setAccount(supplier.getAccount());
			paymentApply.setBankName(supplier.getBankName());
			paymentApply.setRemake(req.getParameter("remake"));
			paymentApply.setUserId(pUser.getId());
			paymentApply.setUserName(pUser.getUserName());

			paymentApplyService.creat(paymentApply);
			Date sumdate = null;
			if (StringUtils.isNotBlank(queryDateStr)) {
				sumdate = DateUtil.parseDateStr(queryDateStr, "yyyy-MM");
				Payment payment = this.paymentService.findBySupplierIdAndSumdate(Integer.valueOf(supplierId), sumdate);
				payment.setPaymentingPrice(payment.getPaymentingPrice() + (paymentApply.getPaymentPrice() == null ? 0 : paymentApply.getPaymentPrice()));
				payment.setPaymentPrice(payment.getPaymentPrice() - (paymentApply.getPaymentPrice() == null ? 0 : paymentApply.getPaymentPrice()));
				this.paymentService.update(payment);

			}

			this.saveLog(req.getSession(), paymentApply, "付款申请单，id:"+ paymentApply.getId(), pUser.getCityId());
			return "添加成功";

		} catch (Throwable e) {
			logger.error("添加付款申请单", e);
			return e.getMessage();
		}
	}


	@RequestMapping("paymentApplyAdd.htm")
	public ModelAndView paymentAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("paymentApply/paymentApplyAdd");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		mv.addObject("paymentTime", new Date());

		List<PersonUser> applicantList = this.userService.findByPost("采购", user.getCityId());

		mv.addObject("applicantList", applicantList);
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping(value = "/changeStatus.do")
	@ResponseBody
	public String changeStatus(HttpServletRequest req) {
		try {
			PersonUser user = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String id = req.getParameter("id");
			if (StringUtils.isEmpty(id)) {
				return "请选择要删除的付款请求单!";
			}
			Integer status = Integer.valueOf(req.getParameter("status"));

			PaymentApply paymentApply = new PaymentApply();
			paymentApply.setId(Long.valueOf(id));
			paymentApply.setStatus(status);

			PaymentApply apply = this.paymentApplyService.findById(Long.valueOf(id));

			if (apply != null) {
				PaymentAccountLock aLock=new PaymentAccountLock();
				aLock.setYears(apply.getYear().toString());
				aLock.setMonths(apply.getMonth().toString());
				aLock.setCityId(user.getCityId());
				int isHad = this.paymentAccountLockService.findLockByCityId(aLock);
				if (isHad > 0) {
					return "已锁帐，无法操作";
				}

				Date sumdate = null;
				String sumdateStr = apply.getYear() + "-" + apply.getMonth();
				sumdate = DateUtil.parseDateStr(sumdateStr, "yyyy-MM");
				Payment payment = this.paymentService.findBySupplierIdAndSumdate(apply.getSupplierId(), sumdate);

				if (status.equals(1)) {
//					payment.setReceiptPrice(payment.getPaymentPrice() +  (apply.getPaymentPrice() == null ? 0 : apply.getPaymentPrice()));
//					payment.setPaymentPrice(payment.getPaymentPrice() +  (apply.getPaymentPrice() == null ? 0 : apply.getPaymentPrice()));
					payment.setPaymentingPrice(payment.getPaymentingPrice() -  (apply.getPaymentPrice() == null ? 0 : apply.getPaymentPrice()));
					payment.setNotpaymentPrice(payment.getNotpaymentPrice() -  (apply.getPaymentPrice() == null ? 0 : apply.getPaymentPrice()));

				} else {
					if (apply.getStatus() == 0) {
						payment.setPaymentingPrice(payment.getPaymentingPrice() -  (apply.getPaymentPrice() == null ? 0 : apply.getPaymentPrice()));
						payment.setPaymentPrice(payment.getPaymentPrice() +  (apply.getPaymentPrice() == null ? 0 : apply.getPaymentPrice()));
					} else {
						payment.setPaymentPrice(payment.getPaymentPrice() +  (apply.getPaymentPrice() == null ? 0 : apply.getPaymentPrice()));
						payment.setNotpaymentPrice(payment.getNotpaymentPrice() +  (apply.getPaymentPrice() == null ? 0 : apply.getPaymentPrice()));
					}
				}
				if (status.equals(1)) {
					paymentApply.setPaymentedTime(new Date());
				}
				paymentApplyService.update(paymentApply);
				this.paymentService.update(payment);
			}

			return "200";

		} catch (Throwable e) {
			logger.error("添加付款申请单", e);
			return e.getMessage();
		}
	}

	private void saveLog(HttpSession session,PaymentApply dto,String content,Integer cityId){
		try{
			SysLog sysLog = new SysLog();
			sysLog.setContent(content);
			sysLog.setCreateTime(new Date());
			sysLog.setUserId(SessionHelper.getUserId(session));
			sysLog.setCityId(cityId);
			sysLog.setDataType(LogDataTypeEnum.PAYMENTAPPLY.getName());
			sysLog.setDataId(dto.getId().toString());

			String dataContent = new Gson().toJson(dto,
					new TypeToken<Purchase>() {
					}.getType());

			sysLog.setDataContent(dataContent);

			logService.createLog(sysLog);
		}catch(Exception e){
			logger.error("保存日志失败",e);
		}
	}

	/**
	 * 根据简称返回简称
	 * */
	@RequestMapping(value = "/autoGetPaymenPrice.do")
	@ResponseBody
	public  HashMap<String, Object> autoGetPaymenPrice(HttpServletRequest request) {
		PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == curUser.getCityId()){
			return new HashMap<String, Object>();
		};
		Integer supplierId = Integer.valueOf(request.getParameter("supplierId"));
		String queryDate = request.getParameter("queryDate");
		Date dateTime = null;
		if(StringUtils.isNotEmpty(queryDate) && !queryDate.equals("0")){
			Date date = DateUtil.parseDateStr(queryDate, "yyyy-MM");
			dateTime = DateUtil.getFirstDayOfMonth(date);
		}
		return purchaseService.autoGetPaymenPrice(supplierId, dateTime);
	}

	@RequestMapping("paymentApplyPrint.htm")
	public ModelAndView paymentApplyPrint(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("paymentApply/paymentApplyPrint");
		try {
			String id = request.getParameter("id");

			PaymentApply paymentApply = this.paymentApplyService.findById(Long.valueOf(id));
			String moneyToCN = TestController.number2CNMontrayUnit(new BigDecimal(paymentApply.getPaymentPrice()/100));

			mv.addObject("dto", paymentApply);
			mv.addObject("moneyToCN", moneyToCN);


		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

		return mv;
	}


}
