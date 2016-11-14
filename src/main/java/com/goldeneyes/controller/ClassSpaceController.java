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
import com.goldeneyes.service.ClassSpaceService;
import com.goldeneyes.util.CommonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author konglm
 *
 */
@Controller
@RequestMapping("/classSpace")
public class ClassSpaceController {
	@Resource
	ClassSpaceService classSpaceService;

	@RequestMapping("/getAllClassSpaces")
	public void getAllClassSpace(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonData = new JSONObject();
		List<ClassSpace> classSpaces = new ArrayList<ClassSpace>();
		try {
			classSpaces = classSpaceService.getAllClassSpace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
			return;
		}
		JSONArray jsonArray = new JSONArray();
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
		jsonData.put("Data", jsonArray);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
	}

	/**
	 * 获取用户未读班级空间条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoReadClassSpacesCntByUser")
	public void getNoReadClassSpacesCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("userId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = classSpaceService.getNoReadClassSpacesCntByUser(userId, 2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			jsonData.put("Result", cnt);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取用户未读班级空间列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoReadClassSpacesByUser")
	public void getNoReadClassSpacesByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("userId") || !jsonInput.has("pageIndex") || !jsonInput.has("pageSize")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int userId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				pageIndex = Integer.parseInt(jsonInput.getString("pageIndex"));
				pageSize = Integer.parseInt(jsonInput.getString("pageSize"));
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
			List<ClassSpace> classSpaces = new ArrayList<ClassSpace>();
			try {
				totalCnt = classSpaceService.getNoReadClassSpacesCntByUser(userId, 2);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				classSpaces = classSpaceService.getNoReadClassSpacesByUser(userId, 2, pageIndex, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (ClassSpace classSpace : classSpaces) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", classSpace.getTabid());
				jsonobj.put("ClassId", classSpace.getClassid());
				jsonobj.put("MsgContent", classSpace.getMsgcontent());
				jsonobj.put("PublisherId", classSpace.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(classSpace.getPublishdate()));
				jsonobj.put("EncType", classSpace.getEnctype());
				jsonobj.put("EncAddr", classSpace.getEncaddr());
				jsonobj.put("EncImgAddr", classSpace.getEncimgaddr());
				jsonobj.put("ReadCnt", classSpaceService.getReadCntBySpaceId(classSpace.getTabid()));
				jsonobj.put("LikeCnt", classSpaceService.getLikeCntBySpaceId(classSpace.getTabid()));
				jsonArray.put(jsonobj);
			}
			jsonData.put("TotalCnt", totalCnt);
			jsonData.put("TotalPage", totalPage);
			jsonData.put("Data", jsonArray);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取用户未读某班级空间条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoReadClassSpacesCntByUserForClass")
	public void getNoReadClassSpacesCntByUserForClass(HttpServletRequest request, HttpServletResponse response,
			Model model) {
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

		if (!jsonInput.has("userId") || !jsonInput.has("classId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int classId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				classId = Integer.parseInt(jsonInput.getString("classId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = classSpaceService.getNoReadClassSpacesCntByUserForClass(userId, 2, classId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			jsonData.put("Result", cnt);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取用户未读班级空间列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoReadClassSpacesByUserForClass")
	public void getNoReadClassSpacesByUserForClass(HttpServletRequest request, HttpServletResponse response,
			Model model) {
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
				|| !jsonInput.has("classId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int userId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			int classId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				pageIndex = Integer.parseInt(jsonInput.getString("pageIndex"));
				pageSize = Integer.parseInt(jsonInput.getString("pageSize"));
				classId = Integer.parseInt(jsonInput.getString("classId"));
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
			List<ClassSpace> classSpaces = new ArrayList<ClassSpace>();
			try {
				totalCnt = classSpaceService.getNoReadClassSpacesCntByUserForClass(userId, 2, classId);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				classSpaces = classSpaceService.getNoReadClassSpacesByUserForClass(userId, 2, pageIndex, pageSize,
						classId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (ClassSpace classSpace : classSpaces) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", classSpace.getTabid());
				jsonobj.put("ClassId", classSpace.getClassid());
				jsonobj.put("MsgContent", classSpace.getMsgcontent());
				jsonobj.put("PublisherId", classSpace.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(classSpace.getPublishdate()));
				jsonobj.put("EncType", classSpace.getEnctype());
				jsonobj.put("EncAddr", classSpace.getEncaddr());
				jsonobj.put("EncImgAddr", classSpace.getEncimgaddr());
				jsonobj.put("ReadCnt", classSpaceService.getReadCntBySpaceId(classSpace.getTabid()));
				jsonobj.put("LikeCnt", classSpaceService.getLikeCntBySpaceId(classSpace.getTabid()));
				jsonArray.put(jsonobj);
			}
			jsonData.put("TotalCnt", totalCnt);
			jsonData.put("TotalPage", totalPage);
			jsonData.put("Data", jsonArray);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取用户某条班级空间是否点赞
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getIsLikeClassSpaceByUser")
	public void getIsLikeClassSpaceByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("userId") || !jsonInput.has("classSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int isLike = 0;
			try {
				isLike = classSpaceService.getIsLikeClassSpaceByUser(userId, 2, classSpaceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			jsonData.put("Result", isLike);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取班级空间所有评论条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpaceCommentsCntById")
	public void getClassSpaceCommentsCntById(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = classSpaceService.getClassSpaceCommentsCntById(classSpaceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			jsonData.put("Result", cnt);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取班级空间所有评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpaceCommentsById")
	public void getClassSpaceCommentsById(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classSpaceId") || !jsonInput.has("pageIndex") || !jsonInput.has("pageSize")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int classSpaceId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
				pageIndex = Integer.parseInt(jsonInput.getString("pageIndex"));
				pageSize = Integer.parseInt(jsonInput.getString("pageSize"));
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
			List<ClassSpaceComment> classSpaceComments = new ArrayList<ClassSpaceComment>();
			try {
				totalCnt = classSpaceService.getClassSpaceCommentsCntById(classSpaceId);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				classSpaceComments = classSpaceService.getClassSpaceCommentsById(classSpaceId, pageIndex, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (ClassSpaceComment classSpaceComment : classSpaceComments) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", classSpaceComment.getTabid());
				jsonobj.put("UserId", classSpaceComment.getUserid());
				jsonobj.put("CommentContent", classSpaceComment.getCommentcontent());
				jsonobj.put("ReplyId", classSpaceComment.getReplyid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("CommentDate", formater.format(classSpaceComment.getCommentdate()));
				jsonobj.put("UpperId", classSpaceComment.getUpperid());
				jsonArray.put(jsonobj);
			}
			jsonData.put("TotalCnt", totalCnt);
			jsonData.put("TotalPage", totalPage);
			jsonData.put("Data", jsonArray);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取班级空间所有点赞用户
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getIsLikeUsersById")
	public void getIsLikeUsersById(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			List<Integer> userIds = new ArrayList<Integer>();
			try {
				userIds = classSpaceService.getIsLikeUsersById(classSpaceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (Integer userId : userIds) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("UserId", userId);
				jsonArray.put(jsonobj);
			}
			jsonData.put("Data", jsonArray);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取用户班级空间所有未读评论回复条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpaceCommentReplysCntByUser")
	public void getClassSpaceCommentReplysCntByUser(HttpServletRequest request, HttpServletResponse response,
			Model model) {
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

		if (!jsonInput.has("userId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = classSpaceService.getClassSpaceCommentReplysCntByUser(userId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			jsonData.put("Result", cnt);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取用户班级空间所有未读评论回复列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpaceCommentReplysByUser")
	public void getClassSpaceCommentReplysByUser(HttpServletRequest request, HttpServletResponse response,
			Model model) {
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

		if (!jsonInput.has("userId") || !jsonInput.has("pageIndex") || !jsonInput.has("pageSize")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int userId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				pageIndex = Integer.parseInt(jsonInput.getString("pageIndex"));
				pageSize = Integer.parseInt(jsonInput.getString("pageSize"));
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
			List<ClassSpaceComment> classSpaceComments = new ArrayList<ClassSpaceComment>();
			try {
				totalCnt = classSpaceService.getClassSpaceCommentReplysCntByUser(userId);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				classSpaceComments = classSpaceService.getClassSpaceCommentReplysByUser(userId, pageIndex, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (ClassSpaceComment classSpaceComment : classSpaceComments) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", classSpaceComment.getTabid());
				jsonobj.put("UserId", classSpaceComment.getUserid());
				jsonobj.put("CommentContent", classSpaceComment.getCommentcontent());
				jsonobj.put("ClassSpaceId", classSpaceComment.getClassspaceid());
				jsonobj.put("UpperId", classSpaceComment.getUpperid());
				jsonArray.put(jsonobj);
			}
			jsonData.put("TotalCnt", totalCnt);
			jsonData.put("TotalPage", totalPage);
			jsonData.put("Data", jsonArray);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取某班级空间条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpacesCntByClass")
	public void getClassSpacesCntByClass(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classId = 0;
			try {
				classId = Integer.parseInt(jsonInput.getString("classId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = classSpaceService.getClassSpacesCntByClass(classId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			jsonData.put("Result", cnt);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取某班级空间列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpacesByClass")
	public void getClassSpacesByClass(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classId") || !jsonInput.has("pageIndex") || !jsonInput.has("pageSize")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int classId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				classId = Integer.parseInt(jsonInput.getString("classId"));
				pageIndex = Integer.parseInt(jsonInput.getString("pageIndex"));
				pageSize = Integer.parseInt(jsonInput.getString("pageSize"));
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
			List<ClassSpace> classSpaces = new ArrayList<ClassSpace>();
			try {
				totalCnt = classSpaceService.getClassSpacesCntByClass(classId);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				classSpaces = classSpaceService.getClassSpacesByClass(classId, pageIndex, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (ClassSpace classSpace : classSpaces) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", classSpace.getTabid());
				jsonobj.put("ClassId", classSpace.getClassid());
				jsonobj.put("MsgContent", classSpace.getMsgcontent());
				jsonobj.put("PublisherId", classSpace.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(classSpace.getPublishdate()));
				jsonobj.put("EncType", classSpace.getEnctype());
				jsonobj.put("EncAddr", classSpace.getEncaddr());
				jsonobj.put("EncImgAddr", classSpace.getEncimgaddr());
				jsonobj.put("ReadCnt", classSpaceService.getReadCntBySpaceId(classSpace.getTabid()));
				jsonobj.put("LikeCnt", classSpaceService.getLikeCntBySpaceId(classSpace.getTabid()));
				jsonArray.put(jsonobj);
			}
			jsonData.put("TotalCnt", totalCnt);
			jsonData.put("TotalPage", totalPage);
			jsonData.put("Data", jsonArray);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取某条班级空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpaceById")
	public void getClassSpaceById(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			ClassSpace classSpace = new ClassSpace();
			try {
				classSpace = classSpaceService.getClassSpaceById(classSpaceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			if (classSpace != null) {
				jsonData.put("ClassId", classSpace.getClassid());
				jsonData.put("MsgContent", classSpace.getMsgcontent());
				jsonData.put("PublisherId", classSpace.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonData.put("PublishDate", formater.format(classSpace.getPublishdate()));
				jsonData.put("EncType", classSpace.getEnctype());
				jsonData.put("EncAddr", classSpace.getEncaddr());
				jsonData.put("EncImgAddr", classSpace.getEncimgaddr());
			}
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 新增某班级空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addClassSpace")
	public void addClassSpace(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classId") || !jsonInput.has("msgContent") || !jsonInput.has("teacherId")
				|| !jsonInput.has("encType") || !jsonInput.has("encAddr") || !jsonInput.has("encImg")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classId = 0;
			String msgContent = "";
			int teacherId = 0;
			int encType = 0;
			String encAddr = "";
			String encImg = "";
			try {
				classId = Integer.parseInt(jsonInput.getString("classId"));
				msgContent = jsonInput.getString("msgContent");
				teacherId = Integer.parseInt(jsonInput.getString("teacherId"));
				encType = Integer.parseInt(jsonInput.getString("encType"));
				encAddr = jsonInput.getString("encAddr");
				encImg = jsonInput.getString("encImg");
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.addClassSpace(classId, msgContent, teacherId, encType, encAddr, encImg);
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
	 * 新增某用户某班级空间评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addClassSpaceComment")
	public void addClassSpaceComment(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classSpaceId") || !jsonInput.has("commentContent") || !jsonInput.has("userId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			String commentContent = "";
			int userId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
				commentContent = jsonInput.getString("commentContent");
				userId = Integer.parseInt(jsonInput.getString("userId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.addClassSpaceComment(userId, classSpaceId, commentContent);
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
	 * 新增某用户某班级空间评论回复
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addClassSpaceCommentReply")
	public void addClassSpaceCommentReply(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classSpaceId") || !jsonInput.has("commentContent") || !jsonInput.has("userId")
				|| !jsonInput.has("replyUserId") || !jsonInput.has("upperId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			String commentContent = "";
			int userId = 0;
			int replyUserId = 0;
			int upperId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
				commentContent = jsonInput.getString("commentContent");
				userId = Integer.parseInt(jsonInput.getString("userId"));
				replyUserId = Integer.parseInt(jsonInput.getString("replyUserId"));
				upperId = Integer.parseInt(jsonInput.getString("upperId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.addClassSpaceCommentReply(userId, replyUserId, classSpaceId, commentContent,
						upperId);
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
	 * 推送给某用户的某班级空间
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addClassSpaceForUser")
	public void addClassSpaceForUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("userId") || !jsonInput.has("classSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.addClassSpaceForUser(userId, 2, classSpaceId);
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
	 * 修改某用户某班级空间阅读状态为已读
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setClassSpaceReadByUser")
	public void setClassSpaceReadByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("userId") || !jsonInput.has("classSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.setClassSpaceReadByUser(userId, 2, classSpaceId);
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
	 * 修改某用户某班级空间点赞状态为点赞
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setClassSpaceLikeByUser")
	public void setClassSpaceLikeByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("userId") || !jsonInput.has("classSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.setClassSpaceLikeByUser(userId, 2, classSpaceId);
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
	 * 修改某用户某班级空间评论回复查看状态
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setClassSpaceCommentReplyById")
	public void setClassSpaceCommentReplyById(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classSpaceCommentId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceCommentId = 0;
			try {
				classSpaceCommentId = Integer.parseInt(jsonInput.getString("classSpaceCommentId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.setClassSpaceCommentReplyById(classSpaceCommentId);
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
	 * 屏蔽某班级空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setOffClassSpaceById")
	public void setOffClassSpaceById(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.setOffClassSpaceById(classSpaceId);
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
	 * 删除某班级空间（附件一起删除）
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delClassSpaceById")
	public void delClassSpaceById(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonInput.getString("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.delClassSpaceById(classSpaceId);
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
	 * 删除某用户某班级空间评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delClassSpaceCommentById")
	public void delClassSpaceCommentById(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		if (!jsonInput.has("classSpaceCommentId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceCommentId = 0;
			try {
				classSpaceCommentId = Integer.parseInt(jsonInput.getString("classSpaceCommentId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.delClassSpaceCommentById(classSpaceCommentId);
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
}
