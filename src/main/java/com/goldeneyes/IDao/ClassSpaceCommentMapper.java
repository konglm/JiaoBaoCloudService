package com.goldeneyes.IDao;

import java.util.List;

import com.goldeneyes.pojo.ClassSpaceComment;

public interface ClassSpaceCommentMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(ClassSpaceComment record);

    int insertSelective(ClassSpaceComment record);

    ClassSpaceComment selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(ClassSpaceComment record);

    int updateByPrimaryKey(ClassSpaceComment record);
    /**
     * 获取最大ID
     * @return
     */
    int getMaxId();
    /**
	 * 获取班级空间所有评论条数
	 * @param classSpaceId
	 * @return
	 */
    int getClassSpaceCommentsCntById(int classSpaceId);
    /**
	 * 获取班级空间所有评论
	 * @param classSpaceId
	 * @return
	 */
	List<ClassSpaceComment> getClassSpaceCommentsById(int classSpaceId,int pageIndex,int pageSize);
	/**
	 * 获取用户班级空间所有未读评论回复条数
	 * @param userId
	 * @return
	 */
	int getClassSpaceCommentReplysCntByUser(int userId);
	/**
	 * 获取用户班级空间所有未读评论回复列表
	 * @param userId
	 * @return
	 */
	List<ClassSpaceComment> getClassSpaceCommentReplysByUser(int userId,int pageIndex,int pageSize);
	/**
	 * 修改某用户某班级空间评论回复查看状态
	 * @param record
	 * @return
	 */
	int setClassSpaceCommentReplyById(ClassSpaceComment record);
	/**
	 * 删除某班级空间的评论
	 * @param classSpaceId
	 * @return
	 */
	int deleteByClassSpaceId(int classSpaceId);
	/**
	 * 获取班级空间点赞数量
	 * @param classSpaceId
	 * @return
	 */
	int getLikeCntBySpaceId(int classSpaceId);
}