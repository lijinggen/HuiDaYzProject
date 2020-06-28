package com.huida.bean;

public class WendaComment extends Comment{
    private Integer admireTimes;
    private String openID;
    private String avatarUrl;
    private String nickName;

    public Integer getAdmireTimes() {
        return admireTimes;
    }

    public void setAdmireTimes(Integer admireTimes) {
        this.admireTimes = admireTimes;
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

    @Override
    public String toString() {
        return "WendaComment{" +
                "admireTimes=" + admireTimes +
                ", openID='" + openID + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
