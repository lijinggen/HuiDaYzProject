var QQMapWX = require('../../../libs/qqmap-wx-jssdk.js');
var qqmapsdk;
const app = getApp()
Page({
  data: {
    location_head: "正在获取当前位置",
    hownew: ["请选择成色", "九九新", "九五新", "九成新", "八成新", "七成新", "七成新以下"],
    classify: ["请选择类别", "手机", "电脑", "衣服", "文具", "生活用品", "化妆用品", "其它"],
    index1: 0,
    index2: 0,
    imgTempUrl: [],
    finalImgUrl: [],
    btnIsUsing: false
  },
  onLoad: function (e) {
    
    var that = this
    qqmapsdk = new QQMapWX({
      key: '65XBZ-7WWCI-WPLGX-5QOWO-MFR6F-NHFGT'
    });
    wx.getLocation({
      type: 'wgs84',
      success(res) {
        const latitude = res.latitude
        const longitude = res.longitude
        qqmapsdk.reverseGeocoder({
          location: {
            latitude: latitude,
            longitude: longitude
          },
          success: function (res) {
            qqmapsdk.reverseGeocoder({
              location: {
                latitude: latitude,
                longitude: longitude
              },
              success: function (res) {
                that.setData({
                  location_head: res.result.address
                })
              }
            })
          }
        })
      }
    })
  },
  bindPickerChange1: function (e) {
    this.setData({
      index1: e.detail.value
    })
  },
  bindPickerChange2: function (e) {
    this.setData({
      index2: e.detail.value
    })
  },
  issueXianzhi: function (e) {
    var that = this
    var issueTime = new Date().getTime();
    var location = e.detail.value.location
    var location_head = this.data.location_head
    var title = e.detail.value.title;
    var introduce = e.detail.value.introduce
    var hownew = e.detail.value.hownew;
    var contact = e.detail.value.contact;
    var classify = e.detail.value.classify;
    var wechat = e.detail.value.wechat;
    var price = e.detail.value.price;
    var openID = app.globalData.openID;
    var avatarUrl=app.globalData.userInfo.avatarUrl
    var nickName=app.globalData.userInfo.nickName
    if (classify == "请选择类别" || hownew == "请选择成色" || title == "" || wechat == "" ||
      contact == "" || introduce == "" || location == "" || price=="") {
      wx.showToast({
        title: '请输入完所有信息',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    if (this.data.imgTempUrl.length == 0) {
      wx.showToast({
        title: '请选择至少一张照片',
        icon: 'none',
        duration: 2000
      })
      return ;
    }
    if (location_head != "正在获取当前位置") {
      location = location_head + location;
    }
    this.setData({
      btnIsUsing: true
    })
    var _tempFilePaths = this.data.imgTempUrl
    for (var i = 0; i < _tempFilePaths.length; i++) {
      wx.uploadFile({
        url: app.globalData.serverUrl + "/xianzhi/uploadImg",
        filePath: _tempFilePaths[i],
        name: 'image',
        header: {
          'content-type': 'multipart/form-data',
        },
        success: function (res) {
          that.data.finalImgUrl.push(res.data);
          if (that.data.finalImgUrl.length == that.data.imgTempUrl.length) {
            var finalImgUrl = JSON.stringify(that.data.finalImgUrl);
            wx.request({
              url: app.globalData.serverUrl + "/xianzhi/issueXianzhi",
              method: "POST",
              header: {
                'content-type': 'application/json'
              },
              data: {
                issueTime: issueTime,
                location: location,
                openID: openID,
                wechat: wechat,
                introduce: introduce,
                classify: classify,
                title: title,
                hownew: hownew,
                contact: contact,
                finalImgUrl: finalImgUrl,
                watchTimes: 0,
                avatarUrl: avatarUrl,
                nickName: nickName,
                price:price
              },
              success: function (e) {
                wx.showToast({
                  title: '发布成功',
                  duration: 2000,
                  success: function () {
                    setTimeout(function () {
                      wx.navigateBack({
                      })
                    }, 2000)
                  }
                })
              }
            })
          }
        }
      })
    }
  },
  chooseImg: function (e) {
    var that = this
    wx.chooseImage({
      count: 3,
      sizeType: ['original'],
      sourceType: ['album', 'camera'],
      success(res) {
        that.setData({
          imgTempUrl: res.tempFilePaths
        })
      }
    })
  }
})