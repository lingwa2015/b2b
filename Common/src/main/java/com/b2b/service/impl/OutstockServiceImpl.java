package com.b2b.service.impl;

import com.b2b.common.dao.OutstockItemMapper;
import com.b2b.common.dao.OutstockMapper;
import com.b2b.common.dao.PurchaseItemMapper;
import com.b2b.common.dao.PurchaseMapper;
import com.b2b.common.domain.*;
import com.b2b.service.ItemService;
import com.b2b.service.OutstockService;
import com.b2b.service.PurchaseService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OutstockServiceImpl implements OutstockService {

	@Autowired
	OutstockMapper outstockMapper;

	@Autowired
	OutstockItemMapper outstockItemMapper;

	@Autowired
	ItemService itemService;


	@Override
	public int insert(Outstock outstock) {
		return this.outstockMapper.insert(outstock);
	}

	@Override
	public Long getMaxOutstockId() {
		return this.outstockMapper.getMaxOutstockId();
	}

	@Override
	public void createOutstock(Outstock outstock) {
		// 如果执行日期为空，则默认为当前日期
		if (outstock.getExecutedTime() == null) {
			outstock.setExecutedTime(new Date());
		}
		this.outstockMapper.insertSelective(outstock);

		for (OutstockItem outstockItem : outstock.getOutstockItemList()) {
			this.outstockItemMapper.insert(outstockItem);
		}
	}

	@Override
	public List<Outstock> findOutstocksAndOutstocksItemByCondition(Date startTime, Date endTime, String supplierName, String itemName, String param, Integer cityId) {
		return this.outstockMapper.findOutstocksAndOutstocksItemByCondition(startTime, endTime, supplierName, itemName, param, cityId);
	}

	@Override
	public Outstock findOutstockById(Long outstockId) {
		return this.outstockMapper.selectByPrimaryKey(outstockId);
	}

	@Override
	public int updateOutstockStatus(Long outstockId, Integer status) {
		Outstock outstock = new Outstock();
		outstock.setId(outstockId);
		outstock.setStatus(status);
		return this.outstockMapper.updateByPrimaryKeySelective(outstock);
	}

	@Override
	public Pair<Outstock, List<OutstockItem>> findById(Long outstockId) {
		Outstock outstock = outstockMapper.selectByPrimaryKey(outstockId);
		List<OutstockItem> itemList = this.outstockItemMapper.findByOutstockId(outstockId);
		return Pair.of(outstock, itemList);
	}

	@Override
	public Integer findPreparationNum(Integer cityId) {
		return this.outstockMapper.findPreparationNum(cityId);
	}

	@Override
	public Long findTotalPrice(Date startTime, Date endTime, String supplierName, String itemName, String param, Integer cityId) {
		return this.outstockMapper.findTotalPrice(startTime, endTime, supplierName, itemName, param, cityId);
	}

	@Override
	public int updateOutstock(Outstock outstock) {
		return this.outstockMapper.updateByPrimaryKeySelective(outstock);
	}


}
