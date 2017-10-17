package com.b2b.service.impl;

import com.b2b.common.dao.RecommendCashbackMapper;
import com.b2b.common.domain.RecommendCashback;
import com.b2b.service.RecommendCashbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by shenhui on 2017/8/10.
 */
@Service
public class RecommendCashbackServiceImpl implements RecommendCashbackService {
    @Autowired
    RecommendCashbackMapper recommendCashbackMapper;

    @Override
    public void insert(RecommendCashback recommendCashback) {
        this.recommendCashbackMapper.insert(recommendCashback);
    }

    @Override
    public List<RecommendCashback> findByCondition(String companyName, Date startTime, Date endTime, Integer cityId) {
        return this.recommendCashbackMapper.findByCondition(companyName,startTime,endTime,cityId);
    }

    @Override
    public void update(RecommendCashback recommendCashback) {
        this.recommendCashbackMapper.updateByPrimaryKeySelective(recommendCashback);
    }
}
