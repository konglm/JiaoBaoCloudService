package com.goldeneyes.IDao;

import com.goldeneyes.pojo.ClassSpaceEnc;

public interface ClassSpaceEncMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(ClassSpaceEnc record);

    int insertSelective(ClassSpaceEnc record);

    ClassSpaceEnc selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(ClassSpaceEnc record);

    int updateByPrimaryKey(ClassSpaceEnc record);
}