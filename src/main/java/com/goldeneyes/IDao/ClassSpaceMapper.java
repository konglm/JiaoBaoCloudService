package com.goldeneyes.IDao;

import com.goldeneyes.pojo.ClassSpace;

public interface ClassSpaceMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(ClassSpace record);

    int insertSelective(ClassSpace record);

    ClassSpace selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(ClassSpace record);

    int updateByPrimaryKey(ClassSpace record);
}