package com.yztc.entity;

/**
 * Created by Administrator on 2016/9/13.
 */
public class User {
    private String id;
    private String nickname;
    private String uid;
    private String root_id;
    private String parent_id;
    private String content;
    private String post_time;
    private String reply_no;
    private String user_thumb;

    public User(String id, String nickname, String uid, String root_id, String parent_id, String content,
                String post_time, String reply_no, String user_thumb) {
        this.id = id;
        this.nickname = nickname;
        this.uid = uid;
        this.root_id = root_id;
        this.parent_id = parent_id;
        this.content = content;
        this.post_time = post_time;
        this.reply_no = reply_no;
        this.user_thumb = user_thumb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRoot_id() {
        return root_id;
    }

    public void setRoot_id(String root_id) {
        this.root_id = root_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public String getReply_no() {
        return reply_no;
    }

    public void setReply_no(String reply_no) {
        this.reply_no = reply_no;
    }

    public String getUser_thumb() {
        return user_thumb;
    }

    public void setUser_thumb(String user_thumb) {
        this.user_thumb = user_thumb;
    }
}
