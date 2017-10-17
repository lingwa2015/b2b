package com.b2b.common.dao;

import com.b2b.common.domain.DebitNote;
import com.b2b.common.domain.DebitNoteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DebitNoteMapper {
    int countByExample(DebitNoteExample example);

    int deleteByExample(DebitNoteExample example);

    int deleteByPrimaryKey(String id);

    int insert(DebitNote record);

    int insertSelective(DebitNote record);

    List<DebitNote> selectByExample(DebitNoteExample example);

    DebitNote selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DebitNote record, @Param("example") DebitNoteExample example);

    int updateByExample(@Param("record") DebitNote record, @Param("example") DebitNoteExample example);

    int updateByPrimaryKeySelective(DebitNote record);

    int updateByPrimaryKey(DebitNote record);
}