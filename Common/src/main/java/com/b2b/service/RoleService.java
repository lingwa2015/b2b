package com.b2b.service;

import java.util.ArrayList;
import java.util.List;

import com.b2b.common.domain.Role;
import com.b2b.page.Page;

public interface RoleService {

	void saveRole(Role role, ArrayList<Integer> list);

	Page<Role> findPage(int currentPage, int pageSize, Integer cityId);

	Role findById(int id);

	void updateRole(Role role, ArrayList<Integer> list);

	void delete(Role role);

	List<Role> findByUserId(int userId);

	List<Role> findAllByCityId(Integer cityId);

}
