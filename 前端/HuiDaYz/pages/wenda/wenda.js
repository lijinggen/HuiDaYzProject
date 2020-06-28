const app=getApp()
Page({

  data: {
    page:0,
    wendaList:[],
    isFold:new Array,
    anr:new Array,
    commentContent:new Array
  },
  onLoad: function (options) {
    var that=this
    
    this.setData({
      page:0,
      isFold:new Array,
      anr:new Array
    })
    var isFold=this.data.isFold
    var tempAnr=this.data.anr
    var commentContent=this.data.commentContent
    wx.request({
      url: app.globalData.serverUrl+"/wenda/loading?page="+that.data.page,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success:function(e){
        for(var i=0;i<e.data.length;i++){
          if(e.data[i].finalImgUrl!=null)
            e.data[i].finalImgUrl=JSON.parse(e.data[i].finalImgUrl)
            isFold.push(false)
            tempAnr.push({})
            commentContent.push("")
        }
        that.setData({
          wendaList:e.data,
          isFold:isFold,
          anr:tempAnr,
          commentContent:commentContent
        })
      }
    })
  },
  issueWenda:function(res){
    wx.navigateTo({
      url: '../issue/wenda/wenda',
    })
  },
  onReachBottom: function() {
    var page = this.data.page+1;
    var that=this
    var isFold=this.data.isFold
    var tempAnr=this.data.anr
    var commentContent=this.data.commentContent
    this.setData({
      page:page+1
    })
    wx.request({
      url: app.globalData.serverUrl+"/wenda/loading?page="+page,
      method: "GET",
      header: {
        'content-type': 'application/json'
      },
      success:function(e){
        console.log(e)
        var wendaList=that.data.wendaList
        for(var i=0;i<e.data.length;i++){
          e.data[i].finalImgUrl=JSON.parse(e.data[i].finalImgUrl)
          isFold.push(false)
          tempAnr.push({})
          wendaList.push(e.data[i]);
          commentContent.push("")
        }
        console.log(wendaList)
        that.setData({
          wendaList:wendaList,
          anr:tempAnr,
          commentContent:commentContent
        })
      }
    })
  },

  
  donghua:function(e){
    var index=e.currentTarget.dataset.index;
    var isFold=this.data.isFold;
    var tempAnr=this.data.anr
    isFold[index]=!isFold[index]
    var that=this

    let option = {
      duration: 500, // 动画执行时间
      timingFunction: 'ease-in-out' // 动画执行效果
    };

    if(isFold[index]){
      this.setData({
        isFold:isFold,
      })

      var aar = wx.createAnimation(option);// 创建动画
      tempAnr[index]=aar
      aar.height('400rpx').step();
      tempAnr[index]=aar.export()
      that.setData({
        anr:tempAnr
      });
    }else{
      var aar = wx.createAnimation(option);// 创建动画
      tempAnr[index]=aar
      aar.height('0rpx').step();
      tempAnr[index]=aar.export()
      that.setData({
        anr:tempAnr,
        isFold:isFold
      });
    }
  },
  getCommentContent:function(e){
    var content=e.detail.value
    var index=e.currentTarget.dataset.index
    var commentContent=this.data.commentContent
    commentContent[index]=content
    this.setData({
      commentContent:commentContent
    })
  },
  issueComment:function(e){

    var index=e.currentTarget.dataset.index
    var comment=this.data.commentContent[index]
    var belongID=e.currentTarget.dataset.belongid
    wx.request({
      url:app.globalData.serverUrl+"/wenda/issueComment",
      method: "POST",
      header: {
        'content-type': 'application/json'
      },
      data:{
        commentContent:comment,
        issueTime:new Date().getTime(),
        belongID:belongID,
        avatarUrl: app.globalData.userInfo.avatarUrl,
        nickName: app.globalData.userInfo.nickName,
        admireTimes:0,
        openID:app.globalData.openID
      },
      success:function(e){
        
      }
    })
  }
})