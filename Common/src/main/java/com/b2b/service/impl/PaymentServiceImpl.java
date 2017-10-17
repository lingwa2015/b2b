package com.b2b.service.impl;

import com.b2b.common.dao.PaymentApplyMapper;
import com.b2b.common.dao.PaymentMapper;
import com.b2b.common.domain.Payment;
import com.b2b.common.domain.PaymentApply;
import com.b2b.service.PaymentApplyService;
import com.b2b.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by a. on 2017/8/29.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;


    @Override
    public List<Payment> findAll(Date date, Integer cityId) {
        return paymentMapper.findAll(date, cityId);
    }

    @Override
    public int deleteByDate(Date date, Integer cityId) {
        return paymentMapper.deleteByDate(date, cityId);
    }

    @Override
    public int insert(Payment payment) {
        return paymentMapper.insertSelective(payment);
    }

    @Override
    public Payment findSumPrice(Date startTime, Date endTime, String param, String status, String supplierName, Integer cityId) {
        return paymentMapper.findSumPrice(startTime, endTime, param, status, supplierName , cityId);
    }

    @Override
    public List<Payment> findList(Date startTime, Date endTime, String param, String status, String supplierName, Integer cityId) {
        return paymentMapper.findPaymentSupplier(startTime, endTime, param, status, supplierName , cityId);
    }

    @Override
    public List<Payment> findListBySupplierId(Integer supplierId) {
        return paymentMapper.findListBySupplierId(supplierId);
    }

    @Override
    public Payment findById(Long id) {
        return paymentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Payment findBySupplierIdAndSumdate(Integer supplierId, Date sumdate) {
        return paymentMapper.findBySupplierIdAndSumdate(supplierId, sumdate);
    }

    @Override
    public int update(Payment payment) {
        return paymentMapper.updateByPrimaryKeySelective(payment);
    }
}
