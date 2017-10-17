package com.b2b.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.AdvisoryMapper;
import com.b2b.common.domain.Advisory;
import com.b2b.common.domain.AdvisoryExample;
import com.b2b.page.Page;
import com.b2b.service.AppAdvisoryService;

@Service
public class AppAdvisoryServiceImpl implements AppAdvisoryService {
	@Autowired
	private AdvisoryMapper advisoryMapper;
	
	@Override
	public void insertSelective(Advisory advisory) {
		this.advisoryMapper.insertSelective(advisory);
	}

	@Override
	public Page<Advisory> findPage(int currentPage, int pageSize) {
		AdvisoryExample example = new AdvisoryExample();
		int count = this.advisoryMapper.countByExample(example);
		if (count == 0) {
			return new Page<Advisory>();
		}
		int start = Page.calStartRow(currentPage, pageSize);
		example.setLimit(pageSize);
		example.setStart(start);
		example.setLimitFlag(true);
		example.setOrderByClause("created desc");
		List<Advisory> advisorys = this.advisoryMapper.selectByExample(example);
		return new Page<Advisory>(currentPage, count, pageSize, advisorys);
	}

	@Override
	public Advisory findById(int id) {
		return this.advisoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public void update(Advisory advisory) {
		this.advisoryMapper.updateByPrimaryKeySelective(advisory);
	}

}
