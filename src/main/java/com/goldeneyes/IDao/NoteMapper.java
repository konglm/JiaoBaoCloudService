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
	/**
	 * 获取点到记事所有点赞用户
	 * @param noteId
	 * @return
	 */
	List<Integer> getIsLikeUsersByNote(int noteId);
	/**
	 * 获取某学生点到记事列表
	 * @param studentId
	 * @return
	 */
	List<Note> getNotesByStudent(int studentId);
	/**
	 * 获取某条点到记事信息
	 * @param noteId
	 * @return
	 */
	Note getNoteById(int noteId);
}