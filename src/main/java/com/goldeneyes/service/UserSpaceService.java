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

package com.goldeneyes.service;

import java.util.List;

import com.goldeneyes.pojo.UserSpace;
import com.goldeneyes.pojo.UserSpaceComment;
import com.goldeneyes.pojo.UserSpaceMsg;

/**
 * @author konglm
 *
 */
public interface UserSpaceService {
	public List<UserSpace> getAllUserSpace() throws Exception;
	
	/**
	 * 获取用户未读用户空间条数
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public int getNoReadUserSpacesCntByUser(int userId,int spaceType) throws Exception;
	/**
	 * 获取用户未读用户空间列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public List<UserSpace> getNoReadUserSpacesByUser(int userId,int spaceType,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取用户未读某用户空间条数
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public int getNoReadUserSpacesCntByUserForPublisher(int userId,int spaceType,int publisherId) throws Exception;
	/**
	 * 获取用户未读某用户空间列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public List<UserSpace> getNoReadUserSpacesByUserForPublisher(int userId,int spaceType,int pageIndex,int pageSize,int publisherId) throws Exception;
	/**
	 * 获取用户某条用户空间是否点赞
	 * @param userId
	 * @param spaceType
	 * @param userSpaceId
	 * @return
	 */
	public int getIsLikeUserSpaceByUser(int userId,int spaceType,int userSpaceId) throws Exception;
	/**
	 * 获取用户空间所有评论条数
	 * @param userSpaceId
	 * @return
	 */
	public int getUserSpaceCommentsCntById(int userSpaceId) throws Exception;
	/**
	 * 获取用户空间所有评论
	 * @param userSpaceId
	 * @return
	 */
	public List<UserSpaceComment> getUserSpaceCommentsById(int userSpaceId,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取用户空间所有点赞用户
	 * @param userSpaceId
	 * @return
	 */
	public List<Integer> getIsLikeUsersById(int userSpaceId) throws Exception;
	/**
	 * 获取用户用户空间所有未读评论回复条数
	 * @param userId
	 * @return
	 */
	public int getUserSpaceCommentReplysCntByUser(int userId) throws Exception;
	/**
	 * 获取用户用户空间所有未读评论回复列表
	 * @param userId
	 * @return
	 */
	public List<UserSpaceComment> getUserSpaceCommentReplysByUser(int userId,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取某用户空间条数
	 * @param userId
	 * @return
	 */
	public int getUserSpacesCntByUser(int userId) throws Exception;
	/**
	 * 获取某用户空间列表
	 * @param userId
	 * @return
	 */
	public List<UserSpace> getUserSpacesByUser(int userId,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取某条用户空间信息
	 * @param userSpaceId
	 * @return
	 */
	public UserSpace getUserSpaceById(int userSpaceId) throws Exception;
	/**
	 * 新增某用户空间信息
	 * @param userId
	 * @param msgContent
	 * @param teacherId
	 * @return 0：失败，其他：新增的用户空间ID
	 */
	public int addUserSpace(int userId,String msgContent,int noteType,int encType,String encAddr,String encImg,String encIntro) throws Exception;
	/**
	 * 新增某用户某用户空间评论
	 * @param userId
	 * @param userSpaceId
	 * @param commentContent
	 * @return 是否删除成功 0失败1成功
	 */
	public int addUserSpaceComment(int userId,int userSpaceId,String commentContent) throws Exception;
	/**
	 * 新增某用户某用户空间评论回复
	 * @param userId
	 * @param replyUserId
	 * @param userSpaceId
	 * @param commentContent
	 * @return 是否删除成功 0失败1成功
	 */
	public int addUserSpaceCommentReply(int userId,int replyUserId,int userSpaceId,String commentContent,int upperId) throws Exception;
	/**
	 * 推送给某用户的某用户空间
	 * @param userId
	 * @param spaceType
	 * @param userSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int addUserSpaceForUser(int userId,int spaceType,int userSpaceId) throws Exception;
	/**
	 * 修改某用户某用户空间阅读状态为已读
	 * @param userId
	 * @param spaceType
	 * @param userSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setUserSpaceReadByUser(int userId,int spaceType,int userSpaceId) throws Exception;
	/**
	 * 修改某用户某用户空间点赞状态为点赞
	 * @param userId
	 * @param spaceType
	 * @param userSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setUserSpaceLikeByUser(int userId,int spaceType,int userSpaceId) throws Exception;
	/**
	 * 修改某用户空间评论回复查看状态
	 * @param userSpaceCommentId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setUserSpaceCommentReplyById(int userSpaceCommentId) throws Exception;
	/**
	 * 屏蔽某用户空间信息
	 * @param userSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setOffUserSpaceById(int userSpaceId) throws Exception;
	/**
	 * 删除某空间（附件一起删除）
	 * @param userSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int delUserSpaceById(int userSpaceId) throws Exception;
	/**
	 * 删除某用户某用户空间评论
	 * @param userSpaceCommentId
	 * @return 是否删除成功 0失败1成功
	 */
	public int delUserSpaceCommentById(int userSpaceCommentId) throws Exception;
	/**
	 * 获取用户空间所有留言条数
	 * @param userSpaceId
	 * @return
	 */
	public int getUserSpaceMsgsCntById(int userSpaceId) throws Exception;
	/**
	 * 获取用户空间所有留言
	 * @param userSpaceId
	 * @return
	 */
	public List<UserSpaceMsg> getUserSpaceMsgsById(int userSpaceId,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取用户用户空间所有未读留言回复条数
	 * @param userId
	 * @return
	 */
	public int getUserSpaceMsgReplysCntByUser(int userId) throws Exception;
	/**
	 * 获取用户用户空间所有未读留言回复列表
	 * @param userId
	 * @return
	 */
	public List<UserSpaceMsg> getUserSpaceMsgReplysByUser(int userId,int pageIndex,int pageSize) throws Exception;
	/**
	 * 新增某用户某用户空间留言
	 * @param userId
	 * @param userSpaceId
	 * @param msgStr
	 * @return 是否删除成功 0失败1成功
	 */
	public int addUserSpaceMsg(int userId,int userSpaceId,String msgStr) throws Exception;
	/**
	 * 新增某用户某用户空间留言回复
	 * @param userId
	 * @param replyUserId
	 * @param userSpaceId
	 * @param msgStr
	 * @return 是否删除成功 0失败1成功
	 */
	public int addUserSpaceMsgReply(int userId,int replyUserId,int userSpaceId,String msgStr,int upperId) throws Exception;
	/**
	 * 修改某用户空间留言回复查看状态
	 * @param userSpaceMsgId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setUserSpaceMsgReplyById(int userSpaceMsgId) throws Exception;
	/**
	 * 删除某用户某用户空间留言
	 * @param userSpaceMsgId
	 * @return 是否删除成功 0失败1成功
	 */
	public int delUserSpaceMsgById(int userSpaceMsgId) throws Exception;
}
