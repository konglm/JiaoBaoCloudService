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
	public List<ClassSpace> getNoReadClassSpacesByUser(int userId,int spaceType) throws Exception;
	/**
	 * 获取用户某条班级空间是否点赞
	 * @param userId
	 * @param spaceType
	 * @param ClassSpaceId
	 * @return
	 */
	public int getIsLikeClassSpaceByUser(int userId,int spaceType,int ClassSpaceId) throws Exception;
	/**
	 * 获取班级空间所有评论
	 * @param ClassSpaceId
	 * @return
	 */
	public List<ClassSpaceComment> getClassSpaceCommentsByUser(int ClassSpaceId) throws Exception;
	/**
	 * 获取班级空间所有点赞用户
	 * @param ClassSpaceId
	 * @return
	 */
	public List<Integer> getIsLikeUsersByClassSpace(int ClassSpaceId) throws Exception;
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
	public List<ClassSpaceComment> getClassSpaceCommentReplysByUser(int userId) throws Exception;
	/**
	 * 获取某学生班级空间列表
	 * @param studentId
	 * @return
	 */
	public List<ClassSpace> getClassSpacesByClass(int studentId) throws Exception;
	/**
	 * 获取某条班级空间信息
	 * @param ClassSpaceId
	 * @return
	 */
	public ClassSpace getClassSpaceById(int ClassSpaceId) throws Exception;
	/**
	 * 获取某条班级空间附件列表
	 * @param ClassSpaceId
	 * @return
	 */
	public List<ClassSpaceEnc> getClassSpaceEncById(int ClassSpaceId) throws Exception;
	/**
	 * 新增某学生班级空间信息
	 * @param studentId
	 * @param ClassSpaceStr
	 * @param teacherId
	 * @return 0：失败，其他：新增的班级空间ID
	 */
	public int addClassSpace(int studentId,String ClassSpaceStr,int teacherId) throws Exception;
	/**
	 * 新增某学生班级空间附件
	 * @param ClassSpaceId
	 * @param encType
	 * @param encAddr
	 * @param encImg
	 * @param teacherId
	 * @param encOrder
	 * @return 是否删除成功 0失败1成功
	 */
	public int addClassSpaceEnc(int ClassSpaceId,String encType,String encAddr,String encImg,int teacherId,int encOrder) throws Exception;
	/**
	 * 新增某用户某班级空间评论
	 * @param userId
	 * @param ClassSpaceId
	 * @param commentStr
	 * @return 是否删除成功 0失败1成功
	 */
	public int addClassSpaceComment(int userId,int ClassSpaceId,String commentStr) throws Exception;
	/**
	 * 新增某用户某班级空间评论回复
	 * @param userId
	 * @param replyUserId
	 * @param ClassSpaceId
	 * @param commentStr
	 * @return 是否删除成功 0失败1成功
	 */
	public int addClassSpaceCommentReply(int userId,int replyUserId,int ClassSpaceId,String commentStr) throws Exception;
	/**
	 * 推送给某用户的某班级空间
	 * @param userId
	 * @param spaceType
	 * @param ClassSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int sendClassSpaceForUser(int userId,int spaceType,int ClassSpaceId) throws Exception;
	/**
	 * 修改某用户某班级空间阅读状态为已读
	 * @param userId
	 * @param spaceType
	 * @param ClassSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setClassSpaceReadByUser(int userId,int spaceType,int ClassSpaceId) throws Exception;
	/**
	 * 新增某用户某班级空间点赞状态为点赞
	 * @param userId
	 * @param spaceType
	 * @param ClassSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setClassSpaceLikeByUser(int userId,int spaceType,int ClassSpaceId) throws Exception;
	/**
	 * 修改某用户某班级空间评论回复查看状态
	 * @param userId
	 * @param ClassSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int setClassSpaceCommentReplyByUser(int ClassSpaceCommentId) throws Exception;
	/**
	 * 屏蔽某学生某班级空间信息
	 * @param studentId
	 * @param ClassSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int offClassSpaceByClass(int ClassSpaceId) throws Exception;
	/**
	 * 删除某学生某班级空间（附件一起删除）
	 * @param ClassSpaceId
	 * @return 是否删除成功 0失败1成功
	 */
	public int delClassSpaceByClass(int ClassSpaceId) throws Exception;
	/**
	 * 删除某用户某班级空间评论
	 * @param ClassSpaceCommentId
	 * @return 是否删除成功 0失败1成功
	 */
	public int delClassSpaceCommentByClass(int ClassSpaceCommentId) throws Exception;
}
