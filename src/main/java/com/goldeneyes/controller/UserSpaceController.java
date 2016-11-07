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

import com.goldeneyes.pojo.UserSpace;
import com.goldeneyes.pojo.UserSpaceComment;
import com.goldeneyes.pojo.UserSpaceEnc;
import com.goldeneyes.pojo.UserSpaceMsg;
import com.goldeneyes.service.UserSpaceService;
import com.goldeneyes.util.CommonTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author konglm
 *
 */
@Controller
@RequestMapping("/userSpace")
public class UserSpaceController {
	@Resource
	UserSpaceService userSpaceService;

	@RequestMapping("/getAllUserSpaces")
	public void getAllUserSpace(HttpServletRequest request, HttpServletResponse response, Model model) {
		JSONObject jsonData = new JSONObject();
		List<UserSpace> userSpaces = new ArrayList<UserSpace>();
		try {
			userSpaces = userSpaceService.getAllUserSpace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
			return;
		}
		JSONArray jsonArray = new JSONArray();
		for (UserSpace userSpace : userSpaces) {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("TabId", userSpace.getTabid());
			jsonobj.put("UserId", userSpace.getUserid());
			jsonobj.put("MsgContent", userSpace.getMsgcontent());
			jsonobj.put("PublisherId", userSpace.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("PublishDate", formater.format(userSpace.getPublishdate()));
			jsonArray.put(jsonobj);
		}
		jsonData.put("Data", jsonArray);
		// 在这里输出，手机端就拿到web返回的值了
		CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
	}

	/**
	 * 获取用户未读用户空间条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoReadUserSpacesCntByUser")
	public void getNoReadUserSpacesCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
				cnt = userSpaceService.getNoReadUserSpacesCntByUser(userId, 3);
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
	 * 获取用户未读用户空间列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoReadUserSpacesByUser")
	public void getNoReadUserSpacesByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("pageIndex") == null)
				|| (jsonObject.getString("pageSize") == null)) {
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
			List<UserSpace> userSpaces = new ArrayList<UserSpace>();
			try {
				totalCnt = userSpaceService.getNoReadUserSpacesCntByUser(userId, 3);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				userSpaces = userSpaceService.getNoReadUserSpacesByUser(userId, 3, pageIndex, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (UserSpace userSpace : userSpaces) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", userSpace.getTabid());
				jsonobj.put("UserId", userSpace.getUserid());
				jsonobj.put("MsgContent", userSpace.getMsgcontent());
				jsonobj.put("PublisherId", userSpace.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(userSpace.getPublishdate()));
				jsonobj.put("NoteType", userSpace.getNotetype());
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
	 * 获取用户某条用户空间是否点赞
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getIsLikeUserSpaceByUser")
	public void getIsLikeUserSpaceByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("userSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int userSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int isLike = 0;
			try {
				isLike = userSpaceService.getIsLikeUserSpaceByUser(userId, 3, userSpaceId);
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
	 * 获取用户空间所有评论条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpaceCommentsCntById")
	public void getUserSpaceCommentsCntById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if (jsonObject.getString("userSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = userSpaceService.getUserSpaceCommentsCntById(userSpaceId);
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
	 * 获取用户空间所有评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpaceCommentsById")
	public void getUserSpaceCommentsById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userSpaceId") == null) || (jsonObject.getString("pageIndex") == null)
				|| (jsonObject.getString("pageSize") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int userSpaceId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
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
			List<UserSpaceComment> userSpaceComments = new ArrayList<UserSpaceComment>();
			try {
				totalCnt = userSpaceService.getUserSpaceCommentsCntById(userSpaceId);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				userSpaceComments = userSpaceService.getUserSpaceCommentsById(userSpaceId, pageIndex, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (UserSpaceComment userSpaceComment : userSpaceComments) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", userSpaceComment.getTabid());
				jsonobj.put("UserId", userSpaceComment.getUserid());
				jsonobj.put("CommentContent", userSpaceComment.getCommentcontent());
				jsonobj.put("ReplyId", userSpaceComment.getReplyid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("CommentDate", formater.format(userSpaceComment.getCommentdate()));
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
	 * 获取用户空间所有点赞用户
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
						
		if (jsonObject.getString("userSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			List<Integer> userIds = new ArrayList<Integer>();
			try {
				userIds = userSpaceService.getIsLikeUsersById(userSpaceId);
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
	 * 获取用户用户空间所有未读评论回复条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpaceCommentReplysCntByUser")
	public void getUserSpaceCommentReplysCntByUser(HttpServletRequest request, HttpServletResponse response,
			Model model) {
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
				cnt = userSpaceService.getUserSpaceCommentReplysCntByUser(userId);
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
	 * 获取用户用户空间所有未读评论回复列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpaceCommentReplysByUser")
	public void getUserSpaceCommentReplysByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("pageIndex") == null)
				|| (jsonObject.getString("pageSize") == null)) {
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
			List<UserSpaceComment> userSpaceComments = new ArrayList<UserSpaceComment>();
			try {
				totalCnt = userSpaceService.getUserSpaceCommentReplysCntByUser(userId);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				userSpaceComments = userSpaceService.getUserSpaceCommentReplysByUser(userId, pageIndex, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (UserSpaceComment userSpaceComment : userSpaceComments) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", userSpaceComment.getTabid());
				jsonobj.put("UserId", userSpaceComment.getUserid());
				jsonobj.put("CommentContent", userSpaceComment.getCommentcontent());
				jsonobj.put("UserSpaceId", userSpaceComment.getUserspaceid());
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
	 * 获取某学生用户空间条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpacesCntByUser")
	public void getUserSpacesCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
				cnt = userSpaceService.getUserSpacesCntByUser(userId);
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
	 * 获取某学生用户空间列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpacesByUser")
	public void getUserSpacesByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("pageIndex") == null)
				|| (jsonObject.getString("pageSize") == null)) {
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
			List<UserSpace> userSpaces = new ArrayList<UserSpace>();
			try {
				totalCnt = userSpaceService.getUserSpacesCntByUser(userId);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				userSpaces = userSpaceService.getUserSpacesByUser(userId, pageIndex, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (UserSpace userSpace : userSpaces) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", userSpace.getTabid());
				jsonobj.put("UserId", userSpace.getUserid());
				jsonobj.put("MsgContent", userSpace.getMsgcontent());
				jsonobj.put("PublisherId", userSpace.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(userSpace.getPublishdate()));
				jsonobj.put("NoteType", userSpace.getNotetype());
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
	 * 获取某条用户空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpaceById")
	public void getUserSpaceById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if (jsonObject.getString("userSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			UserSpace userSpace = new UserSpace();
			try {
				userSpace = userSpaceService.getUserSpaceById(userSpaceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			jsonData.put("UserId", userSpace.getUserid());
			jsonData.put("MsgContent", userSpace.getMsgcontent());
			jsonData.put("PublisherId", userSpace.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonData.put("PublishDate", formater.format(userSpace.getPublishdate()));
			jsonData.put("NoteType", userSpace.getNotetype());
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 获取某条用户空间附件列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpaceEncById")
	public void getUserSpaceEncById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if (jsonObject.getString("userSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			List<UserSpaceEnc> userSpaceEncs = new ArrayList<UserSpaceEnc>();
			try {
				userSpaceEncs = userSpaceService.getUserSpaceEncById(userSpaceId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (UserSpaceEnc userSpaceEnc : userSpaceEncs) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("EncType", userSpaceEnc.getEnctype());
				jsonobj.put("EncName", userSpaceEnc.getEncname());
				jsonobj.put("EncAddr", userSpaceEnc.getEncaddr());
				jsonobj.put("EncImgAddr", userSpaceEnc.getEncimgaddr());
				jsonobj.put("PublisherId", userSpaceEnc.getPublisherid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("PublishDate", formater.format(userSpaceEnc.getPublishdate()));
				jsonobj.put("EncOrder", userSpaceEnc.getEncorder());
				jsonArray.put(jsonobj);
			}
			jsonData.put("Data", jsonArray);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 新增某学生用户空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addUserSpace")
	public void addUserSpace(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("msgContent") == null)
				|| (jsonObject.getString("teacherId") == null) || (jsonObject.getString("noteType") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			String msgContent = "";
			int teacherId = 0;
			int noteType = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
				msgContent = jsonObject.getString("msgContent");
				teacherId = Integer.parseInt(jsonObject.getString("teacherId"));
				noteType = Integer.parseInt(jsonObject.getString("noteType"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int id = 0;
			try {
				id = userSpaceService.addUserSpace(userId, msgContent, teacherId, noteType);
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
	 * 新增某学生用户空间附件
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addUserSpaceEnc")
	public void addUserSpaceEnc(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userSpaceId") == null) || (jsonObject.getString("encType") == null)
				|| (jsonObject.getString("encAddr") == null) || (jsonObject.getString("encImg") == null)
				|| (jsonObject.getString("teacherId") == null) || (jsonObject.getString("encOrder") == null)
				|| (jsonObject.getString("encName") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			String encName = "";
			String encType = "";
			String encAddr = "";
			String encImg = "";
			int teacherId = 0;
			int encOrder = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
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
				success = userSpaceService.addUserSpaceEnc(userSpaceId,encName, encType, encAddr, encImg, teacherId, encOrder);
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
	 * 新增某用户某用户空间评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addUserSpaceComment")
	public void addUserSpaceComment(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userSpaceId") == null) || (jsonObject.getString("commentContent") == null)
				|| (jsonObject.getString("userId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			String commentContent = "";
			int userId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
				commentContent = jsonObject.getString("commentContent");
				userId = Integer.parseInt(jsonObject.getString("userId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.addUserSpaceComment(userId, userSpaceId, commentContent);
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
	 * 新增某用户某用户空间评论回复
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addUserSpaceCommentReply")
	public void addUserSpaceCommentReply(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userSpaceId") == null) || (jsonObject.getString("commentContent") == null)
				|| (jsonObject.getString("userId") == null) || (jsonObject.getString("replyUserId") == null)
				|| (jsonObject.getString("upperId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			String commentContent = "";
			int userId = 0;
			int replyUserId = 0;
			int upperId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
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
				success = userSpaceService.addUserSpaceCommentReply(userId, replyUserId, userSpaceId, commentContent, upperId);
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
	 * 推送给某用户的某用户空间
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addUserSpaceForUser")
	public void addUserSpaceForUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("userSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int userSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.addUserSpaceForUser(userId, 3, userSpaceId);
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
	 * 修改某用户某用户空间阅读状态为已读
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setUserSpaceReadByUser")
	public void setUserSpaceReadByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("userSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int userSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.setUserSpaceReadByUser(userId, 3, userSpaceId);
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
	 * 修改某用户某用户空间点赞状态为点赞
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setUserSpaceLikeByUser")
	public void setUserSpaceLikeByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("userSpaceId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int userSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonObject.getString("userId"));
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.setUserSpaceLikeByUser(userId, 3, userSpaceId);
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
	 * 修改某用户某用户空间评论回复查看状态
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setUserSpaceCommentReplyById")
	public void setUserSpaceCommentReplyById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if (jsonObject.getString("userSpaceCommentId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceCommentId = 0;
			try {
				userSpaceCommentId = Integer.parseInt(jsonObject.getString("userSpaceCommentId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.setUserSpaceCommentReplyById(userSpaceCommentId);
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
	 * 屏蔽某学生某用户空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setOffUserSpaceById")
	public void setOffUserSpaceById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if (jsonObject.getString("userSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.setOffUserSpaceById(userSpaceId);
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
	 * 删除某学生某用户空间（附件一起删除）
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delUserSpaceById")
	public void delUserSpaceById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if (jsonObject.getString("userSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.delUserSpaceById(userSpaceId);
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
	 * 删除某用户某用户空间评论
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delUserSpaceCommentById")
	public void delUserSpaceCommentById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if (jsonObject.getString("userSpaceCommentId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceCommentId = 0;
			try {
				userSpaceCommentId = Integer.parseInt(jsonObject.getString("userSpaceCommentId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.delUserSpaceCommentById(userSpaceCommentId);
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
	 * 获取用户空间所有留言条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpaceMsgsCntById")
	public void getUserSpaceMsgsCntById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if (jsonObject.getString("userSpaceId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = userSpaceService.getUserSpaceMsgsCntById(userSpaceId);
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
	 * 获取用户空间所有留言
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpaceMsgsById")
	public void getUserSpaceMsgsById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userSpaceId") == null) || (jsonObject.getString("pageIndex") == null) || (jsonObject.getString("pageSize") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int userSpaceId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
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
			List<UserSpaceMsg> userSpaceMsgs = new ArrayList<UserSpaceMsg>();
			try {
				userSpaceMsgs = userSpaceService.getUserSpaceMsgsById(userSpaceId,pageIndex,pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (UserSpaceMsg userSpaceMsg : userSpaceMsgs) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", userSpaceMsg.getTabid());
				jsonobj.put("UserId", userSpaceMsg.getUserid());
				jsonobj.put("MsgContent", userSpaceMsg.getMsgcontent());
				jsonobj.put("ReplyId", userSpaceMsg.getReplyid());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("MsgDate", formater.format(userSpaceMsg.getMsgdate()));
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
	 * 获取用户用户空间所有未读留言回复条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpaceMsgReplysCntByUser")
	public void getUserSpaceMsgReplysCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
				cnt = userSpaceService.getUserSpaceMsgReplysCntByUser(userId);
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
	 * 获取用户用户空间所有未读留言回复列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpaceMsgReplysByUser")
	public void getUserSpaceMsgReplysByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userId") == null) || (jsonObject.getString("pageIndex") == null)
				|| (jsonObject.getString("pageSize") == null)) {
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
			List<UserSpaceMsg> userSpaceMsgs = new ArrayList<UserSpaceMsg>();
			try {
				totalCnt = userSpaceService.getUserSpaceMsgReplysCntByUser(userId);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				userSpaceMsgs = userSpaceService.getUserSpaceMsgReplysByUser(userId, pageIndex, pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (UserSpaceMsg userSpaceMsg : userSpaceMsgs) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("TabId", userSpaceMsg.getTabid());
				jsonobj.put("UserId", userSpaceMsg.getUserid());
				jsonobj.put("MsgContent", userSpaceMsg.getMsgcontent());
				jsonobj.put("UserSpaceId", userSpaceMsg.getUserspaceid());
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
	 * 新增某用户某用户空间留言
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addUserSpaceMsg")
	public void addUserSpaceMsg(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userSpaceId") == null) || (jsonObject.getString("msgStr") == null)
				|| (jsonObject.getString("userId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			String msgStr = "";
			int userId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
				msgStr = jsonObject.getString("msgStr");
				userId = Integer.parseInt(jsonObject.getString("userId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.addUserSpaceMsg(userId, userSpaceId, msgStr);
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
	 * 新增某用户某用户空间留言回复
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addUserSpaceMsgReply")
	public void addUserSpaceMsgReply(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if ((jsonObject.getString("userSpaceId") == null) || (jsonObject.getString("msgStr") == null)
				|| (jsonObject.getString("userId") == null) || (jsonObject.getString("replyUserId") == null)
				|| (jsonObject.getString("upperId") == null)) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			String msgStr = "";
			int userId = 0;
			int replyUserId = 0;
			int upperId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonObject.getString("userSpaceId"));
				msgStr = jsonObject.getString("msgStr");
				userId = Integer.parseInt(jsonObject.getString("userId"));
				replyUserId = Integer.parseInt(jsonObject.getString("replyUserId"));
				upperId = Integer.parseInt(jsonObject.getString("upperId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.addUserSpaceMsgReply(userId, replyUserId, userSpaceId, msgStr, upperId);
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
	 * 修改某用户某用户空间留言回复查看状态
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setUserSpaceMsgReplyById")
	public void setUserSpaceMsgReplyById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if (jsonObject.getString("userSpaceMsgId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceMsgId = 0;
			try {
				userSpaceMsgId = Integer.parseInt(jsonObject.getString("userSpaceMsgId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.setUserSpaceMsgReplyById(userSpaceMsgId);
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
	 * 删除某用户某用户空间留言
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delUserSpaceMsgById")
	public void delUserSpaceMsgById(HttpServletRequest request, HttpServletResponse response, Model model) {
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
						
		if (jsonObject.getString("userSpaceMsgId") == null) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceMsgId = 0;
			try {
				userSpaceMsgId = Integer.parseInt(jsonObject.getString("userSpaceMsgId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.delUserSpaceMsgById(userSpaceMsgId);
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
