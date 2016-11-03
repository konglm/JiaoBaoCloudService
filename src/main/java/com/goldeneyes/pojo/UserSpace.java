package com.goldeneyes.pojo;

import java.util.Date;

public class UserSpace {
    private Integer tabid;

    private Integer userid;

    private String msgcontent;

    private Integer publisherid;

    private Date publishdate;

    private Byte notetype;

    private Byte status;

    public Integer getTabid() {
        return tabid;
    }

    public void setTabid(Integer tabid) {
        this.tabid = tabid;
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

    public Integer getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(Integer publisherid) {
        this.publisherid = publisherid;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public Byte getNotetype() {
        return notetype;
    }

    public void setNotetype(Byte notetype) {
        this.notetype = notetype;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}