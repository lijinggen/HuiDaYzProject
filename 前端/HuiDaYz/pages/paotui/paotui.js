const app=getApp()
Page({
  data: {
    page:0,
    maxPage:0,
    paotuiList:new Array
  },
  onLoad: function (options) {
    wx.stopPullDownRefresh()
    var that=this
    wx.request({
      url: app.globalData.serverUrl + "/paotui/loading?page=" + that.data.page,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success:function(res){
 
        if(that.data.paotuiList.length==0){
          that.setData({
            paotuiList:res.data
          })
        }
        else {
          var paotuiList=that.data.paotuiList
          for(var i=0;i<res.data.length;i++){
            paotuiList.push(res.data[i]);
          }
          that.setData({
            paotuiList:paotuiList
          })
        }
        wx.request({
          url: app.globalData.serverUrl + "/paotui/getMaxPage",
          method: "GET",
          header: {
            'content-type': 'application/json'
          },
          success: function (res) {
            that.setData({
              maxPage: res.data
            })
          }
        })
      }
    })
  },
  onPullDownRefresh: function () {
    this.setData({
      page:0,
      paotuiList:new Array
    })
    this.onLoad()
  },
  onReachBottom: function () {
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
  issuePaotui:function(){
    wx.navigateTo({
      url: "../issue/paotui/paotui",
    })
  }
})