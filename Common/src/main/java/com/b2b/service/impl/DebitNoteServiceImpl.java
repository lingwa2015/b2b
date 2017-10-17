package com.b2b.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.DebitNoteDemoMapper;
import com.b2b.common.dao.DebitNoteMapper;
import com.b2b.common.dao.InvoiceMapper;
import com.b2b.common.dao.PreferentialMapper;
import com.b2b.common.domain.DebitNote;
import com.b2b.common.domain.DebitNoteDemo;
import com.b2b.common.domain.Invoice;
import com.b2b.common.domain.Preferential;
import com.b2b.page.Page;
import com.b2b.service.DebitNoteService;
import com.google.common.collect.Maps;

@Service
public class DebitNoteServiceImpl implements DebitNoteService {

	@Autowired
	DebitNoteMapper debitNoteMapper;
	
	@Autowired
	DebitNoteDemoMapper debitNoteDemoMapper;
	
	@Autowired
	InvoiceMapper invoiceMapper;
	
	@Autowired
	PreferentialMapper preferentialMapper;
	
	@Override
	public void create(DebitNote dto){
		debitNoteMapper.insert(dto);
	}
	
	@Override
	public int delete(DebitNote dto,String userId){
		int ret=0;
		if(dto!=null){
			dto.setDeleteStatus(0);
			dto.setCreateDate(new Date());
			dto.setRemark(dto.getRemark()+";删除的收款单用户是："+userId);
			ret=debitNoteMapper.updateByPrimaryKey(dto);
		}
		return ret;
	}
	
