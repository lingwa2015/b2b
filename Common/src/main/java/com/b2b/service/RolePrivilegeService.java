package com.b2b.service;

import com.b2b.common.domain.RolePrivilege;

public interface RolePrivilegeService {

	void save(RolePrivilege privilege);

	void deleteByRoleId(Integer roleId);

	void deleteByPrivilegeId(Integer id);

}
