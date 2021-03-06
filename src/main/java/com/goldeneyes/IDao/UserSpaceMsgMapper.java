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
     * 获取最大ID
     * @return
     */
    int getMaxId();
    /**
   	 * 获取用户空间所有留言条数
   	 * @param userSpaceId
   	 * @return
   	 */
    int getUserSpaceMsgsCntById(int userId);
    /**
   	 * 获取用户空间所有留言
   	 * @param userSpaceId
   	 * @return
   	 */
   	List<UserSpaceMsg> getUserSpaceMsgsById(int userId,int pageIndex,int pageSize);
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
   	 * 修改某用户空间留言查看状态
   	 * @param record
   	 * @return
   	 */
   	int setUserSpaceMsgById(UserSpaceMsg record);
	/**
   	 * 修改某用户空间留言回复查看状态
   	 * @param record
   	 * @return
   	 */
   	int setUserSpaceMsgReplyById(UserSpaceMsg record);
   	/**
   	 * 获取某回复ID回复列表
   	 * @param userSpaceMsgId
   	 * @return
   	 */
   	List<UserSpaceMsg> getMsgsById(int userMsgId);
   	/**
   	 * 通过用户ID获取留言板info的ID
   	 * @param userId
   	 * @return
   	 */
   	int getUserMsgInfoIdByUser(int userId);
}