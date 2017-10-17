package com.b2b.service;

import com.b2b.common.domain.ItemMonthReport;
import com.b2b.common.domain.Payment;
import com.b2b.common.domain.PaymentApply;

import java.util.Date;
import java.util.List;

public interface PaymentService {


	List<Payment> findAll(Date date, Integer cityId);

	int deleteByDate(Date date, Integer cityId);

	int insert(Payment payment);

	Payment findSumPrice(Date startTime, Date endTime, String param, String status, String supplierName, Integer cityId);

	List<Payment> findList(Date startTime, Date endTime, String param, String status, String supplierName, Integer cityId);

	List<Payment> findListBySupplierId(Integer supplierId);

    Payment findById(Long id);

    Payment findBySupplierIdAndSumdate(Integer supplierId, Date sumdate);

	int update(Payment payment);
}
