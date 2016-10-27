package com.goldeneyes.IDao;

import com.goldeneyes.pojo.ClassSpaceComment;

public interface ClassSpaceCommentMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(ClassSpaceComment record);

    int insertSelective(ClassSpaceComment record);

    ClassSpaceComment selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(ClassSpaceComment record);

    int updateByPrimaryKey(ClassSpaceComment record);
}