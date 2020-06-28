const app = getApp()
Page({
  data: {
    avatarUrl: "",
    scstateList: new Array,
    page: 0,
    maxPage: 0,
    hideModal: true,
    animationData: {},
    content: "",
    placeHolder: "评论",
    scstateID: "",
    isFold: new Array,
    currentComment: []
  },
  onLoad: function(options) {

    var that = this;
    wx.stopPullDownRefresh()
    this.setData({
      avatarUrl: app.globalData.userInfo.avatarUrl
    })
    wx.request({
      url: app.globalData.serverUrl + "/scstate/loading?page=" + that.data.page,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        var isFold = that.data.isFold
        for (var i = 0; i < res.data.length; i++) {
          res.data[i].finalImageList = JSON.parse(res.data[i].finalImageList)
          isFold.push(0)
        }
        if (that.data.scstateList.length == 0) {
          that.setData({
            scstateList: res.data,
            isFold: isFold
          })
        } else {
          var scstateList = that.data.scstateList
          var isFold = that.data.isFold
          for (var i = 0; i < res.data.length; i++) {
            scstateList.push(res.data[i])
            isFold.push(0)
          }
          that.setData({
            scstateList: scstateList,
            isFold: isFold
          })
        }
        wx.request({
          url: app.globalData.serverUrl + "/scstate/getMaxPage",
          method: "GET",
          header: {
            'content-type': 'application/json'
          },
          success: function(res) {
            that.setData({
              maxPage: res.data
            })
          }
        })
      }
    })
  },
  issueScstate: function(e) {
    wx.navigateTo({
      url: '../issue/scstate/scstate',
    })
  },
  previewImg: function(e) {
    wx.previewImage({
      current: e.currentTarget.dataset.currenturl,
      urls: e.currentTarget.dataset.urls
    })
  },
  onPullDownRefresh: function() {
    var that=this
    that.setData({
      page:0,
      scstateList:[],
      isFold:[]
    })
    this.onLoad();

  },

  showModal: function(e) {
    var that = this;
    if (e.currentTarget.dataset.target != null) {
      that.setData({
        placeHolder: "回复@" + e.currentTarget.dataset.target.answerNickName,
        currentComment: e.currentTarget.dataset.target
      })
    } else {
      that.setData({
        placeHolder: "评论",
        currentComment: []
      })
    }
    that.setData({
      scstateID: e.currentTarget.dataset.scstateid,
      hideModal: false
    })
    var animation = wx.createAnimation({
      duration: 300,
      timingFunction: 'ease',
    })
    this.animation = animation
    setTimeout(function() {
      that.fadeIn();
    }, 200)
  },

  hideModal: function() {
    var that = this;
    var animation = wx.createAnimation({
      duration: 300,
      timingFunction: 'ease',
    })
    this.animation = animation
    that.fadeDown();
    setTimeout(function() {
      that.setData({
        scstateID: "",
        hideModal: true,
        currentComment: []
      })
    }, 300)

  },

  fadeIn: function() {
    this.animation.translateY(0).step()
    this.setData({
      animationData: this.animation.export()
    })
  },
  fadeDown: function() {
    this.animation.translateY(300).step()
    this.setData({
      animationData: this.animation.export(),
    })
  },
  getContent: function(e) {
    this.setData({
      content: e.detail.value
    })
  },

  issueComment: function(e) {
    var that = this
    var placeHolder = this.data.placeHolder
    var scstateID = this.data.scstateID
    var content = this.data.content
    if (content == "") {
      wx.showToast({
        title: '请输入内容',
        icon: 'none'
      })
      return;
    }
    if (placeHolder == "评论") {

      wx.request({
        url: app.globalData.serverUrl + "/scstate/issueComment",
        method: "POST",
        header: {
          'content-type': 'application/json'
        },
        data: {
          answerOpenID: app.globalData.openID,
          answerNickName: app.globalData.userInfo.nickName,
          answerAvatarUrl: app.globalData.userInfo.avatarUrl,
          issueTime: new Date().getTime(),
          scstateID: scstateID,
          content: content
        },
        success: function(res) {
          var comment = new Object;
          comment.answerOpenID = app.globalData.openID
          comment.answerNickName = app.globalData.userInfo.nickName
          comment.answerAvatarUrl = app.globalData.userInfo.avatarUrl
          comment.issueTime = new Date().getTime()
          comment.scstateID = scstateID
          comment.content = content
          var scstateList = that.data.scstateList
          for (var i = 0; i < scstateList.length; i++) {
            if (scstateList[i].scstateID == scstateID) {
              scstateList[i].scstateCommentList.push(comment)
            }
          }
          wx.showToast({
            title: '回复成功',
            duration: 1000,
            success: function() {
              setTimeout(function() {
                that.setData({
                  content: "",
                  scstateList: scstateList
                })
                that.hideModal()
              }, 1000)
            }
          })
        }
      })
    } else {
      wx.request({
        url: app.globalData.serverUrl + "/scstate/issueComment",
        method: "POST",
        header: {
          'content-type': 'application/json'
        },
        data: {
          answerOpenID: app.globalData.openID,
          answerNickName: app.globalData.userInfo.nickName,
          answerAvatarUrl: app.globalData.userInfo.avatarUrl,
          targetOpenID: that.data.currentComment.answerOpenID,
          targetNickName: that.data.currentComment.answerNickName,
          targetAvatarUrl: that.data.currentComment.answerAvatarUrl,
          issueTime: new Date().getTime(),
          scstateID: scstateID,
          content: content
        },
        success: function(res) {
          var comment = new Object;
          comment.answerOpenID = app.globalData.openID
          comment.answerNickName = app.globalData.userInfo.nickName
          comment.answerAvatarUrl = app.globalData.userInfo.avatarUrl
          comment.targetOpenID = that.data.currentComment.answerOpenID
          comment.targetNickName = that.data.currentComment.answerNickName
          comment.targetAvatarUrl = that.data.currentComment.answerAvatarUrl
          comment.issueTime = new Date().getTime()
          comment.scstateID = scstateID
          comment.content = content
          var scstateList = that.data.scstateList
          for (var i = 0; i < scstateList.length; i++) {
            if (scstateList[i].scstateID == scstateID) {
              scstateList[i].scstateCommentList.push(comment)
            }
          }
          wx.showToast({
            title: '回复成功',
            duration: 1000,
            success: function() {
              setTimeout(function() {
                that.setData({
                  content: "",
                  scstateList: scstateList
                })
                that.hideModal()
              }, 1000)
            }
          })
        }
      })
    }
  },

  onReachBottom: function() {
    var page = this.data.page;
    if (page == this.data.maxPage) {
      return;
    } else {
      this.setData({
        page: page + 1
      })
      this.onLoad()
    }
  },
  fold: function(e) {
    var that = this
    var index = e.currentTarget.dataset.index
    var isFold = that.data.isFold;
    if (that.data.isFold[index] == 0) {
      isFold[index] = 1;
    } else {
      isFold[index] = 0;
    }
    that.setData({
      isFold: isFold
    })
  },
  changePlaceHolder: function(e) {
    this.setData({
      placeHolder: '回复@' + e.currentTarget.dataset.target.answerNickName
    })
  }
})