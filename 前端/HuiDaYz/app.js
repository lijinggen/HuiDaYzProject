App({
  onLaunch: function() {
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    wx.login({
      success: res => {
        var that = this
        wx.request({
          url: that.globalData.serverUrl + "/openID/session",
          method: "GET",
          data: {
            "code": res.code
          },
          header: {
            'content-type': 'application/json'
          },
          success: function(res) {
            that.globalData.openID = res.data.openid
          }
        })
      }
    })
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: res => {
              this.globalData.userInfo = res.userInfo
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
    
  },
  globalData: {
    userInfo: null,
    openID: null,
    serverUrl: "http://192.168.0.118:8080"
  }
})