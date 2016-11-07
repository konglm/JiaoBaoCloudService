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

import java.io.IOException;
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
				
		if (jsonObject.getString("userId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
				
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("pageIndex") == null) || (jsonObject.getString("pageSize") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int userId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
				pageIndex = Integer.parseInt(jsonObject.getString("pageIndex"));
				pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
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
				classSpaces = classSpaceService.getNoReadClassSpacesByUser(userId, 2,pageIndex,pageSize);
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("classSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
				
		if (jsonObject.getString("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
				
		if ((jsonObject.getString("classSpaceId") == null) || (jsonObject.getString("pageIndex") == null) || (jsonObject.getString("pageSize") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int classSpaceId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
				pageIndex = Integer.parseInt(jsonObject.getString("pageIndex"));
				pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
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
				classSpaceComments = classSpaceService.getClassSpaceCommentsById(classSpaceId,pageIndex,pageSize);
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
				
		if (jsonObject.getString("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
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
	public void getClassSpaceCommentReplysCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if (jsonObject.getString("userId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
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
	public void getClassSpaceCommentReplysByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("pageIndex") == null) || (jsonObject.getString("pageSize") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int userId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
				pageIndex = Integer.parseInt(jsonObject.getString("pageIndex"));
				pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
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
				classSpaceComments = classSpaceService.getClassSpaceCommentReplysByUser(userId,pageIndex,pageSize);
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
	 * 获取某学生班级空间条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpacesCntByClass")
	public void getClassSpacesCntByClass(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if (jsonObject.getString("classId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classId = 0;
			try {
				classId = Integer.parseInt(jsonObject.getString("classId"));
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
	 * 获取某学生班级空间列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpacesByClass")
	public void getClassSpacesByClass(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if ((jsonObject.getString("classId") == null) || (jsonObject.getString("pageIndex") == null) || (jsonObject.getString("pageSize") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int classId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				classId = Integer.parseInt(jsonObject.getString("classId"));
				pageIndex = Integer.parseInt(jsonObject.getString("pageIndex"));
				pageSize = Integer.parseInt(jsonObject.getString("pageSize"));
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
				classSpaces = classSpaceService.getClassSpacesByClass(classId,pageIndex,pageSize);
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if (jsonObject.getString("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
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
			jsonData.put("ClassId", classSpace.getClassid());
			jsonData.put("MsgContent", classSpace.getMsgcontent());
			jsonData.put("PublisherId", classSpace.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonData.put("PublishDate", formater.format(classSpace.getPublishdate()));
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取某条班级空间附件列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getClassSpaceEncById")
	public void getClassSpaceEncById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if (jsonObject.getString("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			List<ClassSpaceEnc> classSpaceEncs = new ArrayList<ClassSpaceEnc>();
			try {
				classSpaceEncs = classSpaceService.getClassSpaceEncById(classSpaceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
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
			jsonData.put("Data", jsonArray);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 新增某学生班级空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addClassSpace")
	public void addClassSpace(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if ((jsonObject.getString("classId") == null) || (jsonObject.getString("msgContent") == null)
				|| (jsonObject.getString("teacherId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classId = 0;
			String msgContent = "";
			int teacherId = 0;
			try {
				classId = Integer.parseInt(jsonObject.getString("classId"));
				msgContent = jsonObject.getString("msgContent");
				teacherId = Integer.parseInt(jsonObject.getString("teacherId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int id = 0;
			try {
				id = classSpaceService.addClassSpace(classId, msgContent, teacherId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			if (id == 0) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1002").toString());
			} else {
				
				jsonData.put("ID", id);
				
				// 在这里输出，手机端就拿到web返回的值了
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
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
	@RequestMapping("/addClassSpaceEnc")
	public void addClassSpaceEnc(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if ((jsonObject.getString("classSpaceId") == null) || (jsonObject.getString("encType") == null)
				|| (jsonObject.getString("encAddr") == null) || (jsonObject.getString("encImg") == null)
				|| (jsonObject.getString("teacherId") == null) || (jsonObject.getString("encOrder") == null)
				|| (jsonObject.getString("encName") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			String encName = "";
			String encType = "";
			String encAddr = "";
			String encImg = "";
			int teacherId = 0;
			int encOrder = 0;
			try {
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
				encName = jsonObject.getString("encName");
				encType = jsonObject.getString("encType");
				encAddr = jsonObject.getString("encAddr");
				encImg = jsonObject.getString("encImg");
				teacherId = Integer.parseInt(jsonObject.getString("teacherId"));
				encOrder = Integer.parseInt(jsonObject.getString("encOrder"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.addClassSpaceEnc(classSpaceId,encName, encType, encAddr, encImg, teacherId, encOrder);
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if ((jsonObject.getString("classSpaceId") == null) || (jsonObject.getString("commentContent") == null)
				|| (jsonObject.getString("userId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			String commentContent = "";
			int userId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
				commentContent = jsonObject.getString("commentContent");
				userId = Integer.parseInt(jsonObject.getString("userId"));
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if ((jsonObject.getString("classSpaceId") == null) || (jsonObject.getString("commentContent") == null)
				|| (jsonObject.getString("userId") == null) || (jsonObject.getString("replyUserId") == null)
				|| (jsonObject.getString("upperId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			String commentContent = "";
			int userId = 0;
			int replyUserId = 0;
			int upperId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
				commentContent = jsonObject.getString("commentContent");
				userId = Integer.parseInt(jsonObject.getString("userId"));
				replyUserId = Integer.parseInt(jsonObject.getString("replyUserId"));
				upperId = Integer.parseInt(jsonObject.getString("upperId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = classSpaceService.addClassSpaceCommentReply(userId, replyUserId, classSpaceId, commentContent, upperId);
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("classSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("classSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("classSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int classSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if (jsonObject.getString("classSpaceCommentId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceCommentId = 0;
			try {
				classSpaceCommentId = Integer.parseInt(jsonObject.getString("classSpaceCommentId"));
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
	 * 屏蔽某学生某班级空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setOffClassSpaceById")
	public void setOffClassSpaceById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if (jsonObject.getString("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
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
	 * 删除某学生某班级空间（附件一起删除）
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delClassSpaceById")
	public void delClassSpaceById(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if (jsonObject.getString("classSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceId = 0;
			try {
				classSpaceId = Integer.parseInt(jsonObject.getString("classSpaceId"));
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
		JSONObject jsonData = new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = CommonTool.getRequestPostStr(request);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
		JSONObject jsonObject = JSONObject.fromObject(requestStr);
						
		if (jsonObject.getString("classSpaceCommentId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int classSpaceCommentId = 0;
			try {
				classSpaceCommentId = Integer.parseInt(jsonObject.getString("classSpaceCommentId"));
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
