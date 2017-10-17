package com.b2b.common.dao;

import com.b2b.common.domain.WXAccount;
import com.b2b.common.domain.WXAccountExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WXAccountMapper {
    int countByExample(WXAccountExample example);

    int deleteByExample(WXAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WXAccount record);

    int insertSelective(WXAccount record);

    List<WXAccount> selectByExample(WXAccountExample example);

    WXAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WXAccount record, @Param("example") WXAccountExample example);

    int updateByExample(@Param("record") WXAccount record, @Param("example") WXAccountExample example);

    int updateByPrimaryKeySelective(WXAccount record);

    int updateByPrimaryKey(WXAccount record);

	void updateForAddMoney(@Param("cid")Integer cid, @Param("money")long money);
}