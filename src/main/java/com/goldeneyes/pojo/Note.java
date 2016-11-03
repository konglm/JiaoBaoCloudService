package com.goldeneyes.pojo;

import java.util.Date;

public class Note {
    private Integer tabid;

    private Integer studentid;

    private Byte notetype;

    private Byte checktype;

    private String msgcontent;

    private Integer publisherid;

    private Date publishdate;

    private Byte status;

    public Integer getTabid() {
        return tabid;
    }

    public void setTabid(Integer tabid) {
        this.tabid = tabid;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Byte getNotetype() {
        return notetype;
    }

    public void setNotetype(Byte notetype) {
        this.notetype = notetype;
    }

    public Byte getChecktype() {
        return checktype;
    }

    public void setChecktype(Byte checktype) {
        this.checktype = checktype;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}