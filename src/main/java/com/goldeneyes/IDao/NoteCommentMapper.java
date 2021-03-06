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
     * 获取最大ID
     * @return
     */
    int getMaxId();
    /**
   	 * 获取点到记事所有评论条数
   	 * @param noteId
   	 * @return
   	 */
   	int getNoteCommentsCntById(int noteId);
    /**
	 * 获取点到记事所有评论
	 * @param noteId
	 * @return
	 */
	List<NoteComment> getNoteCommentsById(int noteId,int pageIndex,int pageSize);
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
	List<NoteComment> getNoteCommentReplysByUser(int userId,int pageIndex,int pageSize);
	/**
	 * 修改某用户某点到记事评论回复查看状态
	 * @param record
	 * @return
	 */
	int setNoteCommentReplyById(NoteComment record);
	/**
	 * 删除某点到记事的评论
	 * @param noteId
	 * @return
	 */
	int deleteByNoteId(int noteId);
}