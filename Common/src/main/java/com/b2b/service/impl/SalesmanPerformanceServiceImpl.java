package com.b2b.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.SalesmanPerformanceMapper;
import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.SalesmanPerformance;
import com.b2b.common.domain.SalesmanPerformanceExample;
import com.b2b.common.domain.SalesmanPerformanceExample.Criteria;
import com.b2b.common.util.DateUtil;
import com.b2b.dto.TranSumDto;
import com.b2b.page.Page;
import com.b2b.service.AccountLockService;
import com.b2b.service.CustomerService;
import com.b2b.service.OrderService;
import com.b2b.service.RefundService;
import com.b2b.service.SalesmanPerformanceService;

@Service
public class SalesmanPerformanceServiceImpl implements
		SalesmanPerformanceService {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	AccountLockService accountLockService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	RefundService refundService;
	
	@Autowired
	private SalesmanPerformanceMapper salesmanPerformanceMapper;
	
	@Override
	public void create(Date sumDate, Date mardate) {
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(sumDate);
        int year = cal.get(Calendar.YEAR);//获取年份 
        int month=cal.get(Calendar.MONTH);//获取月份
        Date firstDate=DateUtil.getFirstDayOfMonth(sumDate);
        Date curdate = new Date();
		AccountLock aLock=new AccountLock();
		aLock.setYears(year+"");
		aLock.setMonths(month+1+"");
		//int accountLock=accountLockService.findLock(aLock);
		//if(accountLock!=1){
			//删除之前的
			SalesmanPerformanceExample example = new SalesmanPerformanceExample();
			Criteria criteria = example.createCriteria();
			criteria.andCountDateGreaterThanOrEqualTo(firstDate);
			criteria.andCountDateLessThanOrEqualTo(sumDate);
			this.salesmanPerformanceMapper.deleteByExample(example);
			List<CustomerUser> customerUsers = this.customerService.findList();
			for (CustomerUser customerUser : customerUsers) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(customerUser.getCreatedTime());
				calendar.add(Calendar.MONTH, +1);
				Date date = calendar.getTime();
				SalesmanPerformance performance = new SalesmanPerformance();
				performance.setCompanyId(customerUser.getId());
				performance.setConpanyName(customerUser.getCompanyName());
				performance.setCountDate(sumDate);
				performance.setInterfaceMan(customerUser.getInterfacePerson());
				performance.setCreateTime(curdate);
				int count1 = this.orderService.countNumByUserAndTime(customerUser.getId(),firstDate,sumDate);
				int count2 = this.refundService.countNumByUserAndTime(customerUser.getId(),firstDate,sumDate);
				performance.setOrderNum(count1);
				performance.setRefundNum(count2);
				int money1 = this.orderService.countMoneyByUserAndTimeAndSign(customerUser.getId(),firstDate,sumDate,1);
				int money2 = this.refundService.countMoneyByUserAndTime(customerUser.getId(),firstDate,sumDate);
				long bagmoney = this.orderService.countMoneyByUserAndTimeAndSign(customerUser.getId(),firstDate,sumDate,2);
				long money = money1-money2;
				long money10 = money+bagmoney;
				if(date.after(sumDate)){
					performance.setSaleMoney(money10);
					performance.setNewSaleMoney(money);
					performance.setBagMoney(bagmoney);
					if(customerUser.getCreatedTime().after(mardate)){
						performance.setAfterMarsaleMoney(money);
					}else{
						performance.setAfterMarsaleMoney(0l);
					}
				}else if (date.after(firstDate)&&date.before(sumDate)) {
					performance.setSaleMoney(money10);
					int money3 = this.orderService.countMoneyByUserAndTimeAndSign(customerUser.getId(),firstDate,date,1);
					int money4 = this.refundService.countMoneyByUserAndTime(customerUser.getId(),firstDate,date);
					long money5 = money3-money4;
					performance.setNewSaleMoney(money5);
					performance.setBagMoney(bagmoney);
					if(customerUser.getCreatedTime().after(mardate)){
						performance.setAfterMarsaleMoney(money);
					}else{
						performance.setAfterMarsaleMoney(0l);
					}
				}else{
					performance.setSaleMoney(money10);
					performance.setNewSaleMoney(0l);
					performance.setBagMoney(bagmoney);
					if(customerUser.getCreatedTime().after(mardate)){
						performance.setAfterMarsaleMoney(money);
					}else{
						performance.setAfterMarsaleMoney(0l);
					}
				}
				this.salesmanPerformanceMapper.insert(performance);
			}
		//}
	}

	@Override
	public Page<SalesmanPerformance> findPageByDate(Date startTime,
			Date endTime, int currentPage, int pageSize) {
		Page<SalesmanPerformance> page = null;
		int count = salesmanPerformanceMapper.countGroupByInterfaceman(startTime,endTime);
		if (count == 0) {
			currentPage = 1;
			page = new Page<SalesmanPerformance>(currentPage, count,
					pageSize, null);
			return page;
		}
		int start = Page.calStartRow(currentPage, pageSize);
		List<SalesmanPerformance> list = this.salesmanPerformanceMapper.findPageByDate(startTime,endTime,start,pageSize);
		page = new Page<SalesmanPerformance>(currentPage, count,
				pageSize, list);
		return page;
	}
	
}
