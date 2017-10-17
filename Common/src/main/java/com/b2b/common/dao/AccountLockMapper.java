package com.b2b.common.dao;

import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.AccountLockExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AccountLockMapper {
    int countByExample(AccountLockExample example);

    int deleteByExample(AccountLockExample example);

    int insert(AccountLock record);

    int insertSelective(AccountLock record);

    List<AccountLock> selectByExample(AccountLockExample example);

    int updateByExampleSelective(@Param("record") AccountLock record, @Param("example") AccountLockExample example);

    int updateByExample(@Param("record") AccountLock record, @Param("example") AccountLockExample example);

	AccountLock findisLock(@Param("aLock")AccountLock aLock, @Param("lockdate")Date lockdate);

	AccountLock findisLockByCityId(@Param("aLock")AccountLock aLock, @Param("lockdate")Date lockdate,
			@Param("cityId")Integer cityId);
}