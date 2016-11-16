package com.goldeneyes.IDao;

import java.util.List;

import com.goldeneyes.pojo.SpaceContentStatus;
import com.goldeneyes.pojo.UserSpaceComment;

public interface UserSpaceCommentMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(UserSpaceComment record);

    int insertSelective(UserSpaceComment record);

    UserSpaceComment selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(UserSpaceComment record);

    int updateByPrimaryKey(UserSpaceComment record);
    /**
     * 获取最大ID
     * @return
     */
    int getMaxId();
    /**
   	 * 获取用户空间所有评论条数
   	 * @param userSpaceId
   	 * @return
   	 */
   	int getUserSpaceCommentsCntById(int userSpaceId);
    /**
	 * 获取用户空间所有评论
	 * @param userSpaceId
	 * @return
	 */
	List<UserSpaceComment> getUserSpaceCommentsById(int userSpaceId,int pageIndex,int pageSize);
	/**
	 * 获取用户用户空间所有未读评论回复条数
	 * @param userId
	 * @return
	 */
	int getUserSpaceCommentReplysCntByUser(int userId);
	/**
	 * 获取用户用户空间所有未读评论回复列表
	 * @param userId
	 * @return
	 */
	List<UserSpaceComment> getUserSpaceCommentReplysByUser(int userId,int pageIndex,int pageSize);
	/**
	 * 删除某用户空间的评论
	 * @param userSpaceId
	 * @return
	 */
	int deleteByUserSpaceId(int userSpaceId);
	/**
	 * 修改某用户空间评论查看状态
	 * @param record
	 * @return
	 */
	int setUserSpaceCommentById(UserSpaceComment record);
	/**
	 * 修改某用户空间回复查看状态
	 * @param record
	 * @return
	 */
	int setUserSpaceCommentReplyById(UserSpaceComment record);
	/**
	 * 修改某用户空间点赞查看状态
	 * @param record
	 * @return
	 */
	int setUserSpaceLikeById(UserSpaceComment record);
	/**
	 * 获取某评论ID评论列表
	 * @param userSpaceCommentId
	 * @return
	 */
	List<UserSpaceComment> getCommentsById(int userSpaceCommentId);
}