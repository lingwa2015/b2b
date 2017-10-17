package com.b2b.service;

import com.b2b.common.domain.RecommendRedback;

import java.util.Date;
import java.util.List;

/**
 * Created by shenhui on 2017/8/11.
 */
public interface RecommendRedbackService {
    void insert(RecommendRedback recommendRedback);

    List<RecommendRedback> findByCondition(String companyName, Date startTime, Date endTime, Integer cityId);

    void update(RecommendRedback recommendRedback);

    void fahongbao(RecommendRedback recommendRedback);

    RecommendRedback findById(Integer id);
}
