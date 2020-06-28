const app = getApp()
Page({
  data: {
    xianzhiList: [],
    page: 0,
    maxPage: 0
  },
  onLoad: function(options) {
    var target = options.target
    var that = this;
    wx.request({
      url: app.globalData.serverUrl + '/xianzhi/search?target=' + target,
      method: "GET",
      data: {},
      header: {
        'content-type': 'application/json'
      },
      success: function(res) {
        for (var i = 0; i < res.data.length; i++) {
          res.data[i].finalImgUrl = JSON.parse(res.data[i].finalImgUrl)
        }
        that.setData({
          xianzhiList: res.data
        })
        wx.request({
          url: app.globalData.serverUrl + '/xianzhi/getTempMaxPageNumber',
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
  detailXianzhi: function(e) {
    wx.navigateTo({
      url: '../detail/xianzhi/xianzhi?xianzhiID=' + e.currentTarget.dataset.xianzhiid,
    })
  },
  loadTempList: function() {
    var that=this
    wx.request({
      url: app.globalData.serverUrl + '/xianzhi/loadTempList?page=' +that.data.page,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success: function (res) {
        for (var i = 0; i < res.data.length; i++) {
          res.data[i].finalImgUrl = JSON.parse(res.data[i].finalImgUrl)
        }
        that.setData({
          xianzhiList: res.data
        })
      }
    })
  },
  nextPage: function() {
    this.data.page = this.data.page + 1;
    if (this.data.page <= this.data.maxPage){
      this.loadTempList();
    }
    else {
      this.data.page = this.data.page - 1;
      wx.showToast({
        title: '已经是最后一页了',
        icon: "none"
      })
    }
  },
  lastPage: function() {
    this.data.page = this.data.page - 1;
    if (this.data.page >= 0)
      this.loadTempList();
    else {
      this.data.page = this.data.page + 1;
      wx.showToast({
        title: '已经到达首页',
        icon: "none"
      })
    }
  },
})