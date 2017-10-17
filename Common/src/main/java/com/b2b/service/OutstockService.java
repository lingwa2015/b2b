package com.b2b.service;

import com.b2b.common.domain.Outstock;
import com.b2b.common.domain.OutstockItem;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Date;
import java.util.List;

/**
 * Created by a. on 2017/8/16.
 */
public interface OutstockService {
    int insert(Outstock outstock);

    Long getMaxOutstockId();

    void createOutstock(Outstock outstock);

    List<Outstock> findOutstocksAndOutstocksItemByCondition(Date startTime, Date endTime, String supplierName, String itemName, String param, Integer cityId);

    Outstock findOutstockById(Long outstockId);

    int updateOutstockStatus(Long outstockId, Integer integer);

    Pair<Outstock,List<OutstockItem>> findById(Long outstockId);

    Integer findPreparationNum(Integer cityId);

    Long findTotalPrice(Date startTime, Date endTime, String supplierName, String itemName, String param, Integer cityId);

    int updateOutstock(Outstock outstock);
}
