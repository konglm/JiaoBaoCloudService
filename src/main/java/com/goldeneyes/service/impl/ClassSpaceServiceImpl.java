/*----------------------------------------------------------------
 *  Copyright (C) 2016山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：
 * 文件功能描述：
 *
 * 
 * 创建标识：
 *
 * 修改标识：
 * 修改描述：
 *----------------------------------------------------------------*/

package com.goldeneyes.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.goldeneyes.IDao.ClassSpaceCommentMapper;
import com.goldeneyes.IDao.ClassSpaceEncMapper;
import com.goldeneyes.IDao.ClassSpaceMapper;
import com.goldeneyes.IDao.SpaceContentStatusMapper;
import com.goldeneyes.pojo.ClassSpace;
import com.goldeneyes.pojo.ClassSpaceComment;
import com.goldeneyes.pojo.ClassSpaceEnc;
import com.goldeneyes.pojo.SpaceContentStatus;
import com.goldeneyes.service.ClassSpaceService;
import com.goldeneyes.util.CommonTool;

/**
 * @author konglm
 *
 */
@Service("classSpaceService")
public class ClassSpaceServiceImpl implements ClassSpaceService {
	@Resource
	ClassSpaceMapper classSpaceMapper;
	@Resource
	ClassSpaceCommentMapper classSpaceCommentMapper;
	@Resource
	ClassSpaceEncMapper classSpaceEncMapper;
	@Resource
	SpaceContentStatusMapper spaceContentStatusMapper;

