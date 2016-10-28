package com.goldeneyes.IDao;

import java.util.List;

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
	List<NoteEnc> getNoteEncById(int noteId);
	/**
	 * 删除某点到记事的附件
	 * @param noteId
	 * @return
	 */
	int deleteByNoteId(int noteId);
}