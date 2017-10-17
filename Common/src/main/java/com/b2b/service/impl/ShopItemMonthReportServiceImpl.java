package com.b2b.service.impl;

import com.b2b.common.dao.ShopItemMonthReportMapper;
import com.b2b.common.domain.ShopItemMonthReport;
import com.b2b.common.domain.ShopItemMonthReportExample;
import com.b2b.common.domain.ShopItemMonthReportExample.Criteria;
import com.b2b.common.util.DateUtil;
import com.b2b.service.ShopItemMonthReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopItemMonthReportServiceImpl implements ShopItemMonthReportService {
	@Autowired
	private ShopItemMonthReportMapper shopItemMonthReportMapper;

	@Override
	public void deleteByDate(Date date4) {
		this.shopItemMonthReportMapper.deleteByDate(date4);
	}

	@Override
	public List<ShopItemMonthReport> findShopItemsByDate(Date data, Integer cityId) {
		return this.shopItemMonthReportMapper.findShopItemsByDate(data, cityId);
	}

	@Override
	public  Integer  selectShopOrderItemSaleCount(Integer shopID, Integer itemId, Date data) {
		return this.shopItemMonthReportMapper.selectShopOrderItemSaleCount(shopID, itemId, data);
	}

	@Override
	public  Integer  selectShopOrderItemRefundNum(Integer shopID, Integer itemId, Date data) {
		return this.shopItemMonthReportMapper.selectShopOrderItemRefundNum(shopID, itemId, data);
	}

	@Override
	public  Integer  selectShopOrderItemStockNum(Integer shopID, Integer itemId, Date data) {
		return this.shopItemMonthReportMapper.selectShopOrderItemStockNum(shopID, itemId, data);
	}

	@Override
	public void deleteByDateAndCityId(Date date, Integer id) {
		this.shopItemMonthReportMapper.deleteByDateAndCityId(date,id);
	}

	@Override
	public void insert(ShopItemMonthReport report) {
		this.shopItemMonthReportMapper.insert(report);
	}

	@Override
	public List<ShopItemMonthReport> findShopItemMontList(Date dateTime, String itemName, String username, Integer reseauId, Integer cityId, String isnew, String param) {
		return this.shopItemMonthReportMapper.findShopItemMontList(dateTime, itemName, username, reseauId, cityId, isnew, param);
	}

	@Override
	public List<ShopItemMonthReport> findShopItems(Date date, Integer cityId) {
		return this.shopItemMonthReportMapper.findShopItems(date, cityId);
	}
}
