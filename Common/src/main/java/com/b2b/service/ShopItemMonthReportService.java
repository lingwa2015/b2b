package com.b2b.service;

import com.b2b.common.domain.ShopItemMonthReport;

import java.util.Date;
import java.util.List;

public interface ShopItemMonthReportService {

	void deleteByDate(Date date4);

	/**
	 * 获取商品店铺月报基本数据
	 * @param data
	 * @param cityId
	 * @return
	 */
	List<ShopItemMonthReport> findShopItemsByDate(Date data, Integer cityId);


	/**
	 * 获取消费数量：商品被消费了多少数量
	 * @param shopID
	 * @param itemId
	 * @param data
	 * @return
	 */
	Integer selectShopOrderItemSaleCount(Integer shopID, Integer itemId, Date data);

	/**
	 * 获取退货数量：商品一共被退货的数量
	 * @param shopID
	 * @param itemId
	 * @param data
	 * @return
	 */
	Integer selectShopOrderItemRefundNum(Integer shopID, Integer itemId, Date data);

	/**
	 * 获取累计库存（备注：都是发生在对应周期内的累计）
	 * @param shopID
	 * @param itemId
	 * @param data
	 * @return
	 */
	Integer selectShopOrderItemStockNum(Integer shopID, Integer itemId, Date data);

	/**
	 * 删除数据
	 * @param date
	 * @param cityId
	 * @return
	 */
	void deleteByDateAndCityId(Date date, Integer cityId);

	/**
	 * d录入数据
	 * @param report
	 * @return
	 */
	void insert(ShopItemMonthReport report);


	List<ShopItemMonthReport> findShopItemMontList(Date dateTime, String itemName, String username, Integer reseauId, Integer cityId, String isnew, String param);

    List<ShopItemMonthReport> findShopItems(Date date1, Integer cityId);
}
