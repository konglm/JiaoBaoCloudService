package com.goldeneyes.IDao;

import com.goldeneyes.pojo.SpaceContentStatus;

public interface SpaceContentStatusMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(SpaceContentStatus record);

    int insertSelective(SpaceContentStatus record);

    SpaceContentStatus selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(SpaceContentStatus record);

    int updateByPrimaryKey(SpaceContentStatus record);
}