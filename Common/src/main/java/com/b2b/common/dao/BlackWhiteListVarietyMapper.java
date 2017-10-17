package com.b2b.common.dao;

import com.b2b.common.domain.BlackWhiteListVariety;
import com.b2b.common.domain.BlackWhiteListVarietyExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BlackWhiteListVarietyMapper {
    int countByExample(BlackWhiteListVarietyExample example);

    int deleteByExample(BlackWhiteListVarietyExample example);

    int deleteByPrimaryKey(Integer bwlvId);

    int insert(BlackWhiteListVariety record);

    int insertSelective(BlackWhiteListVariety record);

    List<BlackWhiteListVariety> selectByExample(BlackWhiteListVarietyExample example);

    BlackWhiteListVariety selectByPrimaryKey(Integer bwlvId);

    int updateByExampleSelective(@Param("record") BlackWhiteListVariety record, @Param("example") BlackWhiteListVarietyExample example);

    int updateByExample(@Param("record") BlackWhiteListVariety record, @Param("example") BlackWhiteListVarietyExample example);

    int updateByPrimaryKeySelective(BlackWhiteListVariety record);

    int updateByPrimaryKey(BlackWhiteListVariety record);

	BlackWhiteListVariety findBlackByVarietyId(@Param("customerId")Integer customerId,@Param("flag") int flag,
			@Param("varietyId")Integer varietyId);

	List<HashMap<String, Object>> findAll(@Param("id")Integer id,@Param("i")int i);
}