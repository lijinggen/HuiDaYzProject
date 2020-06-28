package com.huida.bean;

import java.util.Date;

public class Comment {
    private Integer commentID;
    private Date issueTime;
    private String formatTime;
    private String commentContent;
    private Integer belongID;

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getBelongID() {
        return belongID;
    }

    public void setBelongID(Integer belongID) {
        this.belongID = belongID;
    }
}
