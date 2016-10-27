package com.goldeneyes.IDao;

import com.goldeneyes.pojo.UserSpace;

public interface UserSpaceMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(UserSpace record);

    int insertSelective(UserSpace record);

    UserSpace selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(UserSpace record);

    int updateByPrimaryKey(UserSpace record);
}