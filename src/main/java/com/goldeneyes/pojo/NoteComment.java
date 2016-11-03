package com.goldeneyes.pojo;

import java.util.Date;

public class NoteComment {
    private Integer tabid;

    private Integer noteid;

    private Integer upperid;

    private Integer userid;

    private String commentcontent;

    private Integer replyid;

    private Date commentdate;

    private Byte replystatus;

    private Date replydate;

    private Byte status;

    public Integer getTabid() {
        return tabid;
    }

    public void setTabid(Integer tabid) {
        this.tabid = tabid;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public Integer getUpperid() {
        return upperid;
    }

    public void setUpperid(Integer upperid) {
        this.upperid = upperid;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}