	/**
	 * @author konglm
	 */
	@Override
	public List<ClassSpace> getAllClassSpace() throws Exception {
		// TODO Auto-generated method stub
		List<ClassSpace> classSpaces = classSpaceMapper.getAllClassSpace();
		return classSpaces;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getNoReadClassSpacesCntByUser(int userId, int spaceType) throws Exception {
		// TODO Auto-generated method stub
		int cnt = classSpaceMapper.getNoReadClassSpacesCntByUser(userId, spaceType);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<ClassSpace> getNoReadClassSpacesByUser(int userId, int spaceType) throws Exception {
		// TODO Auto-generated method stub
		List<ClassSpace> classSpaces = classSpaceMapper.getNoReadClassSpacesByUser(userId, spaceType);
		return classSpaces;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getIsLikeClassSpaceByUser(int userId, int spaceType, int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		int isLike = classSpaceMapper.getIsLikeClassSpaceByUser(userId, spaceType, classSpaceId);
		return isLike;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<ClassSpaceComment> getClassSpaceCommentsByUser(int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		List<ClassSpaceComment> classSpaceComments = classSpaceCommentMapper.getClassSpaceCommentsByUser(classSpaceId);
		return classSpaceComments;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<Integer> getIsLikeUsersByClassSpace(int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> isLikers = classSpaceMapper.getIsLikeUsersByClassSpace(classSpaceId);
		return isLikers;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getClassSpaceCommentReplysCntByUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = classSpaceCommentMapper.getClassSpaceCommentReplysCntByUser(userId);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<ClassSpaceComment> getClassSpaceCommentReplysByUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		List<ClassSpaceComment> classSpaceComments = classSpaceCommentMapper.getClassSpaceCommentReplysByUser(userId);
		return classSpaceComments;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<ClassSpace> getClassSpacesByClass(int classId) throws Exception {
		// TODO Auto-generated method stub
		List<ClassSpace> classSpaces = classSpaceMapper.getClassSpacesByClass(classId);
		return classSpaces;
	}

	/**
	 * @author konglm
	 */
	@Override
	public ClassSpace getClassSpaceById(int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		ClassSpace classSpace = classSpaceMapper.getClassSpaceById(classSpaceId);
		return classSpace;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<ClassSpaceEnc> getClassSpaceEncById(int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		List<ClassSpaceEnc> classSpaceEncs = classSpaceEncMapper.getClassSpaceEncById(classSpaceId);
		return classSpaceEncs;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addClassSpace(int classId, String classSpaceStr, int teacherId) throws Exception {
		// TODO Auto-generated method stub
		int maxId = classSpaceMapper.getMaxId(); // 为了返回ID，手工插入ID值
		ClassSpace classSpace = new ClassSpace();
		classSpace.setTabid(maxId);
		classSpace.setClassid(classId);
		classSpace.setMsgcontent(classSpaceStr);
		classSpace.setPublisherid(teacherId);
		classSpace.setPublishdate(new Date());
		classSpace.setStatus(CommonTool.int2byte(1));
		try {
			classSpaceMapper.insert(classSpace);		
		} catch (Exception e) {
			return 0;
		}
		return maxId;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addClassSpaceEnc(int classSpaceId, String encType, String encAddr, String encImg, int teacherId, int encOrder) throws Exception {
		// TODO Auto-generated method stub
		ClassSpaceEnc classSpaceEnc = new ClassSpaceEnc();
		classSpaceEnc.setClassspaceid(classSpaceId);
		classSpaceEnc.setEnctype(encType);
		classSpaceEnc.setEncaddr(encAddr);
		classSpaceEnc.setEncimgaddr(encImg);
		classSpaceEnc.setPublisherid(teacherId);
		classSpaceEnc.setPublishdate(new Date());
		classSpaceEnc.setEncorder(encOrder);
		try {
			classSpaceEncMapper.insert(classSpaceEnc);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addClassSpaceComment(int userId, int classSpaceId, String commentStr) throws Exception {
		// TODO Auto-generated method stub
		ClassSpaceComment classSpaceComment = new ClassSpaceComment();
		classSpaceComment.setUserid(userId);
		classSpaceComment.setClassspaceid(classSpaceId);
		classSpaceComment.setCommentcontent(commentStr);
		classSpaceComment.setCommentdate(new Date());
		classSpaceComment.setReplyid(0);
		classSpaceComment.setReplystatus(CommonTool.int2byte(0));
		classSpaceComment.setStatus(CommonTool.int2byte(1));
		try {
			classSpaceCommentMapper.insert(classSpaceComment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addClassSpaceCommentReply(int userId, int replyUserId, int classSpaceId, String commentStr) throws Exception {
		// TODO Auto-generated method stub
		ClassSpaceComment classSpaceComment = new ClassSpaceComment();
		classSpaceComment.setUserid(userId);
		classSpaceComment.setClassspaceid(classSpaceId);
		classSpaceComment.setCommentcontent(commentStr);
		classSpaceComment.setCommentdate(new Date());
		classSpaceComment.setReplyid(replyUserId);
		classSpaceComment.setReplystatus(CommonTool.int2byte(0));
		classSpaceComment.setStatus(CommonTool.int2byte(1));
		try {
			classSpaceCommentMapper.insert(classSpaceComment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int sendClassSpaceForUser(int userId, int spaceType, int classSpaceId) throws Exception {
		// TODO Auto-generated method stub

		SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
		spaceContentStatus.setUserid(userId);
		spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
		spaceContentStatus.setSpaceid(classSpaceId);
		spaceContentStatus.setIsread(CommonTool.int2byte(0));
		spaceContentStatus.setIslike(CommonTool.int2byte(0));

		try {
			spaceContentStatusMapper.insert(spaceContentStatus);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public int setClassSpaceReadByUser(int userId, int spaceType, int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
		spaceContentStatus.setUserid(userId);
		spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
		spaceContentStatus.setSpaceid(classSpaceId);
		try{
			spaceContentStatusMapper.setClassSpaceReadByUser(spaceContentStatus);
		}catch(Exception e){
			return 0;
		}
		
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int setClassSpaceLikeByUser(int userId, int spaceType, int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
		spaceContentStatus.setUserid(userId);
		spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
		spaceContentStatus.setSpaceid(classSpaceId);
		try{
			spaceContentStatusMapper.setClassSpaceLikeByUser(spaceContentStatus);
		}catch(Exception e){
			return 0;
		}
		
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int setClassSpaceCommentReplyByUser(int classSpaceCommentId) throws Exception {
		// TODO Auto-generated method stub
		ClassSpaceComment classSpaceComment = new ClassSpaceComment();
		classSpaceComment.setTabid(classSpaceCommentId);
		try {
			classSpaceCommentMapper.setClassSpaceCommentReplyByUser(classSpaceComment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int offClassSpaceByClass(int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		ClassSpace classSpace = new ClassSpace();
		classSpace.setTabid(classSpaceId);
		try {
			classSpaceMapper.offClassSpaceByClass(classSpace);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int delClassSpaceByClass(int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		try{
			classSpaceMapper.deleteByPrimaryKey(classSpaceId);
			classSpaceEncMapper.deleteByClassSpaceId(classSpaceId);
			classSpaceCommentMapper.deleteByClassSpaceId(classSpaceId);
			spaceContentStatusMapper.deleteByClassSpaceId(classSpaceId);
		} catch(Exception e){
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int delClassSpaceCommentByClass(int classSpaceCommentId) throws Exception {
		// TODO Auto-generated method stub
		try{
			classSpaceCommentMapper.deleteByPrimaryKey(classSpaceCommentId);
		} catch(Exception e){
			return 0;
		}
		return 1;
	}
}
