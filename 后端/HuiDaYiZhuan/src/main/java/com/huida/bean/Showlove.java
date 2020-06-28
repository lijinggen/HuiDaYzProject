package com.huida.bean;

import java.util.Date;

public class Showlove {
    private String target;
    private String reason;
    private Integer isShow;
    private String openID;
    private Date issueTime;
    private String formatTime;
    private String avatarUrl;
    private String nickName;
    private String finalImgUrl;
    private Integer showloveID;

    public Integer getShowloveID() {
        return showloveID;
    }

    public void setShowloveID(Integer showloveID) {
        this.showloveID = showloveID;
    }

    public String getFinalImgUrl() {
        return finalImgUrl;
    }

    public void setFinalImgUrl(String finalImgUrl) {
        this.finalImgUrl = finalImgUrl;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
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

    @Override
    public String toString() {
        return "Showlove{" +
                "target='" + target + '\'' +
                ", reason='" + reason + '\'' +
                ", isShow=" + isShow +
                ", openID='" + openID + '\'' +
                ", issueTime=" + issueTime +
                ", formatTime='" + formatTime + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", finalImgUrl='" + finalImgUrl + '\'' +
                '}';
    }
}
