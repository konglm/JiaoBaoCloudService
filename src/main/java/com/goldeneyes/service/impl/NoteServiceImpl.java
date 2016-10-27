/*----------------------------------------------------------------
 *  Copyright (C) 2016山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：NoteServiceImpl
 * 文件功能描述：点到记事服务层实现
 *
 * 
 * 创建标识：konglm20161026
 *
 * 修改标识：
 * 修改描述：
 *----------------------------------------------------------------*/

package com.goldeneyes.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.goldeneyes.IDao.NoteMapper;
import com.goldeneyes.pojo.Note;
import com.goldeneyes.service.NoteService;

/**
 * @author konglm
 *
 */
@Service("noteService")
public class NoteServiceImpl implements NoteService {
	@Resource
	NoteMapper noteMapper;
	/**
	 *  @author konglm
	 */
	@Override
	public List<Note> getAllNote() {
		// TODO Auto-generated method stub
		List<Note> notes = noteMapper.getAllNote();
		return notes;
	}

}
