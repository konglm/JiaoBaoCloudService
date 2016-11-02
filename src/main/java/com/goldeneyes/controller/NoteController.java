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
import java.util.ArrayList;
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
		JSONArray jsonArray = new JSONArray();
		List<Note> notes = new ArrayList<Note>();
		try {
			notes = noteService.getAllNote();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
			return;
		}
		for (Note note : notes) {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("TabId", note.getTabid());
			jsonobj.put("StudentId", note.getStudentid());
			jsonobj.put("MsgContent", note.getMsgcontent());
			jsonobj.put("PublisherId", note.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("PublishDate", formater.format(note.getPublishdate()));
			jsonArray.put(jsonobj);
		}
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
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
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("userId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = noteService.getNoReadNotesCntByUser(userId, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("Result", cnt);
			jsonArray.put(jsonobj);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
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
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("userId") == null) || (request.getParameter("pageIndex") == null) || (request.getParameter("pageSize") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			List<Note> notes = new ArrayList<Note>();
			try {
				notes = noteService.getNoReadNotesByUser(userId, 1, pageIndex, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			for (Note note : notes) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", note.getTabid());
				jsonobj.put("StudentId", note.getStudentid());
				jsonobj.put("MsgContent", note.getMsgcontent());
				jsonobj.put("PublisherId", note.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(note.getPublishdate()));
				jsonArray.put(jsonobj);
			}
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
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
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("userId") == null) || (request.getParameter("noteId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			int noteId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
				noteId = Integer.parseInt(request.getParameter("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int isLike = 0;
			try {
				isLike = noteService.getIsLikeNoteByUser(userId, 1, noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("Result", isLike);
			jsonArray.put(jsonobj);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}
	
	/**
	 * 获取点到记事所有评论条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoteCommentsCntById.do")
	public void getNoteCommentsCntById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("noteId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteId = 0;
			try {
				noteId = Integer.parseInt(request.getParameter("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = noteService.getNoteCommentsCntById(noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("Result", cnt);
			jsonArray.put(jsonobj);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}


	/**
	 * 获取点到记事所有评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoteCommentsById.do")
	public void getNoteCommentsByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("noteId") == null) || (request.getParameter("pageIndex") == null) || (request.getParameter("pageSize") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				noteId = Integer.parseInt(request.getParameter("noteId"));
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			List<NoteComment> noteComments = new ArrayList<NoteComment>();
			try {
				noteComments = noteService.getNoteCommentsById(noteId,pageIndex,pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}

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
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}

	/**
	 * 获取点到记事所有点赞用户
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getIsLikeUsersById.do")
	public void getIsLikeUsersById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("noteId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteId = 0;
			try {
				noteId = Integer.parseInt(request.getParameter("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			List<Integer> userIds = new ArrayList<Integer>();
			try {
				userIds = noteService.getIsLikeUsersById(noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}

			for (Integer userId : userIds) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("UserId", userId);
				jsonArray.put(jsonobj);
			}
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
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
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("userId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = noteService.getNoteCommentReplysCntByUser(userId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("Result", cnt);
			jsonArray.put(jsonobj);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
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
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("userId") == null) || (request.getParameter("pageIndex") == null) || (request.getParameter("pageSize") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
				userId = Integer.parseInt(request.getParameter("pageIndex"));
				userId = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			List<NoteComment> noteComments = new ArrayList<NoteComment>();
			try {
				noteComments = noteService.getNoteCommentReplysByUser(userId,pageIndex,pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}

			for (NoteComment noteComment : noteComments) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", noteComment.getTabid());
				jsonobj.put("UserId", noteComment.getUserid());
				jsonobj.put("CommentContent", noteComment.getCommentcontent());
				jsonobj.put("NoteId", noteComment.getNoteid());
				jsonArray.put(jsonobj);
			}
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}
	
	/**
	 * 获取某学生点到记事条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNotesCntByStudent.do")
	public void getNotesCntByStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("studentId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int studentId = 0;
			try {
				studentId = Integer.parseInt(request.getParameter("studentId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = noteService.getNotesCntByStudent(studentId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}

			JSONObject jsonobj = new JSONObject();
			jsonobj.put("Result", cnt);
			jsonArray.put(jsonobj);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
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
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("studentId") == null) || (request.getParameter("pageIndex") == null) || (request.getParameter("pageSize") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int studentId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				studentId = Integer.parseInt(request.getParameter("studentId"));
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			List<Note> notes = new ArrayList<Note>();
			try {
				notes = noteService.getNotesByStudent(studentId,pageIndex,pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}

			for (Note note : notes) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", note.getTabid());
				jsonobj.put("StudentId", note.getStudentid());
				jsonobj.put("MsgContent", note.getMsgcontent());
				jsonobj.put("PublisherId", note.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(note.getPublishdate()));
				jsonArray.put(jsonobj);
			}
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}

	/**
	 * 获取某条点到记事信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoteById.do")
	public void getNoteById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("noteId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteId = 0;
			try {
				noteId = Integer.parseInt(request.getParameter("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			Note note = new Note();
			try {
				note = noteService.getNoteById(noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("StudentId", note.getStudentid());
			jsonobj.put("MsgContent", note.getMsgcontent());
			jsonobj.put("PublisherId", note.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("PublishDate", formater.format(note.getPublishdate()));
			jsonArray.put(jsonobj);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}

	/**
	 * 获取某条点到记事附件列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoteEncById.do")
	public void getNoteEncById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("noteId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteId = 0;
			try {
				noteId = Integer.parseInt(request.getParameter("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			List<NoteEnc> noteEncs = new ArrayList<NoteEnc>();
			try {
				noteEncs = noteService.getNoteEncById(noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}

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
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}

	/**
	 * 新增某学生点到记事信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addNote.do")
	public void addNote(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("studentId") == null) || (request.getParameter("noteStr") == null)
				|| (request.getParameter("teacherId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int studentId = 0;
			String noteStr = "";
			int teacherId = 0;
			try {
				studentId = Integer.parseInt(request.getParameter("studentId"));
				noteStr = request.getParameter("noteStr");
				teacherId = Integer.parseInt(request.getParameter("teacherId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int id = 0;
			try {
				id = noteService.addNote(studentId, noteStr, teacherId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			if (id == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 4).toString());
			} else {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("ID", id);
				jsonArray.put(jsonobj);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
			}
		}
	}

	/**
	 * 新增某学生点到记事附件
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addNoteEnc.do")
	public void addNoteEnc(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("noteId") == null) || (request.getParameter("encType") == null)
				|| (request.getParameter("encAddr") == null) || (request.getParameter("encImg") == null)
				|| (request.getParameter("teacherId") == null) || (request.getParameter("encOrder") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteId = 0;
			String encType = "";
			String encAddr = "";
			String encImg = "";
			int teacherId = 0;
			int encOrder = 0;
			try {
				noteId = Integer.parseInt(request.getParameter("noteId"));
				encType = request.getParameter("encType");
				encAddr = request.getParameter("encAddr");
				encImg = request.getParameter("encImg");
				teacherId = Integer.parseInt(request.getParameter("teacherId"));
				encOrder = Integer.parseInt(request.getParameter("encOrder"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.addNoteEnc(noteId, encType, encAddr, encImg, teacherId, encOrder);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 4).toString());
			} else {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Result", success);
				jsonArray.put(jsonobj);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
			}
		}
	}

	/**
	 * 新增某用户某点到记事评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addNoteComment.do")
	public void addNoteComment(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("noteId") == null) || (request.getParameter("commentStr") == null)
				|| (request.getParameter("userId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteId = 0;
			String commentStr = "";
			int userId = 0;
			try {
				noteId = Integer.parseInt(request.getParameter("noteId"));
				commentStr = request.getParameter("commentStr");
				userId = Integer.parseInt(request.getParameter("userId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.addNoteComment(userId, noteId, commentStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 4).toString());
			} else {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Result", success);
				jsonArray.put(jsonobj);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
			}
		}
	}

	/**
	 * 新增某用户某点到记事评论回复
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addNoteCommentReply.do")
	public void addNoteCommentReply(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("noteId") == null) || (request.getParameter("commentStr") == null)
				|| (request.getParameter("userId") == null) || (request.getParameter("replyUserId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteId = 0;
			String commentStr = "";
			int userId = 0;
			int replyUserId = 0;
			try {
				noteId = Integer.parseInt(request.getParameter("noteId"));
				commentStr = request.getParameter("commentStr");
				userId = Integer.parseInt(request.getParameter("userId"));
				replyUserId = Integer.parseInt(request.getParameter("replyUserId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.addNoteCommentReply(userId, replyUserId, noteId, commentStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 4).toString());
			} else {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Result", success);
				jsonArray.put(jsonobj);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
			}
		}
	}

	/**
	 * 推送给某用户的某点到记事
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/sendNoteForUser.do")
	public void sendNoteForUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("userId") == null) || (request.getParameter("noteId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			int noteId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
				noteId = Integer.parseInt(request.getParameter("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.sendNoteForUser(userId, 1, noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 4).toString());
			} else {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Result", success);
				jsonArray.put(jsonobj);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
			}
		}
	}

	/**
	 * 修改某用户某点到记事阅读状态为已读
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setNoteReadByUser.do")
	public void setNoteReadByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("userId") == null) || (request.getParameter("noteId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			int noteId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
				noteId = Integer.parseInt(request.getParameter("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.setNoteReadByUser(userId, 1, noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 4).toString());
			} else {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Result", success);
				jsonArray.put(jsonobj);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
			}
		}
	}

	/**
	 * 修改某用户某点到记事点赞状态为点赞
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setNoteLikeByUser.do")
	public void setNoteLikeByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("userId") == null) || (request.getParameter("noteId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			int noteId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
				noteId = Integer.parseInt(request.getParameter("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.setNoteLikeByUser(userId, 1, noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 4).toString());
			} else {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Result", success);
				jsonArray.put(jsonobj);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
			}
		}
	}

	/**
	 * 修改某用户某点到记事评论回复查看状态
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setNoteCommentReplyById.do")
	public void setNoteCommentReplyById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("noteCommentId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteCommentId = 0;
			try {
				noteCommentId = Integer.parseInt(request.getParameter("noteCommentId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.setNoteCommentReplyById(noteCommentId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 4).toString());
			} else {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Result", success);
				jsonArray.put(jsonobj);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
			}
		}
	}

	/**
	 * 屏蔽某学生某点到记事信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/offNoteById.do")
	public void offNoteById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("noteId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteId = 0;
			try {
				noteId = Integer.parseInt(request.getParameter("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.offNoteById(noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 4).toString());
			} else {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Result", success);
				jsonArray.put(jsonobj);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
			}
		}
	}

	/**
	 * 删除某学生某点到记事（附件一起删除）
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delNoteById.do")
	public void delNoteById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("noteId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteId = 0;
			try {
				noteId = Integer.parseInt(request.getParameter("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.delNoteById(noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 4).toString());
			} else {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Result", success);
				jsonArray.put(jsonobj);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
			}
		}
	}

	/**
	 * 删除某用户某点到记事评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delNoteCommentById.do")
	public void delNoteCommentById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("noteCommentId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int noteCommentId = 0;
			try {
				noteCommentId = Integer.parseInt(request.getParameter("noteCommentId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.delNoteCommentById(noteCommentId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 4).toString());
			} else {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("Result", success);
				jsonArray.put(jsonobj);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
			}

		}
	}
}
