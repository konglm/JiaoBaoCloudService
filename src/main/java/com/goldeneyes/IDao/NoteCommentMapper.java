package com.goldeneyes.IDao;

import java.util.List;

import com.goldeneyes.pojo.NoteComment;

public interface NoteCommentMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(NoteComment record);

    int insertSelective(NoteComment record);

    NoteComment selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(NoteComment record);

    int updateByPrimaryKey(NoteComment record);
    /**
	 * 获取点到记事所有评论
	 * @param noteId
	 * @return
	 */
	List<NoteComment> getNoteCommentsByUser(int noteId);
	/**
	 * 获取用户点到记事所有未读评论回复条数
	 * @param userId
	 * @return
	 */
	int getNoteCommentReplysCntByUser(int userId);
	/**
	 * 获取用户点到记事所有未读评论回复列表
	 * @param userId
	 * @return
	 */
	List<NoteComment> getNoteCommentReplysByUser(int userId);
}