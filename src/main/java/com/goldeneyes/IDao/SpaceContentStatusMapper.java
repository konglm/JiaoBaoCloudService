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
     * 新增某用户某点到记事点赞状态为点赞
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
}