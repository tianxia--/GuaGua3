package com.yztc.entity;

/**
 * Created by Administrator on 2016/9/18.
 */
public class Comic {
    int id;
    private int comicId;
    private String title;
    private String thumb;

    public Comic() {
    }

    public Comic(int id, String title, String thumb) {
        this.comicId = id;
        this.title = title;
        this.thumb = thumb;
    }

    public Comic(String thumb, String title) {

        this.thumb = thumb;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComicId() {
        return comicId;
    }

    public void setComicId(int comicId) {
        this.comicId = comicId;
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
