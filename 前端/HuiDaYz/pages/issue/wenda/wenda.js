const app = getApp()
Page({

  data: {
    imageUrl: [],
    finalImageList:[],
    issuing:false
  },
  onLoad: function(options) {

  },
  issueWenda: function(e) {
    var question = e.detail.value.question;
    var detail = e.detail.value.detail
    var openID = app.globalData.openID;
    var avatarUrl = app.globalData.userInfo.avatarUrl
    var nickName = app.globalData.userInfo.nickName
    var that=this
    if (question.length == 0 || detail.length == 0) {
      wx.showToast({
        title: '问题或问题详情不能为空!',
        icon: 'none'
      })
      return;
    }
    this.setData({
      issuing:true
    })
    var imageUrl = this.data.imageUrl
    if (imageUrl.length != 0) {
      for (var i = 0; i < imageUrl.length; i++) {
        wx.uploadFile({
          url: app.globalData.serverUrl + "/wenda/uploadImg",
          filePath: imageUrl[i],
          name: 'image',
          header: {
            'content-type': 'multipart/form-data',
          },
          success: function(res) {
            var finalImageList = that.data.finalImageList
            finalImageList.push(res.data)
            that.setData({
              finalImageList: finalImageList
            })
            if (that.data.finalImageList.length == that.data.imageUrl.length) {
              var finalImageList = JSON.stringify(that.data.finalImageList);
              wx.request({
                url: app.globalData.serverUrl + "/wenda/issueWenda",
                method: "POST",
                header: {
                  'content-type': 'application/json'
                },
                data: {
                  openID: openID,
                  avatarUrl: avatarUrl,
                  nickName: nickName,
                  issueTime: new Date().getTime(),
                  finalImgUrl: finalImageList,
                  watchTimes: 0,
                  question: question,
                  detail: detail,

                },
                success: function(e) {
                  wx.showToast({
                    title: '发布成功',
                    duration: 2000,
                    success: function() {
                      setTimeout(function() {
                        wx.navigateBack({})
                      }, 2000)
                    }
                  })
                }
              })
            }
          }
        })
      }
    }
    else{
      wx.request({
        url: app.globalData.serverUrl + "/wenda/issueWenda",
        method: "POST",
        header: {
          'content-type': 'application/json'
        },
        data: {
          openID: openID,
          avatarUrl: avatarUrl,
          nickName: nickName,
          issueTime: new Date().getTime(),
          finalImgUrl: null,
          watchTimes: 0,
          question: question,
          detail: detail,

        },
        success: function(e) {
          wx.showToast({
            title: '发布成功',
            duration: 2000,
            success: function() {
              setTimeout(function() {
                wx.navigateBack({})
              }, 2000)
            }
          })
        }
      })
    }

  },
  chooseImg: function(res) {
    var that = this
    wx.chooseImage({
      count: 4,
      sizeType: ['original'],
      sourceType: ['album', 'camera'],
      success(res) {
        that.setData({
          imageUrl: res.tempFilePaths
        })
      }
    })
  }
})