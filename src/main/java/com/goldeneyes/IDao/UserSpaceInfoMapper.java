package com.goldeneyes.IDao;

import com.goldeneyes.pojo.UserSpaceInfo;

public interface UserSpaceInfoMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(UserSpaceInfo record);

    int insertSelective(UserSpaceInfo record);

    UserSpaceInfo selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(UserSpaceInfo record);

    int updateByPrimaryKey(UserSpaceInfo record);
}