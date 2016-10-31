package com.goldeneyes.IDao;

import java.util.List;

import com.goldeneyes.pojo.UserSpaceEnc;

public interface UserSpaceEncMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(UserSpaceEnc record);

    int insertSelective(UserSpaceEnc record);

    UserSpaceEnc selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(UserSpaceEnc record);

    int updateByPrimaryKey(UserSpaceEnc record);
    /**
	 * 获取某条用户空间附件列表
	 * @param userSpaceId
	 * @return
	 */
	List<UserSpaceEnc> getUserSpaceEncById(int userSpaceId);
	/**
	 * 删除某用户空间的附件
	 * @param userSpaceId
	 * @return
	 */
	int deleteByUserSpaceId(int userSpaceId);
}