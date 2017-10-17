package com.b2b.common.dao;

import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.PaymentAccountLock;
import com.b2b.common.domain.PaymentAccountLockExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaymentAccountLockMapper {
    int countByExample(PaymentAccountLockExample example);

    int deleteByExample(PaymentAccountLockExample example);

    int insert(PaymentAccountLock record);

    int insertSelective(PaymentAccountLock record);

    List<PaymentAccountLock> selectByExample(PaymentAccountLockExample example);

    int updateByExampleSelective(@Param("record") PaymentAccountLock record, @Param("example") PaymentAccountLockExample example);

    int updateByExample(@Param("record") PaymentAccountLock record, @Param("example") PaymentAccountLockExample example);

    PaymentAccountLock findisLock(@Param("aLock")PaymentAccountLock aLock, @Param("lockdate")Date lockdate);

    PaymentAccountLock findisLockByCityId(@Param("aLock")PaymentAccountLock aLock, @Param("lockdate")Date lockdate,
                                   @Param("cityId")Integer cityId);
}