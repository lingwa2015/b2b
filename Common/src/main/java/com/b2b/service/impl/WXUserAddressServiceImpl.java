package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.Constant;
import com.b2b.common.dao.WXUserAddressMapper;
import com.b2b.common.domain.WXUserAddress;
import com.b2b.common.domain.WXUserAddressExample;
import com.b2b.common.domain.WXUserAddressExample.Criteria;
import com.b2b.service.WXUserAddressService;

@Service
public class WXUserAddressServiceImpl implements WXUserAddressService {
	@Autowired
	WXUserAddressMapper wxuserAddressMapper;
	
	/**
	 * 新增地址
	 * */
	@Override
	public void create(WXUserAddress dto) {
		updateDefaultStatusByWXUserId(dto.getWxuserId());//修改给用户的其他地址为非默认
		dto.setStatus(Constant.VALID_STATUS);
		dto.setCreatedTime(new Date());
		dto.setUpdatedTime(new Date());
		dto.setDefaultStatus(Constant.VALID_STATUS);
		wxuserAddressMapper.insert(dto);
	}
	
	/**
	 * 查询客户的默认地址信息
	 * */
	@Override
	public List<WXUserAddress> selectByExample(int wxuserId){
		WXUserAddressExample example=new WXUserAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andDefaultStatusEqualTo(Constant.VALID_STATUS);
		criteria.andWxuserIdEqualTo(wxuserId);
		List<WXUserAddress> list = wxuserAddressMapper.selectByExample(example);
		return list;
	}

	/**
	 * 根据微信用户Id更新默认状态
	 * */
	public void updateDefaultStatusByWXUserId(int wxuserId) {
		wxuserAddressMapper.updateDefaultStatusByWXUserId(wxuserId);
	}
	
	/**
	 * 根据id将地址设为默认
	 * */
	@Override
	public void updateDefaultStatusById(int id,int wxuserId) {
		updateDefaultStatusByWXUserId(wxuserId);//修改该微信用户的其他地址为非默认
		WXUserAddress dto =new WXUserAddress();
		dto.setId(id);
		dto.setDefaultStatus(Constant.VALID_STATUS);
		dto.setUpdatedTime(new Date());
		wxuserAddressMapper.updateByPrimaryKeySelective(dto);
	}
	
	/**
	 * 根据id修改地址
	 * */
	@Override
	public void updateAddressById(int id,int provinceId,int cityId,int townId,String address,String addressDetails) {
		WXUserAddress dto =new WXUserAddress();
		dto.setId(id);
		dto.setProvinceId(provinceId);
		dto.setCityId(cityId);
		dto.setTownId(townId);
		dto.setAddress(address);
		dto.setAddressDetails(addressDetails);
		dto.setUpdatedTime(new Date());
		wxuserAddressMapper.updateByPrimaryKeySelective(dto);
	}

	/**
	 * 删除时修改状态标识
	 * */
	@Override
	public void delete(int id) {
		WXUserAddress dto =new WXUserAddress();
		dto.setId(id);
		dto.setStatus(Constant.DELETE_STATUS);
		dto.setUpdatedTime(new Date());
		wxuserAddressMapper.updateByPrimaryKeySelective(dto);
	}

	/**
	 * 查询微信用户地址信息，当wxuserId=0时直接查询全部用户信息
	 * */
	@Override
	public List<WXUserAddress> findByWXUserId(int wxuserId) {
		return wxuserAddressMapper.findByWXUserId(wxuserId);
	}
	
	
	/**
	 * 根据ID查询地址信息
	 * */
	@Override
	public WXUserAddress findById(int id){
		return wxuserAddressMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(WXUserAddress wxUserAddress) {
		this.wxuserAddressMapper.updateByPrimaryKeySelective(wxUserAddress);
	}
}
