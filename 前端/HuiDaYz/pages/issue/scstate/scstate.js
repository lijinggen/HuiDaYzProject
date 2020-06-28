const app = getApp()
Page({
  data: {
    imageList: [],
    finalImageList: [],
    content: "",
    issuing: false
  },
  onLoad: function(options) {},
  chooseImage: function(e) {
    var that = this;
    wx.chooseImage({
      count: 9 - that.data.imageList.length,
      sizeType: ['original'],
      sourceType: ['album', 'camera'],
      success(res) {
        if (that.data.imageList.length != 0) {
          var imageList = that.data.imageList;
          for (var i = 0; i < res.tempFilePaths.length; i++) {
            imageList.push(res.tempFilePaths[i]);
          }
          that.setData({
            imageList: imageList
          })
        } else {
          that.setData({
            imageList: res.tempFilePaths
          })
        }
      }
    })
  },
  issue: function(e) {
    const that = this
    var imageList = this.data.imageList
    var openID = app.globalData.openID;
    var avatarUrl = app.globalData.userInfo.avatarUrl
    var nickName = app.globalData.userInfo.nickName
    var content = this.data.content
    if (content == "") {
      wx.showToast({
        title: '请填写内容',
        icon: "none"
      })
      return;
    }
    this.setData({
      issuing: true
    })
    if(imageList.length!=0){
      for (var i = 0; i < imageList.length; i++) {
        wx.uploadFile({
          url: app.globalData.serverUrl + "/scstate/uploadImg",
          filePath: imageList[i],
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
            if (that.data.finalImageList.length == that.data.imageList.length) {
              var finalImageList = JSON.stringify(that.data.finalImageList);
              wx.request({
                url: app.globalData.serverUrl + "/scstate/issueScstate",
                method: "POST",
                header: {
                  'content-type': 'application/json'
                },
                data: {
                  openID: openID,
                  avatarUrl: avatarUrl,
                  nickName: nickName,
                  issueTime: new Date().getTime(),
                  isTop: 0,
                  finalImageList: finalImageList,
                  admireTimes: 0,
                  content: content
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
    }else{
      wx.request({
        url: app.globalData.serverUrl + "/scstate/issueScstate",
        method: "POST",
        header: {
          'content-type': 'application/json'
        },
        data: {
          openID: openID,
          avatarUrl: avatarUrl,
          nickName: nickName,
          issueTime: new Date().getTime(),
          isTop: 0,
          finalImageList: null,
          admireTimes: 0,
          content: content
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
  getContent: function(e) {
    this.setData({
      content: e.detail.value
    })
  },
  deleteImage: function(e) {
    var that = this;
    var imageList = that.data.imageList;
    var index = e.currentTarget.dataset.index; 
    wx.showModal({
      title: '提示',
      content: '确定要删除此图片吗？',
      success: function(res) {
        if (res.confirm) {
          imageList.splice(index, 1);
        } else if (res.cancel) {
          return false;
        }
        that.setData({
          imageList: imageList
        });
      }
    })
  }
})