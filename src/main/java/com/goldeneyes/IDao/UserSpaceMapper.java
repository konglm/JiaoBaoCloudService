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
	List<UserSpace> getNoReadUserSpacesByUser(int userId,int spaceType);
	/**
	 * 获取用户某条用户空间是否点赞
	 * @param userId
	 * @param spaceType
	 * @param noteId
	 * @return
	 */
	int getIsLikeUserSpaceByUser(int userId,int spaceType,int noteId);
	/**
	 * 获取用户空间所有点赞用户
	 * @param noteId
	 * @return
	 */
	List<Integer> getIsLikeUsersByUserSpace(int noteId);
	/**
	 * 获取某学生用户空间列表
	 * @param studentId
	 * @return
	 */
	List<UserSpace> getUserSpacesByUser(int userId);
	/**
	 * 获取某条用户空间信息
	 * @param noteId
	 * @return
	 */
	UserSpace getUserSpaceById(int noteId);
	/**
	 * 屏蔽某学生某用户空间信息
	 * @param record
	 * @return
	 */
	int offUserSpaceByUser(UserSpace record);
}