const app = getApp()
Page({
  data: {
    xianzhi: new Object,
    comment: [],
    commentContent: "",
    placeholder: "请输入评论"
  },
  onLoad: function(options) {
    var that = this;
    wx.request({
        url: app.globalData.serverUrl + "/xianzhi/detailXianzhi?xianzhiID=" + options.xianzhiID,
        method: "GET",
        header: {
          'content-type': 'application/json'
        },
        success: function(res) {
          res.data.finalImgUrl = JSON.parse(res.data.finalImgUrl);
          that.setData({
            xianzhi: res.data
          })
        }
      }),
      wx.request({
        url: app.globalData.serverUrl + "/xianzhi/getComment?xianzhiID=" + options.xianzhiID,
        method: "GET",
        header: {
          'content-type': 'application/json'
        },
        success: function(res) {
          that.setData({
            comment: res.data,
            placeholder: "请输入评论",
            commentContent: ""
          })
        }
      })
  },
  getCommentContent: function(e) {
    this.setData({
      commentContent: e.detail.value
    })
  },
  issuingComment: function(e) {
    var that = this;
    var issueTime = new Date().getTime();
    if (this.data.commentContent.length == 0) {
      wx.showToast({
        title: '请输入内容！',
        icon: none
      })
      return;
    }
    if (this.data.placeholder != "请输入评论") {
      this.setData({
        commentContent: this.data.placeholder + this.data.commentContent
      })
    }
    wx.request({
      url: app.globalData.serverUrl + "/xianzhi/issueComment",
      data: {
        belongID: that.data.xianzhi.xianzhiID,
        commentContent: that.data.commentContent,
        issueTime: issueTime
      },
      method: "POST",
      header: {
        'content-type': 'application/json'
      },
      success: function(e) {
        wx.showToast({
          title: '回复成功',
          duration: 2000,
          icon: "none"
        })
        wx.request({
          url: app.globalData.serverUrl + "/xianzhi/getComment?xianzhiID=" + that.data.xianzhi.xianzhiID,
          method: "GET",
          header: {
            'content-type': 'application/json'
          },
          success: function(res) {
            that.setData({
              comment: res.data,
              placeholder: "请输入评论",
              commentContent: ""
            })
          }
        })
      }
    })
  },
  changeResponse: function(e) {
    var content = e.currentTarget.dataset.content
    if (content != "请输入评论") {
      content = '回复@' + content + "楼:";
    }
    this.setData({
      placeholder: content
    })
  },
  previewImg: function (e) {
    wx.previewImage({
      current: e.currentTarget.dataset.currenturl,
      urls: e.currentTarget.dataset.urls
    })
  }
})