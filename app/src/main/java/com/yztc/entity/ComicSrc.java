package com.yztc.entity;

/**
 * Created by Administrator on 2016/9/13.
 */
public class ComicSrc {
    private String title;
    private String id;
    private String lastCharpterTitle;
    private String lastCharpterId;
    private long lastCharpterUpdateTime;

    public ComicSrc(String title, String id, String lastCharpterTitle, String lastCharpterId, long lastCharpterUpdateTime) {
        this.title = title;
        this.id = id;
        this.lastCharpterTitle = lastCharpterTitle;
        this.lastCharpterId = lastCharpterId;
        this.lastCharpterUpdateTime = lastCharpterUpdateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastCharpterTitle() {
        return lastCharpterTitle;
    }

    public void setLastCharpterTitle(String lastCharpterTitle) {
        this.lastCharpterTitle = lastCharpterTitle;
    }

    public String getLastCharpterId() {
        return lastCharpterId;
    }

    public void setLastCharpterId(String lastCharpterId) {
        this.lastCharpterId = lastCharpterId;
    }

    public long getLastCharpterUpdateTime() {
        return lastCharpterUpdateTime;
    }

    public void setLastCharpterUpdateTime(long lastCharpterUpdateTime) {
        this.lastCharpterUpdateTime = lastCharpterUpdateTime;
    }
}