	@Override
	public DebitNote findById(String id) {
		return debitNoteMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public void createInvoice(Invoice dto)
	{
		invoiceMapper.insert(dto);
	}
	
	@Override
	public int deleteInvoice(Invoice dto,String userId){
		int ret=0;
		if(dto!=null){
			dto.setDeleteStatus(0);
			dto.setCreateDate(new Date());
			dto.setRemark(dto.getRemark()+";删除开票的用户是："+userId);
			ret=invoiceMapper.updateByPrimaryKey(dto);
		}
		return ret;
	}
	
	@Override
	public Invoice findByIdInvoice(String id)
	{
		return invoiceMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public void createPreferential(Preferential dto)
	{
		preferentialMapper.insert(dto);
	}
	
	@Override
	public int deletePreferential(Preferential dto,String userId)
	{
		int ret=0;
		if(dto!=null){
			dto.setDeleteStatus(0);
			dto.setCreateDate(new Date());
			dto.setRemark(dto.getRemark()+";删除优惠的用户是："+userId);
			ret=preferentialMapper.updateByPrimaryKey(dto);
		}
		return ret;
	}
	
	@Override
	public Preferential findByIdPreferential(String id)
	{
		return preferentialMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Page<DebitNoteDemo> debitNoteList(String userName, int currentPage ,int pageSize, Date starttime, Date querytime, Date crestdate, Date creenddate,Integer cityId)
	{
		Page<DebitNoteDemo> page = null;
		int start = Page.calStartRow(currentPage, pageSize);
		int limit = pageSize;
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userName", userName);
		paramMap.put("status", Constant.VALID_STATUS);
		paramMap.put("start", Integer.valueOf(start));
		paramMap.put("limit", Integer.valueOf(limit));
		paramMap.put("starttime", starttime);
		paramMap.put("querytime", querytime);
		paramMap.put("crestdate", crestdate);
		paramMap.put("creenddate", creenddate);
		paramMap.put("cityId", cityId);
		int count = this.debitNoteDemoMapper.countByDebitNoteSql(paramMap);
		if (count == 0) {
			currentPage = 1;
			page = new Page<DebitNoteDemo>(currentPage, count, pageSize, null);
			return page;
		}
		List<DebitNoteDemo> debitNoteDemo = this.debitNoteDemoMapper.selectDebitNoteByOrderDate(paramMap);
		
		page = new Page<DebitNoteDemo>(currentPage, count, pageSize, debitNoteDemo);
		return page;
	}
	
	@Override
	public List<DebitNoteDemo> debitNoteListExport(String userName)
	{
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userName", userName);
		paramMap.put("status", Constant.VALID_STATUS);
		List<DebitNoteDemo> debitNoteDemo = this.debitNoteDemoMapper.selectDebitNoteByOrderDate(paramMap);
		return debitNoteDemo;
	}
	
	@Override
	public Page<DebitNoteDemo> debitInvoiceList(String userName, int currentPage ,int pageSize,Date starttime, Date querytime, Date crestdate, Date creenddate,Integer cityId)
	{
		Page<DebitNoteDemo> page = null;
		int start = Page.calStartRow(currentPage, pageSize);
		int limit = pageSize;
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userName", userName);
		paramMap.put("status", Constant.VALID_STATUS);
		paramMap.put("start", Integer.valueOf(start));
		paramMap.put("limit", Integer.valueOf(limit));
		paramMap.put("starttime", starttime);
		paramMap.put("querytime", querytime);
		paramMap.put("crestdate", crestdate);
		paramMap.put("creenddate", creenddate);
		paramMap.put("cityId", cityId);
		int count = this.debitNoteDemoMapper.countByInvoiceSql(paramMap);
		if (count == 0) {
			currentPage = 1;
			page = new Page<DebitNoteDemo>(currentPage, count, pageSize, null);
			return page;
		}
		List<DebitNoteDemo> debitNoteDemo = this.debitNoteDemoMapper.selectInvoiceByOrderDate(paramMap);
		
		page = new Page<DebitNoteDemo>(currentPage, count, pageSize, debitNoteDemo);
		return page;
	}
	
	@Override
	public List<DebitNoteDemo> debitInvoiceListExport(String userName)
	{
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userName", userName);
		paramMap.put("status", Constant.VALID_STATUS);
		List<DebitNoteDemo> debitNoteDemo = this.debitNoteDemoMapper.selectInvoiceByOrderDate(paramMap);
		return debitNoteDemo;
	}
	
	@Override
	public Page<DebitNoteDemo> debitPreferentialList(String userName, int currentPage ,int pageSize,String type, Date starttime, Date querytime,Date crestdate, Date creenddate,Integer cityId)
	{
		Page<DebitNoteDemo> page = null;
		int start = Page.calStartRow(currentPage, pageSize);
		int limit = pageSize;
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userName", userName);
		paramMap.put("status", Constant.VALID_STATUS);
		paramMap.put("start", Integer.valueOf(start));
		paramMap.put("limit", Integer.valueOf(limit));
		paramMap.put("type", type);
		paramMap.put("starttime", starttime);
		paramMap.put("querytime", querytime);
		paramMap.put("crestdate", crestdate);
		paramMap.put("creenddate", creenddate);
		paramMap.put("cityId", cityId);
		int count = this.debitNoteDemoMapper.countByPreferentialSql(paramMap);
		if (count == 0) {
			currentPage = 1;
			page = new Page<DebitNoteDemo>(currentPage, count, pageSize, null);
			return page;
		}
		List<DebitNoteDemo> debitNoteDemo = this.debitNoteDemoMapper.selectPreferentialByOrderDate(paramMap);
		
		page = new Page<DebitNoteDemo>(currentPage, count, pageSize, debitNoteDemo);
		return page;
	}
	
	@Override
	public List<DebitNoteDemo> debitPreferentialListExport(String userName)
	{
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userName", userName);
		paramMap.put("status", Constant.VALID_STATUS);
		List<DebitNoteDemo> debitNoteDemo = this.debitNoteDemoMapper.selectPreferentialByOrderDate(paramMap);
		return debitNoteDemo;
	}
	
	
//	@Override
//	public Page<Pair<DebitNoteDemo, List<DebitNoteDemo>>> debitNoteAmtListPage(String userName, Date startTime, Date endTime, int currentPage ,int pageSize)
//	{
//		Page<Pair<DebitNoteDemo, List<DebitNoteDemo>>> pages = null;
//		int start = Page.calStartRow(currentPage, pageSize);
//		int limit = pageSize;
//		HashMap<String, Object> paramMap = Maps.newHashMap();
//		paramMap.put("userName", userName);
//		paramMap.put("start", Integer.valueOf(start));
//		paramMap.put("limit", Integer.valueOf(limit));
//		paramMap.put("startTime", startTime);
//		paramMap.put("endTime", endTime);
//		int count = this.debitNoteDemoMapper.countPageGroup(paramMap);
//		if (count == 0) {
//			currentPage = 1;
//			pages = new Page<Pair<DebitNoteDemo, List<DebitNoteDemo>>>(currentPage, count, pageSize, null);
//			return pages;
//		}
//		List<DebitNoteDemo> debitNoteDemo = this.debitNoteDemoMapper.selectSumPageGroup(paramMap);
//		List<Pair<DebitNoteDemo, List<DebitNoteDemo>>> orderItemList = new ArrayList<Pair<DebitNoteDemo, List<DebitNoteDemo>>>();
//		for (DebitNoteDemo debit : debitNoteDemo) {
//			HashMap<String, Object> paramMapId = Maps.newHashMap();
//			paramMapId.put("userId", debit.getCompanyId());
//			paramMapId.put("startTime", startTime);
//			paramMapId.put("endTime", endTime);
//			List<DebitNoteDemo> itemList = debitNoteDemoMapper.selectByUserId(paramMapId);
//			if (CollectionUtils.isNotEmpty(itemList)) {
//				orderItemList.add(Pair.of(debit, itemList));
//			}
//		}
//		pages = new Page<Pair<DebitNoteDemo, List<DebitNoteDemo>>>(currentPage, count,
//				pageSize, orderItemList);
//		return pages;
//	}
	
	@Override
	public Page<DebitNoteDemo> debitNoteAmtListPage(String userName, Date endTime, int currentPage,String param ,int pageSize)
	{
		Page<DebitNoteDemo> pages = null;
		int start = Page.calStartRow(currentPage, pageSize);
		int limit = pageSize;
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userName", userName);
		paramMap.put("start", Integer.valueOf(start));
		paramMap.put("limit", Integer.valueOf(limit));
		paramMap.put("endTime", endTime);
		paramMap.put("param", param);
		int count = this.debitNoteDemoMapper.countPageGroup(paramMap);
		if (count == 0) {
			currentPage = 1;
			pages = new Page<DebitNoteDemo>(currentPage, count, pageSize, null);
			return pages;
		}
		List<DebitNoteDemo> debitNoteDemo = this.debitNoteDemoMapper.selectSumPageGroup(paramMap);
		pages = new Page<DebitNoteDemo>(currentPage, count,
				pageSize, debitNoteDemo);
		return pages;
	}
	
	@Override
	public List<DebitNoteDemo> debitNoteAmtListExport(String userName)
	{
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userName", userName);
		paramMap.put("start", null);
		paramMap.put("limit", null);
		paramMap.put("startTime", null);
		paramMap.put("endTime", null);
		paramMap.put("param", 1);
		List<DebitNoteDemo> debitNoteDemo = this.debitNoteDemoMapper.selectSumPageGroup(paramMap);
		return debitNoteDemo;
	}

	@Override
	public DebitNoteDemo findTotal(String userName, Date startTime,  Date endTime, Integer regionId,Integer reseauId,Integer cityId,String type,String debflag,String invflag) {
		return this.debitNoteDemoMapper.findTotal(userName,startTime,endTime,regionId,reseauId,cityId,type,debflag,invflag);
	}

	@Override
	public List<DebitNoteDemo> selectByUserId(HashMap<String, Object> paramMapId) {
		return this.debitNoteDemoMapper.selectByUserId(paramMapId);
	}

	@Override
	public List<DebitNoteDemo> debitNoteAmtList(String userName,Date starttime,
			Date querytime, String param,Integer regionId, Integer reseauId,Integer cityId,String type,String debflag,String invflag) {
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("userName", userName);
		paramMap.put("start", null);
		paramMap.put("limit", null);
		paramMap.put("startTime", starttime);
		paramMap.put("endTime", querytime);
		paramMap.put("param", param);
		paramMap.put("regionId", regionId);
		paramMap.put("reseauId", reseauId);
		paramMap.put("cityId", cityId);
		paramMap.put("type", type);
		paramMap.put("debflag", debflag);
		paramMap.put("invflag", invflag);
		return this.debitNoteDemoMapper.selectSumPageGroup(paramMap);
	}

	@Override
	public Long totalPreferential(String userName, String type, Date starttime,
			Date querytime,Date crestdate, Date creenddate,Integer cityId) {
		return this.debitNoteDemoMapper.totalPreferential(userName,type,starttime,querytime,crestdate,creenddate,cityId);
	}

	@Override
	public List<DebitNoteDemo> findReceivedPaymentsReport(String type,Integer cityId) {
		return this.debitNoteDemoMapper.findReceivedPaymentsReport(type,cityId);
	}

	@Override
	public List<DebitNoteDemo> findReceivedPaymentsDetailReport(String type,
			String queryDate,Integer cityId) {
		return this.debitNoteDemoMapper.findReceivedPaymentsDetailReport(type,queryDate,cityId);
	}

	@Override
	public Long totaldebitNote(String userName, Date starttime, Date querytime,
			Date crestdate, Date creenddate,Integer cityId) {
		return this.debitNoteDemoMapper.totaldebitNote(userName,starttime,querytime,crestdate,creenddate,cityId);
	}

	@Override
	public Long totalInvoice(String userName, Date starttime, Date querytime,
			Date crestdate, Date creenddate,Integer cityId) {
		return this.debitNoteDemoMapper.totalInvoice(userName,starttime,querytime,crestdate,creenddate,cityId);
	}

	@Override
	public DebitNoteDemo findTotalaaa(String userName, Date starttime,
			Date querytime, Integer regionId, Integer reseauId,Integer cityId,String type, String debflag,String invflag) {
		return this.debitNoteDemoMapper.findTotalaaa(userName,starttime,querytime,regionId,reseauId,cityId,type,debflag,invflag);
	}

	@Override
	public Long findBeforeTimeNotVerificationInvoiceFeeByShopId(Integer shopId) {
		return this.debitNoteDemoMapper.findBeforeTimeNotVerificationInvoiceFeeByShopId(shopId);
	}

	@Override
	public Integer findHasVerification(Integer shopId) {
		return this.debitNoteDemoMapper.findHasVerification(shopId);
	}

	@Override
	public DebitNoteDemo findDebitCusNumAndFeeByCityIdAndReseauId(Integer cityId, Integer reseauId) {
		return this.debitNoteDemoMapper.findDebitCusNumAndFeeByCityIdAndReseauId(cityId,reseauId);
	}

	@Override
	public DebitNoteDemo findInvoiceCusNumAndFeeByCityIdAndReseauId(Integer cityId, Integer reseauId) {
		return this.debitNoteDemoMapper.findInvoiceCusNumAndFeeByCityIdAndReseauId(cityId,reseauId);
	}

	@Override
	public DebitNoteDemo findInvoAgeByCityIdAndReseauId(Integer cityId, Integer reseauId) {
		return this.debitNoteDemoMapper.findInvoAgeByCityIdAndReseauId(cityId,reseauId);
	}

	@Override
	public DebitNoteDemo findDebitAgeByCityIdAndReseauId(Integer cityId, Integer reseauId) {
		return this.debitNoteDemoMapper.findDebitAgeByCityIdAndReseauId(cityId,reseauId);
	}

	@Override
	public DebitNoteDemo findInvoiceListByCityIdAndReseauId(Integer cityId, Integer reseauId) {
		return this.debitNoteDemoMapper.findInvoiceListByCityIdAndReseauId(cityId,reseauId);
	}

	@Override
	public DebitNoteDemo findDebitListByCityIdAndReseauId(Integer cityId, Integer reseauId) {
		return this.debitNoteDemoMapper.findDebitListByCityIdAndReseauId(cityId,reseauId);
	}

	@Override
	public List<DebitNoteDemo> findInvoicesByCityIdAndReseauId(Integer cityId, Integer reseauId) {
		return this.debitNoteDemoMapper.findInvoicesByCityIdAndReseauId(cityId,reseauId);
	}

	@Override
	public List<DebitNoteDemo> findDebitsByCityIdAndReseauId(Integer cityId, Integer reseauId) {
		return this.debitNoteDemoMapper.findDebitsByCityIdAndReseauId(cityId,reseauId);
	}

	@Override
	public List<DebitNoteDemo> findDebitagesByCityIdAndReseauIdAndflag(Integer cityId, Integer reseauId, Integer flag,
			Integer param) {
		return this.debitNoteDemoMapper.findDebitagesByCityIdAndReseauIdAndflag(cityId,reseauId,flag,param);
	}

	@Override
	public List<DebitNoteDemo> findInvoiceagesByCityIdAndReseauIdAndflag(Integer cityId, Integer reseauId, Integer flag,
			Integer param) {
		return this.debitNoteDemoMapper.findInvoiceagesByCityIdAndReseauIdAndflag(cityId,reseauId,flag,param);
	}

	@Override
	public Long findMoneyByDateAndCityId(Date date,Integer cityId) {
		return this.debitNoteDemoMapper.findMoneyByDateAndCityId(date,cityId);
	}

	@Override
	public Long findMoneyByDateAndCityIdAndReseauId(Date date, Integer cityId, Integer reseauId) {
		return this.debitNoteDemoMapper.findMoneyByDateAndCityIdAndReseauId(date,cityId,reseauId);
	}
}
