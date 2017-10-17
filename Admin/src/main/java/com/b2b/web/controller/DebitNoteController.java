package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.common.util.OrderNumberGenerator;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.pay.OrderUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.Number;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/debitnote")
public class DebitNoteController {
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	private static final Logger logger = LoggerFactory
			.getLogger(DebitNoteController.class);

	@Autowired
	DebitNoteService debitNoteService;

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

	@RequestMapping(value = "/debitNoteList.htm")
	public ModelAndView showDebitNoteList(HttpServletRequest request) {
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView mv = new ModelAndView("debitnote/list");
		TestController.getMenuPoint(mv, request);
		String userName = request.getParameter("userName");
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
			creenddate = DateUtil.parseDateStr(creenddateStr+" 23:59:59", "yyyy-MM-dd hh:mm:ss");
			mv.addObject("creenddate", creenddateStr);
		}
		Page<DebitNoteDemo> page =null;
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
			if(StringUtils.isBlank(userName)){
				userName=null;
			}
			page = debitNoteService.debitNoteList(userName, currentPage ,Page.DEFAULT_PAGE_SIZE,starttime,querytime,crestdate,creenddate,personUser.getCityId());
			Long totalFee = debitNoteService.totaldebitNote(userName,starttime,querytime,crestdate,creenddate,personUser.getCityId());
			mv.addObject("totalFee", totalFee);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		mv.addObject("userName", userName);
		mv.addObject("page", page);
		return mv;
	}

	@RequestMapping(value = "/invoiceList.htm")
	public ModelAndView showInvoiceList(HttpServletRequest request) {
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView mv = new ModelAndView("debitnote/invoicelist");
		TestController.getMenuPoint(mv, request);
		String userName = request.getParameter("userName");
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
			creenddate = DateUtil.parseDateStr(creenddateStr+" 23:59:59", "yyyy-MM-dd hh:mm:ss");
			mv.addObject("creenddate", creenddateStr);
		}
		Page<DebitNoteDemo> page =null;
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
			if(StringUtils.isBlank(userName)){
				userName=null;
			}
			page = debitNoteService.debitInvoiceList(userName, currentPage ,Page.DEFAULT_PAGE_SIZE,starttime,querytime,crestdate,creenddate,personUser.getCityId());
			Long totalFee = debitNoteService.totalInvoice(userName,starttime,querytime,crestdate,creenddate,personUser.getCityId());
			mv.addObject("totalFee", totalFee);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		mv.addObject("userName", userName);
		mv.addObject("page", page);
		return mv;
	}

	@RequestMapping(value = "/preferentialList.htm")
	public ModelAndView showPreferentialInvoiceList(HttpServletRequest request) {
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView mv = new ModelAndView("debitnote/preferentiallist");
		TestController.getMenuPoint(mv, request);
		String userName = request.getParameter("userName");
		String type = request.getParameter("type");
		mv.addObject("type", type);
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
			creenddate = DateUtil.parseDateStr(creenddateStr+" 23:59:59", "yyyy-MM-dd hh:mm:ss");
			mv.addObject("creenddate", creenddateStr);
		}
		Page<DebitNoteDemo> page =null;
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
			if(StringUtils.isBlank(userName)){
				userName=null;
			}
			page = debitNoteService.debitPreferentialList(userName, currentPage ,Page.DEFAULT_PAGE_SIZE,type,starttime,querytime,crestdate,creenddate,personUser.getCityId());
			Long totalFee = debitNoteService.totalPreferential(userName,type,starttime,querytime,crestdate,creenddate,personUser.getCityId());
			mv.addObject("totalFee", totalFee);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		mv.addObject("userName", userName);
		mv.addObject("page", page);
		return mv;
	}

	
	@RequestMapping(value = "/debitNoteTranSum.htm")
	public ModelAndView showSumList(HttpServletRequest request,@RequestParam(value="reseauId",defaultValue="-1",required=false)Integer reseauId) {
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView mv = new ModelAndView("debitnote/sumlist");
		TestController.getMenuPoint(mv, request);
		String userName = request.getParameter("userName");
		String startTimeStr = request.getParameter("querydate");
		String start1TimeStr = request.getParameter("startdate");
		String param = request.getParameter("param");
		if(StringUtils.isEmpty(param)){
			param = "0";
		}
		mv.addObject("param", param);
		if(reseauId==-1){
			reseauId=null;
		}
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
		String region = request.getParameter("regionId");
		Integer regionId = null;
		if(!StringUtils.isEmpty(region)){
			regionId = Integer.valueOf(region);
		}
		mv.addObject("regionId", regionId);
		String type = request.getParameter("type");
		mv.addObject("type", type);
		String debflag = request.getParameter("debflag");
		mv.addObject("debflag", debflag);
		String invflag = request.getParameter("invflag");
		mv.addObject("invflag", invflag);
		Page<DebitNoteDemo> page=null;
		//Page<DebitNoteDemo> page =null;
		DebitNoteDemo total = null;
		DebitNoteDemo total2 = null;
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
			if(StringUtils.isBlank(userName)){
				userName=null;
			}
			PageHelper.startPage(currentPage, 30);
			List<DebitNoteDemo> debitNoteDemo = debitNoteService.debitNoteAmtList(userName,starttime,querytime,param,regionId,reseauId,personUser.getCityId(),type,debflag,invflag);
			PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(debitNoteDemo);
			page = new Page<DebitNoteDemo>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			total = debitNoteService.findTotal(userName,starttime,querytime,regionId,reseauId,personUser.getCityId(),type,debflag,invflag);
			total2 = debitNoteService.findTotalaaa(userName,starttime,querytime,regionId,reseauId,personUser.getCityId(),type,debflag,invflag);
			List<Reseau> lists = this.reseauService.findAllByCityId(personUser.getCityId());
			List<CityRegion> regions = this.cityRegionService.findByCityId(personUser.getCityId());
			mv.addObject("regions", regions);
			mv.addObject("reseaus", lists);
			mv.addObject("reseauId", reseauId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		mv.addObject("userName", userName);
		mv.addObject("page", page);
		mv.addObject("total", total);
		mv.addObject("total2", total2);
		return mv;
	}
	
	@RequestMapping(value = "/debitNoteTranSumDetail.htm")
	public ModelAndView showSumListDetail(HttpServletRequest request,@RequestParam("id")Integer id){
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		CustomerUser user = this.customerService.findById(id);
		if(null==user || !personUser.getCityId().equals(user.getCityId())){
			return new ModelAndView("notCity");
		}
		ModelAndView view = new ModelAndView("debitnote/sumlistdetail");
		TestController.getMenuPoint(view, request);
		HashMap<String, Object> paramMapId = Maps.newHashMap();
		paramMapId.put("userId", id);
		paramMapId.put("startTime", null);
		paramMapId.put("endTime", null);
		List<DebitNoteDemo> lists =this.debitNoteService.selectByUserId(paramMapId);
		view.addObject("lists", lists);
		view.addObject("user", user);
		return view;
	}

	@RequestMapping("/addDebitNote.htm")
	public ModelAndView addDebitNote(HttpServletRequest request,
			String returnedPurchase) {
		String companyId = request.getParameter("companyId");
		String companyName = request.getParameter("companyName");
		String years = request.getParameter("years");
		String months = request.getParameter("months");
		Date queryDate = new Date();
		String queryYear = String.valueOf(DateUtil.getYear(queryDate));
		String queryMonth = String.valueOf(DateUtil.getMonth(queryDate)-1);
		ModelAndView mv = new ModelAndView("debitnote/add");
		TestController.getMenuPoint(mv, request);
		if(companyId!=null){
			mv.addObject("companyId", companyId);
		}
		if(companyName!=null){
			mv.addObject("companyName", companyName);
		}
		if(years!=null){
			mv.addObject("year", years);
		}else{
			mv.addObject("year", queryYear);
		}
		if(months!=null){
			mv.addObject("month", months);
		}else{
			mv.addObject("month", queryMonth);
		}
		return mv;
	}

	@RequestMapping("/addInvoice.htm")
	public ModelAndView addinvoice(HttpServletRequest request,
			String returnedPurchase) {
		String companyId = request.getParameter("companyId");
		String companyName = request.getParameter("companyName");
		String years = request.getParameter("years");
		String months = request.getParameter("months");
		Date queryDate = new Date();
		String queryYear = String.valueOf(DateUtil.getYear(queryDate));
		String queryMonth = String.valueOf(DateUtil.getMonth(queryDate)-1);
		ModelAndView mv = new ModelAndView("debitnote/addinvoice");
		TestController.getMenuPoint(mv, request);
		if(companyId!=null){
			mv.addObject("companyId", companyId);
		}
		if(companyName!=null){
			mv.addObject("companyName", companyName);
		}
		if(years!=null){
			mv.addObject("year", years);
		}else{
			mv.addObject("year", queryYear);
		}
		if(months!=null){
			mv.addObject("month", months);
		}else{
			mv.addObject("month", queryMonth);
		}
		return mv;
	}
	
	@RequestMapping("/addRemark.htm")
	public ModelAndView addRemark(HttpServletRequest request,
			String returnedPurchase) {
		String companyId = request.getParameter("companyId");
		String companyName = request.getParameter("companyName");
		String years = request.getParameter("years");
		String months = request.getParameter("months");
		Date queryDate = new Date();
		String queryYear = String.valueOf(DateUtil.getYear(queryDate));
		String queryMonth = String.valueOf(DateUtil.getMonth(queryDate)-1);
		ModelAndView mv = new ModelAndView("debitnote/addremark");
		TestController.getMenuPoint(mv, request);
		if(companyId!=null){
			mv.addObject("companyId", companyId);
		}
		if(companyName!=null){
			mv.addObject("companyName", companyName);
		}
		if(years!=null){
			mv.addObject("year", years);
		}else{
			mv.addObject("year", queryYear);
		}
		if(months!=null){
			mv.addObject("month", months);
		}else{
			mv.addObject("month", queryMonth);
		}
		Remark dto = this.remarkService.findByCondition(Integer.valueOf(companyId),years,months);
		mv.addObject("remark", dto);
		return mv;
	}
	
	@RequestMapping(value="/addRemark.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addremark(HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String companyId = request.getParameter("company_id");
			if (StringUtils.isBlank(companyId)) {
				return "用户ID不能为空!";
			}
			CustomerUser customerUser = this.customerService.findById(Integer.valueOf(companyId));
			if(null==customerUser || !personUser.getCityId().equals(customerUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			String years = request.getParameter("year");
			String months = request.getParameter("month");
			String remark = request.getParameter("remark");
			Remark dto = this.remarkService.findByCondition(Integer.valueOf(companyId),years,months);
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == dto ){
				Remark remark2 = new Remark();
				remark2.setCompanyId(Integer.valueOf(companyId));
				remark2.setMonth(months);
				remark2.setYear(years);
				remark2.setCreateTime(new Date());
				remark2.setUpdateTime(remark2.getCreateTime());
				remark2.setUserId(user.getId());
				remark2.setRemark(remark);
				this.remarkService.save(remark2);
				this.saveLog(request.getSession(),remark2, "添加备注",personUser.getCityId());
				return "添加成功";
			}else{
				dto.setRemark(remark);
				dto.setUpdateTime(new Date());
				dto.setUserId(user.getId());
				this.remarkService.update(dto);
				this.saveLog(request.getSession(),dto, "更新备注",personUser.getCityId());
				return "添加成功";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
	}

	@RequestMapping("/addPreferential.htm")
	public ModelAndView addOrderPage(HttpServletRequest request,
			String returnedPurchase) {
		String companyId = request.getParameter("companyId");
		String companyName = request.getParameter("companyName");
		String years = request.getParameter("years");
		String months = request.getParameter("months");
		Date queryDate = new Date();
		String queryYear = String.valueOf(DateUtil.getYear(queryDate));
		String queryMonth = String.valueOf(DateUtil.getMonth(queryDate)-1);
		ModelAndView mv = new ModelAndView("debitnote/addpreferential");
		TestController.getMenuPoint(mv, request);
		if(companyId!=null){
			mv.addObject("companyId", companyId);
		}
		if(companyName!=null){
			mv.addObject("companyName", companyName);
		}
		if(years!=null){
			mv.addObject("year", years);
		}else{
			mv.addObject("year", queryYear);
		}
		if(months!=null){
			mv.addObject("month", months);
		}else{
			mv.addObject("month", queryMonth);
		}
		return mv;
	}

	@RequestMapping(value = "/addDebitNote.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(DebitNote debitNote ,HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			debitNote.setCreateDate(new Date());
			debitNote.setCreateUser(SessionHelper.getUserId(request.getSession()));
			String companyid = request.getParameter("customerId");
			CustomerUser customerUser = this.customerService.findById(Integer.valueOf(companyid));
			if(null==customerUser || !personUser.getCityId().equals(customerUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			String debitnoteAmount = request.getParameter("debitnote_amount");
			String years = request.getParameter("year");
			String months = request.getParameter("month");
			String remark = request.getParameter("remark");
			if (StringUtils.isBlank(companyid)) {
				return "用户ID不能为空!";
			}
			debitNote.setCompanyId(Integer.valueOf(companyid));
			if (StringUtils.isNotBlank(debitnoteAmount)) {
				debitNote.setDebitnoteAmount(Long.parseLong(debitnoteAmount));
			}
			if (StringUtils.isNotBlank(remark)) {
				debitNote.setRemark(remark);
			}
			debitNote.setYears(years);
			debitNote.setMonths(months);
			debitNote.setId(OrderNumberGenerator.buildDebitNoteNo(debitNote.getCreateDate(), debitNote.getCreateUser()));
			debitNote.setDeleteStatus(1);
			debitNoteService.create(debitNote);
			this.saveLog(request.getSession(),debitNote, "添加收款单",personUser.getCityId());
			return "添加成功";

		} catch (Throwable e) {
			logger.error("添加收款单失败", e);
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/addInvoice.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addInvoice(Invoice invoice ,HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			invoice.setCreateDate(new Date());
			invoice.setCreateUser(SessionHelper.getUserId(request.getSession()));
			String companyid = request.getParameter("customerId");
			CustomerUser customerUser = this.customerService.findById(Integer.valueOf(companyid));
			if(null==customerUser || !personUser.getCityId().equals(customerUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			String invoiceAmount = request.getParameter("invoice_amount");
			String years = request.getParameter("year");
			String months = request.getParameter("month");
			String remark = request.getParameter("remark");
			if (StringUtils.isBlank(companyid)) {
				return "用户ID不能为空!";
			}
			invoice.setCompanyId(Integer.valueOf(companyid));
			if (StringUtils.isNotBlank(invoiceAmount)) {
				invoice.setInvoiceAmount(Long.parseLong(invoiceAmount));
			}
			if (StringUtils.isNotBlank(remark)) {
				invoice.setRemark(remark);
			}
			invoice.setYears(years);
			invoice.setMonths(months);
			invoice.setId(OrderNumberGenerator.buildDebitNoteNo(invoice.getCreateDate(), invoice.getCreateUser()));
			invoice.setDeleteStatus(1);
			debitNoteService.createInvoice(invoice);
			this.saveLog(request.getSession(),invoice, "添加开票信息",personUser.getCityId());
			return "添加成功";

		} catch (Throwable e) {
			logger.error("添加开票信息", e);
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/addPreferential.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addPreferential(Preferential preferential ,HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			preferential.setCreateDate(new Date());
			preferential.setCreateUser(SessionHelper.getUserId(request.getSession()));
			String companyid = request.getParameter("customerId");
			CustomerUser customerUser = this.customerService.findById(Integer.valueOf(companyid));
			if(null==customerUser || !personUser.getCityId().equals(customerUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			String preferentialAmount = request.getParameter("preferential");
			String years = request.getParameter("year");
			String months = request.getParameter("month");
			String remark = request.getParameter("remark");
			if (StringUtils.isBlank(companyid)) {
				return "用户ID不能为空!";
			}
			preferential.setCompanyId(Integer.valueOf(companyid));
			if (StringUtils.isNotBlank(preferentialAmount)) {
				preferential.setPreferentialAmount(Long.parseLong(preferentialAmount));
			}
			if (StringUtils.isNotBlank(remark)) {
				preferential.setRemark(remark);
			}
			preferential.setYears(years);
			preferential.setMonths(months);
			preferential.setId(OrderNumberGenerator.buildDebitNoteNo(preferential.getCreateDate(), preferential.getCreateUser()));
			preferential.setDeleteStatus(1);
			debitNoteService.createPreferential(preferential);
			this.saveLog(request.getSession(),preferential, "添加优惠信息",personUser.getCityId());
			return "添加成功";

		} catch (Throwable e) {
			logger.error("添加优惠信息", e);
			return e.getMessage();
		}
	}

	@RequestMapping(value = "preferentialData.do", method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String preferentialData(HttpServletRequest request) {
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "202";
		}
		String companyid = request.getParameter("companyId");
		String years = request.getParameter("years");
		String months = request.getParameter("months");
		try {
			CustomerUser customerUser = this.customerService.findById(Integer.valueOf(companyid));
			if(null==customerUser || !personUser.getCityId().equals(customerUser.getCityId())){
				return "203";
			}
			Verification dto =new Verification();
			dto.setCreateDate(new Date());
			dto.setCreateUser(SessionHelper.getUserId(request.getSession()));
			dto.setCompanyId(Integer.valueOf(companyid));
			dto.setYears(years);
			dto.setMonths(months);
			dto.setId(OrderNumberGenerator.buildDebitNoteNo(dto.getCreateDate(), dto.getCreateUser()));
			dto.setStatus(1);
			if(null!=customerUser){
				if(3==customerUser.getPayBillWay()){
					dto.setType(1);
				}else if (4==customerUser.getPayBillWay()) {
					dto.setType(2);
				}else{
					dto.setType(0);
				}
			}
			verificationService.create(dto);
			this.saveLog(request.getSession(),dto, "添加核销单",personUser.getCityId());
			return "200";
		} catch (Throwable e) {
			logger.error("添加核销单失败", e);
			//return e.getMessage();
			return "201";
		}
	}

	@RequestMapping(value = "delete.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delete(String id,HttpServletRequest request) {
		String result = "操作成功";
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			DebitNote dto = debitNoteService.findById(id);
			if(dto!=null){
				CustomerUser customerUser = this.customerService.findById(dto.getCompanyId());
				if(null==customerUser || !personUser.getCityId().equals(customerUser.getCityId())){
					return "你设置的默认城市与操作城市不符";
				}
				debitNoteService.delete(dto, SessionHelper.getUserId(request.getSession()).toString());
				this.saveLog(request.getSession(),dto, "删除收款单，名称："+dto.getId(),personUser.getCityId());
			}
		} catch (Exception e) {
			logger.error("删除收款单错误", e);
			return "删除失败,原因："+e.getMessage();
		}
		return result;
	}

	@RequestMapping(value = "deleteInvoice.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteInvoice(String id,HttpServletRequest request) {
		String result = "操作成功";
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Invoice dto = debitNoteService.findByIdInvoice(id);
			if(dto!=null){
				CustomerUser customerUser = this.customerService.findById(dto.getCompanyId());
				if(null==customerUser || !personUser.getCityId().equals(customerUser.getCityId())){
					return "你设置的默认城市与操作城市不符";
				}
				debitNoteService.deleteInvoice(dto, SessionHelper.getUserId(request.getSession()).toString());
				this.saveLog(request.getSession(),dto, "删除开票信息，名称："+dto.getId(),personUser.getCityId());
			}
		} catch (Exception e) {
			logger.error("删除开票信息错误", e);
			return "删除失败,原因："+e.getMessage();
		}
		return result;
	}

	@RequestMapping(value = "deletePreferential.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deletePreferential(String id,HttpServletRequest request) {
		String result = "操作成功";
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Preferential dto = debitNoteService.findByIdPreferential(id);
			if(dto!=null){
				CustomerUser customerUser = this.customerService.findById(dto.getCompanyId());
				if(null==customerUser || !personUser.getCityId().equals(customerUser.getCityId())){
					return "你设置的默认城市与操作城市不符";
				}
				debitNoteService.deletePreferential(dto, SessionHelper.getUserId(request.getSession()).toString());
				this.saveLog(request.getSession(),dto, "删除优惠，名称："+dto.getId(),personUser.getCityId());
			}
		} catch (Exception e) {
			logger.error("删除优惠错误", e);
			return "删除失败,原因："+e.getMessage();
		}
		return result;
	}


	/**
	 * 导出销售收款信息到Excel
	 */
	@RequestMapping(value = "exportSumExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportSumExcel(HttpServletRequest request,@RequestParam(value="reseauId",defaultValue="-1",required=false)Integer reseauId,
			HttpServletResponse response) {
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "201";
		}
		String userName = request.getParameter("userName");
		if(StringUtils.isBlank(userName)){
			userName=null;
		}
		String startTimeStr = request.getParameter("querydate");
		String start1TimeStr = request.getParameter("startdate");
		String param = request.getParameter("param");
		if(StringUtils.isEmpty(param)){
			param = "0";
		}
		if(reseauId==-1){
			reseauId=null;
		}
		Date querytime = null;
		Date starttime = null;
		if (StringUtils.isNotBlank(startTimeStr)) {
			querytime = DateUtil.parseDateStr(startTimeStr, "yyyy-MM");
			querytime = DateUtil.endOfMonth(querytime);
		}
		if (StringUtils.isNotBlank(start1TimeStr)) {
			starttime = DateUtil.parseDateStr(start1TimeStr, "yyyy-MM");
			starttime = DateUtil.beginOfMonth(starttime);
		}
		String region = request.getParameter("region");
		Integer regionId = null;
		if(!StringUtils.isEmpty(region)){
			regionId = Integer.valueOf(region);
		}
		String type = request.getParameter("type");
		String debflag = request.getParameter("debflag");
		String invflag = request.getParameter("invflag");
		String FilePutPath = "D:\\销售收款统计.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("销售收款统计.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			//List<DebitNoteDemo> debitNoteList=debitNoteService.debitNoteAmtListExport(userName);
			List<DebitNoteDemo> debitNoteList = debitNoteService.debitNoteAmtList(userName,starttime,querytime,param,regionId,reseauId,personUser.getCityId(),type,debflag,invflag);
			if (debitNoteList != null && !debitNoteList.isEmpty()) {
				sheet.mergeCells(0, 0, 5, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "销售收款统计", format1);
				sheet.addCell(label);

				font1 = new WritableFont(WritableFont.TIMES, 11,
						WritableFont.BOLD);
				WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
				// 设置背景颜色;
				cellFormat1.setBackground(Colour.GRAY_50);
				// 设置文字居中对齐方式;
				cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
				// 设置垂直居中;
				cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				sheet.addCell(new Label(0, 1, "序号", cellFormat1));
				sheet.addCell(new Label(1, 1, "用户名称", cellFormat1));
				sheet.addCell(new Label(2, 1, "区域", cellFormat1));
				sheet.addCell(new Label(3, 1, "公司名称", cellFormat1));
				sheet.addCell(new Label(4, 1, "销售金额", cellFormat1));
				sheet.addCell(new Label(5, 1, "收款金额", cellFormat1));
				sheet.addCell(new Label(6, 1, "优惠金额", cellFormat1));
				sheet.addCell(new Label(7, 1, "未收款金额", cellFormat1));
				sheet.addCell(new Label(8, 1, "开票金额", cellFormat1));
				sheet.addCell(new Label(9, 1, "员工消费金额", cellFormat1));
				sheet.addCell(new Label(10, 1, "id", cellFormat1));
				
				sheet.setColumnView(1, 20);// 根据内容自动设置列宽
				sheet.setColumnView(2, 30);// 根据内容自动设置列宽
				for (int i = 0; i < debitNoteList.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, debitNoteList.get(i).getUserName()));
					sheet.addCell(new Label(2, i + 2, debitNoteList.get(i).getRegion()));
					sheet.addCell(new Label(3, i + 2, debitNoteList.get(i).getCompanyName()));
					Long amountSum=debitNoteList.get(i).getAmountSum();
					sheet.addCell(new Number(4, i + 2, amountSum==null?0:(new BigDecimal(NumberTool.toYuanNumber(amountSum))).doubleValue()));
					Long amountSum1=debitNoteList.get(i).getAmountSum1();
					sheet.addCell(new Number(5, i + 2, amountSum1==null?0:(new BigDecimal(NumberTool.toYuanNumber(amountSum1))).doubleValue()));
					Long amountSum3=debitNoteList.get(i).getAmountSum3();
					sheet.addCell(new Number(6, i + 2, amountSum3==null?0:(new BigDecimal(NumberTool.toYuanNumber(amountSum3))).doubleValue()));
					Long amountSum4=debitNoteList.get(i).getAmountSum4();
					sheet.addCell(new Number(7, i + 2, amountSum4==null?0:(new BigDecimal(NumberTool.toYuanNumber(amountSum4))).doubleValue()));
					Long amountSum2=debitNoteList.get(i).getAmountSum2();
					sheet.addCell(new Number(8, i + 2, amountSum2==null?0:(new BigDecimal(NumberTool.toYuanNumber(amountSum2))).doubleValue()));
					Long amountSum5=debitNoteList.get(i).getAmountSum5();
					sheet.addCell(new Number(9, i + 2, amountSum5==null?0:(new BigDecimal(NumberTool.toYuanNumber(amountSum5))).doubleValue()));
					sheet.addCell(new Number(10, i + 2, debitNoteList.get(i).getCompanyId()));
				}
			}
			// 写入数据并关闭文件
			book.write();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (book != null) {
					book.close();
				}
				os.close();
			} catch (Exception e) {
				logger.error("jftj/genexcel WriteException", e);
			}
		}
		return FilePutPath;
	}
	
	@RequestMapping(value = "/recharge.htm")
	@ResponseBody
	public ModelAndView recharge(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("/customer/vipCharge");
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value = "/recharge.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String dorecharge(HttpServletRequest request){
		try {
			String id = request.getParameter("buyerId");
			if (StringUtils.isEmpty(id)) {
				return "用户ID不能为空!";
			}
			CustomerUser user = this.customerService.findById(Integer.parseInt(id));
			if(user.getIswxvip() != 1){
				return "该客户还未开通会员,无法充值!";
			}
			String charge = request.getParameter("chargeMoney");
			String free = request.getParameter("freeMoney");
			int chargeMoney = 0;
			int freeMoney = 0;
			if(StringUtils.isEmpty(charge)){
				return "请输入充值金额!";
			}else{
				chargeMoney=(int) (Double.parseDouble(charge)*100);
			}
			if(StringUtils.isNotEmpty(free)){
				freeMoney = (int) (Double.parseDouble(free)*100);
			}
			String orderNo = "CZ"+OrderUtil.GetOrderNumber();
			this.customerService.createRecharge(id,chargeMoney,freeMoney,orderNo);
			logger.info("用户id:"+id+"充值成功,金额:"+chargeMoney+"赠送:"+freeMoney);
			WXRechargeRecord record = new WXRechargeRecord();
			record.setCid(Integer.parseInt(id));
			record.setFreeMoney((long)freeMoney);
			record.setRechargeMoney((long)chargeMoney);
			record.setId(orderNo);
			saveLog(request.getSession(), record, "后台充值");
			return "200";
		} catch (Exception e) {
			logger.error("充值失败:"+e);
			return "201";
		}
	}
	
	@RequestMapping("export.do")
	@ResponseBody
	public String export(HttpServletRequest request,HttpServletResponse response,@RequestParam("userId")Integer userId){
		
			String FilePutPath = "D:\\详情.xls";
			WritableWorkbook book = null;
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				book = Workbook.createWorkbook(os);
				// 清空response
				response.reset();
				response.setContentType("application/dowload");
				response.setHeader("Content-disposition","attachment;filename=\""
				+ new String((java.net.URLEncoder.encode("详情.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

				// 打开文件
				// book = Workbook.createWorkbook(f);
				// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
				WritableSheet sheet = book.createSheet("sheet1", 0);
				Date nowtime = new Date();
				String monthdate = DateUtil.formatDate(nowtime, "yyyy-MM");
				String years = request.getParameter("years");
				String months = request.getParameter("months");
				Date start = DateUtil.constructDateByYMD(new Integer(years), new Integer(months), 1);
				String month = DateUtil.formatDate(start, "yyyy-MM");
				Date query = DateUtil.dateAdd("d", -1, start);
				Date end = null;
				if(monthdate.equals(month)){
					end = DateUtil.dateAdd("d", -1, nowtime);
					end = DateUtil.parseDateStr(DateUtil.formatDate(end, "yyyy-MM-dd 23:59:59"), "yyyy-MM-dd hh:mm:ss");
				}else{
					end = DateUtil.getLastDayOfMonth(start);
					end = DateUtil.parseDateStr(DateUtil.formatDate(end, "yyyy-MM-dd 23:59:59"), "yyyy-MM-dd hh:mm:ss");
				}
				List<ShopItemStock> lists = this.shopItemStockService.findlossDetail(userId,query,start,end);
				
				if (lists != null && !lists.isEmpty()) {
					sheet.mergeCells(0, 0, 13, 0);// 合并标题单元格
					// 指定了字串格式
					WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
							WritableFont.BOLD);
					WritableCellFormat format1 = new WritableCellFormat(font1);
					// 把水平对齐方式指定为居中
					format1.setAlignment(jxl.format.Alignment.CENTRE);
					// 把垂直对齐方式指定为居中
					format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
					// 使用了Label类的构造子，指定了字串被赋予那种格式
					Label label = new Label(0, 0, "月账单商品明细", format1);
					sheet.addCell(label);

					font1 = new WritableFont(WritableFont.TIMES, 11,
							WritableFont.BOLD);
					WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
					// 设置背景颜色;
					cellFormat1.setBackground(Colour.GRAY_50);
					// 设置文字居中对齐方式;
					cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
					// 设置垂直居中;
					cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
					sheet.addCell(new Label(0, 1, "编号", cellFormat1));
					sheet.addCell(new Label(1, 1, "一级类目", cellFormat1));
					sheet.addCell(new Label(2, 1, "二级类目", cellFormat1));
					sheet.addCell(new Label(3, 1, "名称", cellFormat1));
					sheet.addCell(new Label(4, 1, "规格", cellFormat1));
					sheet.addCell(new Label(5, 1, "月初", cellFormat1));
					sheet.addCell(new Label(6, 1, "采购", cellFormat1));
					sheet.addCell(new Label(7, 1, "退货", cellFormat1));
					sheet.addCell(new Label(8, 1, "消费", cellFormat1));
					sheet.addCell(new Label(9, 1, "月底", cellFormat1));
					sheet.addCell(new Label(10, 1, "拟损耗", cellFormat1));
					sheet.addCell(new Label(11, 1, "补贴", cellFormat1));
					sheet.addCell(new Label(12, 1, "单价", cellFormat1));


//					sheet.setColumnView(1, 25);// 根据内容自动设置列宽
//					sheet.setColumnView(2, 10);
//					sheet.setColumnView(3, 15);
//					sheet.setColumnView(4, 15);
//					sheet.setColumnView(5, 15);
//					sheet.setColumnView(6, 25);
					for (int i = 0; i < lists.size(); i++) {
						sheet.addCell(new Number(0, i + 2, i + 1));
						sheet.addCell(new Label(1, i + 2, lists.get(i).getOneCate()));
						sheet.addCell(new Label(2, i + 2, lists.get(i).getTwoCate()));
						sheet.addCell(new Label(3, i + 2, lists.get(i).getItemName()));
						sheet.addCell(new Label(4, i + 2, lists.get(i).getSize()));
						sheet.addCell(new Number(5, i + 2, lists.get(i).getFirstNum() ));
						sheet.addCell(new Number(6, i + 2, lists.get(i).getSourcingNum() ));
						sheet.addCell(new Number(7, i + 2, lists.get(i).getRefundNum() ));
						sheet.addCell(new Number(8, i + 2, lists.get(i).getConsumeNum() ));
						sheet.addCell(new Number(9, i + 2, lists.get(i).getLastNum() ));
						sheet.addCell(new Number(10, i + 2, lists.get(i).getLoseNum() ));
						sheet.addCell(new Number(11, i + 2, lists.get(i).getSubsidyNum() ));
						Long price=Long.parseLong(lists.get(i).getPrice().toString());
						sheet.addCell(new Number(12, i + 2, price==null?0:(new BigDecimal(NumberTool.toYuanNumber(price))).doubleValue()));
					}
				}
				// 写入数据并关闭文件
				book.write();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (book != null) {
						book.close();
					}
					os.close();
				} catch (Exception e) {
					logger.error("jftj/genexcel WriteException", e);
				}
			}
			return FilePutPath;
	}
	
	private void saveLog(HttpSession session,WXRechargeRecord dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.RechargeRecord.getName());
	       sysLog.setDataId(dto.getId());

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<WXRechargeRecord>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
	private void saveLog(HttpSession session,DebitNote dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.DEBIT_NOTE.getName());
	       sysLog.setDataId(dto.getId());

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<DebitNote>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
	

	private void saveLog(HttpSession session,Invoice dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.Invoice.getName());
	       sysLog.setDataId(dto.getId());

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<Invoice>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}

	private void saveLog(HttpSession session,Preferential dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.Preferential.getName());
	       sysLog.setDataId(dto.getId());

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

	private void saveLog(HttpSession session,Verification dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.Preferential.getName());
	       sysLog.setDataId(dto.getId());

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<Verification>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
	private void saveLog(HttpSession session,Remark dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.Remark.getName());
	       sysLog.setDataId(dto.getId().toString());

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<Remark>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
