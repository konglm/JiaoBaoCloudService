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

import com.goldeneyes.pojo.ClassSpace;
import com.goldeneyes.pojo.ClassSpaceComment;
import com.goldeneyes.pojo.ClassSpaceEnc;

/**
 * @author konglm
 *
 */
public interface ClassSpaceService {
	public List<ClassSpace> getAllClassSpace() throws Exception;
	
	/**
	 * 获取用户未读班级空间条数
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public int getNoReadClassSpacesCntByUser(int userId,int spaceType) throws Exception;
	/**
	 * 获取用户未读班级空间列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	public List<ClassSpace> getNoReadClassSpacesByUser(int userId,int spaceType,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取用户某条班级空间是否点赞
	 * @param userId
	 * @param spaceType
	 * @param classSpaceId
	 * @return
	 */
	public int getIsLikeClassSpaceByUser(int userId,int spaceType,int classSpaceId) throws Exception;
	/**
	 * 获取班级空间所有评论条数
	 * @param classSpaceId
	 * @return
	 */
	public int getClassSpaceCommentsCntById(int classSpaceId) throws Exception;
	/**
	 * 获取班级空间所有评论
	 * @param classSpaceId
	 * @return
	 */
	public List<ClassSpaceComment> getClassSpaceCommentsById(int classSpaceId,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取班级空间所有点赞用户
	 * @param classSpaceId
	 * @return
	 */
	public List<Integer> getIsLikeUsersById(int classSpaceId) throws Exception;
	/**
	 * 获取用户班级空间所有未读评论回复条数
	 * @param userId
	 * @return
	 */
	public int getClassSpaceCommentReplysCntByUser(int userId) throws Exception;
	/**
	 * 获取用户班级空间所有未读评论回复列表
	 * @param userId
	 * @return
	 */
	public List<ClassSpaceComment> getClassSpaceCommentReplysByUser(int userId,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取某班级空间条数
	 * @param classId
	 * @return
	 */
	public int getClassSpacesCntByClass(int classId) throws Exception;
	/**
	 * 获取某班级空间列表
	 * @param classId
	 * @return
	 */
	public List<ClassSpace> getClassSpacesByClass(int classId,int pageIndex,int pageSize) throws Exception;
	/**
	 * 获取某条班级空间信息
	 * @param classSpaceId
	 * @return
	 */
	public ClassSpace getClassSpaceById(int classSpaceId) throws Exception;
	/**
	 * 获取某条班级空间附件列表
	 * @param classSpaceId
	 * @return
	 */
	public List<ClassSpaceEnc> getClassSpaceEncById(int classSpaceId) throws Exception;
	/**
	 * 新增某班级空间信息
	 * @param classId
	 * @param msgContent
	 * @param teacherId
	 * @return 0：失败，其他：新增的班级空间ID
	 */
	public int addClassSpace(int classId,String msgContent,int teacherId) throws Exception;
	/**
	 * 新增某班级空间附件
	 * @param classSpaceId
	 * @param encType
	 * @param encAddr
	 * @param encImg
	 * @param teacherId
	 * @param encOrder
	 * @return 是否删除成功 0失败1成功
	 */
	public int addClassSpaceEnc(int classSpaceId,String encName,String encType,String encAddr,String encImg,int teacherId,int encOrder) throws Exception;
	/**
	 * 新增某用户某班级空间评论
	 * @param userId
	 * @param classSpaceId
	 * @param commentContent
	 * @return 是否删除成功 0失败1成功
	 */
	public int addClassSpaceComment(int userId,int classSpaceId,String commentContent) throws Exception;
	/**
	 * 新增某用户某班级空间评论回复
	 * @param userId
	 * @param replyUserId
	 * @param classSpaceId
	 * @param commentContent
	 * @return 是否删除成功 0失败1成功
	 */
	public int addClassSpaceCommentReply(int userId,int replyUserId,int classSpaceId,String commentContent,int upperId) throws Exception;
	/**
	 * 推送给某用户的某班级空间
	 * @param userId
	 * @param spaceType
	 * @param classSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int addClassSpaceForUser(int userId,int spaceType,int classSpaceId) throws Exception;
	/**
	 * 修改某用户某班级空间阅读状态为已读
	 * @param userId
	 * @param spaceType
	 * @param classSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setClassSpaceReadByUser(int userId,int spaceType,int classSpaceId) throws Exception;
	/**
	 * 修改某用户某班级空间点赞状态为点赞
	 * @param userId
	 * @param spaceType
	 * @param classSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setClassSpaceLikeByUser(int userId,int spaceType,int classSpaceId) throws Exception;
	/**
	 * 修改某班级空间评论回复查看状态
	 * @param classSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setClassSpaceCommentReplyById(int classSpaceCommentId) throws Exception;
	/**
	 * 屏蔽某班级空间信息
	 * @param classSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setOffClassSpaceById(int classSpaceId) throws Exception;
	/**
	 * 删除某班级空间（附件一起删除）
	 * @param classSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int delClassSpaceById(int classSpaceId) throws Exception;
	/**
	 * 删除某用户某班级空间评论
	 * @param classSpaceCommentId
	 * @return 是否删除成功 0失败1成功
	 */
	public int delClassSpaceCommentById(int classSpaceCommentId) throws Exception;
}
