package com.b2b.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.RoleMapper;
import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.PrivilegeExample;
import com.b2b.common.domain.Role;
import com.b2b.common.domain.RoleExample;
import com.b2b.common.domain.RoleExample.Criteria;
import com.b2b.common.domain.RolePrivilege;
import com.b2b.common.domain.RolePrivilegeExample;
import com.b2b.page.Page;
import com.b2b.service.PrivilegeService;
import com.b2b.service.RolePrivilegeService;
import com.b2b.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private RolePrivilegeService rolePrivilegeService;
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@Override
	public void saveRole(Role role, ArrayList<Integer> list) {
		this.roleMapper.insertSelective(role);
		for (Integer a : list) {
			RolePrivilege privilege = new RolePrivilege();
			privilege.setRoleId(role.getRoleId());
			privilege.setPrivilegeId(a);
			this.rolePrivilegeService.save(privilege);
		}
	}

	@Override
	public Page<Role> findPage(int currentPage, int pageSize,Integer cityId) {
		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andCityIdEqualTo(cityId);
		int count = roleMapper.countByExample(example);
		if(count == 0){
			
		}
		int start = Page.calStartRow(currentPage, pageSize);
		example.setLimit(pageSize);
		example.setStart(start);
		example.setLimitFlag(true);
		List<Role> roles = roleMapper.selectByExample(example);
		for (Role role : roles) {
			List<Privilege> privileges = this.privilegeService.findByRoleId(role.getRoleId());
			role.setPrivileges(privileges);
		}
		return  new Page<Role>(currentPage, count, pageSize, roles);
	}

	@Override
	public Role findById(int id) {
		return this.roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateRole(Role role, ArrayList<Integer> list) {
		//解除所有关联关系
		this.rolePrivilegeService.deleteByRoleId(role.getRoleId());
		this.roleMapper.updateByPrimaryKeySelective(role);
		for (Integer a : list) {
			RolePrivilege privilege = new RolePrivilege();
			privilege.setRoleId(role.getRoleId());
			privilege.setPrivilegeId(a);
			this.rolePrivilegeService.save(privilege);
		}
	}

	@Override
	public void delete(Role role) {
		this.rolePrivilegeService.deleteByRoleId(role.getRoleId());
		this.roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public List<Role> findByUserId(int userId) {
		return this.roleMapper.findByUserId(userId);
	}

	@Override
	public List<Role> findAllByCityId(Integer cityId) {
		RoleExample example = new RoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		criteria.andCityIdEqualTo(cityId);
		return roleMapper.selectByExample(example);
	}

}
