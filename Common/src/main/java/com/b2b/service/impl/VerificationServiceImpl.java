package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.VerificationMapper;
import com.b2b.common.domain.Verification;
import com.b2b.common.domain.VerificationExample;
import com.b2b.common.domain.VerificationExample.Criteria;
import com.b2b.service.VerificationService;

@Service
public class VerificationServiceImpl implements VerificationService {

	@Autowired
	VerificationMapper verificationMapper;
	
	@Override
	public void update(Verification dto)
	{
//		VerificationExample example = new VerificationExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andYearsEqualTo(dto.getYears());
//		criteria.andMonthsEqualTo(dto.getMonths());
//		criteria.andCompanyIdEqualTo(dto.getCompanyId());
//		verificationMapper.updateByExample(dto, example);
	}
	
	@Override
	public void create(Verification dto){
		verificationMapper.insert(dto);
	}
}
