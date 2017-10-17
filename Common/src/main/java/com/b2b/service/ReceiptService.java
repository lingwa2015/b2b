package com.b2b.service;


import com.b2b.common.domain.Receipt;

import java.util.Date;
import java.util.List;

/**
 * 收款单
 * */
public interface ReceiptService {


	List<Receipt> findList(Date starttime, Date querytime, Date crestdate, Date creenddate, String supplierName, Integer cityId);

	Long totalPrice(Date starttime, Date querytime, Date crestdate, Date creenddate, String supplierName, Integer cityId);

    int create(Receipt receipt);

	Receipt findById(String id);

	int update(Receipt receipt);
}
