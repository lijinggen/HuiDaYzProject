const app=getApp()
Page({
  data: {
    showloveList:[]
  },
  onLoad: function (options) {
    wx.stopPullDownRefresh()
    var that=this
    wx.request({
      url: app.globalData.serverUrl + '/showlove/loading',
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success:function(e){
        that.setData({
          showloveList:e.data
        })
      }
    })
  },
  issueShowlove:function(e){
    wx.navigateTo({
      url:"../issue/showlove/showlove",
    })
  },
  previewImg:function(e){
    wx.previewImage({
      current:e.currentTarget.dataset.imgurl,
      urls: [e.currentTarget.dataset.imgurl]
    })
  },
  detailShowlove:function(e){
    wx.navigateTo({
      url: "../detail/showlove/showlove?showloveID=" + e.currentTarget.dataset.showloveid,
    })
  }
})