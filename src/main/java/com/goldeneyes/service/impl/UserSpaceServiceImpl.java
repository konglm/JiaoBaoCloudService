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
import com.goldeneyes.IDao.UserSpaceMapper;
import com.goldeneyes.IDao.UserSpaceMsgMapper;
import com.goldeneyes.pojo.SpaceContentStatus;
import com.goldeneyes.pojo.UserSpace;
import com.goldeneyes.pojo.UserSpaceComment;
import com.goldeneyes.pojo.UserSpaceMsg;
import com.goldeneyes.service.UserSpaceService;
import com.goldeneyes.util.CommonTool;
import com.goldeneyes.vo.AboutMe;

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
	public List<UserSpace> getNoReadUserSpacesByUser(int userId, int spaceType,int pageIndex,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpace> userSpaces = userSpaceMapper.getNoReadUserSpacesByUser(userId, spaceType,pageIndex,pageSize);
		return userSpaces;
	}
	
	/**
	 * @author konglm
	 */
	@Override
	public int getNoReadUserSpacesCntByUserForPublisher(int userId, int spaceType,int publisherId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = userSpaceMapper.getNoReadUserSpacesCntByUserForPublisher(userId, spaceType,publisherId);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<UserSpace> getNoReadUserSpacesByUserForPublisher(int userId, int spaceType,int pageIndex,int pageSize,int publisherId) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpace> userSpaces = userSpaceMapper.getNoReadUserSpacesByUserForPublisher(userId, spaceType,pageIndex,pageSize,publisherId);
		return userSpaces;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int getIsLikeUserSpaceByUser(int userId, int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		int isLike = userSpaceMapper.getIsLikeUserSpaceByUser(userId, userSpaceId);
		return isLike;
	}
	
	/**
	 * @author konglm
	 */
	@Override
	public int getUserSpaceCommentsCntById(int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = userSpaceCommentMapper.getUserSpaceCommentsCntById(userSpaceId);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<UserSpaceComment> getUserSpaceCommentsById(int userSpaceId,int pageIndex,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpaceComment> userSpaceComments = userSpaceCommentMapper.getUserSpaceCommentsById(userSpaceId,pageIndex,pageSize);
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
	public List<UserSpaceComment> getUserSpaceCommentReplysByUser(int userId,int pageIndex,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpaceComment> userSpaceComments = userSpaceCommentMapper.getUserSpaceCommentReplysByUser(userId,pageIndex,pageSize);
		return userSpaceComments;
	}
	
	/**
	 * @author konglm
	 */
	@Override
	public int getUserSpacesCntByUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = userSpaceMapper.getUserSpacesCntByUser(userId);
		return cnt;
	}

	/**
	 * @author konglm
	 */
	@Override
	public List<UserSpace> getUserSpacesByUser(int userId,int pageIndex,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpace> userSpaces = userSpaceMapper.getUserSpacesByUser(userId,pageIndex,pageSize);
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
	public int addUserSpace(int userId, String msgContent, int noteType,int encType,String encAddr,String encImg,String encIntro) throws Exception {
		// TODO Auto-generated method stub
		UserSpace userSpace = new UserSpace();
		userSpace.setUserid(userId);
		userSpace.setMsgcontent(msgContent);
		userSpace.setPublisherid(userId);
		userSpace.setPublishdate(new Date());
		userSpace.setStatus(CommonTool.int2byte(1));
		userSpace.setNotetype(CommonTool.int2byte(noteType));
		userSpace.setEnctype(CommonTool.int2byte(encType));
		userSpace.setEncaddr(encAddr);
		userSpace.setEncimgaddr(encImg);
		userSpace.setEncintro(encIntro);
		try {
			userSpaceMapper.insert(userSpace);		
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int addUserSpaceComment(int userId, int userSpaceId, String commentContent) throws Exception {
		// TODO Auto-generated method stub
		UserSpaceComment userSpaceComment = new UserSpaceComment();
		userSpaceComment.setUserid(userId);
		userSpaceComment.setUserspaceid(userSpaceId);
		userSpaceComment.setCommentcontent(commentContent);
		userSpaceComment.setCommentdate(new Date());
		userSpaceComment.setReplyid(0);
		userSpaceComment.setReplystatus(CommonTool.int2byte(0));
		userSpaceComment.setStatus(CommonTool.int2byte(1));
		userSpaceComment.setUpperid(0);
		userSpaceComment.setCommenttype(CommonTool.int2byte(2));
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
	public int addUserSpaceCommentReply(int userId, int replyUserId, int userSpaceId, String commentContent,int upperId) throws Exception {
		// TODO Auto-generated method stub
		UserSpaceComment userSpaceComment = new UserSpaceComment();
		userSpaceComment.setUserid(userId);
		userSpaceComment.setUserspaceid(userSpaceId);
		userSpaceComment.setCommentcontent(commentContent);
		userSpaceComment.setCommentdate(new Date());
		userSpaceComment.setReplyid(replyUserId);
		userSpaceComment.setReplystatus(CommonTool.int2byte(0));
		userSpaceComment.setStatus(CommonTool.int2byte(1));
		userSpaceComment.setUpperid(upperId);
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
	public int addUserSpaceForUser(int userId, int spaceType, int userSpaceId) throws Exception {
		// TODO Auto-generated method stub

		SpaceContentStatus spaceContentStatus = new SpaceContentStatus();
		spaceContentStatus.setUserid(userId);
		spaceContentStatus.setSpacetype(Byte.valueOf(CommonTool.int2byte(spaceType)));
		spaceContentStatus.setSpaceid(userSpaceId);
		spaceContentStatus.setIsread(CommonTool.int2byte(0));

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
		UserSpaceComment userSpaceComment = new UserSpaceComment();
		userSpaceComment.setUserid(userId);
		userSpaceComment.setUserspaceid(userSpaceId);
		userSpaceComment.setCommentcontent("");
		userSpaceComment.setCommentdate(new Date());
		userSpaceComment.setReplyid(0);
		userSpaceComment.setReplystatus(CommonTool.int2byte(0));
		userSpaceComment.setStatus(CommonTool.int2byte(1));
		userSpaceComment.setUpperid(0);
		userSpaceComment.setCommenttype(CommonTool.int2byte(1));
		try{
			userSpaceCommentMapper.insert(userSpaceComment);
		}catch(Exception e){
			return 0;
		}
		
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int setUserSpaceCommentReplyById(int userSpaceCommentId) throws Exception {
		// TODO Auto-generated method stub
		UserSpaceComment userSpaceComment = new UserSpaceComment();
		userSpaceComment.setTabid(userSpaceCommentId);
		try {
			userSpaceCommentMapper.setUserSpaceCommentReplyById(userSpaceComment);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * @author konglm
	 */
	@Override
	public int setOffUserSpaceById(int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		UserSpace userSpace = new UserSpace();
		userSpace.setTabid(userSpaceId);
		try {
			userSpaceMapper.setOffUserSpaceById(userSpace);
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
			userSpaceCommentMapper.deleteByUserSpaceId(userSpaceId);
			userSpaceMsgMapper.deleteByUserSpaceId(userSpaceId);
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
	public int getUserSpaceMsgsCntById(int userSpaceId) throws Exception {
		// TODO Auto-generated method stub
		int cnt = userSpaceMsgMapper.getUserSpaceMsgsCntById(userSpaceId);
		return cnt;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public List<UserSpaceMsg> getUserSpaceMsgsById(int userSpaceId,int pageIndex,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpaceMsg> userSpaceMsgs = userSpaceMsgMapper.getUserSpaceMsgsById(userSpaceId,pageIndex,pageSize);
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
	public List<UserSpaceMsg> getUserSpaceMsgReplysByUser(int userId,int pageIndex,int pageSize) throws Exception {
		// TODO Auto-generated method stub
		List<UserSpaceMsg> userSpaceMsgs = userSpaceMsgMapper.getUserSpaceMsgReplysByUser(userId,pageIndex,pageSize);
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
		userSpaceMsg.setUpperid(0);
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
	public int addUserSpaceMsgReply(int userId, int replyUserId, int userSpaceId, String msgStr,int upperId) throws Exception {
		// TODO Auto-generated method stub
		UserSpaceMsg userSpaceMsg = new UserSpaceMsg();
		userSpaceMsg.setUserid(userId);
		userSpaceMsg.setUserspaceid(userSpaceId);
		userSpaceMsg.setMsgcontent(msgStr);
		userSpaceMsg.setMsgdate(new Date());
		userSpaceMsg.setReplyid(replyUserId);
		userSpaceMsg.setReplystatus(CommonTool.int2byte(0));
		userSpaceMsg.setStatus(CommonTool.int2byte(1));
		userSpaceMsg.setUpperid(upperId);
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
	
	/**
	 *  @author konglm
	 */
	@Override
	public int getAboutMeCnt(int userId) {
		// TODO Auto-generated method stub
		int cnt = userSpaceMapper.getAboutMeCnt(userId);
		return cnt;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public List<AboutMe> getAboutMe(int userId,int pageIndex,int pageSize) {
		// TODO Auto-generated method stub
		List<AboutMe> aboutMes = userSpaceMapper.getAboutMe(userId,pageIndex,pageSize);
		return aboutMes;
	}

	/**
	 *  @author konglm
	 */
	@Override
	public int getReadCntBySpaceId(int spaceId) {
		// TODO Auto-generated method stub
		int cnt = spaceContentStatusMapper.getReadCntBySpaceId(3,spaceId);
		return cnt;
	}
}
