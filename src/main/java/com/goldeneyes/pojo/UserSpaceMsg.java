package com.goldeneyes.pojo;

import java.util.Date;

public class UserSpaceMsg {
    private Integer tabid;

    private Integer userownerid;

    private Integer upperid;

    private Integer userid;

    private String msgcontent;

    private Integer replyid;

    private Date msgdate;

    private Byte replystatus;

    private Date replydate;

    private Byte status;

    public Integer getTabid() {
        return tabid;
    }

    public void setTabid(Integer tabid) {
        this.tabid = tabid;
    }

    public Integer getUserownerid() {
        return userownerid;
    }

    public void setUserownerid(Integer userownerid) {
        this.userownerid = userownerid;
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

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent == null ? null : msgcontent.trim();
    }

    public Integer getReplyid() {
        return replyid;
    }

    public void setReplyid(Integer replyid) {
        this.replyid = replyid;
    }

    public Date getMsgdate() {
        return msgdate;
    }

    public void setMsgdate(Date msgdate) {
        this.msgdate = msgdate;
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