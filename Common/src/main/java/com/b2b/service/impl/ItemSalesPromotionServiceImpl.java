package com.b2b.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ItemSalesPromotionMapper;
import com.b2b.common.domain.ItemSaleCustomer;
import com.b2b.common.domain.ItemSaleCustomerExample;
import com.b2b.common.domain.ItemSaleCustomerExample.Criteria;
import com.b2b.common.domain.ItemSalesPromotion;
import com.b2b.service.ItemSaleCustomerService;
import com.b2b.service.ItemSalesPromotionService;

@Service
public class ItemSalesPromotionServiceImpl implements ItemSalesPromotionService{
	
	@Autowired
	ItemSalesPromotionMapper itemSalesPromotionMapper;
	
	@Autowired
	private ItemSaleCustomerService itemSaleCustomerService;
	
	@Override
	public List<ItemSalesPromotion> findByItemIdAndDateAndCityId(Integer itemId,Date date, Integer cityId) {
		return this.itemSalesPromotionMapper.findByItemIdAndDateAndCityId(itemId,date,cityId);
	}

	@Override
	public List<ItemSalesPromotion> findByItemIdAndStartAndEndTimeAndCityId(Integer itemId,Date startTime, Date endTime,
			Integer cityId) {
		return this.itemSalesPromotionMapper.findByItemIdAndStartAndEndTimeAndCityId(itemId,startTime,endTime,cityId);
	}

	@Override
	public void save(ItemSalesPromotion dto) {
		this.itemSalesPromotionMapper.insert(dto);
	}

	@Override
	public List<ItemSalesPromotion> findAllByConditions(String itemname, String param,
			Integer cityId) {
		return this.itemSalesPromotionMapper.findAllByConditions(itemname,param,cityId);
	}

	@Override
	public void changeStatus(ItemSalesPromotion promotion) {
		this.itemSalesPromotionMapper.updateByPrimaryKeySelective(promotion);
	}

	@Override
	public void changeTypeAndSaveItemSaleCustomer(Integer itemsaleId, List<ItemSaleCustomer> datas) {
		ItemSalesPromotion promotion = new ItemSalesPromotion();
		promotion.setId(itemsaleId);
		promotion.setShopType(2);
		this.itemSalesPromotionMapper.updateByPrimaryKeySelective(promotion);
		this.itemSaleCustomerService.insert(datas);
	}

	@Override
	public List<ItemSalesPromotion> findStratedByCityId(Integer cityId) {
		return this.itemSalesPromotionMapper.findStratedByCityId(cityId);
	}

	@Override
	public List<ItemSalesPromotion> findByItemSaleIdAndShopId(Integer itemSaleId , Integer id) {
		return this.itemSalesPromotionMapper.findByItemSaleIdAndShopId(itemSaleId,id);
	}

	@Override
	public List<ItemSalesPromotion> findStratedByCityIdAndShopId(Integer cityId, Integer shopId) {
		return this.itemSalesPromotionMapper.findStratedByCityIdAndShopId(cityId,shopId);
	}

	@Override
	public List<ItemSalesPromotion> findPreferentialByItemSaleId(Integer id) {
		return this.itemSalesPromotionMapper.findPreferentialByItemSaleId(id);
	}

	@Override
	public ItemSalesPromotion findTotalByItemSaleId(Integer id) {
		return this.itemSalesPromotionMapper.findTotalByItemSaleId(id);
	}

	@Override
	public ItemSalesPromotion findById(Integer id) {
		return this.itemSalesPromotionMapper.selectByPrimaryKey(id);
	}

}
