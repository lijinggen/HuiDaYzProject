package com.huida.bean;

import java.util.Date;
import java.util.List;

public class Scstate {
    private Integer ScstateID;
    private String openID;
    private String avatarUrl;
    private String nickName;
    private Integer admireTimes;
    private String finalImageList;
    private String content;
    private Date issueTime;
    private String formatTime;
    private Integer isTop;
    private List<ScstateComment> scstateCommentList;

    public List<ScstateComment> getScstateCommentList() {
        return scstateCommentList;
    }

    public void setScstateCommentList(List<ScstateComment> scstateCommentList) {
        this.scstateCommentList = scstateCommentList;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
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

    public Integer getAdmireTimes() {
        return admireTimes;
    }

    public void setAdmireTimes(Integer admireTimes) {
        this.admireTimes = admireTimes;
    }

    public String getFinalImageList() {
        return finalImageList;
    }

    public void setFinalImageList(String finalImageList) {
        this.finalImageList = finalImageList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getScstateID() {
        return ScstateID;
    }

    public void setScstateID(Integer scstateID) {
        ScstateID = scstateID;
    }

    @Override
    public String toString() {
        return "Scstate{" +
                "ScstateID=" + ScstateID +
                ", openID='" + openID + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", admireTimes=" + admireTimes +
                ", finalImageList='" + finalImageList + '\'' +
                ", content='" + content + '\'' +
                ", issueTime=" + issueTime +
                ", formatTime='" + formatTime + '\'' +
                ", isTop=" + isTop +
                '}';
    }
}
