package com.b2b.service;

import com.b2b.common.domain.PaymentApply;

import java.util.Date;
import java.util.List;

/**
 * Created by a. on 2017/8/29.
 */
public interface PaymentApplyService {


    int creat(PaymentApply paymentApply);

    List<PaymentApply> findList(Date startTime, Date endTime, Date createStartTime, Date createEndTime,
                                Date paymentTime, String status, String supplierName, Integer cityId);

    Long findPriceByStatus(Date startTime, Date endTime, Date createStartTime, Date createEndTime, Date paymentTime,
                           String status, String supplierName, Integer cityId);

    int update(PaymentApply paymentApply);

    PaymentApply findById(Long id);
}
