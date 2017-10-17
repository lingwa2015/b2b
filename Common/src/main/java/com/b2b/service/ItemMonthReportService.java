package com.b2b.service;

import com.b2b.common.domain.ItemMonthReport;

import java.util.Date;
import java.util.List;

public interface ItemMonthReportService {

	void deleteByDate(Date date4, Integer cityId);

	/**
	 * 获取所有商品数据
	 * @return
	 */
	List<ItemMonthReport> findAllItem();

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
	ItemMonthReport selectShopOrderInfo(Integer itemId, Date data);

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
	ItemMonthReport selectOrderInfo(Integer itemId, Date data);

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
	ItemMonthReport selectRefundInfo(Integer itemId, Date data);

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

	List<ItemMonthReport> findItemDailyReports(Date date, String itemName,
                                               String param, String isnew, String isrecommend, int onecate, int twocate, Integer cityId);


	/**
	 * 入库单数量
	 * @param itemId
	 * @param data
	 * @return
	 */
	Integer selectStorageOrderNum(Integer itemId, Date data);

	/**
	 * 入库数量
	 * @param itemId
	 * @param data
	 * @return
	 */
	Integer selectStorageNum(Integer itemId, Date data);

	/**
	 * 录入数据
	 * @param report
	 * @return
	 */
	void insert(ItemMonthReport report);

    List<ItemMonthReport> findAllItems(Date date1, Integer cityId);
}
