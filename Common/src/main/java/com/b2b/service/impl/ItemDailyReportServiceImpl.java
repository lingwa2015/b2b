package com.b2b.service.impl;

import com.b2b.common.dao.ItemDailyReportMapper;
import com.b2b.common.domain.ItemDailyReport;
import com.b2b.common.domain.ItemDailyReportExample;
import com.b2b.common.util.DateUtil;
import com.b2b.service.ItemDailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemDailyReportServiceImpl implements ItemDailyReportService {
	@Autowired
	private ItemDailyReportMapper itemDailyReportMapper;

	@Override
	public void deleteByDate(Date date4) {
		Date date = DateUtil.beginOfMonth(date4);
		ItemDailyReportExample example = new ItemDailyReportExample();
		ItemDailyReportExample.Criteria criteria = example.createCriteria();
		criteria.andSumDateGreaterThanOrEqualTo(date);
		criteria.andSumDateLessThanOrEqualTo(date4);
		this.itemDailyReportMapper.deleteByExample(example);
	}

	@Override
	public List<ItemDailyReport> findAllItem() {
		return  this.itemDailyReportMapper.findAllItem();
	}

	@Override
	public Integer selectOrderItemNum(Integer itemId, Date data) {
		return this.itemDailyReportMapper.selectOrderItemNum(itemId, data);
	}

	@Override
	public ItemDailyReport selectShopOrderInfo(Integer itemId, Date data) {
		return this.itemDailyReportMapper.selectShopOrderInfo(itemId, data);
	}

	@Override
	public Integer selectShopNum(Integer itemId, Date data) {
		return this.itemDailyReportMapper.selectShopNum(itemId, data);
	}

	@Override
	public ItemDailyReport selectOrderInfo(Integer itemId, Date data) {
		return this.itemDailyReportMapper.selectShopOrderInfo(itemId, data);
	}

	@Override
	public Integer selectOrderShopNum(Integer itemId, Date data) {
		return this.itemDailyReportMapper.selectOrderShopNum(itemId, data);
	}

	@Override
	public ItemDailyReport selectRefundInfo(Integer itemId, Date data) {
		return this.itemDailyReportMapper.selectRefundInfo(itemId, data);
	}

	@Override
	public Integer selectOrderRefundShopNum(Integer itemId, Date data) {
		return this.itemDailyReportMapper.selectOrderRefundShopNum(itemId, data);
	}

	@Override
	public Integer selectAllShopNum(Integer itemId, Date data) {
		return this.itemDailyReportMapper.selectAllShopNum(itemId, data);
	}

	@Override
	public Integer selectStockNum(Integer itemId, Date data) {
		return this.itemDailyReportMapper.selectStockNum(itemId, data);
	}

	@Override
	public List<ItemDailyReport> findItemDailyReports(Date starttime, Date endtime, String itemName,
				String param, String isnew, String isrecommend, Integer onecate, Integer twocate, Integer cityId) {
		return this.itemDailyReportMapper.findItemDailyReports(starttime, endtime, itemName,
				param, isnew, isrecommend, onecate, twocate, cityId);
	}

	@Override
	public void insert(ItemDailyReport report) {
		this.itemDailyReportMapper.insert(report);
	}

	@Override
	public List<ItemDailyReport> findAllItems(Date startDate) {
		return this.itemDailyReportMapper.findAllItems(startDate);
	}

	@Override
	public int insertItemDailyReportList(List<ItemDailyReport> reports) {
		return this.itemDailyReportMapper.insertItemDailyReportList(reports);
	}


}
