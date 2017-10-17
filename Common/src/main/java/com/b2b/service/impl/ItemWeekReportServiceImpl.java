package com.b2b.service.impl;

import com.b2b.common.dao.ItemWeekReportMapper;
import com.b2b.common.domain.ItemWeekReport;
import com.b2b.service.ItemWeekReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemWeekReportServiceImpl implements ItemWeekReportService {
	@Autowired
	private ItemWeekReportMapper itemWeekReportMapper;

	@Override
	public void deleteByDate(Date date4, Integer weekth) {
		this.itemWeekReportMapper.deleteByDate(date4, weekth);
	}

	@Override
	public List<ItemWeekReport> findAllItem() {
		return  this.itemWeekReportMapper.findAllItem();
	}

	@Override
	public Integer selectOrderItemNum(Integer itemId, Date startDate, Date endDate) {
		return this.itemWeekReportMapper.selectOrderItemNum(itemId, startDate, endDate);
	}

	@Override
	public ItemWeekReport selectShopOrderInfo(Integer itemId, Date startDate, Date endDate) {
		return this.itemWeekReportMapper.selectShopOrderInfo(itemId, startDate, endDate);
	}

	@Override
	public Integer selectShopNum(Integer itemId, Date startDate, Date endDate) {
		return this.itemWeekReportMapper.selectShopNum(itemId, startDate, endDate);
	}

	@Override
	public ItemWeekReport selectOrderInfo(Integer itemId, Date startDate, Date endDate) {
		return this.itemWeekReportMapper.selectShopOrderInfo(itemId, startDate, endDate);
	}

	@Override
	public Integer selectOrderShopNum(Integer itemId, Date startDate, Date endDate) {
		return this.itemWeekReportMapper.selectOrderShopNum(itemId, startDate, endDate);
	}

	@Override
	public ItemWeekReport selectRefundInfo(Integer itemId, Date startDate, Date endDate) {
		return this.itemWeekReportMapper.selectRefundInfo(itemId, startDate, endDate);
	}

	@Override
	public Integer selectOrderRefundShopNum(Integer itemId, Date startDate, Date endDate) {
		return this.itemWeekReportMapper.selectOrderRefundShopNum(itemId, startDate, endDate);
	}

	@Override
	public Integer selectAllShopNum(Integer itemId, Date startDate, Date endDate) {
		return this.itemWeekReportMapper.selectAllShopNum(itemId, startDate, endDate);
	}

	@Override
	public Integer selectStockNum(Integer itemId, Date startDate, Date endDate) {
		return this.itemWeekReportMapper.selectStockNum(itemId, startDate, endDate);
	}

	@Override
	public List<ItemWeekReport> findItemWeekReports(Date starttime, Date endtime, String itemName,
				String param, String isnew, String isrecommend, Integer onecate, Integer twocate, Integer cityId) {
		return this.itemWeekReportMapper.findItemWeekReports(starttime, endtime, itemName,
				param, isnew, isrecommend, onecate, twocate, cityId);
	}

	@Override
	public void insert(ItemWeekReport report) {
		this.itemWeekReportMapper.insert(report);
	}

	@Override
	public List<ItemWeekReport> findAllItems(Date startDate, Date endDate, Integer weekth) {
		return this.itemWeekReportMapper.findAllItems(startDate, endDate, weekth);
	}

}
