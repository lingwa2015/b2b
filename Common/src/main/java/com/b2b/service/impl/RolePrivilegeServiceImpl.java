package com.b2b.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.RolePrivilegeMapper;
import com.b2b.common.domain.RolePrivilege;
import com.b2b.common.domain.RolePrivilegeExample;
import com.b2b.service.RolePrivilegeService;

@Service
public class RolePrivilegeServiceImpl implements RolePrivilegeService {
	@Autowired
	RolePrivilegeMapper rolePrivilegeMapper;
	
	@Override
	public void save(RolePrivilege privilege) {
		rolePrivilegeMapper.insertSelective(privilege);
	}

	@Override
	public void deleteByRoleId(Integer roleId) {
		RolePrivilegeExample example = new RolePrivilegeExample(); 
		com.b2b.common.domain.RolePrivilegeExample.Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		this.rolePrivilegeMapper.deleteByExample(example);
	}

	@Override
	public void deleteByPrivilegeId(Integer id) {
		RolePrivilegeExample example = new RolePrivilegeExample(); 
		com.b2b.common.domain.RolePrivilegeExample.Criteria criteria = example.createCriteria();
		criteria.andPrivilegeIdEqualTo(id);
		this.rolePrivilegeMapper.deleteByExample(example);
	}
	
}
