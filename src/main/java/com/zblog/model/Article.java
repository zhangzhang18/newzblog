package com.zblog.model;

import java.util.Date;

public class Article {
    private Integer articleid;

    private Integer author;

    private String title;

    private Integer articletype;

    private Date createDatetime;

    private Date updateDatetime;

    private Integer isDiscuss;

    private Integer discussCount;

    private Integer isLike;

    private Integer likeCount;

    private Integer wcount;

    private String content;
    private User user;

    public Integer getWcount() {
        return wcount;
    }

    public void setWcount(Integer wcount) {
        this.wcount = wcount;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getArticletype() {
        return articletype;
    }

    public void setArticletype(Integer articletype) {
        this.articletype = articletype;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Integer getIsDiscuss() {
        return isDiscuss;
    }

    public void setIsDiscuss(Integer isDiscuss) {
        this.isDiscuss = isDiscuss;
    }

    public Integer getDiscussCount() {
        return discussCount;
    }

    public void setDiscussCount(Integer discussCount) {
        this.discussCount = discussCount;
    }

    public Integer getIsLike() {
        return isLike;
    }

    public void setIsLike(Integer isLike) {
        this.isLike = isLike;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}