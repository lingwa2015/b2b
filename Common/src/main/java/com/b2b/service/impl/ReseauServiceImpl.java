package com.b2b.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ReseauMapper;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.Reseau;
import com.b2b.common.domain.ReseauExample;
import com.b2b.common.domain.ReseauExample.Criteria;
import com.b2b.service.CustomerService;
import com.b2b.service.ReseauService;
import com.b2b.service.UserService;

@Service
public class ReseauServiceImpl implements ReseauService {
	@Autowired
	ReseauMapper reseauMapper;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	UserService userService;
	
	@Override
	public void save(Reseau reseau) {
		this.reseauMapper.insert(reseau);
		this.userService.deleteReseauId(reseau.getId());
		PersonUser user = new PersonUser();
		user.setId(reseau.getManagerId());
		user.setReseauId(reseau.getId());
		this.userService.updateSelect(user);
	}

	@Override
	public void update(Reseau reseau) {
		this.reseauMapper.updateByPrimaryKeySelective(reseau);
		this.userService.deleteReseauId(reseau.getId());
		PersonUser user = new PersonUser();
		user.setId(reseau.getManagerId());
		user.setReseauId(reseau.getId());
		this.userService.updateSelect(user);
	}

	@Override
	public List<Reseau> findAll() {
		ReseauExample example = new ReseauExample();
		example.setOrderByClause("id desc");
		return this.reseauMapper.selectByExample(example);
	}

	@Override
	public Reseau findById(Integer id) {
		return this.reseauMapper.findById(id);
	}

	@Override
	public void delete(Integer id) {
		this.customerService.deleteReseauId(id);
		this.userService.deleteReseauId(id);
		this.reseauMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Reseau> findAllByCityId(Integer cityId) {
		return this.reseauMapper.findAllByCityId(cityId);
	}

	@Override
	public List<Reseau> findByCityIdAndIds(Integer cityId, List<Integer> ids) {
		return this.reseauMapper.findByCityIdAndIds(cityId,ids);
	}

	@Override
	public List<Reseau> findAllWithManageNameByCityId(Integer cityId) {
		return this.reseauMapper.findAllWithManageNameByCityId(cityId);
	}

}
