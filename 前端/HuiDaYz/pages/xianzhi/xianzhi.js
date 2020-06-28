const app = getApp()
Page({
  data: {
    pxopen: false,
    pxshow: false,
    active: true,
    hownewChoice: "全部",
    classifyChoice: "全部",
    hownew: ["全部", "九九新", "九五新", "九成新", "八成新", "七成新", "七成新以下"],
    classify: ["全部", "手机", "电脑", "衣服", "文具", "生活用品", "化妆用品", "其它"],
    xianzhiList: [],
    page: 0,
    maxPage: 0
  },
  onLoad: function() {
    var that = this
    wx.stopPullDownRefresh()
    wx.request({
      url: app.globalData.serverUrl + '/xianzhi/loading?page=' + that.data.page,
      method: "GET",
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
          url: app.globalData.serverUrl + '/xianzhi/getMaxPageNumber',
          method: "GET",
          header: {
            'content-type': 'application/json'
          },
          success: function(res) {
            that.setData({
              maxPage: res.data,
            })
          }
        })
      }
    })
  },
  listpx: function(e) {
    if (this.data.pxopen) {
      this.setData({
        pxopen: false,
        pxshow: false,
        active: true,
      })
    } else {
      this.setData({
        pxopen: true,
        pxshow: false,
        active: false,
      })
    }
  },
  issueXianzhi: function() {
    wx.navigateTo({
      url: '../issue/xianzhi/xianzhi',
    })
  },
  formsubmit: function(e) {
    var target = e.detail.value.sarchContent
    if (target == "") {
      wx.showToast({
        title: '请输入内容',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    wx.navigateTo({
      url: '../tempXianzhi/tempXianzhi?target=' + target,
    })
  },
  onPullDownRefresh: function() {
    this.onLoad();
  },
  nextPage: function() {
    var that = this
    this.setData({
      page: that.data.page + 1
    })
    if (this.data.page <= this.data.maxPage) {
      this.onLoad();

    } else {
      this.setData({
        page: that.data.page - 1
      })
      wx.showToast({
        title: '已经是最后一页了',
        icon: "none"
      })
    }
  },
  lastPage: function() {
    var that=this
    this.setData({
      page: that.data.page - 1
    })
    if (this.data.page >= 0) {
      this.onLoad();
    } else {
      this.setData({
        page: that.data.page + 1
      })
      wx.showToast({
        title: '已经到达首页',
        icon: "none"
      })
    }
  },
  detailXianzhi: function(e) {
    wx.navigateTo({
      url: '../detail/xianzhi/xianzhi?xianzhiID=' + e.currentTarget.dataset.xianzhiid,
    })
  },

})