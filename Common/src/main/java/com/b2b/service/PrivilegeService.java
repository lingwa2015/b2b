package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.Privilege;
import com.b2b.page.Page;

public interface PrivilegeService {

	void save(Privilege privilege);

	Page<Privilege> findpage(int currentPage, int pageSize);

	List<Privilege> findAll();

	List<Privilege> findByRoleId(Integer roleId);

	Privilege findById(int id);

	void saveEdit(Privilege right);

	void delete(Privilege right);

}
