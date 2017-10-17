package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.CategoryNum;
import com.b2b.common.domain.CategoryNumExample;

/**
 * 锁帐服务
 * */
public interface CategoryNumService {
	void deleteByExample();

	void insert(List<CategoryNum> categoryNum);
	
    List<CategoryNum> selectAll();
}
