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
import com.goldeneyes.pojo.NoteEnc;

/**
 * @author konglm
 *
 */
public interface NoteService {
	
	public List<Note> getAllNote();
	
	/**
	 * 获取用户未读点到记事条数
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public int getNotesCntByUser(int userId,int spaceType);
	/**
	 * 获取用户未读点到记事列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public List<Note> getNoReadNotesByUser(int userId,int spaceType);
	/**
	 * 获取用户某条点到记事是否点赞
	 * @param userId
	 * @param spaceType
	 * @param noteId
	 * @return
	 */
	public boolean getIsLikeNoteByUser(int userId,int spaceType,int noteId);
	/**
	 * 获取点到记事所有评论
	 * @param noteId
	 * @return
	 */
	public List<NoteComment> getNoteCommentsByUser(int noteId);
	/**
	 * 获取点到记事所有点赞用户
	 * @param noteId
	 * @return
	 */
	public List<Integer> getIsLikeUsersByNote(int noteId);
	/**
	 * 获取用户点到记事所有未读评论回复条数
	 * @param userId
	 * @return
	 */
	public int getNoteCommentReplysCntByUser(int userId);
	/**
	 * 获取用户点到记事所有未读评论回复列表
	 * @param userId
	 * @return
	 */
	public List<NoteComment> getNoteCommentReplysByUser(int userId);
	/**
	 * 获取某学生点到记事列表
	 * @param studentId
	 * @return
	 */
	public List<Note> getNotesByStudent(int studentId);
	/**
	 * 获取某条点到记事信息
	 * @param noteId
	 * @return
	 */
	public Note getNoteById(int noteId);
	/**
	 * 获取某条点到记事附件列表
	 * @param noteId
	 * @return
	 */
	public NoteEnc getNoteEncById(int noteId);
	/**
	 * 新增某学生点到记事信息
	 * @param studentId
	 * @param noteStr
	 * @param teacherId
	 * @return 新增的点到记事ID
	 */
	public int addNote(int studentId,String noteStr,int teacherId);
	/**
	 * 新增某学生点到记事附件
	 * @param studentId
	 * @param noteStr
	 * @param encType
	 * @param encAddr
	 * @param encImg
	 * @param teacherId
	 * @param encOrder
	 */
	public void addNoteEnc(int studentId,String noteStr,String encType,String encAddr,String encImg,int teacherId,int encOrder);
}
