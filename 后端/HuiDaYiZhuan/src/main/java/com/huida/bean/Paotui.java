package com.huida.bean;

import java.util.Date;

public class Paotui {
    private Integer paotuiID;
    private String openID;
    private String nickName;
    private String avatarUrl;
    private Date issueTime;
    private String formatTime;

    private String phone;
    private String wechat;
    private Double price;

    private String qujianPlace;
    private String qujianTime;
    private String songdaPlace;
    private String songdaTime;
    private String imageUrl;
    private String classify;

    public String getFormatTime() {
        return formatTime;
    }

    public void setFormatTime(String formatTime) {
        this.formatTime = formatTime;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Integer getPaotuiID() {
        return paotuiID;
    }

    public void setPaotuiID(Integer paotuiID) {
        this.paotuiID = paotuiID;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getQujianPlace() {
        return qujianPlace;
    }

    public void setQujianPlace(String qujianPlace) {
        this.qujianPlace = qujianPlace;
    }

    public String getQujianTime() {
        return qujianTime;
    }

    public void setQujianTime(String qujianTime) {
        this.qujianTime = qujianTime;
    }

    public String getSongdaPlace() {
        return songdaPlace;
    }

    public void setSongdaPlace(String songdaPlace) {
        this.songdaPlace = songdaPlace;
    }

    public String getSongdaTime() {
        return songdaTime;
    }

    public void setSongdaTime(String songdaTime) {
        this.songdaTime = songdaTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
