package com.b2b.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.b2b.common.domain.StockCheck;
import com.b2b.common.domain.StockCheckItem;
import com.b2b.page.Page;

/*
 * 盘库单
 */
public interface StockCheckService {

	Page<Pair<StockCheck, List<StockCheckItem>>> findStockCheck(String userName,String type, Date startTime, Date endTime, int currentPage, int pageSize, Integer cityId, String itemName);

	Pair<StockCheck, List<StockCheckItem>> findById(int id);

	void create(StockCheck order);

	void update(StockCheck order);

	void delete(int id);

	List<HashMap<String, Object>> findStockCheckAboveZero(Date start, Date end, Integer cityId);

	List<HashMap<String, Object>> findStockCheckBelowZero(Date start, Date end, Integer cityId);

	Long findTotalByCondition(String userName, String type, Date startTime,
			Date endTime, Integer cityId, String itemName);
}
