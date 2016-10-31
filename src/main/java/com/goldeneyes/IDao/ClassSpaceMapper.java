package com.goldeneyes.IDao;

import java.util.List;

import com.goldeneyes.pojo.ClassSpace;
import com.goldeneyes.pojo.Note;

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
	List<ClassSpace> getNoReadClassSpacesByUser(int userId,int spaceType);
	/**
	 * 获取用户某条班级空间是否点赞
	 * @param userId
	 * @param spaceType
	 * @param ClassSpaceId
	 * @return
	 */
	int getIsLikeClassSpaceByUser(int userId,int spaceType,int ClassSpaceId);
	/**
	 * 获取班级空间所有点赞用户
	 * @param ClassSpaceId
	 * @return
	 */
	List<Integer> getIsLikeUsersByClassSpace(int ClassSpaceId);
	/**
	 * 获取某学生班级空间列表
	 * @param studentId
	 * @return
	 */
	List<ClassSpace> getClassSpacesByClass(int studentId);
	/**
	 * 获取某条班级空间信息
	 * @param ClassSpaceId
	 * @return
	 */
	ClassSpace getClassSpaceById(int ClassSpaceId);
	/**
	 * 屏蔽某学生某班级空间信息
	 * @param record
	 * @return
	 */
	int offClassSpaceByClass(ClassSpace record);
}