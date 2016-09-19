package com.yztc.entity;

/**
 * Created by Administrator on 2016/9/13.
 */
public class Classify {
    private String title;
    private String thumb;
    private int  cateId;
    //我修改了这个文件
    public Classify(String title, int cateId, String thumb) {
        this.title = title;
        this.cateId = cateId;
        this.thumb = thumb;
    }


    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
