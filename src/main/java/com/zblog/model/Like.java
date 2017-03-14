package com.zblog.model;

import java.util.Date;

public class Like extends LikeKey {
    private Date likeDatetime;

    private Integer articleid;

    public Date getLikeDatetime() {
        return likeDatetime;
    }

    public void setLikeDatetime(Date likeDatetime) {
        this.likeDatetime = likeDatetime;
    }

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }
}