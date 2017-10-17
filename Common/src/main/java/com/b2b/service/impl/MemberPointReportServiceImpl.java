package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.MemberPointReportMapper;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.MemberPoint;
import com.b2b.common.domain.MemberPointReport;
import com.b2b.common.domain.MemberPointReportExample;
import com.b2b.common.domain.MemberPointReportExample.Criteria;
import com.b2b.service.CustomerService;
import com.b2b.service.MemberPointReportService;
import com.b2b.service.MemberPointService;

@Service
public class MemberPointReportServiceImpl implements MemberPointReportService {
	@Autowired
	private MemberPointReportMapper memberPointReportMapper;
	
	@Autowired
	private MemberPointService memberPointService;
	
	@Autowired
	CustomerService customerService;
	
	@Override
	public void save(MemberPointReport point) {
		MemberPoint memberPoint = this.memberPointService.findByid(point.getShopId());
		if(memberPoint==null){
			CustomerUser user = this.customerService.findById(point.getShopId());
			this.memberPointReportMapper.insert(point);
			MemberPoint memberPoint2 = new MemberPoint();
			memberPoint2.setShopId(point.getShopId());
			memberPoint2.setAccount(point.getPoint());
			memberPoint2.setCreatedTime(new Date());
			memberPoint2.setShopName(user.getUserName());
			this.memberPointService.insert(memberPoint2);
		}else{
			this.memberPointReportMapper.insert(point);
			this.memberPointService.updateAccount(memberPoint.getShopId(),point.getPoint());
		}
	}

	@Override
	public List<MemberPointReport> findByShopId(Integer userid) {
		return this.memberPointReportMapper.findByShopId(userid);
	}

	@Override
	public void deleteByID(MemberPointReport memberPointReport) {
		this.memberPointReportMapper.deleteByPrimaryKey(memberPointReport.getId());
		this.memberPointService.updateAccount(memberPointReport.getShopId(),-memberPointReport.getPoint());
	}

	@Override
	public MemberPointReport findById(Integer id) {
		return this.memberPointReportMapper.selectByPrimaryKey(id);
	}

}
