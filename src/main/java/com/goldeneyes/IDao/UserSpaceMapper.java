package com.goldeneyes.IDao;

import java.util.List;

import com.goldeneyes.pojo.UserSpace;
import com.goldeneyes.vo.AboutMe;

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
	int getNoReadUserSpacesCntByUser(int userId,int spaceType,int noteType);
	/**
	 * 获取用户未读用户空间列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	List<UserSpace> getNoReadUserSpacesByUser(int userId,int spaceType,int pageIndex,int pageSize,int noteType);
	/**
	 * 获取用户未读某用户空间条数
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	int getNoReadUserSpacesCntByUserForPublisher(int userId,int spaceType,int publisherId,int noteType);
	/**
	 * 获取用户未读某用户空间列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	List<UserSpace> getNoReadUserSpacesByUserForPublisher(int userId,int spaceType,int pageIndex,int pageSize,int publisherId,int noteType);
	/**
	 * 获取用户某条用户空间是否点赞
	 * @param userId
	 * @param spaceType
	 * @param userSpaceId
	 * @return
	 */
	int getIsLikeUserSpaceByUser(int userId,int userSpaceId);
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
	int getUserSpacesCntByUser(int userId,int noteType);
	/**
	 * 获取某用户空间列表
	 * @param userId
	 * @return
	 */
	List<UserSpace> getUserSpacesByUser(int userId,int pageIndex,int pageSize,int noteType);
	/**
	 * 获取某条用户空间信息
	 * @param userSpaceId
	 * @return
	 */
	UserSpace getUserSpaceById(int userSpaceId,int noteType);
	/**
	 * 屏蔽某学生某用户空间信息
	 * @param record
	 * @return
	 */
	int setOffUserSpaceById(UserSpace record);
	/**
	 * 获取与我相关的数量
	 * @param userId
	 * @return
	 */
	int getAboutMeCnt(int userId);
	/**
	 * 获取与我相关
	 * @param userId
	 * @return
	 */
	List<AboutMe> getAboutMe(int userId,int pageIndex,int pageSize);
	/**
	 * 获取未读的与我相关的数量
	 * @param userId
	 * @return
	 */
	int getNoReadAboutMe(int userId);
	/**
	 * 获取多用户空间的最后一条数据
	 * @param userIds
	 * @return
	 */
	List<UserSpace> getUserSpacesByIds(String userIds);
	/**
	 * 获取用户所有空间的信息
	 * @param userId
	 * @return
	 */
	List<UserSpace> getAllUserSpaceByUser(int userId);
	/**
	 * 通过用户ID获取用户空间info的ID
	 * @param userId
	 * @return
	 */
	int getUserSpaceInfoIdByUser(int userId);
}