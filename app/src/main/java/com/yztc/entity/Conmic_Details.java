package com.yztc.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
public class Conmic_Details {
    private String title;
    private long comicId;
    private String thumb;
    private String authorName;
    private String comicType;
    private String areaName;
    private String updateTime;
    private List<ComicSrc> comicSrc;
    private String tucaos;
    private String intro;

    public Conmic_Details(String title, long comicId, String thumb, String authorName,
                          String comicType, String areaName, String updateTime,
                          List<ComicSrc> comicSrc, String tucaos, String intro) {
        this.title = title;
        this.comicId = comicId;
        this.thumb = thumb;
        this.authorName = authorName;
        this.comicType = comicType;
        this.areaName = areaName;
        this.updateTime = updateTime;
        this.comicSrc = comicSrc;
        this.tucaos = tucaos;
        this.intro = intro;
    }

    public String getTucaos() {
        return tucaos;
    }

    public void setTucaos(String tucaos) {
        this.tucaos = tucaos;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getComicId() {
        return comicId;
    }

    public void setComicId(long comicId) {
        this.comicId = comicId;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<ComicSrc> getComicSrc() {
        return comicSrc;
    }

    public void setComicSrc(List<ComicSrc> comicSrc) {
        this.comicSrc = comicSrc;
    }
}
