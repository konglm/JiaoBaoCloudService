/*----------------------------------------------------------------
 *  Copyright (C) 2016山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：NoteController
 * 文件功能描述：点到记事控制层
 *
 * 
 * 创建标识：konglm20161026
 *
 * 修改标识：
 * 修改描述：
 *----------------------------------------------------------------*/

package com.goldeneyes.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldeneyes.pojo.Note;
import com.goldeneyes.pojo.NoteComment;
import com.goldeneyes.pojo.NoteEnc;
import com.goldeneyes.service.NoteService;
import com.goldeneyes.util.CommonTool;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

/**
 * @author konglm
 *
 */
@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource
	NoteService noteService;

	@RequestMapping("/getAllNotes.do")
	public void getAllNote(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Note> notes = noteService.getAllNote();
		JSONArray jsonArray = new JSONArray();
		for (Note note : notes) {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("StudentId", note.getStudentid());
			jsonobj.put("MsgContent", note.getMsgcontent());
			jsonobj.put("PublisherId", note.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("PublishDate", formater.format(note.getPublishdate()));
			jsonArray.put(jsonobj);
		}
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonArray.toString());
	}

	/**
	 * 获取用户未读点到记事条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoReadNotesCntByUser.do")
	public void getNoReadNotesCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int cnt = noteService.getNoReadNotesCntByUser(userId, 1);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", cnt);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}

	/**
	 * 获取用户未读点到记事列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoReadNotesByUser.do")
	public void getNoReadNotesByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		List<Note> notes = noteService.getNoReadNotesByUser(userId, 1);
		JSONArray jsonArray = new JSONArray();
		for (Note note : notes) {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("StudentId", note.getStudentid());
			jsonobj.put("MsgContent", note.getMsgcontent());
			jsonobj.put("PublisherId", note.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("PublishDate", formater.format(note.getPublishdate()));
			jsonArray.put(jsonobj);
		}
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonArray.toString());
	}

	/**
	 * 获取用户某条点到记事是否点赞
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getIsLikeNoteByUser.do")
	public void getIsLikeNoteByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int spaceType = Integer.parseInt(request.getParameter("spaceType"));
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		int isLike = noteService.getIsLikeNoteByUser(userId, spaceType, noteId);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", isLike);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}

	/**
	 * 获取点到记事所有评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoteCommentsByUser.do")
	public void getNoteCommentsByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		List<NoteComment> noteComments = noteService.getNoteCommentsByUser(noteId);
		JSONArray jsonArray = new JSONArray();
		for (NoteComment noteComment : noteComments) {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("UserId", noteComment.getUserid());
			jsonobj.put("CommentContent", noteComment.getCommentcontent());
			jsonobj.put("ReplyId", noteComment.getReplyid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("CommentDate", formater.format(noteComment.getCommentdate()));
			jsonArray.put(jsonobj);
		}
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonArray.toString());
	}
	/**
	 * 获取点到记事所有点赞用户
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getIsLikeUsersByNote.do")
	public void getIsLikeUsersByNote(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		List<Integer> userIds = noteService.getIsLikeUsersByNote(noteId);
		JSONArray jsonArray = new JSONArray();
		for (Integer userId : userIds) {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("UserId", userId);
			jsonArray.put(jsonobj);
		}
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonArray.toString());
	}
	/**
	 * 获取用户点到记事所有未读评论回复条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoteCommentReplysCntByUser.do")
	public void getNoteCommentReplysCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		int cnt = noteService.getNoteCommentReplysCntByUser(userId);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", cnt);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}

	/**
	 * 获取用户点到记事所有未读评论回复列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoteCommentReplysByUser.do")
	public void getNoteCommentReplysByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		int userId = Integer.parseInt(request.getParameter("userId"));
		List<NoteComment> noteComments = noteService.getNoteCommentReplysByUser(userId);
		JSONArray jsonArray = new JSONArray();
		for (NoteComment noteComment : noteComments) {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("UserId", noteComment.getUserid());
			jsonobj.put("CommentContent", noteComment.getCommentcontent());
			jsonobj.put("ReplyId", noteComment.getReplyid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("CommentDate", formater.format(noteComment.getCommentdate()));
			jsonArray.put(jsonobj);
		}
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonArray.toString());
	}

	/**
	 * 获取某学生点到记事列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNotesByStudent.do")
	public void getNotesByStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		List<Note> notes = noteService.getNotesByStudent(studentId);
		JSONArray jsonArray = new JSONArray();
		for (Note note : notes) {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("StudentId", note.getStudentid());
			jsonobj.put("MsgContent", note.getMsgcontent());
			jsonobj.put("PublisherId", note.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("PublishDate", formater.format(note.getPublishdate()));
			jsonArray.put(jsonobj);
		}
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonArray.toString());
	}
	/**
	 * 获取某条点到记事信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoteById.do")
	public void getNoteById(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		Note note = noteService.getNoteById(noteId);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("StudentId", note.getStudentid());
		jsonobj.put("MsgContent", note.getMsgcontent());
		jsonobj.put("PublisherId", note.getPublisherid());
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		jsonobj.put("PublishDate", formater.format(note.getPublishdate()));

		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
	/**
	 * 获取某条点到记事附件列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoteEncById.do")
	public void getNoteEncById(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		List<NoteEnc> noteEncs = noteService.getNoteEncById(noteId);
		JSONArray jsonArray = new JSONArray();
		for (NoteEnc noteEnc : noteEncs) {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("EncType", noteEnc.getEnctype());
			jsonobj.put("EncName", noteEnc.getEncname());
			jsonobj.put("EncAddr", noteEnc.getEncaddr());
			jsonobj.put("EncImgAddr", noteEnc.getEncimgaddr());
			jsonobj.put("PublisherId", noteEnc.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("PublishDate", formater.format(noteEnc.getPublishdate()));
			jsonobj.put("EncOrder", noteEnc.getEncorder());
			jsonArray.put(jsonobj);
		}
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonArray.toString());
	}
	/**
	 * 新增某学生点到记事信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addNote.do")
	public void addNote(HttpServletRequest request, HttpServletResponse response, Model model) {
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		String noteStr = request.getParameter("noteStr");
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));

		int id = noteService.addNote(studentId, noteStr, teacherId);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("ID", id);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
	/**
	 * 新增某学生点到记事附件
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addNoteEnc.do")
	public void addNoteEnc(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		String encType = request.getParameter("encType");
		String encAddr = request.getParameter("encAddr");
		String encImg = request.getParameter("encImg");
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		int encOrder = Integer.parseInt(request.getParameter("encOrder"));

		int success = noteService.addNoteEnc(noteId, encType, encAddr, encImg, teacherId, encOrder);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", success);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
	/**
	 * 新增某用户某点到记事评论
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addNoteComment.do")
	public void addNoteComment(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		String commentStr = request.getParameter("commentStr");
		int userId = Integer.parseInt(request.getParameter("userId"));

		int success = noteService.addNoteComment(userId, noteId, commentStr);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", success);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
	/**
	 * 新增某用户某点到记事评论回复
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addNoteCommentReply.do")
	public void addNoteCommentReply(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		String commentStr = request.getParameter("commentStr");
		int userId = Integer.parseInt(request.getParameter("userId"));
		int replyUserId = Integer.parseInt(request.getParameter("replyUserId"));

		int success = noteService.addNoteCommentReply(userId, replyUserId, noteId, commentStr);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", success);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
	/**
	 * 推送给某用户的某点到记事
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/sendNoteForUser.do")
	public void sendNoteForUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int spaceType = Integer.parseInt(request.getParameter("spaceType"));

		int success = noteService.sendNoteForUser(userId, spaceType, noteId);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", success);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
	/**
	 * 修改某用户某点到记事阅读状态为已读
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setNoteReadByUser.do")
	public void setNoteReadByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int spaceType = Integer.parseInt(request.getParameter("spaceType"));

		int success = noteService.setNoteReadByUser(userId, spaceType, noteId);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", success);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
	/**
	 * 新增某用户某点到记事点赞状态为点赞
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setNoteLikeByUser.do")
	public void setNoteLikeByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int spaceType = Integer.parseInt(request.getParameter("spaceType"));

		int success = noteService.setNoteLikeByUser(userId, spaceType, noteId);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", success);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
	/**
	 * 修改某用户某点到记事评论回复查看状态
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setNoteCommentReplyByUser.do")
	public void setNoteCommentReplyByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteCommentId = Integer.parseInt(request.getParameter("noteCommentId"));

		int success = noteService.setNoteCommentReplyByUser(noteCommentId);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", success);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
	/**
	 * 屏蔽某学生某点到记事信息
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/offNoteByStudent.do")
	public void offNoteByStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));

		int success = noteService.offNoteByStudent(noteId);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", success);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
	/**
	 * 删除某学生某点到记事（附件一起删除）
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delNoteByStudent.do")
	public void delNoteByStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteId = Integer.parseInt(request.getParameter("noteId"));

		int success = noteService.delNoteByStudent(noteId);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", success);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
	/**
	 * 删除某用户某点到记事评论
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delNoteCommentByStudent.do")
	public void delNoteCommentByStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
		int noteCommentId = Integer.parseInt(request.getParameter("noteCommentId"));

		int success = noteService.delNoteCommentByStudent(noteCommentId);
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Result", success);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, jsonobj.toString());
	}
}
