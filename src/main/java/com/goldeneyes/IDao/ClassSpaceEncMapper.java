package com.goldeneyes.IDao;

import java.util.List;

import com.goldeneyes.pojo.ClassSpaceEnc;

public interface ClassSpaceEncMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(ClassSpaceEnc record);

    int insertSelective(ClassSpaceEnc record);

    ClassSpaceEnc selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(ClassSpaceEnc record);

    int updateByPrimaryKey(ClassSpaceEnc record);
    
    /**
   	 * 获取某条班级空间附件列表
   	 * @param classSpaceId
   	 * @return
   	 */
   	List<ClassSpaceEnc> getClassSpaceEncById(int classSpaceId);
   	/**
   	 * 删除某班级空间的附件
   	 * @param classSpaceId
   	 * @return
   	 */
   	int deleteByClassSpaceId(int classSpaceId);
}