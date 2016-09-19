package com.yztc.entity;

/**
 * Created by Administrator on 2016/9/12.
 */
public class LastCharpter {
    private String id;
    private String title;

    public LastCharpter(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
