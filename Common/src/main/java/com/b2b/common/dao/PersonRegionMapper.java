package com.b2b.common.dao;

import com.b2b.common.domain.PersonRegion;
import com.b2b.common.domain.PersonRegionExample;
import com.b2b.common.domain.PersonUser;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PersonRegionMapper {
    int countByExample(PersonRegionExample example);

    int deleteByExample(PersonRegionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PersonRegion record);

    int insertSelective(PersonRegion record);

    List<PersonRegion> selectByExample(PersonRegionExample example);

    PersonRegion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PersonRegion record, @Param("example") PersonRegionExample example);

    int updateByExample(@Param("record") PersonRegion record, @Param("example") PersonRegionExample example);

    int updateByPrimaryKeySelective(PersonRegion record);

    int updateByPrimaryKey(PersonRegion record);

	List<PersonUser> findPersonuserByRegionAndPost(String region);
}