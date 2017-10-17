package com.b2b.common.dao;

import com.b2b.common.domain.PayFriends;
import com.b2b.common.domain.PayFriendsExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PayFriendsMapper {
    int countByExample(PayFriendsExample example);

    int deleteByExample(PayFriendsExample example);

    int deleteByPrimaryKey(String id);

    int insert(PayFriends record);

    int insertSelective(PayFriends record);

    List<PayFriends> selectByExample(PayFriendsExample example);

    PayFriends selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PayFriends record, @Param("example") PayFriendsExample example);

    int updateByExample(@Param("record") PayFriends record, @Param("example") PayFriendsExample example);

    int updateByPrimaryKeySelective(PayFriends record);

    int updateByPrimaryKey(PayFriends record);

	int findTotalMoney(Integer orderId);

	void changePayStatus(@Param("id")String id, @Param("i")int i);

}