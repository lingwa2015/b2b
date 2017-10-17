package com.b2b.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.b2b.common.domain.FreeDayReport;

public interface FreeDayReportService {

	FreeDayReport findByDate(Date queryDate);

	void insert(ArrayList<FreeDayReport> list2, Date startDate);

	List<FreeDayReport> findAll(Date starttime, Date endtime, int reseauId);

	FreeDayReport findByDateAndReseauId(Integer id, Date queryDate);

	List<FreeDayReport> findAllDayReport();

}
