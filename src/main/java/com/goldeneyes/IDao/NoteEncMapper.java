package com.goldeneyes.IDao;

import com.goldeneyes.pojo.NoteEnc;

public interface NoteEncMapper {
    int deleteByPrimaryKey(Integer tabid);

    int insert(NoteEnc record);

    int insertSelective(NoteEnc record);

    NoteEnc selectByPrimaryKey(Integer tabid);

    int updateByPrimaryKeySelective(NoteEnc record);

    int updateByPrimaryKey(NoteEnc record);
    /**
	 * 获取某条点到记事附件列表
	 * @param noteId
	 * @return
	 */
	NoteEnc getNoteEncById(int noteId);
}