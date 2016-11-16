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

//	@RequestMapping("/getAllNotes")
//	public void getAllNote(HttpServletRequest request, HttpServletResponse response, Model model) {
//		JSONObject jsonData = new JSONObject();
//		List<Note> notes = new ArrayList<Note>();
//		try {
//			notes = noteService.getAllNote();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//			return;
//		}
//		JSONArray jsonArray = new JSONArray();
//		for (Note note : notes) {
//			JSONObject jsonobj = new JSONObject();
//			jsonobj.put("TabId", note.getTabid());
//			jsonobj.put("StudentId", note.getStudentid());
//			jsonobj.put("MsgContent", note.getMsgcontent());
//			jsonobj.put("PublisherId", note.getPublisherid());
//			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			jsonobj.put("PublishDate", formater.format(note.getPublishdate()));
//			jsonArray.put(jsonobj);
//		}
//		jsonData.put("Data", jsonArray);
//		// 在这里输出，手机端就拿到web返回的值了
//		CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//	}

	/**
	 * 获取用户未读点到记事条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getNoReadNotesCntByUser")
//	public void getNoReadNotesCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("userId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int userId = 0;
//			try {
//				userId = Integer.parseInt(jsonInput.getString("userId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//			int cnt = 0;
//			try {
//				cnt = noteService.getNoReadNotesCntByUser(userId, 1);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			jsonData.put("Result", cnt);
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 获取用户未读点到记事列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getNoReadNotesByUser")
//	public void getNoReadNotesByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("userId") || !jsonInput.has("pageIndex") || !jsonInput.has("pageSize")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		} else {
//			int userId = 0;
//			int pageIndex = 0;
//			int pageSize = 0;
//			try {
//				userId = Integer.parseInt(jsonInput.getString("userId"));
//				pageIndex = Integer.parseInt(jsonInput.getString("pageIndex"));
//				pageSize = Integer.parseInt(jsonInput.getString("pageSize"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//			if ((pageIndex <= 0) || (pageSize <= 0)) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1006").toString());
//				return;
//			}
//			int totalCnt = 0;
//			int totalPage = 0;
//			List<Note> notes = new ArrayList<Note>();
//			try {
//				totalCnt = noteService.getNoReadNotesCntByUser(userId, 1);
//				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
//				notes = noteService.getNoReadNotesByUser(userId, 1, pageIndex, pageSize);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			JSONArray jsonArray = new JSONArray();
//			for (Note note : notes) {
//				JSONObject jsonobj = new JSONObject();
//				jsonobj.put("TabId", note.getTabid());
//				jsonobj.put("StudentId", note.getStudentid());
//				jsonobj.put("MsgContent", note.getMsgcontent());
//				jsonobj.put("PublisherId", note.getPublisherid());
//				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				jsonobj.put("PublishDate", formater.format(note.getPublishdate()));
//				jsonobj.put("NoteType", note.getNotetype());
//				jsonobj.put("CheckType", note.getChecktype());
//				jsonobj.put("EncType", note.getEnctype());
//				jsonobj.put("EncAddr", note.getEncaddr());
//				jsonobj.put("EncImgAddr", note.getEncimgaddr());
//				jsonArray.put(jsonobj);
//			}
//			jsonData.put("TotalCnt", totalCnt);
//			jsonData.put("TotalPage", totalPage);
//			jsonData.put("Data", jsonArray);
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 获取用户未读某学生点到记事条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getNoReadNotesCntByUserForStudent")
//	public void getNoReadNotesCntByUserForStudent(HttpServletRequest request, HttpServletResponse response,
//			Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("userId") || !jsonInput.has("studentId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int userId = 0;
//			int studentId = 0;
//			try {
//				userId = Integer.parseInt(jsonInput.getString("userId"));
//				studentId = Integer.parseInt(jsonInput.getString("studentId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//			int cnt = 0;
//			try {
//				cnt = noteService.getNoReadNotesCntByUserForStudent(userId, 1, studentId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			jsonData.put("Result", cnt);
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 获取用户某学生点到记事列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNotesByUserForStudent")
	public void getNotesByUserForStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 返回参数用
		JSONObject jsonData = new JSONObject();
		// 接收参数用
		JSONObject jsonInput = new JSONObject();

		// 接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}

		if (!jsonInput.has("userId") || !jsonInput.has("pageIndex") || !jsonInput.has("pageSize")
				|| !jsonInput.has("studentId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int userId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			int studentId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				pageIndex = Integer.parseInt(jsonInput.getString("pageIndex"));
				pageSize = Integer.parseInt(jsonInput.getString("pageSize"));
				studentId = Integer.parseInt(jsonInput.getString("studentId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			if ((pageIndex <= 0) || (pageSize <= 0)) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1006").toString());
				return;
			}
			int totalCnt = 0;
			int totalPage = 0;
			List<Note> notes = new ArrayList<Note>();
			int noReadCnt = 0;
			try {
				noReadCnt = noteService.getNoReadNotesCntByUserForStudent(userId, 1, studentId);
				totalCnt = noteService.getNotesCntByStudent(studentId);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				notes = noteService.getNotesByStudent(studentId, pageIndex, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (Note note : notes) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", note.getTabid());
				jsonobj.put("StudentId", note.getStudentid());
				jsonobj.put("MsgContent", note.getMsgcontent());
				jsonobj.put("PublisherId", note.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(note.getPublishdate()));
				jsonobj.put("NoteType", note.getNotetype());
				jsonobj.put("CheckType", note.getChecktype());
				jsonobj.put("EncType", note.getEnctype());
				jsonobj.put("EncAddr", note.getEncaddr());
				jsonobj.put("EncImgAddr", note.getEncimgaddr());
				jsonArray.put(jsonobj);
			}
			jsonData.put("TotalCnt", totalCnt);
			jsonData.put("TotalPage", totalPage);
			jsonData.put("NoReadCnt", noReadCnt);
			jsonData.put("Data", jsonArray);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取用户某条点到记事是否点赞
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getIsLikeNoteByUser")
//	public void getIsLikeNoteByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("userId") || !jsonInput.has("noteId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int userId = 0;
//			int noteId = 0;
//			try {
//				userId = Integer.parseInt(jsonInput.getString("userId"));
//				noteId = Integer.parseInt(jsonInput.getString("noteId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//
//			int isLike = 0;
//			try {
//				isLike = noteService.getIsLikeNoteByUser(userId, 1, noteId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			jsonData.put("Result", isLike);
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 获取点到记事所有评论条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getNoteCommentsCntById")
//	public void getNoteCommentsCntById(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("noteId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int noteId = 0;
//			try {
//				noteId = Integer.parseInt(jsonInput.getString("noteId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//			int cnt = 0;
//			try {
//				cnt = noteService.getNoteCommentsCntById(noteId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//
//			jsonData.put("Result", cnt);
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 获取点到记事所有评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getNoteCommentsById")
//	public void getNoteCommentsByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("noteId") || !jsonInput.has("pageIndex") || !jsonInput.has("pageSize")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		} else {
//			int noteId = 0;
//			int pageIndex = 0;
//			int pageSize = 0;
//			try {
//				noteId = Integer.parseInt(jsonInput.getString("noteId"));
//				pageIndex = Integer.parseInt(jsonInput.getString("pageIndex"));
//				pageSize = Integer.parseInt(jsonInput.getString("pageSize"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//			if ((pageIndex <= 0) || (pageSize <= 0)) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1006").toString());
//				return;
//			}
//			int totalCnt = 0;
//			int totalPage = 0;
//			List<NoteComment> noteComments = new ArrayList<NoteComment>();
//			try {
//				totalCnt = noteService.getNoteCommentsCntById(noteId);
//				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
//				noteComments = noteService.getNoteCommentsById(noteId, pageIndex, pageSize);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			JSONArray jsonArray = new JSONArray();
//			for (NoteComment noteComment : noteComments) {
//				JSONObject jsonobj = new JSONObject();
//				jsonobj.put("TabId", noteComment.getTabid());
//				jsonobj.put("UserId", noteComment.getUserid());
//				jsonobj.put("CommentContent", noteComment.getCommentcontent());
//				jsonobj.put("ReplyId", noteComment.getReplyid());
//				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				jsonobj.put("CommentDate", formater.format(noteComment.getCommentdate()));
//				jsonobj.put("UpperId", noteComment.getUpperid());
//				jsonArray.put(jsonobj);
//			}
//			jsonData.put("TotalCnt", totalCnt);
//			jsonData.put("TotalPage", totalPage);
//			jsonData.put("Data", jsonArray);
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 获取点到记事所有点赞用户
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getIsLikeUsersById")
//	public void getIsLikeUsersById(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("noteId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int noteId = 0;
//			try {
//				noteId = Integer.parseInt(jsonInput.getString("noteId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//			List<Integer> userIds = new ArrayList<Integer>();
//			try {
//				userIds = noteService.getIsLikeUsersById(noteId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			JSONArray jsonArray = new JSONArray();
//			for (Integer userId : userIds) {
//				JSONObject jsonobj = new JSONObject();
//				jsonobj.put("UserId", userId);
//				jsonArray.put(jsonobj);
//			}
//			jsonData.put("Data", jsonArray);
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 获取用户点到记事所有未读评论回复条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getNoteCommentReplysCntByUser")
//	public void getNoteCommentReplysCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("userId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int userId = 0;
//			try {
//				userId = Integer.parseInt(jsonInput.getString("userId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//			int cnt = 0;
//			try {
//				cnt = noteService.getNoteCommentReplysCntByUser(userId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			jsonData.put("Result", cnt);
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 获取用户点到记事所有未读评论回复列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getNoteCommentReplysByUser")
//	public void getNoteCommentReplysByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("userId") || !jsonInput.has("pageIndex") || !jsonInput.has("pageSize")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		} else {
//			int userId = 0;
//			int pageIndex = 0;
//			int pageSize = 0;
//			try {
//				userId = Integer.parseInt(jsonInput.getString("userId"));
//				pageIndex = Integer.parseInt(jsonInput.getString("pageIndex"));
//				pageSize = Integer.parseInt(jsonInput.getString("pageSize"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//			if ((pageIndex <= 0) || (pageSize <= 0)) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1006").toString());
//				return;
//			}
//			int totalCnt = 0;
//			int totalPage = 0;
//			List<NoteComment> noteComments = new ArrayList<NoteComment>();
//			try {
//				totalCnt = noteService.getNoteCommentReplysCntByUser(userId);
//				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
//				noteComments = noteService.getNoteCommentReplysByUser(userId, pageIndex, pageSize);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			JSONArray jsonArray = new JSONArray();
//			for (NoteComment noteComment : noteComments) {
//				JSONObject jsonobj = new JSONObject();
//				jsonobj.put("TabId", noteComment.getTabid());
//				jsonobj.put("UserId", noteComment.getUserid());
//				jsonobj.put("CommentContent", noteComment.getCommentcontent());
//				jsonobj.put("NoteId", noteComment.getNoteid());
//				jsonobj.put("UpperId", noteComment.getUpperid());
//				jsonArray.put(jsonobj);
//			}
//			jsonData.put("TotalCnt", totalCnt);
//			jsonData.put("TotalPage", totalPage);
//			jsonData.put("Data", jsonArray);
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 获取某学生点到记事条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getNotesCntByStudent")
//	public void getNotesCntByStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("studentId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int studentId = 0;
//			try {
//				studentId = Integer.parseInt(jsonInput.getString("studentId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//			int cnt = 0;
//			try {
//				cnt = noteService.getNotesCntByStudent(studentId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//
//			jsonData.put("Result", cnt);
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 获取某学生点到记事列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getNotesByStudent")
//	public void getNotesByStudent(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("studentId") || !jsonInput.has("pageIndex") || !jsonInput.has("pageSize")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		} else {
//			int studentId = 0;
//			int pageIndex = 0;
//			int pageSize = 0;
//			try {
//				studentId = Integer.parseInt(jsonInput.getString("studentId"));
//				pageIndex = Integer.parseInt(jsonInput.getString("pageIndex"));
//				pageSize = Integer.parseInt(jsonInput.getString("pageSize"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//			if ((pageIndex <= 0) || (pageSize <= 0)) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1006").toString());
//				return;
//			}
//			int totalCnt = 0;
//			int totalPage = 0;
//			List<Note> notes = new ArrayList<Note>();
//			try {
//				totalCnt = noteService.getNotesCntByStudent(studentId);
//				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
//				notes = noteService.getNotesByStudent(studentId, pageIndex, pageSize);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			JSONArray jsonArray = new JSONArray();
//			for (Note note : notes) {
//				JSONObject jsonobj = new JSONObject();
//				jsonobj.put("TabId", note.getTabid());
//				jsonobj.put("StudentId", note.getStudentid());
//				jsonobj.put("MsgContent", note.getMsgcontent());
//				jsonobj.put("PublisherId", note.getPublisherid());
//				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				jsonobj.put("PublishDate", formater.format(note.getPublishdate()));
//				jsonobj.put("NoteType", note.getNotetype());
//				jsonobj.put("CheckType", note.getChecktype());
//				jsonobj.put("EncType", note.getEnctype());
//				jsonobj.put("EncAddr", note.getEncaddr());
//				jsonobj.put("EncImgAddr", note.getEncimgaddr());
//				jsonArray.put(jsonobj);
//			}
//			jsonData.put("TotalCnt", totalCnt);
//			jsonData.put("TotalPage", totalPage);
//			jsonData.put("Data", jsonArray);
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 获取某条点到记事信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/getNoteById")
//	public void getNoteById(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("noteId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int noteId = 0;
//			try {
//				noteId = Integer.parseInt(jsonInput.getString("noteId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//			Note note = new Note();
//			try {
//				note = noteService.getNoteById(noteId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			if (note != null) {
//				jsonData.put("StudentId", note.getStudentid());
//				jsonData.put("MsgContent", note.getMsgcontent());
//				jsonData.put("PublisherId", note.getPublisherid());
//				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				jsonData.put("PublishDate", formater.format(note.getPublishdate()));
//				jsonData.put("NoteType", note.getNotetype());
//				jsonData.put("CheckType", note.getChecktype());
//				jsonData.put("EncType", note.getEnctype());
//				jsonData.put("EncAddr", note.getEncaddr());
//				jsonData.put("EncImgAddr", note.getEncimgaddr());
//			}
//			// 在这里输出，手机端就拿到web返回的值了
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//		}
//	}

	/**
	 * 新增某学生点到记事信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addNote")
	public void addNote(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 返回参数用
		JSONObject jsonData = new JSONObject();
		// 接收参数用
		JSONObject jsonInput = new JSONObject();

		// 接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}

		if (!jsonInput.has("studentId") || !jsonInput.has("msgContent") || !jsonInput.has("teacherId")
				|| !jsonInput.has("noteType") || !jsonInput.has("checkType") || !jsonInput.has("encType")
				|| !jsonInput.has("encAddr") || !jsonInput.has("encImg")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int studentId = 0;
			String msgContent = "";
			int teacherId = 0;
			int noteType = 0;
			int checkType = 0;
			int encType = 0;
			String encAddr = "";
			String encImg = "";
			try {
				studentId = Integer.parseInt(jsonInput.getString("studentId"));
				msgContent = jsonInput.getString("msgContent");
				teacherId = Integer.parseInt(jsonInput.getString("teacherId"));
				noteType = Integer.parseInt(jsonInput.getString("noteType"));
				checkType = Integer.parseInt(jsonInput.getString("checkType"));
				encType = Integer.parseInt(jsonInput.getString("encType"));
				encAddr = jsonInput.getString("encAddr");
				encImg = jsonInput.getString("encImg");
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.addNote(studentId, msgContent, teacherId, noteType, checkType, encType, encAddr,
						encImg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
			} else {
				jsonData.put("Result", success);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
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
//	@RequestMapping("/addNoteComment")
//	public void addNoteComment(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("noteId") || !jsonInput.has("commentContent") || !jsonInput.has("userId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int noteId = 0;
//			String commentContent = "";
//			int userId = 0;
//			try {
//				noteId = Integer.parseInt(jsonInput.getString("noteId"));
//				commentContent = jsonInput.getString("commentContent");
//				userId = Integer.parseInt(jsonInput.getString("userId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//
//			int success = 0;
//			try {
//				success = noteService.addNoteComment(userId, noteId, commentContent);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			if (success == 0) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
//			} else {
//				jsonData.put("Result", success);
//				// 在这里输出，手机端就拿到web返回的值了
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//			}
//		}
//	}

	/**
	 * 新增某用户某点到记事评论回复
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/addNoteCommentReply")
//	public void addNoteCommentReply(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("noteId") || !jsonInput.has("commentContent") || !jsonInput.has("userId")
//				|| !jsonInput.has("replyUserId") || !jsonInput.has("upperId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int noteId = 0;
//			String commentContent = "";
//			int userId = 0;
//			int replyUserId = 0;
//			int upperId = 0;
//			try {
//				noteId = Integer.parseInt(jsonInput.getString("noteId"));
//				commentContent = jsonInput.getString("commentContent");
//				userId = Integer.parseInt(jsonInput.getString("userId"));
//				replyUserId = Integer.parseInt(jsonInput.getString("replyUserId"));
//				upperId = Integer.parseInt(jsonInput.getString("upperId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//
//			int success = 0;
//			try {
//				success = noteService.addNoteCommentReply(userId, replyUserId, noteId, commentContent, upperId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			if (success == 0) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
//			} else {
//				jsonData.put("Result", success);
//				// 在这里输出，手机端就拿到web返回的值了
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//			}
//		}
//	}

	/**
	 * 推送给某用户的某点到记事
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/addNoteForUser")
//	public void addNoteForUser(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("userId") || !jsonInput.has("noteId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int userId = 0;
//			int noteId = 0;
//			try {
//				userId = Integer.parseInt(jsonInput.getString("userId"));
//				noteId = Integer.parseInt(jsonInput.getString("noteId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//
//			int success = 0;
//			try {
//				success = noteService.addNoteForUser(userId, 1, noteId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			if (success == 0) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
//			} else {
//				jsonData.put("Result", success);
//				// 在这里输出，手机端就拿到web返回的值了
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//			}
//		}
//	}
//	
	/**
	 * 推送记事给多user
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addNoteForMutiUsers")
	public void addNoteForMutiUsers(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 返回参数用
		JSONObject jsonData = new JSONObject();
		// 接收参数用
		JSONObject jsonInput = new JSONObject();

		// 接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}

		if (!jsonInput.has("userIds") || !jsonInput.has("noteId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			List<Integer> userIds = new ArrayList<Integer>();
			int noteId = 0;
			try {
				userIds = CommonTool.getListFromJsonArray(jsonInput.getJSONArray("userIds"));
				noteId = Integer.parseInt(jsonInput.getString("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.addNoteForMutiUsers(userIds, 1, noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
			} else {
				jsonData.put("Result", success);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
			}
		}
	}

	/**
	 * 修改某用户针对某学生所有点到记事阅读状态为已读
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setNoteReadByUser")
	public void setNoteReadByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 返回参数用
		JSONObject jsonData = new JSONObject();
		// 接收参数用
		JSONObject jsonInput = new JSONObject();

		// 接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}

		if (!jsonInput.has("userId") || !jsonInput.has("studentId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int studentId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				studentId = Integer.parseInt(jsonInput.getString("studentId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.setNoteReadByUser(userId, 1, studentId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
			} else {
				jsonData.put("Result", success);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
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
//	@RequestMapping("/setNoteLikeByUser")
//	public void setNoteLikeByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("userId") || !jsonInput.has("noteId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int userId = 0;
//			int noteId = 0;
//			try {
//				userId = Integer.parseInt(jsonInput.getString("userId"));
//				noteId = Integer.parseInt(jsonInput.getString("noteId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//
//			int success = 0;
//			try {
//				success = noteService.setNoteLikeByUser(userId, 1, noteId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			if (success == 0) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
//			} else {
//				jsonData.put("Result", success);
//				// 在这里输出，手机端就拿到web返回的值了
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//			}
//		}
//	}

	/**
	 * 修改某用户某点到记事评论回复查看状态
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
//	@RequestMapping("/setNoteCommentReplyById")
//	public void setNoteCommentReplyById(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("noteCommentId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int noteCommentId = 0;
//			try {
//				noteCommentId = Integer.parseInt(jsonInput.getString("noteCommentId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//
//			int success = 0;
//			try {
//				success = noteService.setNoteCommentReplyById(noteCommentId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			if (success == 0) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
//			} else {
//				jsonData.put("Result", success);
//				// 在这里输出，手机端就拿到web返回的值了
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//			}
//		}
//	}

	/**
	 * 屏蔽某学生某点到记事信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setOffNoteById")
	public void setOffNoteById(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 返回参数用
		JSONObject jsonData = new JSONObject();
		// 接收参数用
		JSONObject jsonInput = new JSONObject();

		// 接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}

		if (!jsonInput.has("noteId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int noteId = 0;
			try {
				noteId = Integer.parseInt(jsonInput.getString("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.setOffNoteById(noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
			} else {
				jsonData.put("Result", success);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
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
	@RequestMapping("/delNoteById")
	public void delNoteById(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 返回参数用
		JSONObject jsonData = new JSONObject();
		// 接收参数用
		JSONObject jsonInput = new JSONObject();

		// 接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}

		if (!jsonInput.has("noteId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int noteId = 0;
			try {
				noteId = Integer.parseInt(jsonInput.getString("noteId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = noteService.delNoteById(noteId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			if (success == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
			} else {
				jsonData.put("Result", success);
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
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
//	@RequestMapping("/delNoteCommentById")
//	public void delNoteCommentById(HttpServletRequest request, HttpServletResponse response, Model model) {
//		// 返回参数用
//		JSONObject jsonData = new JSONObject();
//		// 接收参数用
//		JSONObject jsonInput = new JSONObject();
//
//		// 接收APP端发来的json请求
//		String requestStr = "";
//		try {
//			requestStr = (String) request.getAttribute("requestStr");
//			jsonInput = JSONObject.fromObject(requestStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//			return;
//		}
//
//		if (!jsonInput.has("noteCommentId")) {
//			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
//		} else {
//			int noteCommentId = 0;
//			try {
//				noteCommentId = Integer.parseInt(jsonInput.getString("noteCommentId"));
//			} catch (Exception e) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
//				return;
//			}
//
//			int success = 0;
//			try {
//				success = noteService.delNoteCommentById(noteCommentId);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
//				return;
//			}
//			if (success == 0) {
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
//			} else {
//				jsonData.put("Result", success);
//				// 在这里输出，手机端就拿到web返回的值了
//				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
//			}
//
//		}
//	}
}
