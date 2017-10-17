package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.RemarkMapper;
import com.b2b.common.domain.Remark;
import com.b2b.service.RemarkService;
@Service
public class RemarkServiceImpl implements RemarkService {
	@Autowired
	RemarkMapper remarkMapper;
	
	@Override
	public Remark findByCondition(Integer companyId, String years, String months) {
		
		return this.remarkMapper.findByCondition(companyId,years,months);
	}

	@Override
	public void save(Remark remark2) {
		this.remarkMapper.insert(remark2);
	}

	@Override
	public void update(Remark dto) {
		this.remarkMapper.updateByPrimaryKeySelective(dto);
	}

}
