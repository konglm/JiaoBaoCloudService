package com.goldeneyes.pojo;

import java.util.Date;

public class UserSpaceComment {
    private Integer tabid;

    private Integer userspaceid;

    private Integer userid;

    private String commentcontent;

    private Integer replyid;

    private Date commentdate;

    private Byte replystatus;

    private Date replydate;

    public Integer getTabid() {
        return tabid;
    }

    public void setTabid(Integer tabid) {
        this.tabid = tabid;
    }

    public Integer getUserspaceid() {
        return userspaceid;
    }

    public void setUserspaceid(Integer userspaceid) {
        this.userspaceid = userspaceid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent == null ? null : commentcontent.trim();
    }

    public Integer getReplyid() {
        return replyid;
    }

    public void setReplyid(Integer replyid) {
        this.replyid = replyid;
    }

    public Date getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Date commentdate) {
        this.commentdate = commentdate;
    }

    public Byte getReplystatus() {
        return replystatus;
    }

    public void setReplystatus(Byte replystatus) {
        this.replystatus = replystatus;
    }

    public Date getReplydate() {
        return replydate;
    }

    public void setReplydate(Date replydate) {
        this.replydate = replydate;
    }
}