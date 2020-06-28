package com.huida.bean;

import java.util.Date;
import java.util.List;

public class Wenda {
    private String openID;
    private String avatarUrl;
    private String nickName;
    private String finalImgUrl;
    private Integer watchTimes;
    private String question;
    private String detail;
    private String formatTime;
    private Date issueTime;
    private Integer wendaID;
    private List<WendaComment> wendaCommentList;

    public List<WendaComment> getWendaCommentList() {
        return wendaCommentList;
    }

    public void setWendaCommentList(List<WendaComment> wendaCommentList) {
        this.wendaCommentList = wendaCommentList;
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

    public String getFinalImgUrl() {
        return finalImgUrl;
    }

    public void setFinalImgUrl(String finalImgUrl) {
        this.finalImgUrl = finalImgUrl;
    }

    public Integer getWatchTimes() {
        return watchTimes;
    }

    public void setWatchTimes(Integer watchTimes) {
        this.watchTimes = watchTimes;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public Integer getWendaID() {
        return wendaID;
    }

    public void setWendaID(Integer wendaID) {
        this.wendaID = wendaID;
    }

    @Override
    public String toString() {
        return "Wenda{" +
                "openID='" + openID + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", finalImgUrl='" + finalImgUrl + '\'' +
                ", watchTimes=" + watchTimes +
                ", question='" + question + '\'' +
                ", detail='" + detail + '\'' +
                ", formatTime='" + formatTime + '\'' +
                ", issueTime=" + issueTime +
                ", wendaID=" + wendaID +
                '}';
    }
}
