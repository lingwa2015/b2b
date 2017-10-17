package com.b2b.service.impl;

import com.b2b.Constant;
import com.b2b.common.dao.ItemMapper;
import com.b2b.common.dao.StockMapper;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.Stock;
import com.b2b.common.domain.StockExample;
import com.b2b.common.domain.StockExample.Criteria;
import com.b2b.page.Page;
import com.b2b.service.StockService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

	private static final Logger logger = LoggerFactory
			.getLogger(StockServiceImpl.class);

	@Autowired
	StockMapper stockMapper;

	@Autowired
	ItemMapper itemMapper;

	@Override
	public void create(Stock dto) {
		try {
			dto.setState(Constant.VALID_STATUS);
			dto.setCreateTime(new Date());
			dto.setModifyTime(new Date());
			dto.setWarningNum(100);
			stockMapper.insert(dto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void update(Stock dto) {
		try {
			dto.setState(Constant.VALID_STATUS);
			dto.setModifyTime(new Date());

			stockMapper.updateByPrimaryKeySelective(dto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateForAdd(int itemId, int num) {
        //Stock stock = this.findByItem(itemId);
        Stock stock = this.findByItemIngoreIsdown(itemId);
        if(stock==null){
        	stock = new Stock();
        	Item item = itemMapper.selectByPrimaryKey(itemId);
        	stock.setItemName(item.getItemName());
        	stock.setItemId(itemId);
        	stock.setNum(num);
        	Stock ss = this.stockMapper.findByItemid(itemId);
        	if(null==ss){
        		this.create(stock);
        	}
        }else{
        	this.stockMapper.updateStockNumAdd(itemId,num);
        }
	}

	@Override
	public void updateForReduce(int itemId, int num) {
		//Stock stock = this.findByItem(itemId);
		Stock stock = this.findByItemIngoreIsdown(itemId);
	        if(stock==null){
	        	stock = new Stock();
	        	Item item = itemMapper.selectByPrimaryKey(itemId);
	        	stock.setItemName(item.getItemName());
	        	stock.setItemId(itemId);
	        	stock.setNum(0);
	        	Stock ss = this.stockMapper.findByItemid(itemId);
	        	if(null==ss){
	        		this.create(stock);
	        	}
	        	throw new RuntimeException("该商品没有库存记录:"+stock.getItemName());
	        }else{
//	        	stock.setNum(stock.getNum()-num);
//	        	if(stock.getNum()<0){
//	        		throw new RuntimeException("库存不足 ：" + stock.getItemName());
//	        	}
//	        	this.update(stock);
	        	int i = this.stockMapper.updateStockNumReduce(itemId,num);
				if(i==0){
					throw new RuntimeException("库存不足 ：" + stock.getItemName());
				}
	        }
	}
	
	@Override
	public void updateForReduceCheck(int itemId, int num) {
		Stock stock = this.findByItemIngoreIsdown(itemId);
	        if(stock!=null){
	        	stock.setNum(stock.getNum()-num);
	        	this.update(stock);
	        }
	}

	@Override
	public Stock findByItem(int itemId) {
		try {
			StockExample example = new StockExample();
			Criteria criteria = example.createCriteria();
			criteria.andStateEqualTo(Constant.VALID_STATUS);
			criteria.andItemIdEqualTo(itemId);

			List<Stock> list = stockMapper.selectByExample(example);
			if (CollectionUtils.isNotEmpty(list)) {
				return  list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return null;
	}
	
	private Stock findByItemIngoreIsdown(int itemId) {
		try {

			List<Stock> list = stockMapper.findByItemIngoreIsdown(itemId);
			if (CollectionUtils.isNotEmpty(list)) {
				return  list.get(0);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return null;
	}
	

	@Override
	public Stock findById(int stockId) {
		return stockMapper.selectByPrimaryKey(stockId);
	}

	@Override
	public void delete(int id) {
		try {
			Stock dto = stockMapper.selectByPrimaryKey(id);
			dto.setState(Constant.DELETE_STATUS);
			dto.setModifyTime(new Date());

			stockMapper.updateByPrimaryKey(dto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

        @Override
        public void deleteByItemId(int itemId) {
            try {
                    Stock dto = this.findByItem(itemId);
                    if(dto!=null){
	                    dto.setState(Constant.DELETE_STATUS);
	                    dto.setModifyTime(new Date());

	                    stockMapper.updateByPrimaryKey(dto);
                    }
            } catch (Exception e) {
                    throw new RuntimeException(e);
            }
        }

		@Override
		public List<Stock> findByItem(List<Integer> itemIds) {
			try {
				StockExample example = new StockExample();
				Criteria criteria = example.createCriteria();
				criteria.andStateEqualTo(Constant.VALID_STATUS);
				criteria.andItemIdIn(itemIds);
				return stockMapper.selectByExample(example);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		@Override
		public List<Stock> findByItemId(ArrayList<Integer> itemIds) {
			return this.stockMapper.findByItemId(itemIds);
		}

		@Override
		public Page<HashMap<String,Object>> findPageWithModifyNum(Stock stock,
				int currentPage, int pageSize, Integer cityId, int onecate, int twocate, String isdown) {
			try {
				StockExample example = new StockExample();
				Criteria criteria = example.createCriteria();
				criteria.andStateEqualTo(Constant.VALID_STATUS);
				
				String itemName = stock.getItemName();
				if(StringUtils.isNotBlank(itemName)){
					criteria.andItemNameLike("%"+itemName+"%");
				}

				
				int count = stockMapper.countStockInfoWithModifyNum(itemName,cityId, onecate, twocate, isdown);
				if (count == 0) {
					return new Page<HashMap<String,Object>>();
				}
				int start = Page.calStartRow(currentPage, pageSize);
				int limit = pageSize;
				
				List<HashMap<String,Object>> stocks = this.stockMapper.getStockInfoWithModifyNum(itemName,limit,start,cityId, onecate, twocate, isdown);

				return new Page<HashMap<String,Object>>(currentPage, count, pageSize, stocks);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		//临时
		@Override
		public List<HashMap<String, Object>> temp() {
			return this.stockMapper.temp();
		}

		@Override
		public Stock findByItemIdIngoreIsdown(Integer itemId) {
			List<Stock> list = stockMapper.findByItemIdIngoreIsdown(itemId);
			if(!list.isEmpty()){
				return list.get(0);
			}
			return null;
		}

		@Override
		public List<Stock> findByCondition(String itemName, boolean alertFlag,
				int onecate, int twocate, String isdown, int unsalable,Integer cityId) {
			return this.stockMapper.findByCondition(itemName,alertFlag, onecate, twocate, isdown,unsalable,cityId);
		}

		@Override
		public Long findStockTotalMoney(String itemName, boolean alertFlag,
				int onecate, int twocate, String isdown ,int unsalable, Integer cityId) {
			return this.stockMapper.findStockTotalMoney(itemName,alertFlag, onecate, twocate, isdown,unsalable,cityId);
		}

		@Override
		public List<HashMap<String, Object>> findAllStock(String itemName,
				boolean alertFlag,int onecate, int twocate, String isdown,int unsalable,Integer cityId) {
			return this.stockMapper.findAllStock(itemName,alertFlag, onecate, twocate, isdown,unsalable,cityId);
		}

		@Override
		public void updateLastTime(Stock dto) {
			stockMapper.updateByPrimaryKeySelective(dto);
		}

		@Override
		public Long getStockTotalMoneyByCityId(Integer cityId) {
			return this.stockMapper.getStockTotalMoneyByCityId(cityId);
		}

}
