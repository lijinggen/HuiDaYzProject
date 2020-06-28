package com.huida.bean;

import java.util.Arrays;
import java.util.Date;

public class Xianzhi {
    private Integer xianzhiID;
    private String openID;
    private String formatTime;
    private Date issueTime;
    private String title;
    private String introduce;
    private String classify;
    private String location;
    private String hownew;
    private String wechat;
    private String contact;
    private String finalImgUrl;
    private Integer watchTimes;
    private String avatarUrl;
    private String nickName;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getWatchTimes() {
        return watchTimes;
    }

    public void setWatchTimes(Integer watchTimes) {
        this.watchTimes = watchTimes;
    }

    public String getFinalImgUrl() {
        return finalImgUrl;
    }

    public void setFinalImgUrl(String finalImgUrl) {
        this.finalImgUrl = finalImgUrl;
    }

    public Integer getXianzhiID() {
        return xianzhiID;
    }

    public void setXianzhiID(Integer xianzhiID) {
        this.xianzhiID = xianzhiID;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getFormatTime() {
        return formatTime;
    }

    public void setFormatTime(String formatTime) {
        this.formatTime = formatTime;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHownew() {
        return hownew;
    }

    public void setHownew(String hownew) {
        this.hownew = hownew;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Xianzhi{" +
                "xianzhiID=" + xianzhiID +
                ", openID='" + openID + '\'' +
                ", formatTime='" + formatTime + '\'' +
                ", issueTime=" + issueTime +
                ", title='" + title + '\'' +
                ", introduce='" + introduce + '\'' +
                ", classify='" + classify + '\'' +
                ", location='" + location + '\'' +
                ", hownew='" + hownew + '\'' +
                ", wechat='" + wechat + '\'' +
                ", contact='" + contact + '\'' +
                ", finalImgUrl='" + finalImgUrl + '\'' +
                ", watchTimes=" + watchTimes +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", price=" + price +
                '}';
    }
}
