package com.goldeneyes.pojo;

import java.util.Date;

public class SpaceContentStatus {
    private Integer tabid;

    private Integer userid;

    private Byte spacetype;

    private Integer spaceid;

    private Byte isread;

    private Date readdate;

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

    public Byte getSpacetype() {
        return spacetype;
    }

    public void setSpacetype(Byte spacetype) {
        this.spacetype = spacetype;
    }

    public Integer getSpaceid() {
        return spaceid;
    }

    public void setSpaceid(Integer spaceid) {
        this.spaceid = spaceid;
    }

    public Byte getIsread() {
        return isread;
    }

    public void setIsread(Byte isread) {
        this.isread = isread;
    }

    public Date getReaddate() {
        return readdate;
    }

    public void setReaddate(Date readdate) {
        this.readdate = readdate;
    }
}