package com.yztc.entity;

/**
 * Created by Administrator on 2016/9/18. private String[] addrs;
 */
public class ReadConmic {
    private String title;
    private int sid;
    private long updateTime;
    private int counts;
    private double size;


    public ReadConmic(String title, int sid, long updateTime, int counts, double size, String[] addrs) {
        this.title = title;
        this.sid = sid;
        this.updateTime = updateTime;
        this.counts = counts;
        this.size = size;
        this.addrs = addrs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String[] getAddrs() {
        return addrs;
    }

    public void setAddrs(String[] addrs) {
        this.addrs = addrs;
    }
}
