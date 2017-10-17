package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.WXRechargeRecordMapper;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.NotifyState;
import com.b2b.common.domain.WXRechargeRecord;
import com.b2b.common.domain.WXRechargeRecordExample;
import com.b2b.common.domain.WXRechargeRecordExample.Criteria;
import com.b2b.page.Page;
import com.b2b.service.CustomerService;
import com.b2b.service.NotifyStateService;
import com.b2b.service.WXAccountService;
import com.b2b.service.WXRechargeRecordService;



@Service
public class WXRechargeRecordServiceImpl implements WXRechargeRecordService {
	@Autowired
	private WXRechargeRecordMapper wxRechargeRecordMapper;
	
	@Autowired
	private WXAccountService wxAccountService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private NotifyStateService notifyStateService;
	
	@Override
	public List<WXRechargeRecord> findAllByCid(Integer customerUserId) {
		WXRechargeRecordExample example = new WXRechargeRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(customerUserId);
		criteria.andStateEqualTo(1);
		return this.wxRechargeRecordMapper.selectByExample(example);
	}

	@Override
	public void save(String id, int money, int freeMoney,
			String orderNo) {
		WXRechargeRecord record = new WXRechargeRecord();
		record.setCid(Integer.parseInt(id));
		record.setFreeMoney((long)freeMoney);
		record.setRechargeMoney((long)money);
		record.setId(orderNo);
		record.setCreatedTime(new Date());
		record.setState(0);
		this.wxRechargeRecordMapper.insert(record);
	}

	@Override
	public void changeStatus(String id) {
		WXRechargeRecord record = this.wxRechargeRecordMapper.selectByPrimaryKey(id);
		record.setState(1);
		this.wxRechargeRecordMapper.updateByPrimaryKey(record);
		long money = record.getFreeMoney()+record.getRechargeMoney();
		this.wxAccountService.updateForAddMoney(record.getCid(),money);
		CustomerUser customerUser = this.customerService.findById(record.getCid());
		if(null!=customerUser && 1!=customerUser.getIswxvip()){
			customerUser.setIswxvip(1);
			this.customerService.update(customerUser);
		}
		NotifyState state = new NotifyState();
		state.setId(id);
		state.setStatus(1);
		state.setCreatedTime(new Date());
		this.notifyStateService.insert(state);
	}

	@Override
	public Page<WXRechargeRecord> findPageByCondition(int currentPage,
			int pageSize, Date startTime, Date endTime, String userName) {
		int count = this.wxRechargeRecordMapper.countByCondition(startTime, endTime,userName);
		if(count ==0){
			return new Page<WXRechargeRecord>();
		}
		int start = Page.calStartRow(currentPage, pageSize);
		List<WXRechargeRecord> list = this.wxRechargeRecordMapper.findPageByCondition(startTime, endTime,userName,start,pageSize);
		Page<WXRechargeRecord> page = new Page<WXRechargeRecord>(currentPage,count,pageSize,list);
		return page;
	}

}
