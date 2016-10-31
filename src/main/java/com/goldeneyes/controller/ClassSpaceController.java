/*----------------------------------------------------------------
 *  Copyright (C) 2016山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：
 * 文件功能描述：
 *
 * 
 * 创建标识：
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

import com.goldeneyes.pojo.ClassSpace;
import com.goldeneyes.pojo.ClassSpaceComment;
import com.goldeneyes.pojo.ClassSpaceEnc;
import com.goldeneyes.service.ClassSpaceService;
import com.goldeneyes.util.CommonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author konglm
 *
 */
@Controller
@RequestMapping("/")
public class ClassSpaceController {
	@Resource
	ClassSpaceService classSpaceService;

	@RequestMapping("/getAllClassSpaces.do")
	public void getAllClassSpace(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		List<ClassSpace> classSpaces = new ArrayList<ClassSpace>();
		try {
			classSpaces = classSpaceService.getAllClassSpace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
			return;
		}
		for (ClassSpace classSpace : classSpaces) {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("TabId", classSpace.getTabid());
			jsonobj.put("ClassId", classSpace.getClassid());
			jsonobj.put("MsgContent", classSpace.getMsgcontent());
			jsonobj.put("PublisherId", classSpace.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("PublishDate", formater.format(classSpace.getPublishdate()));
			jsonArray.put(jsonobj);
		}
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
	}

	/**
	 * 获取用户未读班级空间条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoReadClassSpacesCntByUser.do")
	public void getNoReadClassSpacesCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
				cnt = classSpaceService.getNoReadClassSpacesCntByUser(userId, 1);
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
	 * 获取用户未读班级空间列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoReadClassSpacesByUser.do")
	public void getNoReadClassSpacesByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
			List<ClassSpace> classSpaces = new ArrayList<ClassSpace>();
			try {
				classSpaces = classSpaceService.getNoReadClassSpacesByUser(userId, 1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			for (ClassSpace classSpace : classSpaces) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("ClassId", classSpace.getClassid());
				jsonobj.put("MsgContent", classSpace.getMsgcontent());
				jsonobj.put("PublisherId", classSpace.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(classSpace.getPublishdate()));
				jsonArray.put(jsonobj);
			}
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}

	/**
	 * 获取用户某条班级空间是否点赞
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getIsLikeClassSpaceByUser.do")
	public void getIsLikeClassSpaceByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("userId") == null) || (request.getParameter("classSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int isLike = 0;
			try {
				isLike = classSpaceService.getIsLikeClassSpaceByUser(userId, 1, classSpaceId);
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
	 * 获取班级空间所有评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpaceCommentsByUser.do")
	public void getClassSpaceCommentsByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			List<ClassSpaceComment> classSpaceComments = new ArrayList<ClassSpaceComment>();
			try {
				classSpaceComments = classSpaceService.getClassSpaceCommentsByUser(classSpaceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}

			for (ClassSpaceComment classSpaceComment : classSpaceComments) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("UserId", classSpaceComment.getUserid());
				jsonobj.put("CommentContent", classSpaceComment.getCommentcontent());
				jsonobj.put("ReplyId", classSpaceComment.getReplyid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("CommentDate", formater.format(classSpaceComment.getCommentdate()));
				jsonArray.put(jsonobj);
			}
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}

	/**
	 * 获取班级空间所有点赞用户
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getIsLikeUsersByClassSpace.do")
	public void getIsLikeUsersByClassSpace(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			List<Integer> userIds = new ArrayList<Integer>();
			try {
				userIds = classSpaceService.getIsLikeUsersByClassSpace(classSpaceId);
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
	 * 获取用户班级空间所有未读评论回复条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpaceCommentReplysCntByUser.do")
	public void getClassSpaceCommentReplysCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
				cnt = classSpaceService.getClassSpaceCommentReplysCntByUser(userId);
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
	 * 获取用户班级空间所有未读评论回复列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpaceCommentReplysByUser.do")
	public void getClassSpaceCommentReplysByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
			List<ClassSpaceComment> classSpaceComments = new ArrayList<ClassSpaceComment>();
			try {
				classSpaceComments = classSpaceService.getClassSpaceCommentReplysByUser(userId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}

			for (ClassSpaceComment classSpaceComment : classSpaceComments) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("UserId", classSpaceComment.getUserid());
				jsonobj.put("CommentContent", classSpaceComment.getCommentcontent());
				jsonobj.put("ClassSpaceId", classSpaceComment.getClassspaceid());
				jsonArray.put(jsonobj);
			}
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}

	/**
	 * 获取某学生班级空间列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpacesByClass.do")
	public void getClassSpacesByClass(HttpServletRequest request, HttpServletResponse response, Model model) {
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
			List<ClassSpace> classSpaces = new ArrayList<ClassSpace>();
			try {
				classSpaces = classSpaceService.getClassSpacesByClass(studentId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}

			for (ClassSpace classSpace : classSpaces) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("ClassId", classSpace.getClassid());
				jsonobj.put("MsgContent", classSpace.getMsgcontent());
				jsonobj.put("PublisherId", classSpace.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(classSpace.getPublishdate()));
				jsonArray.put(jsonobj);
			}
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}

	/**
	 * 获取某条班级空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpaceById.do")
	public void getClassSpaceById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			ClassSpace classSpace = new ClassSpace();
			try {
				classSpace = classSpaceService.getClassSpaceById(classSpaceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("ClassId", classSpace.getClassid());
			jsonobj.put("MsgContent", classSpace.getMsgcontent());
			jsonobj.put("PublisherId", classSpace.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("PublishDate", formater.format(classSpace.getPublishdate()));
			jsonArray.put(jsonobj);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}

	/**
	 * 获取某条班级空间附件列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpaceEncById.do")
	public void getClassSpaceEncById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}
			List<ClassSpaceEnc> classSpaceEncs = new ArrayList<ClassSpaceEnc>();
			try {
				classSpaceEncs = classSpaceService.getClassSpaceEncById(classSpaceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 2).toString());
				return;
			}

			for (ClassSpaceEnc classSpaceEnc : classSpaceEncs) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("EncType", classSpaceEnc.getEnctype());
				jsonobj.put("EncName", classSpaceEnc.getEncname());
				jsonobj.put("EncAddr", classSpaceEnc.getEncaddr());
				jsonobj.put("EncImgAddr", classSpaceEnc.getEncimgaddr());
				jsonobj.put("PublisherId", classSpaceEnc.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(classSpaceEnc.getPublishdate()));
				jsonobj.put("EncOrder", classSpaceEnc.getEncorder());
				jsonArray.put(jsonobj);
			}
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 1).toString());
		}
	}

	/**
	 * 新增某学生班级空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addClassSpace.do")
	public void addClassSpace(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("studentId") == null) || (request.getParameter("classSpaceStr") == null)
				|| (request.getParameter("teacherId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int studentId = 0;
			String classSpaceStr = "";
			int teacherId = 0;
			try {
				studentId = Integer.parseInt(request.getParameter("studentId"));
				classSpaceStr = request.getParameter("classSpaceStr");
				teacherId = Integer.parseInt(request.getParameter("teacherId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int id = 0;
			try {
				id = classSpaceService.addClassSpace(studentId, classSpaceStr, teacherId);
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
	 * 新增某学生班级空间附件
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addClassSpaceEnc.do")
	public void addClassSpaceEnc(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("classSpaceId") == null) || (request.getParameter("encType") == null)
				|| (request.getParameter("encAddr") == null) || (request.getParameter("encImg") == null)
				|| (request.getParameter("teacherId") == null) || (request.getParameter("encOrder") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int classSpaceId = 0;
			String encType = "";
			String encAddr = "";
			String encImg = "";
			int teacherId = 0;
			int encOrder = 0;
			try {
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
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
				success = classSpaceService.addClassSpaceEnc(classSpaceId, encType, encAddr, encImg, teacherId, encOrder);
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
	 * 新增某用户某班级空间评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addClassSpaceComment.do")
	public void addClassSpaceComment(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("classSpaceId") == null) || (request.getParameter("commentStr") == null)
				|| (request.getParameter("userId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int classSpaceId = 0;
			String commentStr = "";
			int userId = 0;
			try {
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
				commentStr = request.getParameter("commentStr");
				userId = Integer.parseInt(request.getParameter("userId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.addClassSpaceComment(userId, classSpaceId, commentStr);
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
	 * 新增某用户某班级空间评论回复
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addClassSpaceCommentReply.do")
	public void addClassSpaceCommentReply(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("classSpaceId") == null) || (request.getParameter("commentStr") == null)
				|| (request.getParameter("userId") == null) || (request.getParameter("replyUserId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int classSpaceId = 0;
			String commentStr = "";
			int userId = 0;
			int replyUserId = 0;
			try {
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
				commentStr = request.getParameter("commentStr");
				userId = Integer.parseInt(request.getParameter("userId"));
				replyUserId = Integer.parseInt(request.getParameter("replyUserId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.addClassSpaceCommentReply(userId, replyUserId, classSpaceId, commentStr);
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
	 * 推送给某用户的某班级空间
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/sendClassSpaceForUser.do")
	public void sendClassSpaceForUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("userId") == null) || (request.getParameter("classSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.sendClassSpaceForUser(userId, 1, classSpaceId);
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
	 * 修改某用户某班级空间阅读状态为已读
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setClassSpaceReadByUser.do")
	public void setClassSpaceReadByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("userId") == null) || (request.getParameter("classSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.setClassSpaceReadByUser(userId, 1, classSpaceId);
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
	 * 新增某用户某班级空间点赞状态为点赞
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setClassSpaceLikeByUser.do")
	public void setClassSpaceLikeByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if ((request.getParameter("userId") == null) || (request.getParameter("classSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(request.getParameter("userId"));
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.setClassSpaceLikeByUser(userId, 1, classSpaceId);
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
	 * 修改某用户某班级空间评论回复查看状态
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setClassSpaceCommentReplyByUser.do")
	public void setClassSpaceCommentReplyByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("classSpaceCommentId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int classSpaceCommentId = 0;
			try {
				classSpaceCommentId = Integer.parseInt(request.getParameter("classSpaceCommentId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.setClassSpaceCommentReplyByUser(classSpaceCommentId);
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
	 * 屏蔽某学生某班级空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/offClassSpaceByClass.do")
	public void offClassSpaceByClass(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.offClassSpaceByClass(classSpaceId);
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
	 * 删除某学生某班级空间（附件一起删除）
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delClassSpaceByClass.do")
	public void delClassSpaceByClass(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(request.getParameter("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.delClassSpaceByClass(classSpaceId);
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
	 * 删除某用户某班级空间评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delClassSpaceCommentByClass.do")
	public void delClassSpaceCommentByClass(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONArray jsonArray = new JSONArray();
		if (request.getParameter("classSpaceCommentId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 5).toString());
		} else {
			int classSpaceCommentId = 0;
			try {
				classSpaceCommentId = Integer.parseInt(request.getParameter("classSpaceCommentId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonArray, 3).toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.delClassSpaceCommentByClass(classSpaceCommentId);
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
