package com.b2b.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.b2b.common.domain.Refund;
import com.b2b.common.domain.RefundItem;
import com.b2b.dto.RefundDto;
import com.b2b.page.Page;

public interface RefundService {

    Pair<Refund, List<RefundItem>> findById(int id);

    void createRefund(Refund refund);

    void updateRefund(Refund refund);

    void delete(int id);

    List<Refund> find(int userId,Date startTime, Date endTime);

    void updateRefundTotalFee();

	//List<Refund> findTranSumRefunds(Date startTime, Date endTime);

	void createRefundAndMoney(Refund refund, Integer id);

	void deleteAndBackMoney(int id, Integer id2);

	int countMoneyByUserAndTime(Integer id, Date firstDate, Date sumDate);

	int countNumByUserAndTime(Integer id, Date firstDate, Date sumDate);

	List<Refund> findRefundByShopId(Integer shopId);

	Refund findRefundById(Integer id);

	List<HashMap<String, Object>> findItemInfoByOrderNo(Integer id);

	int findRefundNumByMonth(Integer id, Date date);
	
	/**
	 * 福利店月退款金额笔数
	 * @param id
	 * @param date2
	 * @param date1
	 * @return
	 */
	Refund findCurrentMonthFreeShopRefundFee(Integer id, Date date2, Date date1);
	
	Long findTotal(String userName, Date startTime, Date endTime, String param, int reseauId, Integer cityId, String itemName);
	/**
	 * 添加退货单，待确认
	 * @param refund
	 */
	void createRefundNotUpdateStock(Refund refund);

	void deleteNotStock(int id);

	void confirm(int id);

	Integer findBeConfirmRefundByCityId(Integer cityId);

	List<Refund> findByCondition(String userName,
			Date startTime, Date endTime, String param, int reseauId, Integer cityId, String itemName);

	List<Refund> findByTimeAndCityId(Date start, Date end, Integer cityId);

	List<Refund> findTranSumRefundsByCityId(Date startTime, Date endTime,
			Integer cityId);

	Refund findRefundFeeAndRefundNumByDayAndUserId(Integer userId, Date date);
}
