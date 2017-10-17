package com.b2b.job;

import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("genTranSumJob")
public class GenTranSumJob {

	private static final Logger logger = LoggerFactory
			.getLogger(GenTranSumJob.class);

	@Autowired
	TranSumService tranSumService;

	@Autowired
	OrderService orderService;

	@Autowired
	RefundService refundService;

	@Autowired
	DayShopStockService dayShopStockService;

	@Autowired
	ConfigService configService;

	@Autowired
	CustomerService CustomerService;

	@Autowired
	ConsumeRankService consumeRankService;

	@Autowired
	ShopOrderService shopOrderService;

	@Autowired
	DailyReportService dailyReportService;
	
	@Autowired
	ShopDailyReportService shopDailyReportService;
	
	@Autowired
	ItemScoreService itemScoreService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	MonthReportService monthReportService;
	
	@Autowired
	ShopMonthReportService shopMonthReportService;
	
	@Autowired
	ShopItemService shopItemService;
	
	@Autowired
	TranConsumeService tranConsumeService;
	
	@Autowired
	ShopWeekReportService shopWeekReportService;
	
	@Autowired
	WeekReportService weekReportService;
	
	@Autowired
	FreeShopMonthReportService freeShopMonthReportService;
	
	@Autowired
	FreeMonthReportService freeMonthReportService;
	
	@Autowired
	FreeShopWeekReportService freeShopWeekReportService;
	
	@Autowired
	FreeWeekReportService freeWeekReportService;
	
	@Autowired
	FreeDayReportService freeDayReportService;
	
	@Autowired
	MemberPointService memberPointService;
	
	@Autowired
	MemberPointReportService memberPointReportService;
	
	@Autowired
	AccountLockService accountLockService;
	
	@Autowired
	ReseauService reseauService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	FreeShopDailyReportService freeShopDailyReportService;
	
	@Autowired
	ShopSalesService shopSalesService;

	@Autowired
	ShopItemMonthReportService shopItemMonthReportService;

	@Autowired
	ItemDailyReportService itemDailyReportService;

	@Autowired
	ItemWeekReportService itemWeekReportService;

	@Autowired
	ItemMonthReportService itemMonthReportService;

	@Autowired
	PaymentService paymentService;

	@Autowired
	PaymentAccountLockService paymentAccountLockService;


	@Autowired
	ItemWeekVolumeService itemWeekVolumeService;

	public String work() {

		logger.warn("开始生成核算数据.................................");
		try {
			// String sumDate =
			// configService.findByName(ConfigEnum.TRAN_SUM_DATE);
			// if(StringUtils.isNotBlank(sumDate)){
			// date = DateUtils.parseDate(sumDate, "yyyy-MM-dd HH:mm:ss");
			// }
			List<City> citys = this.cityService.findAllOpenCity();
			for (City city : citys) {
				Date date = new Date();
				SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
				SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, -1);
				date = calendar.getTime();
				Date endDate = dft2.parse(dft.format(date));

				logger.warn(city.getCityName()+"当月核算日期："
						+ DateUtil.formatDate(date, "yyyy-MM-dd 23:59:59"));
				String result = tranSumService.createBySumBatch(endDate,date,city.getId());
				logger.warn(city.getCityName()+"当月核算结果：" + result);

				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime(date);
				Date date2 = DateUtil.dateAdd("m", -1, date);
				calendar1.set(Calendar.DAY_OF_MONTH, 1);
				calendar1.add(Calendar.DATE, -1);
				date = calendar1.getTime();
				Date endPreDate = dft2.parse(dft.format(date));
				logger.warn(city.getCityName()+"上月核算日期："
						+ DateUtil.formatDate(date, "yyyy-MM-dd 23:59:59"));
				String preresult = tranSumService.createBySumBatch(endPreDate,date2,city.getId());
				logger.warn(city.getCityName()+"上月核算结果：" + preresult);
			}

			return null;
		} catch (Exception e) {
			logger.error("生成核算数据错误", e);
		}

		logger.warn("生成核算数据结束.................................");

