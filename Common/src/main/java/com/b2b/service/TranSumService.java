package com.b2b.service;

import java.util.Date;
import java.util.List;

import com.b2b.common.domain.Item;
import com.b2b.common.domain.TranSum;
import com.b2b.dto.TranSumDto;
import com.b2b.page.Page;

public interface TranSumService {

	public void create(TranSum dto);

	public String createBySumBatch(Date sumDate, Date date, Integer cityId);

	//public Page<TranSumDto> findPage(TranSumDto dto ,Date startTime, Date endTime, int currentPage ,int pageSize);

	public TranSumDto findDetail(int id);

//	public int delete(Date firstDate,Date sumDate);
	
	public List<TranSumDto> findAll(Date startTime, Date endTime);
	
	public TranSumDto findDetailNew(String userId,String years,String months);

	public Long findCurrentMonthSourcingMoney(Integer customerUserId);

	public String createByconsumeBatch2(Date endDate, Date date, Integer cityId);

	public String createByconsumeBatch(Date endPreDate, Date date2, Integer cityId);

	public String createSumBatch(Date endTime, Integer userid, Integer cityId);

	public List<TranSumDto> findByCondition(String userName, Date startTime,
			Date endTime, Integer cityId);

}
