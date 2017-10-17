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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/receipt")
public class ReceiptController {
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	private static final Logger logger = LoggerFactory
			.getLogger(ReceiptController.class);

	@Autowired
	ReceiptService receiptService;

	@Autowired
	SupplierService supplierService;

	@Autowired
	VerificationService verificationService;
	
	@Autowired
	CustomerService customerService;

	@Autowired
	UserService userService;
	
	@Autowired
	RemarkService remarkService;

	@Autowired
	LogService logService;
	
	@Autowired
	ShopItemStockService shopItemStockService;
	
	@Autowired
	ReseauService reseauService;
	
	@Autowired
	CityRegionService cityRegionService;

	@Autowired
	PaymentService paymentService;

	@RequestMapping(value = "/receiptList.htm")
	public ModelAndView showReceiptList(HttpServletRequest request) {
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if (null == personUser.getCityId()) {
			return new ModelAndView("noCity");
		}
		ModelAndView mv = new ModelAndView("receipt/receiptlist");
		String supplierName = request.getParameter("supplierName");
		String startTimeStr = request.getParameter("querydate");

		String start1TimeStr = request.getParameter("startdate");
		Date querytime = null;
		Date starttime = null;
		if (StringUtils.isNotBlank(startTimeStr)) {
			querytime = DateUtil.parseDateStr(startTimeStr, "yyyy-MM");
			querytime = DateUtil.endOfMonth(querytime);
			mv.addObject("querydate", startTimeStr);
		}
		if (StringUtils.isNotBlank(start1TimeStr)) {
			starttime = DateUtil.parseDateStr(start1TimeStr, "yyyy-MM");
			starttime = DateUtil.beginOfMonth(starttime);
			mv.addObject("startdate", start1TimeStr);
		}
		String crestdateStr = request.getParameter("crestdate");
		String creenddateStr = request.getParameter("creenddate");
		Date crestdate = null;
		Date creenddate = null;
		if (StringUtils.isNotBlank(crestdateStr)) {
			crestdate = DateUtil.parseDateStr(crestdateStr, DATE_FORMAT_YMD);
			mv.addObject("crestdate", crestdateStr);
		}
		if (StringUtils.isNotBlank(creenddateStr)) {
			creenddate = DateUtil.parseDateStr(creenddateStr + " 23:59:59", "yyyy-MM-dd hh:mm:ss");
			mv.addObject("creenddate", creenddateStr);
		}
		Page<Receipt> page = null;
		try {
			if (StringUtils.isBlank(supplierName)) {
				supplierName = null;
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			PageHelper.startPage(currentPage, 50);
			List<Receipt> list = this.receiptService.findList(starttime, querytime, crestdate,
					creenddate, supplierName , personUser.getCityId());

			PageInfo<Receipt> info = new PageInfo<Receipt>(list);

			page = new Page<Receipt>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());

			mv.addObject("page", page);



			Long totalPrice = receiptService.totalPrice(starttime, querytime, crestdate,
					creenddate, supplierName , personUser.getCityId());
			mv.addObject("totalPrice", totalPrice);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		mv.addObject("supplierName", supplierName);
		mv.addObject("page", page);
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping("/addReceipt.htm")
	public ModelAndView addreceipt(HttpServletRequest request) {
		String supplierId = request.getParameter("supplierId");
		String supplierName = request.getParameter("supplierId");
		String sumdate = request.getParameter("sumdate");
		Date queryDate = new Date();
		String queryYear = String.valueOf(DateUtil.getYear(queryDate));
		String queryMonth = String.valueOf(DateUtil.getMonth(queryDate)-1);
		ModelAndView mv = new ModelAndView("receipt/addreceipt");
		if(supplierId!=null){
			mv.addObject("supplierId", supplierId);
		}
		if(supplierId!=null){
			mv.addObject("supplierName", supplierName);
		}
		if(sumdate==null){
			mv.addObject("sumdate", queryYear + "-" + (queryMonth.length()==1 ? "0"+ queryMonth : queryMonth));
		}else{
			mv.addObject("sumdate", sumdate);
		}

		TestController.getMenuPoint(mv, request);
		return mv;
	}


	@RequestMapping(value = "deleteReceipt.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteReceipt(String id,HttpServletRequest request) {
		String result = "操作成功";
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Receipt dto = receiptService.findById(id);
			if(dto!=null){
				Supplier supplier = this.supplierService.findById(dto.getSupplierId());
				if(null==supplier || !personUser.getCityId().equals(supplier.getCityId())){
					return "你设置的默认城市与操作城市不符";
				}
				Receipt receipt = new Receipt();
				Receipt receipt1 = this.receiptService.findById(id);
				receipt.setId(Long.valueOf(id));
				receipt.setStatus(0);
				receiptService.update(receipt);
				Payment payment = this.paymentService.findBySupplierIdAndSumdate(supplier.getId(), receipt1.getSumdate());
				if (payment != null) {
					payment.setReceiptPrice(payment.getReceiptPrice() - receipt1.getReceiptPrice());
					this.paymentService.update(payment);
				}
				this.saveLog(request.getSession(),dto, "删除发票信息，名称："+dto.getId(),personUser.getCityId());
			}
		} catch (Exception e) {
			logger.error("删除开票信息错误", e);
			return "删除失败,原因："+e.getMessage();
		}
		return result;
	}

	@RequestMapping(value = "/addReceipt.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addReceipt(HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Receipt receipt = new Receipt();
			receipt.setCreatedTime(new Date());
			receipt.setUserId(personUser.getId());
			receipt.setUserName(personUser.getUserName());
			String supplierId = request.getParameter("supplierId");
			Supplier supplier = this.supplierService.findById(Integer.valueOf(supplierId));

			if(null==supplier || !personUser.getCityId().equals(supplier.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			String receiptPrice = request.getParameter("receiptPrice");
			String sumdateStr = request.getParameter("sumdate");
			String receiptNo = request.getParameter("receiptNo");
			String remark = request.getParameter("remark");
			if (StringUtils.isBlank(supplierId)) {
				return "用户ID不能为空!";
			}
			receipt.setSupplierId(Integer.valueOf(supplierId));
			receipt.setSupplier(supplier.getSupplierName());
			receipt.setCityId(personUser.getCityId());
			receipt.setReceiptNo(receiptNo);
			if (StringUtils.isNotBlank(receiptPrice)) {
				receipt.setReceiptPrice(NumberTool.str2Double2Fen(receiptPrice));
			}
			if (StringUtils.isNotBlank(remark)) {
				receipt.setRemake(remark);
			}
			Date sumdate = null;
			if (StringUtils.isNotBlank(sumdateStr)) {
				sumdate = DateUtil.parseDateStr(sumdateStr, "yyyy-MM");
			}
			receipt.setSumdate(sumdate);
			receiptService.create(receipt);
			Payment payment = this.paymentService.findBySupplierIdAndSumdate(Integer.valueOf(supplierId), sumdate);
			if (payment != null) {
				payment.setReceiptPrice(payment.getReceiptPrice() + receipt.getReceiptPrice());
				this.paymentService.update(payment);
			}
			this.saveLog(request.getSession(),receipt, "添加发票信息",personUser.getCityId());
			return "添加成功";

		} catch (Throwable e) {
			logger.error("添加发票信息", e);
			return e.getMessage();
		}
	}

	private void saveLog(HttpSession session,Receipt dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.RECEIPT.getName());
	       sysLog.setDataId(dto.getId().toString());

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<Preferential>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
