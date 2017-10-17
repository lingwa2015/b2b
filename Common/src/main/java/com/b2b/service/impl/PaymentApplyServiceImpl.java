package com.b2b.service.impl;

import com.b2b.common.dao.PaymentApplyMapper;
import com.b2b.common.domain.PaymentApply;
import com.b2b.service.PaymentApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by a. on 2017/8/29.
 */
@Service
public class PaymentApplyServiceImpl implements PaymentApplyService {

    @Autowired
    private PaymentApplyMapper paymentApplyMapper;


    @Override
    public int creat(PaymentApply paymentApply) {
        return paymentApplyMapper.insertSelective(paymentApply);
    }

    @Override
    public List<PaymentApply> findList(Date startTime, Date endTime, Date createStartTime, Date createEndTime,
                                       Date paymentTime, String status, String supplierName, Integer cityId) {
        return paymentApplyMapper.findList(startTime, endTime, createStartTime,
                createEndTime, paymentTime, status, supplierName , cityId);
    }

    @Override
    public Long findPriceByStatus(Date startTime, Date endTime, Date createStartTime, Date createEndTime, Date paymentTime, String status, String supplierName, Integer cityId) {
        return paymentApplyMapper.findPriceByStatus(startTime, endTime, createStartTime,
                createEndTime, paymentTime, status, supplierName , cityId);
    }

    @Override
    public int update(PaymentApply paymentApply) {
        return paymentApplyMapper.updateByPrimaryKeySelective(paymentApply);
    }

    @Override
    public PaymentApply findById(Long id) {
        return paymentApplyMapper.selectByPrimaryKey(id);
    }
}
