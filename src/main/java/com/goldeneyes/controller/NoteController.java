/*----------------------------------------------------------------
 *  Copyright (C) 2016山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：NoteController
 * 文件功能描述：点到记事控制层
 *
 * 
 * 创建标识：konglm20161026
 *
 * 修改标识：
 * 修改描述：
 *----------------------------------------------------------------*/

package com.goldeneyes.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldeneyes.pojo.Note;
import com.goldeneyes.service.NoteService;
import com.goldeneyes.util.CommonTool;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

/**
 * @author konglm
 *
 */
@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource
	NoteService noteService;
	
	@RequestMapping("/getAllNotes.do")
	public void getAllNote(HttpServletRequest request,HttpServletResponse response,Model model){
		List<Note> notes = noteService.getAllNote();
		JSONArray jsonArray = new JSONArray();
		for(Note note: notes){
			JSONObject jsonobj=new JSONObject();
			jsonobj.put("StudentId",note.getStudentid());
			jsonobj.put("MsgContent",note.getMsgcontent());
			jsonobj.put("PublisherId",note.getPublisherid());
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jsonobj.put("PublishDate",formater.format(note.getPublishdate()));
			jsonArray.put(jsonobj);
		}
        //在这里输出，手机端就拿到web返回的值了
        CommonTool.outJsonString(response,jsonArray.toString());
	}

}
