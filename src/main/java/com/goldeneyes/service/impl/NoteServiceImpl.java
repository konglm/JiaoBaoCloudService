/*----------------------------------------------------------------
 *  Copyright (C) 2016山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：NoteServiceImpl
 * 文件功能描述：点到记事服务层实现
 *
 * 
 * 创建标识：konglm20161026
 *
 * 修改标识：
 * 修改描述：
 *----------------------------------------------------------------*/

package com.goldeneyes.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.goldeneyes.IDao.NoteMapper;
import com.goldeneyes.pojo.Note;
import com.goldeneyes.pojo.NoteComment;
import com.goldeneyes.pojo.NoteEnc;
import com.goldeneyes.service.NoteService;

/**
 * @author konglm
 *
 */
@Service("noteService")
public class NoteServiceImpl implements NoteService {
	@Resource
	NoteMapper noteMapper;
	/**
	 *  @author konglm
	 */
	@Override
	public List<Note> getAllNote() {
		// TODO Auto-generated method stub
		List<Note> notes = noteMapper.getAllNote();
		return notes;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public int getNotesCntByUser(int userId, int spaceType) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public List<Note> getNoReadNotesByUser(int userId, int spaceType) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public boolean getIsLikeNoteByUser(int userId, int spaceType, int noteId) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public List<NoteComment> getNoteCommentsByUser(int noteId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public List<Integer> getIsLikeUsersByNote(int noteId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public int getNoteCommentReplysCntByUser(int userId) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public List<NoteComment> getNoteCommentReplysByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public List<Note> getNotesByStudent(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public Note getNoteById(int noteId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public NoteEnc getNoteEncById(int noteId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public int addNote(int studentId, String noteStr, int teacherId) {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 *  @author konglm
	 */
	@Override
	public void addNoteEnc(int studentId, String noteStr, String encType, String encAddr, String encImg, int teacherId,
			int encOrder) {
		// TODO Auto-generated method stub
		
	}
	

}
