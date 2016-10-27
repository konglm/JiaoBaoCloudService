package com.goldeneyes.IDao;

import com.goldeneyes.pojo.UserSpaceEnc;

public interface UserSpaceEncMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(UserSpaceEnc record);

    int insertSelective(UserSpaceEnc record);

    UserSpaceEnc selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(UserSpaceEnc record);

    int updateByPrimaryKey(UserSpaceEnc record);
}