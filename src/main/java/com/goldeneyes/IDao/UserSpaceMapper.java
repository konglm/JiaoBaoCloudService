package com.goldeneyes.IDao;

import java.util.List;

import com.goldeneyes.pojo.UserSpace;

public interface UserSpaceMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(UserSpace record);

    int insertSelective(UserSpace record);

    UserSpace selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(UserSpace record);

    int updateByPrimaryKey(UserSpace record);
    
    List<UserSpace> getAllUserSpace();
    /**
     * 获取最大ID
     * @return
     */
    int getMaxId();
    /**
	 * 获取用户未读用户空间条数
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	int getNoReadUserSpacesCntByUser(int userId,int spaceType);
	/**
	 * 获取用户未读用户空间列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	List<UserSpace> getNoReadUserSpacesByUser(int userId,int spaceType,int pageIndex,int pageSize);
	/**
	 * 获取用户某条用户空间是否点赞
	 * @param userId
	 * @param spaceType
	 * @param userSpaceId
	 * @return
	 */
	int getIsLikeUserSpaceByUser(int userId,int spaceType,int userSpaceId);
	/**
	 * 获取用户空间所有点赞用户
	 * @param userSpaceId
	 * @return
	 */
	List<Integer> getIsLikeUsersById(int userSpaceId);
	/**
	 * 获取某用户空间条数
	 * @param userId
	 * @return
	 */
	int getUserSpacesCntByUser(int userId);
	/**
	 * 获取某用户空间列表
	 * @param userId
	 * @return
	 */
	List<UserSpace> getUserSpacesByUser(int userId,int pageIndex,int pageSize);
	/**
	 * 获取某条用户空间信息
	 * @param userSpaceId
	 * @return
	 */
	UserSpace getUserSpaceById(int userSpaceId);
	/**
	 * 屏蔽某学生某用户空间信息
	 * @param record
	 * @return
	 */
	int offUserSpaceById(UserSpace record);
}