package com.b2b.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.TranSumMapper;
import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.BaseTranDetail;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.DayShopStock;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.Refund;
import com.b2b.common.domain.ShopOrder;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.TranConsume;
import com.b2b.common.domain.TranSum;
import com.b2b.common.domain.TranSumExample;
import com.b2b.common.domain.TranSumExample.Criteria;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.util.DateUtil;
import com.b2b.common.util.EncryptHelper;
import com.b2b.dto.TranSumDto;
import com.b2b.job.GenTranSumJob;
import com.b2b.page.Page;
import com.b2b.service.AccountLockService;
import com.b2b.service.CustomerService;
import com.b2b.service.DayShopStockService;
import com.b2b.service.LogService;
import com.b2b.service.OrderService;
import com.b2b.service.RefundService;
import com.b2b.service.ShopItemService;
import com.b2b.service.ShopOrderService;
import com.b2b.service.TranConsumeService;
import com.b2b.service.TranSumService;
import com.b2b.service.UserService;
import com.google.common.collect.Lists;

@Service
public class TranSumServiceImpl implements TranSumService {
	private static final Logger logger = LoggerFactory
			.getLogger(TranSumServiceImpl.class);
	
	@Autowired
	TranSumMapper tranSumMapper;

	@Autowired
	CustomerService customerService;

	@Autowired
	OrderService orderService;

	@Autowired
	RefundService refundService;

	@Autowired
	AccountLockService accountLockService;
	
	@Autowired
	TranConsumeService tranConsumeService;
	
	@Autowired
	ShopOrderService shopOrderService;
	
	@Autowired
	ShopItemService shopItemService;
	
	@Autowired
	DayShopStockService dayShopStockService;
	
	@Autowired
	LogService logService;

	@Override
	public void create(TranSum dto) {
		dto.setCreateDate(new Date());
		tranSumMapper.insert(dto);
	}

//	@Override
//	public Page<TranSumDto> findPage(TranSumDto dto, Date startTime, Date endTime,
//			int currentPage, int pageSize) {
//		Page<TranSumDto> page = null;
//
//		TranSumExample example = new TranSumExample();
//		example.setOrderByClause("sum_date desc");
//		Criteria criteria = example.createCriteria();
//
//		// 开始时间
//		if (startTime != null) {
//			criteria.andSumDateGreaterThanOrEqualTo(startTime);
//		}
//
//		// 结束时间
//		if (endTime != null) {
//			criteria.andSumDateLessThanOrEqualTo(endTime);
//		}
//
//		Integer userId = dto.getUserId();
//
//		if (userId != null && userId > 0) {
//			criteria.andUserIdEqualTo(userId);
//		}
//
//		int count = tranSumMapper.countByExample(example);
//		if (count == 0) {
//			currentPage = 1;
//			page = new Page<TranSumDto>(currentPage, count,
//					pageSize, null);
//			return page;
//		}
//		int start = Page.calStartRow(currentPage, pageSize);
//
//		example.setLimit(pageSize);
//		example.setStart(start);
//		example.setLimitFlag(true);
//
//		List<TranSum> dataList = tranSumMapper.selectByExample(example);
//		List<TranSumDto> sumList = new ArrayList<TranSumDto>();
//		for (TranSum sum : dataList) {
//			TranSumDto tranSumDto = new TranSumDto(sum);
//			CustomerUser personUser = this.customerService.findById(sum.getUserId());
//			if (personUser != null) {
//				tranSumDto.setUserId(personUser.getId());
//				tranSumDto.setUserName(personUser.getUserName());
//				tranSumDto.setUserPhone(personUser.getMobilePhone());
//				tranSumDto.setCompanyName(personUser.getCompanyName());
//			}
//
//			sumList.add(tranSumDto);
//		}
//
//		page = new Page<TranSumDto>(currentPage, count,
//				pageSize, sumList);
//
//		return page;
//	}
	
