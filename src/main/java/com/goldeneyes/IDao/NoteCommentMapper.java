package com.goldeneyes.IDao;

import com.goldeneyes.pojo.NoteComment;

public interface NoteCommentMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(NoteComment record);

    int insertSelective(NoteComment record);

    NoteComment selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(NoteComment record);

    int updateByPrimaryKey(NoteComment record);
}