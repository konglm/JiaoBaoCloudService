package com.goldeneyes.IDao;

import java.util.List;

import com.goldeneyes.pojo.ClassSpace;

public interface ClassSpaceMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(ClassSpace record);

    int insertSelective(ClassSpace record);

    ClassSpace selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(ClassSpace record);

    int updateByPrimaryKey(ClassSpace record);
    
    List<ClassSpace> getAllClassSpace();
    /**
     * 获取最大ID
     * @return
     */
    int getMaxId();
    /**
	 * 获取用户未读班级空间条数
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	int getNoReadClassSpacesCntByUser(int userId,int spaceType);
	/**
	 * 获取用户未读班级空间列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	List<ClassSpace> getNoReadClassSpacesByUser(int userId,int spaceType,int pageIndex,int pageSize);
	/**
	 * 获取用户未读某班级空间条数
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	int getNoReadClassSpacesCntByUserForClass(int userId,int spaceType,int classId);
	/**
	 * 获取用户未读某班级空间列表
	 * @param userId
	 * @param spaceType
	 * @return
	 */
	List<ClassSpace> getNoReadClassSpacesByUserForClass(int userId,int spaceType,int pageIndex,int pageSize,int classId);
	/**
	 * 获取用户某条班级空间是否点赞
	 * @param userId
	 * @param spaceType
	 * @param classSpaceId
	 * @return
	 */
	int getIsLikeClassSpaceByUser(int userId,int classSpaceId);
	/**
	 * 获取班级空间所有点赞用户
	 * @param classSpaceId
	 * @return
	 */
	List<Integer> getIsLikeUsersById(int classSpaceId);
	/**
	 * 获取班级空间条数
	 * @param classId
	 * @return
	 */
	int getClassSpacesCntByClass(int classId);
	/**
	 * 获取班级空间列表
	 * @param classId
	 * @return
	 */
	List<ClassSpace> getClassSpacesByClass(int classId,int pageIndex,int pageSize);
	/**
	 * 获取某条班级空间信息
	 * @param classSpaceId
	 * @return
	 */
	ClassSpace getClassSpaceById(int classSpaceId);
	/**
	 * 屏蔽某学生某班级空间信息
	 * @param record
	 * @return
	 */
	int setOffClassSpaceById(ClassSpace record);
}