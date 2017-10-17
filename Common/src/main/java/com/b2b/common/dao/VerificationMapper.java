package com.b2b.common.dao;

import com.b2b.common.domain.Verification;
import com.b2b.common.domain.VerificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VerificationMapper {
    int countByExample(VerificationExample example);

    int deleteByExample(VerificationExample example);

    int deleteByPrimaryKey(String id);

    int insert(Verification record);

    int insertSelective(Verification record);

    List<Verification> selectByExample(VerificationExample example);

    Verification selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Verification record, @Param("example") VerificationExample example);

    int updateByExample(@Param("record") Verification record, @Param("example") VerificationExample example);

    int updateByPrimaryKeySelective(Verification record);

    int updateByPrimaryKey(Verification record);
}