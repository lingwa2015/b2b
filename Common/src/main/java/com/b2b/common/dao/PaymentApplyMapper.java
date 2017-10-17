package com.b2b.common.dao;

import com.b2b.common.domain.PaymentApply;
import com.b2b.common.domain.PaymentApplyExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaymentApplyMapper {
    int countByExample(PaymentApplyExample example);

    int deleteByExample(PaymentApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PaymentApply record);

    int insertSelective(PaymentApply record);

    List<PaymentApply> selectByExample(PaymentApplyExample example);

    PaymentApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PaymentApply record, @Param("example") PaymentApplyExample example);

    int updateByExample(@Param("record") PaymentApply record, @Param("example") PaymentApplyExample example);

    int updateByPrimaryKeySelective(PaymentApply record);

    int updateByPrimaryKey(PaymentApply record);

    List<PaymentApply> findList(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
                                @Param("createStartTime")Date createStartTime, @Param("createEndTime")Date createEndTime,
                                @Param("paymentTime")Date paymentTime, @Param("status")String status,
                                @Param("supplierName")String supplierName, @Param("cityId")Integer cityId);

    Long findPriceByStatus(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
                           @Param("createStartTime")Date createStartTime, @Param("createEndTime")Date createEndTime,
                           @Param("paymentTime")Date paymentTime, @Param("status")String status,
                           @Param("supplierName")String supplierName, @Param("cityId")Integer cityId);
}