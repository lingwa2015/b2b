package com.b2b.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import com.b2b.common.dao.StockCheckItemMapper;
import com.b2b.common.dao.StockCheckMapper;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.StockCheck;
import com.b2b.common.domain.StockCheckExample;
import com.b2b.common.domain.StockCheckExample.Criteria;
import com.b2b.common.domain.StockCheckItem;
import com.b2b.common.domain.StockCheckItemExample;
import com.b2b.page.Page;
import com.b2b.service.CustomerService;
import com.b2b.service.StockCheckService;
import com.b2b.service.StockService;



@Service
public class StockCheckServiceImpl implements StockCheckService{

	private static final Logger logger = LoggerFactory.getLogger(StockCheckServiceImpl.class);


	@Autowired
	StockCheckMapper stockCheckMapper;

	@Autowired
	StockCheckItemMapper stockCheckItemMapper;

	@Autowired
	StockService stockService;
	
	@Autowired
	CustomerService customerService;

	@Override
	public Page<Pair<StockCheck, List<StockCheckItem>>> findStockCheck(String userName,String type, 
			Date startTime, Date endTime, int currentPage, int pageSize, Integer cityId,String itemName) {
		Page<Pair<StockCheck, List<StockCheckItem>>> page = null;

		StockCheckExample stockCheckExample = new StockCheckExample();
		stockCheckExample.setOrderByClause("executed_time desc,id desc");
		//stockCheckExample.setOrderByClause("executed_time desc");
		Criteria criteria = stockCheckExample.createCriteria();
		criteria.andStateEqualTo(Constant.VALID_STATUS);
		criteria.andCityIdEqualTo(cityId);
		if(!StringUtils.isEmpty(userName)){
			List<Integer> ids = this.customerService.findIdsByUserName(userName);
			if(ids.size()>0){
				criteria.andUserIdIn(ids);
			}
		}
		if(!StringUtils.isEmpty(type)){
			criteria.andTypeEqualTo(Integer.parseInt(type));
		}
		// 开始时间
		if (startTime != null) {
			criteria.andExecutedTimeGreaterThanOrEqualTo(startTime);
		}

		// 结束时间
		if (endTime != null) {
			criteria.andExecutedTimeLessThanOrEqualTo(endTime);
		}
		
		if(!StringUtils.isEmpty(itemName)){
			List<Integer> lists = this.stockCheckItemMapper.findStockCheckIdsByItemName(itemName);
			criteria.andIdIn(lists);
		}

		int count = stockCheckMapper.countByExample(stockCheckExample);
		if (count == 0) {
			currentPage = 1;
			page = new Page<Pair<StockCheck, List<StockCheckItem>>>(currentPage, count,
					pageSize, null);
			return page;
		}
		int start = Page.calStartRow(currentPage, pageSize);

		stockCheckExample.setLimit(pageSize);
		stockCheckExample.setStart(start);
		stockCheckExample.setLimitFlag(true);

		List<StockCheck> stockChecks = stockCheckMapper.selectByExample(stockCheckExample);
		List<Pair<StockCheck, List<StockCheckItem>>> stockCheckItemList = new ArrayList<Pair<StockCheck, List<StockCheckItem>>>();
		for (StockCheck stockCheck2 : stockChecks) {
			List<StockCheckItem> itemList = this.findStockCheckItem(stockCheck2.getId());
			if(stockCheck2.getUserId()!=null){
				CustomerUser user = this.customerService.findById(stockCheck2.getUserId());
				stockCheck2.setUserName(user.getUserName());
			}
			if (CollectionUtils.isNotEmpty(itemList)) {
				stockCheckItemList.add(Pair.of(stockCheck2, itemList));
			}
		}

		page = new Page<Pair<StockCheck, List<StockCheckItem>>>(currentPage,
				count, pageSize, stockCheckItemList);

		return page;
	}

	@Override
	public Pair<StockCheck, List<StockCheckItem>> findById(int id) {
		StockCheck stockCheck = stockCheckMapper.selectByPrimaryKey(id);

		List<StockCheckItem> itemList = this.findStockCheckItem(id);

		return Pair.of(stockCheck, itemList);
	}

	private List<StockCheckItem> findStockCheckItem(int stockCheckId){
		StockCheckItemExample itemExample = new StockCheckItemExample();
		itemExample.createCriteria().andCheckIdEqualTo(stockCheckId);
		List<StockCheckItem> itemList = stockCheckItemMapper.selectByExample(itemExample);

		if(CollectionUtils.isEmpty(itemList)){
			logger.warn("无法找到入库单商品:"+stockCheckId);
		}
		return itemList;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	@Override
	public void create(StockCheck stockCheck) {
		stockCheck.setState(Constant.VALID_STATUS);
		stockCheck.setCreatedTime(new Date());
		stockCheck.setModifiedTime(new Date());
		this.stockCheckMapper.insert(stockCheck);

		List<StockCheckItem> itemList = stockCheck.getItemList();
		for(StockCheckItem item : itemList){
			item.setCheckId(stockCheck.getId());
			this.stockCheckItemMapper.insert(item);

			if(item.getNum()>=0){
				stockService.updateForAdd(item.getItemId(), item.getNum());
			}else{
				stockService.updateForReduce(item.getItemId(), -item.getNum());
			}
		}


	}

	@Override
	public void update(StockCheck stockCheck) {
		stockCheck.setState(Constant.VALID_STATUS);
		stockCheck.setModifiedTime(new Date());
		this.stockCheckMapper.updateByPrimaryKeySelective(stockCheck);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void delete(int id) {

		 Pair<StockCheck, List<StockCheckItem>> pair = this.findById(id);

		//更新库存
			List<StockCheckItem> itemList = pair.getRight();
			if(CollectionUtils.isNotEmpty(itemList)){
				for(StockCheckItem item : itemList){
					if(item.getNum()>0){
						stockService.updateForReduce(item.getItemId(), item.getNum());
					}else{
						stockService.updateForAdd(item.getItemId(), -item.getNum());
					}

				}
			}

		StockCheck stockCheck = pair.getLeft();
		stockCheck.setState(Constant.DELETE_STATUS);
		stockCheck.setModifiedTime(new Date());
		this.stockCheckMapper.updateByPrimaryKeySelective(stockCheck);


	}

	@Override
	public List<HashMap<String, Object>> findStockCheckAboveZero(Date start, Date end,Integer cityId) {
		return this.stockCheckMapper.findStockCheckAboveZero(start,end,cityId);
	}

	@Override
	public List<HashMap<String, Object>> findStockCheckBelowZero(Date start, Date end,Integer cityId) {
		return this.stockCheckMapper.findStockCheckBelowZero(start,end,cityId);
	}

	@Override
	public Long findTotalByCondition(String userName, String type,
			Date startTime, Date endTime, Integer cityId,String itemName) {
		return this.stockCheckMapper.findTotalByCondition(userName,type,startTime,endTime,cityId,itemName);
	}

}
