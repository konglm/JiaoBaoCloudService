package com.goldeneyes.IDao;

import java.util.List;

import com.goldeneyes.pojo.UserSpaceMsg;

public interface UserSpaceMsgMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(UserSpaceMsg record);

    int insertSelective(UserSpaceMsg record);

    UserSpaceMsg selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(UserSpaceMsg record);

    int updateByPrimaryKey(UserSpaceMsg record);
    /**
   	 * 获取用户空间所有留言条数
   	 * @param userSpaceId
   	 * @return
   	 */
    int getUserSpaceMsgsCntById(int userSpaceId);
    /**
   	 * 获取用户空间所有留言
   	 * @param userSpaceId
   	 * @return
   	 */
   	List<UserSpaceMsg> getUserSpaceMsgsById(int userSpaceId,int pageIndex,int pageSize);
   	/**
   	 * 获取用户用户空间所有未读留言回复条数
   	 * @param userId
   	 * @return
   	 */
   	int getUserSpaceMsgReplysCntByUser(int userId);
   	/**
   	 * 获取用户用户空间所有未读留言回复列表
   	 * @param userId
   	 * @return
   	 */
   	List<UserSpaceMsg> getUserSpaceMsgReplysByUser(int userId,int pageIndex,int pageSize);
   	/**
   	 * 修改某用户某用户空间留言回复查看状态
   	 * @param record
   	 * @return
   	 */
   	int setUserSpaceMsgReplyById(UserSpaceMsg record);
   	/**
   	 * 删除某用户空间的留言
   	 * @param userSpaceId
   	 * @return
   	 */
   	int deleteByUserSpaceId(int userSpaceId);
}