		return null;
	}

	public String workconsume() {
		logger.warn("开始生成消费数据.................................");
		try {
			// String sumDate =
			// configService.findByName(ConfigEnum.TRAN_SUM_DATE);
			// if(StringUtils.isNotBlank(sumDate)){
			// date = DateUtils.parseDate(sumDate, "yyyy-MM-dd HH:mm:ss");
			// }
			List<City> citys = this.cityService.findAllOpenCity();
			for (City city : citys) {


			Date date = new Date();
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
			Date endDate = dft2.parse(dft.format(date));

			logger.warn(city.getCityName()+"当月消费日期："
					+ DateUtil.formatDate(date, "yyyy-MM-dd 23:59:59"));
			this.dayShopStockService.create(endDate);
			String result = tranSumService.createByconsumeBatch2(endDate,date,city.getId());
			logger.warn(city.getCityName()+"当月消费结果：" + result);

			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(date);
			Date date2 = DateUtil.dateAdd("m", -1, date);
			calendar1.set(Calendar.DAY_OF_MONTH, 1);
			calendar1.add(Calendar.DATE, -1);
			date = calendar1.getTime();
			Date endPreDate = dft2.parse(dft.format(date));
			logger.warn(city.getCityName()+"上月消费日期："
					+ DateUtil.formatDate(date, "yyyy-MM-dd 23:59:59"));
			String preresult = tranSumService.createByconsumeBatch(endPreDate,date2,city.getId());
			logger.warn(city.getCityName()+"上月消费结果：" + preresult);

			}
		} catch (Exception e) {
			logger.error("生成消费数据错误", e);
		}

		logger.warn("生成消费数据结束.................................");

		return null;
	}

	public void workConsumeRank() {
		try {
			SimpleDateFormat dft3 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
			Date date = calendar.getTime();
			calendar.add(Calendar.DATE, -6);
			Date date2 = calendar.getTime();
			Date endDate = dft2.parse(dft.format(date));
			Date startDate = dft2.parse(dft3.format(date2));
			List<CustomerUser> shops = this.CustomerService.findShops();
			if (!shops.isEmpty()) {
				for (CustomerUser customerUser : shops) {
					this.consumeRankService.delete(customerUser.getId());
					List<ConsumeRank> lists = this.consumeRankService.rank(
							customerUser.getId(), startDate, endDate);
					if (!lists.isEmpty()) {
						for (ConsumeRank consumeRank : lists) {
							consumeRank.setShopId(customerUser.getId());
							this.consumeRankService.insert(consumeRank);
						}
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void workForUpdateOrderTotalFee() {

		logger.warn("开始核对订单总金额数据.................................");
		try {
			orderService.updateOrderTotalFee();
		} catch (Exception e) {
			logger.error("核对订单总金额错误", e);
		}

		logger.warn("核对订单总金额结束.................................");
	}

	public void workForUpdateRefundTotalFee() {

		logger.warn("开始核对盘库单总金额数据.................................");
		try {
			refundService.updateRefundTotalFee();
		} catch (Exception e) {
			logger.error("核对盘库单总金额错误", e);
		}

		logger.warn("核对盘库单总金额结束.................................");
	}

	//生成便利店单店日报
	public void workShopDailyReport(){
		logger.warn("开始便利店单店生成日报数据.................................");
		try {
			Date date = new Date();
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dft3 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			SimpleDateFormat dft4 = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
			calendar.add(Calendar.DATE, -7);
			Date date2 = calendar.getTime();
			Date startDate = dft2.parse(dft3.format(date));
			Date endDate = dft2.parse(dft.format(date));
			Date queryDate = dft4.parse(dft4.format(date2));
			List<HashMap<String,Object>> lists = this.CustomerService.findAllShop();
			if(!lists.isEmpty()){
				for (HashMap<String, Object> shop : lists) {
					Integer id = Integer.parseInt(shop.get("id").toString());
					ShopOrder shopOrder = this.shopOrderService.findByShopIdAndDate(id,startDate,endDate);
					ShopDailyReport shopDailyReport = this.shopDailyReportService.findByShopIdAndSumdate(id,queryDate);
					ShopDailyReport report = new ShopDailyReport();
					report.setShopId(id);
					report.setSumdate(date);
					report.setDiscount(new BigDecimal(shop.get("shop_discount").toString()));
					report.setShopName(shop.get("user_name").toString());
					report.setTotalConsume(shopOrder.getTotalPrice());
					report.setTotalExpend(shopOrder.getActualPrice());
					report.setConsumePersonNum(shopOrder.getShopId());
					report.setConsumePen(shopOrder.getBuyerId());
					HashMap<String,Object> map = this.shopItemService.findTolalMoneyAndKind(id);
					report.setOnshelfFee(Long.parseLong(map.get("money").toString()));
					report.setOnshelfKind(Integer.parseInt(map.get("kinds").toString()));
					Integer movekind=this.shopOrderService.findKindsByShopIdAndDate(id,startDate,endDate);
					report.setMoveKind(movekind);
					Order order = this.orderService.findOrderFeeAndOrderNumByDayAndUserId(id, startDate);
					report.setOrderFee(order.getTotalFee());
					report.setOrderNum(order.getTotalNum());
					if(Integer.parseInt(map.get("kinds").toString())>0){
						double aa =  Integer.parseInt(map.get("kinds").toString());
						double bb = movekind;
						double cc = bb/aa;
						BigDecimal bg = new BigDecimal(cc).setScale(2,
								BigDecimal.ROUND_HALF_UP);
						report.setMovePercent(bg);
					}
					if (shopOrder.getShopId()>0) {
						report.setAvgExpend(shopOrder.getTotalPrice()/shopOrder.getShopId());
					}else{
						report.setAvgExpend(0l);
					}
					if(null!=shopDailyReport){
						Integer num = shopDailyReport.getConsumePersonNum();
						report.setBeforeLastWeekNum(report.getConsumePersonNum()-num);
						if(null!=shopDailyReport.getTotalConsume()&&shopDailyReport.getTotalConsume()!=0){
							double consume = shopDailyReport.getTotalConsume();
							double a = (report.getTotalConsume()-consume)*100/consume;
							BigDecimal bg = new BigDecimal(a).setScale(2,
									BigDecimal.ROUND_HALF_UP);
							report.setBeforeLastWeekExpand(bg);
						}
						if(null!=shopDailyReport.getAvgExpend()&&shopDailyReport.getAvgExpend()!=0){
							double avg =shopDailyReport.getAvgExpend();
							double b = (report.getAvgExpend()-avg)*100/avg;
							BigDecimal bg = new BigDecimal(b).setScale(2,
									BigDecimal.ROUND_HALF_UP);
							report.setBeforeLastWeekAvg(bg);
						}
					}
					this.shopDailyReportService.createBySumBatch(report);
				}
			}
		} catch (Exception e) {
			logger.error("生成便利店单店日报数据错误", e);
		}
		logger.warn("生成便利店单店日报数据结束.................................");
	}

	public void workItemScore(){
		List<Integer> lists = this.consumeRankService.findShopIdByWeekFeeAndKinds();
		if(!lists.isEmpty()){
			for (Integer id : lists) {
				CustomerUser customerUser = this.CustomerService.findById(id);
				List<ConsumeRank> ranks = this.consumeRankService.findItemIdByShopId(id);
				if(!ranks.isEmpty()){
					double size = ranks.size();
					for (int i = 0; i < ranks.size(); i++) {
						double ii = (i+1);
						double a = ii/size;
						int score = 0 ;
						if(a>= 0.1 && a <0.2){
							if(a<0.15){
								score = 10;
							}else{
								score = 9;
							}
						}else if (a>= 0.2 && a <0.3) {
							if(a<0.25){
								score = 9;
							}else{
								score = 8;
							}
						}else if (a>= 0.3 && a <0.4) {
							if(a<0.35){
								score = 8;
							}else{
								score = 7;
							}
						}else if (a>= 0.4 && a <0.5) {
							if(a<0.45){
								score = 7;
							}else{
								score = 6;
							}
						}else if (a>= 0.5 && a <0.6) {
							if(a<0.55){
								score = 6;
							}else{
								score = 5;
							}
						}else if (a>= 0.6 && a <0.7) {
							if(a<0.65){
								score = 5;
							}else{
								score = 4;
							}
						}else if (a>= 0.7 && a <0.8) {
							if(a<0.75){
								score = 4;
							}else{
								score = 3;
							}
						}else if (a>= 0.8 && a <0.9) {
							if(a<0.85){
								score = 3;
							}else{
								score = 2;
							}
						}else if (a>= 0.9 && a <=1.0) {
							if(a<0.95){
								score = 2;
							}else{
								score = 1;
							}
						}else {
							score = 10;
						}
						ItemScore itemScore = new ItemScore();
						itemScore.setItemId(ranks.get(i).getItemId());
						itemScore.setScore(score);
						itemScore.setShopId(id);
						itemScore.setShopName(customerUser.getUserName());
						itemScore.setCreatedTime(new Date());
						this.itemScoreService.insert(itemScore);
					}
				}
			}
		}
		List<ItemScore> scores = this.itemScoreService.findAvgGroupByItemId();
		if(!scores.isEmpty()){
			for (ItemScore itemScore : scores) {
				this.itemService.updateItemScore(itemScore.getItemId(),itemScore.getAvgScore());
			}
		}
	}



	//单店月报
	public void workShopMonthReport(){
		logger.warn("开始生成单店月报数据.................................");
		try {
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dft3 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			SimpleDateFormat dft4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
			Date date = calendar.getTime();
			int day = DateUtil.getDay(date);
			String month = dft.format(date);
			Date date4 = dft2.parse(dft2.format(date));
			Date endDate = dft4.parse(dft3.format(date));
			calendar.add(Calendar.MONTH, -1);
			Date date3 = calendar.getTime();
			Date month2 = dft2.parse(dft2.format(date3));
			Date fdate = DateUtil.getFirstDayOfMonth(date4);
			List<CustomerUser> shops = this.CustomerService.findShops();
			if(!shops.isEmpty()){
				this.shopMonthReportService.deleteByDate(date4);

				for (CustomerUser customerUser : shops) {
					ShopOrder shopOrder = this.shopOrderService.findMonthRecordByShopIdAndDate(customerUser.getId(),month);

					if(null==shopOrder){
						ShopMonthReport report = new ShopMonthReport();
						Calendar cal1 = Calendar.getInstance();
						cal1.setTime(date4);
						Calendar cal2 = Calendar.getInstance();
						if (customerUser.getContractDate() != null) {
							cal2.setTime(customerUser.getContractDate());
							if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
								if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
									report.setIsnew(1);
								} else {
									report.setIsnew(0);
								}
							} else {
								report.setIsnew(0);
							}
						} else {
							report.setIsnew(1);
						}
						report.setSumDate(date4);
						report.setUserName(customerUser.getUserName());
						report.setUserId(customerUser.getId());
						report.setDiscount(customerUser.getShopDiscount());
						report.setConsumeFee(0l);
						report.setExpandFee(0l);
						report.setAvgFee(0l);
						report.setConsumeNum(0);
						report.setConsumePen(0);
						report.setAvgManFee(0l);
						report.setSourcingFee(0l);
						report.setOrderNum(0);
						report.setRefundNum(0);
						report.setLossPercent(0);
						TranConsume stock = this.tranConsumeService.findMonthStockByUserIdAndDate(customerUser.getId(),date4);
						if(null!=stock){
							report.setStock(stock.getGoodsMoney());
							report.setMonthFirstStock(stock.getMonthStockMoney());
						}
						ShopOrder order = this.shopOrderService.findProfitByShopIdAndDate(customerUser.getId(),fdate,endDate);
						report.setZhekoucaigou(order.getSourcing());
						report.setProfit(order.getExpend());
						if(order.getSourcing()>0){
							double aa = order.getExpend();
							double bb = order.getSourcing();
							double profitrate =aa/bb*100;
							BigDecimal bgg = new BigDecimal(profitrate).setScale(2,
									BigDecimal.ROUND_HALF_UP);
							report.setProfitRate(bgg);
						}
						report.setWastage(0l);
						report.setSubsidy(0l);
						ShopMonthReport shopMonthReport = this.shopMonthReportService.findByUserIdAndSumDate(customerUser.getId(),month2);
						if(null!=shopMonthReport){
							if(null!=report.getConsumeFee()&&null!=shopMonthReport.getConsumeFee()&&shopMonthReport.getConsumeFee()!=0){
								double consumeFee2 = shopMonthReport.getConsumeFee();
								double a = (report.getConsumeFee()-consumeFee2)/consumeFee2*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeExpandFee(bg);
							}
							if(null!=report.getAvgFee()&&null!=shopMonthReport.getAvgFee()&&shopMonthReport.getAvgFee()!=0){
								double avgFee = shopMonthReport.getAvgFee();
								double a = (report.getAvgFee()-avgFee)/avgFee*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeAvgFee(bg);
							}
							if(null!=report.getConsumeNum()&&null!=shopMonthReport.getConsumeNum()&&shopMonthReport.getConsumeNum()!=0){
								double consumeNum2 = shopMonthReport.getConsumeNum();
								double a = (report.getConsumeNum()-consumeNum2)/consumeNum2*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeConsumeNum(bg);
							}
							if(null!=report.getConsumePen()&&null!=shopMonthReport.getConsumePen()&&shopMonthReport.getConsumePen()!=0){
								double consumePen2 = shopMonthReport.getConsumePen();
								double a = (report.getConsumePen()-consumePen2)/consumePen2*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeConsumePen(bg);
							}
							if(null!=report.getAvgManFee()&&null!=shopMonthReport.getAvgManFee()&&shopMonthReport.getAvgManFee()!=0){
								double avgManFee = shopMonthReport.getAvgManFee();
								double a = (report.getAvgManFee()-avgManFee)/avgManFee*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeAvgManFee(bg);
							}
							if(null!=report.getSourcingFee()&&null!=shopMonthReport.getSourcingFee()&&shopMonthReport.getSourcingFee()!=0){
								double sourcingFee = shopMonthReport.getSourcingFee();
								double a = (report.getSourcingFee()-sourcingFee)/sourcingFee*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeSourcingFee(bg);
							}
							if(null!=report.getOrderNum()&&null!=shopMonthReport.getOrderNum()&&shopMonthReport.getOrderNum()!=0){
								double orderNum2 = shopMonthReport.getOrderNum();
								double a = (report.getOrderNum()-orderNum2)/orderNum2*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeOrderNum(bg);
							}
							if(null!=report.getLossPercent()&&null!=shopMonthReport.getLossPercent()&&shopMonthReport.getLossPercent()!=0){
								double lossPercent = shopMonthReport.getLossPercent();
								double a = (report.getLossPercent()-lossPercent)/lossPercent*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeLossPercent(bg);
							}

						}
						this.shopMonthReportService.insert(report);
					}else{
						Integer orderNum = this.orderService.findOrderNumByMonth(customerUser.getId(),date4);
						Integer refund = this.refundService.findRefundNumByMonth(customerUser.getId(),date4);
						ShopMonthReport report = new ShopMonthReport();
						//report.setRegion(customerUser.getRegion());
						report.setSumDate(date4);
						report.setUserName(customerUser.getUserName());
						report.setUserId(customerUser.getId());
						report.setDiscount(customerUser.getShopDiscount());
						report.setConsumeFee(shopOrder.getConsume());
						report.setExpandFee(shopOrder.getExpend());
						Date firstdate = this.shopOrderService.findFirstPayDay(customerUser.getId());
						if(null!=firstdate){
							Date firstDayOfMonth = DateUtil.getFirstDayOfMonth(date);
							Date firstdate2 = dft2.parse(dft2.format(firstdate));
							long diff2 = DateUtil.diff(firstdate2,date4 , Calendar.HOUR);
							if(diff2>=0){
								if(firstdate.after(firstDayOfMonth)){
									int day2 = DateUtil.getDay(firstdate);
									int day3 = DateUtil.getDay(date4);
									int a = day3 - day2+1;
									report.setAvgFee(shopOrder.getConsume()/a);
								}else{
									report.setAvgFee(shopOrder.getConsume()/day);
								}
							}else{
								continue;
							}
						}
						Integer consumeNum = this.shopOrderService.findConsumeNumByShopIdAndMonth(customerUser.getId(),date4);
						report.setConsumeNum(consumeNum);
						Integer consumepen = this.shopOrderService.findConsumePenByShopIdAndMonth(customerUser.getId(),date4);
						report.setConsumePen(consumepen);
						if(null!=report.getAvgFee()&&consumeNum>0){
							report.setAvgManFee(report.getAvgFee()/consumeNum);
						}
						report.setSourcingFee(shopOrder.getSourcing());
						report.setOrderNum(orderNum);
						report.setRefundNum(refund);
						int aaaa = 100-shopOrder.getStatus();
						if(aaaa<0 || aaaa>=100 ){
							report.setLossPercent(0);
						}else{
							report.setLossPercent(100-shopOrder.getStatus());
						}
						TranConsume stock = this.tranConsumeService.findMonthStockByUserIdAndDate(customerUser.getId(),date4);
						if(null!=stock){
							report.setStock(stock.getGoodsMoney());
							report.setMonthFirstStock(stock.getMonthStockMoney());
						}
						ShopOrder order = this.shopOrderService.findProfitByShopIdAndDate(customerUser.getId(),fdate,endDate);
						report.setZhekoucaigou(order.getSourcing());
						report.setProfit(order.getExpend());
						if(order.getSourcing()>0){
							double aa = order.getExpend();
							double bb = order.getSourcing();
							double profitrate =aa/bb*100;
							BigDecimal bgg = new BigDecimal(profitrate).setScale(2,
									BigDecimal.ROUND_HALF_UP);
							report.setProfitRate(bgg);
						}
						report.setWastage(shopOrder.getLoss()-shopOrder.getSubsidy());
						report.setSubsidy(shopOrder.getSubsidy());
						ShopMonthReport shopMonthReport = this.shopMonthReportService.findByUserIdAndSumDate(customerUser.getId(),month2);
						if(null!=shopMonthReport){
							if(null!=report.getConsumeFee()&&null!=shopMonthReport.getConsumeFee()&&shopMonthReport.getConsumeFee()!=0){
								double consumeFee2 = shopMonthReport.getConsumeFee();
								double a = (report.getConsumeFee()-consumeFee2)/consumeFee2*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeExpandFee(bg);
							}
							if(null!=report.getAvgFee()&&null!=shopMonthReport.getAvgFee()&&shopMonthReport.getAvgFee()!=0){
								double avgFee = shopMonthReport.getAvgFee();
								double a = (report.getAvgFee()-avgFee)/avgFee*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeAvgFee(bg);
							}
							if(null!=report.getConsumeNum()&&null!=shopMonthReport.getConsumeNum()&&shopMonthReport.getConsumeNum()!=0){
								double consumeNum2 = shopMonthReport.getConsumeNum();
								double a = (report.getConsumeNum()-consumeNum2)/consumeNum2*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeConsumeNum(bg);
							}
							if(null!=report.getConsumePen()&&null!=shopMonthReport.getConsumePen()&&shopMonthReport.getConsumePen()!=0){
								double consumePen2 = shopMonthReport.getConsumePen();
								double a = (report.getConsumePen()-consumePen2)/consumePen2*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeConsumePen(bg);
							}
							if(null!=report.getAvgManFee()&&null!=shopMonthReport.getAvgManFee()&&shopMonthReport.getAvgManFee()!=0){
								double avgManFee = shopMonthReport.getAvgManFee();
								double a = (report.getAvgManFee()-avgManFee)/avgManFee*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeAvgManFee(bg);
							}
							if(null!=report.getSourcingFee()&&null!=shopMonthReport.getSourcingFee()&&shopMonthReport.getSourcingFee()!=0){
								double sourcingFee = shopMonthReport.getSourcingFee();
								double a = (report.getSourcingFee()-sourcingFee)/sourcingFee*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeSourcingFee(bg);
							}
							if(null!=report.getOrderNum()&&null!=shopMonthReport.getOrderNum()&&shopMonthReport.getOrderNum()!=0){
								double orderNum2 = shopMonthReport.getOrderNum();
								double a = (report.getOrderNum()-orderNum2)/orderNum2*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeOrderNum(bg);
							}
							if(null!=report.getLossPercent()&&null!=shopMonthReport.getLossPercent()&&shopMonthReport.getLossPercent()!=0){
								double lossPercent = shopMonthReport.getLossPercent();
								double a = (report.getLossPercent()-lossPercent)/lossPercent*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeLossPercent(bg);
							}

						}
						this.shopMonthReportService.insert(report);
					}
					shopMonthReportService.updateIsnew(date4);
				}
			}




				int YEAR = calendar.get(Calendar.YEAR);//获取年份
		        int MONTH =calendar.get(Calendar.MONTH);//获取月份
				AccountLock aLock=new AccountLock();
				aLock.setYears(YEAR+"");
				aLock.setMonths(MONTH+1+"");
				List<City> citys = this.cityService.findAllOpenCity();
				for (City city : citys) {
				    AccountLock accountLock=accountLockService.findisLockByCityId(aLock, date3, city.getId());
					if(accountLock==null){
						Date date5 = DateUtil.getLastDayOfMonth(date3);
						int day5 = DateUtil.getDay(date5);
						String month4 = dft.format(date5);
						Date date6 = dft2.parse(dft2.format(date5));
						Date endDate2 = dft4.parse(dft3.format(date5));
						calendar.add(Calendar.MONTH, -1);
						Date date7 = calendar.getTime();
						Date month5 = dft2.parse(dft2.format(date7));
						Date fdate2 = DateUtil.getFirstDayOfMonth(date6);
						List<CustomerUser> listshops = this.CustomerService.findOpenShopBeforeLastMonth(date6,city.getId());
						if(!listshops.isEmpty()){
							this.shopMonthReportService.deleteByDateAndCityId(date6,city.getId());
							for (CustomerUser customerUser : listshops) {
								ShopOrder shopOrder = this.shopOrderService.findMonthRecordByShopIdAndDate(customerUser.getId(),month4);

								if(null==shopOrder){
									Calendar cal1 = Calendar.getInstance();
									cal1.setTime(date6);
									Calendar cal2 = Calendar.getInstance();
									ShopMonthReport report = new ShopMonthReport();
									if (customerUser.getContractDate() != null) {
										cal2.setTime(customerUser.getContractDate());
										if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
											if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
												report.setIsnew(1);
											} else {
												report.setIsnew(0);
											}
										} else {
											report.setIsnew(0);
										}
									} else {
										report.setIsnew(1);
									}
									report.setSumDate(date6);
									report.setUserName(customerUser.getUserName());
									report.setUserId(customerUser.getId());
									report.setDiscount(customerUser.getShopDiscount());
									report.setConsumeFee(0l);
									report.setExpandFee(0l);
									report.setAvgFee(0l);
									report.setConsumeNum(0);
									report.setConsumePen(0);
									report.setAvgManFee(0l);
									report.setSourcingFee(0l);
									report.setOrderNum(0);
									report.setRefundNum(0);
									report.setLossPercent(0);
									TranConsume stock = this.tranConsumeService.findMonthStockByUserIdAndDate(customerUser.getId(),date6);
									if(null!=stock){
										report.setStock(stock.getGoodsMoney());
										report.setMonthFirstStock(stock.getMonthStockMoney());
									}
									ShopOrder order = this.shopOrderService.findProfitByShopIdAndDate(customerUser.getId(),fdate2,endDate2);
									report.setZhekoucaigou(order.getSourcing());
									report.setProfit(order.getExpend());
									if(order.getSourcing()>0){
										double aa = order.getExpend();
										double bb = order.getSourcing();
										double profitrate =aa/bb*100;
										BigDecimal bgg = new BigDecimal(profitrate).setScale(2,
												BigDecimal.ROUND_HALF_UP);
										report.setProfitRate(bgg);
									}
									report.setWastage(0l);
									report.setSubsidy(0l);
									ShopMonthReport shopMonthReport = this.shopMonthReportService.findByUserIdAndSumDate(customerUser.getId(),month5);
									if(null!=shopMonthReport){
										if(null!=report.getConsumeFee()&&null!=shopMonthReport.getConsumeFee()&&shopMonthReport.getConsumeFee()!=0){
											double consumeFee2 = shopMonthReport.getConsumeFee();
											double a = (report.getConsumeFee()-consumeFee2)/consumeFee2*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeExpandFee(bg);
										}
										if(null!=report.getAvgFee()&&null!=shopMonthReport.getAvgFee()&&shopMonthReport.getAvgFee()!=0){
											double avgFee = shopMonthReport.getAvgFee();
											double a = (report.getAvgFee()-avgFee)/avgFee*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeAvgFee(bg);
										}
										if(null!=report.getConsumeNum()&&null!=shopMonthReport.getConsumeNum()&&shopMonthReport.getConsumeNum()!=0){
											double consumeNum2 = shopMonthReport.getConsumeNum();
											double a = (report.getConsumeNum()-consumeNum2)/consumeNum2*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeConsumeNum(bg);
										}
										if(null!=report.getConsumePen()&&null!=shopMonthReport.getConsumePen()&&shopMonthReport.getConsumePen()!=0){
											double consumePen2 = shopMonthReport.getConsumePen();
											double a = (report.getConsumePen()-consumePen2)/consumePen2*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeConsumePen(bg);
										}
										if(null!=report.getAvgManFee()&&null!=shopMonthReport.getAvgManFee()&&shopMonthReport.getAvgManFee()!=0){
											double avgManFee = shopMonthReport.getAvgManFee();
											double a = (report.getAvgManFee()-avgManFee)/avgManFee*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeAvgManFee(bg);
										}
										if(null!=report.getSourcingFee()&&null!=shopMonthReport.getSourcingFee()&&shopMonthReport.getSourcingFee()!=0){
											double sourcingFee = shopMonthReport.getSourcingFee();
											double a = (report.getSourcingFee()-sourcingFee)/sourcingFee*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeSourcingFee(bg);
										}
										if(null!=report.getOrderNum()&&null!=shopMonthReport.getOrderNum()&&shopMonthReport.getOrderNum()!=0){
											double orderNum2 = shopMonthReport.getOrderNum();
											double a = (report.getOrderNum()-orderNum2)/orderNum2*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeOrderNum(bg);
										}
										if(null!=report.getLossPercent()&&null!=shopMonthReport.getLossPercent()&&shopMonthReport.getLossPercent()!=0){
											double lossPercent = shopMonthReport.getLossPercent();
											double a = (report.getLossPercent()-lossPercent)/lossPercent*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeLossPercent(bg);
										}

									}
									this.shopMonthReportService.insert(report);
								}else{
									Integer orderNum = this.orderService.findOrderNumByMonth(customerUser.getId(),date6);
									Integer refund = this.refundService.findRefundNumByMonth(customerUser.getId(),date6);
									ShopMonthReport report = new ShopMonthReport();
									//report.setRegion(customerUser.getRegion());
									report.setSumDate(date6);
									report.setUserName(customerUser.getUserName());
									report.setUserId(customerUser.getId());
									report.setDiscount(customerUser.getShopDiscount());
									report.setConsumeFee(shopOrder.getConsume());
									report.setExpandFee(shopOrder.getExpend());
									Date firstdate = this.shopOrderService.findFirstPayDay(customerUser.getId());
									if(null!=firstdate){
										Date firstDayOfMonth = DateUtil.getFirstDayOfMonth(date5);
										Date firstdate2 = dft2.parse(dft2.format(firstdate));
										long diff2 = DateUtil.diff(firstdate2,date6 , Calendar.HOUR);
										if(diff2>=0){
											if(firstdate.after(firstDayOfMonth)){
												int day2 = DateUtil.getDay(firstdate);
												int day3 = DateUtil.getDay(date6);
												int a = day3 - day2+1;
												report.setAvgFee(shopOrder.getConsume()/a);
											}else{
												report.setAvgFee(shopOrder.getConsume()/day5);
											}
										}else{
											continue;
										}
									}
									Integer consumeNum = this.shopOrderService.findConsumeNumByShopIdAndMonth(customerUser.getId(),date6);
									report.setConsumeNum(consumeNum);
									Integer consumepen = this.shopOrderService.findConsumePenByShopIdAndMonth(customerUser.getId(),date6);
									report.setConsumePen(consumepen);
									if(null!=report.getAvgFee()&&consumeNum>0){
										report.setAvgManFee(report.getAvgFee()/consumeNum);
									}
									report.setSourcingFee(shopOrder.getSourcing());
									report.setOrderNum(orderNum);
									report.setRefundNum(refund);
									int aaaa = 100-shopOrder.getStatus();
									if(aaaa<0 || aaaa>=100 ){
										report.setLossPercent(0);
									}else{
										report.setLossPercent(100-shopOrder.getStatus());
									}
									TranConsume stock = this.tranConsumeService.findMonthStockByUserIdAndDate(customerUser.getId(),date6);
									if(null!=stock){
										report.setStock(stock.getGoodsMoney());
										report.setMonthFirstStock(stock.getMonthStockMoney());
									}
									ShopOrder order = this.shopOrderService.findProfitByShopIdAndDate(customerUser.getId(),fdate2,endDate2);
									report.setZhekoucaigou(order.getSourcing());
									report.setProfit(order.getExpend());
									if(order.getSourcing()>0){
										double aa = order.getExpend();
										double bb = order.getSourcing();
										double profitrate =aa/bb*100;
										BigDecimal bgg = new BigDecimal(profitrate).setScale(2,
												BigDecimal.ROUND_HALF_UP);
										report.setProfitRate(bgg);
									}
									report.setWastage(shopOrder.getLoss()-shopOrder.getSubsidy());
									report.setSubsidy(shopOrder.getSubsidy());
									ShopMonthReport shopMonthReport = this.shopMonthReportService.findByUserIdAndSumDate(customerUser.getId(),month5);
									if(null!=shopMonthReport){
										if(null!=report.getConsumeFee()&&null!=shopMonthReport.getConsumeFee()&&shopMonthReport.getConsumeFee()!=0){
											double consumeFee2 = shopMonthReport.getConsumeFee();
											double a = (report.getConsumeFee()-consumeFee2)/consumeFee2*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeExpandFee(bg);
										}
										if(null!=report.getAvgFee()&&null!=shopMonthReport.getAvgFee()&&shopMonthReport.getAvgFee()!=0){
											double avgFee = shopMonthReport.getAvgFee();
											double a = (report.getAvgFee()-avgFee)/avgFee*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeAvgFee(bg);
										}
										if(null!=report.getConsumeNum()&&null!=shopMonthReport.getConsumeNum()&&shopMonthReport.getConsumeNum()!=0){
											double consumeNum2 = shopMonthReport.getConsumeNum();
											double a = (report.getConsumeNum()-consumeNum2)/consumeNum2*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeConsumeNum(bg);
										}
										if(null!=report.getConsumePen()&&null!=shopMonthReport.getConsumePen()&&shopMonthReport.getConsumePen()!=0){
											double consumePen2 = shopMonthReport.getConsumePen();
											double a = (report.getConsumePen()-consumePen2)/consumePen2*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeConsumePen(bg);
										}
										if(null!=report.getAvgManFee()&&null!=shopMonthReport.getAvgManFee()&&shopMonthReport.getAvgManFee()!=0){
											double avgManFee = shopMonthReport.getAvgManFee();
											double a = (report.getAvgManFee()-avgManFee)/avgManFee*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeAvgManFee(bg);
										}
										if(null!=report.getSourcingFee()&&null!=shopMonthReport.getSourcingFee()&&shopMonthReport.getSourcingFee()!=0){
											double sourcingFee = shopMonthReport.getSourcingFee();
											double a = (report.getSourcingFee()-sourcingFee)/sourcingFee*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeSourcingFee(bg);
										}
										if(null!=report.getOrderNum()&&null!=shopMonthReport.getOrderNum()&&shopMonthReport.getOrderNum()!=0){
											double orderNum2 = shopMonthReport.getOrderNum();
											double a = (report.getOrderNum()-orderNum2)/orderNum2*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeOrderNum(bg);
										}
										if(null!=report.getLossPercent()&&null!=shopMonthReport.getLossPercent()&&shopMonthReport.getLossPercent()!=0){
											double lossPercent = shopMonthReport.getLossPercent();
											double a = (report.getLossPercent()-lossPercent)/lossPercent*100;
											BigDecimal bg = new BigDecimal(a).setScale(2,
													BigDecimal.ROUND_HALF_UP);
											report.setBeforeLossPercent(bg);
										}

									}
									this.shopMonthReportService.insert(report);
								}
							}
					}
						shopMonthReportService.updateIsnew(date6);
					}
				}



		} catch (ParseException e) {
			logger.error("生成便利店单店月报数据错误", e);
		}
		logger.warn("生成便利店单店月报数据结束.................................");
	}

	 //福利店单店月报
		public void workFreeShopMonthReport(){
			logger.warn("开始生成福利店单店月报数据.................................");
			try {
				List<CustomerUser> shops = this.CustomerService.findFreeShops();
				SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
				SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -1);
				Date date = calendar.getTime();
				Date date1 = dft2.parse(dft2.format(date));
				Date date2 = DateUtil.getFirstDayOfMonth(date1);
				calendar.add(Calendar.MONTH, -1);
				Date date3 = calendar.getTime();
				Date date4 = dft2.parse(dft2.format(date3));
				if(!shops.isEmpty()){
					this.freeShopMonthReportService.deleteByDate(date1);
					for (CustomerUser customerUser : shops) {
						FreeShopMonthReport report = new FreeShopMonthReport();
						Calendar cal1 = Calendar.getInstance();
						cal1.setTime(date1);
						Calendar cal2 = Calendar.getInstance();
						if (customerUser.getContractDate() != null) {
							cal2.setTime(customerUser.getContractDate());
							if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
								if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
									report.setIsnew(1);
								} else {
									report.setIsnew(0);
								}
							} else {
								report.setIsnew(0);
							}
						} else {
							report.setIsnew(1);
						}
						report.setUserName(customerUser.getUserName());
						//report.setRegion(customerUser.getRegion());
						report.setShopId(customerUser.getId());
						report.setSumDate(date1);
						Order order = this.orderService.findCurrentMonthFreeShopOrderFee(customerUser.getId(),date2,date1);
						report.setOrderFee(order.getTotalFee());
						report.setOrderNum(order.getTotalNum());
						Refund refund = this.refundService.findCurrentMonthFreeShopRefundFee(customerUser.getId(),date2,date1);
						report.setRefundFee(refund.getTotalFee());
						//LastModUser 退货笔数
						report.setRefundNum(refund.getLastModUser());
						report.setSoucingFee(order.getTotalFee()-refund.getTotalFee());
						report.setAvgDayFee((order.getTotalFee()-refund.getTotalFee())/30);

						Order ddd = this.orderService.findProfitByUserIdAndDate(customerUser.getId(),date2,date1);
						report.setZhekoucaigou(ddd.getTotalCost());
						report.setProfit(ddd.getTotalFee());
						if(ddd.getTotalCost()>0){
							double aa = ddd.getTotalFee();
							double bb = ddd.getTotalCost();
							double profitrate =aa/bb*100;
							BigDecimal bgg = new BigDecimal(profitrate).setScale(2,
									BigDecimal.ROUND_HALF_UP);
							report.setProfitRate(bgg);
						}

						FreeShopMonthReport freeShopMonthReport = this.freeShopMonthReportService.findByUserIdAndSumDate(customerUser.getId(),date4);
						if(null!=freeShopMonthReport){
							if(null!=report.getSoucingFee()&&null!=freeShopMonthReport.getSoucingFee()&&freeShopMonthReport.getSoucingFee()!=0){
								double consumeFee2 = freeShopMonthReport.getSoucingFee();
								double a = (report.getSoucingFee()-consumeFee2)/consumeFee2*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeSoucingFee(bg);
							}
						}
						this.freeShopMonthReportService.insert(report);
					}
				}

				int YEAR = calendar.get(Calendar.YEAR);//获取年份
		        int MONTH =calendar.get(Calendar.MONTH);//获取月份
				AccountLock aLock=new AccountLock();
				aLock.setYears(YEAR+"");
				aLock.setMonths(MONTH+1+"");
				List<City> citys = this.cityService.findAllOpenCity();
				for (City city : citys) {

					AccountLock accountLock=accountLockService.findisLockByCityId(aLock, date3, city.getId());
					if(accountLock==null){
					Date date5 = DateUtil.getLastDayOfMonth(date3);
					Date date6 = dft2.parse(dft2.format(date5));
					Date date9 = DateUtil.getFirstDayOfMonth(date3);
					calendar.add(Calendar.MONTH, -1);
					Date date7 = calendar.getTime();
					Date date8 = dft2.parse(dft2.format(date7));
					List<CustomerUser> freeshops = this.CustomerService.findOpenFreeShopBeforeLastMonth(date6, city.getId());
					if(!freeshops.isEmpty()){
						this.freeShopMonthReportService.deleteByDateAndCityId(date6,city.getId());
						for (CustomerUser customerUser : freeshops) {
							FreeShopMonthReport report = new FreeShopMonthReport();
							Calendar cal1 = Calendar.getInstance();
							cal1.setTime(date6);
							Calendar cal2 = Calendar.getInstance();
							if (customerUser.getContractDate() != null) {
								cal2.setTime(customerUser.getContractDate());
								if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
									if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
										report.setIsnew(1);
									} else {
										report.setIsnew(0);
									}
								} else {
									report.setIsnew(0);
								}
							} else {
								report.setIsnew(1);
							}

							report.setUserName(customerUser.getUserName());
							//report.setRegion(customerUser.getRegion());
							report.setShopId(customerUser.getId());
							report.setSumDate(date6);
							Order order = this.orderService.findCurrentMonthFreeShopOrderFee(customerUser.getId(),date9,date6);
							report.setOrderFee(order.getTotalFee());
							report.setOrderNum(order.getTotalNum());
							Refund refund = this.refundService.findCurrentMonthFreeShopRefundFee(customerUser.getId(),date9,date6);
							report.setRefundFee(refund.getTotalFee());
							//LastModUser 退货笔数
							report.setRefundNum(refund.getLastModUser());
							report.setSoucingFee(order.getTotalFee()-refund.getTotalFee());
							report.setAvgDayFee((order.getTotalFee()-refund.getTotalFee())/30);
							Order ddd = this.orderService.findProfitByUserIdAndDate(customerUser.getId(),date9,date6);
							report.setZhekoucaigou(ddd.getTotalCost());
							report.setProfit(ddd.getTotalFee());
							if(ddd.getTotalCost()>0){
								double aa = ddd.getTotalFee();
								double bb = ddd.getTotalCost();
								double profitrate =aa/bb*100;
								BigDecimal bgg = new BigDecimal(profitrate).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setProfitRate(bgg);
							}
							FreeShopMonthReport freeShopMonthReport = this.freeShopMonthReportService.findByUserIdAndSumDate(customerUser.getId(),date8);
							if(null!=freeShopMonthReport){
								if(null!=report.getSoucingFee()&&null!=freeShopMonthReport.getSoucingFee()&&freeShopMonthReport.getSoucingFee()!=0){
									double consumeFee2 = freeShopMonthReport.getSoucingFee();
									double a = (report.getSoucingFee()-consumeFee2)/consumeFee2*100;
									BigDecimal bg = new BigDecimal(a).setScale(2,
											BigDecimal.ROUND_HALF_UP);
									report.setBeforeSoucingFee(bg);
								}
							}
							this.freeShopMonthReportService.insert(report);
						}
					}
					}
				}
			} catch (ParseException e) {
				logger.error("生成福利店单店月报数据错误", e);
			}
			logger.warn("生成福利店单店月报数据结束.................................");
		}

		public void workFreeShopDayReport(){
			logger.warn("开始生成福利店单店日报数据.................................");
			try {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -1);
				Date date = calendar.getTime();
				List<CustomerUser> shops = this.CustomerService.findFreeShops();
				for (CustomerUser customerUser : shops) {
					FreeShopDailyReport report = new FreeShopDailyReport();
					report.setShopId(customerUser.getId());
					report.setShopName(customerUser.getUserName());
					report.setSumDate(date);
					Order order = this.orderService.findOrderFeeAndOrderNumByDayAndUserId(customerUser.getId(), date);
					report.setOrderFee(order.getTotalFee());
					report.setOrderNum(order.getTotalNum());
					Refund refund = this.refundService.findRefundFeeAndRefundNumByDayAndUserId(customerUser.getId(),date);
					report.setRefundFee(refund.getTotalFee());
					report.setRefundNum(refund.getLastModUser());
					report.setSourcingFee(order.getTotalFee()-refund.getTotalFee());
					this.freeShopDailyReportService.insert(report);
				}
			} catch (Exception e) {
				logger.error("生成福利店单店日报数据错误", e);
			}
			logger.warn("生成福利店单店日报数据结束.................................");
		}


		public void workjifen(){
			logger.warn("开始统计积分.................................");
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				List<CustomerUser> users = this.CustomerService.findAll();
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);
				Date date = calendar.getTime();
				String month = dft.format(date);
				Date date1 = dft2.parse(dft2.format(date));
				Date date2 = DateUtil.getFirstDayOfMonth(date1);
				Date date3 = DateUtil.getLastDayOfMonth(date1);
				for (CustomerUser customerUser : users) {
					if(3==customerUser.getIswxvip()){
						ShopOrder shopOrder = this.shopOrderService.findMonthRecordByShopIdAndDate(customerUser.getId(),month);
						if(null==shopOrder){
							continue;
						}
						Long money = 0l;
						if(customerUser.getPayBillWay()==1 || customerUser.getPayBillWay()==4){
							money = shopOrder.getExpend()-(shopOrder.getLoss()+shopOrder.getSubsidy());
						}else{
							money = shopOrder.getExpend();
						}
						double mul = 1;
						int loss = 100-shopOrder.getStatus();
						if(loss<5){
							mul = 1.2;
						}else if(5<=loss && loss<10){
							mul = 1;
						}else{
							mul = 0.8;
						}
						double c = money*mul/100;
						long d = Math.round(c);
						MemberPointReport  memberPointReport = new MemberPointReport();
						memberPointReport.setMultiple(mul);
						memberPointReport.setType(1);
						memberPointReport.setSumDate(new Date());
						memberPointReport.setCreatedTime(new Date());
						memberPointReport.setShopId(customerUser.getId());
						memberPointReport.setPoint(d);
						memberPointReport.setStatus(1);
						this.memberPointReportService.save(memberPointReport);
					}else{
						Order order = this.orderService.findCurrentMonthFreeShopOrderFee(customerUser.getId(),date2,date3);
						Refund refund = this.refundService.findCurrentMonthFreeShopRefundFee(customerUser.getId(),date2,date3);
						long money = order.getTotalFee()-refund.getTotalFee();
						if(money==0){
							continue;
						}
						double c = money/100;
						long d = Math.round(c);
						MemberPointReport  memberPointReport = new MemberPointReport();
						memberPointReport.setMultiple(1.0);
						memberPointReport.setType(1);
						memberPointReport.setSumDate(new Date());
						memberPointReport.setCreatedTime(new Date());
						memberPointReport.setShopId(customerUser.getId());
						memberPointReport.setPoint(d);
						memberPointReport.setStatus(1);
						this.memberPointReportService.save(memberPointReport);
					}
				}
			} catch (ParseException e) {
				logger.error("统计积分数据错误", e);
			}
			logger.warn("统计积分数据结束.................................");
		}

		//弃用
		public void workSalemanMoney(){
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			Date date1 = calendar.getTime();
			calendar.add(Calendar.MONTH, -1);
			Date date3 = calendar.getTime();
			Date date2 = DateUtil.getLastDayOfMonth(date3);
			int YEAR = calendar.get(Calendar.YEAR);//获取年份
	        int MONTH =calendar.get(Calendar.MONTH);//获取月份
			List<City> citys = this.cityService.findAllOpenCity();
			for (City city : citys) {
				List<CustomerUser> users= this.CustomerService.findtichengUserByCityId(city.getId());
				//本月
				this.shopSalesService.deleteBySumDateAndCityId(city.getId(),date1);
				for (CustomerUser user : users) {
					if(user.getStatus()>3){
						continue;
					}
					Long base = 0l;
					if(user.getIswxvip()==3){
						//in (1831,1834,1836,1837,1839,1841,1842,1845,1846,1847,1852,1853,1854,1855,1856,1857,1858,1859,1861,1908,1918,1928,1931,1932)
						ShopMonthReport shopMonthReport = this.shopMonthReportService.findByShopIdAndDate(user.getId(), date1);
						if(shopMonthReport==null){
							continue;
						}
						if(user.getPayBillWay()==2 || user.getPayBillWay()==3){
							base = shopMonthReport.getExpandFee();
						}else{
							base = shopMonthReport.getExpandFee() - shopMonthReport.getWastage();
						}
					}else{
						FreeShopMonthReport report = this.freeShopMonthReportService.findByUserIdAndSumDate(user.getId(), date1);
						if(report==null){
							continue;
						}
						base = report.getSoucingFee();
					}

					if(user.getStatus()==3 && user.getContractNumber()==null){
						continue;
					}
					double proportion = 0;
					if(user.getPayBillWay()==2 || user.getPayBillWay()==3){
							proportion = 0.1;
					}else if (user.getPayBillWay()==1) {
						if(user.getStatus()==1){
							proportion = 0.1;
						}else{
							proportion = 0.05;
						}
					}else{
						if(user.getStatus()==1){
							proportion = 0.1;
						}else{
							proportion = 0;
						}
					}
					BigDecimal xishu = user.getRoyaltyCoefficient();
					long fee = (long) (base*proportion*xishu.doubleValue());
					ShopSales sales = new ShopSales();
					sales.setContractDate(user.getContractDate());
					sales.setContractNumber(user.getContractNumber());
					sales.setPayBillWay(user.getPayBillWay());
					sales.setRoyaltyCoefficient(xishu);
					sales.setShopId(user.getId());
					sales.setSumBase(base);
					sales.setSumFee(fee);
					sales.setSumProportion(new BigDecimal(proportion));
					sales.setUserName(user.getUserName());
					sales.setCityId(city.getId());
					sales.setSumDate(date1);
					this.shopSalesService.insert(sales);
				}

				//上月
				AccountLock aLock=new AccountLock();
				aLock.setYears(YEAR+"");
				aLock.setMonths(MONTH+1+"");
				AccountLock accountLock=accountLockService.findisLockByCityId(aLock, date3, city.getId());
				if(accountLock==null){
					this.shopSalesService.deleteBySumDateAndCityId(city.getId(),date2);
					for (CustomerUser user : users) {
						if(user.getStatus()<2){
							continue;
						}
						Long base = 0l;
						if(user.getIswxvip()==3){
							ShopMonthReport shopMonthReport = this.shopMonthReportService.findByShopIdAndDate(user.getId(), date2);
							if(user.getPayBillWay()==2 || user.getPayBillWay()==3){
								base = shopMonthReport.getExpandFee();
							}else{
								base = shopMonthReport.getExpandFee() - shopMonthReport.getWastage();
							}
						}else{
							FreeShopMonthReport report = this.freeShopMonthReportService.findByUserIdAndSumDate(user.getId(), date2);
							base = report.getSoucingFee();
						}

						if(user.getStatus()==4 && user.getContractNumber()==null){
							continue;
						}
						double proportion = 0;
						if(user.getPayBillWay()==2 || user.getPayBillWay()==3){
								proportion = 0.1;
						}else if (user.getPayBillWay()==1) {
							if(user.getStatus()==2){
								proportion = 0.1;
							}else{
								proportion = 0.05;
							}
						}else{
							if(user.getStatus()==2){
								proportion = 0.1;
							}else{
								proportion = 0;
							}
						}
						BigDecimal xishu = user.getRoyaltyCoefficient();
						long fee = (long) (base*proportion*xishu.doubleValue());
						ShopSales sales = new ShopSales();
						sales.setContractDate(user.getContractDate());
						sales.setContractNumber(user.getContractNumber());
						sales.setPayBillWay(user.getPayBillWay());
						sales.setRoyaltyCoefficient(xishu);
						sales.setShopId(user.getId());
						sales.setSumBase(base);
						sales.setSumFee(fee);
						sales.setSumProportion(new BigDecimal(proportion));
						sales.setUserName(user.getUserName());
						sales.setCityId(city.getId());
						sales.setSumDate(date2);
						this.shopSalesService.insert(sales);
					}
				}
			}
		}



