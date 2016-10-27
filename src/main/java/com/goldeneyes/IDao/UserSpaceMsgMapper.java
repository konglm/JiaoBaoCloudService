package com.goldeneyes.IDao;

import com.goldeneyes.pojo.UserSpaceMsg;

public interface UserSpaceMsgMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(UserSpaceMsg record);

    int insertSelective(UserSpaceMsg record);

    UserSpaceMsg selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(UserSpaceMsg record);

    int updateByPrimaryKey(UserSpaceMsg record);
}