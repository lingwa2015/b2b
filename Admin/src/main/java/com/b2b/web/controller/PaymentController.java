package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.NumberTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
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
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/payment")
public class PaymentController {

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	private static final String DATE_FORMAT_YM = "yyyy-MM";

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
	PaymentService paymentService;

	@Autowired
	StorageService storageService;

	@Autowired
	PaymentApplyService paymentApplyService;

	@Autowired
	PaymentAccountLockService paymentAccountLockService;

	@RequestMapping("paymentList.htm")
	public ModelAndView paymentApplyList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("payment/paymentList");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}

		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		String param = request.getParameter("param");

		Date startTime = null;
		Date endTime = null;

		if (StringUtils.isNotBlank(startTimeStr)) {
			startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YM);
			mv.addObject("startTime", startTimeStr);
		}
		if (StringUtils.isNotBlank(endTimeStr)) {
			endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YM);
			mv.addObject("endTime", endTimeStr);
		}

		String status = request.getParameter("status");
		mv.addObject("status", status);
		mv.addObject("param", param);
		String supplierName = request.getParameter("supplierName");
		mv.addObject("supplierName", supplierName);

		Payment sumPrice= this.paymentService.findSumPrice(startTime, endTime, param, status, supplierName , user.getCityId());
		mv.addObject("sumPrice", sumPrice);

		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		PageHelper.startPage(currentPage, 50);
		List<Payment> list = this.paymentService.findList(startTime, endTime, param, status, supplierName , user.getCityId());

		PageInfo<Payment> info = new PageInfo<Payment>(list);

		Page<Payment> page = new Page<Payment>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());

		mv.addObject("page", page);

		String lockDateStr = request.getParameter("lockDate");
		String[] lockDate = null;
		if (StringUtils.isNotBlank(lockDateStr)) {
			lockDate = lockDateStr.split("-");
		} else {
			Calendar now = Calendar.getInstance();
			int month = now.get(Calendar.MONTH) + 1;
			lockDateStr = (now.get(Calendar.YEAR) + "-" + (month < 10 ? "0" + month : month));
			lockDate = lockDateStr.split("-");
		}
		PaymentAccountLock aLock=new PaymentAccountLock();
		aLock.setYears(lockDate[0]);
		aLock.setMonths(lockDate[1]);
		aLock.setCityId(user.getCityId());
		int isHad = this.paymentAccountLockService.findLockByCityId(aLock);
		mv.addObject("lockDate", lockDateStr);
		mv.addObject("accountLock", isHad);
		TestController.getMenuPoint(mv, request);

		return mv;
	}

	@RequestMapping(value = "exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
							  HttpServletResponse response) {
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		String FilePutPath = "D:\\采购付款列表.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
					+ new String((java.net.URLEncoder.encode("采购付款列表.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			WritableSheet sheet = book.createSheet("sheet1", 0);
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			String param = request.getParameter("param");

			Date startTime = null;
			Date endTime = null;

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YM);
			}
			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YM);
			}

			String status = request.getParameter("status");
			String supplierName = request.getParameter("supplierName");
			Payment sumPrice= this.paymentService.findSumPrice(startTime, endTime, param, status, supplierName , personUser.getCityId());

			List<Payment> list = this.paymentService.findList(startTime, endTime, param, status, supplierName , personUser.getCityId());


			if (list != null && !list.isEmpty()) {
				sheet.mergeCells(0, 0, 8, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "采购付款列表", format1);
				sheet.addCell(label);

				font1 = new WritableFont(WritableFont.TIMES, 11,
						WritableFont.BOLD);
				WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
				// 设置背景颜色;
				cellFormat1.setBackground(jxl.format.Colour.GRAY_50);
				// 设置文字居中对齐方式;
				cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
				// 设置垂直居中;
				cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				sheet.addCell(new Label(0 , 1, "序号", cellFormat1));
				sheet.addCell(new Label(1 , 1, "供应商", cellFormat1));
				sheet.addCell(new Label(2 , 1, "采购入库金额", cellFormat1));
				sheet.addCell(new Label(3 , 1, "退货金额", cellFormat1));
				sheet.addCell(new Label(4 , 1, "应付款金额", cellFormat1));
				sheet.addCell(new Label(5 , 1, "未付款金额", cellFormat1));
				sheet.addCell(new Label(6 , 1, "优惠金额", cellFormat1));
				sheet.addCell(new Label(7 , 1, "开票金额", cellFormat1));
				sheet.addCell(new Label(8 , 1, "状态", cellFormat1));


				for (int i = 0; i < list.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, list.get(i).getSupplier()));
					Long stockPrice = list.get(i).getStockPrice();
					sheet.addCell(new Number(2, i + 2, stockPrice == null ? 0 : (
							new BigDecimal(NumberTool.toYuanNumber(stockPrice))).doubleValue()));
					Long outstockPrice = list.get(i).getOutstockPrice();
					sheet.addCell(new Number(3, i + 2, outstockPrice == null ? 0 : (
							new BigDecimal(NumberTool.toYuanNumber(outstockPrice))).doubleValue()));
					Long paymentPrice = list.get(i).getPaymentPrice();
					sheet.addCell(new Number(4, i + 2, paymentPrice == null ? 0 : (
							new BigDecimal(NumberTool.toYuanNumber(paymentPrice))).doubleValue()));
					Long notpaymentPrice = list.get(i).getNotpaymentPrice();
					sheet.addCell(new Number(5, i + 2, notpaymentPrice == null ? 0 : (
							new BigDecimal(NumberTool.toYuanNumber(notpaymentPrice))).doubleValue()));
					Long preferentialBenefitPrice = list.get(i).getPreferentialBenefitPrice();
					sheet.addCell(new Number(6, i + 2, preferentialBenefitPrice == null ? 0 : (
							new BigDecimal(NumberTool.toYuanNumber(preferentialBenefitPrice))).doubleValue()));
					Long receiptPrice = list.get(i).getReceiptPrice();
					sheet.addCell(new Number(7, i + 2, receiptPrice == null ? 0 : (
							new BigDecimal(NumberTool.toYuanNumber(receiptPrice))).doubleValue()));
					String statusStr = list.get(i).getStatus() == 0 ? "待付款" : "已付款";
					sheet.addCell(new Label(8, i + 2, statusStr));
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

	@RequestMapping("paymentDetails.htm")
	public ModelAndView paymentDetail(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("payment/paymentDetails");
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if (null == user.getCityId()) {
				return new ModelAndView("noCity");
			}

			String supplierId = request.getParameter("supplierId");
			Supplier supplier = this.supplierService.findById(Integer.valueOf(supplierId));
			mv.addObject("supplier", supplier);

			List<Payment> lists = this.paymentService.findListBySupplierId(Integer.valueOf(supplierId));
			mv.addObject("lists", lists);

		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Payment> page = new Page<Payment>(1, 1,
					Page.DEFAULT_PAGE_SIZE,new ArrayList<Payment>());
			mv.addObject("page", page);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping("findStorageList.htm")
	public ModelAndView findStorageList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("payment/paymentDetailsPrint");
		try {
			String supplierId = request.getParameter("supplierId");
			String sumdateStr = request.getParameter("sumdate");
			Date sumdate = null;

			if (StringUtils.isNotBlank(sumdateStr)) {
				sumdate = DateUtil.parseDateStr(sumdateStr, "yyyy-MM");
			}
			List<StorageItem> storages = this.storageService.selectBySupplierIdAndSumdate(Integer.valueOf(supplierId), sumdate);
			List<StorageItem> outs = this.storageService.findOutstockItem(Integer.valueOf(supplierId), sumdate);
			List<StorageItem> list = new ArrayList<StorageItem>();
			list.addAll(storages);
			list.addAll(outs);

			Supplier supplier = this.supplierService.findById(Integer.valueOf(supplierId));


			mv.addObject("list", list);
			mv.addObject("supplierId", supplierId);
			mv.addObject("supplierName", supplier.getSupplierName());
			mv.addObject("sumdate", sumdate);



		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping("paymentEdition.htm")
	public ModelAndView paymentEdition(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("payment/paymentEdition");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		String id = request.getParameter("id");
		String supplierId = request.getParameter("supplierId");
		Supplier supplier = this.supplierService.findById(Integer.valueOf(supplierId));

		List<PersonUser> applicantList = this.userService.findByPost("采购", user.getCityId());

		Payment dto = paymentService.findById(Long.valueOf(id));
		mv.addObject("paymentTime", new Date());
		mv.addObject("supplier", supplier);

		mv.addObject("applicantList", applicantList);
		mv.addObject("dto", dto);
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping(value = "/changeRemake.do")
	@ResponseBody
	public String changeRemake(HttpServletRequest req) {
		try {
			PersonUser user = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String id = req.getParameter("id");
			if (StringUtils.isEmpty(id)) {
				return "请选择要编辑的订单";
			}
			String remake = req.getParameter("remake");

			Payment payment = this.paymentService.findById(Long.valueOf(id));

			if (payment != null) {
				payment.setRemake(remake);
				this.paymentService.update(payment);
			}

			return "200";

		} catch (Throwable e) {
			logger.error("添加付款申请单", e);
			return e.getMessage();
		}
	}

	@RequestMapping(value = "exportStorageExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportStorageExcel(HttpServletRequest request,
							  HttpServletResponse response) {
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		String FilePutPath = "D:\\采购和退货明细.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
					+ new String((java.net.URLEncoder.encode("采购和退货明细.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			WritableSheet sheet = book.createSheet("sheet1", 0);

			String supplierId = request.getParameter("supplierId");
			String sumdateStr = request.getParameter("sumdate");
			Date sumdate = null;

			if (StringUtils.isNotBlank(sumdateStr)) {
				sumdate = DateUtil.parseDateStr(sumdateStr, "yyyy-MM");
			}
			List<StorageItem> storages = this.storageService.selectBySupplierIdAndSumdate(Integer.valueOf(supplierId), sumdate);
			List<StorageItem> outs = this.storageService.findOutstockItem(Integer.valueOf(supplierId), sumdate);
			List<StorageItem> list = new ArrayList<StorageItem>();
			list.addAll(storages);
			list.addAll(outs);

			Supplier supplier = this.supplierService.findById(Integer.valueOf(supplierId));


			if (list != null && !list.isEmpty()) {
				sheet.mergeCells(0, 0, 7, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "采购和退货明细", format1);
				sheet.addCell(label);


				String cell2 = "供应商：" + supplier.getSupplierName();
				cell2 = cell2 + "  年月：" + DateUtil.formatDate(sumdate, "yyyy-MM-dd");
				font1 = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
				format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.LEFT);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				Label label2 = new Label(0, 1, cell2, format1);
				sheet.addCell(label2);

				font1 = new WritableFont(WritableFont.TIMES, 11,
						WritableFont.BOLD);
				WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
				// 设置背景颜色;
				cellFormat1.setBackground(jxl.format.Colour.GRAY_50);
				// 设置文字居中对齐方式;
				cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
				// 设置垂直居中;
				cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				sheet.addCell(new Label(0 , 2, "采购/退货", cellFormat1));
				sheet.addCell(new Label(1 , 2, "操作日期", cellFormat1));
				sheet.addCell(new Label(2 , 2, "单号", cellFormat1));
				sheet.addCell(new Label(3 , 2, "品名", cellFormat1));
				sheet.addCell(new Label(4 , 2, "单位", cellFormat1));
				sheet.addCell(new Label(5 , 2, "数量", cellFormat1));
				sheet.addCell(new Label(6 , 2, "单价", cellFormat1));
				sheet.addCell(new Label(7 , 2, "金额", cellFormat1));


				for (int i = 0; i < list.size(); i++) {
					sheet.addCell(new Label(0, i + 3, list.get(i).getId() == 1 ? "采购" : "退货"));
					sheet.addCell(new Label(1, i + 3,  DateUtil.formatDate(list.get(i).getExecutedTime(), "yyyy-MM-dd")));
					sheet.addCell(new Label(2, i + 3,  list.get(i).getNumber()));
					sheet.addCell(new Label(3, i + 3,  list.get(i).getItemName()));
					sheet.addCell(new Label(4, i + 3,  list.get(i).getSizeValue()));
					sheet.addCell(new Number(5, i + 3,  list.get(i).getBuyNum()));
					sheet.addCell(new Number(6, i + 3,  new BigDecimal(NumberTool.toYuanNumber(list.get(i).getBuyPrice())).doubleValue()));
					sheet.addCell(new Number(7, i + 3,  new BigDecimal(NumberTool.toYuanNumber(list.get(i).getTotalFee())).doubleValue()));
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

	@RequestMapping(value = "/paymentLock.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String paymentLock(HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "noCity";
			}
			String[] lockDate = request.getParameter("lockDate").split("-");

			PaymentAccountLock aLock=new PaymentAccountLock();
			aLock.setYears(lockDate[0]);
			aLock.setMonths(lockDate[1]);
			aLock.setCityId(personUser.getCityId());
			int isHad = this.paymentAccountLockService.findLockByCityId(aLock);
			if (isHad <= 0) {
				aLock.setLocks(1);
				aLock.setUserId(personUser.getId());
				aLock.setCreatedTime(new Date());
				paymentAccountLockService.create(aLock);
			} else {
				return "已锁";
			}


		} catch (Exception e) {
			return e.getMessage();
		}
		return "200";
	}

	@RequestMapping(value = "/changeLockDate.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String changeLockDate(HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "noCity";
			}
			String[] lockDate = request.getParameter("lockDate").split("-");

			PaymentAccountLock aLock=new PaymentAccountLock();
			aLock.setYears(lockDate[0]);
			aLock.setMonths(lockDate[1]);
			aLock.setCityId(personUser.getCityId());
			int isHad = this.paymentAccountLockService.findLockByCityId(aLock);
			if (isHad <= 0) {
				return "";
			} else {
				return "1";
			}

		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