	public List<TranSumDto> findAll(Date startTime, Date endTime)
	{
		TranSumExample example = new TranSumExample();
		example.setOrderByClause("sum_date desc");
		Criteria criteria = example.createCriteria();

		// 开始时间
		if (startTime != null) {
			criteria.andSumDateGreaterThanOrEqualTo(startTime);
		}

		// 结束时间
		if (endTime != null) {
			criteria.andSumDateLessThanOrEqualTo(endTime);
		}
		List<TranSum> dataList = tranSumMapper.selectByExample(example);
		List<TranSumDto> sumList = new ArrayList<TranSumDto>();
		for (TranSum sum : dataList) {
			TranSumDto tranSumDto = new TranSumDto(sum);
			CustomerUser personUser = this.customerService.findById(sum.getUserId());
			if (personUser != null) {
				tranSumDto.setUserId(personUser.getId());
				tranSumDto.setUserName(personUser.getUserName());
				tranSumDto.setUserPhone(personUser.getMobilePhone());
				tranSumDto.setCompanyName(personUser.getCompanyName());
				tranSumDto.setInterfacePerson(personUser.getInterfacePerson());
			}

			sumList.add(tranSumDto);
		}
		return sumList;
	}

	@Override
	public String createBySumBatch(Date sumDate,Date date,Integer cityId) {
		StringBuilder sb = new StringBuilder();
//		TranSumExample example =  new TranSumExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andSumDateLessThanOrEqualTo(sumDate);

//		TranSum last = tranSumMapper.selectLast(example);
//		Date firstDate = null;

//		if(last==null){
//			//取这个月的第一天
//			firstDate = DateUtils.setDays(sumDate, 1);
//			firstDate = DateUtils.setHours(firstDate, 0);
//			firstDate = DateUtils.setMinutes(firstDate, 0);
//			firstDate = DateUtils.setSeconds(firstDate, 0);
//		}else{
//			firstDate = DateUtils.addDays(last.getSumDate(), 1);
//		}
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(sumDate);
        int year = cal.get(Calendar.YEAR);//获取年份 
        int month=cal.get(Calendar.MONTH);//获取月份
		Date firstDate=DateUtil.getFirstDayOfMonth(sumDate);
		AccountLock aLock=new AccountLock();
		aLock.setYears(year+"");
		aLock.setMonths(month+1+"");
		AccountLock accountLock=accountLockService.findisLockByCityId(aLock,date,cityId);
		if(null==accountLock){
			this.deleteByDateAndCityId(firstDate, sumDate, cityId);
			List<TranSum> list = orderService.createTranSumByCityId(firstDate, sumDate, cityId);
			if(CollectionUtils.isNotEmpty(list)){
				for(TranSum dto : list){
					dto.setSumDate(sumDate);
					this.create(dto);
					String logStr = dto.getLogStr();
					if(StringUtils.isNotBlank(logStr)){
						sb.append("用户:"+dto.getUserId()+",结果："+logStr).append("\n");
					}
				}
			}
		}
		return sb.toString();

	}
	
	/**
	 * 如果截至日期比开始日期大一天，则删除本月之前的结算记录；否则直接计算当月结算信息
	 * */
//	public int delete(Date firstDate,Date sumDate){
//		TranSumExample tranSumExample = new TranSumExample();
//		Criteria criteria = tranSumExample.createCriteria();
//		criteria.andSumDateGreaterThanOrEqualTo(firstDate);
//		criteria.andSumDateLessThanOrEqualTo(sumDate);
//		return tranSumMapper.deleteByExample(tranSumExample);
//	}
	