//******************************************************************
		public void workFreeShopDayReport1(){
			logger.warn("开始生成福利店单店日报数据.................................");
			try {
				String aaa = "2017-05-02";
				String bbb = "2017-05-19";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    Date date5 = sdf.parse(aaa);
			    Date date6 = sdf.parse(bbb);
				for (int i = 0; i < 1000; i++) {
					Date	date = DateUtil.dateAdd("d", i, date5);
					if(date.after(date6)){
						break;
					}
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						calendar.add(Calendar.DATE, -1);
						date = calendar.getTime();
						List<CustomerUser> shops = this.CustomerService.findFreeShops();
						for (CustomerUser customerUser : shops) {
							FreeShopDailyReport report = new FreeShopDailyReport();
							report.setShopId(customerUser.getId());
							report.setShopName(customerUser.getUserName());
							report.setSumDate(date);
							Order order = this.orderService.findOrderFeeAndOrderNumByDayAndUserId(customerUser.getId(), date);
							report.setOrderFee(order.getTotalFee());
							report.setOrderNum(order.getTotalNum());
							Refund refund = this.refundService.findRefundFeeAndRefundNumByDayAndUserId(customerUser.getId(),date);
							report.setRefundFee(refund.getTotalFee());
							report.setRefundNum(refund.getLastModUser());
							report.setSourcingFee(order.getTotalFee()-refund.getTotalFee());
							this.freeShopDailyReportService.insert(report);
						}
				}
			} catch (Exception e) {
				logger.error("生成福利店单店日报数据错误", e);
			}
			logger.warn("生成福利店单店日报数据结束.................................");
		}

		public void workFreeShopDayReport2(String month){
			logger.warn("开始生成福利店单店日报数据.................................");
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    Date date = sdf.parse(month);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						calendar.add(Calendar.DATE, -1);
						date = calendar.getTime();
						List<CustomerUser> shops = this.CustomerService.findFreeShops();
						for (CustomerUser customerUser : shops) {
							FreeShopDailyReport report = new FreeShopDailyReport();
							report.setShopId(customerUser.getId());
							report.setShopName(customerUser.getUserName());
							report.setSumDate(date);
							Order order = this.orderService.findOrderFeeAndOrderNumByDayAndUserId(customerUser.getId(), date);
							report.setOrderFee(order.getTotalFee());
							report.setOrderNum(order.getTotalNum());
							Refund refund = this.refundService.findRefundFeeAndRefundNumByDayAndUserId(customerUser.getId(),date);
							report.setRefundFee(refund.getTotalFee());
							report.setRefundNum(refund.getLastModUser());
							report.setSourcingFee(order.getTotalFee()-refund.getTotalFee());
							this.freeShopDailyReportService.insert(report);
						}
			} catch (Exception e) {
				logger.error("生成福利店单店日报数据错误", e);
			}
			logger.warn("生成福利店单店日报数据结束.................................");
		}

		public void workShopDailyReport1(){
		logger.warn("开始便利店单店生成日报数据.................................");
		try {
		String aaa = "2016-07-13";
		String bbb = "2016-10-26";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date date5 = sdf.parse(aaa);
	    Date date6 = sdf.parse(bbb);
		for (int i = 0; i < 1000; i++) {
			Date	date = DateUtil.dateAdd("d", i, date5);
			if(date.after(date6)){
				break;
			}
				SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
				SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat dft3 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
				SimpleDateFormat dft4 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, -1);
				date = calendar.getTime();
				calendar.add(Calendar.DATE, -7);
				Date date2 = calendar.getTime();
				Date startDate = dft2.parse(dft3.format(date));
				Date endDate = dft2.parse(dft.format(date));
				Date queryDate = dft4.parse(dft4.format(date2));
				List<HashMap<String,Object>> lists = this.CustomerService.findAllShop();
				if(!lists.isEmpty()){
					for (HashMap<String, Object> shop : lists) {
						Integer id = Integer.parseInt(shop.get("id").toString());
						ShopOrder shopOrder = this.shopOrderService.findByShopIdAndDate(id,startDate,endDate);
						ShopDailyReport shopDailyReport = this.shopDailyReportService.findByShopIdAndSumdate(id,queryDate);
						ShopDailyReport report = new ShopDailyReport();
						report.setShopId(id);
						report.setSumdate(date);
						report.setDiscount(new BigDecimal(shop.get("shop_discount").toString()));
						report.setShopName(shop.get("user_name").toString());
						report.setRegion(shop.get("region").toString());
						report.setTotalConsume(shopOrder.getTotalPrice());
						report.setTotalExpend(shopOrder.getActualPrice());
						report.setConsumePersonNum(shopOrder.getShopId());
						report.setConsumePen(shopOrder.getBuyerId());
						if (shopOrder.getShopId()>0) {
							report.setAvgExpend(shopOrder.getTotalPrice()/shopOrder.getShopId());
						}else{
							report.setAvgExpend(0l);
						}
						if(null!=shopDailyReport){
							Integer num = shopDailyReport.getConsumePersonNum();
							report.setBeforeLastWeekNum(report.getConsumePersonNum()-num);
							if(null!=shopDailyReport.getTotalConsume()&&shopDailyReport.getTotalConsume()!=0){
								double consume = shopDailyReport.getTotalConsume();
								double a = (report.getTotalConsume()-consume)*100/consume;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeLastWeekExpand(bg);
							}
							if(null!=shopDailyReport.getAvgExpend()&&shopDailyReport.getAvgExpend()!=0){
								double avg =shopDailyReport.getAvgExpend();
								double b = (report.getAvgExpend()-avg)*100/avg;
								BigDecimal bg = new BigDecimal(b).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeLastWeekAvg(bg);
							}
						}
						this.shopDailyReportService.createBySumBatch(report);
					}
				}
		}
		} catch (Exception e) {
			logger.error("生成便利店单店日报数据错误", e);
		}
		logger.warn("生成便利店单店日报数据结束.................................");
	}

		public void workFreeShopMonthReport1(){
			logger.warn("开始生成福利店单店月报数据.................................");
			try {
				String bbb = "2017-05-01";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			    Date datebbb = sdf.parse(bbb);
				List<CustomerUser> shops = this.CustomerService.findFreeShops();
				SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
				SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(datebbb);
				calendar.add(Calendar.DATE, -1);
				Date date = calendar.getTime();
				Date date1 = dft2.parse(dft2.format(date));
				Date date2 = DateUtil.getFirstDayOfMonth(date1);
				calendar.add(Calendar.MONTH, -1);
				Date date3 = calendar.getTime();
				Date date4 = dft2.parse(dft2.format(date3));
				if(!shops.isEmpty()){
					this.freeShopMonthReportService.deleteByDate(date1);
					for (CustomerUser customerUser : shops) {
						FreeShopMonthReport report = new FreeShopMonthReport();
						report.setUserName(customerUser.getUserName());
						//report.setRegion(customerUser.getRegion());
						report.setShopId(customerUser.getId());
						report.setSumDate(date1);
						Order order = this.orderService.findCurrentMonthFreeShopOrderFee(customerUser.getId(),date2,date1);
						report.setOrderFee(order.getTotalFee());
						report.setOrderNum(order.getTotalNum());
						Refund refund = this.refundService.findCurrentMonthFreeShopRefundFee(customerUser.getId(),date2,date1);
						report.setRefundFee(refund.getTotalFee());
						//LastModUser 退货笔数
						report.setRefundNum(refund.getLastModUser());
						report.setSoucingFee(order.getTotalFee()-refund.getTotalFee());
						report.setAvgDayFee((order.getTotalFee()-refund.getTotalFee())/30);

						Order ddd = this.orderService.findProfitByUserIdAndDate(customerUser.getId(),date2,date1);
						report.setZhekoucaigou(ddd.getTotalCost());
						report.setProfit(ddd.getTotalFee());
						if(ddd.getTotalCost()>0){
							double aa = ddd.getTotalFee();
							double bb = ddd.getTotalCost();
							double profitrate =aa/bb*100;
							BigDecimal bgg = new BigDecimal(profitrate).setScale(2,
									BigDecimal.ROUND_HALF_UP);
							report.setProfitRate(bgg);
						}

						FreeShopMonthReport freeShopMonthReport = this.freeShopMonthReportService.findByUserIdAndSumDate(customerUser.getId(),date4);
						if(null!=freeShopMonthReport){
							if(null!=report.getSoucingFee()&&null!=freeShopMonthReport.getSoucingFee()&&freeShopMonthReport.getSoucingFee()!=0){
								double consumeFee2 = freeShopMonthReport.getSoucingFee();
								double a = (report.getSoucingFee()-consumeFee2)/consumeFee2*100;
								BigDecimal bg = new BigDecimal(a).setScale(2,
										BigDecimal.ROUND_HALF_UP);
								report.setBeforeSoucingFee(bg);
							}
						}
						this.freeShopMonthReportService.insert(report);
					}
				}



				//}
			} catch (ParseException e) {
				logger.error("生成福利店单店月报数据错误", e);
			}
			logger.warn("生成福利店单店月报数据结束.................................");
		}

	//生成商品店铺月报
	public void workShopItemMonthReport(){
		logger.warn("开始生成商品店铺月报数据.................................");
		try {
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
			Date date = calendar.getTime();
			Date date1 = dft.parse(dft.format(date));
			calendar.add(Calendar.MONTH, -1);
			Date date2 = calendar.getTime();
			List<CustomerUser> shops = this.CustomerService.findShops();
			if(!shops.isEmpty()){
				this.shopItemMonthReportService.deleteByDateAndCityId(date1, null);
				List<ShopItemMonthReport> shopItemMonthList = this.shopItemMonthReportService.findShopItems(date1, null);
				for(ShopItemMonthReport report : shopItemMonthList)
					this.shopItemMonthReportService.insert(report);
//				insertShopItemMonthRepor(shopItemMonthList, date1);
			}
			int YEAR = calendar.get(Calendar.YEAR);//获取年份
			int MONTH =calendar.get(Calendar.MONTH);//获取月份
			AccountLock aLock=new AccountLock();
			aLock.setYears(YEAR+"");
			aLock.setMonths(MONTH+1+"");
			List<City> citys = this.cityService.findAllOpenCity();
			for (City city : citys) {
				AccountLock accountLock = accountLockService.findisLockByCityId(aLock, date2, city.getId());
				if(accountLock == null){
					Date date4 = DateUtil.getLastDayOfMonth(date2);
					Date date5 = dft.parse(dft.format(date4));
					calendar.add(Calendar.MONTH, -1);
					List<CustomerUser> listshops = this.CustomerService.findOpenShopBeforeLastMonth(date5,city.getId());
					if(!listshops.isEmpty()){
						List<ShopItemMonthReport> shopItemMonthList = this.shopItemMonthReportService.findShopItems(date5, city.getId());
						for (ShopItemMonthReport report : shopItemMonthList) {
							this.shopItemMonthReportService.deleteByDateAndCityId(date5, city.getId());
							this.shopItemMonthReportService.insert(report);
						}
					}
				}
			}

		} catch (ParseException e) {
			logger.error("生成商品店铺月报数据错误", e);
		}
		logger.warn("生成商品店铺月报数据结束.................................");
	}

	//生成商品报表日报
	public void workItemDailyReport(){
		logger.warn("开始生成商品日报数据.................................");
		try {
			Date date = new Date();
			SimpleDateFormat dft2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dft3 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
			Date startDate = dft2.parse(dft3.format(date));
			List<ItemDailyReport> reports = this.itemDailyReportService.findAllItems(startDate);
			for (ItemDailyReport report : reports) {
				this.itemDailyReportService.insert(report);
			}
		} catch (Exception e) {
			logger.error("生成商品日报数据错误", e);
		}
		logger.warn("生成商品日报数据结束.................................");
	}

	//生成商品报表周报
	public void workItemWeekReport(){
		logger.warn("开始生成商品周报数据.................................");
		try {
			Date date = new Date();
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			SimpleDateFormat dft3 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -1);
			date = calendar.getTime();
			calendar.add(Calendar.DATE, -7);
			int d = 0;
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
				d = -6;
			} else {
				d = 2 - cal.get(Calendar.DAY_OF_WEEK);
			}
			cal.add(Calendar.DAY_OF_WEEK, d);
			// 所在周开始日期
			Date startDate = dft3.parse(dft3.format(cal.getTime()));
			cal.add(Calendar.DAY_OF_WEEK, 6);
			// 所在周结束日期
			Date endDate = dft.parse(dft.format(cal.getTime()));
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(date);
			Integer weekth = calendar.get(Calendar.WEEK_OF_YEAR);

			//获取基本信息
			List<ItemWeekReport> reports = this.itemWeekReportService.findAllItems(startDate, endDate, weekth);

			if(!reports.isEmpty()){
				this.itemWeekReportService.deleteByDate(startDate, weekth);
				for (ItemWeekReport report : reports) {
					this.itemWeekReportService.insert(report);
				}
			}
		} catch (Exception e) {
			logger.error("生成商品周报数据错误", e);
		}
		logger.warn("生成商品周报数据结束.................................");
	}

	//生成商品月报
	public void workItemMonthReport(){
		logger.warn("开始生成商品月报数据.................................");
		try {
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
			Date date = calendar.getTime();
			Date date1 = dft.parse(dft.format(date));
			calendar.add(Calendar.MONTH, -1);
			Date date2 = calendar.getTime();
			List<ItemMonthReport> reports = this.itemMonthReportService.findAllItems(date1, null);
			if(!reports.isEmpty()){
				this.itemMonthReportService.deleteByDate(date1, null);
				insertItemMonthRepor(reports);
			}
			int YEAR = calendar.get(Calendar.YEAR);//获取年份
			int MONTH =calendar.get(Calendar.MONTH);//获取月份
			AccountLock aLock=new AccountLock();
			aLock.setYears(YEAR+"");
			aLock.setMonths(MONTH+1+"");
			List<City> citys = this.cityService.findAllOpenCity();
			for (City city : citys) {
				AccountLock accountLock = accountLockService.findisLockByCityId(aLock, date2, city.getId());
				if(accountLock == null){
					Date date4 = DateUtil.getLastDayOfMonth(date2);
					Date date5 = dft.parse(dft.format(date4));
					calendar.add(Calendar.MONTH, -1);
					List<ItemMonthReport> reports2 = this.itemMonthReportService.findAllItems(date5, city.getId());
					if(!reports2.isEmpty()){
						this.itemMonthReportService.deleteByDate(date5, city.getId());
						insertItemMonthRepor(reports2);
					}
				}
			}

		} catch (ParseException e) {
			logger.error("生成商品月报数据错误", e);
		}
		logger.warn("生成商品月报数据结束.................................");
	}


	//生成商品月报
	public void workOldItemMonthReport(){
		logger.warn("开始生成老商品月报数据.................................");
		try {

			Date startDate = new SimpleDateFormat("yyyy-MM").parse("2016-01");
			Date endDate = new SimpleDateFormat("yyyy-MM").parse("2017-07");

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			while (calendar.getTime().before(endDate)) {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				Date date = sdf.parse(sdf.format(calendar.getTime()));
				List<ItemMonthReport> reports = this.itemMonthReportService.findAllItems(date, null);
				if(!reports.isEmpty()){
					this.itemMonthReportService.deleteByDate(date, null);
					insertItemMonthRepor(reports);
				}
				calendar.add(Calendar.MONTH, 1);//进行当前日期月份加1
			}

		} catch (ParseException e) {
			logger.error("生成老商品月报数据错误", e);
		}
		logger.warn("生成老商品月报数据结束.................................");
	}


	private void insertItemMonthRepor(List<ItemMonthReport> reports) {
		for (ItemMonthReport report : reports) {
			this.itemMonthReportService.insert(report);
		}
	}

	//付款列表数据生成
	public void workPayment() {
		logger.warn("开始付款列表数据.................................");
		try {
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
			Date date = calendar.getTime();
			Date date1 = dft.parse(dft.format(date));
			calendar.add(Calendar.MONTH, -1);
			Date date2 = calendar.getTime();
			List<Payment> payments = this.paymentService.findAll(date1, null);
			if(!payments.isEmpty()){
				this.paymentService.deleteByDate(date1, null);
				for (Payment payment : payments) {
					this.paymentService.insert(payment);
				}
			}
			int YEAR = calendar.get(Calendar.YEAR);//获取年份
			int MONTH =calendar.get(Calendar.MONTH);//获取月份
			PaymentAccountLock aLock=new PaymentAccountLock();
			aLock.setYears(YEAR+"");
			aLock.setMonths(MONTH+1+"");
			List<City> citys = this.cityService.findAllOpenCity();
			for (City city : citys) {
				PaymentAccountLock accountLock = paymentAccountLockService.findisLockByCityId(aLock, date2, city.getId());
				if(accountLock == null){
					Date date4 = DateUtil.getLastDayOfMonth(date2);
					Date date5 = dft.parse(dft.format(date4));
					calendar.add(Calendar.MONTH, -1);
					List<Payment> payments2 = this.paymentService.findAll(date5, city.getId());
					if(!payments2.isEmpty()){
						this.paymentService.deleteByDate(date5, city.getId());
						for (Payment payment : payments2) {
							this.paymentService.insert(payment);
						}
					}
				}
			}


		} catch (Exception e) {
			logger.error("生成付款列表数据错误", e);
		}
		logger.warn("生成付款列表数据结束.................................");
	}

	//客户自动分级
	public void atuoChangeGrade() {
		logger.warn("开始客户自动分级.................................");
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Date dateStart = calendar.getTime();
			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(Calendar.DAY_OF_MONTH, 0);
			calendar2.set(Calendar.HOUR_OF_DAY, 23);
			calendar2.set(Calendar.MINUTE, 59);
			calendar2.set(Calendar.SECOND, 59);
			calendar2.set(Calendar.MILLISECOND, 999);
			Date dateEnd = calendar2.getTime();

			List<CustomerUser> customerUsers = CustomerService.findAutoChangeGrade(dateStart, dateEnd);
			if (customerUsers != null) {
				for (CustomerUser customerUser : customerUsers) {
					CustomerService.updateByPrimaryKeySelective(customerUser);
				}
			}

		} catch (Exception e) {
			logger.error("客户自动分级错误", e);
		}
		logger.warn("开始客户自动结束.................................");
	}

	//计算商品周消耗量
	public void getItemWeekVolume() {
		logger.warn("开始计算商品周消耗量.................................");
		try {
			List<ItemWeekVolume> list = this.itemWeekVolumeService.findVolume();

			itemWeekVolumeService.deleteAll();
			for (ItemWeekVolume itemWeekVolume : list) {
				this.itemWeekVolumeService.insert(itemWeekVolume);
			}
		} catch (Exception e) {
			logger.error("计算商品周消耗量错误", e);
		}
		logger.warn("计算商品周消耗量结束.................................");
	}

}
