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
     * 获取最大ID
     * @return
     */
    int getMaxId();
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
	 * 获取用户某条点到记事是否点赞
	 * @param userId
	 * @param spaceType
	 * @param noteId
	 * @return
	 */
	int getIsLikeNoteByUser(int userId,int spaceType,int noteId);
	/**
	 * 获取点到记事所有点赞用户
	 * @param noteId
	 * @return
	 */
	List<Integer> getIsLikeUsersById(int noteId);
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
	/**
	 * 屏蔽某学生某点到记事信息
	 * @param record
	 * @return
	 */
	int offNoteById(Note record);
}