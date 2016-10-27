package com.goldeneyes.IDao;

import com.goldeneyes.pojo.NoteEnc;

public interface NoteEncMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(NoteEnc record);

    int insertSelective(NoteEnc record);

    NoteEnc selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(NoteEnc record);

    int updateByPrimaryKey(NoteEnc record);
}