	@Override
	public TranSumDto findDetail(int id) {
		TranSum sum = tranSumMapper.selectByPrimaryKey(id);
		TranSumDto tranSumDto = new TranSumDto(sum);
		CustomerUser personUser = this.customerService.findById(sum.getUserId());
		if (personUser != null) {
			tranSumDto.setUserId(personUser.getId());
			tranSumDto.setUserName(personUser.getUserName());
			tranSumDto.setUserPhone(personUser.getMobilePhone());
			tranSumDto.setCompanyName(personUser.getCompanyName());

			Date startTime = DateUtil.getFirstDayOfMonth(sum.getSumDate());
			Date endTime = DateUtil.getLastDayOfMonth(sum.getSumDate());
			Date date = new Date();
			int curMonth=DateUtil.getMonth(date);
			int sumMonth=DateUtil.getMonth(endTime);
			int curYear=DateUtil.getYear(date);
			int sumYear=DateUtil.getYear(endTime);
			int curDate=DateUtil.getDay(date);
			if(curMonth==sumMonth && curYear==sumYear && curDate!=1){
				SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, -1);
				date = calendar.getTime();
				try {
					endTime=dft.parse(dft.format(date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			List<Refund> refundList = refundService.find(sum.getUserId(), startTime, endTime);
			List<Order> orderList = orderService.findTranSumOrder(sum.getUserId(), startTime, endTime);

			//tranSumDto.setOrderList(orderList);
			//tranSumDto.setRefundList(refundList);

			List<BaseTranDetail> detailList = Lists.newArrayList();
			if(CollectionUtils.isNotEmpty(orderList)){
				for(Order order : orderList){
					BaseTranDetail dto = new BaseTranDetail();
					dto.setExecutedTime(order.getExecutedTime());
					dto.setNo(order.getOrderNo());
					dto.setTotalFee(order.getTotalFee());
					detailList.add(dto);
				}
			}

			if(CollectionUtils.isNotEmpty(refundList)){
				for(Refund refund : refundList){
					BaseTranDetail dto = new BaseTranDetail();
					dto.setExecutedTime(refund.getExecutedTime());
					dto.setNo(EncryptHelper.md5(refund.getId()+""));
					dto.setTotalFee(-refund.getTotalFee());
					detailList.add(dto);
				}
			}
			//根据Collections.sort重载方法来实现  
			Collections.sort(detailList,new Comparator<BaseTranDetail>(){  
				public int compare(BaseTranDetail b1, BaseTranDetail b2) {  
					return b1.getExecutedTime().compareTo(b2.getExecutedTime());  
				}  
			}); 
			//Collections.sort(detailList); 
			tranSumDto.setDetailList(detailList);
		}

		return tranSumDto;
	}

	
	@Override
	public TranSumDto findDetailNew(String userId,String years,String months) {
			String getStr="";
			SimpleDateFormat dft0 = new SimpleDateFormat("yyyy-MM-dd");
		    if(months.length()==1){
		    	getStr=years+"-0"+months+"-01";
		    }else{
		    	getStr=years+"-"+months+"-01";
		    }
		    Date getDate=null;
			try {
				getDate = dft0.parse(getStr);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			Date startTime = DateUtil.getFirstDayOfMonth(getDate);
			Date endTime = DateUtil.getLastDayOfMonth(getDate);
			TranSumExample example = new TranSumExample();
			Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(Integer.parseInt(userId));
			criteria.andSumDateGreaterThanOrEqualTo(startTime);
			criteria.andSumDateLessThanOrEqualTo(endTime);
			TranSum sum = tranSumMapper.selectByExample(example).get(0);
			TranSumDto tranSumDto = new TranSumDto(sum);
		    CustomerUser personUser = this.customerService.findById(Integer.parseInt(userId));
			if (personUser != null) {
				tranSumDto.setUserId(personUser.getId());
				tranSumDto.setUserName(personUser.getUserName());
				tranSumDto.setUserPhone(personUser.getMobilePhone());
				tranSumDto.setCompanyName(personUser.getCompanyName());
				Date date = new Date();
				int curMonth=DateUtil.getMonth(date);
				int curYear=DateUtil.getYear(date);
				int sumMonth=Integer.parseInt(months);
				int sumYear=Integer.parseInt(years);
				int curDate=DateUtil.getDay(date);
				if(curMonth==sumMonth && curYear==sumYear && curDate!=1){
					SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.DATE, -1);
					date = calendar.getTime();
					try {
						endTime=dft.parse(dft.format(date));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				List<Refund> refundList = refundService.find(Integer.parseInt(userId), startTime, endTime);
				List<Order> orderList = orderService.findTranSumOrder(Integer.parseInt(userId), startTime, endTime);
	
				List<BaseTranDetail> detailList = Lists.newArrayList();
				if(CollectionUtils.isNotEmpty(orderList)){
					for(Order order : orderList){
						BaseTranDetail dto = new BaseTranDetail();
						dto.setNo(order.getOrderNo());
						dto.setTotalFee(order.getTotalFee());
						dto.setExecutedTime(order.getExecutedTime());
	
						detailList.add(dto);
					}
				}
	
				if(CollectionUtils.isNotEmpty(refundList)){
					for(Refund refund : refundList){
						BaseTranDetail dto = new BaseTranDetail();
						dto.setNo(EncryptHelper.md5(refund.getId()+""));
						dto.setTotalFee(-refund.getTotalFee());
						dto.setExecutedTime(refund.getExecutedTime());
	
						detailList.add(dto);
					}
				}
	
				Collections.sort(detailList);
				tranSumDto.setDetailList(detailList);
			}
			return tranSumDto;
	}

	@Override
	public Long findCurrentMonthSourcingMoney(Integer customerUserId) {
		return this.tranSumMapper.findCurrentMonthSourcingMoney(customerUserId);
	}

	@Override
	public String createByconsumeBatch2(Date sumDate,Date lockdate,Integer cityId) {
		StringBuilder sb = new StringBuilder();
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(sumDate);
        int year = cal.get(Calendar.YEAR);//获取年份 
        int month=cal.get(Calendar.MONTH);//获取月份
        cal.set(Calendar.DAY_OF_MONTH, 1); 
        cal.add(Calendar.DATE, -1);
		Date time = cal.getTime();
		Date firstDate=DateUtil.getFirstDayOfMonth(sumDate);
		AccountLock aLock=new AccountLock();
		aLock.setYears(year+"");
		aLock.setMonths(month+1+"");
		AccountLock accountLock=accountLockService.findisLockByCityId(aLock, lockdate, cityId);
		if(accountLock==null){
			this.tranConsumeService.deleteByDateAndCityId(firstDate, sumDate, cityId);
			List<TranConsume> list2 = shopOrderService.createTranConsumeByCityId(firstDate, sumDate, cityId);
			if(CollectionUtils.isNotEmpty(list2)){
				for (TranConsume tranConsume : list2) {
					tranConsume.setSumDate(sumDate);
					Long money = this.shopItemService.findShopItemMoney(tranConsume.getUserId());
					tranConsume.setGoodsMoney(money);
					DayShopStock dayShopStock = this.dayShopStockService.findByDate(time,tranConsume.getUserId());
					if(null!=dayShopStock){
						tranConsume.setMonthStockMoney(dayShopStock.getMoney());
					}
					this.tranConsumeService.create(tranConsume);
				}
			}
		}
		return sb.toString();

	}

	@Override
	public String createByconsumeBatch(Date sumDate,Date lockdate,Integer cityId) {
		StringBuilder sb = new StringBuilder();
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(sumDate);
        int year = cal.get(Calendar.YEAR);//获取年份 
        int month=cal.get(Calendar.MONTH);//获取月份
        cal.set(Calendar.DAY_OF_MONTH, 1); 
        cal.add(Calendar.DATE, -1);
		Date time1 = cal.getTime();
		Date firstDate=DateUtil.getFirstDayOfMonth(sumDate);
		AccountLock aLock=new AccountLock();
		aLock.setYears(year+"");
		aLock.setMonths(month+1+"");
		AccountLock accountLock=accountLockService.findisLockByCityId(aLock, lockdate, cityId);
		if(accountLock==null){
			this.tranConsumeService.deleteByDateAndCityId(firstDate, sumDate, cityId);
			List<TranConsume> list2 = shopOrderService.createTranConsumeByCityId(firstDate, sumDate, cityId);
			if(CollectionUtils.isNotEmpty(list2)){
				for (TranConsume tranConsume : list2) {
					tranConsume.setSumDate(sumDate);
					DayShopStock dayShopStock = this.dayShopStockService.findByDate(sumDate,tranConsume.getUserId());
					if(null!=dayShopStock){
						logger.warn(sumDate+"上月架上库存："+dayShopStock.getMoney());
						tranConsume.setGoodsMoney(dayShopStock.getMoney());
					}
					DayShopStock dayShopStock2 = this.dayShopStockService.findByDate(time1,tranConsume.getUserId());
					if(null!=dayShopStock2){
						tranConsume.setMonthStockMoney(dayShopStock2.getMoney());
					}
					this.tranConsumeService.create(tranConsume);
				}
			}
		}
		return sb.toString();
	}

	@Override
	public String createSumBatch(Date sumDate,Integer userid,Integer cityId) {
		StringBuilder sb = new StringBuilder();

		Calendar cal = Calendar.getInstance(); 
		cal.setTime(sumDate);
        int year = cal.get(Calendar.YEAR);//获取年份 
        int month=cal.get(Calendar.MONTH);//获取月份
		Date firstDate=DateUtil.getFirstDayOfMonth(sumDate);
		AccountLock aLock=new AccountLock();
		aLock.setYears(year+"");
		aLock.setMonths(month+1+"");
		aLock.setCityId(cityId);
		//int accountLock=accountLockService.findLock(aLock);
		int accountLock=accountLockService.findLockByCityId(aLock);
		List<String> ordernos = this.orderService.findPendingDeliveryByDateAndCityId(firstDate, sumDate,cityId);
		if(!ordernos.isEmpty()){
			this.orderService.changeComfirmStatus(ordernos);
			SysLog sysLog = new SysLog();
			sysLog.setCreateTime(new Date());
			sysLog.setUserId(userid);
			sysLog.setCityId(cityId);
			sysLog.setContent("锁帐，未完成订单自动完成");
			sysLog.setDataContent(ordernos.toString());
			this.logService.createLog(sysLog);
		}
		if(accountLock!=1){
			//int i= this.delete(firstDate, sumDate);
			this.deleteByDateAndCityId(firstDate, sumDate,cityId);
			//List<TranSum> list = orderService.createTranSum(firstDate, sumDate);
			List<TranSum> list = orderService.createTranSumByCityId(firstDate, sumDate,cityId);
			if(CollectionUtils.isNotEmpty(list)){
				for(TranSum dto : list){
					dto.setSumDate(sumDate);
					this.create(dto);
					String logStr = dto.getLogStr();
					if(StringUtils.isNotBlank(logStr)){
						sb.append("用户:"+dto.getUserId()+",结果："+logStr).append("\n");
					}
				}
			}
		}
	
        cal.set(Calendar.DAY_OF_MONTH, 1); 
        cal.add(Calendar.DATE, -1);
		Date time = cal.getTime();
		
		if(accountLock!=1){
			//this.tranConsumeService.delete(firstDate, sumDate);
			this.tranConsumeService.deleteByDateAndCityId(firstDate, sumDate,cityId);
			//List<TranConsume> list2 = shopOrderService.createTranConsume(firstDate, sumDate);
			List<TranConsume> list2 = shopOrderService.createTranConsumeByCityId(firstDate, sumDate,cityId);
			if(CollectionUtils.isNotEmpty(list2)){
				for (TranConsume tranConsume : list2) {
					tranConsume.setSumDate(sumDate);
					DayShopStock dayShopStock1 = this.dayShopStockService.findByDate(sumDate,tranConsume.getUserId());
					if(null!=dayShopStock1){
						logger.warn(sumDate+"上月架上库存："+dayShopStock1.getMoney());
						tranConsume.setGoodsMoney(dayShopStock1.getMoney());
					}
					DayShopStock dayShopStock = this.dayShopStockService.findByDate(time,tranConsume.getUserId());
					if(null!=dayShopStock){
						tranConsume.setMonthStockMoney(dayShopStock.getMoney());
					}
					this.tranConsumeService.create(tranConsume);
				}
			}
		}
		//List<String> ordernos = this.orderService.findPendingDeliveryByDate(firstDate, sumDate);

		return sb.toString();
	}

	private void deleteByDateAndCityId(Date firstDate, Date sumDate,
			Integer cityId) {
		this.tranSumMapper.deleteByDateAndCityId(firstDate,sumDate,cityId);
	}

	@Override
	public List<TranSumDto> findByCondition(String userName, Date startTime,
			Date endTime, Integer cityId) {
		return this.tranSumMapper.findByCondition(userName,startTime,endTime,cityId);
	}

}
