package com.b2b.common.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.b2b.common.domain.Business;
import com.b2b.common.domain.BusinessExample;

public interface BusinessMapper {

	  int countByExample(BusinessExample example);

	    int deleteByExample(BusinessExample example);

	    int deleteByPrimaryKey(Integer id);

	    int insert(Business record);

	    int insertSelective(Business record);

	    List<Business> selectByExample(BusinessExample example);

	    Business selectByPrimaryKey(Integer id);

	    int updateByExampleSelective(@Param("record") Business record, @Param("example") BusinessExample example);

	    int updateByExample(@Param("record") Business record, @Param("example") BusinessExample example);

	    int updateByPrimaryKeySelective(Business record);

	    int updateByPrimaryKey(Business record);
}
