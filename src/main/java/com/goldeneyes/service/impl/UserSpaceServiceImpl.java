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

import com.goldeneyes.IDao.SpaceContentStatusMapper;
import com.goldeneyes.IDao.UserSpaceCommentMapper;
import com.goldeneyes.IDao.UserSpaceEncMapper;
import com.goldeneyes.IDao.UserSpaceMapper;
import com.goldeneyes.IDao.UserSpaceMsgMapper;
import com.goldeneyes.pojo.SpaceContentStatus;
import com.goldeneyes.pojo.UserSpace;
import com.goldeneyes.pojo.UserSpaceComment;
import com.goldeneyes.pojo.UserSpaceEnc;
import com.goldeneyes.pojo.UserSpaceMsg;
import com.goldeneyes.service.UserSpaceService;
import com.goldeneyes.util.CommonTool;

/**
 * @author konglm
 *
 */
@Service("userSpaceService")
public class UserSpaceServiceImpl implements UserSpaceService {
	@Resource
	UserSpaceMapper userSpaceMapper;
	@Resource
	UserSpaceCommentMapper userSpaceCommentMapper;
	@Resource
	UserSpaceEncMapper userSpaceEncMapper;
	@Resource
	SpaceContentStatusMapper spaceContentStatusMapper;
	@Resource
	UserSpaceMsgMapper userSpaceMsgMapper;

