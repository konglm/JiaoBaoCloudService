package com.goldeneyes.IDao;

import com.goldeneyes.pojo.ClassSpaceInfo;

public interface ClassSpaceInfoMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(ClassSpaceInfo record);

    int insertSelective(ClassSpaceInfo record);

    ClassSpaceInfo selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(ClassSpaceInfo record);

    int updateByPrimaryKey(ClassSpaceInfo record);
}