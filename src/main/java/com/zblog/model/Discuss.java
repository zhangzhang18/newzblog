package com.zblog.model;

import java.util.Date;

public class Discuss {
    private Integer discussid;

    private Date discussDatetime;

    private Integer vistor;

    private Integer articleid;

    private Integer reply;

    private Integer replyuserid;

    private String topic;

    private Integer isshow;

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    private Integer wcount;

    private String discusscontent;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Integer getReplyuserid() {
        return replyuserid;
    }

    public void setReplyuserid(Integer replyuserid) {
        this.replyuserid = replyuserid;
    }

    public Integer getDiscussid() {
        return discussid;
    }

    public void setDiscussid(Integer discussid) {
        this.discussid = discussid;
    }

    public Date getDiscussDatetime() {
        return discussDatetime;
    }

    public void setDiscussDatetime(Date discussDatetime) {
        this.discussDatetime = discussDatetime;
    }

    public Integer getVistor() {
        return vistor;
    }

    public void setVistor(Integer vistor) {
        this.vistor = vistor;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public Integer getIsshow() {
        return isshow;
    }

    public void setIsshow(Integer isshow) {
        this.isshow = isshow;
    }

    public Integer getWcount() {
        return wcount;
    }

    public void setWcount(Integer wcount) {
        this.wcount = wcount;
    }

    public String getDiscusscontent() {
        return discusscontent;
    }

    public void setDiscusscontent(String discusscontent) {
        this.discusscontent = discusscontent == null ? null : discusscontent.trim();
    }
}