package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.Role;
import com.b2b.common.domain.UserRole;

public interface UserRoleService {

	void deleteByUserId(int userId);

	void save(UserRole userRole);

	List<Privilege> findPrivilegesByUserId(Integer id);

	List<Role> findRoleByUserId(Integer id);

}
