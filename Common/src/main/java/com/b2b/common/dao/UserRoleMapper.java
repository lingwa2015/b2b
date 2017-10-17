package com.b2b.common.dao;

import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.Role;
import com.b2b.common.domain.UserRole;
import com.b2b.common.domain.UserRoleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

	List<Privilege> findPrivilegesByUserId(@Param("id")Integer id);

	List<Role> findRoleByUserId(@Param("id")Integer id);
}