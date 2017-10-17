package com.b2b.common.dao;

import com.b2b.common.domain.RedAccount;
import com.b2b.common.domain.RedAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RedAccountMapper {
    int countByExample(RedAccountExample example);

    int deleteByExample(RedAccountExample example);

    int deleteByPrimaryKey(String id);

    int insert(RedAccount record);

    int insertSelective(RedAccount record);

    List<RedAccount> selectByExample(RedAccountExample example);

    RedAccount selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RedAccount record, @Param("example") RedAccountExample example);

    int updateByExample(@Param("record") RedAccount record, @Param("example") RedAccountExample example);

    int updateByPrimaryKeySelective(RedAccount record);

    int updateByPrimaryKey(RedAccount record);

	void updateAccountMoneyByUserIdAndType(@Param("userId") Integer userId, @Param("type") Integer type,@Param("redfee")Long redfee);

	RedAccount findByUserIdAndType(@Param("userId")Integer userId, @Param("type")Integer type);
}