package com.b2b.common.dao;

import com.b2b.common.domain.RedPacketType;
import com.b2b.common.domain.RedPacketTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RedPacketTypeMapper {
    int countByExample(RedPacketTypeExample example);

    int deleteByExample(RedPacketTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RedPacketType record);

    int insertSelective(RedPacketType record);

    List<RedPacketType> selectByExample(RedPacketTypeExample example);

    RedPacketType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RedPacketType record, @Param("example") RedPacketTypeExample example);

    int updateByExample(@Param("record") RedPacketType record, @Param("example") RedPacketTypeExample example);

    int updateByPrimaryKeySelective(RedPacketType record);

    int updateByPrimaryKey(RedPacketType record);

	Integer findTotalNumByRedId(Integer id);

	List<RedPacketType> findByRedId(Integer redId);
}