package com.yztc.entity;

/**
 * Created by Administrator on 2016/9/12.
 */
public class Conmic {
    private String title;
    private String thumb;
    private String comicId;
    private LastCharpter lastCharpter;
    private String authorName;
    private String comicType;

    public Conmic(String title, String thumb, String comicId, LastCharpter lastCharpter, String authorName, String comicType) {
        this.title = title;
        this.thumb = thumb;
        this.comicId = comicId;
        this.lastCharpter = lastCharpter;
        this.authorName = authorName;
        this.comicType = comicType;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getComicType() {
        return comicType;
    }

    public void setComicType(String comicType) {
        this.comicType = comicType;
    }

    public Conmic(String title, String thumb, String comicId, LastCharpter lastCharpter) {
        this.title = title;
        this.thumb = thumb;
        this.comicId = comicId;
        this.lastCharpter = lastCharpter;
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

    public String getComicId() {
        return comicId;
    }

    public void setComicId(String comicId) {
        this.comicId = comicId;
    }

    public LastCharpter getLastCharpter() {
        return lastCharpter;
    }

    public void setLastCharpter(LastCharpter lastCharpter) {
        this.lastCharpter = lastCharpter;
    }
}
