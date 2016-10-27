package com.goldeneyes.IDao;

import java.util.List;

import com.goldeneyes.pojo.Note;

public interface NoteMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKey(Note record);
    
    List<Note> getAllNote();
}