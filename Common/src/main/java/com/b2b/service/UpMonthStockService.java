package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.UpMonthStock;

public interface UpMonthStockService {

	public List<UpMonthStock> findLastMouth(String selectTime,
			Date start, Date end);

	public void insert(UpMonthStock upMonthStock);
}
