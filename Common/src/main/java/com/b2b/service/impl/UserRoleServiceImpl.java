package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.UserRoleMapper;
import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.Role;
import com.b2b.common.domain.UserRole;
import com.b2b.common.domain.UserRoleExample;
import com.b2b.common.domain.UserRoleExample.Criteria;
import com.b2b.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public void deleteByUserId(int userId) {
		UserRoleExample example = new UserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
			this.userRoleMapper.deleteByExample(example);
	}

	@Override
	public void save(UserRole userRole) {
		this.userRoleMapper.insertSelective(userRole);
	}

	@Override
	public List<Privilege> findPrivilegesByUserId(Integer id) {
		return this.userRoleMapper.findPrivilegesByUserId(id);
	}

	@Override
	public List<Role> findRoleByUserId(Integer id) {
		
		return this.userRoleMapper.findRoleByUserId(id);
	}

}
