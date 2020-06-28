const app = getApp()
Page({
  data: {
    showlove: new Object,
    comment: [],
    commentContent: "",
    placeholder: "请输入评论"
  },
  onLoad: function(options) {
    var that = this;
    wx.request({
      url: app.globalData.serverUrl + "/showlove/detailShowlove?showloveID=" + options.showloveID,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        that.setData({
          showlove: res.data
        })
      }
    })
    wx.request({
      url: app.globalData.serverUrl + "/showlove/getComment?showloveID=" + options.showloveID,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        that.setData({
          comment: res.data
        })
      }
    })
  },
  previewImg: function(e) {
    wx.previewImage({
      current: e.currentTarget.dataset.imgurl,
      urls: [e.currentTarget.dataset.imgurl]
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
    if(this.data.placeholder!="请输入评论"){
      this.setData({
        commentContent:this.data.placeholder+this.data.commentContent
      })
    }
    wx.request({
      url: app.globalData.serverUrl + "/showlove/issueComment",
      data: {
        belongID: that.data.showlove.showloveID,
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
          url: app.globalData.serverUrl + "/showlove/getComment?showloveID=" + that.data.showlove.showloveID,
          method: "GET",
          header: {
            'content-type': 'application/json'
          },
          success: function(res) {
            that.setData({
              comment: res.data,
              placeholder:"请输入评论",
              commentContent:""
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
  }
})