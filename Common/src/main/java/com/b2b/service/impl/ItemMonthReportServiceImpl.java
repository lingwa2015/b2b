package com.b2b.service.impl;

import com.b2b.common.dao.ItemMonthReportMapper;
import com.b2b.common.domain.ItemMonthReport;
import com.b2b.service.ItemMonthReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemMonthReportServiceImpl implements ItemMonthReportService {
	@Autowired
	private ItemMonthReportMapper itemMonthReportMapper;

	@Override
	public void deleteByDate(Date date4, Integer cityId) {
		this.itemMonthReportMapper.deleteByDate(date4, cityId);
	}

	@Override
	public List<ItemMonthReport> findAllItem() {
		return  this.itemMonthReportMapper.findAllItem();
	}

	@Override
	public Integer selectOrderItemNum(Integer itemId, Date data) {
		return this.itemMonthReportMapper.selectOrderItemNum(itemId, data);
	}

	@Override
	public ItemMonthReport selectShopOrderInfo(Integer itemId, Date data) {
		return this.itemMonthReportMapper.selectShopOrderInfo(itemId, data);
	}

	@Override
	public Integer selectShopNum(Integer itemId, Date data) {
		return this.itemMonthReportMapper.selectShopNum(itemId, data);
	}

	@Override
	public ItemMonthReport selectOrderInfo(Integer itemId, Date data) {
		return this.itemMonthReportMapper.selectShopOrderInfo(itemId, data);
	}

	@Override
	public Integer selectOrderShopNum(Integer itemId, Date data) {
		return this.itemMonthReportMapper.selectOrderShopNum(itemId, data);
	}

	@Override
	public ItemMonthReport selectRefundInfo(Integer itemId, Date data) {
		return this.itemMonthReportMapper.selectRefundInfo(itemId, data);
	}

	@Override
	public Integer selectOrderRefundShopNum(Integer itemId, Date data) {
		return this.itemMonthReportMapper.selectOrderRefundShopNum(itemId, data);
	}

	@Override
	public Integer selectAllShopNum(Integer itemId, Date data) {
		return this.itemMonthReportMapper.selectAllShopNum(itemId, data);
	}

	@Override
	public Integer selectStockNum(Integer itemId, Date data) {
		return this.itemMonthReportMapper.selectStockNum(itemId, data);
	}

	@Override
	public List<ItemMonthReport> findItemDailyReports(Date date, String itemName,
				String param, String isnew, String isrecommend, int onecate, int twocate, Integer cityId) {
		return this.itemMonthReportMapper.findItemDailyReports(date, itemName,
				param, isnew, isrecommend, onecate, twocate, cityId);
	}

	@Override
	public Integer selectStorageOrderNum(Integer itemId, Date data) {
		return this.itemMonthReportMapper.selectStorageOrderNum(itemId, data);
	}

	@Override
	public Integer selectStorageNum(Integer itemId, Date data) {
		return this.itemMonthReportMapper.selectStorageNum(itemId, data);
	}

	@Override
	public void insert(ItemMonthReport report) {
		this.itemMonthReportMapper.insert(report);
	}

	@Override
	public List<ItemMonthReport> findAllItems(Date date, Integer cityId) {
		return this.itemMonthReportMapper.findAllItems(date, cityId);
	}

}