	/**
	 * @author konglm
	 */
	@Override
	public List<UserSpace> getAllUserSpace() throws Exception {
		// TODO Auto-generated method stub
		List<UserSpace> userSpaces = userSpaceMapper.getAllUserSpace();
		return userSpaces;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getNoReadUserSpacesCntByUser(int userId, int spaceType) throws Exception {
		// TODO Auto-generated method stub
		int cnt = userSpaceMapper.getNoReadUserSpacesCntByUser(userId, spaceType);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<UserSpace> getNoReadUserSpacesByUser(int userId, int spaceType) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpace> userSpaces = userSpaceMapper.getNoReadUserSpacesByUser(userId, spaceType);
		return userSpaces;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getIsLikeUserSpaceByUser(int userId, int spaceType, int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		int isLike = userSpaceMapper.getIsLikeUserSpaceByUser(userId, spaceType, userSpaceId);
		return isLike;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<UserSpaceComment> getUserSpaceCommentsById(int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpaceComment> userSpaceComments = userSpaceCommentMapper.getUserSpaceCommentsById(userSpaceId);
		return userSpaceComments;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<Integer> getIsLikeUsersById(int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> isLikers = userSpaceMapper.getIsLikeUsersById(userSpaceId);
		return isLikers;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getUserSpaceCommentReplysCntByUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = userSpaceCommentMapper.getUserSpaceCommentReplysCntByUser(userId);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<UserSpaceComment> getUserSpaceCommentReplysByUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpaceComment> userSpaceComments = userSpaceCommentMapper.getUserSpaceCommentReplysByUser(userId);
		return userSpaceComments;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<UserSpace> getUserSpacesByUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpace> userSpaces = userSpaceMapper.getUserSpacesByUser(userId);
		return userSpaces;
	}

	/**
	 * @author konglm
	 */
	@Override
	public UserSpace getUserSpaceById(int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		UserSpace userSpace = userSpaceMapper.getUserSpaceById(userSpaceId);
		return userSpace;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<UserSpaceEnc> getUserSpaceEncById(int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpaceEnc> userSpaceEncs = userSpaceEncMapper.getUserSpaceEncById(userSpaceId);
		return userSpaceEncs;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addUserSpace(int userId, String userSpaceStr, int teacherId) throws Exception {
		// TODO Auto-generated method stub
		int maxId = userSpaceMapper.getMaxId(); // 为了返回ID，手工插入ID值
		UserSpace userSpace = new UserSpace();
		userSpace.setTabid(maxId);
		userSpace.setUserid(userId);
		userSpace.setMsgcontent(userSpaceStr);
		userSpace.setPublisherid(teacherId);
		userSpace.setPublishdate(new Date());
		userSpace.setStatus(CommonTool.int2byte(1));
		try {
			userSpaceMapper.insert(userSpace);		
		} catch (Exception e) {
			return 0;
		}
		return maxId;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addUserSpaceEnc(int userSpaceId, String encType, String encAddr, String encImg, int teacherId, int encOrder) throws Exception {
		// TODO Auto-generated method stub
		UserSpaceEnc userSpaceEnc = new UserSpaceEnc();
		userSpaceEnc.setUserspaceid(userSpaceId);
		userSpaceEnc.setEnctype(encType);
		userSpaceEnc.setEncaddr(encAddr);
		userSpaceEnc.setEncimgaddr(encImg);
		userSpaceEnc.setPublisherid(teacherId);
		userSpaceEnc.setPublishdate(new Date());
		userSpaceEnc.setEncorder(encOrder);
		try {
			userSpaceEncMapper.insert(userSpaceEnc);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addUserSpaceComment(int userId, int userSpaceId, String commentStr) throws Exception {
		// TODO Auto-generated method stub
		UserSpaceComment userSpaceComment = new UserSpaceComment();
		userSpaceComment.setUserid(userId);
		userSpaceComment.setUserspaceid(userSpaceId);
		userSpaceComment.setCommentcontent(commentStr);
		userSpaceComment.setCommentdate(new Date());
		userSpaceComment.setReplyid(0);
		userSpaceComment.setReplystatus(CommonTool.int2byte(0));
		userSpaceComment.setStatus(CommonTool.int2byte(1));
		try {
			userSpaceCommentMapper.insert(userSpaceComment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addUserSpaceCommentReply(int userId, int replyUserId, int userSpaceId, String commentStr) throws Exception {
		// TODO Auto-generated method stub
		UserSpaceComment userSpaceComment = new UserSpaceComment();
		userSpaceComment.setUserid(userId);
		userSpaceComment.setUserspaceid(userSpaceId);
		userSpaceComment.setCommentcontent(commentStr);
		userSpaceComment.setCommentdate(new Date());
		userSpaceComment.setReplyid(replyUserId);
		userSpaceComment.setReplystatus(CommonTool.int2byte(0));
		userSpaceComment.setStatus(CommonTool.int2byte(1));
		try {
			userSpaceCommentMapper.insert(userSpaceComment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int sendUserSpaceForUser(int userId, int spaceType, int userSpaceId) throws Exception {
		// TODO Auto-generated method stub

		SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
		spaceContentStatus.setUserid(userId);
		spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
		spaceContentStatus.setSpaceid(userSpaceId);
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
	public int setUserSpaceReadByUser(int userId, int spaceType, int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
		spaceContentStatus.setUserid(userId);
		spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
		spaceContentStatus.setSpaceid(userSpaceId);
		try{
			spaceContentStatusMapper.setUserSpaceReadByUser(spaceContentStatus);
		}catch(Exception e){
			return 0;
		}
		
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int setUserSpaceLikeByUser(int userId, int spaceType, int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
		spaceContentStatus.setUserid(userId);
		spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
		spaceContentStatus.setSpaceid(userSpaceId);
		try{
			spaceContentStatusMapper.setUserSpaceLikeByUser(spaceContentStatus);
		}catch(Exception e){
			return 0;
		}
		
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int setUserSpaceCommentReplyByUser(int userSpaceCommentId) throws Exception {
		// TODO Auto-generated method stub
		UserSpaceComment userSpaceComment = new UserSpaceComment();
		userSpaceComment.setTabid(userSpaceCommentId);
		try {
			userSpaceCommentMapper.setUserSpaceCommentReplyByUser(userSpaceComment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int offUserSpaceById(int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		UserSpace userSpace = new UserSpace();
		userSpace.setTabid(userSpaceId);
		try {
			userSpaceMapper.offUserSpaceById(userSpace);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int delUserSpaceById(int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		try{
			userSpaceMapper.deleteByPrimaryKey(userSpaceId);
			userSpaceEncMapper.deleteByUserSpaceId(userSpaceId);
			userSpaceCommentMapper.deleteByUserSpaceId(userSpaceId);
			spaceContentStatusMapper.deleteByUserSpaceId(userSpaceId);
		} catch(Exception e){
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int delUserSpaceCommentById(int userSpaceCommentId) throws Exception {
		// TODO Auto-generated method stub
		try{
			userSpaceCommentMapper.deleteByPrimaryKey(userSpaceCommentId);
		} catch(Exception e){
			return 0;
		}
		return 1;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public List<UserSpaceMsg> getUserSpaceMsgsById(int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpaceMsg> userSpaceMsgs = userSpaceMsgMapper.getUserSpaceMsgsById(userSpaceId);
		return userSpaceMsgs;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public int getUserSpaceMsgReplysCntByUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = userSpaceMsgMapper.getUserSpaceMsgReplysCntByUser(userId);
		return cnt;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public List<UserSpaceMsg> getUserSpaceMsgReplysByUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpaceMsg> userSpaceMsgs = userSpaceMsgMapper.getUserSpaceMsgReplysByUser(userId);
		return userSpaceMsgs;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public int addUserSpaceMsg(int userId, int userSpaceId, String msgStr) throws Exception {
		// TODO Auto-generated method stub
		UserSpaceMsg userSpaceMsg = new UserSpaceMsg();
		userSpaceMsg.setUserid(userId);
		userSpaceMsg.setUserspaceid(userSpaceId);
		userSpaceMsg.setMsgcontent(msgStr);
		userSpaceMsg.setMsgdate(new Date());
		userSpaceMsg.setReplyid(0);
		userSpaceMsg.setReplystatus(CommonTool.int2byte(0));
		userSpaceMsg.setStatus(CommonTool.int2byte(1));
		try {
			userSpaceMsgMapper.insert(userSpaceMsg);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public int addUserSpaceMsgReply(int userId, int replyUserId, int userSpaceId, String msgStr) throws Exception {
		// TODO Auto-generated method stub
		UserSpaceMsg userSpaceMsg = new UserSpaceMsg();
		userSpaceMsg.setUserid(userId);
		userSpaceMsg.setUserspaceid(userSpaceId);
		userSpaceMsg.setMsgcontent(msgStr);
		userSpaceMsg.setMsgdate(new Date());
		userSpaceMsg.setReplyid(replyUserId);
		userSpaceMsg.setReplystatus(CommonTool.int2byte(0));
		userSpaceMsg.setStatus(CommonTool.int2byte(1));
		try {
			userSpaceMsgMapper.insert(userSpaceMsg);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public int setUserSpaceMsgReplyById(int userSpaceMsgId) throws Exception {
		// TODO Auto-generated method stub
		UserSpaceMsg userSpaceMsg = new UserSpaceMsg();
		userSpaceMsg.setTabid(userSpaceMsgId);
		try {
			userSpaceMsgMapper.setUserSpaceMsgReplyById(userSpaceMsg);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public int delUserSpaceMsgById(int userSpaceMsgId) throws Exception {
		// TODO Auto-generated method stub
		try{
			userSpaceMsgMapper.deleteByPrimaryKey(userSpaceMsgId);
		} catch(Exception e){
			return 0;
		}
		return 1;
	}
}