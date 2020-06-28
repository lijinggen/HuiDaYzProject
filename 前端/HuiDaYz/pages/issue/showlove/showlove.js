const app = getApp()
Page({
  data: {
    imageTempUrl:[]
  },
  onLoad: function(options) {},

  issueShowlove: function(e) {
    var that =this;
    var nickName;
    var avatarUrl;
    var finalImgUrl;
    if (e.detail.value.isNiming == true) {
      nickName = "匿名";
      avatarUrl = "../../images/niming.jpg"
    } else {
      nickName = app.globalData.userInfo.nickName;
      avatarUrl = app.globalData.userInfo.avatarUrl;
    }
    var issueTime = new Date().getTime();
    var isNiming = e.detail.value.isNiming;
    var reason = e.detail.value.reason;
    var target = e.detail.value.target;
    var openID = app.globalData.openID;
    var isShow = 0;
    if (that.data.imageTempUrl.length == 0) {
      finalImgUrl = "weixuan"
      wx.request({
        url: app.globalData.serverUrl + "/showlove/issueShowlove",
        method: "POST",
        header: {
          'content-type': 'application/json'
        },
        data: {
          issueTime: issueTime,
          reason: reason,
          target: target,
          isShow: isShow,
          openID: openID,
          avatarUrl: avatarUrl,
          nickName: nickName,
          finalImgUrl: finalImgUrl
        },
        success: function (e) {
          wx.showToast({
            title: '发布成功!等待审核',
            duration: 2000,
            success: function () {
              setTimeout(function () {
                wx.navigateBack({})
              }, 2000)
            }
          })
        }
      })
    }
    else {
      wx.uploadFile({
        url: app.globalData.serverUrl + "/showlove/uploadImg",
        filePath: that.data.imageTempUrl[0],
        name: 'image',
        header: {
          'content-type': 'multipart/form-data',
        },
        success:function(e){
          finalImgUrl=e.data
          wx.request({
            url: app.globalData.serverUrl + "/showlove/issueShowlove",
            method: "POST",
            header: {
              'content-type': 'application/json'
            },
            data: {
              issueTime: issueTime,
              reason: reason,
              target: target,
              isShow: isShow,
              openID: openID,
              avatarUrl: avatarUrl,
              nickName: nickName,
              finalImgUrl: finalImgUrl
            },
            success: function (e) {
              wx.showToast({
                title: '发布成功',
                duration: 2000,
                success: function () {
                  setTimeout(function () {
                    wx.navigateBack({})
                  }, 2000)
                }
              })
            }
          })
        }
      })
    }
  },
  chooseImg:function(e){
    var that=this
    wx.chooseImage({
      count: 1,
      sizeType: ['original'],
      sourceType: ['album', 'camera'],
      success: function(res) {
        that.setData({
          imageTempUrl:res.tempFilePaths
        })
      },
    })
  }
})