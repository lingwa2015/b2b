package com.b2b.common.dao;

import com.b2b.common.domain.BeBlackWhiteListVariety;
import com.b2b.common.domain.BeBlackWhiteListVarietyExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BeBlackWhiteListVarietyMapper {
    int countByExample(BeBlackWhiteListVarietyExample example);

    int deleteByExample(BeBlackWhiteListVarietyExample example);

    int deleteByPrimaryKey(Integer bbwlvId);

    int insert(BeBlackWhiteListVariety record);

    int insertSelective(BeBlackWhiteListVariety record);

    List<BeBlackWhiteListVariety> selectByExample(BeBlackWhiteListVarietyExample example);

    BeBlackWhiteListVariety selectByPrimaryKey(Integer bbwlvId);

    int updateByExampleSelective(@Param("record") BeBlackWhiteListVariety record, @Param("example") BeBlackWhiteListVarietyExample example);

    int updateByExample(@Param("record") BeBlackWhiteListVariety record, @Param("example") BeBlackWhiteListVarietyExample example);

    int updateByPrimaryKeySelective(BeBlackWhiteListVariety record);

    int updateByPrimaryKey(BeBlackWhiteListVariety record);

	BeBlackWhiteListVariety findByVarietyId(@Param("customerId")int customerId, @Param("flag")int flag,
			@Param("varietyId")Integer varietyId);

	List<HashMap<String, Object>> findAll(@Param("id")Integer id, @Param("flag")int flag);
}