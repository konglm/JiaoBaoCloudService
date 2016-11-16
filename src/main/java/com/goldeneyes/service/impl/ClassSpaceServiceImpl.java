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
import com.goldeneyes.IDao.ClassSpaceMapper;
import com.goldeneyes.IDao.SpaceContentStatusMapper;
import com.goldeneyes.pojo.ClassSpace;
import com.goldeneyes.pojo.ClassSpaceComment;
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
	public List<ClassSpace> getNoReadClassSpacesByUser(int userId, int spaceType, int pageIndex, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		List<ClassSpace> classSpaces = classSpaceMapper.getNoReadClassSpacesByUser(userId, spaceType, pageIndex,
				pageSize);
		return classSpaces;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getNoReadClassSpacesCntByUserForClass(int userId, int spaceType, int classId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = classSpaceMapper.getNoReadClassSpacesCntByUserForClass(userId, spaceType, classId);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<ClassSpace> getNoReadClassSpacesByUserForClass(int userId, int spaceType, int pageIndex, int pageSize,
			int classId) throws Exception {
		// TODO Auto-generated method stub
		List<ClassSpace> classSpaces = classSpaceMapper.getNoReadClassSpacesByUserForClass(userId, spaceType, pageIndex,
				pageSize, classId);
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
	public int getClassSpaceCommentsCntById(int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = classSpaceCommentMapper.getClassSpaceCommentsCntById(classSpaceId);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<ClassSpaceComment> getClassSpaceCommentsById(int classSpaceId, int pageIndex, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		List<ClassSpaceComment> classSpaceComments = classSpaceCommentMapper.getClassSpaceCommentsById(classSpaceId,
				pageIndex, pageSize);
		return classSpaceComments;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<Integer> getIsLikeUsersById(int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> isLikers = classSpaceMapper.getIsLikeUsersById(classSpaceId);
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
	public List<ClassSpaceComment> getClassSpaceCommentReplysByUser(int userId, int pageIndex, int pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		List<ClassSpaceComment> classSpaceComments = classSpaceCommentMapper.getClassSpaceCommentReplysByUser(userId,
				pageIndex, pageSize);
		return classSpaceComments;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getClassSpacesCntByClass(int classId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = classSpaceMapper.getClassSpacesCntByClass(classId);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<ClassSpace> getClassSpacesByClass(int classId, int pageIndex, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<ClassSpace> classSpaces = classSpaceMapper.getClassSpacesByClass(classId, pageIndex, pageSize);
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
	public int addClassSpace(int classId, String msgContent, int teacherId, int encType, String encAddr, String encImg)
			throws Exception {
		// TODO Auto-generated method stub
		int maxId = classSpaceMapper.getMaxId();
		ClassSpace classSpace = new ClassSpace();
		classSpace.setTabid(maxId);
		classSpace.setClassid(classId);
		classSpace.setMsgcontent(msgContent);
		classSpace.setPublisherid(teacherId);
		classSpace.setPublishdate(new Date());
		classSpace.setStatus(CommonTool.int2byte(1));
		classSpace.setEnctype(CommonTool.int2byte(encType));
		classSpace.setEncaddr(encAddr);
		classSpace.setEncimgaddr(encImg);
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
	public int addClassSpaceComment(int userId, int classSpaceId, String commentContent) throws Exception {
		// TODO Auto-generated method stub
		int maxId = classSpaceCommentMapper.getMaxId();
		ClassSpaceComment classSpaceComment = new ClassSpaceComment();
		classSpaceComment.setTabid(maxId);
		classSpaceComment.setUserid(userId);
		classSpaceComment.setClassspaceid(classSpaceId);
		classSpaceComment.setCommentcontent(commentContent);
		classSpaceComment.setCommentdate(new Date());
		classSpaceComment.setReplyid(0);
		classSpaceComment.setReplystatus(CommonTool.int2byte(0));
		classSpaceComment.setStatus(CommonTool.int2byte(1));
		classSpaceComment.setUpperid(0);
		classSpaceComment.setCommenttype(CommonTool.int2byte(2));
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
	public int addClassSpaceCommentReply(int userId, int replyUserId, int classSpaceId, String commentContent,
			int upperId) throws Exception {
		// TODO Auto-generated method stub
		int maxId = classSpaceCommentMapper.getMaxId();
		ClassSpaceComment classSpaceComment = new ClassSpaceComment();
		classSpaceComment.setTabid(maxId);
		classSpaceComment.setUserid(userId);
		classSpaceComment.setClassspaceid(classSpaceId);
		classSpaceComment.setCommentcontent(commentContent);
		classSpaceComment.setCommentdate(new Date());
		classSpaceComment.setReplyid(replyUserId);
		classSpaceComment.setReplystatus(CommonTool.int2byte(0));
		classSpaceComment.setStatus(CommonTool.int2byte(1));
		classSpaceComment.setUpperid(upperId);
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
	public int addClassSpaceForUser(int userId, int spaceType, int classSpaceId) throws Exception {
		// TODO Auto-generated method stub

		SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
		spaceContentStatus.setUserid(userId);
		spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
		spaceContentStatus.setSpaceid(classSpaceId);
		spaceContentStatus.setIsread(CommonTool.int2byte(0));

		try {
			spaceContentStatusMapper.insert(spaceContentStatus);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addClassSpaceForMutiUsers(List<Integer> userIds, int spaceType, int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		for (Integer userId : userIds) {
			SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
			spaceContentStatus.setUserid(userId);
			spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
			spaceContentStatus.setSpaceid(classSpaceId);
			spaceContentStatus.setIsread(CommonTool.int2byte(0));

			try {
				spaceContentStatusMapper.insert(spaceContentStatus);
			} catch (Exception e) {
				return 0;
			}
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int setClassSpaceReadByUser(int userId, int spaceType, List<Integer> classSpaceIds) throws Exception {
		// TODO Auto-generated method stub
		for (Integer classSpaceId : classSpaceIds) {
			SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
			spaceContentStatus.setUserid(userId);
			spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
			spaceContentStatus.setSpaceid(classSpaceId);
			try {
				spaceContentStatusMapper.setClassSpaceReadByUser(spaceContentStatus);
			} catch (Exception e) {
				return 0;
			}
		}

		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int setClassSpaceLikeByUser(int userId, int spaceType, int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		ClassSpaceComment classSpaceComment = new ClassSpaceComment();
		classSpaceComment.setUserid(userId);
		classSpaceComment.setClassspaceid(classSpaceId);
		classSpaceComment.setCommentcontent("");
		classSpaceComment.setCommentdate(new Date());
		classSpaceComment.setReplyid(0);
		classSpaceComment.setReplystatus(CommonTool.int2byte(0));
		classSpaceComment.setStatus(CommonTool.int2byte(1));
		classSpaceComment.setUpperid(0);
		classSpaceComment.setCommenttype(CommonTool.int2byte(1));
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
	public int setClassSpaceCommentReplyById(int classSpaceCommentId) throws Exception {
		// TODO Auto-generated method stub
		ClassSpaceComment classSpaceComment = new ClassSpaceComment();
		classSpaceComment.setTabid(classSpaceCommentId);
		try {
			classSpaceCommentMapper.setClassSpaceCommentReplyById(classSpaceComment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int setOffClassSpaceById(int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		ClassSpace classSpace = new ClassSpace();
		classSpace.setTabid(classSpaceId);
		try {
			classSpaceMapper.setOffClassSpaceById(classSpace);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int delClassSpaceById(int classSpaceId) throws Exception {
		// TODO Auto-generated method stub
		try {
			classSpaceMapper.deleteByPrimaryKey(classSpaceId);
			classSpaceCommentMapper.deleteByClassSpaceId(classSpaceId);
			spaceContentStatusMapper.deleteByClassSpaceId(classSpaceId);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int delClassSpaceCommentById(int classSpaceCommentId) throws Exception {
		// TODO Auto-generated method stub
		try {
			classSpaceCommentMapper.deleteByPrimaryKey(classSpaceCommentId);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getReadCntBySpaceId(int spaceId) {
		// TODO Auto-generated method stub
		int cnt = spaceContentStatusMapper.getReadCntBySpaceId(2, spaceId);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getLikeCntBySpaceId(int classSpaceId) {
		// TODO Auto-generated method stub
		int cnt = classSpaceCommentMapper.getLikeCntBySpaceId(classSpaceId);
		return cnt;
	}
}
