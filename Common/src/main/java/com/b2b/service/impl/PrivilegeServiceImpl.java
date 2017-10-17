package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.PrivilegeMapper;
import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.PrivilegeExample;
import com.b2b.common.domain.PrivilegeExample.Criteria;
import com.b2b.common.domain.RolePrivilegeExample;
import com.b2b.page.Page;
import com.b2b.service.PrivilegeService;
import com.b2b.service.RolePrivilegeService;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
	@Autowired
	private PrivilegeMapper privilegeMapper;
	
	@Autowired
	private RolePrivilegeService rolePrivilegeService;
	
	@Override
	public void save(Privilege privilege) {
		this.privilegeMapper.insertSelective(privilege);
	}

	@Override
	public Page<Privilege> findpage(int currentPage, int pageSize) {
		PrivilegeExample example = new PrivilegeExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		int count = privilegeMapper.countByExample(example);
		if(count == 0){
			return new Page<Privilege>();
		}
		int start = Page.calStartRow(currentPage, pageSize);
		example.setLimit(pageSize);
		example.setStart(start);
		example.setLimitFlag(true);
		List<Privilege> list = this.privilegeMapper.selectByExample(example);
		return  new Page<Privilege>(currentPage, count, pageSize, list);
	}

	@Override
	public List<Privilege> findAll() {
		PrivilegeExample example = new PrivilegeExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(1);
		return this.privilegeMapper.selectByExample(example);
	}

	@Override
	public List<Privilege> findByRoleId(Integer roleId) {
		return this.privilegeMapper.findByRoleId(roleId);
	}

	@Override
	public Privilege findById(int id) {
		return this.privilegeMapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveEdit(Privilege right) {
		this.privilegeMapper.updateByPrimaryKeySelective(right);
	}

	@Override
	public void delete(Privilege right) {
		this.rolePrivilegeService.deleteByPrivilegeId(right.getId());
		this.privilegeMapper.updateByPrimaryKeySelective(right);
	}

}
