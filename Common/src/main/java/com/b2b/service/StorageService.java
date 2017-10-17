package com.b2b.service;

import com.b2b.common.domain.Storage;
import com.b2b.common.domain.StorageItem;
import com.b2b.page.Page;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Date;
import java.util.List;

/*
 * 入库单
 */
public interface StorageService {

	Page<Pair<Storage, List<StorageItem>>> findStorage(Storage storage, Date startTime, Date endTime, String numbers, String supplierName, int currentPage, int pageSize, Integer cityId, String itemName);

	Pair<Storage, List<StorageItem>> findById(int id);

	void create(Storage order);

	void update(Storage order);

	void delete(int id);

	void updateTotalFee();
	
	StorageItem storageItemTotal(Date startTime, Date endTime, String itemName,String supplierId, String catid, Integer cityId);
	
	Page<StorageItem> storageItemList(Date startTime, Date endTime, String itemName,String supplierId, String catid, int currentPage, int pageSize, Integer cityId);
	
	List<StorageItem> storageItem(Date startTime, Date endTime, String itemName,String supplierId, String catid, Integer cityId);
	
	//每日入库金额查询
	List<Storage> findByTimeAndCityId(Date start, Date end, Integer id);

	List<Storage> findPurchaseBysupplierIdAndSumdate(Integer supplierId, Date sumdate);

    List<StorageItem> findOutstockItem(Integer supplierId, Date sumdate);

	List<StorageItem> selectBySupplierIdAndSumdate(Integer supplierId, Date sumdate);
}
