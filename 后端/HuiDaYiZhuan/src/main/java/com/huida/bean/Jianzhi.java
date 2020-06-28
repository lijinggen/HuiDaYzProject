package com.huida.bean;

import java.util.Date;

public class Jianzhi {
    private Integer JianzhiID;
    private String openID;
    private String nickName;
    private String avatarUrl;
    private Date issueTime;
    private String formatTime;

    private String gongzuoContent;
    private String wechat;
    private String phone;
    private String gongzuoPlace;
    private String price;

    public Integer getJianzhiID() {
        return JianzhiID;
    }

    public void setJianzhiID(Integer jianzhiID) {
        JianzhiID = jianzhiID;
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

    public String getFormatTime() {
        return formatTime;
    }

    public void setFormatTime(String formatTime) {
        this.formatTime = formatTime;
    }

    public String getGongzuoContent() {
        return gongzuoContent;
    }

    public void setGongzuoContent(String gongzuoContent) {
        this.gongzuoContent = gongzuoContent;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGongzuoPlace() {
        return gongzuoPlace;
    }

    public void setGongzuoPlace(String gongzuoPlace) {
        this.gongzuoPlace = gongzuoPlace;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
