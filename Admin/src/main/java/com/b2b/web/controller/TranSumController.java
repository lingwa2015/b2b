package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.BaseTranDetail;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.util.DateUtil;
import com.b2b.dto.TranSumDto;
import com.b2b.job.GenTranSumJob;
import com.b2b.page.Page;
import com.b2b.service.AccountLockService;
import com.b2b.service.CustomerService;
import com.b2b.service.OrderService;
import com.b2b.service.TranSumService;
import com.b2b.web.util.DateTool;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.PdfTools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.Number;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tranSum")
public class TranSumController {

	private static final Logger logger = LoggerFactory.getLogger(TranSumController.class);

	@Autowired
	TranSumService tranSumService;

	@Autowired
	CustomerService customerService;

	@Autowired
	GenTranSumJob genTranSumJob;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	AccountLockService accountLockService;

	@RequestMapping("tranSumList.htm")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("tranSum/list");
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return new ModelAndView("noCity");
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));

			String queryYear = request.getParameter("year");
			String queryMonth = request.getParameter("month");
			Date queryDate = null;
			Date startTime = null;
			Date endTime = null;

			if (StringUtils.isNotEmpty(queryYear) && StringUtils.isNotEmpty(queryMonth)) {
				queryDate = DateUtil.constructDateByYMD(new Integer(queryYear), new Integer(queryMonth), 1);
			} else {
				queryDate = new Date();
				queryYear = String.valueOf(DateUtil.getYear(queryDate));
				//queryMonth = String.valueOf(DateUtil.getMonth(queryDate) - 1);
				queryMonth = String.valueOf(DateUtil.getMonth(queryDate));
				queryDate = DateUtil.constructDateByYMD(new Integer(queryYear), new Integer(queryMonth), 1);
			}

			startTime = DateUtil.getFirstDayOfMonth(queryDate);
			endTime = DateUtil.getLastDayOfMonth(queryDate);

			
			String userName = request.getParameter("userName");
			mv.addObject("userName", userName);

			String startTimeStr = DateUtil.getTimeByFromat(startTime, "yyyy-MM-dd");
			String endTimeStr = DateUtil.getTimeByFromat(endTime, "yyyy-MM-dd");

			logger.debug("start time:" + DateUtil.getTimeByFromat(startTime, "yyyy-MM-dd HH:mm:ss"));
			logger.debug("end time:" + DateUtil.getTimeByFromat(endTime, "yyyy-MM-dd HH:mm:ss"));
			logger.debug("queryYear:" + queryYear);
			logger.debug("queryMonth:" + queryMonth);
			logger.debug("userName:" + userName);
			
			PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
			List<TranSumDto> lists = this.tranSumService.findByCondition(userName,startTime,endTime,pUser.getCityId());
			PageInfo<TranSumDto> info = new PageInfo<TranSumDto>(lists);
			Page<TranSumDto> page = new Page<TranSumDto>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			
			AccountLock aLock=new AccountLock();
			aLock.setYears(queryYear);
			aLock.setMonths(queryMonth);
			aLock.setCityId(pUser.getCityId());
			int accountLock=accountLockService.findLockByCityId(aLock);
			mv.addObject("accountLock", accountLock);
			
			mv.addObject("startTimeStr", startTimeStr);
			mv.addObject("endTimeStr", endTimeStr);
			mv.addObject("page", page);
			mv.addObject("year", queryYear);
			mv.addObject("month", queryMonth);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<TranSumDto> page = new Page<TranSumDto>(1, 1, Page.DEFAULT_PAGE_SIZE, new ArrayList<TranSumDto>());
			mv.addObject("page", page);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping("tranSumLock.htm")
	public ModelAndView listLock(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("tranSum/list");
		TestController.getMenuPoint(mv, request);
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
			String queryYear = request.getParameter("year");
			String queryMonth = request.getParameter("month");
			String lockTime =DateUtil.getLastDayOfMonth(new Integer(queryYear), new Integer(queryMonth)) ; 
			Date queryDate = null;

			if (StringUtils.isNotEmpty(queryYear) && StringUtils.isNotEmpty(queryMonth)) {
				queryDate = DateUtil.constructDateByYMD(new Integer(queryYear), new Integer(queryMonth), 1);
			} else {
				queryDate = new Date();
				queryYear = String.valueOf(DateUtil.getYear(queryDate));
				queryMonth = String.valueOf(DateUtil.getMonth(queryDate));
				queryDate = DateUtil.constructDateByYMD(new Integer(queryYear), new Integer(queryMonth), 1);
			}
			Date endTime = DateUtil.parseDateStr(lockTime+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
			Date startTime=DateUtil.getFirstDayOfMonth(endTime);

			TranSumDto dto = new TranSumDto();


			//锁帐之前再增加一次核销记录
			String result = tranSumService.createSumBatch(endTime,personUser.getId(),personUser.getCityId());
			logger.warn("锁帐之前核算结果："+result);
			
			int ret=orderService.updateOrderStatusByCityId(startTime, endTime,personUser.getCityId());
			if(ret>=0){//增加锁帐记录
				AccountLock aLock=new AccountLock();
				aLock.setYears(queryYear);
				aLock.setMonths(queryMonth);
				aLock.setLocks(1);
				aLock.setUserId(personUser.getId());
				aLock.setCreatedTime(new Date());
				aLock.setCityId(personUser.getCityId());
				accountLockService.create(aLock);
				mv.addObject("accountLock", 1);
			}

			String startTimeStr = DateUtil.getTimeByFromat(startTime, "yyyy-MM-dd");
			String endTimeStr = DateUtil.getTimeByFromat(endTime, "yyyy-MM-dd");

			logger.debug("start time:" + DateUtil.getTimeByFromat(startTime, "yyyy-MM-dd HH:mm:ss"));
			logger.debug("end time:" + DateUtil.getTimeByFromat(endTime, "yyyy-MM-dd HH:mm:ss"));
			logger.debug("queryYear:" + queryYear);
			logger.debug("queryMonth:" + queryMonth);
//			logger.debug("mobilePhone:" + mobilePhone);

//			Page<TranSumDto> page = tranSumService.findPage(dto, startTime, endTime, currentPage,
//					Page.DEFAULT_PAGE_SIZE);
			
			PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
			List<TranSumDto> lists = this.tranSumService.findByCondition(null,startTime,endTime,personUser.getCityId());
			PageInfo<TranSumDto> info = new PageInfo<TranSumDto>(lists);
			Page<TranSumDto> page = new Page<TranSumDto>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			
			mv.addObject("startTimeStr", startTimeStr);
			mv.addObject("endTimeStr", endTimeStr);
			mv.addObject("page", page);
			mv.addObject("year", queryYear);
			mv.addObject("month", queryMonth);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<TranSumDto> page = new Page<TranSumDto>(1, 1, Page.DEFAULT_PAGE_SIZE, new ArrayList<TranSumDto>());
			mv.addObject("page", page);
		}
		return mv;
	}

	@RequestMapping("detail.htm")
	public ModelAndView detail(int id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("tranSum/detail");
		try {
			TranSumDto dto = tranSumService.findDetail(id);
			mv.addObject("dto", dto);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping("print.htm")
	public ModelAndView print(int id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("tranSum/print");
		try {
			TranSumDto dto = tranSumService.findDetail(id);
			mv.addObject("dto", dto);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	
	@RequestMapping("detailNote.htm")
	public ModelAndView detailNote(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("tranSum/detail");
		try {
			String userid=request.getParameter("userid");
			String years=request.getParameter("years");
			String months=request.getParameter("months");
			TranSumDto dto = tranSumService.findDetailNew(userid,years,months);
			mv.addObject("dto", dto);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping(value = "run.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String run(HttpServletRequest request) throws Exception {
		try {
			String result = genTranSumJob.work();
			if (StringUtils.isNotBlank(result)) {
				return result;
			}
		} catch (Exception e) {
			logger.error("核算失败.", e);
			return e.getMessage();
		}

		return "核算成功";
	}

	@RequestMapping("export.do")
	public void export(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.valueOf(request.getParameter("tranSumId"));
			TranSumDto dto = tranSumService.findDetail(id);

			// 创建文档对象，A4纸大小
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			try {
				PdfWriter writer = PdfWriter.getInstance(document, stream);

				// 打开文档
				document.open();

				PdfPTable title = PdfTools.createTable(1, 0);
				title.addCell(PdfTools.createCell("领蛙结算单明细", PdfTools.headfont, Element.ALIGN_CENTER, 1, false));
				document.add(title);

				PdfPTable desc = PdfTools.createTable(4, 0);
				desc.addCell(PdfTools.createCell("客户: " + dto.getUserName(), PdfTools.keyfont, Element.ALIGN_LEFT, 1, false));
				desc.addCell(PdfTools.createCell("公司: " + dto.getCompanyName(), PdfTools.keyfont, Element.ALIGN_LEFT, 1, false));
				desc.addCell(PdfTools.createCell("月份: " + DateTool.dateFormat(dto.getSumDate(), "yyyy-MM"), PdfTools.keyfont, Element.ALIGN_LEFT, 1, false));
				double fee = Double.parseDouble(dto.getAmount().toString());
				fee = fee / 100;
				desc.addCell(PdfTools.createCell("金额: " + String.valueOf(fee), PdfTools.keyfont, Element.ALIGN_LEFT, 1, false));
				document.add(desc);

				PdfPTable tableTitle = PdfTools.createTable(3, 1);
				tableTitle.addCell(PdfTools.createCell("单据编号", PdfTools.keyfont, Element.ALIGN_LEFT));
				tableTitle.addCell(PdfTools.createCell("日期", PdfTools.keyfont, Element.ALIGN_LEFT));
				tableTitle.addCell(PdfTools.createCell("金额", PdfTools.keyfont, Element.ALIGN_LEFT));
				document.add(tableTitle);

				if (CollectionUtils.isNotEmpty(dto.getDetailList())) {
					for (BaseTranDetail detail : dto.getDetailList()) {
						String totalFee = String.valueOf((detail.getTotalFee() * 1.00f) / 100f);
						PdfPTable tableContent = PdfTools.createTable(3, 1);
						tableContent.addCell(PdfTools.createCell(detail.getNo(), PdfTools.textfont, Element.ALIGN_LEFT));
						tableContent.addCell(PdfTools.createCell(DateTool.dateFormat(detail.getExecutedTime(), "yyyy-MM-dd"), PdfTools.textfont, Element.ALIGN_LEFT));
						tableContent.addCell(PdfTools.createCell(totalFee, PdfTools.textfont, Element.ALIGN_LEFT));
						document.add(tableContent);
					}
				}
				// 关闭文档
				document.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			String fileName=dto.getUserName()+"_"+DateUtil.formatDate(dto.getSumDate(), "yyyyMM")+".pdf";
			fileName = new String(fileName.getBytes("gbk"), "iso8859-1");

			// 设置响应文档类型为pdf
			response.setContentType("application/pdf;charset=gbk");
			response.addHeader("content-disposition", "attachment; filename="
					+ fileName);
			// 设置响应数据大小
			response.setContentLength(stream.size());
			// 获取响应数据流
			ServletOutputStream out = response.getOutputStream();
			// 将pdf数据流写入响应数据流中
			stream.writeTo(out);
			out.flush();
			out.close();
		} catch (Throwable e) {
			logger.error("输出结算单明细", e);
		}
	}
	
	/**
	 * 导出核算信息到Excel
	 */
	@RequestMapping(value = "exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String queryYear = request.getParameter("year");
		String queryMonth = request.getParameter("month");
		Date queryDate = null;
		Date startTime = null;
		Date endTime = null;

		if (StringUtils.isNotEmpty(queryYear) && StringUtils.isNotEmpty(queryMonth)) {
			queryDate = DateUtil.constructDateByYMD(new Integer(queryYear), new Integer(queryMonth), 1);
		} else {
			queryDate = new Date();
			queryYear = String.valueOf(DateUtil.getYear(queryDate));
			//queryMonth = String.valueOf(DateUtil.getMonth(queryDate) - 1);
			queryMonth = String.valueOf(DateUtil.getMonth(queryDate));
			queryDate = DateUtil.constructDateByYMD(new Integer(queryYear), new Integer(queryMonth), 1);
		}

		startTime = DateUtil.getFirstDayOfMonth(queryDate);
		endTime = DateUtil.getLastDayOfMonth(queryDate);

		String FilePutPath = "D:\\核算信息.xls";
		WritableWorkbook book = null;
		SimpleDateFormat formatDate = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("客户核算信息.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			List<TranSumDto> tranSum = tranSumService.findAll(startTime,endTime);
			if (tranSum != null && !tranSum.isEmpty()) {
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
				Label label = new Label(0, 0, "客户核算信息表", format1);
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
				sheet.addCell(new Label(2, 1, "公司名称", cellFormat1));
				sheet.addCell(new Label(3, 1, "订单数目", cellFormat1));
				sheet.addCell(new Label(4, 1, "退货单数目", cellFormat1));
				sheet.addCell(new Label(5, 1, "金额", cellFormat1));
				sheet.addCell(new Label(6, 1, "统计月份", cellFormat1));
				sheet.addCell(new Label(7, 1, "对接业务员", cellFormat1));

				sheet.setColumnView(1, 30);// 根据内容自动设置列宽
				sheet.setColumnView(2, 40);// 根据内容自动设置列宽
				for (int i = 0; i < tranSum.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, tranSum.get(i).getUserName()));
					sheet.addCell(new Label(2, i + 2, tranSum.get(i).getCompanyName()));
					sheet.addCell(new Number(3, i + 2, tranSum.get(i).getOrderNum()));
					sheet.addCell(new Number(4, i + 2, tranSum.get(i).getRefundNum()));
					Long amount=tranSum.get(i).getAmount();
					sheet.addCell(new Number(5, i + 2, amount==null?0:(new BigDecimal(NumberTool.toYuanNumber(amount))).doubleValue()));
					sheet.addCell(new Label(6, i + 2,DateUtil.formatDate(tranSum.get(i).getSumDate(),"yyyy-MM")));
					sheet.addCell(new Label(7, i + 2, tranSum.get(i).getInterfacePerson()));
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
}
