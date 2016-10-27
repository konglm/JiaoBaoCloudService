package com.goldeneyes.IDao;

import com.goldeneyes.pojo.UserSpaceComment;

public interface UserSpaceCommentMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(UserSpaceComment record);

    int insertSelective(UserSpaceComment record);

    UserSpaceComment selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(UserSpaceComment record);

    int updateByPrimaryKey(UserSpaceComment record);
}