package com.zblog.model;

import java.util.Date;

public class UserInfo {
    private Integer userid;

    private Integer age;

    private String sex;

    private Date birthday;

    private Date registerDatetime;

    private Date loginDatetime;

    private String loginCont;

    private String userPhoto;

    private String image;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegisterDatetime() {
        return registerDatetime;
    }

    public void setRegisterDatetime(Date registerDatetime) {
        this.registerDatetime = registerDatetime;
    }

    public Date getLoginDatetime() {
        return loginDatetime;
    }

    public void setLoginDatetime(Date loginDatetime) {
        this.loginDatetime = loginDatetime;
    }

    public String getLoginCont() {
        return loginCont;
    }

    public void setLoginCont(String loginCont) {
        this.loginCont = loginCont == null ? null : loginCont.trim();
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto == null ? null : userPhoto.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }
}