const app = getApp()
Page({
  data: {
    classify: ["请选择服务", "快递帮拿", "快递帮寄", "打印"],
    index: 0,
    animationData: null,
    imageUrl: "",
    finalImgUrl: ""
  },
  onLoad: function(options) {

  },
  bindPickerChange: function(e) {
    this.setData({
      index: e.detail.value
    })

    this.kuaidibangnaShowOut()
  },
  kuaidibangnaShowOut: function(res) {
    var that = this;
    var animation = wx.createAnimation({
      duration: 300,
      timingFunction: 'ease',
    })

    this.animation = animation
    setTimeout(function() {
      that.fadeIn();
    }, 200)
  },
  fadeIn: function() {
    this.animation.translateX(100).step()
    this.setData({
      animationData: this.animation.export()
    })
  },
  issueImg: function(e) {
    var that = this
    wx.chooseImage({
      count: 1,
      sizeType: ['original'],
      sourceType: ['album', 'camera'],
      success: function(res) {
        that.setData({
          imageUrl: res.tempFilePaths[0]
        })
      },
    })
  },
  issuePaotui: function(e) {
    var that = this
    var wechat = e.detail.value.wechat
    var phone = e.detail.value.phone
    var price = e.detail.value.price
    var classify = this.data.classify[e.detail.value.classify]
    if (this.data.index == 0) {
      wx.showToast({
        title: '请选择服务',
        icon: "none"
      })
      return;
    }
    if (wechat == "" || phone == "" || price == "") {
      wx.showToast({
        title: '联系方式需要输入完全',
        icon: "none"
      })
      return;
    }

    if (classify == "快递帮拿") {
      var qujianPlace = e.detail.value.qujianPlace
      var qujianma = e.detail.value.qujianma
      var songdaPlace = e.detail.value.songdaPlace
      var songdaTime = e.detail.value.songdaTime
      var weight = e.detail.value.weight
      var wupinClass = e.detail.value.wupinClass
      var nickName = app.globalData.userInfo.nickName
      var avatarUrl = app.globalData.userInfo.avatarUrl
      if (qujianma == "" || qujianPlace == "" || songdaPlace == "" || songdaTime == "" || weight == "" || wupinClass == "") {
        wx.showToast({
          title: '请将信息填写完全',
          icon: 'none'
        })
        return;
      }
      if (that.data.imageUrl == "") {
        wx.showToast({
          title: '请选择取件码相关照片',
          icon: ""
        })
      }
      wx.uploadFile({
        url: app.globalData.serverUrl + "/paotui/uploadImg",
        filePath: that.data.imageUrl,
        name: 'image',
        header: {
          'content-type': 'multipart/form-data',
        },
        success: function(e) {
          var finalImgUrl = e.data
          wx.request({
            url: app.globalData.serverUrl + "/paotui/issuePaotui",
            method: "POST",
            header: {
              'content-type': 'application/json'
            },
            data: {
              qujianPlace: qujianPlace,
              qujianma: qujianma,
              songdaPlace: songdaPlace,
              songdaTime: songdaTime,
              weight: weight,
              wupinClass: wupinClass,
              nickName: nickName,
              avatarUrl: avatarUrl,
              wechat: wechat,
              phone: phone,
              price: price,
              classify: classify,
              issueTime: new Date().getTime(),
              imageUrl: finalImgUrl,
              openID: app.globalData.openID
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
      })
    } else if (classify == "快递帮寄") {
      var qujianPlace = e.detail.value.qujianPlace
      var qujianTime=e.detail.value.qujianTime
      var weight = e.detail.value.weight
      var wupinClass = e.detail.value.wupinClass
      var nickName = app.globalData.userInfo.nickName
      var avatarUrl = app.globalData.userInfo.avatarUrl
      if (qujianTime == "" || qujianPlace == "" || weight == "" || wupinClass == "") {
        wx.showToast({
          title: '请将信息填写完全',
          icon: 'none'
        })
        return;
      }
      wx.request({
        url: app.globalData.serverUrl + "/paotui/issuePaotui",
        method: "POST",
        header: {
          'content-type': 'application/json'
        },
        data: {
          qujianPlace: qujianPlace,
          qujianTime: qujianTime,
          weight: weight,
          wupinClass: wupinClass,
          nickName: nickName,
          avatarUrl: avatarUrl,
          wechat: wechat,
          phone: phone,
          price: price,
          classify: classify,
          issueTime: new Date().getTime(),
          openID: app.globalData.openID
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
    } else {
      var songdaPlace = e.detail.value.songdaPlace
      var songdaTime = e.detail.value.songdaTime
      var nickName = app.globalData.userInfo.nickName
      var avatarUrl = app.globalData.userInfo.avatarUrl
      if (songdaPlace == "" || songdaTime == "") {
        wx.showToast({
          title: '请将信息填写完全',
          icon: 'none'
        })
        return;
      }
      wx.request({
        url: app.globalData.serverUrl + "/paotui/issuePaotui",
        method: "POST",
        header: {
          'content-type': 'application/json'
        },
        data: {
          songdaPlace: songdaPlace,
          songdaTime: songdaTime,
          nickName: nickName,
          avatarUrl: avatarUrl,
          wechat: wechat,
          phone: phone,
          price: price,
          classify: classify,
          issueTime: new Date().getTime(),

          openID: app.globalData.openID
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