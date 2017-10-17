package com.b2b.service;

import com.b2b.common.domain.RecommendCashback;

import java.util.Date;
import java.util.List;

/**
 * Created by shenhui on 2017/8/10.
 */
public interface RecommendCashbackService {
    void insert(RecommendCashback recommendCashback);

    List<RecommendCashback> findByCondition(String companyName, Date startTime, Date endTime, Integer cityId);

    void update(RecommendCashback recommendCashback);
}
