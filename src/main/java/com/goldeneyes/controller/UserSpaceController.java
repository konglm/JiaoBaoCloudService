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

import com.goldeneyes.pojo.UserSpace;
import com.goldeneyes.pojo.UserSpaceComment;
import com.goldeneyes.pojo.UserSpaceMsg;
import com.goldeneyes.service.UserSpaceService;
import com.goldeneyes.util.CommonTool;
import com.goldeneyes.vo.AboutMe;

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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("pageIndex")
				|| !jsonInput.has("pageSize")) {
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
				jsonobj.put("EncType", userSpace.getEnctype());
				jsonobj.put("EncAddr", userSpace.getEncaddr());
				jsonobj.put("EncImgAddr", userSpace.getEncimgaddr());
				jsonobj.put("EncIntro", userSpace.getEncintro());
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
	 * 获取用户未读用户空间条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getNoReadUserSpacesCntByUserForPublisher")
	public void getNoReadUserSpacesCntByUserForPublisher(HttpServletRequest request, HttpServletResponse response, Model model) {
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("publisherId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int publisherId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				publisherId = Integer.parseInt(jsonInput.getString("publisherId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}
			int cnt = 0;
			try {
				cnt = userSpaceService.getNoReadUserSpacesCntByUserForPublisher(userId, 3, publisherId);
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
	@RequestMapping("/getNoReadUserSpacesByUserForPublisher")
	public void getNoReadUserSpacesByUserForPublisher(HttpServletRequest request, HttpServletResponse response, Model model) {
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("pageIndex")
				|| !jsonInput.has("pageSize") || !jsonInput.has("publisherId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int userId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			int publisherId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				pageIndex = Integer.parseInt(jsonInput.getString("pageIndex"));
				pageSize = Integer.parseInt(jsonInput.getString("pageSize"));
				publisherId = Integer.parseInt(jsonInput.getString("publisherId"));
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
				totalCnt = userSpaceService.getNoReadUserSpacesCntByUserForPublisher(userId, 3, publisherId);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				userSpaces = userSpaceService.getNoReadUserSpacesByUserForPublisher(userId, 3, pageIndex, pageSize, publisherId);
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
				jsonobj.put("EncType", userSpace.getEnctype());
				jsonobj.put("EncAddr", userSpace.getEncaddr());
				jsonobj.put("EncImgAddr", userSpace.getEncimgaddr());
				jsonobj.put("EncIntro", userSpace.getEncintro());
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("userSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int userSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId") || !jsonInput.has("pageIndex")
				|| !jsonInput.has("pageSize")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int userSpaceId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
				jsonobj.put("UpperId", userSpaceComment.getUpperid());
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("pageIndex")
				|| !jsonInput.has("pageSize")) {
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
				jsonobj.put("UpperId", userSpaceComment.getUpperid());
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
	 * 获取用户空间条数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpacesCntByUser")
	public void getUserSpacesCntByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
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
	 * 获取某用户空间列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getUserSpacesByUser")
	public void getUserSpacesByUser(HttpServletRequest request, HttpServletResponse response, Model model) {
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("pageIndex")
				|| !jsonInput.has("pageSize")) {
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
				jsonobj.put("EncType", userSpace.getEnctype());
				jsonobj.put("EncAddr", userSpace.getEncaddr());
				jsonobj.put("EncImgAddr", userSpace.getEncimgaddr());
				jsonobj.put("EncIntro", userSpace.getEncintro());
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
			jsonData.put("EncType", userSpace.getEnctype());
			jsonData.put("EncAddr", userSpace.getEncaddr());
			jsonData.put("EncImgAddr", userSpace.getEncimgaddr());
			jsonData.put("EncIntro", userSpace.getEncintro());
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}

	/**
	 * 新增某用户空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/addUserSpace")
	public void addUserSpace(HttpServletRequest request, HttpServletResponse response, Model model) {
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("msgContent")
				|| !jsonInput.has("noteType") || !jsonInput.has("encType")
				|| !jsonInput.has("encAddr") || !jsonInput.has("encImg")
				|| !jsonInput.has("encIntro")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			String msgContent = "";
			int noteType = 0;
			int encType = 0;
			String encAddr = "";
			String encImg = "";
			String encIntro = "";
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				msgContent = jsonInput.getString("msgContent");
				noteType = Integer.parseInt(jsonInput.getString("noteType"));
				encType = Integer.parseInt(jsonInput.getString("encType"));
				encAddr = jsonInput.getString("encAddr");
				encImg = jsonInput.getString("encImg");
				encIntro = jsonInput.getString("encIntro");
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.addUserSpace(userId, msgContent, noteType,encType,encAddr,encImg,encIntro);
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId") || !jsonInput.has("commentContent")
				|| !jsonInput.has("userId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			String commentContent = "";
			int userId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
				commentContent = jsonInput.getString("commentContent");
				userId = Integer.parseInt(jsonInput.getString("userId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId") || !jsonInput.has("commentContent")
				|| !jsonInput.has("userId") || !jsonInput.has("replyUserId")
				|| !jsonInput.has("upperId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			String commentContent = "";
			int userId = 0;
			int replyUserId = 0;
			int upperId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("userSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int userSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("userSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int userSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("userSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userId = 0;
			int userSpaceId = 0;
			try {
				userId = Integer.parseInt(jsonInput.getString("userId"));
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} 
						
		if (!jsonInput.has("userSpaceCommentId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceCommentId = 0;
			try {
				userSpaceCommentId = Integer.parseInt(jsonInput.getString("userSpaceCommentId"));
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
	 * 屏蔽某用户空间信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/setOffUserSpaceById")
	public void setOffUserSpaceById(HttpServletRequest request, HttpServletResponse response, Model model) {
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
	 * 删除某用户空间（附件一起删除）
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/delUserSpaceById")
	public void delUserSpaceById(HttpServletRequest request, HttpServletResponse response, Model model) {
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceCommentId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceCommentId = 0;
			try {
				userSpaceCommentId = Integer.parseInt(jsonInput.getString("userSpaceCommentId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId") || !jsonInput.has("pageIndex") || !jsonInput.has("pageSize")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		} else {
			int userSpaceId = 0;
			int pageIndex = 0;
			int pageSize = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
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
				jsonobj.put("UpperId", userSpaceMsg.getUpperid());
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("pageIndex")
				|| !jsonInput.has("pageSize")) {
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
				jsonobj.put("UpperId", userSpaceMsg.getUpperid());
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId") || !jsonInput.has("msgContent")
				|| !jsonInput.has("userId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			String msgContent = "";
			int userId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
				msgContent = jsonInput.getString("msgContent");
				userId = Integer.parseInt(jsonInput.getString("userId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.addUserSpaceMsg(userId, userSpaceId, msgContent);
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceId") || !jsonInput.has("msgContent")
				|| !jsonInput.has("userId") || !jsonInput.has("replyUserId")
				|| !jsonInput.has("upperId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceId = 0;
			String msgContent = "";
			int userId = 0;
			int replyUserId = 0;
			int upperId = 0;
			try {
				userSpaceId = Integer.parseInt(jsonInput.getString("userSpaceId"));
				msgContent = jsonInput.getString("msgContent");
				userId = Integer.parseInt(jsonInput.getString("userId"));
				replyUserId = Integer.parseInt(jsonInput.getString("replyUserId"));
				upperId = Integer.parseInt(jsonInput.getString("upperId"));
			} catch (Exception e) {
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1003").toString());
				return;
			}

			int success = 0;
			try {
				success = userSpaceService.addUserSpaceMsgReply(userId, replyUserId, userSpaceId, msgContent, upperId);
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceMsgId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceMsgId = 0;
			try {
				userSpaceMsgId = Integer.parseInt(jsonInput.getString("userSpaceMsgId"));
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
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userSpaceMsgId")) {
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
		} else {
			int userSpaceMsgId = 0;
			try {
				userSpaceMsgId = Integer.parseInt(jsonInput.getString("userSpaceMsgId"));
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
	
	/**
	 * 获取用户未读用户空间列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("/getAboutMe")
	public void getAboutMe(HttpServletRequest request, HttpServletResponse response, Model model) {
		//返回参数用
		JSONObject jsonData = new JSONObject();
		//接收参数用
		JSONObject jsonInput =  new JSONObject();
		
		//接收APP端发来的json请求
		String requestStr = "";
		try {
			requestStr = (String) request.getAttribute("requestStr");
			jsonInput = JSONObject.fromObject(requestStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1004").toString());
			return;
		}
						
		if (!jsonInput.has("userId") || !jsonInput.has("pageIndex")
				|| !jsonInput.has("pageSize")) {
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
			List<AboutMe> aboutMes = new ArrayList<AboutMe>();
			try {
				totalCnt = userSpaceService.getAboutMeCnt(userId);
				totalPage = CommonTool.getTotalPage(totalCnt, pageSize);
				aboutMes = userSpaceService.getAboutMe(userId,pageIndex,pageSize);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "1001").toString());
				return;
			}
			JSONArray jsonArray = new JSONArray();
			for (AboutMe aboutMe : aboutMes) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("MsgType", aboutMe.getMsgType());
				jsonobj.put("UserId", aboutMe.getUserId());
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonobj.put("MsgDate", formater.format(aboutMe.getMsgDate()));
				jsonobj.put("TabId", aboutMe.getTabId());
				jsonArray.put(jsonobj);
			}
			jsonData.put("TotalCnt", totalCnt);
			jsonData.put("TotalPage", totalPage);
			jsonData.put("Data", jsonArray);
			// 在这里输出，手机端就拿到web返回的值了
			CommonTool.outJsonString(response, CommonTool.outJson(jsonData, "0000").toString());
		}
	}
}
