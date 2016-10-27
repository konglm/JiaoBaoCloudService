package com.goldeneyes.pojo;

import java.util.Date;

public class UserSpaceEnc {
    private Integer tabid;

    private Integer userspaceid;

    private String enctype;

    private String encname;

    private String encaddr;

    private String encimgaddr;

    private Integer publisherid;

    private Date publishdate;

    private Integer encorder;

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

    public String getEnctype() {
        return enctype;
    }

    public void setEnctype(String enctype) {
        this.enctype = enctype == null ? null : enctype.trim();
    }

    public String getEncname() {
        return encname;
    }

    public void setEncname(String encname) {
        this.encname = encname == null ? null : encname.trim();
    }

    public String getEncaddr() {
        return encaddr;
    }

    public void setEncaddr(String encaddr) {
        this.encaddr = encaddr == null ? null : encaddr.trim();
    }

    public String getEncimgaddr() {
        return encimgaddr;
    }

    public void setEncimgaddr(String encimgaddr) {
        this.encimgaddr = encimgaddr == null ? null : encimgaddr.trim();
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

    public Integer getEncorder() {
        return encorder;
    }

    public void setEncorder(Integer encorder) {
        this.encorder = encorder;
    }
}