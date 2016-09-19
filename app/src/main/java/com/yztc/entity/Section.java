package com.yztc.entity;

/**
 * Created by Administrator on 2016/9/14.
 */
public class Section {
    private String title;
    private int sid;
    private long id;
    private float size;
    private int counts;
    private int iszm;
    private String surl;

    public Section(String title, int sid, long id, float size, int counts, int iszm, String surl) {
        this.title = title;
        this.sid = sid;
        this.id = id;
        this.size = size;
        this.counts = counts;
        this.iszm = iszm;
        this.surl = surl;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getIszm() {
        return iszm;
    }

    public void setIszm(int iszm) {
        this.iszm = iszm;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}
