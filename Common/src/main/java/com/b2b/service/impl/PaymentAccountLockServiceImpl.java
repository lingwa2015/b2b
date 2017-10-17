package com.b2b.service.impl;

import com.b2b.common.dao.PaymentAccountLockMapper;
import com.b2b.common.domain.PaymentAccountLock;
import com.b2b.common.domain.PaymentAccountLockExample;
import com.b2b.service.PaymentAccountLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by a. on 2017/8/29.
 */
@Service
public class PaymentAccountLockServiceImpl implements PaymentAccountLockService {

    @Autowired
    private PaymentAccountLockMapper paymentAccountLockMapper;

    @Override
    public void create(PaymentAccountLock dto){
        paymentAccountLockMapper.insert(dto);
    }

    @Override
    public void update(PaymentAccountLock dto){
        PaymentAccountLockExample example = new PaymentAccountLockExample();
        PaymentAccountLockExample.Criteria criteria = example.createCriteria();
        criteria.andYearsEqualTo(dto.getYears());
        criteria.andMonthsEqualTo(dto.getMonths());
        paymentAccountLockMapper.updateByExample(dto, example);
    }

    @Override
    public PaymentAccountLock findisLock(PaymentAccountLock aLock, Date lockdate) {
        return this.paymentAccountLockMapper.findisLock(aLock, lockdate);
    }

    @Override
    public int findLockByCityId(PaymentAccountLock dto) {
        int lock=0;
        PaymentAccountLockExample example = new PaymentAccountLockExample();
        PaymentAccountLockExample.Criteria criteria = example.createCriteria();
        criteria.andYearsEqualTo(dto.getYears());
        criteria.andMonthsEqualTo(dto.getMonths());
        criteria.andCityIdEqualTo(dto.getCityId());
        List<PaymentAccountLock> accountLockList=paymentAccountLockMapper.selectByExample(example);
        for (PaymentAccountLock aLock : accountLockList) {
            if (aLock != null) {
                lock=aLock.getLocks();
            }
        }
        return lock;
    }

    @Override
    public PaymentAccountLock findisLockByCityId(PaymentAccountLock aLock, Date lockdate,
                                          Integer cityId) {
        return this.paymentAccountLockMapper.findisLockByCityId(aLock, lockdate,cityId);
    }

}
