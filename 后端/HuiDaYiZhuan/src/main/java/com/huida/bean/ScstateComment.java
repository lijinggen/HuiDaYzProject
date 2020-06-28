package com.huida.bean;

import java.util.Date;

public class ScstateComment {
    private Integer scCommentID;
    private String answerOpenID;
    private String answerNickName;
    private String answerAvatarUrl;

    private String targetOpenID;
    private String targetNickName;
    private String targetAvatarUrl;

    private String content;
    private Integer scstateID;
    private Date issueTime;
    private String formatTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswerOpenID() {
        return answerOpenID;
    }

    public void setAnswerOpenID(String answerOpenID) {
        this.answerOpenID = answerOpenID;
    }

    public String getAnswerNickName() {
        return answerNickName;
    }

    public void setAnswerNickName(String answerNickName) {
        this.answerNickName = answerNickName;
    }

    public String getAnswerAvatarUrl() {
        return answerAvatarUrl;
    }

    public void setAnswerAvatarUrl(String answerAvatarUrl) {
        this.answerAvatarUrl = answerAvatarUrl;
    }

    public String getTargetOpenID() {
        return targetOpenID;
    }

    public void setTargetOpenID(String targetOpenID) {
        this.targetOpenID = targetOpenID;
    }

    public String getTargetNickName() {
        return targetNickName;
    }

    public void setTargetNickName(String targetNickName) {
        this.targetNickName = targetNickName;
    }

    public String getTargetAvatarUrl() {
        return targetAvatarUrl;
    }

    public void setTargetAvatarUrl(String targetAvatarUrl) {
        this.targetAvatarUrl = targetAvatarUrl;
    }

    public Integer getScstateID() {
        return scstateID;
    }

    public void setScstateID(Integer scstateID) {
        this.scstateID = scstateID;
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

    public Integer getScCommentID() {
        return scCommentID;
    }

    public void setScCommentID(Integer scCommentID) {
        this.scCommentID = scCommentID;
    }
}
