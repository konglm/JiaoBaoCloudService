/*----------------------------------------------------------------
 *  Copyright (C) 2016山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：NoteService
 * 文件功能描述：点到记事服务层
 *
 * 
 * 创建标识：konglm20161026
 *
 * 修改标识：
 * 修改描述：
 *----------------------------------------------------------------*/

package com.goldeneyes.service;

import java.util.List;

import com.goldeneyes.pojo.Note;
import com.goldeneyes.pojo.NoteComment;

/**
 * @author konglm
 *
 */
public interface NoteService {
	
	public List<Note> getAllNote() throws Exception;
	
	/**
	 * 获取用户未读点到记事条数
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public int getNoReadNotesCntByUser(int userId,int spaceType) throws Exception;
	/**
	 * 获取用户未读点到记事列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public List<Note> getNoReadNotesByUser(int userId,int spaceType,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取用户未读点到记事条数
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public int getNoReadNotesCntByUserForStudent(int userId,int spaceType,int studentId) throws Exception;
	/**
	 * 获取用户未读点到记事列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public List<Note> getNoReadNotesByUserForStudent(int userId,int spaceType,int pageIndex,int pageSize,int studentId) throws Exception;
	/**
	 * 获取用户某条点到记事是否点赞
	 * @param userId
	 * @param spaceType
	 * @param noteId
	 * @return
	 */
	public int getIsLikeNoteByUser(int userId,int spaceType,int noteId) throws Exception;
	/**
	 * 获取点到记事所有评论条数
	 * @param noteId
	 * @return
	 */
	public int getNoteCommentsCntById(int noteId) throws Exception;
	/**
	 * 获取点到记事所有评论
	 * @param noteId
	 * @return
	 */
	public List<NoteComment> getNoteCommentsById(int noteId,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取点到记事所有点赞用户
	 * @param noteId
	 * @return
	 */
	public List<Integer> getIsLikeUsersById(int noteId) throws Exception;
	/**
	 * 获取用户点到记事所有未读评论回复条数
	 * @param userId
	 * @return
	 */
	public int getNoteCommentReplysCntByUser(int userId) throws Exception;
	/**
	 * 获取用户点到记事所有未读评论回复列表
	 * @param userId
	 * @return
	 */
	public List<NoteComment> getNoteCommentReplysByUser(int userId,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取某学生点到记事条数
	 * @param studentId
	 * @return
	 */
	public int getNotesCntByStudent(int studentId) throws Exception;
	/**
	 * 获取某学生点到记事列表
	 * @param studentId
	 * @return
	 */
	public List<Note> getNotesByStudent(int studentId,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取某条点到记事信息
	 * @param noteId
	 * @return
	 */
	public Note getNoteById(int noteId) throws Exception;
	/**
	 * 新增某学生点到记事信息
	 * @param studentId
	 * @param msgContent
	 * @param teacherId
	 * @return 0：失败，其他：新增的点到记事ID
	 */
	public int addNote(int studentId,String msgContent,int teacherId,int noteType,int checkType,int encType,String encAddr,String encImg) throws Exception;
	/**
	 * 新增某用户某点到记事评论
	 * @param userId
	 * @param noteId
	 * @param commentContent
	 * @return 是否删除成功 0失败1成功
	 */
	public int addNoteComment(int userId,int noteId,String commentContent) throws Exception;
	/**
	 * 新增某用户某点到记事评论回复
	 * @param userId
	 * @param replyUserId
	 * @param noteId
	 * @param commentContent
	 * @return 是否删除成功 0失败1成功
	 */
	public int addNoteCommentReply(int userId,int replyUserId,int noteId,String commentContent, int upperId) throws Exception;
	/**
	 * 推送给某用户的某点到记事
	 * @param userId
	 * @param spaceType
	 * @param noteId
	 * @return 是否删除成功 0失败1成功
	 */
	public int addNoteForUser(int userId,int spaceType,int noteId) throws Exception;
	/**
	 * 推送给多位用户的某点到记事
	 * @param userId
	 * @param spaceType
	 * @param noteId
	 * @return
	 * @throws Exception
	 */
	public int addNoteForMutiUsers(List<Integer> userIds,int spaceType,int noteId) throws Exception;
	/**
	 * 修改某用户某点到记事阅读状态为已读
	 * @param userId
	 * @param spaceType
	 * @param noteId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setNoteReadByUser(int userId,int spaceType,List<Integer> noteIds) throws Exception;
	/**
	 * 修改某用户某点到记事点赞状态为点赞
	 * @param userId
	 * @param spaceType
	 * @param noteId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setNoteLikeByUser(int userId,int spaceType,int noteId) throws Exception;
	/**
	 * 修改某点到记事评论回复查看状态
	 * @param noteCommentId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setNoteCommentReplyById(int noteCommentId) throws Exception;
	/**
	 * 屏蔽某点到记事信息
	 * @param noteId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setOffNoteById(int noteId) throws Exception;
	/**
	 * 删除某学生某点到记事（附件一起删除）
	 * @param noteId
	 * @return 是否删除成功 0失败1成功
	 */
	public int delNoteById(int noteId) throws Exception;
	/**
	 * 删除某用户某点到记事评论
	 * @param noteCommentId
	 * @return 是否删除成功 0失败1成功
	 */
	public int delNoteCommentById(int noteCommentId) throws Exception;
}
