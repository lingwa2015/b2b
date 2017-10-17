package com.b2b.common.dao;

import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.PrivilegeExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PrivilegeMapper {
    int countByExample(PrivilegeExample example);

    int deleteByExample(PrivilegeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Privilege record);

    int insertSelective(Privilege record);

    List<Privilege> selectByExample(PrivilegeExample example);

    Privilege selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Privilege record, @Param("example") PrivilegeExample example);

    int updateByExample(@Param("record") Privilege record, @Param("example") PrivilegeExample example);

    int updateByPrimaryKeySelective(Privilege record);

    int updateByPrimaryKey(Privilege record);

	List<Privilege> findByRoleId(Integer roleId);
}