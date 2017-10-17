package com.b2b.common.dao;

import com.b2b.common.domain.RefundFriends;
import com.b2b.common.domain.RefundFriendsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefundFriendsMapper {
    int countByExample(RefundFriendsExample example);

    int deleteByExample(RefundFriendsExample example);

    int deleteByPrimaryKey(String id);

    int insert(RefundFriends record);

    int insertSelective(RefundFriends record);

    List<RefundFriends> selectByExample(RefundFriendsExample example);

    RefundFriends selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RefundFriends record, @Param("example") RefundFriendsExample example);

    int updateByExample(@Param("record") RefundFriends record, @Param("example") RefundFriendsExample example);

    int updateByPrimaryKeySelective(RefundFriends record);

    int updateByPrimaryKey(RefundFriends record);
}