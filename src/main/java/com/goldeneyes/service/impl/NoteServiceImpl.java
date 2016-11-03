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

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.goldeneyes.IDao.NoteCommentMapper;
import com.goldeneyes.IDao.NoteEncMapper;
import com.goldeneyes.IDao.NoteMapper;
import com.goldeneyes.IDao.SpaceContentStatusMapper;
import com.goldeneyes.pojo.Note;
import com.goldeneyes.pojo.NoteComment;
import com.goldeneyes.pojo.NoteEnc;
import com.goldeneyes.pojo.SpaceContentStatus;
import com.goldeneyes.service.NoteService;
import com.goldeneyes.util.CommonTool;

/**
 * @author konglm
 *
 */
@Service("noteService")
public class NoteServiceImpl implements NoteService {
	@Resource
	NoteMapper noteMapper;
	@Resource
	NoteCommentMapper noteCommentMapper;
	@Resource
	NoteEncMapper noteEncMapper;
	@Resource
	SpaceContentStatusMapper spaceContentStatusMapper;

	/**
	 * @author konglm
	 */
	@Override
	public List<Note> getAllNote() throws Exception {
		// TODO Auto-generated method stub
		List<Note> notes = noteMapper.getAllNote();
		return notes;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getNoReadNotesCntByUser(int userId, int spaceType) throws Exception {
		// TODO Auto-generated method stub
		int cnt = noteMapper.getNoReadNotesCntByUser(userId, spaceType);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<Note> getNoReadNotesByUser(int userId, int spaceType,int pageIndex,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<Note> notes = noteMapper.getNoReadNotesByUser(userId, spaceType, pageIndex, pageSize);
		return notes;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getIsLikeNoteByUser(int userId, int spaceType, int noteId) throws Exception {
		// TODO Auto-generated method stub
		int isLike = noteMapper.getIsLikeNoteByUser(userId, spaceType, noteId);
		return isLike;
	}
	
	/**
	 * @author konglm
	 */
	@Override
	public int getNoteCommentsCntById(int noteId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = noteCommentMapper.getNoteCommentsCntById(noteId);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<NoteComment> getNoteCommentsById(int noteId,int pageIndex,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<NoteComment> noteComments = noteCommentMapper.getNoteCommentsById(noteId,pageIndex,pageSize);
		return noteComments;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<Integer> getIsLikeUsersById(int noteId) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> isLikers = noteMapper.getIsLikeUsersById(noteId);
		return isLikers;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getNoteCommentReplysCntByUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = noteCommentMapper.getNoteCommentReplysCntByUser(userId);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<NoteComment> getNoteCommentReplysByUser(int userId,int pageIndex,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<NoteComment> noteComments = noteCommentMapper.getNoteCommentReplysByUser(userId,pageIndex,pageSize);
		return noteComments;
	}
	
	/**
	 * @author konglm
	 */
	@Override
	public int getNotesCntByStudent(int studentId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = noteMapper.getNotesCntByStudent(studentId);
		return cnt;
	}


	/**
	 * @author konglm
	 */
	@Override
	public List<Note> getNotesByStudent(int studentId,int pageIndex,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<Note> notes = noteMapper.getNotesByStudent(studentId,pageIndex,pageSize);
		return notes;
	}

	/**
	 * @author konglm
	 */
	@Override
	public Note getNoteById(int noteId) throws Exception {
		// TODO Auto-generated method stub
		Note note = noteMapper.getNoteById(noteId);
		return note;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<NoteEnc> getNoteEncById(int noteId) throws Exception {
		// TODO Auto-generated method stub
		List<NoteEnc> noteEncs = noteEncMapper.getNoteEncById(noteId);
		return noteEncs;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addNote(int studentId, String msgContent, int teacherId,int noteType,int checkType) throws Exception {
		// TODO Auto-generated method stub
		int maxId = noteMapper.getMaxId(); // 为了返回ID，手工插入ID值
		Note note = new Note();
		note.setTabid(maxId);
		note.setStudentid(studentId);
		note.setMsgcontent(msgContent);
		note.setPublisherid(teacherId);
		note.setPublishdate(new Date());
		note.setStatus(CommonTool.int2byte(1));
		note.setNotetype(CommonTool.int2byte(noteType));
		note.setChecktype(CommonTool.int2byte(checkType));
		try {
			noteMapper.insert(note);		
		} catch (Exception e) {
			return 0;
		}
		return maxId;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addNoteEnc(int noteId, String encType, String encAddr, String encImg, int teacherId, int encOrder) throws Exception {
		// TODO Auto-generated method stub
		NoteEnc noteEnc = new NoteEnc();
		noteEnc.setNoteid(noteId);
		noteEnc.setEnctype(encType);
		noteEnc.setEncaddr(encAddr);
		noteEnc.setEncimgaddr(encImg);
		noteEnc.setPublisherid(teacherId);
		noteEnc.setPublishdate(new Date());
		noteEnc.setEncorder(encOrder);
		try {
			noteEncMapper.insert(noteEnc);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addNoteComment(int userId, int noteId, String commentContent) throws Exception {
		// TODO Auto-generated method stub
		NoteComment noteComment = new NoteComment();
		noteComment.setUserid(userId);
		noteComment.setNoteid(noteId);
		noteComment.setCommentcontent(commentContent);
		noteComment.setCommentdate(new Date());
		noteComment.setReplyid(0);
		noteComment.setReplystatus(CommonTool.int2byte(0));
		noteComment.setStatus(CommonTool.int2byte(1));
		try {
			noteCommentMapper.insert(noteComment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addNoteCommentReply(int userId, int replyUserId, int noteId, String commentContent,int upperId) throws Exception {
		// TODO Auto-generated method stub
		NoteComment noteComment = new NoteComment();
		noteComment.setUserid(userId);
		noteComment.setNoteid(noteId);
		noteComment.setCommentcontent(commentContent);
		noteComment.setCommentdate(new Date());
		noteComment.setReplyid(replyUserId);
		noteComment.setReplystatus(CommonTool.int2byte(0));
		noteComment.setStatus(CommonTool.int2byte(1));
		noteComment.setUpperid(upperId);
		try {
			noteCommentMapper.insert(noteComment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int sendNoteForUser(int userId, int spaceType, int noteId) throws Exception {
		// TODO Auto-generated method stub

		SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
		spaceContentStatus.setUserid(userId);
		spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
		spaceContentStatus.setSpaceid(noteId);
		spaceContentStatus.setIsread(CommonTool.int2byte(0));
		spaceContentStatus.setIslike(CommonTool.int2byte(0));

		try {
			spaceContentStatusMapper.insert(spaceContentStatus);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public int setNoteReadByUser(int userId, int spaceType, int noteId) throws Exception {
		// TODO Auto-generated method stub
		SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
		spaceContentStatus.setUserid(userId);
		spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
		spaceContentStatus.setSpaceid(noteId);
		try{
			spaceContentStatusMapper.setNoteReadByUser(spaceContentStatus);
		}catch(Exception e){
			return 0;
		}
		
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int setNoteLikeByUser(int userId, int spaceType, int noteId) throws Exception {
		// TODO Auto-generated method stub
		SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
		spaceContentStatus.setUserid(userId);
		spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
		spaceContentStatus.setSpaceid(noteId);
		try{
			spaceContentStatusMapper.setNoteLikeByUser(spaceContentStatus);
		}catch(Exception e){
			return 0;
		}
		
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int setNoteCommentReplyById(int noteCommentId) throws Exception {
		// TODO Auto-generated method stub
		NoteComment noteComment = new NoteComment();
		noteComment.setTabid(noteCommentId);
		try {
			noteCommentMapper.setNoteCommentReplyById(noteComment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int offNoteById(int noteId) throws Exception {
		// TODO Auto-generated method stub
		Note note = new Note();
		note.setTabid(noteId);
		try {
			noteMapper.offNoteById(note);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int delNoteById(int noteId) throws Exception {
		// TODO Auto-generated method stub
		try{
			noteMapper.deleteByPrimaryKey(noteId);
			noteEncMapper.deleteByNoteId(noteId);
			noteCommentMapper.deleteByNoteId(noteId);
			spaceContentStatusMapper.deleteByNoteId(noteId);
		} catch(Exception e){
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int delNoteCommentById(int noteCommentId) throws Exception {
		// TODO Auto-generated method stub
		try{
			noteCommentMapper.deleteByPrimaryKey(noteCommentId);
		} catch(Exception e){
			return 0;
		}
		return 1;
	}

}
