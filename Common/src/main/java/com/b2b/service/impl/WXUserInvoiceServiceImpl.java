package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.WXUserAddressMapper;
import com.b2b.common.dao.WXUserInvoiceMapper;
import com.b2b.common.domain.WXUserAddress;
import com.b2b.common.domain.WXUserInvoice;
import com.b2b.service.WXUserAddressService;
import com.b2b.service.WXUserInvoiceService;

@Service
public class WXUserInvoiceServiceImpl implements WXUserInvoiceService {
	@Autowired
	WXUserInvoiceMapper wxuserInvoiceMapper;
	
	/**
	 * 选择开票时,如果该微信用户已有开票信息这修改相关信息，如果没有开票信息时保存开票公司信息
	 * */
	@Override
	public void create(int wxuserId,String companyName)
	{
		WXUserInvoice wxuserInvoice=new WXUserInvoice();
		wxuserInvoice.setWxuserId(wxuserId);
		wxuserInvoice.setCompanyName(companyName);
		wxuserInvoice.setCreatedTime(new Date());
		WXUserInvoice wxuserInvoiceResult= findByWXUserId(wxuserId);
		if(wxuserInvoiceResult!=null){
			wxuserInvoiceResult.setCompanyName(companyName);
			wxuserInvoiceResult.setCreatedTime(new Date());
			wxuserInvoiceMapper.updateByPrimaryKey(wxuserInvoiceResult);
		}else{
			wxuserInvoiceMapper.insert(wxuserInvoice);
		}
	}
	
//	/**
//	 * 修改查询开票信息
//	 * */
//	@Override
//	public void update(WXUserInvoice dto)
//	{
//		dto.setCreatedTime(new Date());
//		wxuserInvoiceMapper.updateByPrimaryKey(dto);
//	}
	
	/**
	 * 根据微信客户id查询开发信息
	 * */
	@Override
	public WXUserInvoice findByWXUserId(int wxuserId)
	{
		List<WXUserInvoice> wxuserInvoiceList=wxuserInvoiceMapper.findByWXUserId(wxuserId);
		WXUserInvoice wxuserInvoice=null;
		if(wxuserInvoiceList.size()>0){
			wxuserInvoice=wxuserInvoiceList.get(0);
		}
		return wxuserInvoice;
	}
	
	/**
	 * 查询所有用户的开票信息
	 * */
	@Override
	public List<WXUserInvoice> findAll()
	{
		return wxuserInvoiceMapper.findByWXUserId(0);
	}

	@Override
	public WXUserInvoice findById(Integer invoiceid) {
		return this.wxuserInvoiceMapper.selectByPrimaryKey(invoiceid);
	}

	@Override
	public void save(WXUserInvoice invoice) {
		this.wxuserInvoiceMapper.insert(invoice);
	}

	@Override
	public void update(int id, String invoiceName) {
		WXUserInvoice invoice = new WXUserInvoice();
		invoice.setId(id);
		invoice.setCompanyName(invoiceName);
		this.wxuserInvoiceMapper.updateByPrimaryKeySelective(invoice);
	}
}
