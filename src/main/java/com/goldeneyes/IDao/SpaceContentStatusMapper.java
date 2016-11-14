package com.goldeneyes.IDao;

import com.goldeneyes.pojo.SpaceContentStatus;

public interface SpaceContentStatusMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(SpaceContentStatus record);

    int insertSelective(SpaceContentStatus record);

    SpaceContentStatus selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(SpaceContentStatus record);

    int updateByPrimaryKey(SpaceContentStatus record);
    /**
     * 修改某用户某点到记事阅读状态为已读
     * @param record
     * @return
     */
    int setNoteReadByUser(SpaceContentStatus record);
    /**
     * 修改某用户某点到记事点赞状态为点赞
     * @param record
     * @return
     */
    int setNoteLikeByUser(SpaceContentStatus record);
    /**
     * 删除某点到记事的阅读状态
     * @param noteId
     * @return
     */
    int deleteByNoteId(int noteId);
    /**
     * 修改某用户某班级空间阅读状态为已读
     * @param record
     * @return
     */
    int setClassSpaceReadByUser(SpaceContentStatus record);
    /**
     * 修改某用户某班级空间点赞状态为点赞
     * @param record
     * @return
     */
    int setClassSpaceLikeByUser(SpaceContentStatus record);
    /**
     * 删除某班级空间的阅读状态
     * @param ClassSpaceId
     * @return
     */
    int deleteByClassSpaceId(int ClassSpaceId);
    /**
     * 修改某用户某用户空间阅读状态为已读
     * @param record
     * @return
     */
    int setUserSpaceReadByUser(SpaceContentStatus record);
    /**
     * 删除某用户空间的阅读状态
     * @param userSpaceId
     * @return
     */
    int deleteByUserSpaceId(int userSpaceId);
    /**
     * 获取某空间的已读数量
     * @param spaceType
     * @param spaceId
     * @return
     */
    int getReadCntBySpaceId(int spaceType,int spaceId);
}