package com.goldeneyes.IDao;

import com.goldeneyes.pojo.UserMsgInfo;

public interface UserMsgInfoMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(UserMsgInfo record);

    int insertSelective(UserMsgInfo record);

    UserMsgInfo selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(UserMsgInfo record);

    int updateByPrimaryKey(UserMsgInfo record);
}