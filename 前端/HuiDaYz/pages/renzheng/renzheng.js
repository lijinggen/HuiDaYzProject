const app=getApp()

Page({

  data: {
    imageList: [],
    renzheng:null,

  },
  onLoad:function(e){
    var openID=app.globalData.openID
    var that=this
    wx.request({
      url: app.globalData.serverUrl+"/renzheng/loading?openID="+openID,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success:function(e){

        that.setData({
          renzheng:e.data,

        })
      }
    })
  },
  uploadImg:function(e){
    var that = this;
    wx.chooseImage({
      count:1,
      sizeType: ['original'],
      sourceType: ['album', 'camera'],
      success(res) {
          that.setData({
            imageList: res.tempFilePaths
          })
      }
    })
  },
  submit:function(e){
    var imageUrl=this.data.imageList[0]
    wx.uploadFile({
      url: app.globalData.serverUrl + "/renzheng/uploadImg",
      filePath: imageUrl,
      name: 'image',
      header: {
        'content-type': 'multipart/form-data',
      },
      success: function (res) {
        wx.request({
          url: app.globalData.serverUrl + "/renzheng/insert",
          method: "POST",
          header: {
            'content-type': 'application/json'
          },
          data:{
            openID:app.globalData.openID,
            finalImgUrl:res.data
          },
          success(res){
            console.log(res)
          }
        })
      }
    })
  }
})