package com.b2b.service;

import com.b2b.common.domain.Stock;
import com.b2b.page.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface StockService {

	void create(Stock dto);

	void update(Stock dto);

	void updateForAdd(int itemId, int num);

	void updateForReduce(int itemId, int num);
	
	void updateForReduceCheck(int itemId, int num);

	Stock findByItem(int itemId);

	Stock findById(int stockId);

	void delete(int id);

    void deleteByItemId(int itemId);
    
    List<Stock> findByItem(List<Integer> itemIds);

	Page<HashMap<String,Object>> findPageWithModifyNum(Stock stock, int currentPage,
                                                       int pageSize, Integer cityId, int onecate, int twocate, String isdown);
	//临时
    List<HashMap<String, Object>> temp();

	List<Stock> findByItemId(ArrayList<Integer> itemIds);

	Stock findByItemIdIngoreIsdown(Integer itemId);

	List<Stock> findByCondition(String itemName, boolean alertFlag,
                                int onecate, int twocate, String isdown, int unsalable, Integer cityId);

	Long findStockTotalMoney(String itemName, boolean alertFlag,
                             int onecate, int twocate, String isdown, int unsalable, Integer cityId);

	List<HashMap<String, Object>> findAllStock(String itemName,
                                               boolean alertFlag, int onecate, int twocate, String isdown, int unsalable, Integer cityId);

	void updateLastTime(Stock stock);

	Long getStockTotalMoneyByCityId(Integer cityId);
	
}
