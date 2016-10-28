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
    /**
	 * 获取用户未读点到记事条数
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	int getNoReadNotesCntByUser(int userId,int spaceType);
	/**
	 * 获取用户未读点到记事列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	List<Note> getNoReadNotesByUser(int userId,int spaceType);
}