package com.b2b.service.impl;

import com.b2b.common.dao.RecommendRedbackMapper;
import com.b2b.common.domain.RecommendRedback;
import com.b2b.common.domain.RedAccount;
import com.b2b.service.RecommendRedbackService;
import com.b2b.service.RedAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by shenhui on 2017/8/11.
 */
@Service
public class RecommendRedbackServiceImpl implements RecommendRedbackService{
    @Autowired
    RecommendRedbackMapper recommendRedbackMapper;

    @Autowired
    RedAccountService redAccountService;
    @Override
    public void insert(RecommendRedback recommendRedback) {
        this.recommendRedbackMapper.insert(recommendRedback);
    }

    @Override
    public List<RecommendRedback> findByCondition(String companyName, Date startTime, Date endTime, Integer cityId) {
        return this.recommendRedbackMapper.findByCondition(companyName,startTime,endTime,cityId);
    }

    @Override
    public void update(RecommendRedback recommendRedback) {
        this.recommendRedbackMapper.updateByPrimaryKeySelective(recommendRedback);
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
    @Override
    public void fahongbao(RecommendRedback recommendRedback) {
        recommendRedback.setType(1);
        this.recommendRedbackMapper.updateByPrimaryKey(recommendRedback);
        RedAccount account = this.redAccountService.findByUserIdAndType(recommendRedback.getUserId(), 1);
        if(null!=account){
            this.redAccountService.updateAccountMoneyByUserIdAndType(recommendRedback.getUserId(),1,20000l);
        }else{
            RedAccount redAccount = new RedAccount();
            redAccount.setType(1);
            redAccount.setUserId(recommendRedback.getUserId());
            redAccount.setAccount(20000l);
            this.redAccountService.save(redAccount);
        }
    }

    @Override
    public RecommendRedback findById(Integer id) {
        return this.recommendRedbackMapper.selectByPrimaryKey(id);
    }
}
