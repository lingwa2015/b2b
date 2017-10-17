package com.b2b.service.impl;

import com.b2b.common.dao.*;
import com.b2b.common.domain.*;
import com.b2b.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    ReceiptMapper receiptMapper;

    @Override
    public List<Receipt> findList(Date starttime, Date querytime, Date crestdate, Date creenddate, String supplierName, Integer cityId) {
        return receiptMapper.findList(starttime, querytime, crestdate, creenddate, supplierName , cityId);
    }

    @Override
    public Long totalPrice(Date starttime, Date querytime, Date crestdate, Date creenddate, String supplierName, Integer cityId) {
        return receiptMapper.totalPrice(starttime, querytime, crestdate, creenddate, supplierName , cityId);
    }

    @Override
    public int create(Receipt receipt) {
        return receiptMapper.insertSelective(receipt);
    }

    @Override
    public Receipt findById(String id) {
        return this.receiptMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    @Override
    public int update(Receipt receipt) {
        return this.receiptMapper.updateByPrimaryKeySelective(receipt);
    }
}
