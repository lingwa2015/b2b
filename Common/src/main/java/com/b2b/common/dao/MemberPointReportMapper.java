package com.b2b.common.dao;

import com.b2b.common.domain.MemberPointReport;
import com.b2b.common.domain.MemberPointReportExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MemberPointReportMapper {
    int countByExample(MemberPointReportExample example);

    int deleteByExample(MemberPointReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MemberPointReport record);

    int insertSelective(MemberPointReport record);

    List<MemberPointReport> selectByExample(MemberPointReportExample example);

    MemberPointReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MemberPointReport record, @Param("example") MemberPointReportExample example);

    int updateByExample(@Param("record") MemberPointReport record, @Param("example") MemberPointReportExample example);

    int updateByPrimaryKeySelective(MemberPointReport record);

    int updateByPrimaryKey(MemberPointReport record);

	List<MemberPointReport> findByShopId(Integer userid);
}