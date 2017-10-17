package com.b2b.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.*;
import com.b2b.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.b2b.Constant;
import com.b2b.common.dao.StorageItemMapper;
import com.b2b.common.dao.StorageMapper;
import com.b2b.common.domain.StorageExample.Criteria;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.page.Page;
import com.google.common.collect.Maps;



@Service
public class StorageServiceImpl implements StorageService{

	private static final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);


	@Autowired
	StorageMapper storageMapper;

	@Autowired
	StorageItemMapper storageItemMapper;

	@Autowired
	StockService stockService;

	@Autowired
	PurchaseItemService purchaseItemService;

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	ItemService itemService;

	@Override
	public Page<Pair<Storage, List<StorageItem>>> findStorage(Storage storage,
			Date startTime, Date endTime,String numbers, String supplierName, int currentPage, int pageSize, Integer cityId,String itemName) {
		Page<Pair<Storage, List<StorageItem>>> page = null;

		int count = storageMapper.countByCondition(startTime,endTime,numbers,supplierName,cityId,itemName);
		if (count == 0) {
			currentPage = 1;
			page = new Page<Pair<Storage, List<StorageItem>>>(currentPage, count,
					pageSize, null);
			return page;
		}
		int start = Page.calStartRow(currentPage, pageSize);

		List<Storage> storages = storageMapper.selectByCondition(startTime,endTime,numbers,supplierName,cityId,itemName,start,pageSize);
		List<Pair<Storage, List<StorageItem>>> storageItemList = new ArrayList<Pair<Storage, List<StorageItem>>>();
		for (Storage storage2 : storages) {
			List<StorageItem> itemList = this.storageItemMapper.findByStorageId(storage2.getId());
			if (CollectionUtils.isNotEmpty(itemList)) {
				storageItemList.add(Pair.of(storage2, itemList));
			}
		}

		page = new Page<Pair<Storage, List<StorageItem>>>(currentPage,
				count, pageSize, storageItemList);

		return page;
	}

	@Override
	public Pair<Storage, List<StorageItem>> findById(int id) {
		//Storage storage = storageMapper.selectByPrimaryKey(id);
		Storage storage = storageMapper.selectById(id);
		List<StorageItem> itemList = this.storageItemMapper.findByStorageId(id);
		return Pair.of(storage, itemList);
	}

	private List<StorageItem> findStorageItem(int storageId){
		StorageItemExample itemExample = new StorageItemExample();
		itemExample.createCriteria().andStorageIdEqualTo(storageId);
		List<StorageItem> itemList = storageItemMapper.selectByExample(itemExample);

		if(CollectionUtils.isEmpty(itemList)){
			logger.warn("无法找到入库单商品:"+storageId);
		}
		return itemList;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void create(Storage storage) {
		storage.setState(Constant.VALID_STATUS);
		storage.setCreatedTime(new Date());
		storage.setModifiedTime(new Date());
		this.storageMapper.insert(storage);

		List<StorageItem> itemList = storage.getStorageItemList();
		for(StorageItem item : itemList){
			item.setStorageId(storage.getId());
			this.storageItemMapper.insert(item);
			stockService.updateForAdd(item.getItemId(), item.getNum());
		}


	}

	@Override
	public void update(Storage storage) {
		storage.setState(Constant.VALID_STATUS);
		storage.setModifiedTime(new Date());
		this.storageMapper.updateByPrimaryKeySelective(storage);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void delete(int id) {

		 Pair<Storage, List<StorageItem>> pair = this.findById(id);

		Storage storage = pair.getLeft();
		String[] numbers = storage.getNumber().split(":");
		String purchaseId = "";
		boolean purchaseFlag = false;
		 if(numbers.length >= 1) {
			 if (numbers[0].equals("From purchase")) {
			 	if(numbers.length == 3){
					purchaseId = numbers[2];
				} else {
					purchaseId = numbers[1];
				}
				 purchaseFlag = true;
			 }

			 //更新库存
			 List<StorageItem> itemList = pair.getRight();
			 if (CollectionUtils.isNotEmpty(itemList)) {
				 for (StorageItem item : itemList) {
					 stockService.updateForReduce(item.getItemId(), item.getNum());

					 if (purchaseFlag) {
						 PurchaseItem oldPurchaseItem = this.purchaseItemService.findbyPurchaseIdAndItemid(purchaseId, item.getItemId());
						 Integer oldPurchasedNum = oldPurchaseItem.getPurchasedNum() == null ? 0 : oldPurchaseItem.getPurchasedNum();
						 oldPurchaseItem.setPurchasedNum(oldPurchasedNum - item.getBuyNum());
						 if (oldPurchaseItem.getPurchasedNum() <= 0) {
							 oldPurchaseItem.setStockFlag(0);
						 } else {
							 oldPurchaseItem.setStockFlag(1);
						 }
						 this.purchaseItemService.update(oldPurchaseItem);
					 }
				 }
			 }
			 if (purchaseFlag) {
			 	Purchase purchase = purchaseService.findPurchaseById(purchaseId);
				 Integer endCount = this.purchaseItemService.findEndCountByPurchaseId(purchaseId);
				 if (endCount == 0) {
					 purchase.setStatus(1);
				 } else {
					 purchase.setStatus(6);
				 }
				 purchaseService.updateByPrimaryKeySelective(purchase);
			 }
			 storage.setState(Constant.DELETE_STATUS);
			 storage.setModifiedTime(new Date());
			 this.storageMapper.updateByPrimaryKeySelective(storage);

		 }
	}
	
	@Override
	public StorageItem storageItemTotal(Date startTime, Date endTime, String itemName,String supplierId,String catid, Integer cityId){
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("item_name", itemName);
		paramMap.put("supplier_id", supplierId);
		paramMap.put("catid", catid);
		paramMap.put("cityId", cityId);
		StorageItem storageItems = this.storageItemMapper.selectStorageItemTotal(paramMap);
		return storageItems;
	}
	
	@Override
	public List<StorageItem> storageItem(Date startTime, Date endTime, String itemName,String supplierId, String catid, Integer cityId)
	{
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("item_name", itemName);
		paramMap.put("supplier_id", supplierId);
		paramMap.put("catid", catid);
		paramMap.put("cityId", cityId);
		List<StorageItem> storageItem = this.storageItemMapper.selectStorageItemByDateTime(paramMap);
		return storageItem;
	}
	
	@Override
	public Page<StorageItem> storageItemList(Date startTime, Date endTime, String itemName,String supplierId,String catid, int currentPage, int pageSize,Integer cityId)
	{
		Page<StorageItem> page = null;
		int start = Page.calStartRow(currentPage, pageSize);
		int limit = pageSize;
		HashMap<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("item_name", itemName);
		paramMap.put("supplier_id", supplierId);
		paramMap.put("catid", catid);
		paramMap.put("cityId", cityId);
		paramMap.put("start", Integer.valueOf(start));
		paramMap.put("limit", Integer.valueOf(limit));
		int count = this.storageItemMapper.countByStorageItemSql(paramMap);
		if (count == 0) {
			currentPage = 1;
			page = new Page<StorageItem>(currentPage, count, pageSize, null);
			return page;
		}
		List<StorageItem> storageItem = this.storageItemMapper.selectStorageItemByDateTime(paramMap);
		page = new Page<StorageItem>(currentPage, count, pageSize, storageItem);
		return page;
	}

	@Override
	public void updateTotalFee() {
		StorageExample storageExample = new StorageExample();
		Criteria criteria = storageExample.createCriteria();
		criteria.andStateEqualTo(Constant.VALID_STATUS);

		List<Storage> storageList = storageMapper.selectByExample(storageExample);
		if(CollectionUtils.isNotEmpty(storageList)){
			for(Storage storage : storageList){
				List<StorageItem> itemList = this.findStorageItem(storage.getId());
				if(CollectionUtils.isNotEmpty(itemList)){
					long totalFee = 0;

					for(StorageItem item : itemList){
						Long fee = 0l;
						if(fee==null||fee==0){
							fee = 0l;
                           Item i = itemService.findById(item.getItemId());
                           if(i!=null){
                        	   fee = i.getCostPrice()*item.getNum();
                        	   item.setTotalFee(fee);
                        	   storageItemMapper.updateByPrimaryKey(item);
                           }
						}
						totalFee += fee;
					}

					if(storage.getTotalFee()==null || totalFee!=storage.getTotalFee()){
						logger.warn("更新入库单总金额,storageId:"+storage.getId()+",oldTotalFee:"+storage.getTotalFee()+",totalFee:"+totalFee);
						storage.setTotalFee(totalFee);

						this.update(storage);
					}
				}
			}
		}

	}

	@Override
	public List<Storage> findByTimeAndCityId(Date start, Date end, Integer id) {
		return this.storageMapper.findByTimeAndCityId(start,end,id);
	}

	@Override
	public List<Storage> findPurchaseBysupplierIdAndSumdate(Integer supplierId, Date sumdate) {
		return this.storageMapper.findPurchaseBysupplierIdAndSumdate(supplierId, sumdate);
	}

	@Override
	public List<StorageItem> findOutstockItem(Integer supplierId, Date sumdate) {
		return this.storageItemMapper.findOutstockItem(supplierId, sumdate);
	}

	@Override
	public List<StorageItem> selectBySupplierIdAndSumdate(Integer supplierId, Date sumdate) {
		return this.storageItemMapper.selectBySupplierIdAndSumdate(supplierId, sumdate);
	}
}
