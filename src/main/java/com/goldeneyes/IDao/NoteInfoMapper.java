package com.goldeneyes.IDao;

import com.goldeneyes.pojo.NoteInfo;

public interface NoteInfoMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(NoteInfo record);

    int insertSelective(NoteInfo record);

    NoteInfo selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(NoteInfo record);

    int updateByPrimaryKey(NoteInfo record);
}