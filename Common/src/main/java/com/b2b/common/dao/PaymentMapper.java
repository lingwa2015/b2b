package com.b2b.common.dao;

import com.b2b.common.domain.Payment;
import com.b2b.common.domain.PaymentExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaymentMapper {
    int countByExample(PaymentExample example);

    int deleteByExample(PaymentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Payment record);

    int insertSelective(Payment record);

    List<Payment> selectByExample(PaymentExample example);

    Payment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Payment record, @Param("example") PaymentExample example);

    int updateByExample(@Param("record") Payment record, @Param("example") PaymentExample example);

    int updateByPrimaryKeySelective(Payment record);

    int updateByPrimaryKey(Payment record);

    List<Payment> findAll(@Param("date")Date date, @Param("cityId")Integer cityId);

    int deleteByDate(@Param("date")Date date, @Param("cityId")Integer cityId);

    Payment findSumPrice(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
                         @Param("param")String param, @Param("status")String status,
                         @Param("supplierName")String supplierName, @Param("cityId")Integer cityId);

    List<Payment> findPaymentSupplier(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
                           @Param("param")String param, @Param("status")String status,
                           @Param("supplierName")String supplierName, @Param("cityId")Integer cityId);

    List<Payment> findListBySupplierId(@Param("supplierId")Integer supplierId);

    Payment findBySupplierIdAndSumdate(@Param("supplierId")Integer supplierId, @Param("sumdate")Date sumdate);
}