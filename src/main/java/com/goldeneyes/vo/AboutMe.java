/*----------------------------------------------------------------
 *  Copyright (C) 2016山东金视野教育科技股份有限公司
 * 版权所有。 
 *
 * 文件名：AboutMe
 * 文件功能描述：与我相关的VO
 *
 * 
 * 创建标识：konglm20161109
 *
 * 修改标识：
 * 修改描述：
 *----------------------------------------------------------------*/

package com.goldeneyes.vo;

import java.util.Date;

/**
 * @author konglm
 *
 */
public class AboutMe {
	private int msgType;
	private int userId;
	private Date msgDate;
	private int tabId;
	
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}
	public int getTabId() {
		return tabId;
	}
	public void setTabId(int tabId) {
		this.tabId = tabId;
	}
	
	

}
