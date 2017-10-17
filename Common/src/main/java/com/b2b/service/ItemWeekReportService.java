package com.b2b.service;

import com.b2b.common.domain.ItemWeekReport;

import java.util.Date;
import java.util.List;

public interface ItemWeekReportService {

	void deleteByDate(Date date4, Integer weekth);

	/**
	 * 获取所有商品数据
	 * @return
	 */
	List<ItemWeekReport> findAllItem();

	/**
	 * 获取数量（出库商品最小规格的数量（我们发货给便利店的数量））
	 * @param itemId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer selectOrderItemNum(Integer itemId, Date startDate, Date endDate);

	/**
	 * 获取消费信息
	 * @param itemId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	ItemWeekReport selectShopOrderInfo(Integer itemId, Date startDate, Date endDate);

	/**
	 * 获取在售店铺数
	 * @param itemId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer selectShopNum(Integer itemId, Date startDate, Date endDate);

	/**
	 * 获取订单数量、订单金额
	 * @param itemId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	ItemWeekReport selectOrderInfo(Integer itemId, Date startDate, Date endDate);

	/**
	 * 订单店铺数
	 * @param itemId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer selectOrderShopNum(Integer itemId, Date startDate, Date endDate);

	/**
	 * 退货数量、退款金额
	 * @param itemId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	ItemWeekReport selectRefundInfo(Integer itemId, Date startDate, Date endDate);

	/**
	 * 退货店铺数
	 * @param itemId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer selectOrderRefundShopNum(Integer itemId, Date startDate, Date endDate);

	/**
	 * 总店铺数
	 * @param itemId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer selectAllShopNum(Integer itemId, Date startDate, Date endDate);

	/**
	 * 累计库存数
	 * @param itemId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Integer selectStockNum(Integer itemId, Date startDate, Date endDate);

	List<ItemWeekReport> findItemWeekReports(Date starttime, Date endtime, String itemName,
                                               String param, String isnew, String isrecommend, Integer onecate, Integer twocate, Integer cityId);

	/**
	 * 录入数据
	 * @param report
	 * @return
	 */
	void insert(ItemWeekReport report);

    List<ItemWeekReport> findAllItems(Date startDate, Date endDate, Integer weekth);
}
