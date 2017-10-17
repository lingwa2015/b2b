package com.b2b.service;

import com.b2b.common.domain.ItemDailyReport;

import java.util.Date;
import java.util.List;

public interface ItemDailyReportService {

	void deleteByDate(Date date4);

	/**
	 * 获取所有商品数据
	 * @return
	 */
	List<ItemDailyReport> findAllItem();

	/**
	 * 获取数量（出库商品最小规格的数量（我们发货给便利店的数量））
	 * @param itemId
	 * @param data
	 * @return
	 */
	Integer selectOrderItemNum(Integer itemId, Date data);

	/**
	 * 获取消费信息
	 * @param itemId
	 * @param data
	 * @return
	 */
	ItemDailyReport selectShopOrderInfo(Integer itemId, Date data);

	/**
	 * 获取在售店铺数
	 * @param itemId
	 * @param data
	 * @return
	 */
	Integer selectShopNum(Integer itemId, Date data);

	/**
	 * 获取订单数量、订单金额
	 * @param itemId
	 * @param data
	 * @return
	 */
	ItemDailyReport selectOrderInfo(Integer itemId, Date data);

	/**
	 * 订单店铺数
	 * @param itemId
	 * @param data
	 * @return
	 */
	Integer selectOrderShopNum(Integer itemId, Date data);

	/**
	 * 退货数量、退款金额
	 * @param itemId
	 * @param data
	 * @return
	 */
	ItemDailyReport selectRefundInfo(Integer itemId, Date data);

	/**
	 * 退货店铺数
	 * @param itemId
	 * @param data
	 * @return
	 */
	Integer selectOrderRefundShopNum(Integer itemId, Date data);

	/**
	 * 总店铺数
	 * @param itemId
	 * @param data
	 * @return
	 */
	Integer selectAllShopNum(Integer itemId, Date data);

	/**
	 * 累计库存数
	 * @param itemId
	 * @param data
	 * @return
	 */
	Integer selectStockNum(Integer itemId, Date data);

	List<ItemDailyReport> findItemDailyReports(Date starttime, Date endtime, String itemName,
			String param, String isnew, String isrecommend, Integer onecate, Integer twocate, Integer cityId);

	/**
	 * d录入数据
	 * @param report
	 * @return
	 */
	void insert(ItemDailyReport report);

    List<ItemDailyReport> findAllItems(Date startDate);

	int insertItemDailyReportList(List<ItemDailyReport> reports);
}
